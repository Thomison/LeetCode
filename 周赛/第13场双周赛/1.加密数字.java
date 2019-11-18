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
