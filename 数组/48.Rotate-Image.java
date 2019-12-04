//1.create a new matrix
class Solution {
    public void rotate(int[][] matrix) {
        int n=matrix.length;
        //copy the value of matrix to new_matrix
        int[][] newMatrix=new int[n][n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                newMatrix[i][j]=matrix[i][j];
            }
        }
        //change the value of matrix according to new_matrix
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                matrix[j][n-1-i]=newMatrix[i][j];
            }
        }
    }
}
//2.axisymmetric twice
class Solution {
    public void rotate(int[][] matrix) {
        int n=matrix.length;
        for(int i=0; i<n/2; i++) {
            for(int j=i; j<n-1-i; j++) {
                int tmp=matrix[i][j];
                matrix[i][j] = matrix[n-1-j][i];
                matrix[n-1-j][i] = matrix[n-1-i][n-1-j];
                matrix[n-1-i][n-1-j] = matrix[j][n-1-i];
                matrix[j][n-1-i]=tmp;
            }
        }
    }
}
//3.move four elements once
class Solution {
    public void rotate(int[][] matrix) {
        int n=matrix.length;
        for(int i=0; i<n/2; i++) {
            for(int j=i; j<n-1-i; j++) {
                int tmp=matrix[i][j];
                matrix[i][j] = matrix[n-1-j][i];
                matrix[n-1-j][i] = matrix[n-1-i][n-1-j];
                matrix[n-1-i][n-1-j] = matrix[j][n-1-i];
                matrix[j][n-1-i]=tmp;
            }
        }
    }
}

