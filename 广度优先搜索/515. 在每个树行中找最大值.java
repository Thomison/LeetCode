class Solution {
    List<Integer> ans;
    Queue<TreeNode> queue;

    public List<Integer> largestValues(TreeNode root) {
        ans = new ArrayList<>();
        queue = new ArrayDeque<>();
        if(root == null)    return ans;

        bfs(root);
        return ans;
    }
    void bfs(TreeNode root) {
        queue.offer(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            int max_row = Integer.MIN_VALUE;

            while(size != 0) {
                TreeNode node = queue.poll();
                max_row = Math.max(max_row, node.val);
                size--;
                
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
            }

            ans.add(max_row);
        }
    }
}
