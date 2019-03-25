import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FractionCal {
    int numerator;  // 分子
    int denominator; // 分母

    FractionCal() {
    }

    FractionCal(int a, int b) {
        if (a == 0) {
            numerator = 0;
            denominator = 1;
        } else {
            setNumeratorAndDenominator(a, b);
        }
    }

    void setNumeratorAndDenominator(int a, int b) {  // 设置分子和分母
        int c = f(Math.abs(a), Math.abs(b));         // 计算最大公约数
        numerator = a / c;
        denominator = b / c;
        if (numerator < 0 && denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
    }


    int getNumerator() {
        return numerator;
    }

    int getDenominator() {
        return denominator;
    }

    int f(int a, int b) {  // 求a和b的最大公约数
        if (a < b) {
            int c = a;
            a = b;
            b = c;
        }
        int r = a % b;
        while (r != 0) {
            a = b;
            b = r;
            r = a % b;
        }
        return b;
    }

    FractionCal add(FractionCal r) {  // 加法运算
        int a = r.getNumerator();
        int b = r.getDenominator();
        int newNumerator = numerator * b + denominator * a;
        int newDenominator = denominator * b;
        FractionCal result = new FractionCal(newNumerator, newDenominator);
        return result;
    }

    FractionCal sub(FractionCal r) {  // 减法运算
        int a = r.getNumerator();
        int b = r.getDenominator();
        int newNumerator = numerator * b - denominator * a;
        int newDenominator = denominator * b;
        FractionCal result = new FractionCal(newNumerator, newDenominator);
        return result;
    }

    FractionCal muti(FractionCal r) { // 乘法运算
        int a = r.getNumerator();
        int b = r.getDenominator();
        int newNumerator = numerator * a;
        int newDenominator = denominator * b;
        FractionCal result = new FractionCal(newNumerator, newDenominator);
        return result;
    }

    FractionCal div(FractionCal r) {  // 除法运算
        int a = r.getNumerator();
        int b = r.getDenominator();
        int newNumerator = numerator * b;
        int newDenominator = denominator * a;
        FractionCal result = new FractionCal(newNumerator, newDenominator);
        return result;
    }

    static String operation1[] = {"+","-","*","÷"};

    // 封装了具体运算，主要为对输入进行转换，对输出封装
    public static String[] compute(String data1, String consequence, String mark) {
        String list[] = new String[3];

        Random random = new Random();
        /*int one = random.nextInt(10)+1;
        int two = random.nextInt(10)+1;
        String data1 = one+"/"+two;*/

        int three = random.nextInt(10)+1;
        int four = random.nextInt(10)+1;
        /**
         * 判断分数是否约简
         */
        FractionCal f1 = new FractionCal();
        int f = f1.f(three, four);
        if(f>1){
            three /= f;
            four /= f;
        }
        //
        String data2 = three+"/"+four;



        //将生成的真分数的分子分母取出
        StringTokenizer fenxi = new StringTokenizer(data1, "/");
        int data1_1 = Integer.parseInt(fenxi.nextToken());
        int data1_2 = Integer.parseInt(fenxi.nextToken());

        fenxi = new StringTokenizer(data2, "/");
        int data2_1 = Integer.parseInt(fenxi.nextToken());
        int data2_2 = Integer.parseInt(fenxi.nextToken());

        FractionCal r1 = new FractionCal(data1_1, data1_2);
        FractionCal r2 = new FractionCal(data2_1, data2_2);

        //生成相应的运算
        String op = operation1[random.nextInt(4)];

        System.out.println("-----" + mark);
        /*System.out.println(op);*/
        while(op == mark && mark!=null){
            op = operation1[random.nextInt(4)];
        }


        FractionCal result;
        int a=0 , b=1 ;
        int flag = 0;
        /**
         * 四种运算
         */
        if (op.equals("+")) {
            result = r1.add(r2);
            a = result.getNumerator();
            b = result.getDenominator();
            /*System.out.println(data1 + " " + operation + " " + data2 + " = " + a + "/" + b);*/
            flag = 1;
        }

        if (op.equals("-")) {
            result = r1.sub(r2);
            a = result.getNumerator();
            b = result.getDenominator();
            /*System.out.println(data1 + " " + operation + " " + data2 + " = " + a + "/" + b);*/
            flag = 1;
        }

        if (op.equals("*")) {
            result = r1.muti(r2);
            a = result.getNumerator();
            b = result.getDenominator();
            /*System.out.println(data1 + " " + operation + " " + data2 + " = " + a + "/" + b);*/
        }

        if (op.equals("÷")) {
            result = r1.div(r2);
            a = result.getNumerator();
            b = result.getDenominator();
            /*System.out.println(data1 + " " + operation + " " + data2 + " = " + a + "/" + b);*/
        }
       /* stringBuffer.append(data1).append(" ").append(op).append(" ").append(data2).append("=").append(" ").append(a).append("/").append(b);*/

        /**
         * 生成目前的结果以及算式的当前样子
         */
        String str = a+"/"+b;
        consequence += op+" "+data2+"";
        if(flag==1){
            consequence = "("+consequence+")";
        }
        list[0] = str;
        list[1] = consequence;
        list[2] = op;

        return list;
    }



}