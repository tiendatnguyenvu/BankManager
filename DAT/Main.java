package DAT;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int choice = 0;
        var empFileName = "//Volumes//PNY//tài liệu CNTT//JAVA//PROJECTS//DAT//file";
        ArrayList<Employee> employees = new ArrayList<>(readEmpFromFile(empFileName));

        //------------------------truc lam-------------------------------------------
        ArrayList <Client> dskh = new ArrayList<Client>();
        readfile_clientList(dskh);
        for (Client x : dskh){
            System.out.println(x.tostring());
            System.out.println();
        }

        updateEmployeeId(employees);
        do {
            // Menu1
            displayMenu1();
            choice = Integer.parseInt(input.nextLine());
            switch (choice) {
                case 1: {
                    // Menu2
                    displayMenu2();
                    choice = Integer.parseInt(input.nextLine());
                    switch (choice) {
                        case 0: {
                            showMessage("Xin chào và hẹn gặp lại!");
                            break;
                        }
                        case 1: {
                            // Menu3
                            displayMenu3();
                            choice = Integer.parseInt(input.nextLine());
                            switch (choice) {
                                case 0: {
                                    showMessage("Xin chào và hẹn gặp lại!");
                                    break;
                                }
                                case 1: {
                                    Employee mananger = createManager(input);
                                    employees.add(mananger);
                                    break;
                                }
                                case 2: {
                                    Employee bankTeller = createBankTeller(input);
                                    employees.add(bankTeller);
                                    break;
                                }
                                case 3: {
                                    Employee accountant = createAccountant(input);
                                    employees.add(accountant);
                                    break;
                                }
                                case 4: {
                                    Employee supportClient = createSupportClient(input);
                                    employees.add(supportClient);
                                    break;
                                }
                                case 5: {
                                    Employee guard = createGuard(input);
                                    employees.add(guard);
                                    break;
                                }

                                default:
                                    showMessage("Sai chức năng! Vụi lòng chọn lại");
                            }
                            break;
                        }
                        //ham xuất
                        case 2: {
                            if (employees.size() > 0) {
                                showEmployees(employees);
                            } else {
                                showMessage("Danh sách rỗng!");
                            }
                            break;
                        }

                        case 3: {
                            if (employees.size() > 0) {
                                System.out.println("Nhập mã nhân sự cần tìm kiếm: ");
                                var empIdInput = input.nextLine();
                                var result = searchEmployeesByEmpID(employees, empIdInput);
                                if (result != null) {
                                    showMessage("TÌM THẤY KẾT QUẢ:");
                                    showEmployee(result);
                                } else {
                                    showMessage("KHÔNG TÌM THẤY NHÂN VIÊN CÓ MÃ " + empIdInput);
                                }

                            } else {
                                showMessage("Danh sách rỗng!");
                            }
                            break;
                        }

                        case 4: {
                            if (employees.size() > 0) {
                                System.out.println("Nhập mã nhân sự cần xóa: ");
                                var empIdInput = input.nextLine();
                                boolean isRemove = removeEmpByIdEmp(employees, empIdInput);
                                if (isRemove) {
                                    showMessage("ĐÃ XÓA NHÂN VIÊN CÓ MÃ NHÂN SỰ " + empIdInput);
                                } else {
                                    showMessage("KHÔNG TÌM THẤY NHÂN VIÊN CÓ MÃ " + empIdInput);
                                }
                            } else {
                                showMessage("Danh sách rỗng!");
                            }
                            break;
                        }

                        case 5: {
                            if (employees.size() > 0) {
                                System.out.println("Nhập mã nhân sự cần nhập liệu: ");
                                var empIdInput = input.nextLine();
                                var result = searchEmployeesByEmpID(employees, empIdInput);
                                if (result != null) {
                                    System.out.println("Nhập số ngày làm việc cập nhập: ");
                                    var dayWorkingInput = Float.parseFloat(input.nextLine());
                                    result.setWorkingDay(dayWorkingInput);
                                    showMessage("Cập nhật thành công!");
                                } else {
                                    System.out.println("\"KHÔNG TÌM THẤY NHÂN VIÊN CÓ MÃ \" + empIdInput");
                                }
                            } else {
                                showMessage("Danh sách rỗng!");
                            }
                            break;
                        }
                        // System.out.println("4.Xóa nhân sự theo mã nhân sự");
                        // System.out.println("5.Cập nhập số ngày làm việc của 1 nhân sự");
                        // System.out.println("6.Hiển thị lương của các nhân sự");
                        // System.out.println("7.Coi nhiệm vụ của các nhân sự");
                        // System.out.println("8.Tìm quản lý theo nhiệm kỳ");
                        // System.out.println("9.Lưu trữ danh sách nhân sự vào File");
                        // System.out.println("---Xin mời lựa chọn: ");
                        //
                        case 6: {
                            if (employees.size() > 0) {
                                showPayroll(employees);
                            } else {
                                showMessage("Danh sách rỗng!");
                            }
                            break;
                        }

                        case 7: {
                            if (employees.size() > 0) {
                                showDuty(employees);
                            } else {
                                showMessage("Danh sách rỗng!");
                            }
                            break;
                        }

                        case 8: {
                            if (employees.size() > 0) {
                                System.out.println("Nhập năm bắt đầu nhiệm kì, ví dụ: 2022");
                                var startYear = Integer.parseInt(input.nextLine());
                                System.out.println("Nhập năm kết thúc nhiệm kì,ví dụ: 2022 ");
                                var endYear = Integer.parseInt(input.nextLine());
                                var result = findManagerByTerm(employees, startYear, endYear);
                                if (result.size() > 0) {
                                    showMessage("Tìm thấy " + result.size()
                                            + " kết quả");
                                } else {
                                    showMessage("Không có kết quả nào");
                                }
                                showEmployees(employees);
                            } else {
                                showMessage("Danh sách rỗng!");
                            }
                            break;
                        }

                        case 9: {
                            if (employees.size() > 0) {
                                var isSuccess = writeEmpToFile(employees, empFileName);
                                if (isSuccess) {
                                    showMessage("Ghi FILE thành công");
                                } else {
                                    showMessage("Ghi FILE thất bại!");
                                }

                            } else {
                                showMessage("Danh sách nhân viên rỗng!");
                            }
                            break;
                        }

                        default:
                            showMessage("Sai chức năng! Vụi lòng chọn lại");
                    }
                    break;
                }

// ------------------------------------truc lam----------------------------------------------------------------------------------------
                case 2: {
                    trucmenu();
                    choice = Integer.parseInt(input.nextLine());
                    switch (choice) {
                        case 0: {
                            showMessage("Xin chao va hen gap lai");
                            break;
                        }

                        // thêm khách hàng
                        case 1: {
                            Client tmp = new Client();
                            tmp.enterInfo(input);
                            dskh.add(tmp);
                            writefile_clientList(dskh);
                            break;
                        }

                        // huy khach hang
                        case 2: {
                            int tmp = 0;
                            System.out.print("Nhap so CCCD khach hang can: ");
                            String idcard = input.nextLine();
                            for (Client x : dskh){
                                if (idcard.compareTo(x.getIdcard()) == 0){
                                    dskh.remove(x);
                                    tmp = 1;
                                }
                            }
                            writefile_clientList(dskh);
                            if (tmp == 1) {
                                System.out.println("Da xoa khach hang");
                            }
                            else {
                                System.out.println("Khon ton tai khach hang tren");
                            }
                            break;
                        }

                        // xuat danh sach khach hang
                        case 3: {
                            if (dskh != null){
                                xuat_ds_kh(dskh);
                            }
                            else {
                                System.out.println("Danh sach rong");
                            }
                            break;
                        }

                        // cap nhat thong tin khach hang
                        case 4: {
                            int tmp = 0, trucchoice;
                            System.out.print("Nhap so CCCD khach hang: ");
                            String idcard = input.nextLine();
                            for (Client x : dskh){
                                if (idcard.compareTo(x.getIdcard()) == 0){
                                    tmp = 1;
                                    trucmenu4();
                                    trucchoice = input.nextInt();
                                    switch (trucchoice) {
                                        case 1:
                                            System.out.print("Nhap so CCCD moi: ");
                                            String cccd = input.nextLine();
                                            x.setIdcard(cccd);
                                            break;
                                        
                                        case 2:
                                            System.out.print("Nhap ho ten khach hang: ");
                                            String name = input.nextLine();
                                            x.setfullname(name);
                                            break;

                                        case 3:
                                            System.out.print("Nhap ngay thang nam sinh (dd/mm/yyyy): ");
                                            String date = input.nextLine();
                                            x.setBirthday(date);
                                            break;
                                        
                                        case 4:
                                            System.out.print("Nhap dia chi: ");
                                            String address = input.nextLine();
                                            x.setResidence(address);
                                            break;

                                        case 5:
                                            System.out.print("Nhap so dien thoai: ");
                                            String phonenumber = input.nextLine();
                                            x.setPhonenumber(phonenumber);
                                            break;
                                        
                                        case 6:
                                            System.out.print("Nhap dia chi email: ");
                                            String email = input.nextLine();
                                            x.setEmail(email);
                                            break;

                                        default:
                                            System.out.println("Nhap sai chuc nang vui long nhap lai");
                                    }
                                }
                            }
                            writefile_clientList(dskh);
                            if (tmp == 0){
                                System.out.println("Danh sach rong");
                            }
                            break;
                        }

                        // tim kiem thong tin khach hang
                        case 5: {
                            int tmp = 0;
                            System.out.print("Nhap so CCCD khach hang can tim: ");
                            String idcard = input.nextLine();
                            for (Client x : dskh){
                                if (idcard.compareTo(x.getIdcard()) == 0){
                                    tmp = 1;
                                    x.outputInfo();
                                    System.out.println("----------------------------------");
                                }
                            }
                            if (tmp == 0){
                                System.out.println("Khong ton tai khach hang");
                            }
                            break;
                        }

                        default:
                            showMessage("Sai chuc nang! Vui long chon lai");
                    }
                    break;
                }

// ================================================dat lam============================================================================
                case 3: {
                    datmenu1();
                    choice = Integer.parseInt(input.nextLine());
                    do {
                        switch (choice) {
                            case 0: {
                                showMessage("Xin chao va hen gap lai");
                                break;
                            }

                            case 1: {
                                break;
                            }

                            case 2: {
                                break;
                            }

                            case 3: {
                                break;
                            }

                            case 4: {
                                break;
                            }

                            case 5: {
                                break;
                            }

                            default:
                                showMessage("Sai chuc nang! Vui long chon lai");
                        }
                    } while (choice != 0);
                    break;
                }

                default:
                    showMessage("Sai chức năng! Vụi lòng chọn lại");
            }

        } while (choice != 0);
    }

    private static ArrayList<Employee> findManagerByTerm(ArrayList<Employee> employees, int startYear, int endYear) {
        ArrayList<Employee> result = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        for (var emp : employees) {
            if (emp instanceof Manager) {
                var manager = (Manager) emp;
                calendar.setTime(manager.getStartDate());
                var start = calendar.get(Calendar.YEAR);
                calendar.setTime(manager.getEndDate());
                var end = calendar.get(Calendar.YEAR);
                // nếu nhiệm kì cần tìm nằm trong giai đoạn
                // nhiệm kì của manager hiện tại, add vào danh sách kết quả
                if (start <= startYear && end >= endYear) {
                    result.add(manager);
                }
            }
        }
        return result;
    }

    private static void showDuty(ArrayList<Employee> employees) {
        showMessage("Giao dịch viên");
        for (var emp : employees) {
            if (emp instanceof BankTeller) {
                System.out.println("Giao dịch viên " + "(" + emp.getEmpId() +
                        ")" + " có nhiệm vụ " + emp.getDuty());
            }
        }
        showMessage("Nhân viên kế toán: ");
        for (var emp : employees) {
            if (emp instanceof Accountant) {
                System.out.println("Nhân viên Kế toán " + "(" + emp.getEmpId()
                        + ")" + " có nhiệm vụ " + emp.getDuty());
            }
        }
        showMessage("Quản lý");
        for (var emp : employees) {
            if (emp instanceof Manager) {
                System.out.println("Quản lý " + "(" + emp.getEmpId() + ")" +
                        " có nhiệm vụ " + emp.getDuty());
            }
        }
        showMessage("Nhân viên hỗ trợ khách hàng");
        for (var emp : employees) {
            if (emp instanceof SupportClient) {
                System.out.println("Nhân viên hỗ trợ khách hàng " + "(" +
                        emp.getEmpId() + ")" + " có nhiệm vụ " + emp.getDuty());
            }
        }
        showMessage("Nhân viên bảo vệ");
        for (var emp : employees) {
            if (emp instanceof Guard) {
                System.out.println("Nhân viên bảo vệ " + "(" + emp.getEmpId()
                        + ")" + " có nhiệm vụ " + emp.getDuty());
            }
        }
    }

    private static void showPayroll(ArrayList<Employee> employees) {
        System.out.printf("%-10s%-35s%-15s%-15s%-15s%-20s\n", "Mã NV",
                "Họ và tên", "Mức lương", "Số ngày làm", "Tiền thưởng", "Tổng lương");
        for (var emp : employees) {
            emp.calcuBonus();
            emp.calcuTotalSalary();
            System.out.printf("%-10s%-35s%-15s%-15s%-15s%-20s\n", emp.getEmpId(),
                    emp.getStringFullName(), emp.getStacticSalary(), emp.getWorkingDay(),
                    emp.getBonus(), emp.getTotalSalary());
        }
    }

    private static boolean removeEmpByIdEmp(ArrayList<Employee> employees, String empIdInput) {
        for (var emp : employees) {
            if (emp.getEmpId().compareToIgnoreCase(empIdInput) == 0) {
                employees.remove(emp);
                return true;
            }
        }
        return false;
    }

    private static void showEmployee(Employee employee) {
        var fomart = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(fomart);
        // var empFirstId = employee.getEmpId().substring(0, 3);
        if (employee instanceof Accountant) {
            Accountant accountant = (Accountant) employee;
            showHeaderAccountant();
            showAccountant(accountant, simpleDateFormat);
        }

        if (employee instanceof BankTeller) {
            BankTeller bankTeller = (BankTeller) employee;
            showHeaderBankTeller();
            showBankTeller(bankTeller, simpleDateFormat);
        }

        if (employee instanceof Guard) {
            Guard guard = (Guard) employee;
            showHeaderGuard();
            showGuard(guard, simpleDateFormat);
        }

        if (employee instanceof Manager) {
            Manager manager = (Manager) employee;
            showHeaderManager();
            showManager(manager, simpleDateFormat);
        }

        if (employee instanceof SupportClient) {
            SupportClient supportClient = (SupportClient) employee;
            showHeaderAccountant();
            showSupportClient(supportClient, simpleDateFormat);
        }
    }

    private static Employee searchEmployeesByEmpID(ArrayList<Employee> employees, String empIdInput) {
        for (var emp : employees) {
            if (emp.getEmpId().compareToIgnoreCase(empIdInput) == 0) {
                return emp;
            }
        }
        return null;
    }

    private static void updateEmployeeId(ArrayList<Employee> employees) {
        int maxId = 1000;
        for (var emp : employees) {
            int curId = Integer.parseInt(emp.getEmpId().substring(3));
            if (curId > maxId) {
                maxId = curId;
            }
        }
        Employee.setNextId(maxId + 1);
    }

    // String empId, String fullName, String address, Date dob,
    // String phoneNumber, String duty, float stacticSalary, float
    // experience, float workingDay, float bonus, float totalSalary
    private static ArrayList<Employee> readEmpFromFile(String empFileName) {
        ArrayList<Employee> employees = new ArrayList<>();
        var fomart = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(fomart);
        File file = new File(empFileName);
        try {
            file.createNewFile();
            var input = new Scanner(file);
            while (input.hasNextLine()) {
                var line = input.nextLine();
                var data = line.split("-");
                var employee = createEmpFromData(data, simpleDateFormat);
                if (employee != null) {
                    employees.add(employee);
                }
            }
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
    }

    private static Employee createEmpFromData(String[] data, SimpleDateFormat simpleDateFormat) {
        var empId = data[0];
        var fullName = data[1];
        var address = data[2];
        Date dob = null;
        try {
            dob = simpleDateFormat.parse(data[3]);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        var phoneNumber = data[4];
        var duty = data[5];
        var staticSalary = Float.parseFloat(data[6]);
        var experience = Float.parseFloat(data[7]);
        var workingDay = Float.parseFloat(data[8]);
        var bonus = Float.parseFloat(data[9]);
        var totalSalary = Float.parseFloat(data[10]);

        String empFirstId = empId.substring(0, 3);
        if (empFirstId.compareTo("ACC") == 0) {
            return new Accountant(empId, fullName, address, dob, phoneNumber, duty, staticSalary, experience,
                    workingDay,
                    bonus, totalSalary);
        } else if (empFirstId.compareTo("BAN") == 0) {
            var language = data[11];
            return new BankTeller(empId, fullName, address, dob, phoneNumber, duty, staticSalary, experience,
                    workingDay,
                    bonus, totalSalary, language);
        } else if (empFirstId.compareTo("SUP") == 0) {
            var service = data[11];
            return new SupportClient(empId, fullName, address, dob, phoneNumber, duty, staticSalary, experience,
                    workingDay,
                    bonus, totalSalary, service);
        } else if (empFirstId.compareTo("GUA") == 0) {
            var areaParking = data[11];
            return new Guard(empId, fullName, address, dob, phoneNumber, duty, staticSalary, experience, workingDay,
                    bonus, totalSalary, areaParking);
        } else if (empFirstId.compareTo("MAN") == 0) {
            Date startDate = null;
            Date endDate = null;
            try {
                startDate = simpleDateFormat.parse(data[11]);
                endDate = simpleDateFormat.parse(data[12]);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            var area = data[13];
            return new Manager(empId, fullName, address, dob, phoneNumber, duty, staticSalary, experience, workingDay,
                    bonus, totalSalary, startDate, endDate, area);
        }
        // Neu không phải các kiểu dữ liệu trên thì return null
        return null;
    }

    // super(employee.getEmpId(), employee.getStringFullName(),
    // employee.getAddress(), employee.getDob(), employee.getPhoneNumber(),
    // employee.getDuty(), employee.getStacticSalary(),
    // employee.getExperience(), employee.getWorkingDay(),
    // employee.getBonus(), employee.getTotalSalary());
    private static boolean writeEmpToFile(ArrayList<Employee> employees, String empFileName) {
        try {
            PrintWriter printWriter = new PrintWriter(empFileName);
            var fomart = "dd/MM/yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(fomart);
            for (var emp : employees) {
                if (emp instanceof Accountant) {
                    // var accountant = (Accountant) emp;
                    printWriter.printf("%s-%s-%s-%s-%s-%s-%f-%f-%f-%f-%f\n",
                            emp.getEmpId(), emp.getStringFullName(),
                            emp.getAddress(), simpleDateFormat.format(emp.getDob()),
                            emp.getPhoneNumber(), emp.getDuty(),
                            emp.getStacticSalary(), emp.getExperience(),
                            emp.getWorkingDay(), emp.getBonus(),
                            emp.getTotalSalary());
                }
                if (emp instanceof BankTeller) {
                    var bankTeller = (BankTeller) emp;
                    printWriter.printf("%s-%s-%s-%s-%s-%s-%f-%f-%f-%f-%f-%s\n",
                            bankTeller.getEmpId(), bankTeller.getStringFullName(),
                            bankTeller.getAddress(), simpleDateFormat.format(bankTeller.getDob()),
                            bankTeller.getPhoneNumber(), bankTeller.getDuty(),
                            bankTeller.getStacticSalary(), bankTeller.getExperience(),
                            bankTeller.getWorkingDay(), bankTeller.getBonus(),
                            bankTeller.getTotalSalary(), bankTeller.getLanguage());
                }
                if (emp instanceof Manager) {
                    var manager = (Manager) emp;
                    printWriter.printf("%s-%s-%s-%s-%s-%s-%f-%f-%f-%f-%f-%s-%s-%s\n",
                            manager.getEmpId(), manager.getStringFullName(),
                            manager.getAddress(), simpleDateFormat.format(manager.getDob()),
                            manager.getPhoneNumber(), manager.getDuty(),
                            manager.getStacticSalary(), manager.getExperience(),
                            manager.getWorkingDay(), manager.getBonus(), manager.getTotalSalary(),
                            simpleDateFormat.format(manager.getStartDate()),
                            simpleDateFormat.format(manager.getEndDate()), manager.getArea());
                }
                if (emp instanceof SupportClient) {
                    var supportClient = (SupportClient) emp;
                    printWriter.printf("%s-%s-%s-%s-%s-%s-%f-%f-%f-%f-%f-%s\n",
                            supportClient.getEmpId(), supportClient.getStringFullName(),
                            supportClient.getAddress(), simpleDateFormat.format(supportClient.getDob()),
                            supportClient.getPhoneNumber(), supportClient.getDuty(),
                            supportClient.getStacticSalary(), supportClient.getExperience(),
                            supportClient.getWorkingDay(), supportClient.getBonus(),
                            supportClient.getTotalSalary(), supportClient.getService());
                }
                if (emp instanceof Guard) {
                    var guard = (Guard) emp;
                    printWriter.printf("%s-%s-%s-%s-%s-%s-%f-%f-%f-%f-%f-%s\n",
                            guard.getEmpId(), guard.getStringFullName(),
                            guard.getAddress(), simpleDateFormat.format(guard.getDob()),
                            guard.getPhoneNumber(), guard.getDuty(),
                            guard.getStacticSalary(), guard.getExperience(),
                            guard.getWorkingDay(), guard.getBonus(),
                            guard.getTotalSalary(), guard.getAreaParking());
                }
            }
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static void showEmployees(ArrayList<Employee> employees) {
        var fomart = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(fomart);
        showHeaderBankTeller();
        for (var emp : employees) {
            if (emp instanceof BankTeller) {
                var bankTeller = (BankTeller) emp;
                showBankTeller(bankTeller, simpleDateFormat);
            }
        }
        //
        showHeaderAccountant();
        for (var emp : employees) {
            if (emp instanceof Accountant) {
                var accountant = (Accountant) emp;
                showAccountant(accountant, simpleDateFormat);
            }
        }
        showHeaderSupportClient();
        for (var emp : employees) {
            if (emp instanceof SupportClient) {
                var supportClient = (SupportClient) emp;
                showSupportClient(supportClient, simpleDateFormat);
            }
        }
        showHeaderManager();
        for (var emp : employees) {
            if (emp instanceof Manager) {
                var manager = (Manager) emp;
                showManager(manager, simpleDateFormat);
            }
        }
        showHeaderGuard();
        for (var emp : employees) {
            if (emp instanceof Guard) {
                var guard = (Guard) emp;
                showGuard(guard, simpleDateFormat);
            }
        }
    }

    private static void showGuard(Guard guard,
            SimpleDateFormat simpleDateFormat) {
        System.out.printf("%-10s%-25s%-40s%-15s%-15s%-25s%-15.1f%-10.1f%-35s\n",
                guard.getEmpId(), guard.getStringFullName(),
                guard.getAddress(), simpleDateFormat.format(guard.getDob()),
                guard.getPhoneNumber(), guard.getDuty(),
                guard.getExperience(), guard.getWorkingDay(), guard.getAreaParking());
    }

    private static void showHeaderGuard() {
        showMessage("DANH SÁCH BẢO VỆ");
        System.out.printf("%-10s%-25s%-40s%-15s%-15s%-25s%-15s%-10s%-35s\n", "Mã NV",
                "Họ và tên", "Địa chỉ", "Ngày sinh", "SĐT", "Nhiệm vụ",
                "Kinh nghiệm", "Ngày làm", "Khu vực trực");
    }

    private static void showManager(Manager manager, SimpleDateFormat simpleDateFormat) {
        System.out.printf("%-10s%-25s%-40s%-15s%-15s%-25s%-15.1f%-10.1f%-15s%-15s%-40s\n",
                manager.getEmpId(), manager.getStringFullName(),
                manager.getAddress(), simpleDateFormat.format(manager.getDob()),
                manager.getPhoneNumber(), manager.getDuty(),
                manager.getExperience(), manager.getWorkingDay(),
                simpleDateFormat.format(manager.getStartDate()),
                simpleDateFormat.format(manager.getEndDate()), manager.getArea());
    }

    private static void showHeaderManager() {
        showMessage("DANH SÁCH QUẢN LÝ");
        System.out.printf("%-10s%-25s%-40s%-15s%-15s%-25s%-15s%-10s%-15s%-15s%-25s\n",
                "Mã NV", "Họ và tên", "Địa chỉ", "Ngày sinh", "SĐT", "Nhiệm vụ",
                "Kinh nghiệm", "Ngày làm", "Ngày BĐ", "Ngày KT", "Khu vực quản bá");
    }

    private static void showSupportClient(SupportClient supportClient, SimpleDateFormat simpleDateFormat) {
        System.out.printf("%-10s%-25s%-40s%-15s%-15s%-25s%-15.1f%-10.1f%-40s\n",
                supportClient.getEmpId(), supportClient.getStringFullName(),
                supportClient.getAddress(), simpleDateFormat.format(supportClient.getDob()),
                supportClient.getPhoneNumber(), supportClient.getDuty(),
                supportClient.getExperience(), supportClient.getWorkingDay(),
                supportClient.getService());
    }

    private static void showHeaderSupportClient() {
        showMessage("DANH SÁCH NHÂN VIÊN HỖ TRỢ KHÁCH HÀNG");
        System.out.printf("%-10s%-25s%-40s%-15s%-15s%-25s%-15s%-10s%-40s\n",
                "Mã NV", "Họ và tên", "Địa chỉ", "Ngày sinh", "SĐT", "Nhiệm vụ",
                "Kinh nghiệm", "Ngày làm", "Dịch vụ hỗ trợ");
    }

    private static void showBankTeller(BankTeller bankTeller,
            SimpleDateFormat simpleDateFormat) {
        System.out.printf("%-10s%-25s%-40s%-15s%-15s%-25s%-15.1f%-10.1f%-40s\n",
                bankTeller.getEmpId(), bankTeller.getStringFullName(),
                bankTeller.getAddress(), simpleDateFormat.format(bankTeller.getDob()),
                bankTeller.getPhoneNumber(), bankTeller.getDuty(),
                bankTeller.getExperience(), bankTeller.getWorkingDay(),
                bankTeller.getLanguage());
    }

    private static void showHeaderBankTeller() {
        showMessage("DANH SÁCH GIAO DỊCH VIÊN NGÂN HÀNG");
        System.out.printf("%-10s%-25s%-40s%-15s%-15s%-25s%-15s%-10s%-40s\n", "Mã NV",
                "Họ và tên", "Địa chỉ", "Ngày sinh", "SĐT", "Nhiệm vụ",
                "Kinh nghiệm", "Ngày làm", "Các ngôn ngữ thành thạo");
    }

    // super(employee.getEmpId(), employee.getStringFullName(),
    // employee.getAddress(), employee.getDob(), employee.getPhoneNumber(),
    // employee.getDuty(), employee.getStacticSalary(),
    // employee.getExperience(), employee.getWorkingDay(),
    // employee.getBonus(), employee.getTotalSalary());

    // super(employee.getEmpId(), employee.getStringFullName(),
    // employee.getAddress(), employee.getDob(), employee.getPhoneNumber(),
    // employee.getDuty()
    // employee.getExperience(), employee.getWorkingDay(),

    private static void showHeaderAccountant() {
        showMessage("DANH SÁCH NHÂN VIÊN KẾ TOÁN");
        System.out.printf("%-10s%-25s%-40s%-15s%-15s%-25s%-15s%-10s\n", "Mã NV",
                "Họ và tên", "Địa chỉ", "Ngày sinh", "SĐT", "Nhiệm vụ",
                "Kinh nghiệm", "Ngày làm");
    }

    private static void showAccountant(Accountant accountant, SimpleDateFormat simpleDateFormat) {
        System.out.printf("%-10s%-25s%-40s%-15s%-15s%-25s%-15.1f%-10.1f\n",
                accountant.getEmpId(), accountant.getStringFullName(),
                accountant.getAddress(), simpleDateFormat.format(accountant.getDob()),
                accountant.getPhoneNumber(), accountant.getDuty(),
                accountant.getExperience(), accountant.getWorkingDay());
    }

    private static Guard createGuard(Scanner input) {
        Accountant accountant = createAccountant(input);
        System.out.println("Nhập khu vực sẽ trực: ");
        var areaParking = input.nextLine();
        return new Guard(accountant, areaParking);
    }

    private static SupportClient createSupportClient(Scanner input) {
        Accountant accountant = createAccountant(input);
        System.out.println("Nhập dịch vụ hỗ trợ: ");
        var service = input.nextLine();
        return new SupportClient(accountant, service);
    }

    private static Manager createManager(Scanner input) {
        Accountant accountant = createAccountant(input);
        var fomart = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(fomart);
        Date startDate = null;
        System.out.println("Ngày bắt đầu nhiệm kì(dd/MM/yyyy): ");
        try {
            startDate = simpleDateFormat.parse(input.nextLine());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Date endDate;
        System.out.println("Ngày kết thúc nhiệm kì(dd/MM/yyyy): ");
        try {
            endDate = simpleDateFormat.parse(input.nextLine());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Nhập khu vực quản bá: ");
        var area = input.nextLine();
        return new Manager(accountant, startDate, endDate, area);
    }

    private static BankTeller createBankTeller(Scanner input) {
        Accountant accountant = createAccountant(input);
        System.out.println("Nhập các ngôn ngữ thành thạo: ");
        var language = input.nextLine();
        return new BankTeller(accountant, language);
    }

    // Hiển thị menu1
    private static void displayMenu1() {
        System.out.println("\n----LỰA CHỌN----");
        System.out.println("0.Thoat chuong trinh");
        System.out.println("1.Bộ phận nhân sự");
        System.out.println("2.Bộ phận khách hàng");
        System.out.println("3.Mảng dịch vụ");
        System.out.println("---Xin mời lựa chọn:");
    }

    // Hiển thị menu2
    private static void displayMenu2() {
        System.out.println("\n----LỰA CHỌN----");
        System.out.println("0.Thoát chương trình");
        System.out.println("1.Thêm mới 1 nhân sự vào danh sách");
        System.out.println("2.Hiển thị danh sách toàn bộ nhân sự hiện có");
        System.out.println("3.Tìm nhân sự theo mã nhân sự");
        System.out.println("4.Xóa nhân sự theo mã nhân sự");
        System.out.println("5.Cập nhập số ngày làm việc của 1 nhân sự");
        System.out.println("6.Hiển thị bảng lương của các nhân sự");
        System.out.println("7.Coi nhiệm vụ của các nhân sự");
        System.out.println("8.Tìm quản lý theo nhiệm kỳ");
        System.out.println("9.Lưu trữ danh sách nhân sự vào File");
        System.out.println("---Xin mời lựa chọn: ");
    }

    // Hiển thị menu3
    private static void displayMenu3() {
        System.out.println("\n----LỰA CHỌN----");
        System.out.println("0.Thoát chương trình");
        System.out.println("1.Thêm mới 1 quản lý");
        System.out.println("2.Thêm mới 1 giao dịch viên");
        System.out.println("3.Thêm mới 1 kế toán");
        System.out.println("4.Thêm mới 1 nhân viên hỗ trợ khách hàng");
        System.out.println("5.Thêm mới 1 bảo vệ");
        System.out.println("---Xin mời lựa chọn:");
    }

    private static Accountant createAccountant(Scanner input) {
        System.out.println("Nhập họ và tên: ");
        var fullName = input.nextLine();
        System.out.println("Nhập địa chỉ: ");
        var address = input.nextLine();
        System.out.println("Nhập ngày sinh dd/MM/yyyy, ví dụ 26/10/2022: ");
        var format = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date dob = null;
        try {
            dob = simpleDateFormat.parse(input.nextLine());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("Nhập số điện thoại: ");
        var phoneNumber = input.nextLine();
        System.out.println("Nhập chức vụ: ");
        var duty = input.nextLine();
        System.out.println("Nhập lương cứng: ");
        var stacticSalary = Float.parseFloat(input.nextLine());
        System.out.println("Nhập số năm kinh nghiệm: ");
        var experience = Float.parseFloat(input.nextLine());
        System.out.println("Nhập số ngày làm việc: ");
        var workingDay = Float.parseFloat(input.nextLine());
        return new Accountant(null, fullName, address, dob, phoneNumber,
                duty, stacticSalary, experience, workingDay, 0, 0);
    }

    private static void showMessage(String msg) {
        System.out.println("\n\t\t\t==>" + msg + "<==");
    }

    // -------------------HAM CUA TRUC----------------------------------------------------
    private static void trucmenu() {
        System.out.println("--LUA CHON--");
        System.out.println("0.Thoat chuong trinh");
        System.out.println("1.Them khach hang");
        System.out.println("2.Huy khach hang");
        System.out.println("3.Xuat danh sach khach hang");
        System.out.println("4.Cap nhat thong tin khach hang");
        System.out.println("5.Tim kiem thong tin khach hang");
        System.out.println("---Xin mời lựa chọn:");
    }

    private static void trucmenu4 (){
        System.out.println("--LUA CHON THONG TIN CAN CAP NHAT--");
        System.out.println("0.So CCCD");
        System.out.println("1.Ho ten khach hang");
        System.out.println("2.Ngay thang nam sinh");
        System.out.println("3.Dia chi");
        System.out.println("4.So dien thoai");
        System.out.println("5.Email");
        System.out.println("---Xin mời lựa chọn:");
    }

    private static void readfile_clientList (ArrayList <Client> dskh){
        String tmp = "";
        try {
            FileReader f = new FileReader("E:\\tài liệu CNTT\\JAVA\\PROJECTS\\danh sach khach hang");
            BufferedReader b = new BufferedReader(f);
            while (true){
                if (tmp == null){
                    break;
                }
                tmp = b.readLine();
                String[] word = tmp.split(" - ");
                Client newclient = new Client(word[0], word[1], word[2], word[3], word[4], word[5]);
                dskh.add(newclient);
            }
            b.close();
            f.close();
        } catch (Exception e) {
        }
    }

    public static void writefile_clientList (ArrayList <Client> dskh){
        try {
            FileWriter f = new FileWriter("E:\\tài liệu CNTT\\JAVA\\PROJECTS\\danh sach khach hang");
            BufferedWriter b = new BufferedWriter(f);
            for (Client x : dskh){
                b.write(x.tostring());
                b.newLine();
            }
            b.close();
            f.close();
        } catch (Exception e) {
        }
    }

    public static void xuat_ds_kh (ArrayList <Client> dskh){
        System.out.printf("%-15s %-25s %-65s %-15s %-15s", "So CCCD", "Ho ten", "Dia chi", "So dien thoai", "Email");
        System.out.println();

        for (Client x : dskh){
            System.out.printf("%-15s %-25s %-65s %-15s %-15s", x.getIdcard(), x.getfullname(), x.getResidence(), x.getPhonenumber(), x.getEmail());
            System.out.println();
        } 
    }

    // --------------------HAM CUA DAT-----------------------------------------------
    private static void datmenu1() {
        System.out.println("--LUA CHON--");
        System.out.println("0.Thoat chuong trinh");
        System.out.println("1.Chuyen khoan");
        System.out.println("2.Rut tien");
        System.out.println("3.Kiem tra so du");
        System.out.println("4.Nap tien");
        System.out.println("5.Kiem tra tai khoan");
        System.out.println("---Xin mời lựa chọn:");
    }
}
