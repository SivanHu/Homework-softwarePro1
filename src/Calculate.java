import java.io.FileWriter;
import java.util.Random;

/***
 * @param: a,b,c,d  运算中的运算数
 * @param: result: 计算结果
 * @param : OPERATE一个存放四则运算的数组
 * @method:  generate: 生成输入的随机数以及生成相应的结果
 */
public class Calculate {
    private static int a, b, c , d, result;
    private static String firOp, secOp, thiOp;
    private final static String[] OPTERATE = {"+","-","*","÷"};
    public static Random random = new Random();

    /***
     * @method generate: 生成随机数以及运算符， 得到相应的结果并将最终的算式写入文件
     * @return true: 操作成功  false: 操作失败
     */
    public static boolean generate(){
        while(true){
            /***
             * 产生四个随机数
             */
            a = random.nextInt(100);
            b = random.nextInt(100);
            c = random.nextInt(100);
            d = random.nextInt(100);

            firOp = OPTERATE[random.nextInt(4)];
            secOp = OPTERATE[random.nextInt(4)];
            thiOp = OPTERATE[random.nextInt(4)];


            //为保证运算中至少有两个不同运算  则需要做以下判断
            if(firOp.equals(secOp)&& firOp.equals(thiOp)){
                continue;
            }
            String infixExpression = Combination(firOp, secOp, thiOp, a, b, c, d);

            try{
                //调用方法得到最终结果
                result = SufToValue.compute(InfToSuf.preToSuffix(infixExpression));
            }catch (Exception e){
                continue;
            }
            if(result>0){
                StringBuffer buffer = new StringBuffer();
                String[] str = infixExpression.split(",");
                for(int i=0; i<str.length; i++){
                    buffer.append(str[i]);
                }
                //把算式组合到一起
                buffer.append(" = ").append(result).append("\n");
                if(writeToFile(buffer.toString())){
                    return true;
                }else return false;
            }
        }
    }

    /***
     * @class TheFormOfCal
     * @method Combination: 将传入的运算符和运算值用“，”分隔开运算符和运算值的中缀字符串
     *
     */


    public static String Combination(String friOp, String secOp, String thrOp, int... numArgs){
        int temp = random.nextInt(5);
        StringBuffer buffer = new StringBuffer();

        switch (temp) {
            case 0: {
                buffer.append(numArgs[0]).append(",").append(friOp).append(",").append("(").append("(").append(",").append(numArgs[1]).append(",").append(secOp).append(",").append(numArgs[2]).append(",")
                        .append(")").append(",").append(thrOp).append(",").append(numArgs[3]).append(",").append(")");
                break;
            }
            case 1: {
                buffer.append("(").append(",").append(numArgs[0]).append(",").append(friOp).append(",").append("(").append(",").append(numArgs[1]).append(",").append(secOp).append(",")
                        .append(numArgs[2]).append(",").append(")").append(")").append(",").append(thrOp).append(",").append(numArgs[3]);
                break;
            }
            case 2: {
                buffer.append(numArgs[0]).append(",").append(friOp).append(",").append("(").append(",").append(numArgs[1]).append(",").append(secOp).append(",").append("(").append(",").append(numArgs[2]).append(",")
                        .append(thrOp).append(",").append(numArgs[3]).append(",").append(")").append(",").append(")");
                break;
            }
            case 3: {
                buffer.append("(").append(",").append(numArgs[0]).append(",").append(friOp).append(",").append(numArgs[1]).append(",").append(")").append(",").append(secOp).append(",").append("(").append(",")
                        .append(numArgs[2]).append(",").append(thrOp).append(",").append(numArgs[3]).append(",").append(")");
                break;
            }
            case 4:{
                buffer.append(numArgs[0]).append(",").append(firOp).append(",").append(numArgs[1]).append(",").append(secOp).append(",").append(numArgs[2]).append(",").append(thrOp).append(",").append(numArgs[3]);
            }
        }
        return buffer.toString();

    }



    /***
     * @param result 结果值
     * @return 写入文件操作成功
     * @purpose: 创建文件，并写入结果
     */
    public static boolean writeToFile(String result){
        try{
            FileWriter writer = new FileWriter("result.txt",true);
            writer.write(result);
            writer.flush();
            writer.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
