package banking.datavalidation;

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

}
