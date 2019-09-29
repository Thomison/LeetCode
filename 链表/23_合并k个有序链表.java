// 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。

//在基于合并两个有序链表的基础上，我们可以遍历链表数组，每次合并两个链表，需要执行合并操作（k-1）次，合并操作采用递归方式来实现。
//emmmm这样做出来之后呢，耗费时间都太多了。于是乎放弃了遍历链表数组的做法，改采用分治算法来实现，

//分治
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        //特殊情况，合并空的链表
        if(lists.length == 0)return null;
        //链表数组不可划分
        if(lists.length == 1)return lists[0];
        
        //分割链表数组
        ListNode[] lists_left = 
            Arrays.copyOfRange(lists, 0, lists.length/2);
        ListNode[] lists_right = 
            Arrays.copyOfRange(lists, lists.length/2, lists.length);
        
        //合并两个链表数组
        return mergeTwoLists(
            mergeKLists(lists_left), mergeKLists(lists_right));
    }
    //合并两个有序链表
    public ListNode mergeTwoLists(ListNode l1, ListNode l2){
        if(l1 == null){
            return l2;
        }
        else if(l2 == null){
            return l1;
        }
        else if(l1.val <= l2.val){
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }
        else{
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}
