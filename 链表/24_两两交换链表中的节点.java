//给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。

// 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

/*
首先创建一个哑结点，作为需要输出的新链表。
然后遍历输入的链表，每遍历到两个结点（一组），
交换两个结点位置后接到新链表末尾，直到遍历到输入链表的末尾。
*/

class Solution {
    public ListNode swapPairs(ListNode head) {
        //特殊情况
        if(head == null) return null;
        
        //创建需要输出的新链表开头的哑结点
        ListNode dummy = new ListNode(0);
        ListNode pre = new ListNode(0);
        //指向新链表末尾两个结点的两个指针
        ListNode curr = dummy;
        pre.next = curr;
        
        //遍历输入的链表
        boolean flag = true;//判断结点的奇偶数位置
        while(head != null){
            if(flag){
                //奇数位置的结点直接接在新链表尾部
                curr.next = head;
                flag = false;
                //移动指针(pre、curr、head)
                head = head.next;
                curr = curr.next;
                pre = pre.next;
                //确保`curr`为新链表的尾结点。
                curr.next = null;
            }
            else{
                //保留副本
                ListNode temp = head.next;
                //偶数位置的结点需要插入到倒数第二个结点的位置
                pre.next = head;
                head.next = curr;
                flag = true;
                //移动指针(pre、head)
                pre = pre.next;
                head = temp;
                //确保`curr`为新链表的尾结点。
                curr.next = null;
            }
        }
        return dummy.next;
    }
}
