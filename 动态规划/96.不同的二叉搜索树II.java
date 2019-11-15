//递归
public List<TreeNode> generateTrees(int n) {
    if(n == 0)
        return new LinkedList<TreeNode>();
    return generateTrees(1,n);
}
public List<TreeNode> generateTrees(int start,int end) {
    List<TreeNode> res = new LinkedList<TreeNode>();
    if(start > end){
        res.add(null);
        return res;
    }
    for(int i = start;i <= end;i++){
        List<TreeNode> subLeftTree = generateTrees(start,i-1);
        List<TreeNode> subRightTree = generateTrees(i+1,end);
        for(TreeNode left : subLeftTree){
            for(TreeNode right : subRightTree){
                TreeNode node = new TreeNode(i);
                node.left = left;
                node.right = right;
                res.add(node);
            }
        }                        
    }
    return res;
}
//动态规划
class Solution {
    private List<List<List<TreeNode>>> dp;
    
    public List<TreeNode> generateTrees(int n) {
        if(n == 0)  return new ArrayList<>();
        //初始化
        dp = new ArrayList<>(n+1);
        for(int i=0; i<=n; i++) {
            dp.add(new ArrayList<>(n+1));
            for(int j=0; j<=n; j++) {
                dp.get(i).add(new ArrayList<>());
            }
        }
        for(int i=1; i<=n; i++) {
            for(int j=i; j<=n; j++) {
                if(i == j) {
                    dp.get(i).get(j).add(new TreeNode(i));
                }
            }
        }
        //构造最优解
        for(int s=2; s<=n; s++) {   //长度
            for(int i=1; i<=n-s+1; i++) {   //左断点
                int j = i+s-1;    //右端点

                for(int k=i; k<=j; k++) {
                    if(k==i) {
                        for(int r=0; r<dp.get(k+1).get(j).size(); r++) {
                            TreeNode root = new TreeNode(k);
                            root.left = null;
                            root.right = dp.get(k+1).get(j).get(r);
                            dp.get(i).get(j).add(root);
                        }
                    }else if(k==j) {
                        for(int l=0; l<dp.get(i).get(k-1).size(); l++) {
                            TreeNode root = new TreeNode(k);
                            root.left = dp.get(i).get(k-1).get(l);
                            root.right = null;
                            dp.get(i).get(j).add(root);
                        }
                    }else {
                        for(int l=0; l<dp.get(i).get(k-1).size(); l++) {
                            for(int r=0; r<dp.get(k+1).get(j).size(); r++) {
                                TreeNode root = new TreeNode(k);
                                root.left = dp.get(i).get(k-1).get(l);
                                root.right = dp.get(k+1).get(j).get(r);
                                dp.get(i).get(j).add(root);
                            }
                        }
                    }                 
                }
            }
        }
        return dp.get(1).get(n);
    }
}
//改进
