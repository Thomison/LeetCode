/*
思路：先将表达式转化为逆波兰表达式，再在逆波兰表达式上计算最终值
*/
/*
中缀表达式转逆波兰表达式:
    创建两个栈，s1用于得到运算符在表达式中所处位置，存放的是运算符String
              s2用于存放转换的逆波兰表达式，存放的是运算符和操作数String
    1)读取原中缀表达式，若当前字符是操作数，直接入栈s2
    2)若当前字符是操作符：
        栈为空：直接入栈s1
        栈不为空：
            1)若当前字符是"("，直接入栈s1
            2)...是")"，则与出栈顺序中最近的"("匹配，并将之间的运算符出栈s1 入栈s2
            3)若当前字符优先级大于栈顶，则直接入栈s1
            4)若当前字符小于等于栈顶，栈顶出栈s1 入栈s2，直到当前字符优先级大于栈顶 当前字符入栈s1
    3)逆序输出栈s2，即为逆波兰表达式
*/
class Solution {
    public int calculate(String s) {
        //1.中缀表达式的字符串转换为tokens流
        String[] tokens = stringToTokens(s.replaceAll(" ",""));
        //2.将中缀表达式转化为逆波兰表达式
        String[] tokensRPN = convertToRPN(tokens);
        //3.计算逆波兰表达式，返回计算结果
        return evalRPN(tokensRPN);
    }
    /*
    function: 将表达式的字符串转换为tokens流
    parameter: 用于表示中缀表达式的字符串
    return: tokens流
    */
    public String[] stringToTokens(String evaluator) {
        List<String> tokens = new ArrayList<>();
        List<Character> operator = Arrays.asList('+','-','*','/','(',')');
        for(int i=0; i<evaluator.length(); i++) {
            if(operator.contains(evaluator.charAt(i))) {    //读取操作符
                tokens.add(String.valueOf(evaluator.charAt(i)));
            }else {     //读取完整操作数
                int j = 0;
                for(j=i+1; j<evaluator.length(); j++) {   //直到读取到下一个操作符为止
                    if(operator.contains(evaluator.charAt(j))) {
                        tokens.add(evaluator.substring(i, j));  //截取字符串
                        break;
                    }
                }
                if(j == evaluator.length()) {       //最后一个操作数不以操作符来界定
                    tokens.add(evaluator.substring(i, j));
                    break;
                }
                i = j - 1;
            }
        }
        return tokens.toArray(new String[tokens.size()]);   //列表转数组
    }
    /*
    function: 中缀表达式转换为逆波兰表达式
    parameter: 用于表示中缀表达式的字符串数组
    return: 用于表示逆波兰表达式的字符串数组
    */
    public String[] convertToRPN(String[] tokens) {
        Stack<String> s1 = new Stack<>();
        Stack<String> s2 = new Stack<>();
        List<String> operator = Arrays.asList("+","-","*","/","(",")");
        Map<String, Integer> operPriority = new HashMap<>();
        operPriority.put("(",0);
        operPriority.put("+",1); 
        operPriority.put("-",1);
        operPriority.put("*",2); 
        operPriority.put("/",2);
        for(int i=0; i<tokens.length; i++) {
            if(!operator.contains(tokens[i])) { //当前字符是操作数
                s2.push(tokens[i]);
            }else {         //当前字符是操作符
                if(s1.empty()){      //栈为空
                    s1.push(tokens[i]);
                }else {        //栈不为空
                    if(tokens[i].equals("(")) {  //当前操作符是"("
                        s1.push(tokens[i]);
                    }else if(tokens[i].equals(")")) {    //当前操作符是")"
                        while(!s1.peek().equals("(")) {
                            s2.push(s1.pop());
                        }
                        s1.pop();       //弹出操作符"("
                    }else {
                        if(operPriority.get(tokens[i]) > operPriority.get(s1.peek())) {     //优先级高于栈顶
                            s1.push(tokens[i]);
                        }else {         //优先级等于或低于栈顶
                            while(operPriority.get(tokens[i]) <= operPriority.get(s1.peek())) {
                                s2.push(s1.pop());
                                if(s1.empty())
                                    break;
                            }
                            s1.push(tokens[i]);     //直到栈为空或者优先级高于栈顶
                        }
                    }
                }
            }
        }
        if(!s1.empty()) {     //处理逆波兰表达式末尾剩余的操作符 
            while(!s1.empty()) 
                s2.push(s1.pop());
        }
        List<String> resultList = new ArrayList<>();    //后缀表达式的逆序
        while(!s2.empty())
            resultList.add(s2.pop());
        String[] resultArray = new String[resultList.size()];
        for(int i=0; i<resultList.size(); i++) {
            resultArray[i] = resultList.get(resultList.size() - 1 - i);
        }
        return resultArray;
    }
    /*
    function: 计算逆波兰表达式
    parameter: 用于表示逆波兰表达式的字符串数组
    return: 计算结果的整型值
    */
    public int evalRPN(String[] tokens) {
        Stack<Integer> evalStack = new Stack<>();       //存储临时结算结果Integer
        List<String> operator = Arrays.asList("+","-","*","/"); //存储运算符
        //遍历token流
        for(int i=0; i<tokens.length; i++) {
            if(evalStack.empty()) {     //栈为空时
                evalStack.push(Integer.valueOf(tokens[i]));
            }else {     //栈不为空时
                if(!operator.contains(tokens[i])) {  //当前字符为变量或数字
                    evalStack.push(Integer.valueOf(tokens[i]));
                }else {         //当前字符为操作符
                    int operand2 = evalStack.pop(), operand1 = evalStack.pop(); //将栈顶两个元素弹出
                    switch(tokens[i]) {     //栈顶两个元素弹出作相应运算，结果再入栈
                        case "+":
                            evalStack.push(
                                operand1 + operand2);
                            break;
                        case "-":
                            evalStack.push(
                                operand1 - operand2);
                            break;
                        case "*":
                            evalStack.push(
                                operand1 * operand2);
                            break;
                        case "/":
                            evalStack.push(
                                operand1 / operand2);
                            break;
                    }
                }
            }
        }
        //取出栈顶值，即为所求
        return evalStack.pop();
    }
}
/*
总结：
    1.要对字符串进行规范化处理，删除所有的空格部分，String.replaceAll()
    2.String转String[]时，注意操作数的界定，不仅是以操作符（末尾最后一个操作数时）
    3.采用预先设定优先级，忘了加上"("的优先级，导致空指针错误，运算符入栈会比较与"("的优先级！！！
*/

