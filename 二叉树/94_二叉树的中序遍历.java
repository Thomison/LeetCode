//解法一：递归（栈帧）

class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> inOrderList = new ArrayList<>();
        helper(root);
        return inOrderList;
    }
    public void helper(TreeNode root) {
        if(root == null)  return;

        helper(root.left);
        inOrderList.add(root.val);
        helper(root.right);
    }
}

解法二：迭代（手动创建栈，模拟递归）

/*
中序遍历的访问顺序是 【左 根 右】，所以我们需要从二叉树的左分支入手，依次push入栈，
根节点向左更新，直到二叉树的最左边，即到了开始访问的时机，栈中存放的结点方便了我们进行回溯父结点。
出栈的顺序就是访问的顺序，为了模拟递归，函数调用是入栈，函数执行是出栈，出栈return到上一个调用它的函数。
*/

/*
        1           如图，先从根节点沿着左分支走到最左边，结点4处，由于结点4没有左孩子
       / \          先访问结点4作为跟结点本身，结点4出栈，接着访问结点4的右孩子（作为整个子二叉树）
      2   3         (若右孩子为空则更新结点root为结点2，访问以结点2为根节点的子二叉树：依次访问其根结点和它的右孩子)
     /\   /\        每次循环弹出访问一个结点，更新结点root为结点8，再对以结点8为跟结点的子二叉树进行沿着左分支走到最左边
    4  5 6  7       结点8访问完之后，且无右孩子，说明当前子二叉树访问完成，则将root更新为结点2
     \              简单的，root=root.right就可以完成对root的更新，若结点4.right=null, 再下一次循环从栈顶弹出应该回溯到的结点
      8             若结点4.right存在，则直接更新就好，综上，一条语句可完成.依次进行，直到根节点为空，并且栈为空。
*/

class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> inOrderList = new ArrayList<>();  //存放访问顺序
        Stack<TreeNode> stack = new Stack<>();  //存放结点，用于回溯
        while(root != null || !stack.empty()) {       //迭代遍历二叉树
            while(root != null) {   //使root指向当前子二叉树的最左结点
                stack.push(root); 
                root = root.left;   
            }
            root = stack.pop();
            inOrderList.add(root.val);   //出栈当前子二叉树的根结点，并访问
            root = root.right;     //更新root结点：当前子二叉树的右孩子 or 树的父结点
        }
        return inOrderList;
    }  
}
