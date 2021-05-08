package banking.account;

import banking.database.Database;
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
        createCardNumber();
        createPinCode();
        balance = 0;
    }

    //in a range from 0000 to 9999
    private void createPinCode(){
        Random random = new Random();
        int temppin = random.nextInt(10000);
        pinCode = String.format("%04d", temppin);
    }

    private void createCardNumber(){
        do{
            cardNumber = gererateLuhnCardNumber();
        } while(db.ifCardExist(cardNumber));
    }

    private String gererateLuhnCardNumber (){
        Random random = new Random();
        String tmp = "";
        int сheckSum = 0;
        for (int i = 0; i < 9; i++) {
            tmp = tmp + random.nextInt(10);
        }
        tmp = "400000" + tmp; //15 чисел. Надо найти сумму после применения алгоритма

        boolean isOdd = true;
        int nSum = 0;
        for (int i = 14; i >= 0; i--){
            int d = tmp.charAt(i) - '0';
            if (isOdd == true)
                d = d * 2;
            // We add two digits to handle
            // cases that make two digits
            // after doubling
            nSum += d / 10;
            nSum += d % 10;

            isOdd = !isOdd;
        }
        if (nSum % 10 == 0) {
            сheckSum = 0;
        }
        else {
            сheckSum = (9- (nSum % 10)) + 1;
        }
        tmp = tmp + String.valueOf(сheckSum);
        return tmp;
    }
}


