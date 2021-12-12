import java.sql.*;
import java.time.format.DateTimeFormatter;

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
            Connection connection = DriverManager.getConnection(url, dbuser, password);
            String query = "select * from users where password=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String surname = resultSet.getString(3);
            int phone = resultSet.getInt(4);
            user = new User(id, name, surname, phone, password);
        } catch (SQLException throwables) {
            return null;
        }
        return user;
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

}
