import java.util.*;
import java.io.*;
class B2800{
    static HashSet<String> answer = new HashSet<>(); // 제거된 문자열 세트
    static ArrayList<int[]> index = new ArrayList<>(); // 괄호 인덱스 정보
    static int combi[];
    static boolean visited[];
    static String input;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine();
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<input.length();i++){
            if(input.charAt(i)=='(') stack.push(i);
            if(input.charAt(i)==')') {
                int idx = stack.pop();
                index.add(new int[]{idx,i});
            }
        }

        int pair_num = index.size(); // 괄호 쌍 개수
        for(int i=1;i<=pair_num;i++){
            visited = new boolean[pair_num];
            combi = new int[pair_num];
            combi(0, 0,pair_num,i);
        }

        ArrayList<String> sort_answer = new ArrayList<>(answer);
        Collections.sort(sort_answer);
        for(String x : sort_answer){
            System.out.println(x);
        }
    }
    public static void combi(int start, int depth, int n ,int r){ // n개 중 r개 선택
        if(depth==r){
            remove_pair(combi, r);
            return;
        }

        for(int i=start; i<n; i++){
            if(!visited[i]){
                visited[i] = true;
                combi[depth] = i;
                combi(i,depth+1, n,r);
                visited[i] = false;
            }
        }
    }
    public static void remove_pair(int [] combi, int r){

       StringBuffer sb = new StringBuffer();
       boolean check[] = new boolean[input.length()];

       for(int i=0;i<r;i++){
           check[index.get(combi[i])[0]] = true;
           check[index.get(combi[i])[1]] = true;
       }

       for(int i=0;i<input.length();i++){ // 문자열 길이
           if(!check[i]) sb.append(input.charAt(i));
       }
        answer.add(sb.toString());
    }
}