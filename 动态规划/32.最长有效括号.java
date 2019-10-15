/*
方法一：堆栈

利用堆栈可以回溯的性质，进行括号匹配，将有效括号匹配弹出，栈中剩余的就是无效括号。
需要注意的是堆栈中存储的是元组（括号字符，在字符串中的下标），用来统计无效括号字符的间隔，从而计算出最长有效括号字符子串。
*/

class Solution {
    //建立元祖类：用来绑定括号字符和在字符串中的下标
    private class Pair {
        public char c;
        public int index;
        public Pair(char c, int index) {this.c = c; this.index = index;}
    }
    public int longestValidParentheses(String s) {
        //初始化堆栈
        Stack<Pair> helper = new Stack<>();
        helper.push(new Pair('n', -1));
        int ans = 0;
        //匹配有效括号，弹出
        for(int i=0; i<s.length(); i++) {
            Pair p = new Pair(s.charAt(i), i);
            if(s.charAt(i) == '(')   helper.push(p);
            else {
                if(helper.peek().c == '(') helper.pop();
                else helper.push(p);
            }
        }
        //通过比较无效括号的下标间隔来得到最长有效括号长度
        int tmp1 = s.length();
        while(!helper.empty()) {
            int tmp2 = helper.pop().index;
            ans = Math.max(tmp1-tmp2-1, ans);
            tmp1 = tmp2;
        }
        return ans;
    }
}

/*
方法二：动态规划

建立长度为s.length()的一维dp数组，dp[i]表示以i-1为下标结尾的字符子串的有效长度。若i-1处为(，显然无效，dp[i] = 0；若i-1处为)，表示有可能有效，则需要根据状态转移规律进行计算。

- 模式为......() ，表示()为一个有效子串，加上前缀的dp长度即可。
  - dp[i] = dp[i-2] + 2
- 模式为......))，需要进一步追溯到和当前)进行匹配的(，即...((...))
  - 需要判断下标i-dp[i-1]-1 处字符是否为(，能够进行匹配则：
    - dp[i] = dp[i-1] + dp[i-dp[i-1]-2] + 2
    
*/

class Solution {
    public int longestValidParentheses(String s) {
        int ans = 0;
        int[] dp = new int[s.length()+1];   //表示当前结尾的有效子串长度，若无效，则默认0
        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i) == '(')  continue;
            else {
                if(i > 0 && s.charAt(i-1) == '(') {     //.....()模式
                    dp[i+1] = dp[i-1] + 2;
                }else if(i > 0 && s.charAt(i-1) == ')') {   //.....))模式
                    int s_index = i-dp[i]-1;   //与前一个(匹配的(的前一个字符    如...((...))
                    int dp_index = s_index-1+1;     //当前部分有效括号子串的前缀     如(....)((....))
                    if(s_index >= 0 && s.charAt(s_index) == '(')
                        dp[i+1] = dp[i] + dp[dp_index] +2;
                }
            }
            ans = Math.max(ans, dp[i+1]);
        }
        return ans;
    }
}
