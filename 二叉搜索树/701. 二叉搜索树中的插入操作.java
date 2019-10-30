//非递归
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode y = null;
        TreeNode x = root;
        TreeNode z = new TreeNode(val);
        
        while(x != null && val != x.val) {
            y = x;	//更新父结点
            if(val < x.val) {
                x = x.left;
            }else {
                x = x.right;
            }
        }
        
        if(x != null) return root; //不用插入的情况
        
        if(y == null) {
            root = z;
        } else if(val < y.val) {
            y.left = z;
        } else {
            y.right = z;
        }
        
        return root;
    }
}
