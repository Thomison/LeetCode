![](https://tyh-blog-image.oss-cn-beijing.aliyuncs.com/pixiv%E4%BD%9C%E5%93%81/D3CkwIbU4AETE_6.jpeg)

# 第164场周赛

## 1.访问所有点的最小时间

![](https://tyh-blog-image.oss-cn-beijing.aliyuncs.com/LeetCode-%E5%9B%BE%E7%89%87/%E5%B1%8F%E5%B9%95%E5%BF%AB%E7%85%A7%202019-11-24%20%E4%B8%8B%E5%8D%881.44.34.png)

![](https://tyh-blog-image.oss-cn-beijing.aliyuncs.com/LeetCode-%E5%9B%BE%E7%89%87/1626_example_1.png)

按照顶点的顺序遍历顶点数组，每次统计两个连续顶点对之间的$dx,dy$，它们之间的最小移动步数为$max(dx, dy)$，累加求和即可。

```java
//1ms
class Solution {
    public int minTimeToVisitAllPoints(int[][] points) {
        int ans=0;
        for(int i=0; i<points.length-1; i++) {
            int dx=Math.abs(points[i+1][0]-points[i][0]);
            int dy=Math.abs(points[i+1][1]-points[i][1]);
            ans += Math.max(dx, dy);
        }
        return ans;
    }
}
```

## 2.统计参与通信的服务器

![](https://tyh-blog-image.oss-cn-beijing.aliyuncs.com/LeetCode-%E5%9B%BE%E7%89%87/%E5%B1%8F%E5%B9%95%E5%BF%AB%E7%85%A7%202019-11-24%20%E4%B8%8B%E5%8D%881.44.44.png)

![](https://tyh-blog-image.oss-cn-beijing.aliyuncs.com/LeetCode-%E5%9B%BE%E7%89%87/untitled-diagram-1-3.jpg)

对二维数组中每个值为1的结点进行访问，沿着这个顶点的四个方向(上、下、左、右)查看到边界的路径上是否存在值$1$的结点，只要这四条路径上存在一个，即可说明这个结点是可以通信的结点，$ans=ans+1$。

```java
//12ms
class Solution {
    int ans;
    int[][] grid;
    int row;
    int col;
    int[][] move={{-1,0},{1,0},{0,1},{0,-1}};
    public int countServers(int[][] grid) {
        this.ans=0;
        this.grid=grid;
        this.row=grid.length;
        this.col=grid[0].length;
        for(int i=0; i<row; i++) {
            for(int j=0; j<col; j++) {
                if(grid[i][j]==1) {
                    boolean flag=false;
                    for(int k=0; k<move.length; k++) {
                        int x=i+move[k][0];
                        int y=j+move[k][1];
                        while(x>=0 && x<row && y>=0 && y<col) {
                            if(grid[x][y]==1) {
                                flag=true;
                                break;
                            }
                            x+=move[k][0];
                            y+=move[k][1];
                        }
                    }
                    if(flag) ans++;
                }
            }
        }
        return ans;
    }
}
```

## 3.搜索推荐系统

![](https://tyh-blog-image.oss-cn-beijing.aliyuncs.com/LeetCode-%E5%9B%BE%E7%89%87/%E5%B1%8F%E5%B9%95%E5%BF%AB%E7%85%A7%202019-11-24%20%E4%B8%8B%E5%8D%881.45.10.png)

$Example$

```
Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
Output: [
["mobile","moneypot","monitor"],
["mobile","moneypot","monitor"],
["mouse","mousepad"],
["mouse","mousepad"],
["mouse","mousepad"]
]
Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"]
After typing m and mo all products match and we show user ["mobile","moneypot","monitor"]
After typing mou, mous and mouse the system suggests ["mouse","mousepad"]
```

构造一个最简单的推荐系统，就是系统会根据你每次所输入的前缀，在单词库里面进行查找拥有此前缀的根据字典序排序前三位的单词。按照题意，迭代你所输入的前缀字符串，用这前缀字符串在（字典序排好序的）字典列表里面进行模式匹配，匹配到的单词就加入新列表，每次迭代后用新列表更新字典，并且构造答案。

```java
//27ms
class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> ans=new ArrayList<>();
        List<String> products_list=Arrays.asList(products);
        Collections.sort(products_list);
        String tmp="";
        List<String> tmp_list=new ArrayList<>();
        for(int i=0; i<searchWord.length(); i++) {
            tmp+=searchWord.charAt(i);
            for(int j=0; j<products_list.size(); j++) {
                if(products_list.get(j).startsWith(tmp)) {
                    tmp_list.add(products_list.get(j));
                }
            }
            List<String> ans_tmp=new ArrayList<>(3);
            int cnt=0;
            while(cnt<3 && cnt<tmp_list.size()) {ans_tmp.add(tmp_list.get(cnt));cnt++;}
            ans.add(ans_tmp);
            
            products_list=tmp_list;
            tmp_list=new ArrayList<>();
        }
        return ans;
    }
}
```

## 4.停在原地的方案数

![](https://tyh-blog-image.oss-cn-beijing.aliyuncs.com/LeetCode-%E5%9B%BE%E7%89%87/%E5%B1%8F%E5%B9%95%E5%BF%AB%E7%85%A7%202019-11-24%20%E4%B8%8B%E5%8D%881.45.20.png)

$Example$

```
Input: steps = 3, arrLen = 2
Output: 4
Explanation: There are 4 differents ways to stay at index 0 after 3 steps.
Right, Left, Stay
Stay, Right, Left
Right, Stay, Left
Stay, Stay, Stay
```

典型的动态规划题目，采用$dp[i][j]$来存储步数为$i$，下标$j$时的方案数，然后每次迭代原问题由三种子问题的解组成：

$dp[i][j]=dp[i-1][j-1]+dp[i-1][j]+dp[i-1][j+1]$

注意两个端点需要特别考虑。

然后这样做出来发现时间和空间在个别案例会超。发现其实不用二维$dp$，一维$dp$就可以解决问题，所以采用降维，每次迭代需要一个临时$tmp$来存储上一个$dp$的值。

这样处理好空间不超了，但是时间还是会超。发现其实可以有些情况可以直接$break$的，比如当前步长数小于下标数的情况，肯定无法到达，所以方案数为0，就不用子问题的解来构造。

```java
//超出内存限制 => 降维
//289ms
class Solution {
    public int numWays(int steps, int arrLen) {
        int[] dp=new int[arrLen];
        int mod=(int)Math.pow(10, 9)+7;
        dp[0]=1;
        for(int i=1; i<=steps; i++) {
            int[] tmp=Arrays.copyOfRange(dp, 0, dp.length);
            for(int j=0; j<arrLen; j++) {
                dp[j] = tmp[j];
                if(j>i) break;  //最大步长都小于下标 肯定无法到达
                if(j==0) {
                    dp[j] += tmp[j+1];
                    if(i==steps) {dp[j]%=mod; break;}
                }else if(j==arrLen-1) {
                    dp[j] += tmp[j-1];
                }else {
                    dp[j] += tmp[j+1];
                    dp[j] %= mod;
                    dp[j] += tmp[j-1];
                }
                dp[j] %= mod;
            }
        }
        return dp[0];
    }
}
```

最后用时$289$ms，时间还是有点高，之后再考虑优化:)


## 总结

- 1.数组
- 2.数组
- 3.字符串
- 4.动态规划

这次题目挺简单，自己都能够独立做出来。耗时90min
