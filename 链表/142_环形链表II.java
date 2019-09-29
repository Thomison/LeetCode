//采用快慢指针，先让两个指针相遇，再重新创建两个同步指针：一个从起点A开始，另外一个从相遇点C开始，当这两个同步指针相遇时，恰好为入环结点B

/*
算法：

1，处理特殊情况，当链表为空或只有一个结点时，链表肯定不成环，更没有入环结点，直接返回null

2，创建两个快慢指针，遍历链表且步速差为两倍，用来完成第一次相遇（到达C点）

​ 1)若不成环，则返回null

​ 2)若成环，标记好第一次相遇的结点位置，进行下一步

3，再创建一个慢指针，指向链表头部。两个慢指针同时遍历，再一次相遇的结点即为返回值。
*/

public class Solution {
    public ListNode detectCycle(ListNode head) {
      	/*1，处理特殊情况，当链表为空或只有一个结点时，链表肯定不成环，更没有入环结点，直接返回null*/
        if(head == null || head.next == null){
            return null;
        }
      	/*2，创建两个快慢指针，遍历链表且步速差为两倍，用来完成第一次相遇（到达C点）*/
        ListNode slow = head, fast = head, slow2 = head;
        ListNode firstMeet = null, secondMeet = null; 
        do{
          	// 1)若不成环，则返回null
            if(fast.next == null || fast.next.next == null){
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }while(slow != fast);
      	// 2)若成环，标记好第一次相遇的结点位置，进行下一步
        firstMeet = slow;
        
      	/*3，再创建一个慢指针，指向链表头部。两个慢指针同时遍历，再一次相遇的结点即为返回值。*/
        while(slow != slow2){
            slow = slow.next;
            slow2 = slow2.next;
        }
        secondMeet = slow;
        
        return secondMeet;
    }
}
