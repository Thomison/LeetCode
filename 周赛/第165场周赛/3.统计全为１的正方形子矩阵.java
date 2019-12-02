/*************************************************************************
    > File Name: 3.统计全为１的正方形子矩阵.java
    > Author: 唐以恒
    > Mail: 1021953309tyh@gmail.com 
    > Created Time: 2019年12月02日 星期一 13时47分31秒
 ************************************************************************/
//T O(n^2) S O(n^2)
class Solution {
    //max: 300*300
    //min: 1*1
    
    public int countSquares(int[][] matrix) {
        int ans=0;
        int row=matrix.length, col=matrix[0].length;
        int[][] dp=new int[row][col];
        //dp[i][j]表示以(i,j)作为正方形右下角的正方形的个数
        for(int i=0; i<row; i++) {
            for(int j=0; j<col; j++) {
                if(i==0 || j==0) {
                    dp[i][j]=(matrix[i][j]==1)? 1:0;
                }else {
                    dp[i][j]=(matrix[i][j]==1)? 
                        Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]))+1 
                        : 0;
                }
                ans+=dp[i][j];
            }
        }
        return ans;
    }
}
