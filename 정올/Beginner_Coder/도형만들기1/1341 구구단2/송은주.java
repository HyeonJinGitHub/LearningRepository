/**************************************************************
    Problem: 1341
    User: 01026488131
    Language: Java
    Result: Success
    Time:232 ms
    Memory:12988 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
class Main{
    private static int s;
    private static int e;
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine(), " ");
        s =  Integer.parseInt(st.nextToken());
        e =  Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        if(s>=e) {
            for(int i=s; i>=e; --i) {
                int cnt = 1;
                for(int j=1; j<=9; ++j) {
                    int temp = i*j;
                    if(temp<10) {
                        sb.append(i).append(" * ").append(j).append(" =  ").append(temp).append("   ");
                    }else {
                        sb.append(i).append(" * ").append(j).append(" = ").append(temp).append("   ");
                    }
                    if(cnt++%3==0) {
                        sb.append('\n');
                    }
                }
                sb.append('\n');
            }
        }else {
            for(int i=s; i<=e; ++i) {
                int cnt = 1;
                for(int j=1; j<=9; ++j) {
                    int temp = i*j;
                    if(temp<10) {
                        sb.append(i).append(" * ").append(j).append(" =  ").append(temp).append("   ");
                    }else {
                        sb.append(i).append(" * ").append(j).append(" = ").append(temp).append("   ");
                    }
                    if(cnt++%3==0) {
                        sb.append('\n');
                    }
                }
                sb.append('\n');
            }
        }
         
        System.out.println(sb);
    }
}
