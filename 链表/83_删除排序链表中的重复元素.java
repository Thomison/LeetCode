/*
步骤：
    1,处理特殊情况:当链表为空或者只有一个结点时，不存在重复元素，直接返回原链表
    2,遍历链表，删除重复的元素所在的结点
        创建两个指针pre和curr，用来实现遍历和删除操作
        遍历时分两种情况：
            1) curr指向的结点是重复元素时，执行删除操作，pre不用后移，curr后移一个单位
            2) curr指向的结点不是重复元素时，不执行删除操作，pre和curr一起需要后移一个单位
    3,返回处理后的链表头部（首结点）
*/

class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        
        /*1,处理特殊情况:当链表为空或者只有一个结点时，不存在重复元素，直接返回原链表*/
        if(head == null)    return head;
        else if(head.next == null)  return head;
        
        /*2,遍历链表，删除重复的元素所在的结点*/
        //创建两个指针pre和curr，用来实现遍历和删除操作
        ListNode pre=null, curr=head;
        //从第二个结点开始遍历，防止头结点的值和哑结点的null值比较引发报错
        pre = curr;
        curr = curr.next;
        while(curr != null){
            //curr指向的结点是重复元素时，执行删除操作，pre不用后移，curr后移一个单位
            if(curr.val == pre.val){
                curr = curr.next;
                pre.next = curr;
            }
            //curr指向的结点不是重复元素时，不执行删除操作，pre和curr一起需要后移一个单位
            else{
                curr = curr.next;
                pre = pre.next;
            }
        }
        
        /*3,返回处理后的链表头部（首结点）*/
        return head;
    }
}


//递归
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        head.next = deleteDuplicates(head.next);
        if(head.val == head.next.val) head = head.next;
        return head;
    }
}
