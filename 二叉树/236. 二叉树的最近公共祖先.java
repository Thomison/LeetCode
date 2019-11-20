class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null)  return null;

        List<TreeNode> list_p = new ArrayList<>();
        dfs(root, p, new ArrayList<>(), list_p);
        List<TreeNode> list_q = new ArrayList<>();
        dfs(root, q, new ArrayList<>(), list_q);

        for(int i=list_p.size()-1; i>=0; i--) {
            if(list_q.contains(list_p.get(i)))  return list_p.get(i);
        }
        return root;
    }
    void dfs(TreeNode root, TreeNode target, List<TreeNode> list, List<TreeNode> list_target) {
        if(root==null) return;
        list.add(root);
        if(target == root) {
            list_target.addAll(list);		//注意不能new一个列表，会被自动销毁
        }
        dfs(root.left, target, list, list_target);
        dfs(root.right, target, list, list_target);
        list.remove(list.size()-1);
    }
}
