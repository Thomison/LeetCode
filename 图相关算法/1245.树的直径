//利用bfs两次找到最深结点，这两个结点之间的边长就是答案。
class Solution {
    List<List<Integer>> graph;
    int vertex_num;
    int ans;
    Queue<Integer> queue;
    int[][] edges;
    
    public int treeDiameter(int[][] edges) {
        this.edges = edges;
        
        init();
        
        //两次bfs分别找到直径的两个端点
        int start = bfs(0, new boolean[vertex_num]);
        int end = bfs(start, new boolean[vertex_num]);
        
        return ans;
    }
    //初始化
    void init() {
        ans = 0;
        vertex_num = edges.length + 1;
        graph = new ArrayList<>(vertex_num);
        queue = new LinkedList<>();
        for(int i=0; i<vertex_num; i++) {
            graph.add(new ArrayList<>());
        }
        for(int i=0; i<edges.length; i++) {
            int u = edges[i][0], v = edges[i][1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
    }
    //返回最深处的顶点
    int bfs(int s, boolean[] isVisited) {
        int last = s;       //记录最后一个搜索到的顶点
        int cnt = 0;     //记录最长路径上的顶点数目
        
        queue.offer(s);
        while(!queue.isEmpty()) {
            int size = queue.size();
            cnt++;     
            
            while(size != 0) {      //弹出一层的顶点
                int u = queue.poll();
                isVisited[u] = true;
                
                for(int i=0; i<graph.get(u).size(); i++) {
                    int v = graph.get(u).get(i);
                    if(!isVisited[v]) {
                        queue.offer(v);
                    }
                }
                last = u;
                size--;
            }
        }
        ans = Math.max(ans, cnt-1);     //更新最长路径数目 = 顶点数-1
        return last;
    } 
}



//一次dfs回溯时每到一个结点视为根节点，将它的最长的两个子路径之和，对答案进行更新
class Solution {
    private int ans = 0;
    private List<List<Integer>> list;
    private boolean[] isVisited;
    
    public int treeDiameter(int[][] edges) {
        int n = edges.length + 1;
        list = new ArrayList<>(n);
        isVisited = new boolean[n];
        
        for(int i=0; i<n; i++) 
            list.add(new ArrayList<>());
        for(int i=0; i<edges.length; i++) {
            int u=edges[i][0], v=edges[i][1];
            list.get(u).add(v);
            list.get(v).add(u);
        }
        
        dfs(0);
        return ans;
    }
    private int dfs(int u) {
        isVisited[u] = true;      //被访问
        int curr_ret = 0, add = 0;
        for(int v: list.get(u)) {
            if(!isVisited[v]) {
                int next_ret = dfs(v);  //下一个顶点返回的最大路径值
                
                if(next_ret > curr_ret) {
                    add = curr_ret;
                    curr_ret = next_ret;
                }else if(next_ret > add) {
                    add = next_ret;
                }
            }
        }
        ans = Math.max(ans, curr_ret + add);
        return curr_ret + 1;
    }
}
