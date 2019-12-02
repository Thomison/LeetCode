/*************************************************************************
    > File Name: 2.不浪费原料的汉堡制作方案.java
    > Author: 唐以恒
    > Mail: 1021953309tyh@gmail.com 
    > Created Time: 2019年12月02日 星期一 13时47分11秒
 ************************************************************************/
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
