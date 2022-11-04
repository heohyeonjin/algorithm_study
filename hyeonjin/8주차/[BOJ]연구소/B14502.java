import java.io.*;
import java.util.*;
class Pos{
    public int x;
    public int y;
    public Pos(int x, int y){
        this.x = x;
        this.y = y;
    }
}
class B14502{
    static int n;
    static int m;
    static int map[][];
    static Queue<Pos> queue = new LinkedList<>();
    static int dir_x[] = {0,1,0,-1};
    static int dir_y[] = {-1,0,1,0};
    static int answer = Integer.MIN_VALUE;
    static int wall_num = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer str = new StringTokenizer(br.readLine());
        n = Integer.parseInt(str.nextToken());
        m = Integer.parseInt(str.nextToken());
        map = new int[n][m]; // 행 열
        for(int i=0;i<n;i++){ // map 초기화
            str = new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                map[i][j] = Integer.parseInt(str.nextToken());
                if(map[i][j]==2){
                    queue.offer(new Pos(i,j));
                }
                if(map[i][j]==1){
                    wall_num++;
                }
            }
        }
        set_wall(0,0,0);
        System.out.println(answer);
    }
    public static void set_wall(int x, int y, int wall){
        // dfs
        if(wall==3){ // 바이러스 퍼뜨리기
            int virus_num = virus();
            int safe = n*m-virus_num-wall_num-3; // 안전구역
            if(safe>answer){
                answer = safe;
            }
            return;
        }
        for(int i=x;i<n;i++){
            for(int j=y;j<m;j++){
                if(map[i][j]==0){
                    map[i][j]=1;
                    set_wall(x,y,wall+1);
                    map[i][j]=0;
                }
            }
        }
    }
    public static int virus(){
        Queue<Pos> copy_queue = new LinkedList<>();
        for(Pos x : queue){
            copy_queue.offer(new Pos(x.x,x.y));
        }
        int copy_map[][] = new int[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                copy_map[i][j] = map[i][j];
            }
        }
        //bfs
        int cnt=0;
        while(!copy_queue.isEmpty()){
            Pos tmp = copy_queue.poll();
            cnt++; // 바이러스 수
            int x = tmp.x;
            int y = tmp.y;
            for(int i=0;i<4;i++){
                int c_x = x+dir_x[i];
                int c_y = y+dir_y[i];
                if(c_x==-1 || c_y ==-1 || c_x==n || c_y==m) continue;
                else if(copy_map[c_x][c_y]==0){
                    copy_queue.offer(new Pos(c_x,c_y));
                    copy_map[c_x][c_y] = 2;
                }
            }
        }
        return cnt;
    }
}