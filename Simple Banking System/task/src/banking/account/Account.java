package banking.account;

import banking.database.Database;

import java.util.Arrays;
import java.util.Random;

public class Account {

    private String pinCode;
    private String cardNumber;
    private int balance;
    Database db;

    public Account() {
        db = new Database();
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getPinCode() {
        return pinCode;
    }

    public String getCardNumber() {
        return cardNumber;
    }


    public void createCard(){
        createPinCode();
        createCardNumber();
        balance = 0;
    }

    //in a range from 0000 to 9999
    private void createPinCode(){
        Random random = new Random();
        int temppin = random.nextInt(10000);
        pinCode = String.format("%04d", temppin);
    }

    private void createCardNumber(){
        Random random = new Random();

        do {
            String tmp = "";
            for (int i = 0; i < 10; i++) {
                tmp = tmp + random.nextInt(10);
            }
            cardNumber = "400000" + tmp;

        } while (db.ifCardExist(cardNumber));
    }
}

