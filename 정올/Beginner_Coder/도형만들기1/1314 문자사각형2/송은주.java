/**************************************************************
    Problem: 1314
    User: 01026488131
    Language: Java
    Result: Success
    Time:331 ms
    Memory:17680 kb
****************************************************************/
 
 
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
 
class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
         
        int n = sc.nextInt();
        char[][] input = new char[n][n];
         
        Queue<Character>q = new LinkedList<>();
        for(int i=0; i<26; ++i) {
            q.offer((char) ('A'+i));
        }
         
        boolean flag = true;
        for(int i=0; i<n; ++i) {
            if(flag) {
                for(int j=0; j<n; ++j) {
                    input[j][i] = q.peek();
                    q.offer(q.poll());
                }
                flag = false;
            }else {
                for(int j=n-1; j>=0; --j) {
                    input[j][i] = q.peek();
                    q.offer(q.poll());
                }
                flag = true;
            }
        }
         
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; ++i) {
            for(int j=0; j<n; ++j) {
                sb.append(input[i][j]).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
}
