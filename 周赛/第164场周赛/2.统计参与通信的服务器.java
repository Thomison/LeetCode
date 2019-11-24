//12ms
class Solution {
    int ans;
    int[][] grid;
    int row;
    int col;
    int[][] move={{-1,0},{1,0},{0,1},{0,-1}};
    public int countServers(int[][] grid) {
        this.ans=0;
        this.grid=grid;
        this.row=grid.length;
        this.col=grid[0].length;
        for(int i=0; i<row; i++) {
            for(int j=0; j<col; j++) {
                if(grid[i][j]==1) {
                    boolean flag=false;
                    for(int k=0; k<move.length; k++) {
                        int x=i+move[k][0];
                        int y=j+move[k][1];
                        while(x>=0 && x<row && y>=0 && y<col) {
                            if(grid[x][y]==1) {
                                flag=true;
                                break;
                            }
                            x+=move[k][0];
                            y+=move[k][1];
                        }
                    }
                    if(flag) ans++;
                }
            }
        }
        return ans;
    }
}
