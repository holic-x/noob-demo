package com.noob.algorithm.common150.q146;


import java.util.HashMap;

/**
 * 146 LRU缓存 todo
 */
class LRUCache {
    // 定义哈希表存储元素
    HashMap<Integer, DLinkedNode> cache = new HashMap<>();
    // 维护cache大小和指定容量
    int size, capacity;
    // 维护队列的头尾指针
    DLinkedNode head, tail;

    // 构造函数
    LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        // 使用虚拟的头尾节点
        head = new DLinkedNode();
        tail = new DLinkedNode();
        // 初始化头尾节点指针
        head.next = tail;
        tail.prev = head;
    }

    // get 方法
    public int get(int key) {
        // 判断元素是否存在，如果不存在则返回-1，如果存在则需更新访问顺序
        DLinkedNode findNode = cache.get(key);
        if (findNode == null) {
            return -1;
        }

        // 元素存在，更新访问顺序，并返回值（删除节点，并将节点重新添加到头）
        removeNode(findNode); // 删除指定节点
        addToHead(findNode); // 将节点加入到头部

        return findNode.val;
    }

    // 删除节点（更改指定node的前后节点的指针）
    private void removeNode(DLinkedNode node) {
        node.prev.next = node.next; // 将node的前一个节点的next指针指向其后一个节点
        node.next.prev = node.prev; // 将node的后一个节点的prev指针指向其前一个节点
    }

    // 将节点加入到头部（先更新node的指针，后更新node前后节点的指针，注意顺序，避免值被覆盖）
    private void addToHead(DLinkedNode node) {
        node.prev = head; // 更新node前指针
        node.next = head.next; // 更新node后指针
        head.next.prev = node; // 更新node的下一个节点的prev指针
        head.next = node; // 更新node的上一个节点的next指针
    }

    // 删除队尾节点
    private DLinkedNode removeTail() {
        DLinkedNode target = tail.prev; // 虚拟尾节点的上一个节点即为队尾元素
        removeNode(target);
        return target; // 返回删除节点信息
    }


    // put 方法
    public void put(int key, int val) {
        // 判断元素是否存在
        DLinkedNode findNode = cache.get(key);
        if (findNode == null) {
            // 说明元素不存在，直接将元素插入到cache中,更新队列访问顺序(判断是否超出阈值，超出阈值则需剔除最近未使用的元素)
            DLinkedNode newNode = new DLinkedNode(key, val);
            cache.put(key, newNode);
            addToHead(newNode); // 将元素补充到队头,并加入哈希表

            // 判断是否超出阈值，超出则剔除元素
            if (cache.size() > capacity) {
                // 超出阈值，需剔除一个队尾元素，并删除对应哈希表中的项
                DLinkedNode target = removeTail();
                cache.remove(target.key);
            }
        } else {
            // 元素存在，更新访问顺序(先删后加)
            removeNode(findNode);
            addToHead(new DLinkedNode(key, val));
        }
    }

}

/**
 * 定义双向链表节点
 */
class DLinkedNode {
    // 元素键、值
    int key, val;
    // 前、后指针
    DLinkedNode prev, next;

    // 构造函数
    DLinkedNode() {
    }

    DLinkedNode(int key, int val) {
        this.key = key;
        this.val = val;
    }
}

