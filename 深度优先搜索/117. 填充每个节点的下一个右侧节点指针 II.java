class Solution {
    public Node connect(Node root) {
        if(root==null)  return null;
        //填充两个子树根节点的next
        if(root.left!=null) {
            if(root.right!=null) {
                root.left.next=root.right;
            }else {
                root.left.next=nextNode(root.next); //从root.next获取后继结点
            }
        }
        if(root.right!=null) {
            root.right.next=nextNode(root.next);
        }
        //细节:先使得所有右子树连接完成,使得连接左子树时获得正确的后继续结点
        connect(root.right);
        connect(root.left);
        return root;
    }
    //得到后继续结点
    Node nextNode(Node node) {
        while(node!=null) {
            if(node.left!=null) return node.left;
            else if(node.right!=null) return node.right;
            node = node.next;
        }
        return null;
    }
}
