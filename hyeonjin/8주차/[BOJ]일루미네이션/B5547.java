import javax.swing.text.Position;
import java.io.*;
import java.util.*;
class pos{
    public int row;
    public int col;

    public pos(int row, int col){
        this.row = row;
        this.col = col;
    }
}
class B5547{
    static int row,col;
    static boolean visited[][];
    static boolean checkZero[][];
    static int map[][];
    static Queue<pos> queue = new LinkedList<>();
    static int wall_cnt;
    static int dir_x_odd[] = {0,1,0,-1,-1,-1};
    static int dir_y_odd[] = {-1,0,1,1,0,-1};
    static int dir_y_even[] = {-1,-1,0,1,1,0};
    static int dir_x_even[] = {0,1,1,1,0,-1};

    public static void main(String[] args) throws IOException {
     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer str = new StringTokenizer(br.readLine());
        col = Integer.parseInt(str.nextToken()); // 8
        row = Integer.parseInt(str.nextToken()); // 4
        visited = new boolean[row][col];
        checkZero = new boolean[row][col];
        map = new int[row][col];
        for(int i=0;i<row;i++) {
            str = new StringTokenizer(br.readLine());
            for(int j=0;j<col;j++){
                map[i][j] = Integer.parseInt(str.nextToken());
            }
        }
        solveWall();
        System.out.println(wall_cnt);
    }
    public static void solveWall(){
     for(int i=0;i<row;i++){
         for(int j=0;j<col;j++) {
             if (map[i][j] == 1) { // 벽인 경우
                 if(visited[i][j] == false) {
                     queue.offer(new pos(i, j));
                     visited[i][j] = true;
                    while(!queue.isEmpty()) {
                         pos tmp = queue.poll();
                         int cnt = 0;
                         for (int k=0; k<6; k++) {
                             int c_row, c_col;
                             if (tmp.row % 2 == 0) { // 짝수 행
                                 c_row = tmp.row + dir_y_even[k];
                                 c_col = tmp.col + dir_x_even[k];
                             } else { // 홀수 행
                                 c_row = tmp.row + dir_y_odd[k];
                                 c_col = tmp.col + dir_x_odd[k];
                             }
                             if(c_row==-1 || c_col ==-1 || c_row ==row || c_col ==col || map[c_row][c_col]==0){ // 외곽은 그냥 벽 갯수만 카운팅
                                 cnt ++;
                             }
                             else if (c_row!=-1 && c_col !=-1 && c_row!=row && c_col != col & map[c_row][c_col] == 1 && visited[c_row][c_col]==false) {
                                 visited[c_row][c_col] = true;
                                 queue.offer(new pos(c_row, c_col));
                             }
                         }
                        wall_cnt+=cnt;
                     }
                 }
             }
             else{ // 중간에 뚫린데 카운트
                 if(checkZero[i][j]==false){
                     int blank_num = solveBlank(i,j);
                     wall_cnt-=blank_num;
                 }
             }
         }
     }
    }
    public static int solveBlank(int r, int c){
        int cnt = 0;
        Queue<pos> q = new LinkedList<>();
        Stack<pos> s = new Stack<>();

            q.offer(new pos(r,c));
            checkZero[r][c] = true;
            s.add(new pos(r,c));

            while(!q.isEmpty()){ // 0으로 연결된 부분 다 queue에 저장
                pos tmp = q.poll();
                for(int i=0;i<6;i++){
                    int c_row, c_col;
                    if(tmp.row%2==0){
                        c_row = tmp.row + dir_y_even[i];
                        c_col = tmp.col + dir_x_even[i];
                    }
                    else{
                        c_row = tmp.row + dir_y_odd[i];
                        c_col = tmp.col + dir_x_odd[i];
                    }
                    if(c_row!=-1 && c_col !=-1 && c_row != row && c_col != col){
                        if(map[c_row][c_col]==0 && checkZero[c_row][c_col] == false){
                            checkZero[c_row][c_col] = true;
                            q.offer(new pos(c_row,c_col));
                            s.add(new pos(c_row,c_col));
                        }
                        else if(map[c_row][c_col]==1){
                            cnt++;
                        }
                    }
                }
            }
            while(!s.isEmpty()){ // 해당 블럭 주변이 외곽이면 counting X
                pos p = s.pop();
                for(int i=0;i<6;i++){
                    int c_row, c_col;
                    if(p.row%2==0){
                        c_row = p.row + dir_y_even[i];
                        c_col = p.col + dir_x_even[i];
                    }
                    else{
                        c_row = p.row + dir_y_odd[i];
                        c_col = p.col + dir_x_odd[i];
                    }
                    if(c_row==-1 || c_col==-1 || c_row==row || c_col ==col){
                        return 0;
                    }
                }

            }
        return cnt;
    }

}