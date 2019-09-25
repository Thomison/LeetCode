//解法一：递归（栈帧）

class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> postOrderList = new ArrayList<>();
        helper(root);
        return postOrderList;
    }
    public void helper(TreeNode root) {
        if(root == null)  return;

        helper(root.left);
        helper(root.right);
        postOrderList.add(root.val);
    }
}

//解法二：迭代（双栈：逆序（根右左）的访问顺序，算是投机做法，不算模拟递归）

/*
后序遍历的访问顺序是 【左 右 根】，我们发现它和前序遍历的访问顺序 【根 左 右】很相似，似乎刚好是呈逆序关系，
可以将之前写好的前序遍历迭代版稍微改动一下，调整为【根 右 左】的访问顺序，并用一个栈接受这样的访问顺序，最后再逆序输出即可。
*/

class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> postOrderList = new ArrayList<>();    //用于存放访问顺序
        Stack<Integer> temp = new Stack<>(); //用于存放访问顺序的逆序
        Stack<TreeNode> stack = new Stack<>();  //存放结点，用于回溯
        while(root != null || !stack.empty()) { //迭代访问二叉树
            while(root != null) {  //沿着二叉树的右分支，走到最右端
                stack.push(root);
                temp.push(root.val);
                root = root.right;
            }
            while(root == null && !stack.empty()) {  //自底向上找到栈中结点第一个非空左孩子
                root = stack.pop().left;
            }
        }
        while(!temp.empty()) {  //逆序（根、右、左）的访问顺序
            postOrderList.add(temp.pop());
        }
        return postOrderList;    
    }
}

/*解法三：迭代（手动创建单栈，模拟递归）

在做迭代前序遍历和中序遍历的时候，发现root经过的路径都是左根右，对于前序和中序来说，访问路径基本上跟经过路径是一致的。
但是在后序遍历中，我们先经过根节点，但是我们不会去访问它，而是会选择先访问它的右子节点。
所以在这种情况下，我们会将根节点留在栈中不弹出，等到需要访问它的时候再出。

那我们什么情况下才能访问根节点？是从右节点回溯到根节点，而不是从左节点回溯到根节点，
所以我们需要记录之前结点和当前节点的关系，来确定是否访问当前节点。
总结起来，我们什么时候才能访问节点。有如下两种情况：

  当前经过节点是叶子节点。
  当前经过节点的右子节点是上一次访问的节点。
  
若不满足上述情况，说明是从左孩子回溯到根节点，需要先访问根节点的右孩子，root = root.right*/

/*
        1           
       / \          
      2   3         
     /\   /\        
    4  5 6  7       

*/

class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> postOrderList = new ArrayList<>();    //用于存放访问顺序
        Stack<TreeNode> stack = new Stack<>();  //存放结点，用于回溯
        TreeNode pre = null;    //记录之前访问过的结点
        while(root != null || !stack.empty()) { //迭代访问二叉树
            while(root != null) {   //使root指向当前子二叉树的最左结点
                stack.push(root);
                root = root.left;
            }
            root = stack.peek();
            if(root.right == null || root.right == pre) {        //当前结点为叶子结点 或者 当前结点的右孩子是上个访问结点
                pre = root;    //更新上一次访问的结点
                postOrderList.add(stack.pop().val);     //出栈，表示访问了当前结点
                root = null;    //让root到下一次循环再更新，避免发生空栈错误
            }else {
                root = root.right;     //访问当前结点的右孩子
            }
        }
        return postOrderList;    
    }
}

