public class AVLTree {
    private TreeNode root;

    public AVLTree () {}
    public AVLTree (int[] nums) {
        for(int i=0; i<nums.length; i++)
            insert(nums[i]);
    }
    //搜索
    public boolean search(int key) {
        return search(root, key);
    }
    private boolean search(TreeNode root, int key) {
        if(root == null) return false;

        if(key < root.val) {
            return search(root.left, key);
        }else if(key > root.val) {
            return search(root.right, key);
        }else
            return true;
    }
    //插入
    public boolean insert(int key) {
        if(root == null) {
            root = new TreeNode(key);
            return true;
        }
        if(search(key)) return false;   //已存在

        root = insert(root, key);
        return true;
    }
    private TreeNode insert(TreeNode root, int val) {
        if(root == null) {
            return new TreeNode(val);
        }

        if(val < root.val) {
            root.left = insert(root.left, val);
        }else if(val > root.val) {
            root.right = insert(root.right, val);
        }
        return reBalance(root);
    }
    //删除
    public boolean delete(int key) {
        if(!search(key)) return false;
        root = delete(root, key);
        return true;
    }
    private TreeNode delete(TreeNode root, int key) {
        if(root == null)
            return null;
        
        if(key < root.val) {
            root.left = delete(root.left, key);

        }else if(key > root.val) {
            root.right = delete(root.right, key);
            
        }else {     //找到被删对象
            if(root.left == null && root.right == null) {
                root = null;
            }else if(root.left == null) {
                root = root.right;
            }else if(root.right == null) {
                root = root.left;
            }else {
                if(getBalanceFactor(root) > 0) {    //替换，删左边最大结点
                    TreeNode leftMax = getMax(root.left);
                    root.val = leftMax.val;
                    
                    root.left = delete(root.left, leftMax.val);
                }else {         //替换，删右边最小结点
                    TreeNode rightMin = getMin(root.right);
                    root.val = rightMin.val;
                    
                    root.right = delete(root.right, rightMin.val);
                }
            }
        }
        return reBalance(root);			//每次回溯时重平衡 
    }
    //得到当前二叉树的最小值
    public int getTreeMin() {
        return getMin(root).val;
    }
    private TreeNode getMin(TreeNode root) {
        TreeNode tmp = root;
        while(tmp.left != null)
            tmp = tmp.left;
        return tmp;
    }
    //得到当前二叉树的最大值
    public int getTreeMax() {
        return getMax(root).val;
    }
    private TreeNode getMax(TreeNode root) {
        TreeNode tmp = root;
        while(tmp.right != null) {
            tmp = tmp.right;
        }
        return tmp;
    }
    //计算二叉树的结点数目
    public int getAVLSize() {
        return getSize(root);
    }
    private int getSize(TreeNode root) {
        if(root == null) return 0;
        return 1 + getSize(root.left) + getSize(root.right);
    }
    //计算高度
    public int getAVLHeight() {return getHeight(root);}
    private int getHeight(TreeNode root) {
        if(root == null)
            return 0;
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }
    //得到该结点的平衡因子
    private int getBalanceFactor(TreeNode root) {
        if(root == null) return 0;
        return getHeight(root.left) - getHeight(root.right);
    }
    //右旋转
    private TreeNode rotate_right(TreeNode root) {
        TreeNode pivot = root.left;
        root.left = pivot.right;
        pivot.right = root;

        return pivot;
    }
    //左旋转
    private TreeNode rotate_left(TreeNode root) {
        TreeNode pivot = root.right;
        root.right = pivot.left;
        pivot.left = root;

        return pivot;
    }
    //LL不平衡 对root进行一次右旋转即可
    private TreeNode balanceLL(TreeNode root) {
        return rotate_right(root);
    }
    //LR不平衡 先对左孩子进行一次左旋转，再对root进行一次右旋转
    private TreeNode balanceLR(TreeNode root) {
        root.left = rotate_left(root.left);
        return rotate_right(root);
    }
    //RR不平衡 对root进行一次左旋转即可
    private TreeNode balanceRR(TreeNode root) {
        return rotate_left(root);
    }
    //RL不平衡 先对右孩子进行一次右旋转，再对root进行一次左旋转
    private TreeNode balanceRL(TreeNode root) {
        root.right = rotate_right(root.right);
        return rotate_left(root);
    }
    //重新平衡以root为根节点的二叉树
    private TreeNode reBalance(TreeNode root) {
        int bf = getBalanceFactor(root);
        if(bf > 1 && getBalanceFactor(root.left) > 0) {
            return balanceLL(root);
        }else if(bf > 1 && getBalanceFactor(root.left) <= 0) {
            return balanceLR(root);
        }else if(bf < -1 && getBalanceFactor(root.right) <= 0) {
            return balanceRR(root);
        }else if(bf < -1 && getBalanceFactor(root.right) > 0) {
            return balanceRL(root);
        }else {
            return root;
        }
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

    /*-------结点类------*/
    private class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;
        public TreeNode(int val) {
            this.val = val;
        }
    }
}

