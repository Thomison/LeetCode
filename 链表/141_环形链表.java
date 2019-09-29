/*
因为环形链表不全是O形成环，也有可能是局部成环，如果采用遍历，保留副本，对比观察结点是否出现过，很容易进入死循环。

​ 所以采用快慢指针的做法，如果链表成环，一直沿着链表遍历下去，两个指针一定会相遇。

​ 注意特殊情况，链表为空或者只有一个结点时，不成环，直接返回。
*/


/*
    1,处理特殊情况，链表为空或只有一个结点时，不成环，直接返回false
    2,创建快慢指针：慢指针单步移动一个结点，快指针单步移动两个结点。
    3,遍历链表，
        1）若快指针遍历到以下结点时，说明链表不成环
        		链表长度奇数倍, fast.next == null
        		链表长度偶数倍, fast.next.next == null
        2）若快指针、慢指针指向同一结点，说明链表成环
*/

public class Solution {
    public boolean hasCycle(ListNode head) {
        
        /*1,处理特殊情况，链表为空或只有一个结点时，不成环，直接返回false*/
        if(head == null)    return false;
        else if(head.next == null)  return false;
        
        /*2,创建快慢指针：慢指针单步移动一个结点，快指针单步移动两个结点。*/
        ListNode slow=head, fast=head;
        
        /*3,遍历链表*/
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
            //若快指针、慢指针指向同一结点，说明链表成环
            if(slow == fast){
                return true;
            }
        }
        //若快指针遍历到null，说明链表不成环
        return false;
    }
}
