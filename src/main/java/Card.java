import java.time.LocalDateTime;

public class Card {
    private int userId;
private String cardName;
private long cardNumber;
private int cardparol;
private long balance;
private LocalDateTime localDateTime;

    public Card(int userId, String cardName, long cardNumber, int cardparol, long balance) {
        this.userId = userId;
        this.cardName = cardName;
        this.cardNumber = cardNumber;
        this.cardparol = cardparol;
        this.balance = balance;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public Card setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
        return this;
    }

    public int getUserId() {
        return userId;
    }

    public Card setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public String getCardName() {
        return cardName;
    }

    public Card setCardName(String cardName) {
        this.cardName = cardName;
        return this;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public Card setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public int getCardparol() {
        return cardparol;
    }

    public Card setCardparol(int cardparol) {
        this.cardparol = cardparol;
        return this;
    }

    public long getBalance() {
        return balance;
    }

    public Card setBalance(long balance) {
        this.balance = balance;
        return this;
    }
}
