package com.noob.algorithm.common150.q295;

import java.util.PriorityQueue;

/**
 * 295 数据流的中位数
 */
public class MedianFinder {

    /**
     * 借助两个优先队列维护数据流元素，以中位数作为分界线处理
     * 1.LessThanOrEqualToQueue（ leftQueue 存储小于等于中位数的元素）
     * 2.MoreThanQueue（rightQueue 存储大于中位数的元素）
     * - 添加元素(如果比较堆顶元素则需判空)
     * - 思路1（比较堆顶元素）：如果 cur <= leftQueueTop(堆顶元素) 加入到leftQueue；如果 cur > rightQueue（小顶堆） 加入到rightQueue
     * - 思路2（比较两个集合元素个数）：
     *  - 相等说明为偶数，下一个数要放leftQueue（先将num放入rightQueue，然后取出rightQueue的堆顶元素放入leftQueue）
     *  - 不相等说明奇数，下一个数要放rightQueue（先将num放入leftQueue，然后取出leftQueue的堆顶元素放入rightQueue）
     *
     * - 获取中位数（区分奇数、偶数）
     * leftQueue（大顶堆）：堆顶元素即为最大的元素
     * rightQueue（小顶堆）：堆顶元素即为最小的元素
     */
    PriorityQueue<Integer> leftQueue;
    PriorityQueue<Integer> rightQueue;

    public MedianFinder() {
        leftQueue = new PriorityQueue<>((x, y) -> (x - y)); // 小顶堆：存储较大的一半集合
        rightQueue = new PriorityQueue<>((x, y) -> (y - x)); // 大顶堆：存储较小的一半集合
    }

    public void addNum(int num) {
        if (leftQueue.size() == rightQueue.size()) {
            // 当两个集合元素个数相等（偶数），需向leftQueue插入元素：将num插入rightQueue，然后将rightQueue的堆顶元素推到leftQueue
            rightQueue.add(num);
            leftQueue.add(rightQueue.poll());
        } else {
            // 当两个集合元素个数不相等（奇数），需向rightQueue插入元素：将num插入leftQueue，然后将leftQueue的堆顶元素推到rightQueue
            leftQueue.add(num);
            rightQueue.add(leftQueue.poll());
        }
    }

    public double findMedian() {
        // 如果两个队列个数相同则说明数据总数为偶数
        if (leftQueue.size() == rightQueue.size()) {
            return (leftQueue.peek() + rightQueue.peek()) / 2.0;
        } else {
            return leftQueue.peek();
        }
    }

}
