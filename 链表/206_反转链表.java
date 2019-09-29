//反转一个单链表

class Solution {
    public ListNode reverseList(ListNode head) {
        //链表为空或只有一个结点时，则不需要反转，直接返回。
        if(head == null || head.next == null) return head;
        
        ListNode pre = null, curr = head;
        while(curr != null){
            ListNode next = curr;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }
}
