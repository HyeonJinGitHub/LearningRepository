/**************************************************************
    Problem: 1329
    User: 01026488131
    Language: Java
    Result: Success
    Time:358 ms
    Memory:17232 kb
****************************************************************/
 
 
import java.util.Scanner;
 
class Main{
    private static int n;
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
         
        n = sc.nextInt();
         
        if(n<1 || n>100 || n%2==0) {
            System.out.println("INPUT ERROR!");
            return;
        }
         
        StringBuilder sb = new StringBuilder();
        int temp = 0;
        int k = 1;
        for(int i=0; i<n; ++i) {
            for(int j=0; j<temp; ++j) {
                sb.append(' ');
            }
            for(int j=0; j<k; ++j) {
                sb.append('*');
            }
             
            if(i<n/2) {
                k+=2;
                temp++;
            }
            else {
                k-=2;
                temp--;
            }
             
            sb.append('\n');
        }
         
        System.out.print(sb.toString());
    }
}
