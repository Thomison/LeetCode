/*
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
*/

/*
[
[2],
[3,4],
[6,5,7],
[4,1,8,3]
]
从底部往上走，每一步都走最优解，最后只有一条路径能够走到顶点，这条路径就是最小和。

状态转移：上一行的每个元素dp[i][j]只有两中选择：从下一行能够达到的两个元素取最大值：Math.max(dp[i+1][j]，dp[i+1] [j+1] ) + triangle.get(i).get(j)

时间复杂度：O(n^2)

空间复杂度：O(n^2)
*/
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int row=triangle.size(); 
        if(row==0)  return 0;
        int col=triangle.get(row-1).size();
        if(col==0)  return 0;
        //initial
        int[][] dp=new int[row+1][col+1];
        //dp
        for(int i=row; i>=1; i--) {
            int curr_row=i-1;
            for(int j=1; j<=triangle.get(curr_row).size(); j++) {
                int curr_col=j-1;
                int curr_value=triangle.get(curr_row).get(curr_col);
                
                if(i==row)  dp[i][j]=curr_value;
                else dp[i][j]=Math.min(dp[i+1][j], dp[i+1][j+1])+curr_value;
            }
        }
        return dp[1][1];
    }
}

/*
改进：因为我们只需要上一层的dp，所以可以用线性空间来存储，逐层更新上一层的dp即可。

时间复杂度：O(n^2)

空间复杂度：O(n)
*/
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int row=triangle.size(); 
        if(row==0)  return 0;
        int col=triangle.get(row-1).size();
        if(col==0)  return 0;
        //initial
        int[] dp=new int[col+1];
        //dp
        for(int i=row; i>=1; i--) {
            int curr_row=i-1;
            for(int j=1; j<=triangle.get(curr_row).size(); j++) {
                int curr_col=j-1;
                int curr_value=triangle.get(curr_row).get(curr_col);
                
                if(i==row)  dp[j]=curr_value;
                else dp[j]=Math.min(dp[j], dp[j+1])+curr_value;
            }
        }
        return dp[1];
    }
}
