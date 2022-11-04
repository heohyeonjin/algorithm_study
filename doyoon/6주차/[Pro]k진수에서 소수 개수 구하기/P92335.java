package week06.P92335;

import java.util.*;
import java.io.*;

class P92335 {
    public boolean isPrime(String temp) {
        Long num = Long.parseLong(temp);

        if (num == 0 || num == 1) return false;
        if (num == 2) return true;

        int limit = (int) Math.sqrt(num);
        for (int i=2; i<=limit; i++) {
            if (num % i == 0) return false;
        }

        return true;
    }

    public int solution(int n, int k) {
        int answer = 0;
        StringBuilder change = new StringBuilder();

        while (n > k) {
            change.append(n % k);
            n /= k;
        }
        change.append(n % k);
        change.reverse();

        int zero = 0;
        String temp = "";
        for (char ch : change.toString().toCharArray()) {
            if (ch == '0') {
                if (!temp.equals("") && isPrime(temp)) answer++;
                temp = "";
                zero++;
            }
            else temp += ch;
        }

        if (zero == 0 && isPrime(change.toString())) answer++;
        else if (!temp.equals("") && isPrime(temp)) answer++;

        return answer;
    }
}