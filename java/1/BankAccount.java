/**
 * Represents a bank account
 */
public class BankAccount {
    public String name;
    public String password;
    public int accountNumber;
    public double balance;

    public BankAccount() { }

    public BankAccount(String name, String password, int accountNumber, double balance) {
        this.name = name;
        this.password = password;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
}
