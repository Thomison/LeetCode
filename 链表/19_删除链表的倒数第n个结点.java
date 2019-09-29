//给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。

//两次遍历
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode curr = head;
        ListNode pre = new ListNode(0);
        pre.next = curr;
        //指定删除第几个节点
        int length = getListLength(head);
        int key = length - n + 1;
        int flag = 0;
        
        if(length == n)return head.next;
        
        while(curr != null){
            flag ++;
            if(key == flag){
                pre.next = curr.next;
                break;
            }
            curr = curr.next;
            pre = pre.next;
        }
        return head;
    }
    //计算链表长度
    public int getListLength(ListNode head){
        ListNode curr = head;
        int length = 0;
        while(curr != null){
            curr = curr.next;
            length ++;
        }
        return length;
    }
}


//一次遍历
public ListNode removeNthFromEnd(ListNode head, int n){
  //生成哑结点，连接到链表头部之前。
  ListNode dummy = new ListNode(0);
  dummy.next = head;
  //生成两个指针
  ListNode first = dummy;
  ListNode second = dummy;
  //先移动first，使得两个指针间隔n+1个结点
  for(int i=0; i <= n+1; i++){
    first = first.next;
  }
  //同时移动两个指针,直到first走到链表末尾，且为null
  while(first != null){
    first = first.next;
    second = second.next;
  }
  //执行删除操作
  second.next = second.next.next;
  //为了处理特殊情况(单节点或删除头结点)
  return dummy.next;
}
