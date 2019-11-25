/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

//17 ms 感觉还能优化 
class Solution {
    int ans;
    int sum;

    public int pathSum(TreeNode root, int sum) {
        this.ans=0;
        this.sum=sum;
        dfs(root);
        return ans;
    }
    void dfs(TreeNode root) {
        if(root==null)  return;
        dfs2(root, 0);
        dfs(root.left);
        dfs(root.right);
    }
    void dfs2(TreeNode root, int tmp) {
        if(root==null) return;
        tmp+=root.val;
        if(tmp==sum) ans++;
        dfs2(root.left, tmp);
        dfs2(root.right, tmp);
    }
}
