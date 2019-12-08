/*************************************************************************
    > File Name: 146.LRU-Cache.java
    > Author: 唐以恒
    > Mail: 1021953309tyh@gmail.com 
    > Created Time: 2019年12月09日 星期一 00时17分33秒
 ************************************************************************/
//32ms
class LRUCache {
    Map<Integer, Node> map;   //通过键key快速定位Node,实现O(1)时间复杂度
    Node head;      //链表头结点
    Node tail;      //链表尾结点
    int size;       //链表大小
    int capacity;      //缓存容量

    //双向链表的结点类
    class Node {
        int key;
        int value;
        Node pre;
        Node next;
        public Node() {}
        public Node(int k, int v) {
            key=k; value=v; 
        }
    }
    //在链表头部增加结点
    void addFirst(int key, int value) {
        Node target = new Node(key, value);
        map.put(key, target);
        target.next = head;
        head.pre = target;
        head = target;    //更新头结点
        if(size == 0) tail = target;    //更新尾结点
        size++;
    }
    //根据键key删除指定结点, 并返回结点的value
    int delete(int key) {
        Node target = map.get(key);
        map.remove(key);
        if(target == head) {    //链表头部
            head = head.next;
            head.pre = null;
        }else if(target == tail) { //链表尾部
            tail = target.pre;  //更新尾结点
            target.pre.next = null;
            target.pre = null;
        }else {     //链表中间
            Node pre = target.pre;
            Node post = target.next;
            pre.next = post;
            post.pre = pre;
            target.pre = null;
            target.next = null;
        }
        size--;
        return target.value;
    }
    //删除链表尾部结点
    void deleteLast() {
        delete(tail.key);
    }
    /*
    实现LRU缓存淘汰策略
    */
    public LRUCache(int capacity) {
        head = new Node();
        tail = new Node();
        size = 0;
        map = new HashMap<>();
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) { 
            return -1;
        }else {
            int ret = delete(key);  //删除原来结点
            addFirst(key, ret); //在链表头部插入新结点
            return ret;
        }
    }
    
    public void put(int key, int value) {
       if(map.containsKey(key)) {   //删除原来结点，再在链表头部插入新结点
           delete(key);
           addFirst(key, value);
       }else if(size < capacity) {  //直接在链表头部插入新结点
           addFirst(key, value);
       }else {  //淘汰链表末尾结点，再在链表头部插入新结点
           deleteLast();    
           addFirst(key, value);
       }
    }
}

