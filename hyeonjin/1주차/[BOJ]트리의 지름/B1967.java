import java.io.*;
import java.util.*;

class Edge{
    public int node;
    public int dis;
    public Edge(int node, int dis){
        this.node = node;
        this.dis = dis;
    }
}
class B1967{
    static int n;
    static List<Edge> graph[];
    static int sum;
    static int maxLeaf;
    static int max=-1;
    static ArrayList<Integer> leaf;
    static boolean visited[];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        graph = new ArrayList[n+1];
        for(int i=0;i<=n;i++){
            graph[i] = new ArrayList<Edge>();
        }
        for(int i=0;i<n-1;i++){
            String str[] = br.readLine().split(" ");
            int v1 = Integer.parseInt(str[0]);
            int v2 = Integer.parseInt(str[1]);
            int cost = Integer.parseInt(str[2]);
            graph[v1].add(new Edge(v2,cost));
            graph[v2].add(new Edge(v1,cost));
        }

        visited = new boolean[n+1];
        dfs(6,0); // 임의의 지점에서 가장 긴 값이 나오는  찾기
        visited = new boolean[n+1];
//        System.out.println(max+","+maxLeaf);
        dfs(maxLeaf,0); // maxLeaf를 루트로 지정하고 기장 긴 거리(지름) 구하기
        System.out.println(max+","+maxLeaf);

    }
    public static void dfs(int v,int sum) {
        visited[v] = true;
        if(max<sum){
            max = sum;
            maxLeaf = v;
        }
        for (Edge x : graph[v]) {
            if (!visited[x.node]) {
                dfs(x.node, sum + x.dis);
            }
        }
    }
}