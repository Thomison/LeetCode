/*
方法一：暴力匹配，O(n^3)

两层循环用来截取n^2个子字符串，
最内层循环用于判别子字符串是否为回文串，
是则根据情况更新结果，
优化后需要判别n/2次。

*/


class Solution {
    public String longestPalindrome(String s) {
        String ans = "";
        for(int i=0; i<s.length(); i++) {
            for(int j=i; j<s.length(); j++) {
                int subStrLength = j+1-i;
                boolean isPalindrome = true;
                for(int k=0; k<subStrLength/2; k++) {
                    if(s.charAt(i+k) != s.charAt(i+subStrLength-1-k)) {
                        isPalindrome = false; break;
                    }
                }
                if(isPalindrome && subStrLength > ans.length())
                    ans = s.substring(i, j+1);
            }
        }
        return ans;
    }
}

/*
方法二：动态规划，O(n^2)

建立一个二维数组dp，
dp[i][j]用来保存从下表i开始到下标j结束的子字符串是否为回文串，
是则为1，否则为0。
这样做的目的使得判断回文串的时间复杂度从O(n)降到O(1)，典型的空间换时间。

状态转移方程：

- 当i == j 或 i + 1 == j 时，即子字符串长度为1或者2时，不需要子问题的状态
- 此外，dp[i][j] 由 s.charAt(i) == s.charAt(j)?和dp[i+1][j-1] == 1 ? 共同决定。

需要注意的是：由于状态转移需要的固定方向，子字符串的扩张方向也有要求，列保持不变，行依次向列靠近。
*/

class Solution {
    public String longestPalindrome(String s) {
        int[][] dp = new int[s.length()][s.length()];
        String ans = "";
        for(int i=0; i<dp.length; i++) {    //初始化dp二维数组
            for(int j=0; j<dp[i].length; j++) {
                if(i == j)  dp[i][j] = 1;
                else dp[i][j] = -1;
            }
        }
        for(int j=0; j<s.length(); j++) {   //指针i和j用于遍历字符串，注意状态如何扩张
            for(int i=0; i<=j; i++) {  
                
                if(i == j || i + 1 == j) {    //字符串长度为1或者2, 不需要子问题的状态
                    if(s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = 1; 
                        ans = (j-i+1 > ans.length()) ? s.substring(i, j+1) : ans;
                    }
                    continue;
                }
                if(s.charAt(i) == s.charAt(j) && dp[i+1][j-1] == 1) {   //字符串长度大于2时，需要子问题的状态
                    dp[i][j] = 1;       //是回文串，更新状态
                    ans = (j-i+1 > ans.length()) ? s.substring(i, j+1) : ans; //更新目标字符串
                }else dp[i][j] = 0;       //非回文串，更新状态
            }
        }
        return ans;
    }
}
