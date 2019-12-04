//3ms
class Solution {
    TreeNode dummy = new TreeNode(-1);  //哑结点
    public TreeNode increasingBST(TreeNode root) {
        solve(root);
        return dummy.right;
    }
    //中序遍历bst
    void solve(TreeNode root) {
        if(root == null)    return;

        solve(root.left);
        //将结点接在新bst的最后一个结点右孩子处
        TreeNode tmp = dummy;
        while(tmp.right != null) {
            tmp = tmp.right;
        }
        tmp.right = new TreeNode(root.val); 
        solve(root.right);
    }
}
