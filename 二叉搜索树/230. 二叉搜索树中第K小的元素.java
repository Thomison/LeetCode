class Solution {
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        inOrder(root, list);
        return list.get(k-1);
    }
    void inOrder(TreeNode root, List<Integer> list) {
        if(root==null)  return;
        inOrder(root.left, list);
        list.add(root.val);
        inOrder(root.right, list);
    }
}

class Solution {
    int ans, cnt;
    public int kthSmallest(TreeNode root, int k) {
        cnt=k;
        inOrder(root);
        return ans;
    }
    void inOrder(TreeNode root) {
        if(root==null || cnt==0)  return;    
        inOrder(root.left);
        cnt--;
        if(cnt==0)  ans=root.val; 
        inOrder(root.right);
    }
}
