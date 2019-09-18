/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int _val,Node _next,Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
};
*/

/*
思路： 
    1）第一次通过next指针遍历链表，只考虑结点的next指针域，
        用来复制原来链表的基本骨架，
        并且建立好原链表每个结点和复制好的新结点的映射关系（键值对），并存储到hashmap<node,node>中
    2）第二次通过next指针遍历两个链表，只考虑原结点的random指针域
        在hashmap中通过键来找值，
        找到值后即为random指向的新链表的新结点，将其赋给当前遍历到的新链表的结点的random域
    3）返回拷贝之后的新链表的头结点
*/

class Solution {
    public Node copyRandomList(Node head) {
        if(head == null) 
            return null;
        Node dummy = new Node(-1, null, null);  //  哑结点, 指向新链表的头结点
        Node temp1 = head, temp2 = dummy;
        Map<Node, Node> nodeMap = new HashMap<>();  // 新老结点映射表, 通过老结点找到新结点
        //第一次遍历原链表，拷贝val和next
        while(temp1 != null) {
            temp2.next = new Node(temp1.val, null, null);
            temp2 = temp2.next;
            nodeMap.put(temp1, temp2);
            temp1 = temp1.next;
        }
        //第二次遍历，拷贝random
        temp1 = head;
        temp2 = dummy.next;
        while(temp1 != null) {
            if(temp1.random != null) {
                temp2.random = nodeMap.get(temp1.random);
            }
            temp1 = temp1.next;
            temp2 = temp2.next;
        }
        return dummy.next;
    }
}
//总结： 时间复杂度O（2n）：遍历了两次链表，n为链表的长度
//      空间复杂度O（2n）：创建了一个hashmap，存储结点映射关系；用于拷贝创建了一个长度为n的新链表
//其他思路： 用新旧结点交替的方法模拟map的效果, 空间复杂度为O(1)，不用创建额外的空间
//           old_node.next = new_node <-> new_node = map.get(old_node)
