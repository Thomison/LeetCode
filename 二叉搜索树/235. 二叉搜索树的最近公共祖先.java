class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null)  return null;
        List<TreeNode> list_p = new ArrayList<>();
        find(root, p, list_p);
        List<TreeNode> list_q = new ArrayList<>();
        find(root, q, list_q);
        for(int i=list_p.size()-1; i>=0; i--) {		//找到两条路径的分叉结点，即为最近公共祖先
            if(list_q.contains(list_p.get(i))) {
                return list_p.get(i);
            }
        }
        return root;
    }
    //构建从根节点到目标结点的结点路径列表
    void find(TreeNode root, TreeNode target, List<TreeNode> list) {
        list.add(root);
        if(target == root) {
            return;
        }else if(target.val < root.val) {
            find(root.left, target, list);
        }else {
            find(root.right, target, list);
        }
    }
}
