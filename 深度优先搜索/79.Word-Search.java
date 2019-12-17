/*************************************************************************
    > File Name: 79.Word-Search.java
    > Author: 唐以恒
    > Mail: 1021953309tyh@gmail.com 
    > Created Time: 2019年12月17日 星期二 10时59分34秒
 ************************************************************************/
class Solution {
    private boolean flag = false;   //判断是否找到可行解

    public boolean exist(char[][] board, String word) {
        if(board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }else if(word == null || word.length() == 0) {
            return true;
        }

        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[0].length; j++) {
                if(dfs(board, word, i, j, 0))
                    return true;
            }
        }
        return false;
    }
    private boolean dfs(char[][] board, String word, int i, int j, int cur) {
        if(cur == word.length()) {
            flag = true;    
            return true;
        }else if(i<0 || i>=board.length || j<0 || j>=board[0].length || board[i][j]!=word.charAt(cur)) {
            return false;
        }
        if(flag) return true;   //剪枝

        char tmp = board[i][j]; //副本
        board[i][j] = '.';      //表示该处字符已经被使用
        boolean res1 = dfs(board, word, i+1, j, cur+1);
        boolean res2 = dfs(board, word, i-1, j, cur+1);
        boolean res3 = dfs(board, word, i, j+1, cur+1);
        boolean res4 = dfs(board, word, i, j-1, cur+1);
        board[i][j] = tmp;  //回溯
        return res1 || res2 || res3 || res4;
    }
}
