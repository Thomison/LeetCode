class Solution {
    public boolean isGoodArray(int[] nums) {
        int curr = nums[0];
        for(int i : nums) {     //只用找到一组互质的数即可
            curr = gcd(curr, i);    
        }
        return curr == 1;
    }
    //辗转相除
    int gcd(int a, int b) {
        if(b == 0)  return a;
        return gcd(b, a%b);
    }
}
