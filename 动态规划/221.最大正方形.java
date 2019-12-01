/*************************************************************************
    > File Name: 221.最大正方形.java
    > Author: 唐以恒
    > Mail: 1021953309tyh@gmail.com 
    > Created Time: 2019年12月02日 星期一 00时18分09秒
 ************************************************************************/
class Solution {
    public int maximalSquare(char[][] matrix) {
        int row=matrix.length;
        if(row==0) return 0;
        int col=matrix[0].length();
        if(col==0) return 0;
        
        int ans=0;
        int[][] dp=new int[row][col];
        for(int i=0; i<row; i++) {
            for(int j=0; j<col; j++) {
                if(i==0 || j==0) {
                    dp[i][j]=(matrix[i][j]=='1')? 1:0;
                }else {
                    dp[i][j]=(matrix[i][j]=='1')? 
                        Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]))+1 
                        :
                        0;
                }
                ans=Math.max(dp[i][j]*dp[i][j], ans);
            }
        }
        
        return ans;
    }
}
