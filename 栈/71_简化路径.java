/*
思路：在栈中进行规范处理，处理完之后逆序输出
*/
class Solution {
    //简化或者是将给出的unix绝对路径转换为规范路径
    public String simplifyPath(String path) {
        if(path == null)    return path;
        Stack<Character> charStack = new Stack<>();
        //入栈，遇到'/'时进行规范化处理
        for(int i=0; i<path.length(); i++) {
            if(charStack.empty()) {
                charStack.push(path.charAt(i));
            }
            else {
                if(path.charAt(i) != '/') {
                    charStack.push(path.charAt(i));
                } else {        //规范化处理
                    if(charStack.peek() == '/') {       //1.多个连续'/'用一个代替
                        charStack.pop();
                    }
                    else if(charStack.peek() == '.'){
                        int dot = 0;
                        while(charStack.peek() != '/' && charStack.peek() == '.') {
                            charStack.pop();
                            dot ++;
                        }
                        if(dot == 1 && charStack.peek() == '/'){    //2.处理/.的情况
                            charStack.pop();//返回本一级 
                            i--;   
                            continue;
                        }
                        if(dot == 2 && charStack.peek() == '/') {       //3.处理/..的情况
                            charStack.pop();
                            if(!charStack.empty()) {
                                while(charStack.peek() != '/')  
                                    charStack.pop();//返回上一级
                                i--;
                                continue;
                            }   
                        }
                    }
                    charStack.push(path.charAt(i));
                }
            }
        }
        //如果是以..或.结尾, 单独处理
        if(charStack.peek() == '.') {
            int dot = 0;
            while(charStack.peek() != '/' && charStack.peek() == '.') {
                charStack.pop();
                dot ++;
            }
            if(dot == 1 && charStack.peek() == '/') {
                charStack.pop();        //删除'/' , 返回本级
                if(charStack.empty()) {
                    charStack.push('/');
                }
            } else if(dot == 2 && charStack.peek() == '/') {
                charStack.pop();
                if(!charStack.empty()) {
                    while(charStack.peek() != '/')  
                        charStack.pop();   //返回上一级
                    charStack.pop();   
                }else {
                    charStack.push('/');
                }  
            }
        } else if(charStack.peek() == '/') {
            charStack.pop();    //4.处理以'/'结尾的情况, 此'/'不能是开头
            if(charStack.empty())
                charStack.push('/');
        }
        //出栈，接到目标字符串的头部
        String result = "";
        while(!charStack.empty()) {
            result = charStack.pop() + result;
        }
        return result;
    }
}

class Solution {
    public String simplifyPath(String path) {
        if(path == null)    return path;
        //将字符串通过'/'分割成字符串列表
        List<String> tokens = splitToTokens(path, '/');
        //创建堆栈，用于规范路径
        Stack<String> tokenStack = new Stack<>();
        //遍历tokens，入栈
        for(int i = 0; i < tokens.size(); i ++) {
            if(tokenStack.empty()) {
                tokenStack.push(tokens.get(i));
            }else {         //特殊符号，需要做规范处理
                if(tokens.get(i).equals("/")) {
                    if(tokenStack.peek().equals("/")) {
                        continue;   //1.在规范路径中，多个连续斜杠需要用一个斜杠替换。
                    }
                    tokenStack.push("/");
                }else if(tokens.get(i).equals(".")) {   //2.规范指向本路径'.'的操作
                    continue;
                }else if(tokens.get(i).equals("..")) {  //3.规范指向上一个路径'..'的操作
                    String s = tokenStack.pop();
                    if(tokenStack.empty()) {
                        tokenStack.push(s);
                        continue;   //3.从根目录向上一级是不可行的
                    }else {
                        tokenStack.pop();
                    }
                }else {     //正常路径名，直接入栈
                    tokenStack.push(tokens.get(i));
                }
            }
        }
        //4.规范处理'/'结尾
        if(tokenStack.peek().equals("/")) {
            tokenStack.pop();
            if(tokenStack.empty()) {    //避免正确结果为"/"的情况被误删
                tokenStack.push("/");
            }
        }
        //所有元素出栈，接在目标字符串的头部(头插法)
        String standPath = "";
        while(!tokenStack.empty()) {
            standPath = tokenStack.pop() + standPath;
        }

        return standPath;
    }
    public List<String> splitToTokens(String string, char c) {
        //以'/'分割字符串，转换为字符串数组tokens
        List<String> tokens = new ArrayList<>();
        //双指针，分割字符串
        int i = 0, j = 0;
        while(i < string.length() && j < string.length()) {
            //单独处理字符串的头部'/'
            if(i == 0 && j == 0 && string.charAt(0) == c) {
                tokens.add(String.valueOf(c)); 
                i ++;
                j ++;
                continue;
            }
            //处理字符串除头部以外的'/'
            if(string.charAt(j) == c) {
                if(i != j) {
                    tokens.add(string.substring(i, j));
                    i = j;
                }else {
                   tokens.add(String.valueOf(c));
                   i ++;
                   j ++; 
                }
            }else {
                j ++;
            }
        }
        //处理末尾没有以'/'结尾的情况
        if(i < string.length() && j == string.length()) {
            tokens.add(string.substring(i, j));
        }
        return tokens;
    }
}

/*
总结：
    思路：
        1.将字符串按照'/'分割成token流
        2.将token流入栈，特殊情况像"/" "." ".."需要进行规范处理
        3."/"在头部，和其他位置意义不一样，需要分开处理
        4."."表示同一级, 不用处理，直接continue
        5.".."表示上一级, 需要处理，注意要分两种情况
            1)上一级是根目录"/"的情况，直接continue
            2)上一级不是跟目录的情况，需要出栈两个元素："/"和上一级的目录名
        6.最后需要是否检查以"/"结尾，若是，同样需要分是否表示根目录两种情况处理
        7.将处理好的tokens出栈，依次插入到新创建的空字符串头部。
*/

//精炼 一句话思路：目录名入栈，遇到.跳过; 遇到..上一级目录名(非根目录)出栈,根目录则跳过; 最后输出栈中的目录名(用/来连接)
class Solution {
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();    //栈中用于存储路径的目录名
        for(String s: path.split("/")) {
            if(s.isEmpty()) continue;
            else {
                if(s.equals("..")) {
                    if(!stack.empty())  stack.pop();    //上一级的目录名出栈
                }
                else if(!s.equals(".")) stack.push(s);  //当前目录名入栈
            }
        }
        String result = "";
        while(!stack.empty()) result = "/" + stack.pop() + result;
        return (result.isEmpty())? "/" : result;
    }
}
