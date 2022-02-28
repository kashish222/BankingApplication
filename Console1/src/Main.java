import java.util.Scanner;
public class Main {
    public static void main(String [] args) {

        Scanner sc = new Scanner(System.in);
        
        
        Account accounts [] = new Account[10];
        int numAccounts = 0; 

        int choice;

        do {
            choice = menu(sc);
            System.out.println();
            
            if(choice == 1) {
                accounts[numAccounts++] = createAccount(sc);
            } else if(choice == 2) {
                doDeposit(accounts, numAccounts, sc);
            } else if(choice == 3) {
                doWithdraw(accounts, numAccounts, sc);
            } else if(choice == 4) {
                applyInterest(accounts, numAccounts, sc);
            } else {
                System.out.println("Bye! Have a nice day.");
            }
            System.out.println();
        } while(choice != 5);
    }
    public static int accountMenu(Scanner sc) {
        System.out.println("Select Account Type");
        System.out.println("Press 1 for Checking Account");
        System.out.println("Press 2 for Savings Account");

        int choice;
        do {
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
        }while(choice < 1 || choice > 2);
        
        return choice;
    } 
    public static int searchAccount(Account accounts [], int count, int accountNumber) {
        for(int i=0; i<count; i++) {
            if(accounts[i].getAccountNumber() == accountNumber) {
                return i;
            }
        }

        return -1; 
    }

   
    public static void doDeposit(Account accounts [], int count, Scanner sc) {
       
        System.out.print("\nPlease enter account number: ");
        int accountNumber = sc.nextInt();

       
        int index = searchAccount(accounts, count, accountNumber);

        if(index >= 0) {
            
            System.out.print("Please enter Deposit Amount: ");
            double amount = sc.nextDouble();

            accounts[index].deposit(amount);
        } else {
            System.out.println("No account exist with AccountNumber: " + accountNumber);
        }
    }

    public static void doWithdraw(Account accounts [], int count, Scanner sc) {
        
        System.out.print("\nPlease enter account number: ");
        int accountNumber = sc.nextInt();

      
        int index = searchAccount(accounts, count, accountNumber);

        if(index >= 0) {

            System.out.print("Please enter Withdraw Amount: ");
            double amount = sc.nextDouble();
            accounts[index].withdraw(amount);
        } else {
            System.out.println("No account exist with AccountNumber: " + accountNumber);
        }
    }

    public static void applyInterest(Account accounts [], int count, Scanner sc) {
       
        System.out.print("\nPlease enter account number: ");
        int accountNumber = sc.nextInt();

       
        int index = searchAccount(accounts, count, accountNumber);

        if(index >= 0) {
            
            
            if(accounts[index] instanceof SavingAccount) {
                ((SavingAccount)accounts[index]).applyInterest();
            }
        } else {
            System.out.println("No account exist with AccountNumber: " + accountNumber);
        }
    }

   
    public static Account createAccount(Scanner sc) {

        Account account = null; 
        int choice = accountMenu(sc);

        int accountNumber;
        System.out.print("Enter Account Number: ");
        accountNumber = sc.nextInt();

        if(choice == 1)  { 
            System.out.print("Enter Transaction Fee: ");
            double fee = sc.nextDouble();
            account = new CheckingAccount(accountNumber, fee);
        } else { 
            
            System.out.print("Please enter Interest Rate: ");
            double ir = sc.nextDouble();
            account = new SavingAccount(accountNumber, ir);
        }
        return account;
    }

   
    public static int menu(Scanner sc) {
    	System.out.println("                   Welcome to KVST Bank Application");
        System.out.println("Bank Account Menu: ");
        System.out.println("Press 1 to Create New Account");
        System.out.println("Press 2 to Deposit Funds");
        System.out.println("Press 3 to Withdraw Funds");
        System.out.println("Press 4 to Apply Interest");
        System.out.println("Press 5 to Quit");

        int choice;

        do {
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
        } while(choice < 1 || choice > 5);

        return choice;
    }    
}
