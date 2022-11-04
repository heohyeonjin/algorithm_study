import java.io.*;
import java.util.*;
class Position{
    public int x;
    public int y;
    public Position(int x, int y){
        this.x=x;
        this.y=y;
    }
}
class B16234{
    static int answer = 0;
    static int start,end;
    static int N;
    static int dir_x[] = {1,-1,0,0};
    static int dir_y[] = {0,0,-1,1}; // 오,왼,상,하
    static int arr[][];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer str = new StringTokenizer(br.readLine());
        N = Integer.parseInt(str.nextToken());
        start = Integer.parseInt(str.nextToken());
        end = Integer.parseInt(str.nextToken());
        arr = new int[N][N];
        for(int i=0;i<N;i++){
            String tmp[] = br.readLine().split(" ");
            for(int j=0;j<N;j++){
                arr[i][j] = Integer.parseInt(tmp[j]);
            }
        }
        Solve();
        System.out.println(answer);
    }

    public static void Solve(){
        Queue<Position> queue = new LinkedList<>();
        Stack<Position> stack = new Stack<>();
        boolean flag = false;
        boolean visited[][] = new boolean[N][N];

        while(true) { // 몇번 answer
            flag = false;
            for (int i=0; i < N; i++) {
                for (int j = 0; j < N; j++) { // queue 시작
                    if(visited[i][j]==false) {
                        queue.offer(new Position(i, j));
                        visited[i][j] = true;
                        int sum = 0;
                        while (!queue.isEmpty()) { // 일단 queue
                            Position tmp = queue.poll();
                            sum+=arr[tmp.x][tmp.y];
                            stack.add(tmp);
                            int x = tmp.x;
                            int y = tmp.y;
                            for (int k = 0; k < 4; k++) {
                                int x_c = x + dir_x[k];
                                int y_c = y + dir_y[k];
                                if (x_c != -1 && y_c != -1 && x_c != N && y_c != N) {
                                    int gap = Math.abs(arr[x_c][y_c] - arr[x][y]);
                                    if (gap >= start && gap <= end && visited[x_c][y_c] == false) {
                                        visited[x_c][y_c] = true;
                                        queue.offer(new Position(x_c, y_c));
                                    }
                                }
                            }
                        }
                        if(stack.size()>1){
                            flag = true;
                        }

                        int avg = sum/stack.size();
                        while (!stack.isEmpty()) {
                            Position tmp = stack.pop();
                            arr[tmp.x][tmp.y] = avg;
                        }
                    }
                }
            }
            if(flag==false)
                break;
            visited = new boolean[N][N];
            answer++;
        }

    }
}