/*
Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".

Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
             Note that you are allowed to reuse a dictionary word.

Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false
*/

/*
思路：

找到最优子结构，当前字符串能够被划分意味着往前追溯一个单词，这个单词之前的子字符串仍然能够被划分。O(n^2)的时间复杂度，因为每便利到字符串的一个前缀字符串时，要往前追溯：判断划分单词之后的前前缀能够被划分，否则继续追溯，若追溯到前缀为空时，表明当前的前缀字符串不能被划分。

状态：dp[i][0]表示当前字符结尾的前缀字符串是否能被拆分 ，用0或1来表示。

dp[i][1]表示离当前字符最近的一个可以划分的前缀字符串的末尾字符的下标。(若当前的前缀字符串可划分，则下标为它本身)

状态转移：

dp[i][0]=0，dp[i][1]=dp[i-1][1]当前的前缀字符串不可划分

dp[i][0]=1，dp[i][1]=i当前的前缀字符串可划分

时间复杂度：O(n^2)

空间复杂度：O(n)
*/

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        //initial
        int[][] dp=new int[s.length()+1][2];
        dp[0][0]=1; 
        dp[0][1]=-1;
        //dp
        for(int i=1; i<=s.length(); i++) {
            int s_i=i-1;
            String tmpWord="";
            for(int j=s_i; j>=0; j--) {
                tmpWord=s.charAt(j)+tmpWord;//向前追溯到的字符加到tmpWord头部
                if(wordDict.contains(tmpWord) && dp[j][0]==1) {//当前的前缀字符串可划分
                    dp[i][0]=1;
                    dp[i][1]=s_i;
                    break;
                }
            }
            if(dp[i][0]==0) {//当前的前缀字符串不可划分
            	dp[i][1]=dp[i-1][1];
            }
        }
        return dp[s.length()][0]==1;
    }
}

/*
关于字符串的编程题，大部分可以用动态规划的思路进行求解，且一般套路是从后往前对字符串进行推导，找到最优子结构，从而写出状态转移方程。
*/
