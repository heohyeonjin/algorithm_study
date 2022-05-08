import java.util.*;
import java.io.*;

class Node {
    int vertex, weight;

    Node(int v, int w) {
        this.vertex = v;
        this.weight = w;
    }
}

class B1967 {
    public static ArrayList<ArrayList<Node>> tree;
    public static int[] visited;
    public static int max_idx, answer = Integer.MIN_VALUE;

    public static void DFS(int cur, int sum) {
        if (answer < sum) {
            answer = sum;
            max_idx = cur;
        }

        visited[cur] = 1;
        for (Node x : tree.get(cur)) {
            if (visited[x.vertex] == 0)
                DFS(x.vertex, sum + x.weight);
        }
        visited[cur] = 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        tree = new ArrayList<>();
        for (int i=0; i<=n; i++) tree.add(new ArrayList<>());

        StringTokenizer st;
        for (int i=0; i<n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            tree.get(a).add(new Node(b, w));
            tree.get(b).add(new Node(a, w));
        }

        visited = new int[n+1];
        DFS(1, 0);

        visited = new int[n+1];
        DFS(max_idx, 0);
        System.out.println(answer);
    }
}