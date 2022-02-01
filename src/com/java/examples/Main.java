package com.java.examples;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<Employee> employeeData = EmployeeDataFactory.getEmployeeData();

        System.out.println("-------FIND THE NUMBER OF MALES AND FEMALES --------");
        Map<String, Long> collect = employeeData.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
        System.out.println(collect);
        System.out.println("-------FIND THE Unique NUMBER OF DEPARTMENTS --------");
        employeeData.stream().map(Employee::getDepartment).distinct().forEach(s -> System.out.println(s));
        System.out.println("-------FIND THE Average Age of Male and Female --------");
        Map<String, Double> averageAge = employeeData.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingDouble(Employee::getAge)));
        System.out.println(averageAge);
        System.out.println("-------FIND THE DETAILS OF HIGHEST PAID EMPLOYEE --------");
        Optional<Employee> collect1 = employeeData.stream().collect(Collectors.maxBy(Comparator.comparing(Employee::getSalary)));
        System.out.println(collect1.get());
        System.out.println("-------FIND THE EMPLOYEE WHO JOINED OF HIGHEST PAID EMPLOYEE --------");
        employeeData.stream().filter(employee -> employee.yearOfJoining>2015).collect(Collectors.toList()).forEach(employee -> System.out.print(employee));
        System.out.println("-------COUNT NUMBER OF EMPLOYEE IN EACH DEPARTMENT --------");
        Map<String, Long> collect2 = employeeData.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
        System.out.println(collect2);
        System.out.println("----WHAT IS AVERAGE SALARY OF EACH DEPARTMENT----");
        Map<String, Double> collect3 = employeeData.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
        System.out.println(collect3);
        System.out.println("----Get the details of youngest male employee in the product development department?-----");
        Optional<Employee> collect4 = employeeData.stream().
                filter(employee -> employee.department.equalsIgnoreCase("Product Development") &&
                        employee.getGender().equalsIgnoreCase("male")).min(Comparator.comparingDouble(Employee::getAge));
        System.out.println(collect4.get());
        System.out.println("-----Who has the most working experience in the organization?--------");
        Optional<Employee> first = employeeData.stream().sorted(Comparator.comparing(Employee::getYearOfJoining)).findFirst();
        System.out.println(first);

    }


}