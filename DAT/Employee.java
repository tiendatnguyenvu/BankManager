package DAT;

import java.util.Date;

public abstract class Employee implements InterfaceEmployee {
    // attribute
    private static int nextId = 1000;
    private String empId;
    private FullName fullName;
    private String address;
    private Date dob;
    private String phoneNumber;
    private String duty;
    private float stacticSalary;
    private float experience;
    private float workingDay;
    private float bonus;
    private float totalSalary;

    public Employee() {
    }

    public Employee(String empId, String fullName, String address, Date dob,
            String phoneNumber, String duty, float stacticSalary, float experience, float workingDay, float bonus,
            float totalSalary) {
        this.empId = empId;
        setFullName(fullName);
        this.address = address;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.duty = duty;
        this.stacticSalary = stacticSalary;
        this.experience = experience;
        this.workingDay = workingDay;
        this.bonus = bonus;
        this.totalSalary = totalSalary;
    }

    // getter and setter

    public final static int getNextId() {
        return nextId;
    }

    public final static void setNextId(int nextId) {
        Employee.nextId = nextId;
    }

    public final String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public final String getStringFullName() {
        return fullName.firstName + " " + fullName.midName + fullName.lastName;
    }

    public final void setFullName(String fullName) {
        this.fullName = new FullName();
        this.fullName.midName = "";
        if (fullName != null) {
            var words = fullName.split("\\s+");
            this.fullName.firstName = words[0];
            for (int i = 1; i < words.length - 1; i++) {
                this.fullName.midName += words[i] + " ";
            }
            this.fullName.lastName = words[words.length - 1];
        }
    }

    public final String getAddress() {
        return address;
    }

    public final void setAddress(String address) {
        this.address = address;
    }

    public final Date getDob() {
        return dob;
    }

    public final void setDob(Date dob) {
        this.dob = dob;
    }

    public final String getPhoneNumber() {
        return phoneNumber;
    }

    public final void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public final String getDuty() {
        return duty;
    }

    public final void setDuty(String duty) {
        this.duty = duty;
    }

    public final float getStacticSalary() {
        return stacticSalary;
    }

    public final void setStacticSalary(float stacticSalary) {
        this.stacticSalary = stacticSalary;
    }

    public final float getExperience() {
        return experience;
    }

    public final void setExperience(float experience) {
        this.experience = experience;
    }

    public final float getWorkingDay() {
        return workingDay;
    }

    public final void setWorkingDay(float workingDay) {
        this.workingDay = workingDay;
    }

    public final float getBonus() {
        return bonus;
    }

    public final void setBonus(float bonus) {
        this.bonus = bonus;
    }

    public final float getTotalSalary() {
        return totalSalary;
    }

    public final void setTotalSalary(float totalSalary) {
        this.totalSalary = totalSalary;
    }

    // method
    @Override
    public abstract void work();

    @Override
    public void calcuBonus() {
        if (workingDay >= 22) {
            var bonusDay = workingDay - 22;
            bonus = bonusDay * 0.5f * stacticSalary / 22;
        } else {
            bonus = 0;
        }
    }

    @Override
    public void calcuTotalSalary() {
        totalSalary = workingDay * stacticSalary / 22 + bonus;
    }

    // Class con FullName
    private class FullName {
        private String firstName;
        private String midName;
        private String lastName;

        // Constructor
        public FullName() {
        }

        public FullName(String firstName, String midName, String lastName) {
            this.firstName = firstName;
            this.midName = midName;
            this.lastName = lastName;
        }

        // getter and setter
        public final String getFirstName() {
            return firstName;
        }

        public final void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public final String getMidName() {
            return midName;
        }

        public final void setMidName(String midName) {
            this.midName = midName;
        }

        public final String getLastName() {
            return lastName;
        }

        public final void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }
}
