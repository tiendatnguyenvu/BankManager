package DAT;

import java.util.Scanner;

public class FullName {
     private String firstname;
     private String midname;
     private String lastname;

     // constructors
     public FullName(String firstname, String midname, String lastname) {
          this.firstname = firstname;
          this.midname = midname;
          this.lastname = lastname;
     }

     public FullName() {
          firstname = "";
          midname = "";
          lastname = "";
     }

     // getter and setter
     public String getFirstname() {
          return firstname;
     }

     public void setFirstname(String firstname) {
          this.firstname = firstname;
     }

     public String getMidname() {
          return midname;
     }

     public void setMidname(String midname) {
          this.midname = midname;
     }

     public String getLastname() {
          return lastname;
     }

     public void setLastname(String lastname) {
          this.lastname = lastname;
     }

     public void enterInfo (Scanner input){
          System.out.print("  Ho: ");
          lastname = input.nextLine();
          lastname = lastname.trim();
          System.out.print("  Ten dem: ");
          midname = input.nextLine();
          midname = midname.trim();
          System.out.print("  Ten: ");
          firstname = input.nextLine();
          firstname = firstname.trim();
     }

     public void outputInfo (){
          System.out.println(this.lastname + " " + this.midname + " " + this.firstname);
     }

     public String tostring (){
          return lastname + " " + midname + " " + firstname;
     }
}
