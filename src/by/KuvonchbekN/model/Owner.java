package by.KuvonchbekN.model;

public class Owner {
    private double balance;
    private double commission;

    {
        this.commission = 0.1;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }
}
