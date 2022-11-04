import java.io.*;
import java.util.*;
class Sub{
    public String name;
    public int stat;

    public Sub(String name, int stat){
        this.name = name;
        this.stat = stat;
    }
}
class B22860{
    static HashMap<String,ArrayList<Sub>> folder = new HashMap<>(); // 폴더 구조
    static HashMap<String, Integer> file = new HashMap<>(); // 파일 개수/종류
    static int total,type;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(br.readLine());
        int folder_num = Integer.parseInt(input.nextToken());
        int file_num = Integer.parseInt(input.nextToken());

        for(int i=0;i<(folder_num+file_num);i++){
            input = new StringTokenizer(br.readLine());
            String pa = input.nextToken();
            String ch = input.nextToken();
            int stat = Integer.parseInt(input.nextToken());
            ArrayList<Sub> tmp;
            if(!folder.containsKey(pa))
                tmp = new ArrayList<>();
            else
                tmp = folder.get(pa);
            tmp.add(new Sub(ch,stat));
            folder.put(pa,tmp);

            if(stat==0) // 파일이면
                if(!file.containsKey(ch)){
                    file.put(ch,0);
                }
        }

        int query_num = Integer.parseInt(br.readLine());
        for(int i=0;i<query_num;i++){
            input = new StringTokenizer(br.readLine(),"/");
            ArrayList<Sub> answer = null;
            while(input.hasMoreTokens()){ // 원하는 지점까지 도달
                String tmp = input.nextToken();
                answer = folder.get(tmp);
            }
            total=0;type=0;
            print(answer);
            for(Map.Entry<String, Integer> x : file.entrySet()){
                if(x.getValue()!=0){
                    type++;
                }
                total += x.getValue();
            }
            for(Map.Entry<String,Integer> x : file.entrySet()){ // 해시맵 초기화
                file.put(x.getKey(),0);
            }
            System.out.println(type+" "+total);
        }
    }
    public static void print(ArrayList<Sub> list){
        if(list==null){
            return;
        }
        for(Sub x : list){
            if(x.stat==1){
                if(folder.containsKey(x.name)){
                    print(folder.get(x.name));
                }
            }
            else{
                file.put(x.name,file.get(x.name)+1);
            }
        }
    }

}