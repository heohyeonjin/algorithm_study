// 트리순회
import java.io.*;
import java.util.*;
class Node{
   public int left;
   public int right;
   public Node(int left, int right){
       this.left = left;
       this.right = right;
   }
}
class B22856{
    static  ArrayList<Node> graph[];
    static int parent[];
    static int cnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N+1];
        parent = new int[N+1];
        parent[1] = 0;
        for(int i=0;i<N+1;i++){
            graph[i] = new ArrayList<>(); // 초기화
        }
        int start = 0;
        // graph 생성
        for(int i=0;i<N;i++){
            StringTokenizer str = new StringTokenizer(br.readLine());
            while(str.hasMoreTokens()){
                int root = Integer.parseInt(str.nextToken());
                if(i==0) start = root;
                int left = Integer.parseInt(str.nextToken());
                int right = Integer.parseInt(str.nextToken());
                graph[root].add(new Node(left,right));
                if(left!=-1)
                    parent[left] = root;
                if(right!=-1)
                    parent[right] = root;
            }
        }

        int answer = 0;
        answer+=(N-1)*2; // 총 간선의 수

        if(graph[start].get(0).right==-1){
            System.out.println(answer);
        }
        else{
            dfs(graph[start].get(0).right); // 마지막 노드 구하기 (root에서 제일 처음 right child부터 구해야 함)
            answer-=cnt-1;
            System.out.println(answer);
        }
    }
    public static void dfs(int v){
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(v);

        while(!queue.isEmpty()){
            int tmp = queue.poll();
            if(graph[tmp].get(0).right==-1){ // right child가 없으면
                if(graph[tmp].get(0).left!=-1){
                    queue.offer(graph[tmp].get(0).left);
                }
                else{
                   // tmp 값이 마지막 노드
                   int find = tmp;
                   while(find!=0){
                    find = parent[find];
                    cnt++;
                   }
                }
            }
            else{
                queue.offer(graph[tmp].get(0).right);
            }
        }

    }
}