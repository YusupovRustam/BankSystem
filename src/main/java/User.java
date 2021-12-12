public class User {
    private Integer id;
    private String name;
    private String lastname;
    private int phone;
    private String password;

    public User(Integer id, String name, String lastname, int phone, String password) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.phone = phone;
        this.password = password;
    }

    public User(String name, String lastname, int phone, String password) {
        this.name = name;
        this.lastname = lastname;
        this.phone = phone;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;

    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phone=" + phone +
                ", password='" + password + '\'' +
                '}';
    }
}
