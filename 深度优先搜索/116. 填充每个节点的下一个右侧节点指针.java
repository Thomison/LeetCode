class Solution {
    public Node connect(Node root) {
        if(root==null)  return root;
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            Node pre = null;
            while(size != 0) {
                Node node = queue.poll();
                size--;
                if(size == 0) node.next = null;   //每一层的最后一个结点单独处理
                if(pre != null) pre.next = node;
                pre = node;     //更新前继结点
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
            }
        }
        return root;
    }
}



class Solution {
    public Node connect(Node root) {
        if(root==null)  return null;
        connect(root.left, root.right);
        return root;
    }
    void connect(Node r1, Node r2) {
        if(r1==null || r2==null)    return;
        r1.next = r2;
      
        connect(r1.left, r1.right);
        connect(r1.right, r2.left);
        connect(r2.left, r2.right);
    }
}
