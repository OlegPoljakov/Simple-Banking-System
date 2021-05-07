package banking.logincabinet.dataonscreen;

import banking.Main;
import banking.account.Account;
import banking.database.Database;
import banking.datavalidation.InputDataValidation;
import banking.logincabinet.Window;

import java.util.Scanner;

public class LogingWindow implements Window {

    public LogingWindow() {
    }

    @Override
    public void ShowDialog() {

        Scanner sc = new Scanner(System.in);
        Database db = new Database();
        InputDataValidation validation = new InputDataValidation();

        outerloop:
        while(true) {
            System.out.println("1. Create an account");
            System.out.println("2. Log into account");
            System.out.println("0. Exit");

            int option = sc.nextInt();

            switch (option) {
                case 1:
                    //Создаем аккаунт - в нем пин код и баланс = 0
                    Account account = new Account();
                    account.createCard();
                    //Если такая карта уже есть в бд, пересоздаем, пока не создадим отстутствующую
                    //while (db.ifCardExist(account.getCardNumber())) {
                    //    account = new Account();
                    //}
                    //Добавляем карту в бд
                    db.addAccount(account);
                    System.out.println("Your card has been created");
                    System.out.println("Your card number:");
                    System.out.println(account.getCardNumber());
                    System.out.println("Your card PIN:");
                    System.out.println(account.getPinCode());
                    break;

                case 2:
                    boolean isOk = false;
                    System.out.println("Enter your card number:");
                    String inputCardNumber = sc.next();
                    System.out.println("Enter your PIN:");
                    String inputCardPin = sc.next();

                    if (validation.isValidPinCode(inputCardPin) && validation.isValidCardNumber(inputCardNumber)) {
                        if(db.ifCardExist(inputCardNumber)){
                            if (db.getPinByCardNumber(inputCardNumber).equals(inputCardPin))
                                isOk = true;
                        }
                    }
                    if (isOk) {
                        System.out.println("You have successfully logged in!");
                        Main.enteredaccount = db.getAccountByCardNumber(inputCardNumber);
                        Main.logged = true;
                        break outerloop;
                    }
                    else {
                        System.out.println("Wrong card number or PIN!");
                    }
                    break;

                case 0:
                    System.exit(0);

            }
        }
    }
}
