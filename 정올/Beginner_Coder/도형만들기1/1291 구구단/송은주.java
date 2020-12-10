?
1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
22
23
24
25
26
27
28
29
30
31
32
33
34
35
36
37
38
39
40
41
42
43
44
45
46
47
48
49
50
51
52
53
54
55
56
57
58
59
60
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
