package com.algorithm.bankerAlgorithm;

/**
 * 1.资源状态：资源状态表相关属性定义
 */
class ResourceState {

    // 进程数
    private int processNum;
    // 资源类型数
    private int resourceNum;

    // i类进程对j类资源的最大需求量
    private int max[][];
    //i类进程对j类资源的占有量
    private int allocation[][];
    //当前系统资源的可分配量（剩余量）
    private int available[];
    //i类进程对j类资源的尚需求量（剩余需求量 =max[][]-allocation[][]）
    private int need[][];

    //  变量初始化构造方法实现
    ResourceState(int processNum,int resourceNum,int max[][], int allocation[][], int available[], int need[][]) {
        this.processNum = processNum;
        this.resourceNum = resourceNum;
        // 需要初始化的有四个部分：max、allocation、available、need
        this.max = max;
        this.allocation = allocation;
        this.available = available;
        this.need = need;
    }

    // 构造方法
    ResourceState(ResourceState rs) {
        this.max = rs.max;
        this.allocation = rs.allocation;
        this.available = rs.available;
        this.need = rs.need;
    }

    // 状态表打印数据输出
    public void showData() {
        //打印当前时刻各个进程对各类资源的数据
        System.out.println("Process  \t\t\t\tmax    \t\t\tAlloc   \t\t\tAvail   \t\t\tneed");
        for (int i = 0; i < this.processNum ; i++) {
            System.out.print("P" + i + ":\t\t\t\t\t\t");
            for(int j=0;j<this.resourceNum;j++){
                System.out.print(this.max[i][j] + " ");
            }
            System.out.print("\t\t\t");
            for(int j=0;j<this.resourceNum;j++){
                System.out.print(this.allocation[i][j] + " ");
            }
            System.out.print("\t\t\t");
            for(int j=0;j<this.resourceNum;j++){
                System.out.print(this.available[j] + " ");
            }
            System.out.print("\t\t\t");
            for(int j=0;j<this.resourceNum;j++){
                System.out.print(this.need[i][j] + " ");
            }
            System.out.println();  //换行
        }
    }

    public int[][] getMax() {
        return max;
    }

    public void setMax(int[][] max) {
        this.max = max;
    }

    public int[][] getAllocation() {
        return allocation;
    }

    public void setAllocation(int[][] allocation) {
        this.allocation = allocation;
    }

    public int[] getAvailable() {
        return available;
    }

    public void setAvailable(int[] available) {
        this.available = available;
    }

    public int[][] getNeed() {
        return need;
    }

    public void setNeed(int[][] need) {
        this.need = need;
    }

    public int getProcessNum() {
        return processNum;
    }

    public void setProcessNum(int processNum) {
        this.processNum = processNum;
    }

    public int getResourceNum() {
        return resourceNum;
    }

    public void setResourceNum(int resourceNum) {
        this.resourceNum = resourceNum;
    }
}


/**
 * 银行家算法实现
 */
public class BankerAlgorithmDemo {

    /**
     * 2.银行家算法实现
     * @param rs      资源状态
     * @param p       对应进程序号
     * @param request request数组对应资源请求量
     */
    public void callBankerAlgorithm(ResourceState rs, int p, int request[]) {
        // 定义布尔变量，查看request是否满足银行家算法要求
        boolean validFlag = false;// 初始化状态为false
        //2.1 先进行判断 ：request<need && request<available
        for (int j = 0; j < rs.getResourceNum(); j++) {
            // 比较每个进程对每类资源的请求量是否小于需求量
            if (request[j] > rs.getNeed()[p][j]) {
                System.out.println("资源申请不合理");
                return;
            }
            // 比较每个进程对每类资源的请求量是否小于当前系统可提供的资源数目
            if (request[j] > rs.getAvailable()[j]) {
                System.out.println("资源申请超过最大可用资源数，资源不够分配");
                return;
            } else {
                validFlag = true;   //满足条件则将状态置为true
            }
        }
        // 循环比较完毕，满足条件则执行“假装分配操作”
        if (validFlag) {
            this.proMalloc(rs,p, request);
            System.out.println("输出“模拟分配”后的资源数据如下：");
            rs.showData();
            System.out.println("提示：银行家算法调用完毕，现进入安全性检测");
            // 进入下一步的安全性检测
            if (this.safety(rs)) {
                System.out.println("\n安全性检测完毕，允许分配，且安全序列表示如上述所示");
            } else {
                System.out.println("\n找不到安全序列，进程资源申请不予满足");
                this.rollback(rs,p, request);  //调用回溯算法实现状态复原
            }
        }
    }

    // 试探性分配数据
    private void proMalloc(ResourceState rs,int p, int request[]) {
        for (int j = 0; j < rs.getResourceNum(); j++) {
            // 系统可用资源量减少
            rs.getAvailable()[j] -= request[j];
            // 当前进程对某类资源的剩余资源量减少
            rs.getNeed()[p][j] -= request[j];
            // 当前进程对某类资源的占有量增加
            rs.getAllocation()[p][j] += request[j];
        }
    }

    // 回溯算法实现
    private void rollback(ResourceState rs,int p, int request[]) {
        for (int j = 0; j < rs.getResourceNum(); j++) {
            // 系统可用资源量复原
            rs.getAvailable()[j] -= request[j];
            // 当前进程对某类资源的剩余资源量复原
            rs.getNeed()[p][j] -= request[j];
            // 当前进程对某类资源的占有量复原
            rs.getAllocation()[p][j] += request[j];
        }
    }

    /**
     * 3.安全性算法实现
     *
     * @return
     */
    public boolean safety(ResourceState rs) {    // work:当前系统资源的工作量，用于safety算法（与available[]相关联），将当前系统各类资源的剩余量传给work
        int work[] = rs.getAvailable();
        // 当前该类进程的安全性状态
        boolean finish[] = new boolean[rs.getProcessNum()];
        for (int k = 0; k < rs.getProcessNum(); k++) {
            // 每个状态初始化安全状态都为false
            finish[k] = false;
        }
        for (int i = 0; i < rs.getProcessNum(); i++) {
            // 安全性状态为true说明已经遍历过，不需要再重复遍历
            if (!finish[i]) {
                // 若当前每个进程对各类资源的需求量都小于当前系统各类资源的剩余量
                boolean validFlag = true;
                for(int j=0;j<rs.getResourceNum();j++){
                    // 若出现不满足条件则设置validFlag为false并跳出循环
                    if(rs.getNeed()[i][j] > work[j]){
                        validFlag = false;
                        break;
                    }
                }
                // 校验validFlag状态，满足条件则释放资源，修改该进程的安全性状态
                if(validFlag){
                    // 为了查看过程修改这个available,但实际是不能修改这个内容，只能借助Work工作向量实现 work[2] += rs.getAllocation()[i][2];
                    //this.available[0]+=this.allocation[i][0];
                    for(int j=0;j<rs.getResourceNum();j++){
                        work[j] += rs.getAllocation()[i][j];
                    }
                    finish[i] = true;
                    // 打印当前的进程序号，记录安全序列
                    System.out.print("P" + i + "--->");
                    // 重新回到起点进行检测（从p0-pi依次进行分析）
                    i = -1;
                    //System.out.println(this.available[0]+" "+this.available[1]+" "+this.available[2]);
                }
            }
        }
        // 判断每个状态是否为true，即判断每个进程是否处于安全性状态
        for (int a = 0; a < rs.getProcessNum(); a++) {
            // 若出现false值则说明不满足安全性校验
            if (!finish[a]) {
                return false;
            }
        }
        return true;
    }
}

