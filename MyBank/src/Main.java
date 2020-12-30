import java.util.Locale;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        scanner.useLocale(Locale.US);
        int action = 0;
        double amount;
        boolean quit = false;
        Bank bank = new Bank("Santander", "Poland");
        bank.createCustomer("John", "Doe", "19.02.1991", "98 765", "1234");
        bank.payIn(500, "98 765");
        bank.createCustomer("Mr", "X", "23.04.1982", "12 345", "4321");
        bank.payIn(300, "12 345");

        System.out.println("Welcome to the online banking application!");
        System.out.println("Via this application, you can create a new account in our bank"
                + " and then make payments!\n");
        showOptions();
        while (!quit){

            System.out.print("\nEnter action (6: to show options): ");
            action = intValidation();
            switch (action){

                case 0:
                    System.out.println("Closing application...");
                    quit = true;
                    break;
                case 1:
                    System.out.print("\tEnter your first name: ");
                    String firstName = scanner.nextLine();
                    System.out.print("\tEnter your last name: ");
                    String lastName = scanner.nextLine();
                    System.out.print("\tEnter your birth date: ");
                    String birthDate = scanner.nextLine();
                    String PESEL;
                    while(true){
                        System.out.print("\tEnter your PESEL: ");
                        PESEL = scanner.nextLine();
                        if(!bank.findPESEL(PESEL)){
                            break;
                        } else {
                            System.out.println("ERROR! A person with PESEL " + PESEL + " is already registered" +
                                    " in our bank. Please try again!");
                        }
                    }
                    String accountNumber;
                    while(true){
                        System.out.print("\tCreate your own account number: ");
                        accountNumber = scanner.nextLine();
                        if(!bank.findAccountNumber(accountNumber)){
                            break;
                        } else {
                            System.out.println("There is already someone with that account number. Please choose a new one!");
                        }
                    }
                    bank.createCustomer(firstName, lastName, birthDate, accountNumber, PESEL);
                    System.out.println("You've successfully registered your account in our bank!");
                    break;
                case 2:
                    System.out.print("How much money do you want to take?: ");
                    amount = doubleValidation();
                    System.out.print("Enter your account number: ");
                    accountNumber = scanner.nextLine();
                    bank.payOut(amount, accountNumber);
                    break;
                case 3:
                    System.out.print("How much money do you want to deposit?: ");
                    amount = doubleValidation();
                    System.out.print("Enter your account number: ");
                    accountNumber = scanner.nextLine();
                    bank.payIn(amount, accountNumber);
                    break;
                case 4:
                    System.out.print("Enter your account number: ");
                    accountNumber = scanner.nextLine();
                    Customer customer1, customer2;
                    if(bank.findCustomer(accountNumber) != null){
                        customer1 = bank.findCustomer(accountNumber);
                    } else {
                        System.out.println("No such customer in our bank!");
                        System.out.println("Back to main menu...");
                        break;
                    }
                    System.out.print("Enter an account number that you want to make a transfer to: ");
                    accountNumber = scanner.nextLine();
                    if(bank.findCustomer(accountNumber) != null){
                        customer2 = bank.findCustomer(accountNumber);
                        if(customer2 == customer1){
                            System.out.println("You've entered your own account number...");
                            System.out.println("Back to main menu...");
                            break;
                        }
                    } else {
                        System.out.println("No such customer in our bank!");
                        System.out.println("Back to main menu...");
                        break;
                    }
                    System.out.print("Enter amount of money that you wish to transfer: ");
                    amount = doubleValidation();
                    bank.transferMoney(customer1, customer2, amount);
                    break;
                case 5:
                    bank.showCustomers();
                    break;
                case 6:
                    showOptions();
                default:
                    System.out.println("Error, please input a number from 0 to 6!");
                    break;
            }
        }
    }

    public static void showOptions(){
        System.out.println("Avaiable options:\n");
        System.out.println("\t0. Close the application.\n");
        System.out.println("\t1. Make an account");
        System.out.println("\t2. Handout");
        System.out.println("\t3. Deposit");
        System.out.println("\t4. Transfer money to someone else");
        System.out.println("\t5. Show customers");
        System.out.println("\t6. Show options\n");
    }

    public static int intValidation(){
        boolean isCorrect = scanner.hasNextInt();
        if(isCorrect){
            int action = scanner.nextInt();
            scanner.nextLine(); // enter
            return action;
        } else {
            System.out.println("Invalid value! Please try again...");
            scanner.next();
            return intValidation();
        }
    }

    public static double doubleValidation(){
        boolean isCorrect = scanner.hasNextDouble();
        if(isCorrect){
            double action = scanner.nextDouble();
            scanner.nextLine(); // enter
            return action;
        } else {
            System.out.println("Invalid value! Please try again...");
            scanner.next();
            return doubleValidation();
        }
    }
}
