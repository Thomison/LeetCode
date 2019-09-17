/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

/*
    要求：
        不能只是单纯的交换结点的值，而是完整的交换结点，要在原链表上执行操作
    思路：
        1)先通过快慢指针的方法找到链表的中间结点，从中间结点断开，分为两个子链表
        2)再反转后半部分的子链表
        3)同时遍历两个子链表，间隔执行插入操作
*/

class Solution {
    public void reorderList(ListNode head) {
        if(head == null || head.next == null || head.next.next == null) {
            return;
        }
        ListNode head2 = breakFromMiddle(head);
        head2 = reverse(head2);
        insertInterval(head, head2);
    }
    /*从链表的中间结点断开，返回后一半链表的头结点*/
    public ListNode breakFromMiddle(ListNode head) {
        ListNode slow = head, fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode head2 = slow.next;
        slow.next = null;
        return head2;
    }
    /*反转链表，返回新链表的头结点*/
    public ListNode reverse(ListNode head) {
        ListNode pre = null, curr = head;
        while(curr != null) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }
    /*将参数中第二个链表head2间隔插入到第一个链表head1中*/
    public void insertInterval(ListNode head1, ListNode head2){
        ListNode temp1 = head1, temp2 = head2;
        while(temp2 != null) {
            ListNode temp2_next = temp2.next, temp1_next = temp1.next;
            temp1.next = temp2;
            temp2.next = temp1_next;
            temp1 = temp1_next;
            temp2 = temp2_next;
        }
    }
}
