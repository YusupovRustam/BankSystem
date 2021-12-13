
import java.time.LocalDateTime;
import java.util.Random;
import java.util.Scanner;

public class BankSystem {
    Scanner scanner = new Scanner(System.in);
    ConnectionJDBC connectionJDBC = new ConnectionJDBC();
    CardConnectionDB cardConnectionDB=new CardConnectionDB();

    public void start(int i) {
        while (true){
            switch (i){
                case 1:
                   login();
                 int a= Console.getNumber("1.Davom etish uchun\n2.Asosiy Menu uchun");
                 if (a==2){
                     return;
                 }
                  break;

                case 2:
                    registration();
                    int b= Console.getNumber("1.Davom etish uchun\n2.Asosiy Menu uchun");
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
            String name = Console.getmassage("ismingizni kiriting");
            String surname = Console.getmassage("familyangizni kiriting");
            Integer phone = Console.getNumber("Telni kiriting");
            String password = Console.getmassage("Password ni kiriting\nDiqqat!!!\npassword uzunligi 5 bolsin va kamida 1ta son,1ta kichik harf va 1ta katta harf bolsin ");
            String password1 = Console.getmassage("Password ni tasdiqlang");
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
        String password= Console.getmassage("Passwordni kiriting \n Agar passwordni unutgan bolsangiz Telni kiriting ");
        try {
            int a=Integer.parseInt(password);
            User user=connectionJDBC.getUser(a);
           if (user!=null){
               String newPassword = Console.getmassage(" yangi Password ni kiriting\nDiqqat!!!\npassword uzunligi 5 bolsin va kamida 1ta son,1ta kichik harf va 1ta katta harf bolsin ");
               String newPassword1 = Console.getmassage("Password ni tasdiqlang");
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
                   do{
                   cardMenu();
                   int i= Console.getNumber("Biror raqamni tanlang");
                   switch (i){
                       case 1:
                           createCard(user1.getId());
                           break;
                       case 2:
                           int parol= Console.getNumber("Enter card parol");
                           long balance = cardConnectionDB.balance(parol,user1.getId());
                           System.out.println(balance);
                           break;
                       case 3:
                           int parol1= Console.getNumber("Enter card parol");
                           long cardraqam= Console.getLong("Card raqamni kiriting");
                           long money= Console.getLong("Enter money");
                           cardConnectionDB.transaction(user1.getId(),parol1,cardraqam,money);
                           break;
                       case 4:
                           pay();
                           break;
                       case 5:
                           int parol2=Console.getNumber("Card Parolni kiriting");
                           if (parol2== user1.getId()){
                               cardConnectionDB.history(parol2);
                           }else {
                               System.out.println("parol xato!!!!!!!!!!!!");
                           }
                           break;
                       case 6:
                          return;
                   }}while (true);

               }

        }
    }

    public void cardMenu(){
        System.out.println("1.Create card");
        System.out.println("2.Card balane");
        System.out.println("3.Transaction");
        System.out.println("4.Tolovlar");
        System.out.println("5.history");
        System.out.println("6.exit");
    }

    public void createCard(int userId){
        System.out.println("===Card Create===");
        String name= Console.getmassage("Enter card name");
        long cardnumber= Console.getLong("enter card number");
        int cardparol= Console.getNumber("enter card parol");
        long cardBalans= Console.getLong("enter card balans");
        Card card=new Card(userId,name,cardnumber,cardparol,cardBalans);
        LocalDateTime localDateTime=LocalDateTime.now();
        card.setLocalDateTime(localDateTime);
        cardConnectionDB.savaCard(card);

    }
    public void pay(){
         while (true){
             payMenu();
              int i= Console.getNumber("biror raqamni tanlang!!!");
              switch (i){
                  case 1:
                      System.out.println("beeline");
                      break;
                  case 2:
                      System.out.println("Ucell");
                      break;
                  case 3:
                      return;
              }
         }
    }

    public void payMenu(){
        //Beeline,Ucel,,,UzMobile,UzOnline,Turon,Sarkor;
        System.out.println("1.Beeline");
        System.out.println("2.Ucell");
    }
}


