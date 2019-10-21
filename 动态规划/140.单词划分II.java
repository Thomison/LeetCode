/*
Input:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
Output:
[
  "cats and dog",
  "cat sand dog"
]

Input:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
Output:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]
Explanation: Note that you are allowed to reuse a dictionary word.

Input:
s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]
Output:
[]

核心：动态规划+深度优先搜索

思路：

139. 单词拆分是只用返回字符串是否能够被划分，而这道题是在139. 单词拆分基础上需要回溯访问之前的划分，组合出所有能够划分字符串的情况，并返回组合情况的个数。

设计当前的前缀字符串对应的状态二元组：分别表示可划分单词的首尾下标

如果当前的前缀字符串能够被划分，则dp.get(i).size()>0

如果当前的前缀字符串能够被划分，则dp.get(i).size()=0

dp.get(i).size()表示当前的前缀字符串结尾处可以被几种单词划分，并且消除了这几种单词之后，其前前缀字符串仍然能够被划分。

和139.单词划分的状态转移不同，之前只需要找到最近的一个可以划分的单词即可，这里我们需要找到当前字符串可以划分的所有情况。

s = "catsanddog"
wordDict = ["cats", "cat", "and", "sand", "dog"];
/*     {
0:      {[0, 0]},
c 1:	{},
a 2:	{},
t 3:    {[0, 2]},
s 4:    {[0, 3]},
a 5:	{},
n 6:	{},
d 7:    {[4, 6][3, 6]},
d 8:	{},
o 9:	{},
g 10:   {[7, 9]}     
                      }								 }
*/
[
  "cats and dog",
  "cat sand dog"
]

通过动态规划得到的状态，我们从字符串的最后一个字符进行深度优先搜索，回溯得到所有可以划分单词组合情况。
*/



class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<List<int[]>> dp=getDP(s, wordDict);
        List<String> ans=new ArrayList<>();
        int n=s.length();
        if(dp.get(n).size()==0)    return ans;//表示字符串不能被划分

        dfs(n, dp, s, "", ans);	

        return ans;
    }
    public void dfs(int row, List<List<int[]>> dp, String s, String tmp, List<String> ans) {
        if(row==0) {
            ans.add(tmp.substring(0, tmp.length()-1)); return;
        }
        for(int i=0; i<dp.get(row).size(); i++) {
            dfs(dp.get(row).get(i)[0],	//下一行的行号 
                dp, s, 		//传递给下一层的str
                s.substring(dp.get(row).get(i)[0], dp.get(row).get(i)[1]+1)+" "+tmp, 
                ans);
        }
    }
    public List<List<int[]>> getDP(String s, List<String> wordDict) {
        //initial
        List<List<int[]>> dp=new ArrayList<>();
        List<int[]> init=new ArrayList<>(1);
        int[] init_tmp={-1,-1};
        init.add(init_tmp);
        dp.add(init);
        //dp
        for(int i=1;i<=s.length(); i++) {
            List<int[]> list_tmp=new ArrayList<>();
            String s_tmp="";
            for(int j=i-1; j>=0; j--) {
                s_tmp=s.charAt(j)+s_tmp;
                if(wordDict.contains(s_tmp) && dp.get(j).size()!=0) {
                    int[] tmp=new int[2];
                    tmp[0]=j; tmp[1]=i-1; 
                    list_tmp.add(tmp);
                }
            }
            dp.add(list_tmp);
        }
        return dp;
    }
}

