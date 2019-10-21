/*
在112. 路径总和的基础上要求我们找出路径和=sum的所有路径。搜索的本质还是深度优先搜索，递归实现。

但要注意的是我们每次递归传入的List<Integer> tmp是同一个对象，相当于我们每遍历到一个结点时就要tmp.add(node.val)，
当回溯return时说明这个结点不是我们要访问的值，需要移除tmp.remove(tmp.size()-1)。

还要注意的是tmp加入到结果ans的时候需要拷贝一份tmp的副本 ，不然的话ans中的tmp会随着后续的搜索而发生改变。
*/

class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> ans=new ArrayList<>();

        dfs(root, sum, new ArrayList<Integer>(), ans);

        return ans;
    }
    public void dfs(TreeNode node, int sum, List<Integer> tmp, List<List<Integer>> list) {
        if(node==null) return;

        tmp.add(node.val);

        if(node.val==sum && node.left==null && node.right==null) {  //当前结点满足为满足要求的叶子结点
            list.add(new ArrayList<>(tmp)); //注意这里要拷贝tmp的副本, 如果ans中存储的是tmp对象，会随着之后的dfs发生改变
        }else {     //否则搜索该结点的左右子树
            dfs(node.left, sum-node.val, tmp, list);
            dfs(node.right, sum-node.val, tmp, list);
        }

        tmp.remove(tmp.size()-1);	//移除访问路径的当前结点
    }
}
