package DAT;

import java.util.Scanner;

public class Address {
     private String number;
     private String street;
     private String ward;
     private String district;
     private String city;
     private String country;
     
     // constructors
     public Address(String number, String street, String ward, String district, String city, String country) {
          this.number = number;
          this.street = street;
          this.ward = ward;
          this.district = district;
          this.city = city;
          this.country = country;
     }

     public Address() {
          number = "0";
          street = "0";
          ward = "0";
          district = "0";
          city = "0";
          country = "0";
     }

     // getter and setter
     public String getNumber() {
          return number;
     }

     public void setNumber(String number) {
          this.number = number;
     }

     public String getStreet() {
          return street;
     }

     public void setStreet(String street) {
          this.street = street;
     }

     public String getWard() {
          return ward;
     }

     public void setWard(String ward) {
          this.ward = ward;
     }

     public String getDistrict() {
          return district;
     }

     public void setDistrict(String district) {
          this.district = district;
     }

     public String getCity() {
          return city;
     }

     public void setCity(String city) {
          this.city = city;
     }

     public String getCountry() {
          return country;
     }

     public void setCountry(String country) {
          this.country = country;
     }

     public void enterInfo (Scanner input){
          System.out.print("  So nha: ");
          number = input.nextLine();
          number = number.trim();
          System.out.print("  Duong: ");
          street = input.nextLine();
          street = street.trim();
          System.out.print("  Phuong/Xa: ");
          ward = input.nextLine();
          ward = ward.trim();
          System.out.print("  Quan/Huyen: ");
          district = input.nextLine();
          district = district.trim();
          System.out.print("  Thanh pho/Tinh: ");
          city = input.nextLine();
          city = city.trim();
          System.out.print("  Quoc gia: ");
          country = input.nextLine();
          country = country.trim();
     }

     public void outputInfo (){
          System.out.println(number + ", " + street + ", " + ward + ", " + district + ", " + city + ", " + country);
     }

     public String tostring (){
          return number + ", " + street + ", " + ward + ", " + district + ", " + city + ", " + country;
     }
}
