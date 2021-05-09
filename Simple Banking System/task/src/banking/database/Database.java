package banking.database;

import banking.account.Account;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;

public class Database {

    private static ArrayList<Account> accountsBase = new ArrayList<Account>();
    private static String url;


    public Database() { }
    public Database(String url) {
        this.url = url;
    }

    /*
    public boolean ifCardExist(String cardNumber){
        boolean exist = false;

        for (int i = 0; i < accountsBase.size(); i++){
            if(cardNumber.equals(accountsBase.get(i).getCardNumber())){
                exist = true;
            }
        }
        return exist;
    }
    public void addAccount(Account acc) {
        accountsBase.add(acc);
    }
    public String getPinByCardNumber(String crdNum){
        String tmp = null;
        for (int i = 0; i < accountsBase.size(); i++){
            if(crdNum.equals(accountsBase.get(i).getCardNumber())){
                tmp = accountsBase.get(i).getPinCode();
            }
        }
        return tmp;
    }
    public Account getAccountByCardNumber(String cardNumber) {
        Account output = null;
        for (int i = 0; i < accountsBase.size(); i++){
            if(cardNumber.equals(accountsBase.get(i).getCardNumber())){
                output = accountsBase.get(i);
            }
        }
        return output;
    }
    */

    public void createDBSQlite() {
        //String url = "jdbc:sqlite:D:\\Java\\HyperSkill Projects\\Simple Banking System\\Simple-Banking-System\\Simple Banking System\\task\\src\\banking\\TEST.db";

        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);

        try (Connection con = dataSource.getConnection()) {
            // Statement creation
            try (Statement statement = con.createStatement()) {
                // Statement execution
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS card(" +
                        "id INTEGER PRIMARY KEY," +
                        "number TEXT," +
                        "pin TEXT," +
                        "balance INTEGER DEFAULT 0)");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveToDBSQlite(Account account){

        String insert = "INSERT INTO card (number, pin, balance) VALUES (?, ?, ?)";

        //String url = "jdbc:sqlite:D:\\Java\\HyperSkill Projects\\Simple Banking System\\Simple-Banking-System\\Simple Banking System\\task\\src\\banking\\TEST.db";
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);

        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement preparedStatement = con.prepareStatement(insert)) {
                preparedStatement.setString(1, account.getCardNumber());
                preparedStatement.setString(2, account.getPinCode());
                preparedStatement.setInt(3, account.getBalance());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Account getAccountByCardNumberSQlite(String cardNumber) {
        Account output = new Account();

        String get = "SELECT pin, balance FROM card WHERE number = ?";

        //String url = "jdbc:sqlite:D:\\Java\\HyperSkill Projects\\Simple Banking System\\Simple-Banking-System\\Simple Banking System\\task\\src\\banking\\TEST.db";
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);

        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement preparedStatement = con.prepareStatement(get)) {

                preparedStatement.setString(1, cardNumber);
                ResultSet resultSet = preparedStatement.executeQuery();

                //output.setCardNumber(resultSet.getString("number"));
                output.setCardNumber(cardNumber);
                output.setPinCode(resultSet.getString("pin"));
                output.setBalance(resultSet.getInt("balance"));

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return output;
    }

    public String getPinByCardNumberSQlite(String cardNumber){

        String tmp = null;

        String get = "SELECT pin FROM card WHERE number = ?";

        //String url = "jdbc:sqlite:D:\\Java\\HyperSkill Projects\\Simple Banking System\\Simple-Banking-System\\Simple Banking System\\task\\src\\banking\\TEST.db";
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);

        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement preparedStatement = con.prepareStatement(get)) {

                preparedStatement.setString(1, cardNumber);
                ResultSet resultSet = preparedStatement.executeQuery();

                tmp = resultSet.getString("pin");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tmp;
    }

    public boolean ifCardExistSQlite(String cardNumber){
        boolean exist = false;

        String get = "SELECT pin FROM card WHERE number = ?";

        //String url = "jdbc:sqlite:D:\\Java\\HyperSkill Projects\\Simple Banking System\\Simple-Banking-System\\Simple Banking System\\task\\src\\banking\\TEST.db";
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);

        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement preparedStatement = con.prepareStatement(get)) {

                preparedStatement.setString(1, cardNumber);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next() != false) {
                    exist = true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exist;
    }

    public int getBalanceSQlite(Account account){
        int balance = 0;

        String get = "SELECT balance FROM card WHERE number = ?";

        //String url = "jdbc:sqlite:D:\\Java\\HyperSkill Projects\\Simple Banking System\\Simple-Banking-System\\Simple Banking System\\task\\src\\banking\\TEST.db";
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);

        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement preparedStatement = con.prepareStatement(get)) {

                preparedStatement.setString(1, account.getCardNumber());
                ResultSet resultSet = preparedStatement.executeQuery();

                balance = resultSet.getInt("balance");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return balance;
    }
}
