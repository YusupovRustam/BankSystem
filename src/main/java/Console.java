import java.util.InputMismatchException;
import java.util.Scanner;

public class Console {


    public static String getmassage(String massage) {
        System.out.println(massage);
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        return s;
    }

    public static Integer getNumber(String massage) {
        int i = -1;
        do {
            System.out.println(massage);
            Scanner scanner = new Scanner(System.in);
            try {
                i = scanner.nextInt();
                return i;
            } catch (InputMismatchException e) {
                System.out.println("raqam kiriting");
                continue;
            }

        } while (true);

    }
    public static long getLong(String massage) {
        long i = -1;
        do {
            System.out.println(massage);
            Scanner scanner = new Scanner(System.in);
            try {
                i = scanner.nextLong();
                return i;
            } catch (InputMismatchException e) {
                System.out.println("raqam kiriting");
                continue;
            }

        } while (true);

    }
}
