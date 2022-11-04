import java.util.*;
import java.io.*;
class Bead{
    public int cnt;
    public int read_x;
    public int read_y;
    public int blue_x;
    public int blue_y;
    public Bead(int cnt){
        this.cnt = cnt;
    }
    public void setRed(int read_x, int read_y){
        this.cnt = cnt;
        this.read_x = read_x;
        this.read_y = read_y;
    }
    public void setBlue(int blue_x, int blue_y){
        this.blue_x = blue_x;
        this.blue_y = blue_y;
    }
}
class B134600{
    static int N,M;
    static char board[][];
    static int dx[] = {0,1,0,-1};
    static int dy[] = {-1,0,1,0};
    static Queue<Bead> queue = new LinkedList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        board = new char[N][M];
        Bead bead = new Bead(0);
        for(int i=0;i<N;i++){
           board[i] = br.readLine().toCharArray();
           for(int j=0;j<M;j++){
               if(board[i][j]=='B'){
                   bead.setBlue(j,i);
               }
               if(board[i][j]=='R'){
                   bead.setRed(j,i);
               }
           }
        }
        queue.offer(bead);
        int answer = BFS();
        System.out.println(answer);
    }
    public static int BFS(){
        while(!queue.isEmpty()) {
            Bead bead = queue.poll();
            int t_b_x = bead.blue_x;
            int t_b_y = bead.blue_y;
            int t_r_x = bead.read_x;
            int t_r_y = bead.read_y;
            int cnt = bead.cnt;
            if(cnt>=10) break;
            cnt++;
            for (int i=0;i<4; i++) { // 위, 오, 아래, 왼
                boolean blue_hole = false;
                boolean red_hole = false;
              int b_x = t_b_x; int b_y = t_b_y;
              int r_x = t_r_x; int r_y = t_r_y;
                while (true) { // 빨간 공 move
                    r_x += dx[i];
                    r_y += dy[i];
                    if (board[r_y][r_x] == 'O') red_hole=true;
                    if (board[r_y][r_x] == '#') {
                        r_x -= dx[i]; r_y -= dy[i]; break;
                    }
                }
                while (true) { //파란 공 move
                    b_x += dx[i];
                    b_y += dy[i];
                    if (board[b_y][b_x] == 'O') blue_hole=true;
                    if (board[b_y][b_x] == '#') {
                        b_x -= dx[i]; b_y -= dy[i];break;
                    }
                }
                if(red_hole&&!blue_hole) return cnt; // 빨간 공만 들어간 경우 리턴

                if(r_x==b_x && r_y==b_y){ // 빨간 공, 파란 공이 겹치는 경우
                 if(dx[i]==0){ // 위,아래
                     if(dy[i]==1){ // 아래
                         if(t_b_y<t_r_y) b_y-=dy[i];
                         else r_y-=dy[i];
                     }
                     else{ // 위
                         if(t_b_y<t_r_y) r_y-=dy[i];
                         else b_y-=dy[i];
                     }
                 }
                 else{ //왼쪽,오른쪽
                     if(dx[i]==-1){ //왼
                         if(t_b_x<t_r_x) r_x-=dx[i];
                         else b_x-=dx[i];
                     }
                     else{ // 오른
                         if(t_b_x<t_r_x) b_x-=dx[i];
                         else r_x-=dx[i];
                     }
                 }
                }
                Bead b = new Bead(cnt);
                b.setRed(r_x,r_y); b.setBlue(b_x,b_y);
                if((r_x==t_r_x && t_r_y==r_y) && (t_b_x==b_x && t_b_y==b_y)) continue; //빨간공, 파란공 하나라도 안움직였으면 큐에 삽입X
                if(!blue_hole) queue.offer(b); // 파란 공이 안들어간 경우만 queue에 넣기
            }
        }
        return -1;
    }
}