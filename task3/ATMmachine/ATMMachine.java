import java.util.*;

// ================================
// CLASS 1 - BANK ACCOUNT CLASS
// ================================
class BankAccount {

    private String accountHolder;
    private String accountNumber;
    private double balance;

    // Constructor
    public BankAccount(String accountHolder, String accountNumber, double initialBalance) {
        this.accountHolder  = accountHolder;
        this.accountNumber  = accountNumber;
        this.balance        = initialBalance;
    }

    // Get Balance
    public double getBalance() {
        return balance;
    }

    // Get Account Holder Name
    public String getAccountHolder() {
        return accountHolder;
    }

    // Get Account Number
    public String getAccountNumber() {
        return accountNumber;
    }

    // Deposit Method
    public boolean deposit(double amount) {
        if(amount <= 0) {
            return false;
        }
        balance = balance + amount;
        return true;
    }

    // Withdraw Method
    public boolean withdraw(double amount) {
        if(amount <= 0) {
            return false;
        }
        if(amount > balance) {
            return false;
        }
        balance = balance - amount;
        return true;
    }
}


// ================================
// CLASS 2 - ATM CLASS
// ================================
class ATM {

    private BankAccount account;
    private Scanner scan;
    private final double MAX_WITHDRAWAL  = 50000;
    private final double MAX_DEPOSIT     = 100000;
    private final double MIN_BALANCE     = 500;

    // Constructor
    public ATM(BankAccount account) {
        this.account = account;
        this.scan    = new Scanner(System.in);
    }

    // ================================
    // DISPLAY WELCOME MESSAGE
    // ================================
    public void showWelcome() {
        System.out.println("================================");
        System.out.println("       WELCOME TO ABC BANK      ");
        System.out.println("================================");
        System.out.println("Account Holder : " + account.getAccountHolder());
        System.out.println("Account Number : " + account.getAccountNumber());
        System.out.println("================================");
    }

    // ================================
    // DISPLAY MAIN MENU
    // ================================
    public void showMenu() {
        System.out.println("\n================================");
        System.out.println("          ATM MENU              ");
        System.out.println("================================");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Exit");
        System.out.println("================================");
        System.out.print("Enter Your Choice (1-4) : ");
    }

    // ================================
    // CHECK BALANCE METHOD
    // ================================
    public void checkBalance() {
        System.out.println("\n================================");
        System.out.println("         ACCOUNT BALANCE        ");
        System.out.println("================================");
        System.out.printf("Available Balance : Rs. %.2f\n", account.getBalance());
        System.out.println("================================");
    }

    // ================================
    // DEPOSIT METHOD
    // ================================
    public void deposit() {
        System.out.println("\n================================");
        System.out.println("          DEPOSIT MONEY         ");
        System.out.println("================================");
        System.out.print("Enter Amount to Deposit : Rs. ");
        double amount = scan.nextDouble();

        // Validate Input
        if(amount <= 0) {
            System.out.println("Invalid Amount! Amount must be greater than 0.");
            return;
        }

        if(amount > MAX_DEPOSIT) {
            System.out.println("Maximum deposit limit is Rs. " + MAX_DEPOSIT);
            return;
        }

        // Do Deposit
        boolean success = account.deposit(amount);

        if(success) {
            System.out.println("================================");
            System.out.printf("Rs. %.2f Deposited Successfully!\n", amount);
            System.out.printf("Available Balance : Rs. %.2f\n", account.getBalance());
            System.out.println("================================");
        } else {
            System.out.println("Deposit Failed! Please try again.");
        }
    }

    // ================================
    // WITHDRAW METHOD
    // ================================
    public void withdraw() {
        System.out.println("\n================================");
        System.out.println("         WITHDRAW MONEY         ");
        System.out.println("================================");
        System.out.print("Enter Amount to Withdraw : Rs. ");
        double amount = scan.nextDouble();

        // Validate Input
        if(amount <= 0) {
            System.out.println("Invalid Amount! Amount must be greater than 0.");
            return;
        }

        if(amount > MAX_WITHDRAWAL) {
            System.out.println("Maximum withdrawal limit is Rs. " + MAX_WITHDRAWAL);
            return;
        }

        if(account.getBalance() - amount < MIN_BALANCE) {
            System.out.println("Insufficient Balance!");
            System.out.printf("Minimum balance of Rs. %.2f must be maintained.\n", MIN_BALANCE);
            System.out.printf("Available Balance : Rs. %.2f\n", account.getBalance());
            return;
        }

        // Do Withdrawal
        boolean success = account.withdraw(amount);

        if(success) {
            System.out.println("================================");
            System.out.printf("Rs. %.2f Withdrawn Successfully!\n", amount);
            System.out.printf("Remaining Balance : Rs. %.2f\n", account.getBalance());
            System.out.println("================================");
            System.out.println("Please collect your cash.");
        } else {
            System.out.println("Withdrawal Failed! Please try again.");
        }
    }

    // ================================
    // START ATM - MAIN LOOP
    // ================================
    public void start() {
        showWelcome();

        boolean running = true;

        while(running) {
            showMenu();
            int choice = scan.nextInt();

            switch(choice) {
                case 1:
                    checkBalance();
                    break;

                case 2:
                    deposit();
                    break;

                case 3:
                    withdraw();
                    break;

                case 4:
                    System.out.println("\n================================");
                    System.out.println("  Thank you for using ABC Bank! ");
                    System.out.println("       Have a nice day!         ");
                    System.out.println("================================");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid Choice! Please enter 1 to 4.");
            }
        }

        scan.close();
    }
}


// ================================
// CLASS 3 - MAIN CLASS
// ================================
public class ATMMachine {

    public static void main(String[] args) {

        // Create Bank Account
        BankAccount account = new BankAccount(
            "Rahul Kumar",
            "SB-123456789",
            10000.00
        );

        // Create ATM and Connect with Account
        ATM atm = new ATM(account);

        // Start ATM
        atm.start();
    }
}