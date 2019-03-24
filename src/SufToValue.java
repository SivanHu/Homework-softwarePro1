import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SufToValue {
    /***
     * @method: main方法用作检验  检验成功之后我将其注释掉
     * @param args
     */
/*    public static void main(String[] args) {
        try {
            System.out.println(compute("16 8 4 1 * ÷ -"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    /***
     * @param: pattern  正则表达式，用于给定字符串分割的形式
     *  该正则表达式也可以直接地简单表达为  \\d+
     */

    private static Pattern pattern = Pattern.compile("\\d+\\.?\\d?");

    /***
     *
     * @param str
     * @return bool类型  判断str是否为数字的形式的字符
     */
    public static boolean isNum(String str) {
        Matcher matcher = pattern.matcher(str);   //mather 返回的是一个Mather对象
        if (matcher.matches()) {
            return true;
        }
        return false;
    }
    /***
     *
     * @param expression 接受来自perToSuffix（）的return 即结果，即用空格分割的后缀表达式
     * @return 将算数运算的最终结果返回
     * @throws Exception 不能整除 或者 结果为负数时，抛出相应异常
     */
    public static int compute(String expression) throws Exception {
        int numOne, numTwo;
        String temp = "";
        String[] express = expression.split(" ");
        Stack<String> stack = new Stack<>();
        /* for (int i = 0; i < express.length; i++) {
            System.out.println("---->" +express[i]);
        }*/
        for (int i = 0; i < express.length; i++){
            /*System.out.println("ok");
            System.out.println(express[i]);
            System.out.println(isNum(express[i]));*/
            if (isNum(express[i])){//是数字则入栈 {1 4 8 16}
                stack.push(express[i]);
                System.out.println(express[i]);
                 /*String str2 = stack.peek();
                System.out.println(str2);*/
            } else//若遇见运算符，则开始进行相应的计算//
            {
                numTwo = Integer.parseInt(stack.pop());
                numOne = Integer.parseInt(stack.pop());
                temp = compute(numOne, numTwo, express[i]).toString();

                if (Integer.parseInt(temp) < 0){    //负数的情况  抛出异常
                    throw new Exception("Negative numbers in operation");
                }
                if(express[i].equals(Const.Operator.div)){ //做除法运算的时，若不能整除，则抛出异常
                    if (numOne % numTwo != 0){
                        throw new Exception("Counts appear in operations");
                    }
                }
                stack.push(temp); //将每一次的运算结果入栈，以备下一次运算的使用
            }
        }

        return Integer.parseInt(stack.pop());
    }

    /**
     * 设置运算符相对应的运算规则
     * @param a 传入的第一个参数
     * @param b 传入的第二个参数
     * @param op  传入的符号
     * @return 返回运算结果
     */
    public static Integer compute(int a, int b, String op){
        switch(op){
            case Const.Operator.add:
                return a+b;
            case Const.Operator.sub:
                return a-b;
            case Const.Operator.mul:
                return a*b;
            case Const.Operator.div:
                return a/b;
            default: return null;
        }
    }

    /***
     * @method: 限定运算符
     */
    public static class Const {
        public interface Operator{
            String add = "+";
            String div = "÷";
            String sub = "-";
            String mul = "*";
        }
    }
}
