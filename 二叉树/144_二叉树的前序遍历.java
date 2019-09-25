//解法一：递归（栈帧）

class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> preOrderList = new ArrayList<>();
        helper(root);
        return preOrderList;
    }
    public void helper(TreeNode root) {
        if(root == null)  return;

        preOrderList.add(root.val);
        helper(root.left);
        helper(root.right);
    }
}

//解法二：迭代（手动创建栈，模拟递归）
/*
前序遍历的访问顺序是 【根 左 右】，所以我们需要从二叉树的左分支入手，依次push入栈，
入栈的同时访问了该结点，根节点向左更新，直到二叉树的最左边，栈中存放的结点方便了我们进行回溯父结点。
入栈的顺序就是访问的顺序，为了模拟递归，函数调用是入栈，函数执行是出栈，出栈return到上一个调用它的函数。
*/

/*
        1           如图，先访问二叉树的根节点1，同时结点1入栈，
       / \          再访问二叉树的左子树，左子树的跟结点2被访问，同时入栈，
      2   3         重复上述过程，直到到达二叉树的最左结点的left指针域，为null
     /\   /\        需要回溯到栈中第一个弹出的结点且那根节点具有右孩子，则更新root
    4  5 6  7       让root访问结点4或结点2或结点1....的右子树，依次进行，直到根结点为空且栈空
*/

class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> preOrderList = new ArrayList<>(); //存放访问顺序
        Stack<TreeNode> stack = new Stack<>();  //存放结点，用于回溯
        while(root != null || !stack.empty()) {     //迭代遍历二叉树
            while(root != null) {   //使root指向当前子二叉树的最左结点
                stack.push(root);
                preOrderList.add(root.val); //将当前子二叉树的根节点入栈，并访问
                root = root.left;
            }
            while(root == null && !stack.empty()) {
                root = stack.pop().right;   //自底向上找到栈中跟结点第一个非空右孩子
            }
        }
        return preOrderList;
    }
}
