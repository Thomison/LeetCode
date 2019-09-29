//给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。

/*
算法：

​ 1，处理特殊情况:当链表为空或者只有一个结点时，不存在重复元素，直接返回原链表

​ 2，创建哑结点、第一层遍历链表的指针pre和curr（防止极端情况：头几个结点就是重复元素，都需要删除）

​ 3，第一层遍历链表，删除所有重复的元素所在的结点

​   1）若当前结点的值和下一结点的值相等，则开始第二层遍历子链表，直到便利到不同值得结点，完成一次性删除多个结点的操作

​   2）若当前结点的值和下一结点的值不相等，继续第一层遍历链表

​ 4，返回哑结点的下一个结点
*/


class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy, curr = head;
        pre.next = curr;
        
        while(curr != null && curr.next != null){
            if(curr.val == curr.next.val){
                ListNode temp = curr.next;
                while(temp.val == curr.val){
                    temp = temp.next;
                    if(temp == null) break;
                }
                pre.next = temp;
                curr = temp;
            }else{
                pre = pre.next;
                curr = curr.next;
            }
        }
        
        return dummy.next;
    }
}
