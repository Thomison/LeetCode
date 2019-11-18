class Solution {
    int ans = 0;
    public int sumNumbers(TreeNode root) {
        if(root==null)  return 0;
        dfs(root, 0);
        return ans;
    }
    void dfs(TreeNode root, int tmp) {
        if(root==null)  return;
        if(root.left==null && root.right==null) { //叶子结点
            ans += tmp*10 + root.val;
        }else {
            dfs(root.left, tmp*10 + root.val);
            dfs(root.right, tmp*10 + root.val);
        }
    }
}
