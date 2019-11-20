class Solution {
    int ans=0;
    TreeNode root;
    
    public int sumOfLeftLeaves(TreeNode root) {
        if(root==null)  return ans;
        this.root=root;
        solve(null, root);
        return ans;
    }
    void solve(TreeNode parent, TreeNode curr) {
        if(curr.left==null && curr.right==null) {   //叶子结点
            if(curr==root)  return;  //根节点单独处理
            else if(curr==parent.left)   ans+=curr.val;
        }
        if(curr.left!=null) {
            solve(curr, curr.left);
        }
        if(curr.right!=null) {
            solve(curr, curr.right);
        }
    }
}
