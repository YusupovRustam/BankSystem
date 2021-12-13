import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class ConnectionJDBC {
    private String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private String dbuser = "System";
    private String password1 = "Rustam!2021";


    public void save(User user) {
        try {
            Connection connection = DriverManager.getConnection(url, dbuser, password1);
            String query = "insert into users (id,name,surname,phone,password) values (users_SEQUENCE.nextval,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setInt(3, user.getPhone());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
            System.out.println("Tabriklaymiz siz ro'hatdan otdingiz!!!");
        } catch (SQLException e) {
            System.out.println("Bazaga saqlanmadi");
        }
    }

    public User getUser(String password) {
        User user = null;
        try {
            Connection connection = DriverManager.getConnection(url, dbuser, password1);
            String query = "select * from users where password='"+password+"'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()){
                int id=resultSet.getInt(1);
            String name = resultSet.getString(2);
            String surname = resultSet.getString(3);
            int phone = resultSet.getInt(4);
                user = new User(id, name, surname, phone, password);
                return user;
            }
            statement.close();
            connection.close();

        } catch (SQLException throwables) {
         throwables.printStackTrace();
        }
        return null;
    }
    public User getUser(int phone) {
        User user = null;
        try {
            Connection connection = DriverManager.getConnection(url, dbuser, password1);
            String query = "select * from users where phone="+phone;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()){
                int id=resultSet.getInt(1);
                String name = resultSet.getString(2);
                String surname = resultSet.getString(3);
                String password2=resultSet.getString(5);
                user = new User(id, name, surname, phone, password2);
                return user;
            }
            statement.close();
            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void saveSms(Sms sms) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, dbuser, password1);
            String query = "insert into sms (password,massage,dates,status) values (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, sms.getUserPassword());
            preparedStatement.setString(2, sms.getMassage());
            DateTimeFormatter dateTimeFormatt = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            String time = sms.getLocalDateTime().format(dateTimeFormatt);
            preparedStatement.setString(3, time);
            preparedStatement.setInt(4, sms.getStatus());
            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

 public List<User>userList(){
        List<User>list=new LinkedList<>();
     try {
         Connection connection=DriverManager.getConnection(url,dbuser,password1);
         String query="select * from users";
         PreparedStatement preparedStatement = connection.prepareStatement(query);
         ResultSet resultSet = preparedStatement.executeQuery();
         while (resultSet.next()){
             int id=resultSet.getInt(1);
             String name=resultSet.getString(2);
             String surname=resultSet.getString(3);
             int phone=resultSet.getInt(4);
             String password=resultSet.getString(5);
             User user=new User(id,name,surname,phone,password);
             list.add(user);
         }
     } catch (SQLException throwables) {
         throwables.printStackTrace();
     }
     return list;

 }
    public  void update(User user) {
        Connection connection=null;
             try {
                 connection=DriverManager.getConnection(url,dbuser,password1);
        Statement statement=connection.createStatement();
        String query="update users set ";
        if (!user.getName().isEmpty()){
            query+="name='"+user.getName()+"',";
        }
        if (!user.getLastname().isEmpty()){
            query+="surname='"+user.getLastname()+"',";
        }
        if (user.getPhone()!=0){
            query+="phone='"+user.getPhone()+"',";
        }
        if (!user.getPassword().isEmpty()){
            query+="password='"+user.getPassword()+"'";
        }
        if (!query.equals("update users set ")){
            if (query.endsWith(",")){
                query=query.substring(0,query.length()-1);
            }
        }
        if(query.equals("update users set ")){
            System.out.println("xato ");
            return;
        }
        query+=" where id="+user.getId();
        statement.execute(query);
        statement.close();
        connection.close();
    }catch (SQLException e){
                 e.printStackTrace();
             }
    }

//USERID
//NAME
//CARDNUMBER
//CARDPAROL
//BALANCE
    //CREATEDATES



}
