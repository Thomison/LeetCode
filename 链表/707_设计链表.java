class MyLinkedList {
    //链表长度
    private int length;
    //链表头结点
    private ListNode head;
    
    //定义链表的内部结点类
    private class ListNode{
        int val;
        ListNode next;
        public ListNode(int x){
            val = x;
        }
    }
    
    /** 构造方法 */
    public MyLinkedList() {
        length = 0;
        head = new ListNode(-1);
    }
    
    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    /* 获取链表中第 index 个节点的值。
          如果索引无效，则返回-1。*/
    public int get(int index) {
        if(index < 0 || index >= length)   
            return -1;
        ListNode temp = head.next;
        for(int i=0; i<index; i++)
            temp = temp.next;
        return temp.val;
    }
    
    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    /* 在链表的第一个元素之前添加一个值为 val 的节点。
          插入后，新节点将成为链表的第一个节点。*/
    public void addAtHead(int val) {
        ListNode addNode = new ListNode(val);
        addNode.next = head.next;
        head.next = addNode;
        length ++;
    }
    
    /** Append a node of value val to the last element of the linked list. */
    /* 将值为 val 的节点追加到链表的最后一个元素。*/
    public void addAtTail(int val) {
        ListNode addNode = new ListNode(val);
        ListNode temp = head;
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = addNode;
        length ++;
    }
    
    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    /* 在链表中的第 index 个节点之前添加值为 val  的节点。
          如果 index 等于链表的长度，则该节点将附加到链表的末尾。
          如果 index 大于链表长度，则不会插入节点。
          如果 index 小于0，则在头部插入节点。
    */
    public void addAtIndex(int index, int val) {
        if(index > length) return;
        if(index < 0){ addAtHead(val); return; }
        if(index == length){ addAtTail(val); return; }
        
        ListNode pre = head, curr = head.next;
        for(int i = 0; i < index; i++){
            pre = pre.next;
            curr = curr.next;
        }
        ListNode addNode = new ListNode(val);
        pre.next = addNode;
        addNode.next = curr;
        length ++;
    }
    
    /** Delete the index-th node in the linked list, if the index is valid. */
    /* 如果索引 index 有效，则删除链表中的第 index 个节点。*/
    public void deleteAtIndex(int index) {
        if(index >= length || index < 0) return;
        
        ListNode curr = head;
        for(int i = 0; i < index; i++){
            curr = curr.next;
        }
        curr.next = curr.next.next;
        length --;
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
