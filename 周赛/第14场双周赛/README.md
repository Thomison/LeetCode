![](https://tyh-blog-image.oss-cn-beijing.aliyuncs.com/pixiv%E4%BD%9C%E5%93%81/DL_QbsNU8AAt6C6.jpeg)

# 第14场双周赛

## 1.十六进制魔术数字

![](https://tyh-blog-image.oss-cn-beijing.aliyuncs.com/LeetCode-%E5%9B%BE%E7%89%87/14%E5%9C%BA%E5%8F%8C%E5%91%A8%E8%B5%9B/%E5%B1%8F%E5%B9%95%E5%BF%AB%E7%85%A7%202019-12-01%20%E4%B8%8A%E5%8D%8812.28.50.png)

注意数字范围，最大为$10^12$

所以不能用$Integer$，而是$Long$。

将十进制数转怀为十六进制的字符串表示形式，再在$StringBuilder$进行具体操作。

```java
class Solution {
    public String toHexspeak(String num) {
        String hex16=Long.toHexString(Long.valueOf(num)).toUpperCase();
        String ret="";
        StringBuilder builder=new StringBuilder(hex16);
        for(int i=0; i<builder.length(); i++) {
            if(builder.charAt(i)=='0') builder.replace(i, i+1, "O");
            else if(builder.charAt(i)=='1') builder.replace(i, i+1, "I");
            else if(builder.charAt(i)>='2' && builder.charAt(i)<='9') return "ERROR";
        }
        return builder.toString();
    }
}
```

## 2.删除区间

![](https://tyh-blog-image.oss-cn-beijing.aliyuncs.com/LeetCode-%E5%9B%BE%E7%89%87/14%E5%9C%BA%E5%8F%8C%E5%91%A8%E8%B5%9B/%E5%B1%8F%E5%B9%95%E5%BF%AB%E7%85%A7%202019-12-01%20%E4%B8%8A%E5%8D%8812.29.08.png)

将两种区间所处情况枚举：
- A、B相离
- A包含B
- B包含A
- A错开B
- B错开A


```java
class Solution {
    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        int left=toBeRemoved[0], right=toBeRemoved[1];
        List<List<Integer>> ret=new ArrayList<>();
        for(int i=0; i<intervals.length; i++) {
            int x=intervals[i][0], y=intervals[i][1];
            
            if(left<=x && y<=right) continue;
            else if(x<left && y>right) {
                List<Integer> tmp=new ArrayList<>();
                List<Integer> tmp2=new ArrayList<>();
                tmp.add(x); tmp.add(left);
                tmp2.add(right); tmp2.add(y);
                ret.add(tmp); ret.add(tmp2);
            }
            else if(x<left && left<=y && y<=right) {
                List<Integer> tmp=new ArrayList<>();
                tmp.add(x); tmp.add(left);
                ret.add(tmp);
            }
            else if(left<=x && x<=right && right<y) {
                List<Integer> tmp=new ArrayList<>();
                tmp.add(right); tmp.add(y);
                ret.add(tmp);
            }else {
                List<Integer> tmp=new ArrayList<>();
                tmp.add(x); tmp.add(y);
                ret.add(tmp);
            }
        }
        return ret;
    }
}
```

## 3.删除树节点

![](https://tyh-blog-image.oss-cn-beijing.aliyuncs.com/LeetCode-%E5%9B%BE%E7%89%87/14%E5%9C%BA%E5%8F%8C%E5%91%A8%E8%B5%9B/%E5%B1%8F%E5%B9%95%E5%BF%AB%E7%85%A7%202019-12-01%20%E4%B8%8A%E5%8D%8812.29.31.png)

![](https://tyh-blog-image.oss-cn-beijing.aliyuncs.com/LeetCode-%E5%9B%BE%E7%89%87/14%E5%9C%BA%E5%8F%8C%E5%91%A8%E8%B5%9B/%E5%B1%8F%E5%B9%95%E5%BF%AB%E7%85%A7%202019-12-01%20%E4%B8%8A%E5%8D%8812.29.38.png)

按照题意计算以每个根节点为子树的其内所有节点值之和。

再将和为0的子树的根节点与父节点的连接断开，并指向-2，这样使得该子树内的所有节点回溯时无法回到-1，而是-2，从而达到删除的目的。

遍历数组，将回溯到-2而不是-1的结点(即为被删除的结点)，`ret--`表示该结点被删除。

```java
class Solution {
    public int deleteTreeNodes(int nodes, int[] parent, int[] value) {
        //保存每个结点对应的子树所有节点值之和
        int[] tree_value=new int[value.length];
        int ret=nodes;
        for(int i=parent.length-1; i>=0; i--) {
            int j=i;
            while(j!=-1) {
                tree_value[j]+=value[i];
                j=parent[j];
            }
        }
        //断开子树和-1的连接
        for(int i=0; i<parent.length; i++) {
            if(tree_value[i]==0) {
                parent[i]=-2;
            }
        }
        //若结点沿着路径不能到达-1 则说明被删除
        for(int i=parent.length-1; i>=0; i--) {
            int j=i;
            while(j>0) {
                j=parent[j];
            }
            if(j==-2) ret--;
        }
        return ret;
    }
}
```

## 4.矩形内船只的数目

![](https://tyh-blog-image.oss-cn-beijing.aliyuncs.com/LeetCode-%E5%9B%BE%E7%89%87/14%E5%9C%BA%E5%8F%8C%E5%91%A8%E8%B5%9B/%E5%B1%8F%E5%B9%95%E5%BF%AB%E7%85%A7%202019-12-01%20%E4%B8%8A%E5%8D%8812.30.10.png)

![](https://tyh-blog-image.oss-cn-beijing.aliyuncs.com/LeetCode-%E5%9B%BE%E7%89%87/14%E5%9C%BA%E5%8F%8C%E5%91%A8%E8%B5%9B/%E5%B1%8F%E5%B9%95%E5%BF%AB%E7%85%A7%202019-12-01%20%E4%B8%8A%E5%8D%8812.30.22.png)

![](https://tyh-blog-image.oss-cn-beijing.aliyuncs.com/LeetCode-%E5%9B%BE%E7%89%87/14%E5%9C%BA%E5%8F%8C%E5%91%A8%E8%B5%9B/%E5%B1%8F%E5%B9%95%E5%BF%AB%E7%85%A7%202019-12-01%20%E4%B8%8A%E5%8D%8812.30.32.png)

因为矩形区域内最多只有10个点存在船，所以我们只需要简单的二分搜索即可。

终止条件为：搜索矩形区域没有船(返回1)，搜索矩形区域收敛于一个点(返回1).

每次将当前矩形区域划分为两半。

```java
class Solution {
    public int countShips(Sea sea, int[] topRight, int[] bottomLeft) {
        if(!sea.hasShips(topRight, bottomLeft)) return 0;
        
        int ret=0;
        if(bottomLeft[0]<topRight[0]) {
            int m=(bottomLeft[0]+topRight[0])/2;
            ret+=countShips(sea, new int[]{m, topRight[1]}, bottomLeft);
            ret+=countShips(sea, topRight, new int[]{m+1, bottomLeft[1]});
        }else if(bottomLeft[1]<topRight[1]) {
            int m=(bottomLeft[1]+topRight[1])/2;
            ret+=countShips(sea, topRight, new int[]{bottomLeft[0], m+1});
            ret+=countShips(sea, new int[]{topRight[0], m}, bottomLeft);
        }else {
            ret=1;
        }
        return ret;
    }
}
```
