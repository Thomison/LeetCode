//匹配括号，利用栈的后进先出特性，每当右括号进入栈时，栈顶即为与之匹配的左括号
class Solution {
    private final int CATEGORIES = 3;
    //判断括号序列是否有效
    public boolean isValid(String s) {
        if(s == null)
            return true;
        if(s.length() % 2 != 0)
            return false;
        
        //建立规则
        List<Character> leftList = Arrays.asList('(','[','{');
        List<Character> rightList = Arrays.asList(')',']','}');
        Map<Character,Character> map = new HashMap<>();
        for(int i = 0; i < CATEGORIES; i ++)    
            map.put(rightList.get(i), leftList.get(i));
        
        //字符串中所有字符入栈，进行匹配
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); i ++) {
            //栈为空或者为左括号，直接入栈
            if(stack.empty() || leftList.contains(s.charAt(i))) {
                stack.push(s.charAt(i));
            }
            //栈为右括号，进行括号匹配
            else if(rightList.contains(s.charAt(i))) {
                //若匹配，则弹出左括号
                if(stack.peek() == map.get(s.charAt(i)))
                    stack.pop();
                //若不匹配，则入栈
                else 
                    stack.push(s.charAt(i));
            }
        }
        //匹配完整个字符串后，若栈中仍有元素即字符串无效
        if(stack.size() > 0)
            return false;
        else
            return true;
    }
}
