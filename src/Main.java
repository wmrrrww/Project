import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

class Transaction {
    private final Date date;
    private final char type; // '+' for deposit, '-' for withdrawal
    private final double amount;
    private final double balance;
    private final String description;

    public Transaction(char type, double amount, double balance, String description) {
        this.date = new Date();
        this.type = type;
        this.amount = amount;
        this.balance = balance;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "date=" + date +
                ", type=" + type +
                ", amount=" + amount +
                ", balance=" + balance +
                ", description='" + description + '\'' +
                '}';
    }
}

class Account {
    private final int id;
    private double balance;
    private double annualInterestRate;
    private final Date dateCreated;
    private final ArrayList<Transaction> transactions;

    public Account(int id, double balance, double annualInterestRate) {
        this.id = id;
        this.balance = balance;
        this.annualInterestRate = annualInterestRate;
        this.dateCreated = new Date();
        this.transactions = new ArrayList<>();
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactions.add(new Transaction('+', amount, balance, "Deposit"));
            System.out.println("Deposit of " + amount + " was successful.");
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactions.add(new Transaction('-', amount, balance, "Withdrawal"));
            System.out.println("Withdrawal of " + amount + " was successful.");
        } else {
            System.out.println("Insufficient funds or invalid amount.");
        }
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        if (annualInterestRate >= 0) {
            this.annualInterestRate = annualInterestRate;
            System.out.println("Annual interest rate updated to " + annualInterestRate + "%.");
        } else {
            System.out.println("Interest rate must be non-negative.");
        }
    }

    public double getMonthlyInterest() {
        return (annualInterestRate / 100) / 12 * balance;
    }

    public void displayAccountSummary() {
        System.out.println("Account ID: " + id);
        System.out.println("Balance: " + balance);
        System.out.println("Annual Interest Rate: " + annualInterestRate);
        System.out.println("Date Created: " + dateCreated);
        System.out.println("Monthly Interest: " + getMonthlyInterest());
        System.out.println("Transactions:");
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Account account = new Account(1233, 1100, 7.5);

        while (true) {
            System.out.println("\n--- Account Menu ---");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. View Account Summary");
            System.out.println("4. Set Annual Interest Rate");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1: // Deposit
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 2: // Withdraw
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawalAmount = scanner.nextDouble();
                    account.withdraw(withdrawalAmount);
                    break;
                case 3: // View Account Summary
                    account.displayAccountSummary();
                    break;
                case 4: // Set Annual Interest Rate
                    System.out.print("Enter new annual interest rate: ");
                    double newInterestRate = scanner.nextDouble();
                    account.setAnnualInterestRate(newInterestRate);
                    break;
                case 5: // Exit
                    System.out.println("Exiting program. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please choose a number between 1 and 5.");
            }
        }
    }
}
