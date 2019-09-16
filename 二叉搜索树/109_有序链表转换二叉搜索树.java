/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
/*
    思路： 快慢指针找到链表中间结点，从结点处断开，将断开后的两段子链表交给递归下一级
          同理还是采用递归，深度优先搜索的形式创建二叉搜索树
          时间复杂度O(n*logn)  
        
*/

//递归，链表上找中点，不用修改（断开）链表
//改进版本
class Solution {
    public TreeNode sortedListToBST(ListNode head) {
    	if(head == null)	return null;
    	return BFS(head, null);
    }
    //递归法得到二叉搜索树的根节点（实质是深度优先搜索）
    public TreeNode BFS(ListNode head, ListNode tail) {
    	if(head == tail)	return null;
    	//快慢指针找到中间结点slow
    	ListNode slow = head, fast = head;
    	while(fast != tail && fast.next != tail) {
    		slow = slow.next;
    		fast = fast.next.next;
    	}
    	TreeNode root = new TreeNode(slow.val);
      //左右子树的根节点
    	root.left = BFS(head, slow);
    	root.right = BFS(slow.next, tail);
    	return root;
    }  
}

//原先版本：修改了链表（断开）
class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null)    return null;
        else if(head.next == null)  return new TreeNode(head.val);
        
        ListNode slow = head, fast = head;
        ListNode pre = new ListNode(-1);
        pre.next = slow;
        while(fast.next != null && fast.next.next != null){
            pre = pre.next;
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = null;
        
        TreeNode root = new TreeNode(slow.val);
        if(head == slow){
            root.left = sortedListToBST(null);
        }else{
            root.left = sortedListToBST(head);
        }
        root.right = sortedListToBST(slow.next);
        
        return root;
    }
}


//递归，链表转换为数组，在数组上找中点
//空间换时间 时间复杂度O(n)
class Solution {
    private List<Integer> values = new ArrayList<>();
    
    public TreeNode sortedListToBST(ListNode head) {
        //链表转数组
        while(head != null){
            values.add(head.val);
            head = head.next;
        }
        //在数组上进行递归
        return handle(values, 0, values.size()-1);
    }
    public TreeNode handle(List<Integer> list, int from, int to){
        if(from > to)  return null;
        else if(from == to) return new TreeNode(list.get(from));
        
        int middle = from + (to - from) / 2;
        TreeNode root = new TreeNode(list.get(middle));
        
        root.left = handle(list, from, middle-1);
        root.right = handle(list, middle+1, to);
        
        return root;
    }
}
