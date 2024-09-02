import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ATMSimulation {

    static class Account {
        private double balance;
        private int pin;
        private List<String> transactionHistory;

        public Account(double initialBalance, int initialPin) {
            this.balance = initialBalance;
            this.pin = initialPin;
            this.transactionHistory = new ArrayList<>();
        }

        public double getBalance() {
            return balance;
        }

        public boolean withdraw(double amount) {
            if (amount > balance) {
                System.out.println("Insufficient funds.");
                return false;
            }
            balance -= amount;
            transactionHistory.add("Withdrawn: $" + amount);
            return true;
        }

        public void deposit(double amount) {
            balance += amount;
            transactionHistory.add("Deposited: $" + amount);
        }

        public boolean changePin(int oldPin, int newPin) {
            if (this.pin == oldPin) {
                this.pin = newPin;
                transactionHistory.add("PIN changed.");
                return true;
            }
            System.out.println("Incorrect old PIN.");
            return false;
        }

        public List<String> getTransactionHistory() {
            return transactionHistory;
        }

        public boolean validatePin(int pin) {
            return this.pin == pin;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Account account = new Account(1000.00, 1234);

        System.out.println("Welcome to the ATM Machine!");
        System.out.print("Please enter your PIN: ");
        int enteredPin = scanner.nextInt();

        if (!account.validatePin(enteredPin)) {
            System.out.println("Invalid PIN. Exiting.");
            return;
        }

        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Balance Inquiry");
            System.out.println("2. Cash Withdrawal");
            System.out.println("3. Cash Deposit");
            System.out.println("4. Change PIN");
            System.out.println("5. Transaction History");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Your balance is: $" + account.getBalance());
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawalAmount = scanner.nextDouble();
                    if (account.withdraw(withdrawalAmount)) {
                        System.out.println("Please take your cash.");
                    }
                    break;
                case 3:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    System.out.println("Amount deposited successfully.");
                    break;
                case 4:
                    System.out.print("Enter your old PIN: ");
                    int oldPin = scanner.nextInt();
                    System.out.print("Enter your new PIN: ");
                    int newPin = scanner.nextInt();
                    if (account.changePin(oldPin, newPin)) {
                        System.out.println("PIN changed successfully.");
                    }
                    break;
                case 5:
                    System.out.println("Transaction History:");
                    for (String transaction : account.getTransactionHistory()) {
                        System.out.println(transaction);
                    }
                    break;
                case 6:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
}
