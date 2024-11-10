package com.noob.algorithm.common150.q502;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 502 IPO
 */
public class Solution2 {

    // 贪心 + 堆（小顶堆维护项目，大顶堆维护利润）
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {

        int len = profits.length; // 项目个数

        List<int[]> pros = new ArrayList<>();
        // 将项目的启动资金和净利润组装到一起
        for (int i = 0; i < len; i++) {
            pros.add(new int[]{capital[i], profits[i]});
        }

        // 借助优先队列（小顶堆）维护项目（按照启动资金capital进行排序）
        PriorityQueue<int[]> proQueue = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // 根据项目净利润进行排序（profits），借助优先队列(大顶堆)
        PriorityQueue<Integer> profitQueue = new PriorityQueue<>((a, b) -> b - a);

        /**
         * 项目选择有两个部分处理：一类是启动的时候就满足启动条件的项目，一类是启动的时候还暂时不满足启动条件的项目（后期随着w的累加可能会达到启动条件，需额外判断）
         * 1.初始化：将启动资金满足<=w(起始资金)的项目装入profitQueue（可启动项目的利润集），其他项目排入proQueue（暂时还不满足启动条件的项目）
         *  - 引入堆概念，是为了优化操作效率，从堆顶获取到所需的要素
         * 2.决策：因为每执行完一个项目，w就会随之改变，因此在选择下一个项目之前还需更新本次可启动选择的项目
         *  - 即每次执行决策之前，需要判断剩余的项目中是否有满足启动条件的项目，将其加入到利润集中供选择。随后从更新后的利润集中选择利润最大的项目执行
         */
        // 1.初始化（划分可启动项目（利润集）、暂时还不满足启动条件的项目）
        for (int i = 0; i < len; i++) {
            if (pros.get(i)[0] <= w) {
                profitQueue.add(pros.get(i)[1]); // 将初始时满足启动条件的项目载入利润集合（表示这些项目可以直接启动）
            } else {
                proQueue.add(pros.get(i)); // 剩余不满足初始化启动条件的项目则按照启动资金消耗载入小顶堆
            }
        }

        // 2.决策：更新利润集、择选、更新利润
        while (k > 0) {
            // 每次执行决策前都需将满足启动条件的项目载入到profitQueue中（即更新profitQueue）
            while (!proQueue.isEmpty() && proQueue.peek()[0] <= w) {
                profitQueue.add(proQueue.poll()[1]);
            }
            // 从当前利润集中选择一个最大的项目执行(如果可执行项目为空，则跳过)
            if (profitQueue.isEmpty()) {
                break;
            }
            w += profitQueue.poll();
            k--; // 一个项目选择完成，计数器-1
        }
        // 返回最大利润
        return w;
    }
}
