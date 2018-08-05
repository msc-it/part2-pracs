import java.util.*;

public class Bank {

    // A list of bank accounts
    // accessible by account number
    private HashMap<Integer, BankAccount> accounts = new HashMap<>();

    public Bank(BankAccount[] accounts) {
        for (BankAccount acc : accounts) {
            this.accounts.put(acc.accountNumber, acc);
        }
    }

    /**
     * Returns the BankAccount associated with the account number
     * Returns null if doesn't exist
     */
    public BankAccount getAccount(int accountNumber) {
        return accounts.get(accountNumber);
    }

    /**
     * Returns true if success, else returns false
     */
    public boolean authenticate(int accountNumber, String password) {
        // Fetch the account using the account number
        BankAccount acc = getAccount(accountNumber);

        // Check if the account exists
        if(acc != null) {

            // Check if the password is correct
            if(acc.password.equals(password)) {

                // All good, return true to indicate success
                return true;
            }
        }

        // Fail.
        return false;
    }
}
