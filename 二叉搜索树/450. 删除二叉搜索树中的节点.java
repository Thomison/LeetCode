/*
分三种情况（被删结点）：
1.没有子结点
2.只有一个子结点
3.有两个子结点
*/
class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode parent = null;
        TreeNode curr = root;

        while(curr != null && key != curr.val) {  //搜索到被删结点curr
            parent = curr;
            if(key < curr.val) {
                curr = curr.left;
            }else {
                curr = curr.right;
            }
        }

        if(curr == null) {  //不存在被删结点
            return root;
        }

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
            }else if(curr.right == null){
                if(parent == null) return curr.left;

                if(curr == parent.left) parent.left = curr.left;
                else if(curr == parent.right) parent.right = curr.left;
            }

        }
        else { //删除对象为curr的后一个元素 被删结点curr的值被替换
            TreeNode rMinP = curr;
            TreeNode rMin = curr.right;
            while(rMin.left != null) {
                rMinP = rMin;
                rMin = rMin.left;
            }
            curr.val = rMin.val;
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
                    rMinP.left = rMin.right;    //删除
                }
            }

        }

        return root;
    }
}
