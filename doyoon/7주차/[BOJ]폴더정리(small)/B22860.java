package week07.B22860;

import java.util.*;
import java.io.*;

class Data {
    String name;
    int isFolder;

    Data(String name, int isFolder) {
        this.name = name;
        this.isFolder = isFolder;
    }
}

// 폴더정리 (small)
public class B22860 {
    public static HashMap<String, ArrayList<Data>> directory;

    public static HashMap<String, Integer> arrangement(String query) {
        String[] folders = query.split("/");
        Stack<String> stack = new Stack<>();
        stack.push(folders[folders.length-1]);

        HashMap<String, Integer> info = new HashMap<>();
        while (!stack.isEmpty()) {
            String curD = stack.pop();
            if (directory.get(curD) == null) continue;

            for (Data data : directory.get(curD)) {
                if (data.isFolder == 1) stack.push(data.name);
                else {
                    info.put(data.name, info.getOrDefault(data.name, 0) + 1);
                }
            }
        }

        return info;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 폴더 개수
        int M = Integer.parseInt(st.nextToken());   // 파일 개수

        directory = new HashMap<>();
        for (int i=0; i<N+M; i++) {
            st = new StringTokenizer(br.readLine());
            String parent = st.nextToken();
            String name = st.nextToken();
            int isFolder = Integer.parseInt(st.nextToken());

            ArrayList<Data> temp = directory.getOrDefault(parent, new ArrayList<>());
            temp.add(new Data(name, isFolder));
            directory.put(parent, temp);
        }

        int Q = Integer.parseInt(br.readLine());
        int[][] answer = new int[Q][2];

        for (int i=0; i<Q; i++) {
            String query = br.readLine();
            HashMap<String, Integer> info = arrangement(query);
            answer[i][0] = info.size();
            for (String name : info.keySet()) answer[i][1] += info.get(name);
        }

        for (int i=0; i<Q; i++) System.out.println(answer[i][0] + " " + answer[i][1]);
    }
}