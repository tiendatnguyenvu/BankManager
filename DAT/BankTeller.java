package DAT;

import java.util.Date;

public class BankTeller extends Employee {
    //Attribute
    private String language;

    //Constructor
    public BankTeller(String language) {
        this.language = language;
    }

    public BankTeller(String empId, String fullName, String address, Date dob,
                      String phoneNumber, String duty, float stacticSalary,
                      float experience, float workingDay, float bonus,
                      float totalSalary, String language) {
        super(empId, fullName, address, dob, phoneNumber, duty, stacticSalary,
                experience, workingDay, bonus, totalSalary);
        this.language = language;
    }

    public BankTeller(Accountant employee, String language) {
        super(employee.getEmpId(), employee.getStringFullName(),
            employee.getAddress(), employee.getDob(), employee.getPhoneNumber(),
            employee.getDuty(), employee.getStacticSalary(),
            employee.getExperience(), employee.getWorkingDay(),
            employee.getBonus(), employee.getTotalSalary());
        setEmpId(null);
        this.language = language;
    }

    //getter and setter
    @Override
    public void setEmpId(String empId) {
        if (empId != null) {
            super.setEmpId(empId);
        }
        else {
            var value = "BAN" + getNextId();
            setNextId(getNextId());
            super.setEmpId(value);
        }
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public void work() {
        System.out.println("Giao dịch viên " + getStringFullName() +
                " có trách nhiệm " + getDuty());
    }

}
