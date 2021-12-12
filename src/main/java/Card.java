public class Card {
    private int userId;
private String cardName;
private long cardNumber;
private int cardparol;
private long balance;

    public Card(int userId, String cardName, long cardNumber, int cardparol, long balance) {
        this.userId = userId;
        this.cardName = cardName;
        this.cardNumber = cardNumber;
        this.cardparol = cardparol;
        this.balance = balance;
    }
}
