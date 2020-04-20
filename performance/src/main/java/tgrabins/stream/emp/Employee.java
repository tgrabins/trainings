package tgrabins.stream.emp;

public class Employee {

    private final Sex sex;

    private final String department;

    private final int salary;

    public Employee(Sex sex, String department, int salary) {
        this.sex = sex;
        this.department = department;
        this.salary = salary;
    }

    public Sex getSex() {
        return sex;
    }

    public String getDepartment() {
        return department;
    }

    public int getSalary() {
        return salary;
    }
}
