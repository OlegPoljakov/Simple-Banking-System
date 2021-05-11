package banking;

import banking.account.Account;
import banking.database.Database;
import banking.logincabinet.Window;
import banking.logincabinet.dataonscreen.WindowFactory;

public class Main {

    public static boolean logged = false;
    public static Account enteredaccount;

    public static void main(String[] args) {

        String url = "jdbc:sqlite:" + args[1];
        //String url = "jdbc:sqlite:D:\\Java\\HyperSkill Projects\\Simple Banking System\\Simple-Banking-System\\Simple Banking System\\task\\src\\banking\\TEST.db";
        Database database = new Database(url);
        //Открыте подключеня, создание бд, если не создана, закрытие соединения
        database.createDBSQlite();

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