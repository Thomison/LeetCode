// 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。

//递归
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode temp = head;
        int nodesNum = 0;
        //遍历子链表，确认剩余结点数是否小于k
        //递归的终止条件
        while(temp != null){
            nodesNum ++;
            temp = temp.next;
        }
        //保持子链表原有顺序返回
        if(nodesNum < k){
            return head;
        }
        //执行翻转子链表的操作
        int flag = 0;
        //需要三个指针pre、curr用来反转，next用来保留副本，方便遍历
        ListNode pre = new ListNode(0);
        ListNode curr = head;
        pre.next = curr;
        ListNode next = curr.next;
        
        while(flag < k){
            flag ++;
            //反转两个结点
            curr.next = pre;
            if(flag == k) break;
            //三个指针向后移动一位
            pre = curr;
            curr = next;
            next = next.next;
        }
        head.next = reverseKGroup(next, k);
        
        //返回反转好的子链表
        return curr;
    }
}



//优秀题解：
public ListNode reverseKGroup(ListNode head, int k) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;

    ListNode pre = dummy;
    ListNode end = dummy;

    while (end.next != null) {
        for (int i = 0; i < k && end != null; i++) end = end.next;
        if (end == null) break;
        ListNode start = pre.next;
        ListNode next = end.next;
        end.next = null;
        pre.next = reverse(start);
        start.next = next;
        pre = start;

        end = pre;
    }
    return dummy.next;
}

private ListNode reverse(ListNode head) {
    ListNode pre = null;
    ListNode curr = head;
    while (curr != null) {
        ListNode next = curr.next;
        curr.next = pre;
        pre = curr;
        curr = next;
    }
    return pre;
}
