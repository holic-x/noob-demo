package com.noob.thread.safe;

/**
 * @description:
 * @author：holic-x
 * @date: 2018/3/26
 * @Copyright： 无所事事是薄弱意志的避难所
 */

import java.util.LinkedList;

/**
 * 构建生产者和消费者进行交互的产品类
 */
class Product {
    private int id;
    private String name;
    public Product() { }
    public Product(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


/**
 * 构建Table模拟容器操作
 */
class Table {
    // 1.用LinkedList模拟桌子
    LinkedList<Product> table = new LinkedList<>();
    // 2.设定桌子的最大容量
    public int max = 10;
    // 3.获取当前桌子的产品数目
    public int size() {
        return table.size();
    }
    // 4.定义相应的事件:生产者生产产品、消费者消费产品
    /**
     * 生产者生产产品需要注意的问题：
     * 当同时有多个厨师发现桌子上的食物缺少，同时想要进行生产，但此时并不需要太多的资源，
     * 则此时他们是属于竞争关系的，因此需要对生产产品的方法加“锁”，使得每次只能有一个厨师放入产品
     */
    public synchronized void put(Product p) {
        /**
         * 当当前放入的食品数目等于餐桌的最大容量，则厨师需要进入等待状态
         */
        while (size() == max) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        /**
         * 如果当前放入产品的数目没有达到餐桌的最大容量，则生产者可以放入产品，随后唤醒所有消费者和生产者
         * 假如说生产者已经满了，需要单独唤醒指定的内容，则可通过juc提供的相关内容实现
         */
        table.add(p);
        notifyAll();
    }

    public synchronized Product take() {
        /**
         * 消费者在取走产品的时候也要判断餐桌上是否有相应的产品，如果桌子上没有产品，则消费者需要进入等待
         */
        while (size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 如果桌子上有产品，则取走最后一个产品随后唤醒所有的线程
        Product p = table.removeLast();
        notifyAll();
        // 返回当前被取走的产品
        return p;
    }
}

/**
 * 模拟生产者
 */
class Producer extends Thread{
    private Table table ;

    public Producer(Table table) {
        super();
        this.table = table;
    }
    @Override
    public void run() {
        int i = 0;
        while(true)
        {
            //生产者生产一个产品
            Product p = new Product(++i,"产品"+i);
            //将生产的产品放入桌子上
            table.put(p);
            System.out.println("生产者生产了一个产品放到桌子上，产品id为"+p.getId());
            //模拟生产的过程，休眠2s
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}



/**
 * 模拟消费者
 */
class Customer extends Thread {
    private Table table;

    public Customer(Table table) {
        super();
        this.table = table;
    };
    @Override
    public void run() {
        while(true)
        {
            Product p = table.take();
            System.out.println("消费者消费了一个产品，产品id为"+p.getId()+",产品名称为："+p.getName());
            //模拟消费者消费过程，休眠2s
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * 模拟生产者消费者操作
 */
public class PCDemo {
    /**
     * 首先，要保证生产者和消费者操作的是同一张桌子
     */
    final Table table = new Table();
    public void init()
    {
        // 模拟5个生产者
        for(int i=0;i<5;i++)
        {
            Producer p = new Producer(table);
            p.start();
        }
        // 模拟3个消费者
        for(int i=0;i<3;i++)
        {
            Customer c = new Customer(table);
            c.start();
        }
        //单独新建一个线程，用于监测桌子上产品容量
        new Thread(new Runnable(){
            @Override
            public void run() {
                while(true)
                {
                    System.out.println("当前桌子容量为："+table.size());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    public static void main(String[] args) {
        new PCDemo().init();
    }
}
