import java.util.*;
import java.io.*;
class B12100{
    // 위,오른쪽,아래,왼쪽
    static int dx[] = {0,1,0,-1}; //열
    static int dy[] = {-1,0,1,0}; //행
    static long answer = 0;
    static int N;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int [][] board = new int[N][N];
        for(int i=0;i<N;i++){
            String str[] = br.readLine().split(" ");
            for(int j=0;j<N;j++){
                board[i][j] = Integer.parseInt(str[j]);
                answer = Math.max(answer,board[i][j]);
            }
        }
        if(N==1){
            System.out.println(answer);
            return;
        }

        dfs(board,5);
        System.out.println(answer);

    }
    public static void dfs(int [][] board , int depth){
        if(depth==0) return;

        for(int i=0;i<4;i++){
           int update[][] = board_update(board,dx[i],dy[i]);
           dfs(update,depth-1);
        }
    }
    public static int [][] board_update(int [][] input, int x, int y){
        //최대 값 갱신
        int board[][] = new int[N][N];
        boolean check[][] = new boolean[N][N]; // 합쳐진 블록인지 확인
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                board[i][j] = input[i][j];
            }
        }
        // 아래, 오른쪽
        if((x==0&&y==1)||(x==1&&y==0)){
            for(int i=N-1; i>=0; i--) {
                for (int j=N-1; j>=0; j--) {
                    if(board[i][j]==0) continue;
                    int c_i = i; int c_j = j;
                    int n_i = i + y;
                    int n_j = j + x;
                    if (n_i < 0 || n_j < 0 || n_i > N - 1 || n_j > N - 1) continue; // 테두리

                    while(true){
                        if(board[n_i][n_j]!=0){
                            if (board[c_i][c_j] == board[n_i][n_j]&&!check[n_i][n_j]) {
                                board[n_i][n_j] = board[c_i][c_j] * 2;
                                board[c_i][c_j] = 0;
                                check[n_i][n_j] = true;
                                answer = Math.max(answer, board[n_i][n_j]);
                            }
                            break;
                        }
                        else{ // board가 0이면
                            board[n_i][n_j] = board[c_i][c_j];
                            board[c_i][c_j] = 0;
                            c_i = n_i; c_j = n_j;
                            n_i +=y; n_j +=x;
                        }
                        if (n_i < 0 || n_j < 0 || n_i > N - 1 || n_j > N - 1) break; // 테두리
                    }
                }
            }
        }
        // 위, 왼쪽
        if((x==0&&y==-1)||(x==-1&&y==0)){
            for(int i=0; i<N; i++) {
                for (int j=0; j<N; j++) {
                    if(board[i][j]==0) continue;
                    int c_i = i; int c_j = j;
                    int n_i = i + y;
                    int n_j = j + x;
                    if (n_i < 0 || n_j < 0 || n_i > N - 1 || n_j > N - 1) continue; // 테두리
                    while(true){
                        if(board[n_i][n_j]!=0){
                            if (board[c_i][c_j] == board[n_i][n_j] &&!check[n_i][n_j]) {
                                board[n_i][n_j] = board[c_i][c_j] * 2;
                                board[c_i][c_j] = 0;
                                check[n_i][n_j]=true;
                                answer = Math.max(answer, board[n_i][n_j]);
                            }
                            break;
                        }
                        else{ // board가 0이면
                            board[n_i][n_j] = board[c_i][c_j];
                            board[c_i][c_j] = 0;
                            c_i = n_i; c_j = n_j;
                            n_i +=y; n_j +=x;
                        }
                        if (n_i < 0 || n_j < 0 || n_i > N - 1 || n_j > N - 1) break; // 테두리
                    }

                }
            }
        }
           return board;
    }
}
