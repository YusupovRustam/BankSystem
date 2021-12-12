
import java.time.LocalDateTime;
import java.util.Random;
import java.util.Scanner;

public class BankSystem {
    Scanner scanner = new Scanner(System.in);
    ConnectionJDBC connectionJDBC = new ConnectionJDBC();

    public void start(int i) {
        while (true){
            switch (i){
                case 1:
                   login();
                 int a=Consule.getNumber("1.Davom etish uchun\n2.Asosiy Menu uchun");
                 if (a==2){
                     return;
                 }
                  break;

                case 2:
                    registration();
                    int b=Consule.getNumber("1.Davom etish uchun\n2.Asosiy Menu uchun");
                    if (b==2){
                        return;
                    }
                    break;
                case 3:
                    break;
                case 4:
                    break;
            }
        }

    }

    public void registration() {
        System.out.println("====Registration====");
        do {
            String name = Consule.getmassage("ismingizni kiriting");
            String surname = Consule.getmassage("familyangizni kiriting");
            Integer phone = Consule.getNumber("Telni kiriting");
            String password = Consule.getmassage("Password ni kiriting\nDiqqat!!!\npassword uzunligi 5 bolsin va kamida 1ta son,1ta kichik harf va 1ta katta harf bolsin ");
            String password1 = Consule.getmassage("Password ni tasdiqlang");
            if (password.equals(password1) && Checking.checkPassword(password)) {
                do {
                    LocalDateTime localDateTime = LocalDateTime.now();
                    String s2 = getSms(5);
                    System.out.println(s2);
                    System.out.println("kelgan smsni kiriting");
                    String s1 = scanner.nextLine();
                    LocalDateTime localDateTime1 = LocalDateTime.now();
                    if (s2.equals(s1) && (localDateTime.plusMinutes(1).isBefore(localDateTime1))) {
                        Sms sms1 = new Sms(password, s2, localDateTime, 0);
                        connectionJDBC.saveSms(sms1);
                        continue;
                    }
                    if (!s2.equals(s1)) {
                        continue;
                    }
                    Sms sms = new Sms(password, s2, localDateTime, 1);
                    connectionJDBC.saveSms(sms);
                    break;
                } while (true);
                User user = new User(name, surname, phone, password);
                connectionJDBC.save(user);
                break;
            } else {
                System.out.println("Passworda hato qildingz!!!!");
            }
        } while (true);
    }


    public String getSms(int len) {
        if (len <= 0) {
            return null;
        }
        String symbols = "QWERTYUIPLKJHGFDSAMNBVCXZqwertyupkjhgfdsazxcvbnm123456789";
        String s = "";
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            int n = random.nextInt(symbols.length());
            char c = symbols.charAt(n);
            s += c;
        }
        return s;
    }

    public void login(){
        String password=Consule.getmassage("Passwordni kiriting \n Agar passwordni unutgan bolsangiz Telni kiriting ");
        try {
            int a=Integer.parseInt(password);
            User user=connectionJDBC.getUser(a);
           if (user!=null){
               String newPassword = Consule.getmassage(" yangi Password ni kiriting\nDiqqat!!!\npassword uzunligi 5 bolsin va kamida 1ta son,1ta kichik harf va 1ta katta harf bolsin ");
               String newPassword1 = Consule.getmassage("Password ni tasdiqlang");
               if (newPassword.equals(newPassword1) && Checking.checkPassword(newPassword)) {
                   do {
                       LocalDateTime localDateTime = LocalDateTime.now();
                       String s2 = getSms(5);
                       System.out.println(s2);
                       System.out.println("kelgan smsni kiriting");
                       String s1 = scanner.nextLine();
                       LocalDateTime localDateTime1 = LocalDateTime.now();
                       if (s2.equals(s1) && (localDateTime.plusMinutes(1).isBefore(localDateTime1))) {
                           Sms sms1 = new Sms(password, s2, localDateTime, 0);
                           connectionJDBC.saveSms(sms1);
                           continue;
                       }
                       if (!s2.equals(s1)) {
                           continue;
                       }
                       Sms sms = new Sms(newPassword, s2, localDateTime, 1);
                       connectionJDBC.saveSms(sms);
                       user.setPassword(newPassword);
                       connectionJDBC.update(user);
                       break;
                   } while (true);
               } else {
                   System.out.println("Password yoki telni hato qildingz!!!!");
               }
               }

        }catch (RuntimeException e){
               User user1=connectionJDBC.getUser(password);
               if (user1!=null){
                   cardMenu();
                   int i=Consule.getNumber("Biror raqamni tanlang");

               }

        }
    }

    public void cardMenu(){
        System.out.println("1.Create card");
        System.out.println("2.Card balane");
        System.out.println("3.Transaction");
        System.out.println("4.Tolovlar");
        System.out.println("5.exit");
    }
}


