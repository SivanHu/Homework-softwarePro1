import java.util.Random;
import java.util.Scanner;

public class TestCal {
    public static void franction_consquence() {
        // TODO Auto-generated method stub

        // 用户输入两分数和运算符
        /*Scanner input = new Scanner(System.in);
        System.out.println("请用户输入第一个分数(格式a/b)");
        String data1 = input.next();
        System.out.println("请用户输入第二个分数(格式c/d)");
        String data2 = input.next();*/

        // 根据用户输入进行具体运算
        FractionCal cal = new FractionCal();
        System.out.println("运算结果为:");
        Random random = new Random();
        int a = random.nextInt(10)+1;
        int b = random.nextInt(10)+1;

        FractionCal fractionCal = new FractionCal();
        int f = fractionCal.f(a, b);
        if(f>1){
            a = a/f;
            b = b/f;
        }
        String data1 = a+"/"+b;
       /* System.out.println("--------------" + a+"/"+b);*/
        String str = data1;
        String consequence = str;
        String list[] = null;
        String mark = null;
        list = cal.compute(data1, consequence,mark);
       /* for (int i = 0; i < list.length; i++) {
            System.out.println("---"+list[i]);
        }*/
       int i = random.nextInt(3)+1;
       int j=0;
       String op =null;
       int flag = 0;
       while(j<i){
           op = list[2];
           System.out.println("++++" + op);
           if(flag ==0 && j == i-1){
               mark = op;
               list = cal.compute(list[0], list[1], mark);
               /*System.out.println("already");*/
           }else{
               list = cal.compute(list[0], list[1], mark);
           }

           if(list[2] != op ){
               flag = 1;
           }

           j++;
       }



        consequence = list[1]+"="+list[0];
        System.out.println(list[1]+"="+list[0]);
        Calculate.writeToFile(consequence+"\n");

//	  }
    }
}