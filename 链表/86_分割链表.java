/*
步骤：
    1,处理特殊情况，当链表为空或只有一个结点时，直接返回原链表
    2,创建两个新链表，分别用来保存比给定x值小的原链表结点，和比给定x值大于等于的原链表结点
    3,遍历原链表
        将遍历到的结点接在两个新链表其中之一的末尾
    4,拼接两个新链表
    5,返回处理好的链表头结点
*/

class Solution {
    public ListNode partition(ListNode head, int x) {
        
        /*1,处理特殊情况，当链表为空或只有一个结点时，直接返回原链表*/
        if(head == null)    return head;
        else if(head.next == null)  return head;
        
        /*2,创建两个新链表
            分别用来保存比给定x值小的原链表结点，和比给定x值大于等于的原链表结点
            创建两个指针，用来指向新链表的末尾，便于增加结点
        */
        ListNode left=new ListNode(-1), right=new ListNode(-1);
        ListNode left_temp=left, right_temp=right;
        
        /*3,遍历原链表,将遍历到的结点接在两个新链表其中之一的末尾*/
        ListNode temp = head;
        while(temp != null){
            if(temp.val < x){
                left_temp.next = temp;
                left_temp = left_temp.next;
            }else{
                right_temp.next = temp;
                right_temp = right_temp.next;
            }
            temp = temp.next;
        }
      	//确保两个新链表以当前结点结尾，引用指向null
        left_temp.next = null;
        right_temp.next = null;
        
        /*4,拼接两个新链表*/
        left_temp.next = right.next;
        
        /*5,返回处理好的链表头结点*/
        return left.next;
    }
}
