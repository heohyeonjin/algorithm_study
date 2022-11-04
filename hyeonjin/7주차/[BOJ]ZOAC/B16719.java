import java.io.*;
class B16719{
    static boolean answer[];
    static String input;
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      input = br.readLine();
      int s = 0;
      int e = input.length()-1; // 인덱스 기준
        answer = new boolean[input.length()];
      solve(s,e);
    }
    public static void solve(int s, int e){
        if(s>e)
            return;
        int min_idx = 0;
        int tmp = Integer.MAX_VALUE;
        for(int i=s;i<=e;i++) {
            if (tmp > input.charAt(i)) {
                tmp = input.charAt(i);
                min_idx = i;
            }
        }
          answer[min_idx] = true;
          for(int j=0;j<input.length();j++){
              if(answer[j]){
                  System.out.print(input.charAt(j));
              }
          }
          System.out.println();
          solve(min_idx+1,e);
          solve(s,min_idx-1);
        }
    }
