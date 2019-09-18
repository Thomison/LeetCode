/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public Node() {}

    public Node(int _val,Node _prev,Node _next,Node _child) {
        val = _val;
        prev = _prev;
        next = _next;
        child = _child;
    }
};
*/
//递归实现深度优先搜索，
//实质上是从最底层开始不断的将双向链表插入到上一级child指针指向它的地方
class Solution {
    public Node flatten(Node head) {
        Node temp = head;
        
        while(temp != null) {
            //保存副本
            Node parent_tail = temp.next;
            
            if(temp.child != null) {
                
                Node child = flatten(temp.child);   //递归返回下一级处理好的单级链表
                
                //创建关键结点的指针
                Node parent_head = temp, child_tail = null;
                //遍历下一级的单级双向链表, 找到其尾结点
                Node child_temp = child;
                while(child_temp.next != child_tail) {
                    child_temp = child_temp.next;
                }
                child_tail = child_temp;
                
                //执行插入
                parent_head.next = child;
                child.prev = parent_head;
                if(parent_tail != null){        //temp非尾结点，temp为尾结点则不执行
                    child_tail.next = parent_tail;
                    parent_tail.prev = child_tail;
                }
                //插入完成之后，消除该结点的child指针域
                temp.child = null;  
            }
            
            temp = parent_tail;
        }
        return head;
    }
}

//总结：在进行该层单级双向链表尾部结点的插入操作时，因为之前已经使得temp.next = child
//所以判断该节点是否尾结点时，就不能用temp.next != null, 而是用parent_tail != null, 否则会报空指针错误, null.next ????
