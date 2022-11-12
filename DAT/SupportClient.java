package DAT;

import java.util.Date;

public class SupportClient extends Employee {
    // Attribute
    private String service;

    // Constructor
    public SupportClient(String service) {
        this.service = service;
    }

    public SupportClient(String empId, String fullName, String address,
            Date dob, String phoneNumber, String duty, float stacticSalary, float experience, float workingDay,
            float bonus, float totalSalary, String service) {
        super(empId, fullName, address, dob, phoneNumber, duty, stacticSalary,
                experience, workingDay, bonus, totalSalary);
        this.service = service;
    }

    public SupportClient(Accountant employee, String service) {
        super(employee.getEmpId(), employee.getStringFullName(),
                employee.getAddress(), employee.getDob(), employee.getPhoneNumber(),
                employee.getDuty(), employee.getStacticSalary(),
                employee.getExperience(), employee.getWorkingDay(),
                employee.getBonus(), employee.getTotalSalary());
        setEmpId(null);
        this.service = service;
    }

    // getter and setter
    @Override
    public void setEmpId(String empId) {
        if (empId != null) {
            super.setEmpId(empId);
        } else {
            var value = "SUP" + getNextId();
            setNextId(getNextId() + 1);
            super.setEmpId(value);
        }
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    // method
    @Override
    public void work() {
        System.out.println("Nhân viên hỗ trợ khách hàng " +
                getStringFullName() + "có trách nhiệm " + getDuty());
    }
}
