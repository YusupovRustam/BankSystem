import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        //2021-12-11

        Scanner scanner = new Scanner(System.in);
        BankSystem bankSystem = new BankSystem();
        while (true) {
            menu();
            int i = scanner.nextInt();
            switch (i) {
                case 1:
                    break;
                case 2:
                    bankSystem.start();
                    break;
                case 3:
                    break;
                case 0:
                    break;

            }
        }
    }


    public static void menu() {
        System.out.println("====Menu====");
        System.out.println("1.Login");
        System.out.println("2.Registration");
        System.out.println("3.Currency");
        System.out.println("0.exit");
    }

}


