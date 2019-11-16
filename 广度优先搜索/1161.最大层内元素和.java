//图的广度优先搜索，对遍历的每一层保存两个中间值(层内元素和以及对应层数)
 //找到层内元素和最大的层数
class Solution {
    int ans;    //表示层内元素和最大的层号
    int sum;    //表示最大和
    Queue<TreeNode> queue;

    public int maxLevelSum(TreeNode root) {
        int depth = 0;
        ans = 0;
        sum = Integer.MIN_VALUE;
        queue = new ArrayDeque<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            int tmp = 0;
            depth++;
            while(size != 0) {
                TreeNode node = queue.poll();
                tmp += node.val;
                if(node.left != null)   queue.add(node.left);
                if(node.right != null)  queue.add(node.right);
                size--;
            }
            if(tmp > sum) {
                sum = tmp;
                ans = depth;
            }
        }
        return ans;
    }
}
