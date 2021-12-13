import java.time.LocalDateTime;

public class AllTransaction {
    //USERID
    //MONEYTRANSFERRED
    //CARDNUMBER
    //BALANCE
    //DATES
    private int userId;
    private long moneyTransferred;
    private long cardNumber;
    private long balance;
    private LocalDateTime localDateTime;
    private float service;

    public AllTransaction(int userId, long moneyTransferred, long cardNumber, long balance, LocalDateTime localDateTime) {
        this.userId = userId;
        this.moneyTransferred = moneyTransferred;
        this.cardNumber = cardNumber;
        this.balance = balance;
        this.localDateTime = localDateTime;
    }

    public float getService() {
        return service;
    }

    public void setService(float service) {
        this.service = service;
    }

    public int getUserId() {
        return userId;
    }

    public AllTransaction setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public long getMoneyTransferred() {
        return moneyTransferred;
    }

    public AllTransaction setMoneyTransferred(long moneyTransferred) {
        this.moneyTransferred = moneyTransferred;
        return this;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public AllTransaction setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public long getBalance() {
        return balance;
    }

    public AllTransaction setBalance(long balance) {
        this.balance = balance;
        return this;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public AllTransaction setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
        return this;
    }

    @Override
    public String toString() {
        return "AllTransaction{" +
                "userId=" + userId +
                ", moneyTransferred=" + moneyTransferred +
                ", cardNumber=" + cardNumber +
                ", balance=" + balance +
                ", localDateTime=" + localDateTime +
                ", service=" + service +
                '}';
    }
}
