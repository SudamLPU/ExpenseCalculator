package k22ug;

import java.util.HashMap;
import java.util.Scanner;

public class ExpenseCalculator {

    private HashMap<String, Double> expenses;

    public ExpenseCalculator() {
        this.expenses = new HashMap<>();
    }

    public void addExpense(String date, double amount) {
        this.expenses.put(date, this.expenses.getOrDefault(date, 0.0) + amount);
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpenseCalculator calculator = new ExpenseCalculator();

        while (true) {
            System.out.println("\nExpense Calculator");
            System.out.println("1. Add an expense");
            System.out.println("2. Display expenses");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (choice) {
                case 1:
                    System.out.print("Enter the date (YYYY-MM-DD): ");
                    String date = scanner.nextLine();
                    System.out.print("Enter the amount: ");
                    double amount = scanner.nextDouble();
                    calculator.addExpense(date, amount);
                    break;
                case 2:
                    calculator.displayExpenses();
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}