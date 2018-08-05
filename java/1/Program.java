import java.io.*;
import java.util.*;

public class Program {

    public static void main(String[] args) {
        // Seed the bank with test accounts
        BankAccount[] accounts = {
            new BankAccount("Galdin", "gprpwd", 12345, 1000),
            new BankAccount("Blossom", "bpcpwd", 22345, 1000),
            new BankAccount("Jackie", "wertw", 32345, 1000),
            new BankAccount("Anuja", "dfghd", 42345, 1000),
        };

        MoneyBank bank = new MoneyBank(accounts);
        Scanner scanner = new Scanner(System.in);
        
        // Get username and password
        System.out.print("Enter account number: ");
        int acc = getIntegerInput(scanner);
        System.out.print("Enter password: ");
        // String pass = scanner.nextLine(); // uncomment this if you're using an IDE
        String pass = new String(System.console().readPassword());
        
        // authenticate the user
        if(!bank.authenticate(acc, pass)) {
            // Authentication failed, exit the program
            System.err.println("⚠️ Authentication failed.");
            return;
        }

        // Authentication was successful.
        // Welcome the user.
        System.out.println("Welcome to Java Bank 🙋");

        // Run the program loop
        boolean shouldContinue = true;
        do {
            // Get a valid menu option from the user
            int option = getOption(scanner);
            switch(option) { // and execute the corresponding method
                case 1: { // deposit
                    System.out.print("▶︎ Enter deposit amount: ");
                    int amount = getIntegerInput(scanner);
                    bank.moneyDeposit(amount, acc);
                }
                break;
                case 2: { // withdraw
                    System.out.print("▶︎ Enter withdraw amount: ");
                    int amount = getIntegerInput(scanner);
                    bank.moneyWithdraw(amount, acc);
                }
                break;
                case 3: // balance
                    bank.enquireBalance(acc);
                break;
                case 4: // quit
                    shouldContinue = false;
                break;
            }
        } while(shouldContinue);
        
        // Exit prompt
        System.out.println("Thank you for using Java Bank. Until next time ✌️");
    }

    // Gets a valid integer input from the user
    private static int getIntegerInput(Scanner scanner) {
        try{
            return Integer.parseInt(scanner.nextLine());
        }
        catch (NumberFormatException ex) {
            System.err.print("❌ Enter a valid number: ");
            return getIntegerInput(scanner);
        }
    }

    // Shows the menu and returns a valid option from the user
    private static int getOption(Scanner scanner) {
        // Print the menu
        System.out.println("------------------");
        System.out.println("1. Deposit Amount");
        System.out.println("2. Withdraw Amount");
        System.out.println("3. Enquire Amount");
        System.out.println("4. Exit");
        System.out.print("▶︎ Your option: ");

        // Get user input
        int option = getIntegerInput(scanner);

        // Validate the input
        if(option >= 1 && option <= 4) {
            // Valid input: return whatever the user provided
            return option;
        }

        // Invalid input: show a prompt and execute this method again
        System.out.println("❌ Invalid Option. Try again.");
        return getOption(scanner);
    }
}
