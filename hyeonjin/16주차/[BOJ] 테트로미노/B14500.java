import java.util.*;
import java.io.*;
class B14500{
    static int N,M;
    static int paper[][];
    static boolean visited[][];
    static int dir_row[] = {-1,0,1,0};
    static int dir_col[] = {0,1,0,-1};
    static int answer = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input[] = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        paper = new int[N][M];

        for(int i=0;i<N;i++){
            input = br.readLine().split(" ");
            for(int j=0;j<M;j++){
                paper[i][j] = Integer.parseInt(input[j]);
            }
        }

        visited = new boolean[N][M];
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                visited[i][j]=true;
                dfs(1,i,j,paper[i][j]);
                visited[i][j]=false;
            }
        }
        System.out.println(answer);
    }
    public static void dfs(int depth, int row, int col, int sum){

        if(depth==4){
            answer = Math.max(answer,sum); return;

        }
        for(int i=0;i<4;i++){
            int n_row = row+dir_row[i];
            int n_col = col+dir_col[i];
            if(n_row<0||n_col<0||n_row>N-1||n_col>M-1) continue;
            if(!visited[n_row][n_col]) {

                visited[n_row][n_col] = true;
                dfs(depth + 1, n_row, n_col,sum+paper[n_row][n_col]);
                visited[n_row][n_col] = false;

                if(depth == 2) {
                    visited[n_row][n_col] = true;
                    dfs(depth + 1, row, col,sum+paper[n_row][n_col]);
                    visited[n_row][n_col] = false;
                }
            }
        }
    }
}