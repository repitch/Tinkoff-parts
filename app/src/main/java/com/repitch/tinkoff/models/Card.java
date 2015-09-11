package com.repitch.tinkoff.models;

/**
 * Created by repitch on 10.09.15.
 */
public class Card {

    public static final int TYPE_MASTERCARD = 0;
    public static final int TYPE_VISA = 1;

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCashLimit() {
        return cashLimit;
    }

    public void setCashLimit(int cashLimit) {
        this.cashLimit = cashLimit;
    }

    public int getCashBalance() {
        return cashBalance;
    }

    public void setCashBalance(int cashBalance) {
        this.cashBalance = cashBalance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    private String cardNumber;
    private String fullname;
    private int type;
    private int cashLimit;
    private int cashBalance;
    private boolean blocked = false;

    public Card(String cardNumber,
                String fullname,
                int type,
                int cashLimit,
                int cashBalance,
                boolean blocked){
        this.cardNumber = cardNumber;
        this.fullname = fullname;
        this.type = type;
        this.cashLimit = cashLimit;
        this.cashBalance = cashBalance;
        this.blocked = blocked;
    }




}
