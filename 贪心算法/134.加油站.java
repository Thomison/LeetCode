/*
方法一：暴力匹配

T O(n^2) S O(1)

遍历加油站数组，表示从当前加油站出发，是否能够环绕一圈，如果下标与出发时的下标一致，表示环绕成功，返回出发加油站的下标

如果遍历完加油站数组，都没能返回表示无解，返回-1
*/
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        here:
        for(int i=0; i<n; i++) {
            //判断是否能够绕一圈回到当前加油站
            int j=i, curr_gas=0;
            //模拟环绕操作
            do{
                curr_gas += gas[j];     //加油
                if(curr_gas < cost[j])  continue here;
                else {
                    curr_gas -= cost[j];    //减去开销
                    j++;
                    if(j == n)  j = 0;
                }
            }while(j != i);
            return i;
        }
        return -1;
    }
}

/*
方法二：一次遍历

T O(n) S O(1)

车能够环绕一圈的条件是：

  所有站里的油总量要>=车子的总耗油量。
  车能够从i开到gas.length-1处，即i为结果。
  
证明：

如果有解，将解的下标作为分界处，前半段称为A，后半段称为B

从该解的位置出发,如果能过遍历到最后一个加油站店，那么B剩余的油量必定大于A每个站点所要消耗的油量

  因为总油量>=总消耗, A油量<A消耗 B油量>B消耗 所以B剩余的油量应该>=A多余的消耗
  关键在于找到临界点：最后一个不能到达站点的下标+1
  
重点：B剩余的油>=A缺少的总油。必然可以推出，B剩余的油>=A站点的每个子站点缺少的油。

*/

class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        //判断是否有解
        if(getSum(gas) < getSum(cost))  return -1;  //总油量<总消耗：一定无解
        else {  //总油量>=总消耗：一定有解
            int ans = 0, curr_gas = 0;
            for(int i=0; i<n; i++) {
                curr_gas += gas[i] - cost[i];
                if(curr_gas < 0) {
                    curr_gas = 0;
                    ans = i+1;
                }
            }
            return ans;
        }
    }
    //得到数组的和
    public int getSum(int[] nums) {
        int rs = 0;
        for(int i=0; i<nums.length; i++)    rs += nums[i];
        return rs;
    }
}
