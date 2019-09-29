//反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。

class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        //当m=n时，不用反转链表
        if(m == n) return head;
        //用于反转链表的两个指针
        ListNode pre = new ListNode(0);
        ListNode curr = head;
        pre.next = curr;
        int flag = 1;
        //用于连接子链表和首尾部分
        ListNode start=null,end=null,left=null,right=null;
        //遍历链表
        while(curr != null){
            if(flag == m-1) left = curr;
            if(flag == n+1) right = curr;
            if(flag == m)   end = curr;
            if(flag == n)   start = curr;
            //当curr指针进入m-n的范围内，反转子链表
            if(flag > m && flag <= n){
                //用于保存前一个结点的指针
                ListNode tempNext = curr.next;
                curr.next = pre;
                pre = curr;
                curr = tempNext;
            }else{
                pre = pre.next;
                curr = curr.next;
            }
            flag ++;
        }
        //连接子链表和原链表的首尾两部分
        if(m != 1){
            left.next = start;
            end.next = right;
            return head;
        }else{
            end.next = right;
            return start;
        }
    }
}
