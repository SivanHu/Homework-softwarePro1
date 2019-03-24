import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 测试正则表达式
 */

public class Test {
    public static void main(String[] args) {

        Pattern p = Pattern.compile("\\d+\\.?\\d?");
        Matcher matcher = p.matcher("71");

        System.out.println(matcher.matches());


    }

}
