class Solution {
    String[][] res;
    
    public List<List<String>> printTree(TreeNode root) {
        if(root==null)  return new ArrayList<>();
        
        int height = getTreeHeight(root);   //计算二维数组规模
        int width = (int)Math.pow(2, height) - 1;
        res = new String[height][width];
        
        fill(root, 0, 0, width-1);  //填充二叉树结点
        
        List<List<String>> ans = new ArrayList<>();     //构造返回列表
        for(int i=0; i<height; i++) {
            ans.add(new ArrayList<String>());
            for(int j=0; j<width; j++) {
                if(res[i][j] != null) ans.get(i).add(res[i][j]);
                else ans.get(i).add("");
            }
        }
        return ans;
    }
    int getTreeHeight(TreeNode root) {	//计算二叉树的高度
        if(root==null)  return 0;
        else return 1 + Math.max(getTreeHeight(root.left), getTreeHeight(root.right));
    }
    void fill(TreeNode root, int depth, int left, int right) {	//填充二叉树结点值到二维数组的指定位置上
        if(root==null)  return;
        else {
            int mid = left + (right-left)/2;
            res[depth][mid] = String.valueOf(root.val);
            fill(root.left, depth+1, left, mid-1);
            fill(root.right, depth+1, mid+1, right);
        }
    }
}
