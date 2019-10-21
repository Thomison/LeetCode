/*
本来观察到链表的顺序刚好是二叉树的前序遍历顺序，想先通过dfs将结点值加入到列表中，再将列表还原成单指针域的树。
但是题目要求是在原地址上进行修改，作罢。

假设现在根节点1的左子树和右子树都已经被转化为单链表形式，我们要做的就是将左子树插入到根节点和右子树之间，
插入完成后，注意消除根结点的指针域。递归完成上述过程即可。

通过递归来模拟深度优先搜索（前序遍历）。
*/

class Solution {
    public void flatten(TreeNode root) {
        dfs(root);
    }
    //返回处理好的树的根节点
    public TreeNode dfs(TreeNode node) {
        if(node==null) return null;
        //dfs左右子树:使得左右子树符合要求(单链表的形式)
        TreeNode left=dfs(node.left), right=dfs(node.right);
        if(left==null) 
            return node;
        else {
            TreeNode tmp=left;
            while(tmp.right!=null) {
                tmp=tmp.right;
            }
            node.right=left;
            tmp.right=right;
            node.left=null;		//注意：插入操作完成后，根节点的左孩子需要置空
            return node;
        }
    }
}
