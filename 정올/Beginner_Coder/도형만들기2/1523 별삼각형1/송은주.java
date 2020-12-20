/**************************************************************
    Problem: 1523
    User: 01026488131
    Language: Java
    Result: Success
    Time:169 ms
    Memory:12852 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
class Main{
    private static int n;
    private static int m;
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
         
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
         
        if(n<1 || n>100 || m<1 || m>3) {
            System.out.println("INPUT ERROR!");
            return;
        }
        StringBuilder sb = new StringBuilder();
         
        switch(m) {
        case 1:
            for(int i=0; i<n; ++i) {
                for(int j=0; j<=i; ++j) {
                    sb.append('*');
                }
                sb.append('\n');
            }
            break;
        case 2:
            for(int i=n; i>0; --i) {
                for(int j=0; j<i; ++j) {
                    sb.append('*');
                }
                sb.append('\n');
            }
            break;
        case 3:
            int temp = 1;
            for(int i=n; i>0; --i) {
                for(int j=0; j<i-1; ++j) {
                    sb.append(' ');
                }
                for(int j=0; j<temp; ++j) {
                    sb.append('*');
                }
                temp+=2;
                sb.append('\n');
            }
            break;
        }
         
        System.out.println(sb);
    }
     
}
