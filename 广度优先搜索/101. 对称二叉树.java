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


class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root == null)    return true;

        Queue<TreeNode> q1 = new LinkedList<>();
        Queue<TreeNode> q2 = new LinkedList<>();    
        q1.add(root);
        q2.add(root);

        while(!q1.isEmpty() && !q2.isEmpty()) {
            TreeNode node1 = q1.poll();
            TreeNode node2 = q2.poll();
            if(node1==null && node2==null)  continue;
            if(node1==null || node2==null)  return false;
            if(node1.val != node2.val)  return false;

            q1.add(node1.left);
            q1.add(node1.right);
            q2.add(node2.right);
            q2.add(node2.left);
        }

        return true;
    }
}


class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root == null)    return true;
        return solve(root.left, root.right);
    }
    boolean solve(TreeNode root1, TreeNode root2) {
        if(root1==null && root2==null)  return true;
        if(root1==null || root2==null)  return false;
        return (root1.val==root2.val) && 
            (solve(root1.left, root2.right)) && (solve(root1.right, root2.left));
    }
}

