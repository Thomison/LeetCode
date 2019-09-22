/*
资料：
    新建一个表达式,
    如果当前字符为变量或者为数字，则压栈，
    如果是运算符，则将栈顶两个元素弹出作相应运算，结果再入栈，
    最后当表达式扫描完后，栈里的就是结果。
*/
/*
原理：
    逆波兰表达式，也叫后缀表达式
    其将运算符放在两个操作数后面，利用栈LIFO特性，便于计算机理解操作含义
    并将优先级高的操作符相对于优先级低的操作符在表达式中顺序靠前.
*/
class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> evalStack = new Stack<>();       //存储临时结算结果Integer
        List<String> operator = Arrays.asList("+","-","*","/"); //存储几个运算符
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
    1.因为运算符不用入栈，栈中存储的就可以都是Integer，避免繁琐的类型转换
    2.注意出栈的两个操作数operand1，operand2的顺序
*/
