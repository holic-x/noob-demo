package com.sz.demo.test;

import org.flowable.common.engine.impl.AbstractEngineConfiguration;
import org.flowable.common.engine.impl.interceptor.Command;
import org.flowable.common.engine.impl.interceptor.CommandContext;
import org.flowable.engine.impl.cfg.ProcessEngineConfigurationImpl;

/**
 * @ClassName CustomCmd
 * @Description TODO
 * @Author
 * @Date 2020/6/17 15:05
 * @Version
 **/
public class CustomCmd implements Command<Object> {

    String processInstanceId = null;

    String deleteReason = "级联删除操作";

    public CustomCmd(String processInstanceId, String deleteReason) {
        this.processInstanceId = processInstanceId;
        this.deleteReason = deleteReason;
    }

    @Override
    public Object execute(CommandContext commandContext) {
        AbstractEngineConfiguration currentEngineConfiguration = commandContext.getCurrentEngineConfiguration();
        if(currentEngineConfiguration!=null){
            ProcessEngineConfigurationImpl config = (ProcessEngineConfigurationImpl) currentEngineConfiguration;
            config.getExecutionEntityManager().deleteProcessInstance(processInstanceId,deleteReason,true);
        }
        return null;
    }
}
