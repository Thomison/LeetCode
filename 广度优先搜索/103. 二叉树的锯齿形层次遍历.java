/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    List<List<Integer>> ans = new ArrayList<>();
    
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if(root == null)    return ans;
        bfs(root);
        return ans;
    }
    //广度优先搜索实现层次遍历
    void bfs(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> tmp = new LinkedList<>();
            while(size != 0) {          
                TreeNode node = queue.poll();
                if(ans.size() % 2 == 0) {
                    tmp.addLast(node.val);      //正序
                }else {
                    tmp.addFirst(node.val);     //逆序
                }
                if(node.left != null)   queue.add(node.left);
                if(node.right != null)   queue.add(node.right);
                size--;
            }
            ans.add(tmp);
        }
    }
}
