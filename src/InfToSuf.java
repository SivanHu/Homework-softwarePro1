import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/***
 * 做一个这样的中缀转后缀的操作
 * 中缀表达式a + b*c + (d * e + f) * g，
 * 其转换成后缀表达式则为a b c * + d e * f  + g * +。
 */
public class InfToSuf {
    private static Stack<String> stack = new Stack();
    private static String[] op = {"+", "-", "*" ,"÷"};
    private static Map<String, Integer> map = new HashMap<>();
    /***
     * @static 里面的内容，用于定义运算符的优先级
     */
    static{
        map.put("+", 1);
        map.put("-", 1);
        map.put("*", 2);
        map.put("÷", 2);
        map.put("" , -1);
    }
    /***
     *
     * @param str 比较str是否为运算符的方法
     * @return bool类型   返回true
     */
    private static boolean isOp(String str){
        for(int i=0; i < op.length; i++){
            if(op[i].equals(str)){
                return true;
            }
        }
        return false;
    }

    /***
     * @param ：equation如果与栈顶的值不同是且其是运算符，则它就是第一个运算符
     * @return：返回运算符
     */
    private static String getFirstOp(){
        String equation = "";
        for (int i = stack.size()-1; i >= 0 ; i--) {
            equation = stack.get(i);
            if("(".equals(equation)){
                break;
            }else if(isOp(equation)){
                return equation;
            }
        }
        return "";
    }

    /***
     * @method preTosuffix ：将中序字符串转换为后序字符串
     * @param expree 传入的字符串
     * @return 通过StringBuffer存入后缀表达顺序的字符
     * @throws Exception
     */
    public static String preToSuffix(String expree)throws Exception{
        String[] expression = expree.split(",");
        StringBuffer suffixStr = new StringBuffer();
        String temp = "";
        /*for (int i = 0; i < expression.length; i++) {
            System.out.println("--->"+expression[i]);
        }*/
        try{
            for(int i = 0; i < expression.length; i++){
                if(SufToValue.isNum(expression[i])){
                    suffixStr.append(expression[i]).append(" ");  //如果是数字，将数字与空格等依次存入字符串缓存区的过程
                }else if(expression[i].equals(")")){
                    temp = stack.pop();
                    while(!temp.equals("(")){   //如遇到右括号，将栈顶元素依次pop出，直到遇到左括号停止
                        suffixStr.append(temp).append(" ");
                        temp = stack.pop();
                    }
                }else if(expression[i].equals("(")|| map.get(expression[i])>=map.get(getFirstOp())){ //遇到（和后面的运算的优先级更高，那么将前一个元素push进栈，先计算后面
                    stack.push(expression[i]);
                }else{  //输入的末尾则将栈中所有的元素全部输出
                    temp = stack.pop();
                    suffixStr.append(temp).append(" ").append(expression[i]).append(" ");
                }
            }
        }catch (Exception e){
            throw  e;
        }
        while(stack.size() > 0){
            suffixStr.append(stack.pop());
        }
        return suffixStr.toString();
    }

}
