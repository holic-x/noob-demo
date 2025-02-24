import java.util.*;

/**
 * KMW053-寻宝（最小生成树基础题型）
 */
public class Solution2 {
    /**
     * Kruskal 算法
     *
     * @param n     顶点个数
     * @param edges 边及对应权值关系
     */
    public static int kruskal(int n, Map<String, Integer> edges) {
        DisjointSet disjointSet = new DisjointSet();
        disjointSet.init(n + 1); // 并查集初始化

        // 1.边排序：对map的value进行排序，此处转化为List处理
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(edges.entrySet());
        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
//                return o2.getValue().compareTo(o1.getValue());
                return o1.getValue() - o2.getValue(); // 按照value从小到大
            }
        });

        // 2.边遍历：遍历排序后的边
        int sum = 0;
        for (Map.Entry<String, Integer> entry : list) {
            String[] edge = entry.getKey().split("->");
            int u = Integer.valueOf(edge[0]), v = Integer.valueOf(edge[1]);
            if (!disjointSet.isSame(u, v)) {
                // 如果u、v不在一个集合，加入该边（加入并查集）
                disjointSet.join(u, v);
                System.out.println(u + "->" + v); // 输入加入的边
                sum += entry.getValue(); // 累加边路径
            }
        }

        // 返回结果
        System.out.println("最小路径总和：" + sum);
        return sum;
    }


    public static void main(String[] args) {
        // 输入控制
        Scanner sc = new Scanner(System.in);
        System.out.println("1.输入V顶点数量，E边数");
        int v = sc.nextInt();
        int e = sc.nextInt();
        System.out.println("2.输入边关系（a b c）表示a连接b，权值为c");
        // 通过Map<edge,val>存储边的权值关系(key为`a->b`,value为`c`)
        Map<String, Integer> edges = new HashMap<>();
        while (e-- > 0) {
            int node1 = sc.nextInt();
            int node2 = sc.nextInt();
            int val = sc.nextInt();
            // 构建无向图的边关系
            edges.put(node1 + "->" + node2, val);
            // edges.put(node2 + "->" + node1, val); 此处只需要判断边，因此只需要校验一条边的两个端点即可，不需要存储两次
        }

        // 调用kruskal算法获取最小连通图的路径总和
        Solution2.kruskal(v, edges);
    }
}

/**
 * 并查集处理
 */
class DisjointSet {

    static int[] father;

    // 1.初始化
    public static void init(int n) {
        father = new int[n];
        for (int i = 0; i < n; i++) {
            father[i] = i;
        }
    }

    // 2.find 寻根
    public static int find(int u) {
        if (u == father[u]) {
            return u;
        }
        father[u] = find(father[u]); // 路径压缩
        return father[u];
    }

    // 3.join 将两个节点加入集合
    public static void join(int u, int v) {
        // 分别寻找u、v的根，构建根连接
        int uRoot = find(u);
        int vRoot = find(v);
        // 如果同根则不执行操作
        if (uRoot == vRoot) {
            return;
        }
        // 如果不同根则构建根连接
        father[vRoot] = uRoot; // v指向u
    }

    // 4.isSame 判断是否同根
    public static boolean isSame(int u, int v) {
        int uRoot = find(u);
        int vRoot = find(v);
        return uRoot == vRoot;
    }
}