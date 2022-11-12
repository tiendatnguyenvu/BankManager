package DAT;

import java.util.Scanner;

public class BankAccount extends Client implements Service {
     private long accountnumber;
     private double accountbalance;
     private String bankname;

     // constructors
     public BankAccount(String fullname, String birthday, String residence, String phonenumber, String email, String idcard, long accountnumber, double accountbalance, String bankname) {
          super(idcard, fullname, birthday, residence, phonenumber, email);
          this.accountnumber = accountnumber;
          this.accountbalance = accountbalance;
          this.bankname = bankname;
     }
     public BankAccount(long accountnumber, double accountbalance, String bankname) {
          this.accountnumber = accountnumber;
          this.accountbalance = accountbalance;
          this.bankname = bankname;
     }
     public BankAccount (){
          accountbalance = 0;
          accountnumber = 0;
          bankname = null;
     }

     // getter and setter
     public long getAccountnumber() {
          return accountnumber;
     }
     public void setAccountnumber(long accountnumber) {
          this.accountnumber = accountnumber;
     }
     public double getAccountbalance() {
          return accountbalance;
     }
     public void setAccountbalance(double accountbalance) {
          this.accountbalance = accountbalance;
     }

     public void enterInfo (Scanner input){
          System.out.println("Ngan hang: ");
          bankname = input.nextLine();
          System.out.println("So tai khoan: ");
          accountnumber = input.nextLong();
          System.out.println("So du tai khoan: ");
          accountbalance = input.nextDouble();
     }

     public void outputInfo (){
          System.out.println("Ngan hang: " + bankname);
          System.out.println("So tai khoan: " + accountnumber);
          System.out.println("So du tai khoan: " + accountbalance);
     }

     // interface service
     @Override
     public void deposite() {

     }

     @Override
     public void tranfers() {

     }

     @Override
     public void withdraw() {
          
     }

     @Override
     public void checkAccount() {
  
     }

     @Override
     public void checkBalance() {
          
     }
}
