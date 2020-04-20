package tgrabins.stream.emp;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class EmployeeFinder {

    Map<Sex, List<Employee>> splitBySex(List<Employee> employees) {
        return employees.stream().collect(groupingBy(Employee::getSex));
    }

    Map<Sex, Map<String, List<Employee>>> bySexAndDepartmnet(List<Employee> employees) {
        return employees.stream().collect(groupingBy(Employee::getSex, groupingBy(Employee::getDepartment, Collectors.toList())));
    }

    Map<Sex, Map<String, Employee>> bySexAndDepartmnetWithMaxSalary(List<Employee> employees) {
        return employees.stream().collect(
                Collectors.groupingBy(Employee::getSex,
                        Collectors.groupingBy(Employee::getDepartment,
                                Collectors.collectingAndThen(
                                        Collectors.maxBy(Comparator.comparing(Employee::getSalary)), Optional::get ))));
    }



}
