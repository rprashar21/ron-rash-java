package designpatterns.creational.singleton.solid.srp.after;

public class Employee {

    private String employeeId;
    private String employeeName;
    private String employeeAddress;


    public void save()
    {
        // save the employee
        new EmployeeRepository().save(this);
    }

    public void calulateEmployeeTax()
    {
        // cal tax of an employee
        EmployeeTaxCalculator.calculateTax(this);
    }

    // now every class has only one reason to change

    // 1. when employee attributes change  --> changes are made in this class
    // 2. when something in tax slab changes --> changes are made in taxCalulator class
    // 3. when save criteria changes  --> changes are made in EmployeeRepository class

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
