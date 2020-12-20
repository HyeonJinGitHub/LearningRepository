/**************************************************************
    Problem: 1291
    User: 01026488131
    Language: Java
    Result: Success
    Time:365 ms
    Memory:16624 kb
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
         
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
             
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
             
            if(s<2 || s>9 || e<2 || e>9) {
                System.out.println("INPUT ERROR!");             
            }else {
                for(int i=1; i<=9; ++i) {
                    if(s>=e) {
                        for(int j=s; j>=e; --j) {
                            int temp = i*j;
                            if(temp<10) {
                                System.out.print(j + " * " + i + " =  " + temp+"   ");
                            }else {
                                System.out.print(j + " * " + i + " = " + temp+"   ");
                            }
                        }
                    }else {
                        for(int j=s; j<=e; ++j) {
                            int temp = i*j;
                            if(temp<10) {
                                System.out.print(j + " * " + i + " =  " + temp+"   ");
                            }else {
                                System.out.print(j + " * " + i + " = " + temp+"   ");
                            }
                        }
                    }
                    System.out.println();
                }
                break;
            }
             
        }
         
    }
}
