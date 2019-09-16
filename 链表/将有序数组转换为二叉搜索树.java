**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
/*
    思路：高度平衡二叉搜索树实质上就是要求我们创建AVL树
        但是题目给定的是一个有序数组，而不是让我们无序将数字插入到AVL树中
        我们发现了一条规律就是：创建的二叉树的根节点必须是数组的中点
            若数组长度奇数，恰好是中点：若是偶数，则是两个中点第一个第二个都可以。
        按照此规律，二叉树中往下走每个“根节点”都是对应“子数组的中间元素”
        所以，我们可以递归的创建高度平衡二叉搜索树(区间分治)
*/
// 递归
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        //退出条件
        if(nums.length == 0)    return null;
        else if(nums.length == 1)   return new TreeNode(nums[0]);
        
        //找到数组的中点元素的下标
        int middle = nums.length / 2;
        
        //创建二叉树的根节点
        TreeNode root = new TreeNode(nums[middle]);
        
        //将之前创建好的左右子树接到根节点的左右结点
        root.left = sortedArrayToBST(Arrays.copyOfRange(nums, 0, middle));
        root.right = sortedArrayToBST(Arrays.copyOfRange(nums, middle+1, nums.length));
        
        //返回根节点
        return root;
    }
}

//改进：不需要额外创建新数组的空间
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        return handle(nums, 0, nums.length-1);
    }
    public TreeNode handle(int[] nums, int from, int to){
        if(from > to)  return null;
        //else if(from == to) return new TreeNode(nums[from]);
        
        //得到中间元素的下标
        int middle = from + (to - from) / 2;
        TreeNode root = new TreeNode(nums[middle]);
        
        root.left = handle(nums, from, middle-1);
        root.right = handle(nums, middle+1, to);
        
        return root;
    }
}
