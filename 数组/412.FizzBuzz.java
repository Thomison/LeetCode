/*************************************************************************
    > File Name: 412.FizzBuzz.java
    > Author: 唐以恒
    > Mail: 1021953309tyh@gmail.com 
    > Created Time: 2019年11月27日 星期三 23时56分11秒
 ************************************************************************/
//2ms
class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> ans=new ArrayList<>(n);
        for(int i=1; i<=n; i++) {
            if(i%3==0 && i%5==0) ans.add("FizzBuzz");
            else if(i%3==0) ans.add("Fizz");
            else if(i%5==0) ans.add("Buzz");
            else ans.add(String.valueOf(i));
        }
        return ans;
    }
}
