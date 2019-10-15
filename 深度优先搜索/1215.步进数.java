/*

暴力解法就是遍历从low到high范围内的所有数字，再对当前数字从个位迭代到高位，进行判断是否为步进数，这种做法试了试，时间复杂度太高，放弃。

我们发现high是有上限的，加上满足步进数的数字不多，我们可以先列举完从0到2*10^9的所有步进数，再对给定的low-high进行筛选，筛选出来再排序即可。

搜索所有可能的步进数的方式采用dfs深度优先搜索。

*/

class Solution {
    private List<Long> allNum = new ArrayList<>();
    
    public List<Integer> countSteppingNumbers(int low, int high) {
        List<Integer> ans = new ArrayList<>();  //初始化
        //从0开始，找到0-2*10^9范围内所有的步进数(深度优先搜索)
        init();
        //对给出的low-high限定范围进行筛选步进数
        for(int i=0; i<allNum.size(); i++) {
            if(allNum.get(i) >= low && allNum.get(i) <= high) {
                ans.add(Integer.parseInt(allNum.get(i)+""));
            }
        }
        //排序
        Collections.sort(ans);
        return ans;
    }
    public void init() {
        allNum.add(0l);
        for(int i=1; i<=9; i++) {
            dfs(Integer.parseInt(i+""), i);
        }
    }
    public void dfs(long curr, int pre) {    //pre表示curr的个位数
        if(curr > 2000000000) return;
        allNum.add(curr);
        for(int i=0; i<=9; i++) {
            if(abs(i - pre) == 1) {
                dfs(curr * 10 + i, i);
            }
        }
    }
    public int abs(int i) {
        return (i >= 0) ? i : (-1)*i;
    }
}
