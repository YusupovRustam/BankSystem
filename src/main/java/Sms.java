import java.time.LocalDateTime;

public class Sms {

    private String password;
    private String massage;
    private LocalDateTime localDateTime;
    private int status;

    public Sms(String password, String massage, LocalDateTime localDateTime, int status) {
        this.password = password;
        this.massage = massage;
        this.localDateTime = localDateTime;
        this.status = status;
    }

    public String getUserPassword() {
        return password;
    }

    public void setUserPassword(String password) {
        this.password = password;

    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;

    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
