class Solution {
    public String minRemoveToMakeValid(String s) {
        Stack<Integer> stack = new Stack<>();   //存储下标
        StringBuilder builder = new StringBuilder(s);
        
        here:
        for(int i=0; i<s.length(); i++) {
            if(stack.empty()) 
                stack.push(i);
						else {
                if(s.charAt(i) != ')') 
                    stack.push(i);
                else {		//当前字符为右括号时
                    while(!stack.empty()) {
                        char c = s.charAt(stack.peek());
                        if(c == '(') {		//匹配到左括号
                            stack.pop();
                            continue here;
                        }else if(c == ')') {		//终止：1.栈空，2.遇到")"
                            break;
                        }else {
                            stack.pop();
                        }
                    }
                    stack.push(i);	//")"入栈
                }
            }
        }
        
        while(!stack.empty()) {
            int index = stack.pop();
            if(s.charAt(index) == '(' || s.charAt(index) == ')') {	//删除未匹配的括号字符
                builder.deleteCharAt(index);
            }
        }
        
        return builder.toString();
    }
}
