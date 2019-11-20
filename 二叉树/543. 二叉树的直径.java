class Solution {
    int ans=0;
    public int diameterOfBinaryTree(TreeNode root) {
        getHeight(root);
        return ans;
    }
    int getHeight(TreeNode root) {
        if(root==null)  return 0;
        int left_height=getHeight(root.left);
        int right_height=getHeight(root.right);
        ans=Math.max(ans, left_height+right_height);
        return 1 + Math.max(left_height, right_height);
    }
}
