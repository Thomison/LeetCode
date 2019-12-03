/*************************************************************************
    > File Name: 1171_从链表中删去总和值为零的连续节点.java
    > Author: 唐以恒
    > Mail: 1021953309tyh@gmail.com 
    > Created Time: 2019年12月03日 星期二 18时57分48秒
 ************************************************************************/
/*
 * Given the head of a linked list, we repeatedly **delete consecutive sequences of nodes that sum to 0** until there are no such sequences.
 */
//7ms
class Solution {
    public ListNode removeZeroSumSublists(ListNode head) {
        int preListLength=getListLength(head);
        while(true){
            head=solve(head);
            int newListLength=getListLength(head);
            //当删除前后链表长度保持一致时，说明链表不存在和为０的连续子链表
            if(preListLength==newListLength) break; 
            else preListLength=newListLength;
        }
        return head;
    }
    /*
    返回链表的长度
    */
    int getListLength(ListNode head) {
        if(head==null) return 0;
        int ret=0;
        ListNode tmp=head;
        while(tmp!=null) {
            ret++;
            tmp=tmp.next;
        }
        return ret;
    }
    /*
    暴力删除，尝试删除从每个结点开始的子链表(和为０时)
    */
    ListNode solve(ListNode head) {
        ListNode dummy=new ListNode(-1);    //哑结点：指向链表头部
        dummy.next=head;
        ListNode pre=dummy, curr=head;
        while(curr!=null) {
            ListNode tmp=curr;
            int sum=0;
            boolean isDeleted=false;    //是否执行了删除此结点作为头结点的子链表
            while(tmp!=null) {
                sum+=tmp.val;
                if(sum==0) {
                    pre.next=tmp.next;
                    isDeleted=true;
                    break;
                }else {
                    tmp=tmp.next;
                }
            }
            if(isDeleted) {
                curr=tmp.next;
            }else {
                pre=curr;
                curr=curr.next;
            }
        }
        return dummy.next;
    }
}
//2ms
class Solution {
    boolean hasDeleted=false;
    public ListNode removeZeroSumSublists(ListNode head) {
        while(!hasDeleted) {
            head=solve(head);
        }
        return head;
    }
    /*
    尝试删除从每个结点开始的和为０子链表
    */
    ListNode solve(ListNode head) {
        hasDeleted=false;
        ListNode dummy=new ListNode(-1);
        dummy.next=head;
        ListNode pre=dummy, curr=head;
        while(curr!=null) {
            ListNode tmp=curr;
            int sum=0;
            boolean isDeleted=false;
            while(tmp!=null) {
                sum+=tmp.val;
                if(sum==0) {
                    pre.next=tmp.next;
                    isDeleted=true;
                    hasDeleted=true;
                    break;
                }else {
                    tmp=tmp.next;
                }
            }
            if(isDeleted) {
                curr=tmp.next;
            }else {
                pre=curr;
                curr=curr.next;
            }
        }
        return dummy.next;
    }
}
