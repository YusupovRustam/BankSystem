import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CardConnectionDB {
    private String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private String dbuser = "System";
    private String password1 = "Rustam!2021";
    public void savaCard(Card card){
        try {
            Connection connection = DriverManager.getConnection(url, dbuser, password1);
            String query = "insert into card5 (USERID,NAME,CARDNUMBER,CARDPAROL,BALANCE,CREATEDATES) values (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,card.getUserId());
            preparedStatement.setString(2,card.getCardName());
            preparedStatement.setLong(3,card.getCardNumber());
            preparedStatement.setInt(4,card.getCardparol());
            preparedStatement.setLong(5,card.getBalance());
            LocalDateTime localDateTime=card.getLocalDateTime();
            DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
            String format = localDateTime.format(dateTimeFormatter);
            preparedStatement.setString(6,format);
            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
            System.out.println("Card create!!!!!!!!!!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public long balance(int parol,int USERID){
        try {
            long balance=0;
            Connection connection = DriverManager.getConnection(url, dbuser, password1);
            String query="select balance from card5 where CARDPAROL=? and USERID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,parol);
            preparedStatement.setInt(2,USERID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                balance=resultSet.getLong("BALANCE");
            }
            preparedStatement.close();
            connection.close();
            return balance;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }
    public long balance(long CARDNUMBER){
        try {
            long balance=0;
            Connection connection = DriverManager.getConnection(url, dbuser, password1);
            Statement statement = connection.createStatement();
            String query="select balance from card5 where CARDNUMBER="+CARDNUMBER;
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()){
                balance=resultSet.getLong("BALANCE");
            }else {
                System.out.println("Qarta topilmadi!!!!!!!!!");
                return -1;
            }
            statement.close();
            connection.close();
            return balance;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }
    public void transaction(int userId,int cardParol ,long to_CARDNUMBER, long moneyValue){
        try {
            Connection connection = DriverManager.getConnection(url, dbuser, password1);
            float service= (float) (moneyValue*Services.Trans_from_card_to_card);
            long fromBalance= (long) (balance(cardParol,userId)-moneyValue-service);
            long toBalance=balance(to_CARDNUMBER)+moneyValue;
            long b=balance(to_CARDNUMBER);
            if(b==-1){
                return;
            }
            String query="update card5 set BALANCE="+fromBalance+" Where USERID="+userId+" and CARDPAROL="+cardParol;
            String query1="update card5 set BALANCE="+toBalance+" Where CARDNUMBER="+to_CARDNUMBER;
            Statement statement = connection.createStatement();
            statement.executeQuery(query);
            statement.executeQuery(query1);
            statement.close();
            connection.close();
            AllTransaction allTransaction=new AllTransaction(userId,moneyValue,to_CARDNUMBER,fromBalance,LocalDateTime.now());
            allTransaction.setService(service);
             saveAllTransaction(allTransaction);
            System.out.println("pul otkazildi!!!!!!!!!!!!!!!!!!!!!!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void saveAllTransaction(AllTransaction allTransaction){
        try {
            Connection connection=DriverManager.getConnection(url,dbuser,password1);
            String query = "insert into ALLTRANSACTION (USERID,MONEYTRANSFERRED,CARDNUMBER,BALANCE,DATES,SERVICE) values (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,allTransaction.getUserId());
            preparedStatement.setLong(2,allTransaction.getMoneyTransferred());
            preparedStatement.setLong(3,allTransaction.getCardNumber());
            preparedStatement.setLong(4,allTransaction.getBalance());
            preparedStatement.setString(5,DateFormat.stringDateNow(allTransaction.getLocalDateTime()));
            preparedStatement.setFloat(6,allTransaction.getService());
            preparedStatement.executeQuery();
            preparedStatement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
    public void history(int userId){
        try {
            Connection connection=DriverManager.getConnection(url,dbuser,password1);
            String query="select MONEYTRANSFERRED,CARDNUMBER,BALANCE,DATES,SERVICE from ALLTRANSACTION where USERID="+userId;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                long MONEYTRANSFERRED = resultSet.getLong(2);
                long CARDNUMBER=resultSet.getLong(3);
                long BALANCE=resultSet.getLong(4);
                String DATES=resultSet.getString(5);
                float SERVICE=resultSet.getFloat(6);
                LocalDateTime localDateTime=LocalDateTime.parse(DATES);
                AllTransaction allTransaction=new AllTransaction(userId,MONEYTRANSFERRED,CARDNUMBER,BALANCE,localDateTime);
                allTransaction.setService(SERVICE);
                System.out.println(allTransaction);
            }
            statement.close();
            connection.close();
        } catch (SQLException throwables) {

        }

    }
}
