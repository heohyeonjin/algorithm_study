import java.util.*;
import java.io.*;

class B17073 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] tree = new ArrayList[N+1];
        for (int i=1; i<=N; i++) tree[i] = new ArrayList<>();

        for (int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }

        int leafNode = 0;
        for (int i=2; i<=N; i++) {
            if (tree[i].size() == 1) leafNode++;
        }

        System.out.println(String.format("%.10f", (double)W/leafNode));
    }
}