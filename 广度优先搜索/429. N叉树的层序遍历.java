class Solution {
    List<List<Integer>> ans;
    Queue<Node> queue;          //队列

    public List<List<Integer>> levelOrder(Node root) {
        ans = new ArrayList<>();
        queue = new LinkedList<>();
        if(root == null)    return ans;

        bfs(root);
        return ans;
    }
    //广度优先搜索实现层次遍历
    void bfs(Node root) {
        queue.offer(root);
        while(!queue.isEmpty()) {       //循环体内表示遍历一行的结点
            int size = queue.size();
            List<Integer> tmp = new ArrayList<>(size);

            while(size != 0) {
                Node node = queue.poll();
                tmp.add(node.val);
                for(int i=0; i<node.children.size(); i++) {
                    queue.offer(node.children.get(i));
                }
                size--;
            }

            ans.add(tmp);
        }
    }
}
