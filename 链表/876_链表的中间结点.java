
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/*
思路：采用快慢指针，一次遍历找到链表中间结点
*/
class Solution {
    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
/*
思路二：计数法，两次遍历找到链表中间结点
*/
class Solution {
    public ListNode middleNode(ListNode head) {
        ListNode temp = head;
        int length = 0;
        while(temp != null){
            temp = temp.next;
            length ++;
        }
        int middle = 0;
        middle = length / 2 + 1;
        temp = head;
        int index = 0;
        while(true){
            index ++;
            if(index == middle){
                return temp;
            }
            temp = temp.next;
        }
    }
}
