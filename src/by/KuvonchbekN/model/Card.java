package by.KuvonchbekN.model;

import java.util.UUID;

public class Card extends BaseModel {
    private UUID ownerId;
    private String cardNumber;
    private double balance;
    private boolean isMain;

    {
        this.balance = 100d;
    }

    public Card(String name, UUID ownerId, String cardNumber, boolean isMain) {
        super(name);
        this.ownerId = ownerId;
        this.cardNumber = cardNumber;
        this.isMain = isMain;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isMain() {
        return isMain;
    }

    public void setMain(boolean main) {
        isMain = main;
    }
}
