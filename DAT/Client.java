package DAT;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Scanner;

public class Client {
     private FullName fullname;
     private Date birthday;
     private Address residence;
     private String phonenumber;
     private String email;
     private String idcard;
     
     // constructors
     public Client(String idcard ,String fullname, String birthday, String residence, String phonenumber, String email) {
          setfullname(fullname);
          setBirthday(birthday);
          setaddress(residence);
          this.phonenumber = phonenumber;
          this.email = email;
          this.idcard = idcard;
     }

     public Client() {
          phonenumber = "";
          email = "";
          idcard = "";
          fullname = new FullName();
          residence = new Address();
     }

     // getter and setter
     public String getfullname() {
          return fullname.tostring();
     }

     public void setfullname(FullName fullname) {
          this.fullname = fullname;
     }

     public Date getBirthday() {
          return birthday;
     }

     public void setBirthday(String date) {
          try {
               this.birthday = new SimpleDateFormat ("dd/mm/yyyy").parse(date);
          } catch (ParseException e) {
               e.printStackTrace();
          }
     }

     public String getResidence() {
          return residence.tostring();
     }

     public void setResidence(Address residence) {
          this.residence = residence;
     }

     public String getPhonenumber() {
          return phonenumber;
     }

     public void setPhonenumber(String phonenumber) {
          this.phonenumber = phonenumber;
     }

     public String getEmail() {
          return email;
     }

     public void setEmail(String email) {
          this.email = email;
     }

     public String getIdcard() {
          return idcard;
     }

     public void setIdcard(String idcard) {
          this.idcard = idcard;
     }

     void setfullname (String fullname){
          String[] word = fullname.split(" ");
          String tmp = "";
          this.fullname.setLastname(word[0]);
          for (int i=1; i<word.length-1; i++){
               tmp += word[i] + " ";
          }
          this.fullname.setMidname(tmp);
          this.fullname.setFirstname(word[word.length-1]);
     }

     private void setaddress (String address){
          String[] word = address.split(", ");
          this.residence.setNumber (word[0]);
          this.residence.setStreet(word[1]);
          this.residence.setWard(word[2]);
          this.residence.setDistrict(word[3]);
          this.residence.setCity(word[4]);
          this.residence.setCountry(word[5]);
     }

     public void enterInfo (Scanner input){
          System.out.println("Nhap ho ten khach hang: ");
          fullname.enterInfo(input);
          System.out.print("So CCCD: ");
          idcard = input.nextLine();
          idcard = idcard.trim();
          // nhập dữ liệu ngày sinh 
          SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
          try {
               System.out.print("Nhap ngay thang nam sinh (dd/mm/yyyy): ");
               birthday = df.parse(input.nextLine());
          } catch (ParseException ex) {
          }
          // kết thúc nhập dữ liệu ngày sinh 
          System.out.println("Nhap dia chi: ");
          residence.enterInfo(input);
          System.out.print("So dien thoai: ");
          phonenumber = input.nextLine();
          phonenumber = phonenumber.trim();
          System.out.print("Dia chi email: ");
          email = input.nextLine();
          email = email.trim();
     }

     public void outputInfo (){
          System.out.print("Ho ten: ");
          fullname.outputInfo();
          System.out.println();
     }

     public String tostring (){
          return idcard + " - " + fullname.tostring() + " - " + residence.tostring() + " - " + phonenumber + " - " + email;
     }

     public void setResidence(String address) {
     }
}
