/*
思想是深度优先搜索，由递归实现
本质上访问二叉树结点的顺序是前序遍历，但我们可以选择什么样的情况下该结点被访问。

递归遍历二叉树的本质就是dfs的过程。

dfs可以总结成：一条路走到底，碰壁了就回头，回溯到可以走另外一条路的结点，继续走这另外一条路走到底....如此往复。
需要注意的是： 在图论的dfs需要标价已经走过的路，以防止走重复的路，陷入死循环。
但这里不用，因为这里我们采用的是递归实现dfs。入栈就是往前走，出栈就是往后回溯。
*/

class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        return dfs(root, 0, sum);
    }
    public boolean dfs(TreeNode node, int tmp, int sum) {
        if(node==null)  return false;
        if(tmp+node.val==sum && node.left==null && node.right==null) {
            return (tmp+node.val == sum);
        }else {
            return (
                dfs(node.left, tmp+node.val, sum) || dfs(node.right, tmp+node.val, sum));
        }
    }
}

//优化
class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root==null) return false;
        else if(sum-root.val==0 && root.left==null && root.right==null) return true;
        else return (hasPathSum(root.left, sum-root.val) || hasPathSum(root.right, sum-root.val));
    }
}
