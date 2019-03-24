import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File file = new File("result.txt");
        if (file.exists()) {
            file.delete();
        }
        Calculate.writeToFile("2017010254\n");
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        for (int i = 0; i < num; i++) {
            TestCal.franction_consquence();

            if (Calculate.generate()) {
                System.out.println("Success");
            }
        }
    }
}
