// bfs
class Solution {
    int ans;
    Queue<Node> queue;
        
    public int maxDepth(Node root) {
        if(root == null) return 0;
        ans = 0;   
        queue = new LinkedList<>();
        
        bfs(root);
        
        return ans;
    }
    
    void bfs(Node root) {
        queue.offer(root);
        int cnt = 0;        //记录层数
        
        while(!queue.isEmpty()) {
            int size = queue.size();
            cnt++;
            
            while(size != 0) {
                Node root = queue.poll();
                
                for(int i=0; i<root.children.size(); i++) {
                    queue.offer(root.children.get(i));  //由于树不能回溯， 所以不需要判断是否被访问过
                }
                size--;
            }
        }
        
        ans = cnt;
    }
}


//dfs
class Solution {
    int ans;
        
    public int maxDepth(Node root) {
        if(root == null) return 0;
        ans = 0;    
        
        dfs(root, 0);
        
        return ans;
    }

    void dfs(Node root, int tmp) {
        if(root == null) {
            return;
        }else {
            if(root.children.size() == 0) { //叶子
                ans = Math.max(ans, tmp+1);
            }else {
                for(int i=0; i<root.children.size(); i++) {
                    dfs(root.children.get(i), tmp+1);
                }
            }
        }
    }
}
