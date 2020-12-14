/**************************************************************
    Problem: 1338
    User: 01026488131
    Language: Java
    Result: Success
    Time:341 ms
    Memory:17404 kb
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
         
        for(int i=0; i<n; ++i) {
            for(int j=n-i, k=i, l=n-1; j>0; --j, ++k, --l) {
//              System.out.println(k+","+l);
                input[k][l] = q.peek();
                q.offer(q.poll());
            }
//          System.out.println("==");
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; ++i) {
            for(int j=0; j<n; ++j) {
                if('A'<=input[i][j] && input[i][j] <='Z')
                    sb.append(input[i][j]).append(' ');
                else sb.append("  ");
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
}
