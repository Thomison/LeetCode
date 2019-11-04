//dfs
class Solution {
    int ans;
    
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        
        ans = Integer.MAX_VALUE;
        dfs(root, 0); 
        return ans;
    }
    
    void dfs(TreeNode root, int tmp) {
        if(root == null) {
            return;
        }else {
            if(root.left==null && root.right==null) {   //叶子结点更新ans
                ans = Math.min(tmp+1, ans);     //tmp代表的是边数，点数=边数+1
            }else {
                dfs(root.left, tmp+1);
                dfs(root.right, tmp+1);
            }
        }
    }
}
//bfs
class Solution {
    int ans;
    Queue<TreeNode> queue;
    
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        
        queue = new LinkedList<>();
        bfs(root);
        return ans;
    }
    
    void bfs(TreeNode root) {
        queue.offer(root);
        int cnt = 0;
        
        here:
        while(!queue.isEmpty()) {
            int size = queue.size();
            cnt++;
            
            while(size != 0) {
                TreeNode node = queue.poll();
                if(node.left==null && node.right==null) {   //叶子结点
                    break here;
                }
                if(node.left != null) {
                    queue.offer(node.left);
                }
                if(node.right != null) {
                    queue.offer(node.right);
                }
                size--;
            }
        }
        
        ans = cnt;
    }
}
