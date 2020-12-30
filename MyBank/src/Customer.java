import java.util.ArrayList;

public class Customer {

    private String firstName;
    private String lastName;
    private String birthDate;
    private String PESEL; // Polish ID number.
    private String accountNumber;
    private double balance;
    private ArrayList<Double> payInList;
    private ArrayList<Double> payOutList;

    public Customer(String firstName, String lastName, String birthDate, String PESEL, String accountNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.balance = 0.00D;
        this.PESEL = PESEL;
        this.accountNumber = accountNumber;
        payInList = new ArrayList<Double>();
        payOutList = new ArrayList<Double>();
    }


    public String getFullName(){
        return firstName + " " + lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getPESEL() {
        return PESEL;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public boolean payIn(double amount){
        balance = balance + amount;
        System.out.println("Operation successfull! You've deposited " + amount + "zl to your bank account!");
        System.out.println("[Your balance: " + balance + "zl].");
        return true;
    }

    public boolean payOut(double amount){
        if(balance - amount < 0){
            System.out.println("You don't have enough money to take such amount   -   [Your balance: " + balance + "zl].");
            System.out.println("Operation unsuccessfull...");
            System.out.println("Back to main menu...");
            return false;
        } else {
            balance = balance - amount;
            System.out.println("Operation successfull! You've took " + amount + "zl from your bank account!");
            System.out.println("[Your balance: " + balance + "zl].");
            return true;
        }
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
