/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 
/*
    要求：时间O（n）空间，空间O（1）
    思路：需要在原链表上进行修改，创建两个分别指向奇偶下标结点的指针
        用来分别连接好两个子链表，
        最后再将奇数下标的子链表的最后一个结点指向偶数下标的第一个结点
        注意：最后一个偶数下标的结点需要指向null
                两个指针不能分开遍历
*/

class Solution {
    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null)   return head;
        
        //分别用来遍历奇数下标和偶数下标链表的两个指针
        ListNode odd = head, even = head.next;
        //用来记录偶数下标链表的头结点，便于后续拼接
        ListNode evenHead = head.next;
        //同时遍历链表，拼接两个子链表
        while(true){
            //顺序（先奇数后偶数）
            if(even.next != null){
                odd.next = odd.next.next;
                odd = odd.next;
            }else   break;
            
            if(odd.next != null){
                even.next = even.next.next;
                even = even.next;
            }else   break;
        }
        //拼接两个子链表
        odd.next = evenHead;
        //处理链表尾结点
        even.next = null;
        return head;
    }
}
