/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

/*
思路：
    1)先计算链表长度listLength
    2)先讨论链表长度不足以分割的情况(listLength < k)
    3)当链表长度足以分割后，
        需要计算分割后的子链表基础长度partLength = listLength/k
        以及含有额外结点的子链表个数k1 = listLength%k
    4)遍历原链表，对链表进行分隔，分隔规律如下
        先分隔k1个长度为(partLength+1)的子链表
        再分隔k2个长度为partLength的子链表
    5)分别将各个子链表的头结点填充到结点数组中去
*/

class Solution {
    public ListNode[] splitListToParts(ListNode root, int k) {
        int listLength = getListLength(root);
        ListNode[] parts = new ListNode[k];
        //链表长度不足以分割
        if(listLength < k) {
            for(int i = 0; i < k; i++) {
                if(root != null) {
                    ListNode next = root.next;
                    root.next = null;
                    parts[i] = root;
                    root = next;
                }else {
                    parts[i] = null;
                }
            }
            return parts;
        }
        //链表长度足以分割
        int partLength = listLength / k;    //k2部分子链表的长度
        int k1 = listLength % k, k2 = k - k1;   //k1、k2两个部分的子链表个数
        前k1个子链表
        for(int i = 0; i < k1; i++) {
            ListNode head = root;
            for(int j = 0; j < partLength; j ++) {
                root = root.next;
            }
            ListNode next = root.next;
            root.next = null;
            root = next;
            parts[i] = head;
        }
        //前k2个子链表
        for(int i = 0; i < k2; i++) {
            ListNode head = root;
            for(int j = 0; j < partLength-1; j ++) {
                root = root.next;
            }
            ListNode next = root.next;
            root.next = null;
            root = next;
            parts[i+k1] = head;
        }
        
        return parts;
    }
    public int getListLength(ListNode head) {
        ListNode temp = head;
        int result = 0;
        while(temp != null) {
            result ++;
            temp = temp.next;
        }
        return result;
    }
}



/*
改进：
    1)将链表不足以分割和链表足以分割两种情况合并起来，整体化思量
    2)将分割前k1部分子链表和分割前k2部分子链表合并起来，减少重复的代码量
*/

class Solution {
    public ListNode[] splitListToParts(ListNode root, int k) {
        ListNode[] parts = new ListNode[k];
        
        int listLength = getListLength(root);   //原链表的长度
        int partLength = listLength / k;    //k2部分子链表的长度
        int k1 = listLength % k, k2 = k - k1;   //k1、k2两个部分的子链表个数

        //将原链表分隔成k个部分，并且将各个链表的头结点填充到结点数组中
        for(int i = 0; i < k; i++) {
            parts[i] = root;
            //截断子链表
            int tempLength = i < k1 ? partLength + 1 : partLength;
            for(int j = 0; j < tempLength - 1; j++) {
                root = root.next;
            }
            if(root != null) {
                ListNode next = root.next;
                root.next = null;
                root = next;
            }
        }
        
        return parts;
    }
    public int getListLength(ListNode head) {
        ListNode temp = head;
        int result = 0;
        while(temp != null) {
            result ++;
            temp = temp.next;
        }
        return result;
    }
}
