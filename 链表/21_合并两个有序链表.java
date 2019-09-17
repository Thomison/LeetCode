/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
//遍历两个链表，以此比较结点值大小，用头插法创建排好序的新链表，空间复杂度O(n)
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //初始化需要返回的输出链表(有一个哑结点)
        ListNode result = new ListNode(0);
        //创建用来遍历两个输入链表的指针
        ListNode l1_point = l1;
        ListNode l2_point = l2;
        //创建用来遍历输出链表的指针
        ListNode resultPoint = result;
        //遍历两个输入链表，比较结点值，值小的用来创建新结点
        while(l1_point != null || l2_point != null) {
            //当两个结点都不为空时，直接比较两个结点的值
            if(l1_point != null && l2_point != null){
                //比较结点值
                if(l1_point.val <= l2_point.val) {
                    resultPoint.next = new ListNode(l1_point.val);
                    resultPoint = resultPoint.next;
                    l1_point = l1_point.next;
                }else {
                    resultPoint.next = new ListNode(l2_point.val);
                    resultPoint = resultPoint.next;
                    l2_point = l2_point.next; 
                }
            }else if(l1_point == null) {
              	resultPoint.next = l2_point;
                break;
            }else {
              	resultPoint.next = l1_point;
                break;
            }
        }
        //返回创建的新链表
        return result.next;
    }
}


//递归，常数级空间浪费
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        
        //返回值：排好序的子链表的头结点
        if(l1.val <= l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}

//迭代，常数级空间浪费(只创建了一个指针)
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
         
        ListNode dummy = new ListNode(-1);
        ListNode temp = dummy;
        while(l1 != null || l2 != null) {
            if(l1 == null) {
                temp.next = l2;
                break;
            }else if(l2 == null) {
                temp.next = l1;
                break;
            }else {
                if(l1.val <= l2.val){
                    temp.next = l1;
                    l1 = l1.next;
                }else {
                    temp.next = l2;
                    l2 = l2.next;
                }
            }
            temp = temp.next;
        }
        return dummy.next;
    }
}
