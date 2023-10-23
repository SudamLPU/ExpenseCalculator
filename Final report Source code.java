package k22ug;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class ExpenseCalculator {

    private HashMap<String, Double> expenses;

    public ExpenseCalculator() {
        this.expenses = new HashMap<>();
    }

    public void addExpense(String date, double amount) {
        if (isValidDateFormat(date)) {
            this.expenses.put(date, this.expenses.getOrDefault(date, 0.0) + amount);
        } else {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
        }
    }

    public void modifyExpense(String date, double newAmount) {
        if (isValidDateFormat(date)) {
            if (this.expenses.containsKey(date)) {
                this.expenses.put(date, newAmount);
                System.out.println("Expense modified successfully.");
            } else {
                System.out.println("Expense not found for the given date.");
            }
        } else {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
        }
    }

    public void deleteExpense(String date) {
        if (isValidDateFormat(date)) {
            if (this.expenses.containsKey(date)) {
                this.expenses.remove(date);
                System.out.println("Expense deleted successfully.");
            } else {
                System.out.println("Expense not found for the given date.");
            }
        } else {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
        }
    }

    public double calculateTotalExpenses() {
        return this.expenses.values().stream().mapToDouble(Double::doubleValue).sum();
    }

    public void displayExpenses() {
        for (String date : this.expenses.keySet()) {
            System.out.println(date + ": $" + this.expenses.get(date));
        }
        System.out.println("Total Expenses: $" + calculateTotalExpenses());
    }

    private boolean isValidDateFormat(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false); // To make the parsing strict

        try {
            Date parsedDate = dateFormat.parse(date);
            return parsedDate != null;
        } catch (ParseException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpenseCalculator calculator = new ExpenseCalculator();

        while (true) {
            System.out.println("\nExpense Calculator");
            System.out.println("1. Add an expense");
            System.out.println("2. Modify an expense");
            System.out.println("3. Delete an expense");
            System.out.println("4. Display expenses");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (choice) {
                case 1:
                    System.out.print("Enter the date (YYYY-MM-DD): ");
                    String addDate = scanner.nextLine();
                    System.out.print("Enter the amount: ");
                    double addAmount = scanner.nextDouble();
                    calculator.addExpense(addDate, addAmount);
                    break;
                case 2:
                    System.out.print("Enter the date to modify (YYYY-MM-DD): ");
                    String modifyDate = scanner.nextLine();
                    System.out.print("Enter the new amount: ");
                    double newAmount = scanner.nextDouble();
                    calculator.modifyExpense(modifyDate, newAmount);
                    break;
                case 3:
                    System.out.print("Enter the date to delete (YYYY-MM-DD): ");
                    String deleteDate = scanner.nextLine();
                    calculator.deleteExpense(deleteDate);
                    break;
                case 4:
                    calculator.displayExpenses();
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
