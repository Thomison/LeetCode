/*
先对每个边界的O进行dfs 可达的O都标记为Y

剩下的O就是被环绕的区域

再遍历board,将O改为X，将Y改回O
*/

class Solution {
    boolean[][] visited;
    int row;
    int col;
    char[][] board;
    int[][] dire = {{-1,0},{1,0},{0,-1},{0,1}};
    
    public void solve(char[][] board) {
        this.board = board;
        row = board.length;
        if(row == 0 || row == 1)    return;
        col = board[0].length;
        if(col == 0 || col == 1)    return;
        
        visited = new boolean[row][col];
        //将边界可达的O转换为Y
        for(int i=0; i<row; i+=row-1) {
            for(int j=0; j<col; j++) {
                if(board[i][j]!='X') dfs(i, j);
            }
        }
        for(int i=0; i<row; i++) {
            for(int j=0; j<col; j+=col-1) {
                if(board[i][j]!='X') dfs(i, j);
            }
        }
        //将剩余的O转换为X 将Y转换回O
        for(int i=0; i<row; i++) {
            for(int j=0; j<col; j++) {
                if(board[i][j] == 'O') board[i][j] = 'X';
                else if(board[i][j] == 'Y') board[i][j] = 'O';
            }
        }
    }
    void dfs(int i, int j) {
        if(board[i][j]=='Y') return;
        
        visited[i][j] = true;       //只要被搜索到就将其转化为Y
        board[i][j] = 'Y';
        for(int k=0; k<dire.length; k++) {
            int x=i+dire[k][0];
            int y=j+dire[k][1];
            if(x<0 || x>=row || y<0 || y>=col) {  //超出边界
                continue;
            }else if(!visited[x][y] && board[x][y]=='O'){  //搜索未被访问到的O结点
                dfs(x, y);
            }
        }
    }
}
