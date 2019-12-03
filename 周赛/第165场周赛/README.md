![](https://tyh-blog-image.oss-cn-beijing.aliyuncs.com/pixiv%E4%BD%9C%E5%93%81/D4kIoiMUcAAmdpw.jpeg)

# 第165场周赛

## [1.找出井字棋的获胜者](https://leetcode-cn.com/problems/find-winner-on-a-tic-tac-toe-game/)

![](https://tyh-blog-image.oss-cn-beijing.aliyuncs.com/LeetCode-%E5%9B%BE%E7%89%87/165%E5%9C%BA%E5%91%A8%E8%B5%9B/2019-12-02_13-04_1.png)

```
输入：moves = [[0,0],[2,0],[1,1],[2,1],[2,2]]
输出："A"
解释："A" 获胜，他总是先走。
"X  "    "X  "    "X  "    "X  "    "X  "
"   " -> "   " -> " X " -> " X " -> " X "
"   "    "O  "    "O  "    "OO "    "OOX"
```


- `1 <= moves.length <= 9`
- `moves[i].length == 2`
- `0 <= moves[i][j] <= 2`
- `moves` 里没有重复的元素。
- `moves` 遵循井字棋的规则。



看到这道题的时候想起上学期学Java基础时做的在线井字游戏，它没走一步也是有个判断当前是否产生赢家、是否平局、是否还能继续的判断机制。

而这个机制的实现就是这道题，因为$3 \times 3$ 一共就３＋３＋２＝８种情况判断是否产生赢家，简单的遍历就好。

将$moves$数组的每个元素填充到$flag$标志数组上，标志数组上只出现'0','O','X'这三种字符，'0'代表空。

暴力判断横、竖、纵共８种情况，看是否产生赢家，没有就查看$moves$的长度是否达到９，达到说明平局，没有达到说明还可以继续下棋。

```java
class Solution {
    public String tictactoe(int[][] moves) {
        char[][] flag=new char[3][3];   //默认为'0'
        for(int i=0; i<moves.length; i++) {
            if(i%2==0) flag[moves[i][0]][moves[i][1]]='X';//A
            else flag[moves[i][0]][moves[i][1]]='O'; //B
        }
        String winner="";
        for(int i=0; i<3; i++) {    //检查行
            if(flag[i][0]==flag[i][1] && flag[i][1]==flag[i][2]) {
                if(flag[i][0]=='X') winner="A";
                else if(flag[i][0]=='O') winner="B";
            }
        }
        for(int j=0; j<3; j++) {    //检查列
            if(flag[0][j]==flag[1][j] && flag[1][j]==flag[2][j]) {
                if(flag[0][j]=='X') winner="A";
                else if(flag[0][j]=='O') winner="B";
            }
        }
        if(flag[0][0]==flag[1][1] && flag[1][1]==flag[2][2]) { //检查对角线
            if(flag[1][1]=='X') winner="A";
            else if(flag[1][1]=='O') winner="B";
        }
        if(flag[2][0]==flag[1][1] && flag[1][1]==flag[0][2]) { 
            if(flag[1][1]=='X') winner="A";
            else if(flag[1][1]=='O') winner="B";
        }
        if(moves.length==9 && winner.equals("")) return "Draw";
        else if(moves.length<9 && winner.equals("")) return "Pending";
        else return winner;   
    }
}
```

## [2.不浪费原料的汉堡制作方案](https://leetcode-cn.com/problems/number-of-burgers-with-no-waste-of-ingredients/)

![](https://tyh-blog-image.oss-cn-beijing.aliyuncs.com/LeetCode-%E5%9B%BE%E7%89%87/165%E5%9C%BA%E5%91%A8%E8%B5%9B/2019-12-02_13-04.png)

- `0 <= tomatoSlices <= 10^7`
- `0 <= cheeseSlices <= 10^7`

简单的解决一元二次方程组的数学问题。(鸡兔同笼问题)

要么无解，要么只有一组解。

方程组如下：

$$
\left\lbrace 
\begin{aligned}
    4x+2y=tomatoSlices \\
    x+y=cheeseSlices
\end{aligned}
\right.
$$

$x=\frac{tomato}{2}-cheese$

检查$x,y$是否为非负数且整数.

```java
class Solution {
    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        List<Integer> ret=new ArrayList<>();
        if(tomatoSlices%2==1) return ret;   //tomatoSlices must be even
        
        int total_jumbo=0;
        int total_small=0;
        //only one answer
        //4x+2y=tomato -> 2x+y=tomato/2
        //x+y=cheese
        total_jumbo=(tomatoSlices/2)-cheeseSlices;
        total_small=cheeseSlices-total_jumbo;
        if(total_jumbo<0 || total_small<0) return ret;
        
        ret.add(total_jumbo);
        ret.add(total_small);
        return ret;
    }
}
```

## [3.统计全为１的正方形子矩阵](https://leetcode-cn.com/problems/count-square-submatrices-with-all-ones/)

![](https://tyh-blog-image.oss-cn-beijing.aliyuncs.com/LeetCode-%E5%9B%BE%E7%89%87/165%E5%9C%BA%E5%91%A8%E8%B5%9B/2019-12-02_13-02.png)

```
输入：matrix =
[
  [0,1,1,1],
  [1,1,1,1],
  [0,1,1,1]
]
输出：15
解释： 
边长为 1 的正方形有 10 个。
边长为 2 的正方形有 4 个。
边长为 3 的正方形有 1 个。
正方形的总数 = 10 + 4 + 1 = 15.
```

- `1 <= arr.length <= 300`
- `1 <= arr[0].length <= 300`
- `0 <= arr[i][j] <= 1`

$dp[i][j]$用来表示以$(i,j)$作为正方形右下角的正方形个数，其最大边长即为正方形个数。

![](https://pic.leetcode-cn.com/120f5fc93d20c5bc5bea301622512fd2c482ccb1228da4afb0dcad4538f21281-image.png)

转移方程：$dp[i][j]=min(dp[i-1][j-1],dp[i][j-1],dp[i-1][j])$

当前最大边长正方形是由横、竖、纵三个方向的最小值来决定的，所以我们需要上述三个状态。

```java
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
```

## [4.分割回文串　III](https://leetcode-cn.com/problems/palindrome-partitioning-iii/submissions/)

![](https://tyh-blog-image.oss-cn-beijing.aliyuncs.com/LeetCode-%E5%9B%BE%E7%89%87/165%E5%9C%BA%E5%91%A8%E8%B5%9B/2019-12-02_13-00.png)


- `1 <= k <= s.length <= 100`
- `s` 中只含有小写英文字母。


先找到每个子字符串修改为回文串所需要修改的最小字符数。

当前字符串划分为j个回文字符串所修改的最小字符数：列举所有前缀＋后缀组合情况：前缀字符串划分为j-1个回文字符串所需要修改的最小字符数，后缀字符串划分为1个回文字符串所需要修改的最小字符数。从这些情况中选择最少的修改字符数。

$ dp1[i][j]$:表示字符串子串$S_{ij}$修改为回文串的最小修改次数。
$ dp2[i][j]$:表示字符串前缀$S_{0i}$划分为$j$个回文串的最小修改次数。

状态转移：

$$
dp1[i][j]=
\left\lbrace 
    \begin{aligned}
        dp1[i+1][j-1],s(i)=s(j)\\
        dp1[i+1][j-1]+1,s(i)!=s(j)
    \end{aligned}
\right.
$$

$$
dp2[i][j]=min(dp2[m][j-1]+dp1[m+1][i]),m\in[j-1-1,i)
$$

dp1目的是得到子串不分割时修正为回文的最小修改数。
dp2(pos, par)目的是列举所有划分情况中最优的情况，总是划分第j-1个


```java
//T O(n^3) S O(n^2) 
class Solution {
    public int palindromePartition(String s, int k) {
        int n=s.length();
        char[] chars=s.toCharArray();
        //dp1[i][j]:子字符串S_ij修改字符的最小个数
        int[][] dp1=new int[n][n]; 
        //dp2[i][j]:字符串前缀S_0i分割为j个回文字符修改字符的最小个数
        int[][] dp2=new int[n][k+1];  

        //构造dp1
        for(int l=1; l<=n; l++) {       //根据子串的长度进行迭代
            for(int i=0; i<n-l+1; i++) {    //左下标
                int j=i+l-1;    //右下标
                if(chars[i]==chars[j]) {    //长度为１或２时单独考虑
                    if(i==j || i+1==j) dp1[i][j]=0;
                    else dp1[i][j]=dp1[i+1][j-1];
                }else {
                    if(i+1==j) dp1[i][j]=1;
                    else dp1[i][j]=dp1[i+1][j-1]+1;
                }
            }
        }
        //构造dp2
        for(int i=0; i<n; i++) {    //根据前缀的下标进行迭代
            for(int j=1; j<=Math.min(i, k); j++) {
                if(j==1) {
                    dp2[i][1]=dp1[0][i];    //边界：划分为一个回文串的情况
                }else {
                    dp2[i][j]=Integer.MAX_VALUE;
                    for(int m=(j-1)-1; m<i; m++) {
                        // 0-m:j-1个回文, （m+1）-i:1个回文
                        dp2[i][j]=Math.min(dp2[i][j],
                                    dp2[m][j-1]+dp1[m+1][i]);
                    }
                }
                
            }
        }
        return dp2[n-1][k];
    }
}
```


