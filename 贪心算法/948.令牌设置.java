/*************************************************************************
    > File Name: 948.令牌设置.java
    > Author: 唐以恒
    > Mail: 1021953309tyh@gmail.com 
    > Created Time: 2019年11月26日 星期二 22时31分21秒
 ************************************************************************/

//贪心策略：将tokens排好序，从小到大遍历数组，
//P>=tokens[i]时，减少能量P-=tokens[i++]，加分
//P<tokens[i]时，增加能量P+=tokens[i--]，减分
//如果增加能量后仍然P<token[i]，则返回当前分值＋１
//需要维护左右两个指针i,j
//注意边界条件：一开始就不能减少能量;增加最大能量后仍然不能转化最小能量;i==j时刚好轮到增加能量

//2ms
class Solution {
    public int bagOfTokensScore(int[] tokens, int P) {
        Arrays.sort(tokens); //排序
        int i=0, j=tokens.length-1;  //左右指针
        int ans=0;
        boolean flag=false; //用于判断提前结束的标志
        while(i<=j) {   //每个令牌只能使用一次
            if(P>=tokens[i]) {  //减少能量
                P-=tokens[i++];
                ans++;
                flag=false;
            }else { //增加能量
                if(flag) return ans+1;  //增加最大能量后无法减少最小能量
                if(i==j || i==0) return ans;
                P+=tokens[j--];
                ans--;
                flag=true;
            }
        }
        return ans;
    }
}
