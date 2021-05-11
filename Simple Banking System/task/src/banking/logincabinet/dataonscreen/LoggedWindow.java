package banking.logincabinet.dataonscreen;

import banking.Main;
import banking.database.Database;
import banking.datavalidation.InputDataValidation;
import banking.logincabinet.Window;

import java.util.Scanner;

public class LoggedWindow implements Window {

    public LoggedWindow() {
    }

    @Override
    public void ShowDialog() {

        Scanner sc = new Scanner(System.in);
        Database db = new Database();
        int sum;

        outerloop:
        while(true) {

            System.out.println("1. Balance");
            System.out.println("2. Add");
            System.out.println("3. Do transfer");
            System.out.println("4. Close account");
            System.out.println("5. Log out");
            System.out.println("0. Exit");

            int option = sc.nextInt();

            switch (option) {

                case 1:
                    System.out.println("Balance: " + db.getBalanceSQlite(Main.enteredaccount));
                    break;
                case 2:
                    System.out.println("Enter income:");
                    sum = sc.nextInt();
                    db.updateBalanceSQlite(sum, Main.enteredaccount);
                    System.out.println("Income was added!");
                    break;
                case 3:
                    String error = "";
                    System.out.println("Transfer");
                    System.out.println("Enter card number:");
                    String inputCardNumber = sc.next();
                    error = InputDataValidation.transValid(inputCardNumber, Main.enteredaccount);
                    if(error.isEmpty()){
                        System.out.println("Enter how much money you want to transfer:");
                        sum = sc.nextInt();
                        if (db.getBalanceSQlite(Main.enteredaccount) >= sum ){
                            db.transferMoney(sum, inputCardNumber, Main.enteredaccount);
                            System.out.println("Success!");
                        }
                        else {
                            System.out.println("Not enough money!");
                        }
                    }
                    else {
                        System.out.println(error);
                    }
                    break;


                case 4:
                    db.closeAccountSQlite(Main.enteredaccount);
                    Main.enteredaccount = null;
                    Main.logged = false;
                    System.out.println("The account has been closed!");
                    break outerloop;
                case 5:
                    Main.logged = false;
                    Main.enteredaccount = null;
                    break outerloop;
                case 0:
                    System.out.println("Bye!");
                    System.exit(0);
            }
        }
    }
}
