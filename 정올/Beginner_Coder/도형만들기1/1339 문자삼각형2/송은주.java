/**************************************************************
    Problem: 1339
    User: 01026488131
    Language: Java
    Result: Success
    Time:317 ms
    Memory:17224 kb
****************************************************************/
 
 
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
 
class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
         
        int n = sc.nextInt();
        if(n%2==0 || n<1 || n>100) {
            System.out.println("INPUT ERROR");
            return;
        }
        char[][] input = new char[n][n];
        Queue<Character>q = new LinkedList<>();
        for(int i=0; i<26; ++i) {
            q.offer((char) ('A'+i));
        }
         
        for(int i=n/2, temp=1; i>=0; --i, temp+=2) {
            for(int j=i, k=0; k<temp; ++k, ++j) {
                input[j][i] = q.peek();
                q.offer(q.poll());
            }
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
