import java.util.*;
import java.io.*;

class B9934 {
    public static int K;
    public static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();

    public static void binaryTree(int[] input, int level) {
        if (level == K) return;

        int mid = input.length / 2;
        int root = input[mid];
        tree.get(level).add(root);

        int[] left = Arrays.copyOfRange(input, 0, mid);
        int[] right = Arrays.copyOfRange(input, mid + 1, input.length);
        binaryTree(left, level + 1);
        binaryTree(right, level + 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        for (int i=0; i<K; i++) tree.add(new ArrayList<>());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int count = (int) Math.pow(2, K) - 1;
        int[] nodes = new int[count];
        for (int i=0; i<count; i++) nodes[i] = Integer.parseInt(st.nextToken());

        binaryTree(nodes, 0);
        for (int i=0; i<K; i++) {
            for (Integer x : tree.get(i)) System.out.print(x + " ");
            System.out.println();
        }
    }
}