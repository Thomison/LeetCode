
![](https://tyh-blog-image.oss-cn-beijing.aliyuncs.com/pixiv%E4%BD%9C%E5%93%81/58351853_p0.jpg)

# LeetCode-第13场双周赛

> 这周难度相比前几周好像有所增加，看排行榜前几名花了四十多分钟完成。

## 1.加密数字

![](https://tyh-blog-image.oss-cn-beijing.aliyuncs.com/LeetCode-%E5%9B%BE%E7%89%87/%E5%B1%8F%E5%B9%95%E5%BF%AB%E7%85%A7%202019-11-16%20%E4%B8%8B%E5%8D%8811.48.56.png)

完全二叉树
下标对应数字，从叶子结点回溯到根节点，根据奇偶性判断是左孩子还是右孩子。

```java
class Solution {
    public String encode(int num) {
        String ans = "";
        while(num != 0) {
            if(num % 2 == 0) {  //偶数
                ans = "1" + ans;
                num = (num-2) / 2;
            }else {     //奇数
                ans = "0" + ans;
                num = (num-1) / 2;
            }
        }
        return ans;
    }
}
```

大佬的做法：

```java
//返回num+1的对应二进制且去掉首位
return BigInteger.valueOf(num + 1).toString(2).substring(1)
```

---

## 2.最小公共区域

![](https://tyh-blog-image.oss-cn-beijing.aliyuncs.com/LeetCode-%E5%9B%BE%E7%89%87/%E5%B1%8F%E5%B9%95%E5%BF%AB%E7%85%A7%202019-11-16%20%E4%B8%8B%E5%8D%8811.49.04.png)

树
求两个结点的最近公共祖先。
通过哈希表存储当前字符串-父亲字符串，来访问父亲结点。键值对是唯一的。
分别存储两个结点到根节点的结点数组(路径)，再遍历得到两个路径的相交点，即为最小公共区域。

```java
class Solution {
    public String findSmallestRegion(List<List<String>> regions, String region1, String region2) {
        String ans = "";
        Map<String, String> child_parent = new HashMap<>();
        
        for(int i=0; i<regions.size(); i++) {
            String parent = regions.get(i).get(0);
            if( !child_parent.containsKey(parent) ) {
                child_parent.put(parent, null);
            }
            for(int j=1; j<regions.get(i).size(); j++) {
                child_parent.put(regions.get(i).get(j), parent);
            }
        }
        
        List<String> list1 = new ArrayList<>();
        while(region1 != null) {
            list1.add(region1);
            region1 = child_parent.get(region1);    //to parent
        }
        List<String> list2 = new ArrayList<>();
        while(region2 != null) {
            list2.add(region2);
            region2 = child_parent.get(region2);    //to parent
        }
        for(int i=0; i<list1.size(); i++) {
            if(list2.contains(list1.get(i))) {
                ans = list1.get(i);
                break;
            }
        }
        
        return ans;
    }
}
```

---

## 3.近义词句子

![](https://tyh-blog-image.oss-cn-beijing.aliyuncs.com/LeetCode-%E5%9B%BE%E7%89%87/%E5%B1%8F%E5%B9%95%E5%BF%AB%E7%85%A7%202019-11-16%20%E4%B8%8B%E5%8D%8811.49.13.png)

$dfs$和$set$

先将能够合并的近义词对进行合并，得到近义词集合。
再对句子根据空格分割后的数组进行$dfs$，找到所有句子方案。

```java
class Solution {
    List<List<String>> synonyms;
    Set<String> set;       //存储近义词字典的集合
    String text;
    String[] words;         //以空格划分句子，得到字符串数组
    Map<String, Set<String>> map;       //近义词映射近义词集合
    List<String> ans;

    public List<String> generateSentences(List<List<String>> synonyms, String text) {
        initial(synonyms, text);    //初始化set和map

        makeSynonyms();     //生成map

        dfs(0, words.length-1, ""); //对words进行dfs

        Collections.sort(ans);  //字典序排序

        return ans;
    }
    //初始化
    void initial(List<List<String>> synonyms, String text) {
        this.synonyms = synonyms;
        set = new HashSet<>();
        for(int i=0; i<synonyms.size(); i++) {
            for(int j=0; j<synonyms.get(i).size(); j++) {
                set.add(synonyms.get(i).get(j));
            }
        }
        this.text = text;
        words = text.split(" ");
        map = new HashMap<>();
        ans = new ArrayList<>();
    }
    //合并近义词集合
    void makeSynonyms() {
        for(List<String> synonym : synonyms) {  //取出一对近义词
            Set<String> set1 = map.getOrDefault(synonym.get(0), new HashSet<>());
            Set<String> set2 = map.getOrDefault(synonym.get(1), new HashSet<>());

            if(set1 != set2) {  //合并两个集合
                Set<String> ret = new HashSet<>(set1);  //排除重复元素
                ret.addAll(set2);

                ret.add(synonym.get(0));        //添加这这一对近义词到集合中
                ret.add(synonym.get(1));

                for(String s : ret) {
                    map.put(s, ret);        //更新近义词集合中所有字符串的映射对
                }
            }
        }
    }
    //dfs生成所有的句子
    void dfs(int curr, int end, String tmp) {
        if(curr > end) {
            tmp = tmp.substring(0, tmp.length()-1); //删除结尾处的空格
            ans.add(tmp);
            return;
        }
        if(set.contains(words[curr])) {
            Set<String> set = map.get(words[curr]);
            for(String s : set) {       //对所有的近义词进行dfs
                dfs(curr+1, end, tmp + s + " ");
            }
        }else {
            dfs(curr+1, end, tmp + words[curr] + " ");
        }
    }
}
```

## 4.不相交的握手

![](https://tyh-blog-image.oss-cn-beijing.aliyuncs.com/LeetCode-%E5%9B%BE%E7%89%87/%E5%B1%8F%E5%B9%95%E5%BF%AB%E7%85%A7%202019-11-16%20%E4%B8%8B%E5%8D%8811.49.22.png)

```
输入：num_people = 2
输出：1

输入：num_people = 4
输出：2

输入：num_people = 6
输出：5

输入：num_people = 8
输出：14
```
* `2 <= num_people <= 1000`
* `num_people % 2 == 0`

卡塔兰数

> 卡塔兰数是组合数学中一个常在各种计数问题中出现的数列。以比利时的数学家欧仁·查理·卡特兰（1814–1894）命名。

![](https://wikimedia.org/api/rest_v1/media/math/render/svg/57de4926a69e67cdcdf999030c5ec3c25d97b0c9)

公式：
$f(n) = \sum_{i=0}^{n-1} f(i) \times f(n-i-1)$

说到底其实满足动态规划的性质，随便找个顶点作为支点，它有n种方案可以将图划分为两部分，每个方案的划分数为两个子部分划分方案的乘积，最后累加即可。

```java
class Solution {
    int mod = (int)Math.pow(10, 9) + 7;
    public int numberOfWays(int num_people) {
        if(num_people % 2 != 0) return 0;
        int n = num_people / 2;
        int[] dp = new int[501];
        dp[0] = 1;
        dp[1] = 1;
        for(int i=2; i<=n; i++) {  
            for(int j=0; j<i; j++) {
                dp[i] += dp[j] * dp[i-j-1] % mod;
                dp[i] %= mod;
            }
        }
        return dp[n];
    }
}
```

---
## 总结：

这周还是没有全做出来，说到底还是编程素质不够，继续培养吧，顺利做出来前两道题，第三道知道如何做，卡在了如何将近义词合并成集合，`set`相关知识需要掌握;第四道知道要用动态规划，但是没找到最优子结构，数论的知识也要掌握，总之这周题的题不是特别难，中规中矩。
