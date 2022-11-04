import java.io.*;
import java.util.*;
class B13023{
    static int N,M;
    static ArrayList<Integer> graph[];
    static boolean visited[];
    static int answer = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer str = new StringTokenizer(br.readLine());
        N = Integer.parseInt(str.nextToken()); // 사람 수
        M = Integer.parseInt(str.nextToken()); // 관계 수
        visited = new boolean[N];
        graph = new ArrayList[N];
        for(int i=0;i<N;i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++){
            str = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(str.nextToken());
            int b = Integer.parseInt(str.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        for(int i=0;i<N;i++){
            DFS(0,i);
            if(answer==1) break;
        }

        System.out.println(answer);
    }
    public static void DFS(int level, int x){

        if(level == 4){
            answer = 1;
            return;
        }

        visited[x] = true;
        for(int tmp : graph[x]){
            if(!visited[tmp]){
                if(answer==1) return;
                DFS(level+1,tmp);
            }
        }
        visited[x] = false;
    }
}