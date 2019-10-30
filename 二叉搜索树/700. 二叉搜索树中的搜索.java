//递归
class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        if(root == null) return null;
        
        if(val < root.val) return searchBST(root.left, val);
        else if(val > root.val) return searchBST(root.right, val);
        else return root;
    }
}
//非递归
class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        TreeNode curr = root;
        
        while(curr != null) {
            if(val < curr.val) {
                curr = curr.left;
            }else if(val > curr.val) {
                curr = curr.right;
            }else {
                break;
            }
        }
        
        return curr;
    }
}
