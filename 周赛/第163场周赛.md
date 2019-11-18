![](https://tyh-blog-image.oss-cn-beijing.aliyuncs.com/pixiv%E4%BD%9C%E5%93%81/DS1GxpqVoAA7Syz.jpeg)

# LeetCode-第163场周赛

> 上午去做课程实验，所以ge了，晚上补做了下，发现自己真的好菜....

## 1.二维网格迁移

![](https://tyh-blog-image.oss-cn-beijing.aliyuncs.com/LeetCode-%E5%9B%BE%E7%89%87/2019-11-17_18-44_1.png)

一次移动为：将二维数组每个元素向后移动一位，用一个`pre`来保存从上一个位置传递到当前这个位置的数字。

```java
class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        
        int n = grid.length;
        int m = grid[0].length;
        List<List<Integer>> ans = new ArrayList<>();
        
        for(int i=0; i<k; i++) {
            int pre = grid[n-1][m-1];  //保存上一行末尾的值
            
            for(int r=0; r<n; r++) {
                
                for(int c=0; c<m; c++) {
                    int copy = grid[r][c];
                    if(c == 0) {
                        grid[r][c] = pre;
                    }else {
                        grid[r][c] = pre;
                    }
                    pre = copy;
                }
            }
        }
        
        for(int i=0; i<n; i++) {
            ans.add(new ArrayList<>());
            for(int j=0; j<m; j++) {
                ans.get(i).add(grid[i][j]);
            }
        }
        
        return ans;
    }
}
```

![](https://tyh-blog-image.oss-cn-beijing.aliyuncs.com/LeetCode-%E5%9B%BE%E7%89%87/2019-11-17_18-44.png)

---

## 2.在受污染的二叉树中查找元素

![](https://tyh-blog-image.oss-cn-beijing.aliyuncs.com/LeetCode-%E5%9B%BE%E7%89%87/2019-11-17_19-06_1.png)

因为对二叉树中值的修改是从上到下的，采用$dfs$前序遍历二叉树的方式，对值进行修改，注意需要保存形参需要保存父结点。

然后查找值采用$bfs$对每个结点的值与`target`进行比较

```java
class FindElements {
    private TreeNode root;

    public FindElements(TreeNode root) {
        this.root = recover(null, root);
    }
    private TreeNode recover(TreeNode parent, TreeNode root) {
        if(root == null) return null;
        if(parent == null) {
            root.val = 0;
        }else {     //前序遍历
            if(root == parent.left) root.val = parent.val * 2 + 1;
            else root.val = parent.val * 2 + 2;
        }
        root.left = recover(root, root.left);
        root.right = recover(root, root.right);
        return root;
    }
    
    public boolean find(int target) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size != 0) {
                TreeNode node = queue.poll();
                if(node.val == target) {
                    return true;
                }
                if(node.left!=null) queue.add(node.left);
                if(node.right!=null) queue.add(node.right);
                size--;
            }
        }
        return false;
    }
}
```

![](https://tyh-blog-image.oss-cn-beijing.aliyuncs.com/LeetCode-%E5%9B%BE%E7%89%87/2019-11-17_19-06.png)

在修复树的同时用一个`map`保存值是否存在二叉树中，后续查找花费`O(1)`的时间复杂度。

```java
class FindElements {
    private TreeNode root;
    private Map<Integer, Boolean> map;

    public FindElements(TreeNode root) {
        map = new HashMap<>();
        this.root = recover(null, root);
    }
    private TreeNode recover(TreeNode parent, TreeNode root) {
        if(root == null) return null;
        if(parent == null) {
            root.val = 0;
        }else {     //前序遍历
            if(root == parent.left) root.val = parent.val * 2 + 1;
            else root.val = parent.val * 2 + 2;
        }
        map.put(root.val, true);
        root.left = recover(root, root.left);
        root.right = recover(root, root.right);
        return root;
    }
    
    public boolean find(int target) {
        return map.getOrDefault(target, false);
    }
}
```

![](https://tyh-blog-image.oss-cn-beijing.aliyuncs.com/LeetCode-%E5%9B%BE%E7%89%87/2019-11-17_21-16.png)

---

## 3.可被三整除的最大和

![](https://tyh-blog-image.oss-cn-beijing.aliyuncs.com/LeetCode-%E5%9B%BE%E7%89%87/2019-11-17_19-13.png)

动态规划

$dp[i][j]$表示前缀数组$nums_{0i}除3的最大和。

每次到`nums[i]`的时候，考虑是否选择它，导致的最大和，落在余数为0....mod-1的几种情况上。

```java
class Solution {
    int mod = 3;        //除数为3
    
    public int maxSumDivThree(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][mod];   //表示当前前缀数组除mod余j的最大和
        
        for(int i=0; i<mod; i++) {    //初始化
            dp[0][i] = (nums[0] % mod == i) ? nums[0] : 0;
        }
        for(int i=1; i<n; i++) {
            
            for(int j=0; j<mod; j++) {
                int max = 0;
                for(int k=0; k<mod; k++) {
                    int tmp = dp[i-1][k] + nums[i];
                    if(tmp % mod == j && tmp > max) {
                        max = tmp;
                    }
                }
                if(max == 0) {
                    dp[i][j] = dp[i-1][j];
                }else {
                    dp[i][j] = Math.max(dp[i-1][j], max);
                }
            }
        }
        return dp[n-1][0];
    }
}
```

![](https://tyh-blog-image.oss-cn-beijing.aliyuncs.com/LeetCode-%E5%9B%BE%E7%89%87/2019-11-17_20-08.png)


附上大佬对$dp$数组降维的做法，每次更新$dp$数组时需要$dp\_tmp$保存结果值，避免混淆。

```java
class Solution {
    
    public int maxSumDivThree(int[] nums) {
        int[] dp = {0, Integer.MIN_VALUE, Integer.MIN_VALUE};
        for(int n : nums) {
            int[] dp_tmp = {0, 0, 0};
            for(int i=0; i<3; i++) {
                //更新当前前缀数组的最大和(除3余数0,1,2)
                dp_tmp[(i+n) % 3] = Math.max(dp[(i+n) % 3], dp[i]+n);
            }
            dp = dp_tmp;
        }
        return dp[0];
    }
}
```

![](https://tyh-blog-image.oss-cn-beijing.aliyuncs.com/LeetCode-%E5%9B%BE%E7%89%87/2019-11-17_20-21.png)

---

## 4.推箱子

![](https://tyh-blog-image.oss-cn-beijing.aliyuncs.com/LeetCode-%E5%9B%BE%E7%89%87/2019-11-17_20-30.png)

![](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/11/16/sample_1_1620.png)

---

## 总结

