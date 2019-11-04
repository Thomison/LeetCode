class Solution {
    int ans;
    Node root;
    Queue<Node> queue;
        
    public int maxDepth(Node root) {
        if(root == null) return 0;
        ans = 0;
        this.root = root;    
        queue = new LinkedList<>();
        
        bfs();
        
        return ans;
    }
    
    void bfs() {
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
