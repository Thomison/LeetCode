/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/*
    思路1：
    先将链表反转，保留为一个新链表，再同时遍历两个链表，比较结点值是否相同。O(n),O(n)
    踩坑：效果不好，其实按照之前反转链表的做法，是在原来链表上进行修改
    改进：将反转链表的做法改成：创建一个空的新链表，遍历原链表，将结点依次接到新链表的表头
    总结：时间、空间复杂度太高，27%、47%
*/
class Solution {
    public static boolean isPalindrome(ListNode head) {
        //处理特殊情况
        if(head == null || head.next == null)   return true;
        //反转链表，保留为一个新链表
        ListNode reverse = reverseLinkList(head);
        //遍历原链表和反转后的链表，比较回文是否成立
        ListNode temp1 = head, temp2 = reverse;
        while(temp1 != null && temp2 != null){
            if(temp1.val != temp2.val)  return false;
            
            temp1 = temp1.next;
            temp2 = temp2.next;
        }
        return true;
    }
    //反转链表，不是修改原链表，而是返回创建的新链表
    public static ListNode reverseLinkList(ListNode head){
        ListNode temp_new = null, temp_old = head;
        while(temp_old != null) {
        	ListNode temp_new_next = new ListNode(temp_old.val);
        	temp_new_next.next = temp_new;
        	temp_new = temp_new_next;
        	temp_old = temp_old.next;
        }
        return temp_new;
    }
}

/*
    思路2：
    遍历链表，将链表中结点的值读取出来，保存到一个整型数组，然后从数组的头尾两边同时开始遍历，比较值是否相同
    总结：一共遍历了两趟链表，一趟得到表厂，一趟用于创建数组，此外还遍历了一趟数组，
        时间、空间复杂度：97%、50%
*/
class Solution {
    public static boolean isPalindrome(ListNode head) {
        ListNode temp = head;
        int length = 0;
        while(temp != null){
            length ++;
            temp = temp.next;
        }
        int[] list = new int[length];
        temp = head;
        int i = 0;
        while(temp != null){
            list[i] = temp.val;
            temp = temp.next;
            i++;
        }
        for(int j=0; j<length; j++){
            if(list[j] != list[length-1-j]) return false;
        }
        return true;
    }
}

/*   
    思路3：
    回文串实际上是以中点为轴对称的，所以只有比较对称的两半部分是否成轴对称
    等价于比较链表前一半和反转过的后一半是否相等  O(n),O(1)
    找到链表中点的方法有：
        1）先第一次遍历计算链表长度，然后第二次遍历到长度一半处，记录链表后一半的头结点，
        2）通过快慢指针一次遍历就可以找到链表的中点，记录链表后一半的头结点
    反转链表的方法有：
        1）通过头插法反转链表后一半
        2）用递归法反转链表后一半
    最后遍历两部分链表，比较遍历到的结点是否相同，验证轴对称
*/
class Solution {
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null)   return true;
        
        ListNode slow = head, fast = head, backHalf = null;
        // int slow_step = 0;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
            // slow_step ++;
        }
        if(fast.next == null){
            backHalf = slow;
        }
        else if(fast.next.next == null){
            backHalf = slow.next;
        }
        
        backHalf = reverse(backHalf);
        
        while(head != null && backHalf != null){
            if(head.val != backHalf.val){
                return false;
            }
            head = head.next;
            backHalf = backHalf.next;
        }
        
        return true;
    }
    //递归法实现反转链表，并返回新链表的头结点
    public ListNode reverse(ListNode head){
        //返回值：反转后链表的头结点
        if(head.next == null)    return head;
        
        ListNode newHead = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
