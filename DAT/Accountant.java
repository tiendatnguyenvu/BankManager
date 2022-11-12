package DAT;

import java.util.Date;

public class Accountant extends Employee {
    //setter and getter
    @Override
    public void setEmpId(String empId) {
        if (empId != null) {
            super.setEmpId(empId);
        }
        else {
            var value = "ACC" + getNextId();
            setNextId(getNextId() + 1);
            super.setEmpId(value);
        }
    }

    @Override
    public void work() {
        System.out.println("Nhân viên kế toán + " + getStringFullName()
                + "có trách nhiệm " + getDuty());
    }

    //Constructor

    public Accountant() {
    }

    public Accountant(String empId, String fullName, String address, Date dob,
                      String phoneNumber, String duty, float stacticSalary,
                      float experience, float workingDay, float bonus
            , float totalSalary) {
        super(empId, fullName, address, dob, phoneNumber, duty, stacticSalary,
                experience, workingDay, bonus, totalSalary);
    }

    public Accountant (Accountant employee) {
        super(employee.getEmpId(), employee.getStringFullName(),
            employee.getAddress(), employee.getDob(), employee.getPhoneNumber(),
            employee.getDuty(), employee.getStacticSalary(),
            employee.getExperience(), employee.getWorkingDay(),
            employee.getBonus(), employee.getTotalSalary());
        setEmpId(null);
    }
}
