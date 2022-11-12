package DAT;

import java.sql.Date;

public class DebitCard extends BankAccount {
     private Date startdate;
     private Date expirationdate;
     private int password;
     private double anualfee;

     //constructors
     public DebitCard(String fullname, String birthday, String residence, String phonenumber,
               String email, String idcard, long accountnumber, double accountbalance, Date startdate, Date expirationdate,
               int password, String bankname, double anualfee) {
          super(fullname, birthday, residence, phonenumber, email, idcard, accountnumber, accountbalance, bankname);
          this.startdate = startdate;
          this.expirationdate = expirationdate;
          this.password = password;
          this.anualfee = anualfee;
     }
     public DebitCard(long accountnumber, double accountbalance, Date startdate, Date expirationdate, int password, String bankname, double anualfee) {
          super(accountnumber, accountbalance, bankname);
          this.startdate = startdate;
          this.expirationdate = expirationdate;
          this.password = password;
          this.anualfee = anualfee;
     }
     public DebitCard(Date startdate, Date expirationdate, int password, double anualfee) {
          this.startdate = startdate;
          this.expirationdate = expirationdate;
          this.password = password;
          this.anualfee = anualfee;
     }
     public DebitCard (){
     }

     // getter and setter
     public Date getStartdate() {
          return startdate;
     }
     public void setStartdate(Date startdate) {
          this.startdate = startdate;
     }
     public Date getexpirationdate() {
          return expirationdate;
     }
     public void setexpirationdate(Date expirationdate) {
          this.expirationdate = expirationdate;
     }
     public int getPassword() {
          return password;
     }
     public void setPassword(int password) {
          this.password = password;
     }

     public double getAnualfee() {
          return anualfee;
     }

     public void setAnualfee(double anualfee) {
          this.anualfee = anualfee;
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
