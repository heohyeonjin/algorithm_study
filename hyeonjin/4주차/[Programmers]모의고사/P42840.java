import java.util.*;
class P42840 {
    public int[] solution(int[] answers) {
        ArrayList<Integer> rst = new ArrayList<>();
        int[] one = {1,2,3,4,5};
        int[] two = {2,1,2,3,2,4,2,5};
        int[] thr = {3,3,1,1,2,2,4,4,5,5};
        int s_one=0;
        int s_two=0;
        int s_thr=0;

        for(int i=0;i<answers.length;i++){
            if(answers[i]==one[i%one.length]){
                s_one++;
            }
            if(answers[i]==two[i%two.length]){
                s_two++;
            }
            if(answers[i]==thr[i%thr.length]){
                s_thr++;
            }
        }

        int tmp[] = new int[3];
        int result[] = new int[3];
        result[0] = s_one;
        result[1] = s_two;
        result[2] = s_thr;
        for(int i=0;i<3;i++){
            tmp[i] = result[i];
        }
        Arrays.sort(tmp);
        int max = tmp[2];

        int k=0;
        for(int i=0;i<result.length;i++){
            if(max==result[i]){
                rst.add(i+1);
            }
        }
        int answer[] = {};
        answer = rst.stream().mapToInt(i->i).toArray();

        return answer;
    }
}