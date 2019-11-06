//迭代
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root == null)    return true;
        
        //bfs
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            List<TreeNode> tmpNodes = new ArrayList<>();
            int size = queue.size();
            //将此层节点出队，加入下一层的节点
            while(size != 0) {
                TreeNode node = queue.poll();
                
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
                
                tmpNodes.add(node.left);
                tmpNodes.add(node.right);
                
                size--;
            }
            //检查下一层是否对称
            for(int i=0; i<tmpNodes.size()/2; i++) {
                TreeNode left = tmpNodes.get(i);
                TreeNode right = tmpNodes.get(tmpNodes.size()-1-i);
                if(left!=null && right!=null) {
                    if(left.val != right.val)   
                        return false;
                }else if(left==null && right==null) 
                    continue;
                else 
                    return false;
            }
        }
        
        return true;
    }
}
