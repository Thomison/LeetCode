/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

/*
    链表删除的常规思路是：使得给定结点node之前结点的指针域指向node之后的结点
    但是本题无法得到给定结点node之前的结点，所以我们可以在删除下一结点之前把其值拷贝给当前结点
    等价于删除了当前结点
    注意：因为不会链表最后一个结点不会被删除，故此方法可行且不用区分情况
*/

class Solution {
    public void deleteNode(ListNode node) {
        //将下一结点存储的值赋给当前结点
        node.val = node.next.val;
        //删除当前结点的下一个结点
        node.next = node.next.next;
    }
}
