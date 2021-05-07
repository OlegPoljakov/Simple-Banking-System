package banking;

import banking.account.Account;
import banking.logincabinet.Window;
import banking.logincabinet.dataonscreen.WindowFactory;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    //private static ArrayList<Account> accountsBase = new ArrayList<Account>();
    public static boolean logged = false;
    public static Account enteredaccount;

    public static void main(String[] args) {

        //Создаем окно кабинета вход\уже вошел
        WindowFactory winFactory = new WindowFactory();

        while(true){
            //Выбираем, какое окно будем выводить - окно для входа, или окно, когда уже вошел
            Window view = winFactory.getCabinet(logged);
            //Выводим выбранное окно
            view.ShowDialog();
        }
    }
}