package com.noob.redis.aopLock.config;

import com.noob.redis.aopLock.annotation.DistributionLock;
import com.noob.redis.aopLock.annotation.DistributionLockParam;
import com.noob.redis.aopLock.model.Resp;
import com.noob.redis.lock.LockParam;
import com.noob.redis.lock.RedisLock;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 切面类 对springboot中aop切面编程
 */
@Aspect
@Component
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RedisAopAspect {
    public RedisAopAspect(){
        log.info("分布锁 aop init");
    }
    /***
     * 定义切入点
     */
    @Pointcut("execution(@com.noob.redis.aopLock.annotation.DistributionLock * *(..))")
    public void pointCut(){

    }

    @Around(value = "pointCut()")
    public Object aroundMethod(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();

        /////////////////////AOP 能取得的信息 start////////////////////////
//        log.info("目标方法名为:{}",pjp.getSignature().getName());
//        log.info("目标方法所属类的简单类名:{}" , pjp.getSignature().getDeclaringType().getSimpleName());
//        log.info("目标方法所属类的类名:{}", pjp.getSignature().getDeclaringTypeName());
//        log.info("目标方法声明类型:{}" , Modifier.toString(pjp.getSignature().getModifiers()));
//        log.info("目标方法返回值类型:{}" , method.getReturnType());
//        //获取传入目标方法的参数
//        Object[] args = pjp.getArgs();
//        for (int i = 0; i < args.length; i++) {
//            log.info("第{}个参数为:{}" ,(i + 1) , args[i]);
//        }
//        log.info("被代理的对象:{}" , pjp.getTarget());
//        log.info("代理对象自己:{}" , pjp.getThis());
        /////////////////////AOP 能取得的信息 end////////////////////////

        //取得注解对象数据
        DistributionLock lock = method.getAnnotation(DistributionLock.class);
        //分布式锁实际的key
        String lockKey = getRealDistributionLockKey(pjp,lock);
        //创建分布式锁对象 start
        LockParam lockParam = new LockParam(lockKey,lock.tryLockTime(),lock.holdLockTime());
        RedisLock redisLock = new RedisLock(lockParam);
        //创建分布式锁对象 end

        //获取锁
        Boolean holdLock = redisLock.lock();
        log.info("lockKey:{}   holdLock：{} ",lockKey,holdLock);
        if(Boolean.FALSE.equals(holdLock)){
            //获取锁失败后，处理返回结果
            return handleAcquireLockFailReturn(pjp);
        }

        try {
            return pjp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }finally {
            if(redisLock!=null){
                Boolean unlock = redisLock.unlock();
                log.info("释放锁：unlock {}",unlock);
            }
        }
    }

    /**
     * 分布式锁获取失败，处理方法
     * @param pjp
     * @return
     */
    public Object handleAcquireLockFailReturn(ProceedingJoinPoint pjp){
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        Class returnType = method.getReturnType();
        // 开发规范：通常项目都有自己的统一的返回对象，Resp.class可以根据自己现有的进行构建
        if(returnType.equals(Resp.class) ){
            log.info("返回值类型 Resp");
            return Resp.buildFail("业务处理繁忙，请稍后重试");
        }

        throw new RuntimeException("获取锁失败");
    }

    /**
     * 加了DistributionLockParam注解参数值，按照顺序返回list
     * @param pjp
     * @return
     */
    public List<Object> getDistributionLockParamList(ProceedingJoinPoint pjp){
        ArrayList<Object> distributionLockParamList = null;
        MethodSignature signature = ((MethodSignature) pjp.getSignature());
        //得到拦截的方法
        Method method = signature.getMethod();
        //获取方法参数注解，返回二维数组是因为某些参数可能存在多个注解
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
//        log.info("parameterAnnotations:{}",parameterAnnotations);
        //获取全部参数
        Object[] objects = pjp.getArgs();
        for(int i = 0; i < parameterAnnotations.length; i++){
            for(Annotation a: parameterAnnotations[i]){
                if(a.annotationType() == DistributionLockParam.class){
                    //初始化distributionLockParamList
                    if(distributionLockParamList==null){
                        distributionLockParamList = new ArrayList();
                    }
                    //获得参数值
                    Object o = objects[i];
                    distributionLockParamList.add(o);
                }
            }
        }

        return distributionLockParamList;
    }

    /**
     * 加了DistributionLockParam注解参数值，拼接成字符串
     * @param pjp
     * @param lock
     * @return
     */
    public String getDistributionLockParamStr(ProceedingJoinPoint pjp,DistributionLock lock){
        List<Object> distributionLockParamList = getDistributionLockParamList(pjp);
        if(distributionLockParamList!=null && distributionLockParamList.size()>0){
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < distributionLockParamList.size(); i++) {
                Object param = distributionLockParamList.get(i);
                sb.append(lock.delimiter());
                sb.append(param);
            }
            return sb.toString();
        }
        return "";
    }

    /**
     * 返回分布式锁key完整的key
     * @param pjp
     * @param lock
     * @return
     */
    public String getRealDistributionLockKey(ProceedingJoinPoint pjp,DistributionLock lock){
        String distributionLockParamStr = getDistributionLockParamStr(pjp,lock);
        return lock.key().concat(distributionLockParamStr);
    }

}
