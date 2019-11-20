public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder builder=new StringBuilder();
        builder.append("[");
        Queue<TreeNode> queue=new LinkedList<>();
        if(root==null) return "[]";
        queue.add(root);
        while(!queue.isEmpty()) {       
            TreeNode node=queue.poll();
            if(node!=null)  {
                builder.append(node.val+",");
                queue.add(node.left);
                queue.add(node.right);
            }else {
                if(queue.isEmpty()) break;
                builder.append("null,");
            }
        }
        builder.replace(builder.length()-1, builder.length(), "]");
        return builder.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.equals("[]")) return null;
        data=data.substring(1,data.length()-1);
        String[] nodes=data.split(",");
        Queue<TreeNode> queue=new LinkedList<>();
        TreeNode ret=new TreeNode(Integer.valueOf(nodes[0]));
        queue.add(ret);
        int curr=1;
        while(!queue.isEmpty()) {
            TreeNode node=queue.poll();
            if(node!=null) {
                if(curr<nodes.length && !nodes[curr].equals("null")) 
                    node.left=new TreeNode(Integer.valueOf(nodes[curr]));
                if(curr+1<nodes.length && !nodes[curr+1].equals("null"))
                    node.right=new TreeNode(Integer.valueOf(nodes[curr+1]));
                queue.add(node.left);
                queue.add(node.right);
                curr+=2;
            }
        }
        return ret;
    }
}
