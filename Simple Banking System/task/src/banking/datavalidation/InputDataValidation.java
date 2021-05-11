package banking.datavalidation;

import banking.account.Account;
import banking.database.Database;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputDataValidation {

    public InputDataValidation() {
    }

    public boolean isValidPinCode(String pinCode)
    {

        // Regex to check valid pin code of India.
        String regex
                = "^[0-9]{4}$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the pin code is empty
        // return false
        if (pinCode == null) {
            return false;
        }

        // Pattern class contains matcher() method
        // to find matching between given pin code
        // and regular expression.
        Matcher m = p.matcher(pinCode);

        // Return if the pin code
        // matched the ReGex
        return m.matches();
    }

    public boolean isValidCardNumber(String cardNumber)
    {

        // Regex to check valid pin code of India.
        String regex
                = "^[0-9]{16}$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the pin code is empty
        // return false
        if (cardNumber == null) {
            return false;
        }

        // Pattern class contains matcher() method
        // to find matching between given pin code
        // and regular expression.
        Matcher m = p.matcher(cardNumber);

        // Return if the pin code
        // matched the ReGex
        return m.matches();
    }

    public static boolean checkLuhn(String ccNumber)
    {
        int sum = 0;
        boolean alternate = false;
        for (int i = ccNumber.length() - 1; i >= 0; i--)
        {
            int n = Integer.parseInt(ccNumber.substring(i, i + 1));
            if (alternate)
            {
                n *= 2;
                if (n > 9)
                {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }

    public static String transValid(String toacc, Account fromacc){
        String output = "";
        Database db = new Database();
        if (toacc.equals(fromacc.getCardNumber())){
            output = "You can't transfer money to the same account!";
        }
        else if (!checkLuhn(toacc)){ //Если проходит проверку, идем дальше - если нет, заходим внутрь
            output = "Probably you made a mistake in the card number. Please try again!";
        }
        else if (!db.ifCardExistSQlite(toacc)){
            output = "Such a card does not exist";
        }
        return output;
    }
}
