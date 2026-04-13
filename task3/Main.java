package task3;

import java.util.Scanner;

// Class to represent a bank account
class Account {
    private double balance;

    // constructor
    public Account(double initialBalance) {
        balance = initialBalance;
    }

    // method to check balance
    public double getBalance() {
        return balance;
    }

    // deposit method
    public void deposit(double amount) {
        balance = balance + amount;
        System.out.println("Amount deposited successfully.");
    }

    // withdraw method
    public boolean withdraw(double amount) {
        if (amount > balance) {
            return false;
        } else {
            balance = balance - amount;
            return true;
        }
    }
}

// ATM class to handle operations
class ATM {
    private Account userAccount;

    public ATM(Account acc) {
        userAccount = acc;
    }

    // display menu
    public void showMenu() {
        System.out.println("\n====== ATM MENU ======");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    // process user input
    public void start() {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            showMenu();
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Your current balance is: ₹" + userAccount.getBalance());
                    break;

                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depAmount = sc.nextDouble();

                    if (depAmount <= 0) {
                        System.out.println("Invalid amount. Please enter a positive value.");
                    } else {
                        userAccount.deposit(depAmount);
                    }
                    break;

                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    double withAmount = sc.nextDouble();

                    if (withAmount <= 0) {
                        System.out.println("Invalid amount.");
                    } else {
                        boolean success = userAccount.withdraw(withAmount);

                        if (success) {
                            System.out.println("Please collect your cash.");
                        } else {
                            System.out.println("Insufficient balance.");
                        }
                    }
                    break;

                case 4:
                    System.out.println("Thank you for using ATM. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 4);

        sc.close();
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        // initial balance can be anything
        Account acc = new Account(5000);
        ATM atmMachine = new ATM(acc);

        atmMachine.start();
    }
}