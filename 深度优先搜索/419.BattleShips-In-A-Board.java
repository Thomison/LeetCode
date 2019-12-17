/*************************************************************************
    > File Name: 419.BattleShips-In-A-Board.java
    > Author: 唐以恒
    > Mail: 1021953309tyh@gmail.com 
    > Created Time: 2019年12月17日 星期二 10时38分08秒
 ************************************************************************/
//dfs 
class Solution {
    int ans = 0;
    char[][] board;
    int row;
    int col;

    public int countBattleships(char[][] board) {
        this.board = board;
        row = board.length;
        col = board[0].length;
        for(int i=0; i<row; i++) {
            for(int j=0; j<col; j++) {
                if(board[i][j] == 'X') {
                    dfs(i, j);
                    ans++;
                }
            }
        }
        return ans;
    }
    void dfs(int i, int j) {
        if(i<0 || i>=row || j<0 || j>=col || board[i][j]=='.') {
            return;
        }
        if(board[i][j] == 'X') {
            board[i][j] = '.';
        }
        dfs(i+1, j);
        dfs(i-1, j);
        dfs(i, j+1);
        dfs(i, j-1);
    }
}

class Solution {

    public int countBattleships(char[][] board) {
        int ans = 0;
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[i].length; j++) {
                if(board[i][j] != 'X') continue;
                if(i==0 && j==0) {
                    ans++;
                }else if(i==0) {
                    if(board[0][j-1] != 'X') ans++;
                }else if(j==0) {
                    if(board[i-1][0] != 'X') ans++;
                }else {
                    if(board[i-1][j] != 'X' && board[i][j-1] != 'X') {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }
}
