import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree {
    private int size;   //结点个数
    private TreeNode root;  //二叉树的根结点

    //内部类结点
    private class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;

        private TreeNode() {}
        private TreeNode(int val) {
            this.val = val;
        }
    }
    
    //构造一个空的二叉搜索树
    public BinarySearchTree() {
        size = 0;
        root = null;
    }
    
    //序列构造二叉搜索树
    public BinarySearchTree(int[] nums) {
        size = 0;
        root = null;
        for(int i=0; i<nums.length; i++) {
            insert(nums[i]);
        }
    }
    
    //计算二叉树的高度
    public int getTreeHeight() {
        return getHeight(root);
    }
    private int getHeight(TreeNode node) {
        if(node == null) return 0;

        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }
    
    //插入
    public boolean insert(int num) {
        int before = size;
        root = insert(root, num);
        int after = size;

        if(before == after) return false;
        else return true;
    }
    private TreeNode insert(TreeNode node, int num) {
        if(node == null) {
            size++;
            return new TreeNode(num);
        }

        if(num < node.val)
            node.left = insert(node.left, num);
        if(num > node.val)
            node.right = insert(node.right, num);
        return node;
    }
    
    //返回中序遍历的数组
    public int[] toArray() {
        List<Integer> tmp = toList();
        int[] ans = new int[tmp.size()];
        for(int i=0; i<ans.length; i++) {
            ans[i] = tmp.get(i);
        }
        return ans;
    }
    //返回中序遍历的列表
    public List<Integer> toList() {
        List<Integer> ans = new ArrayList<>();
        helper(ans, root);
        return ans;
    }
    private void helper(List<Integer> tmp, TreeNode node) {
        if(node == null) return;

        helper(tmp, node.left);
        tmp.add(node.val);
        helper(tmp, node.right);
    }
    
    //打印 (前、中、后)
    public void preOrder() {
        printPreOrder(root);
    }
    private void printPreOrder(TreeNode node) {
        if(node == null) return;

        System.out.print(node.val+" ");
        printPreOrder(node.left);
        printPreOrder(node.right);
    }
    
    public void inOrder() {
        printInOrder(root);
    }
    private void printInOrder(TreeNode node) {
        if(node == null) return;

        printInOrder(node.left);
        System.out.print(node.val+" ");
        printInOrder(node.right);
    }
    
    public void postOrder() {
        printPostOrder(root);
    }
    private void printPostOrder(TreeNode node) {
        if(node == null) return;

        printPostOrder(node.left);
        printPostOrder(node.right);
        System.out.print(node.val+" ");
    }
    
    //删除
    public boolean delete(int key) {
        int before = size;
        deleteNode(root, key);
        int after = size;

        if(before == after) return false;
        else return true;
    }
    private TreeNode deleteNode(TreeNode root, int key) {
        TreeNode parent = null;
        TreeNode curr = root;

        while(curr != null && key != curr.val) {
            parent = curr;
            if(key < curr.val) {
                curr = curr.left;
            }else {
                curr = curr.right;
            }
        }

        if(curr == null) {  //被删结点不存在
            return root;
        }

        size--;

        if(curr.left == null || curr.right == null) {   //删除对象为curr

            if(curr.left == null && curr.right == null) {
                if(parent == null) return null;

                if(curr == parent.left) parent.left = null;
                else if(curr == parent.right) parent.right = null;
            }
            else if(curr.left == null) {
                if(parent == null) return curr.right;

                if(curr == parent.left) parent.left = curr.right;
                else if(curr == parent.right) parent.right = curr.right;
            }
            else if(curr.right == null){
                if(parent == null) return curr.left;

                if(curr == parent.left) parent.left = curr.left;
                else if(curr == parent.right) parent.right = curr.left;
            }

        }
        else { //删除对象为curr的后一个元素
            TreeNode rMinP = curr;
            TreeNode rMin = curr.right;
            while(rMin.left != null) {
                rMinP = rMin;
                rMin = rMin.left;
            }
            curr.val = rMin.val;	//替换
            //删除
            if(rMinP == curr) {
                if(rMin.right == null) {
                    rMinP.right = null;
                }else {
                    rMinP.right = rMin.right;
                }
            }else {
                if(rMin.right == null) {
                    rMinP.left = null;
                }else {
                    rMinP.left = rMin.right;
                }
            }
        }

        return root;
    }
    
    //搜索
    public boolean search(int key) {
        if(searchBST(root, key) != null)
            return true;
        else
            return false;
    }
    private TreeNode searchBST(TreeNode root, int val) {
        TreeNode curr = root;

        while(curr != null) {
            if(val < curr.val) {
                curr = curr.left;
            }else if(val > curr.val) {
                curr = curr.right;
            }else {
                break;
            }
        }

        return curr;
    }
}
