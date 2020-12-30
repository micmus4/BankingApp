import java.util.ArrayList;

public class Bank {

    private String name;
    private String location; // City, address
    private ArrayList<Customer> customers;



    public Bank(String name, String location) {
        this.name = name;
        this.location = location;
        customers = new ArrayList<Customer>();
    }


    public Customer findCustomer(String accountNumber){
        for (int i = 0; i < customers.size(); i++){
            Customer customer = customers.get(i);
            if(customer.getAccountNumber().equals(accountNumber)){
                return customer;
            }
        }
        return null;
    }

    public boolean findAccountNumber(String accountNumber){
        for (int i = 0; i < customers.size(); i++){
            String accountNumberToCheck  = customers.get(i).getAccountNumber();
            if(accountNumber.equals(accountNumberToCheck)){
                return true;
            }
        }
        return false;
    }

    public boolean findPESEL(String PESEL){
        for (int i = 0; i < customers.size(); i++){
            String PESELtoCheck  = customers.get(i).getPESEL();
            if(PESEL.equals(PESELtoCheck)){
                return true;
            }
        }
        return false;
    }

    public boolean payIn(double amount, String accountNumber){
        Customer customer = findCustomer(accountNumber);
        if (customer != null){
            customer.payIn(amount);
        }
        System.out.println("There is no customer in our bank with such account number!");
        System.out.println("Back to main menu...");
        return false;
    }


    public boolean payOut(double amount, String accountNumber){
        Customer customer = findCustomer(accountNumber);
        if(customer != null){
            customer.payOut(amount);
        }
        System.out.println("There is no customer in our bank with such account number!");
        System.out.println("Back to main menu...");
        return false;
    }

    public void createCustomer(String firstName, String lastName, String birthDate, String accountNumber, String PESEL){
        Customer customer = new Customer(firstName, lastName, birthDate, PESEL, accountNumber);
        customers.add(customer);
    }


    public void showCustomers(){
        if(customers.size() == 0){
            System.out.println("There aren't any customers in our bank yet...");
            System.out.println();
            return;
        }
        System.out.println("List of customers:\n");
        for (int i = 0; i < customers.size(); i++){
            Customer customer = customers.get(i);
            System.out.println("\t - " + customer.getFullName() + "   (" + customer.getAccountNumber() + ")"
                    + "   CURRENT BALANCE: " + customer.getBalance() + "zl.");
        }
    }

    public void transferMoney(Customer customer1, Customer customer2, double amount){
        if (customer1.getBalance() - amount < 0){
            System.out.println("You don't have enough money to make such transfer  -   [Your balance: " + customer1.getBalance() + "zl].");
            System.out.println("Operation unsuccessfull...");
            System.out.println("Back to main menu...");
            return;
        } else {
            customer2.setBalance(customer2.getBalance() + amount);
            customer1.setBalance(customer1.getBalance() - amount);
        }
    }
}
