package com.bank;

import java.util.*;

public class Bank {
    private List<Account> accounts = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("\n--- Bank Menu ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Display Account");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1: createAccount(); break;
                case 2: handleDeposit(); break;
                case 3: handleWithdraw(); break;
                case 4: displayAccount(); break;
                case 5: return;
                default: System.out.println("Invalid choice");
            }
        }
    }

    private void createAccount() {
        System.out.println("Enter account type (1. Savings 2. Current): ");
        int type = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Account Number: ");
        String accNo = scanner.nextLine();
        System.out.print("Holder Name: ");
        String name = scanner.nextLine();
        System.out.print("Initial Balance: ");
        double balance = scanner.nextDouble();

        if (type == 1) {
            System.out.print("Interest Rate: ");
            double rate = scanner.nextDouble();
            accounts.add(new SavingsAccount(accNo, name, balance, rate));
        } else {
            System.out.print("Overdraft Limit: ");
            double limit = scanner.nextDouble();
            accounts.add(new CurrentAccount(accNo, name, balance, limit));
        }

        System.out.println("Account created successfully!");
    }

    private Account findAccount(String accNo) {
        for (Account acc : accounts) {
            if (acc.accountNumber.equals(accNo)) {
                return acc;
            }
        }
        return null;
    }

    private void handleDeposit() {
        System.out.print("Enter Account Number: ");
        String accNo = scanner.nextLine();
        Account acc = findAccount(accNo);
        if (acc != null) {
            System.out.print("Amount to Deposit: ");
            double amt = scanner.nextDouble();
            acc.deposit(amt);
        } else {
            System.out.println("Account not found.");
        }
    }

    private void handleWithdraw() {
        System.out.print("Enter Account Number: ");
        String accNo = scanner.nextLine();
        Account acc = findAccount(accNo);
        if (acc != null) {
            System.out.print("Amount to Withdraw: ");
            double amt = scanner.nextDouble();
            acc.withdraw(amt);
        } else {
            System.out.println("Account not found.");
        }
    }

    private void displayAccount() {
        System.out.print("Enter Account Number: ");
        String accNo = scanner.nextLine();
        Account acc = findAccount(accNo);
        if (acc != null) {
            acc.displayDetails();
        } else {
            System.out.println("Account not found.");
        }
    }
}