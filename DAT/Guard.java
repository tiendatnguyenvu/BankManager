package DAT;

import java.util.Date;

public class Guard extends Employee{
    //Attribute
    private String areaParking;

    //Constructor
    public Guard(String areaParking) {
        this.areaParking = areaParking;
    }

    public Guard(String empId, String fullName, String address, Date dob,
                 String phoneNumber, String duty, float stacticSalary, float
                         experience, float workingDay, float bonus, float
                         totalSalary, String areaParking) {
        super(empId, fullName, address, dob, phoneNumber, duty, stacticSalary,
                experience, workingDay, bonus, totalSalary);
        this.areaParking = areaParking;
    }

    public Guard (Accountant employee, String areaParking) {
        super(employee.getEmpId(), employee.getStringFullName(),
            employee.getAddress(), employee.getDob(), employee.getPhoneNumber(),
            employee.getDuty(), employee.getStacticSalary(),
            employee.getExperience(), employee.getWorkingDay(),
            employee.getBonus(), employee.getTotalSalary());
        setEmpId(null);
        this.areaParking = areaParking;
    }

    //getter and setter
    @Override
    public void setEmpId(String empId) {
        if (empId != null) {
            super.setEmpId(empId);
        }
        else {
            var value = "GUA" + getNextId();
            setNextId(getNextId() + 1);
            super.setEmpId(value);
        }
    }
    public String getAreaParking() {
        return areaParking;
    }

    public void setAreaParking(String areaParking) {
        this.areaParking = areaParking;
    }

    //method
    @Override
    public void work() {
        System.out.println("Bảo vệ " + getStringFullName() +
                " có trách nhiệm " + getDuty());
    }
}
