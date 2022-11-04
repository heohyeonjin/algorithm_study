//import java.util.*;
//class Node implements Comparable<Node>{
//    public int v;
//    public int cost;
//    public Node(int v, int cost){
//        this.v = v;
//        this.cost = cost;
//    }
//    @Override
//    public int compareTo(Node o){
//        if(this.cost==o.cost) return this.v-o.v;
//        return this.cost-o.cost;
//    }
//}
//class P72413 {
//    static ArrayList<Node> graph[];
//    public int solution(int n, int s, int a, int b, int[][] fares) {
//        int answer = Integer.MAX_VALUE;
//        int dist[][] = new int[n+1][n+1];
//        graph = new ArrayList[n+1];
//        for(int i=1;i<=n;i++) graph[i] = new ArrayList<>();
//
//        for(int i=0;i<fares.length;i++){
//            int u = fares[i][0];
//            int v = fares[i][1];
//            int cost = fares[i][2];
//
//            graph[u].add(new Node(v,cost));
//            graph[v].add(new Node(u,cost));
//        }
//
//        for(int i=1;i<=n;i++){ //한 정점에 대해
//            dist[i] = dijskra(i,n);
//        }
//
//        for(int i=1;i<=n;i++){
//            answer = Math.min(answer,dist[s][i]+dist[i][a]+dist[i][b]);
//        }
//
//        return answer;
//    }
//    public static int[] dijskra(int start,int n){
//        int dist[] = new int[n+1];
//        for(int i=1;i<=n;i++) dist[i] = Integer.MAX_VALUE;
//        dist[start] = 0;
//
//        PriorityQueue<Node> pq = new PriorityQueue<>();
//        pq.offer(new Node(start,0));
//
//        while(!pq.isEmpty()){
//            Node node = pq.poll(); // 특정 정점 까지, 최소 비용
//
//            if(node.cost!=dist[node.v]) continue; // queue에 있는 값이 최소비용이 아닌 경우 --> queue에서 빠져나오기 전에 해당 최소 비용이 갱신된 경우
//            for(Node x : graph[node.v]){
//                if(node.cost+x.cost<dist[x.v]){
//                    dist[x.v] = node.cost+x.cost;
//                    pq.offer(new Node(x.v,dist[x.v]));
//                }
//            }
//        }
//        return dist;
//    }
//}
