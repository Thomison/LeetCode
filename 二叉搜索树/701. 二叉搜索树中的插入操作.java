//非递归
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode pre = null;
        TreeNode curr = root;
        TreeNode goal = new TreeNode(val);
        
        while(curr != null && val != curr.val) {
            pre = curr;	//更新父结点
            if(val < curr.val) {
                curr = curr.left;
            }else {
                curr = curr.right;
            }
        }
        
        if(curr != null) return root; //不用插入的情况
        
        if(pre == null) {
            root = goal;
        } else if(val < pre.val) {
            pre.left = goal;
        } else {
            pre.right = goal;
        }
        
        return root;
    }
}
