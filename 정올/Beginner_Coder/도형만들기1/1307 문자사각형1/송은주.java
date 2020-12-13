/**************************************************************
    Problem: 1307
    User: 01026488131
    Language: Java
    Result: Success
    Time:367 ms
    Memory:17536 kb
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
         
         
        for(int i=n-1; i>=0; --i) {
            for(int j=n-1; j>=0; --j) {
                input[j][i] = q.peek();
                q.offer(q.poll());
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
