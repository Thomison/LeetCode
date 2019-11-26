//枚举所有可能的逆波兰表达式，然后计算这个表达式.
/*
４个操作数不重复:24，３个操作符号可重复:64
共24*64=1536种情况
情况:
AB#C#D#
AB#CD##
ABC#D##
ABC##D#
ABCD###
*/
class Solution {
    List<String> all=new ArrayList<>(1536*5);
    int[] nums;
    char[] opers;

    public boolean judgePoint24(int[] nums) {
        this.nums=nums;
        createAll();
        for(String s: all) {
            if(isSame(s)) return true;
        }
        return false;
    }
    void createAll() {
        opers=new char[]{'+','-','*','/'};

        for(int i1=0; i1<4; i1++) {
            for(int i2=0; i2<4; i2++) {
                if(i1==i2)  continue;
                for(int i3=0; i3<4; i3++) {
                    if(i3==i1 || i3==i2)    continue;
                    int i4=(0+1+2+3)-i1-i2-i3;
                    for(int o1=0; o1<4; o1++) {
                        for(int o2=0; o2<4; o2++) {
                            for(int o3=0; o3<4; o3++) {
                                //AB#C#D#
                                all.add(""+nums[i1]+nums[i2]+opers[o1]+nums[i3]+opers[o2]+nums[i4]+opers[o3]);
                                //AB#CD##
                                all.add(""+nums[i1]+nums[i2]+opers[o1]+nums[i3]+nums[i4]+opers[o2]+opers[o3]);
                                //ABC#D##
                                all.add(""+nums[i1]+nums[i2]+nums[i3]+opers[o1]+nums[i4]+opers[o2]+opers[o3]);
                                //ABC##D#
                                all.add(""+nums[i1]+nums[i2]+nums[i3]+opers[o1]+opers[o2]+nums[i4]+opers[o3]);
                                //ABCD###
                                all.add(""+nums[i1]+nums[i2]+nums[i3]+nums[i4]+opers[o1]+opers[o2]+opers[o3]);
                            }
                        }
                    }
                }
            }
        }
    }
    boolean isSame(String str) {
        char[] chars=str.toCharArray();
        Stack<Double> s=new Stack<>();
        for(char c: chars) {
            if(c>='0' && c<='9') s.push(Double.valueOf(c));
            else {
                double operand2=s.pop(), operand1=s.pop();
                switch(c) {
                    case '+':
                        s.push(operand1+operand2);
                        break;
                    case '-':
                        s.push(operand1-operand2);
                        break;
                    case '*':
                        s.push(operand1*operand2);
                        break;
                    case '/':
                        if(operand2==0) return false;
                        else {s.push(operand1/operand2); break;}
                }
            }
        }
        double ret=s.pop();
        if(Math.abs(ret-24)<0.001) return true;
        else return false;
    }
}
