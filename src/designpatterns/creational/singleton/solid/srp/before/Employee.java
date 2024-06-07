package designpatterns.creational.singleton.solid.srp.before;

public class Employee {

    private String employeeId;
    private String employeeName;
    private String employeeAddress;


    public void save()
    {
        // save the employee
        // jdc postgres
        // mongd
    }

    public void calulateEmployeeTax()
    {
        // cal tax of an employee
    }

    // now here we have three reasons to change

    // 1. when employee attributes change
    // 2. when something in tax slab changes
    // 3. when save criteria changes

    public Employee(final String employeeId, final String employeeName, final String employeeAddress) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeAddress = employeeAddress;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(final String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(final String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(final String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }
}
