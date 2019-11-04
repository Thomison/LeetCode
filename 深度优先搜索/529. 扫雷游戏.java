/*
搜索终止的条件：
    到达边界或者与炸弹相邻的方块

是否在该顶点的周围继续dfs 取决于该顶点周围是否有炸弹，所以dfs之前要有个先验的过程
*/

class Solution {
    int row;
    int col;
    char[][] ans;
    boolean[][] isVisited;
    int[][] move = {{-1,-1},{-1,0},{-1,1},
                    {0,-1},       {0,1},
                    {1,-1},{1,0},{1,1}};

    public char[][] updateBoard(char[][] board, int[] click) {
        //初始化
        row = board.length;
        col = board[0].length;
        ans = board;
        isVisited = new boolean[row][col];
        //开始游戏
        if(ans[click[0]][click[1]] == 'M') {        //只能点击'M'或'E'
            ans[click[0]][click[1]] = 'X';
        }else {
            dfs(click[0], click[1]);
        }
        //返回游戏结果
        return ans;
    }
    //深度优先搜索
    void dfs(int i, int j) {
        if(i<0 || i>=row || j<0 || j>=col ) {   //边界
            return;
        }
        if(!isVisited[i][j]) {
            isVisited[i][j] = true; //搜索到未访问的顶点
        }else return;   //搜索到访问过的顶点

        int bomb_num = toGetBombNum(i, j);
        if(bomb_num != 0) {     //该顶点周围有炸弹，不在它周围继续搜索
            ans[i][j] = Character.forDigit(bomb_num, 10);
        }else {
            ans[i][j] = 'B';    //该顶点周围有炸弹，在它周围继续搜索

            for(int k=0; k<move.length; k++) {
                    dfs(i+move[k][0], j+move[k][1]);
            }
        }
    }
    //获取该顶点周围的炸弹数量
    int toGetBombNum(int i, int j) {
        int ret = 0;
        for(int k=0; k<move.length; k++) {
            int m=i+move[k][0], n=j+move[k][1];
            if(m<0 || m>=row || n<0 || n>=col ) {
                continue;
            }
            if(ans[m][n] == 'M') {
                ret++;
            }
        }
        return ret;
    }
}
