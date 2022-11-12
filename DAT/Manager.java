package DAT;

import java.util.Date;

public class Manager extends Employee {
    // Attribute
    private Date startDate;
    private Date endDate;
    private String area;

    // Constructor
    public Manager(Date startDate, Date endDate, String area) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.area = area;
    }

    public Manager(String empId, String fullName, String address, Date dob,
            String phoneNumber, String duty, float stacticSalary,
            float experience, float workingDay, float bonus,
            float totalSalary, Date startDate, Date endDate,
            String area) {
        super(empId, fullName, address, dob, phoneNumber, duty,
                stacticSalary, experience, workingDay, bonus, totalSalary);
        this.startDate = startDate;
        this.endDate = endDate;
        this.area = area;
    }

    // String empId, String fullName, String address, Date dob,
    // String phoneNumber, String duty, float stacticSalary, float
    // experience, float workingDay, float bonus, float totalSalary

    public Manager(Accountant employee, Date startDate,
            Date endDate, String area) {
        super(employee.getEmpId(), employee.getStringFullName(),
                employee.getAddress(), employee.getDob(), employee.getPhoneNumber(),
                employee.getDuty(), employee.getStacticSalary(),
                employee.getExperience(), employee.getWorkingDay(),
                employee.getBonus(), employee.getTotalSalary());
        setEmpId(null);
        this.startDate = startDate;
        this.endDate = endDate;
        this.area = area;
    }

    // getter and setter
    @Override
    public void setEmpId(String empId) {
        if (empId != null) {
            super.setEmpId(empId);
        } else {
            var value = "MAN" + getNextId();
            setNextId(getNextId() + 1);
            super.setEmpId(value);
        }
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    // method
    @Override
    public void work() {
        System.out.println("Quản lý" + getStringFullName() +
                " có trách nhiệm " + getDuty());
    }

    @Override
    public void calcuBonus() {
        if (getWorkingDay() >= 20) {
            var bonusDay = getWorkingDay() - 20;
            var amount = bonusDay * 0.5f * getStacticSalary() / 22;
            setBonus(amount);
        } else {
            setBonus(0);
        }
    }

    @Override
    public void calcuTotalSalary() {
        var amount = getWorkingDay() * getStacticSalary() / 22 + getBonus();
        setTotalSalary(amount * 1.25f);
    }
}
