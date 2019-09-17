/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
/*
思路1：创建哈希表存储链表a的所有结点，然后遍历链表b，判断遍历到的当前结点是否包含在哈希表中，
     若遍历到第一个包含在哈希表中的结点，就返回该结点，退出程序
     若遍历到链表b末尾null时，则说明链表a、b没有相交的地方
*/

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null)
            return null;
        Set<ListNode> visited = new HashSet<>();
        //visit linked list A, add node into hashSet.
        while(headA != null){
            visited.add(headA);
            headA = headA.next;
        }
        //visit linked list B, fand the first node contains in hashSet
        while(headB != null){
            if(visited.contains(headB))
                return headB;
            headB = headB.next;
        }
        return null;
    }
}
//总结：时间复杂度：O（m+n）m为链表a长度，n为链表b长度
//      空间复杂度：O（m）


/*
思路2：暴力法
     在遍历链表a的循环体内部遍历链表b（用两个指针）
     当两个指针指向同一个结点时，则是相交的结点
*/

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null)
            return null;
        ListNode tempB = null;
        while(headA != null){
            tempB = headB;
            while(tempB != null){
                if(headA == tempB)
                    return tempB;
                tempB = tempB.next;
            }
            headA = headA.next;
        }
        return null;
    }
}
//总结：时间复杂度：O（m*n）
//      空间复杂度：O（1）
//时间复杂度太高了，不可取


/*
思路3：第一轮同时遍历两个链表，（用两个指针）且开始计数a,b
     更短的链表先便利到链表尾部，这个时候暂停遍历短链表
     继续遍历长链表，直到遍历到长链表的尾结点
     这个时候比较两个指针，若相等则说明两个链表在中间某处相交了
                         若不相等则说明链表不相交，直接返回null
     |a-b|即为两个链表的长度差，
     第二轮同时遍历，让长链表先出发|a-b|个单位，
     此时再让短链表出发，比较遍历到的两个结点
     若相等，说明链表在此处相交则返回该结点
*/

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //handle special status
        if(headA == null || headB == null)
            return null;
        
        //first traverse two linked list, to find the length of them
        ListNode tempA = headA, tempB = headB;
        int a = 1, b = 1;
        while(true){
            if(tempA.next != null){
                tempA = tempA.next;
                a ++;
            }
            if(tempB.next != null){
                tempB = tempB.next;
                b ++;
            }
            if(tempA.next == null && tempB.next == null)
                break;
        }
        if(tempA != tempB) 
            return null;
        
        //second traverse two linked list, to find the intersection of them
        tempA = headA;
        tempB = headB;
        if(a > b){
            for(int i=a-b; i>0; i--)
                tempA = tempA.next;
        }else if(a < b){
            for(int i=b-a; i>0; i--)
                tempB = tempB.next;
        }
        while(true){
            if(tempA == tempB)
                return tempA;
            tempA = tempA.next;
            tempB = tempB.next;
        }
    }
}
//总结：时间复杂度为O(2*max(m,n)-k)  k为链表相交结点到末尾的长度
//     空间复杂度为O(1)   创建了两个指针用来遍历链表a、b，以及创建了三个int整型，分别是链表a、b长度、二者长度差
