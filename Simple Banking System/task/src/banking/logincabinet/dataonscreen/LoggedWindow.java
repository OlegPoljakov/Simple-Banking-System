package banking.logincabinet.dataonscreen;

import banking.Main;
import banking.database.Database;
import banking.logincabinet.Window;

import java.util.Scanner;

public class LoggedWindow implements Window {

    public LoggedWindow() {
    }

    @Override
    public void ShowDialog() {

        Scanner sc = new Scanner(System.in);
        Database db = new Database();

        outerloop:
        while(true) {
            System.out.println("1. Balance");
            System.out.println("2. Log out");
            System.out.println("0. Exit");

            int option = sc.nextInt();

            switch (option) {

                case 1:
                    System.out.println(Main.enteredaccount.getBalance());
                    break;
                case 2:
                    Main.logged = false;
                    Main.enteredaccount = null;
                    break outerloop;
                case 0:
                    System.exit(0);
            }
        }
    }
}
