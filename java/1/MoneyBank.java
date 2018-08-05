public class MoneyBank extends Bank {

    public MoneyBank(BankAccount[] accounts) {
        super(accounts);
    }

    public void moneyDeposit(double amount, int accountNumber) {
        // Validate amount, exit method if validation fails
        if(amount < 100) {
            System.err.println("❌  You must deposit atleast 100.");
            return;
        }

        BankAccount acc = getAccount(accountNumber);
        if(acc != null) {
            acc.balance += amount;
            System.out.println("✅  Your new balance is: " + acc.balance);
        }
    }

    public void moneyWithdraw(double amount, int accountNumber) {
        // Validate amount, exit method if validation fails
        if(amount < 100) {
            System.err.println("❌  You must withdraw atleast 100.");
            return;
        }

        BankAccount acc = getAccount(accountNumber);
        if(acc != null) {
            if(acc.balance >= amount) {
                acc.balance -= amount;
                System.out.println("✅  Your new balance is: " + acc.balance);
            }
            else {
                System.out.println("⚠️  Not enough balance.");
            }
        }
    }

    public void enquireBalance(int accountNumber) {
        BankAccount acc = getAccount(accountNumber);
        if(acc != null) {
            System.out.println("✅  Your balance is: " + acc.balance);
        }
    }
}
