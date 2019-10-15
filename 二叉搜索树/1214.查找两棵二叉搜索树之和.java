/*

如果给出的不是两个bst，而是两个有序数组就好了。
那么我们直接中序遍历两个bst，将遍历到的结点的数字加入到列表中
最后再对这两个有序列表进行操作：O（n^2）的时间复杂度判等。
组合两个数字对可能得情况，判断其和是否等于target

*/

class Solution {
    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();
    
    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        helper(root1, list1);
        helper(root2, list2);
        for(int i=0; i<list1.size(); i++) {
            for(int j=0; j<list2.size(); j++) {
                if(list1.get(i) + list2.get(j) == target) return true;
            }
        }
        return false;
    }
    //中序输出
    public void helper(TreeNode root, List<Integer> list) {
        if(root == null) return;
        
        helper(root.left, list);
        list.add(root.val);
        helper(root.right, list);
    }
}
