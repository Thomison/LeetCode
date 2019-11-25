import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
//dfs构造出可能得算术表达式情况，然后用逆波兰表达式求解，判断每种情况是否等于24
class Solution {
    char[] operators={'+','-','*','/','(',')'};
    
    public boolean judgePoint24(int[] nums) {
        
    }

    /*
    input:  str
    output: int
    */
    int compute(String str) {
        //构造操作符优先级
        Map<Character, Integer> priority=new HashMap<>();
        for(char o: operators) {
            if(0=='(' || o==')') priority.put(o, 0);
            else if(o=='+' || o=='-') priority.put(o, 1);
            else priority.put(o, 2);
        }
        //表达式字符串转化为后缀字符列表
        char[] chars=str.toCharArray();
        Stack<Character> s1=new Stack<>(), s2=new Stack<>();
        for(char c: chars) {
            if(c>='0' && c<='9') s2.push(c);
            else {
                if(s1.empty())  s1.push(c);
                else {
                    if(c=='(') s1.push(c);
                    else if(c==')') {
                        while(!s1.peek('(')) s2.push(s1.pop());
                        s1.pop();   //pop '('
                    }else {
                        if(priority.get(c)>priority.get(s1.peek())) 
                            s1.push(c);
                        else {
                            while(priority.get(c)<priority.get(s1.peek())) {
                                s2.push(s1.pop());
                                if(s1.empty())  break;
                            }
                            s1.push(c);
                        }
                    }
                }
            }
        }
        if(!s1.empty()) {
            while(!s1.empty()) s2.push(s1.pop());
        }
        List<Character> postfix=new ArrayList<>();
        int cnt=0;
        while(!s2.empty()) {
            postfix.add(s2.pop());
        }
        //逆波兰表达式求解表达式
        Stack<Double> s3=new Stack<>();
        for(Character c: postfix) {
            if(s3.empty()) s3.push(Double.valueOf(c));
            else {
                if(c>='0' && c<='9') s3.push(Double.valueOf(c));
                else {
                    int num2=s3.pop(), num1=s3.pop();
                    switch(c) {
                        case '+':
                            s3.push(num1+num2);
                            break;
                        case '-':
                            s3.push(num1-num2);
                            break;
                        case '*':
                            s3.push(num1*num2);
                            break;
                        case '/':
                            s3.push(num1/num2);
                            break;
                    }
                }
            }
        }
        return (int)s3.pop();
    }
}
