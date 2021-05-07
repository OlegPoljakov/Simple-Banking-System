package banking.database;

import banking.account.Account;

import java.util.ArrayList;

public class Database {

    private static ArrayList<Account> accountsBase = new ArrayList<Account>();

    public Database() {

    }

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
}
