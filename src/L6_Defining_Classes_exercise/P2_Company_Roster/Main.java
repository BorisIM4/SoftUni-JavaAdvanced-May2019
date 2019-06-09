package L6_Defining_Classes_exercise.P2_Company_Roster;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        HashMap<String, Department> departments = new HashMap<>();

        while (n-- > 0) {
            String[] token = scanner.nextLine().split("\\s+");

            String departmentName = token[3];

            Employee emp = new Employee(
                    token[0],
                    Double.parseDouble(token[1]),
                    token[2]
            );

            if (token.length == 5) {
                if (Character.isDigit(token[4].charAt(0))) {
                    emp.setAge(Integer.parseInt(token[4]));

                } else {
                    emp.setEmail(token[4]);
                }
            } else if (token.length == 6) {
                emp.setEmail(token[4]);
                emp.setAge(Integer.parseInt(token[5]));
            }

            if (!departments.containsKey(departmentName)) {
                departments.put(departmentName, new Department());
            }
            departments.get(departmentName).addEmployee(emp);

        }

        Map.Entry<String, Department> highestAvgSalary = departments.entrySet().stream()
                .sorted((f, s) -> {
                    int result = 0;
                    if (s.getValue().getAverageSalary() > f.getValue().getAverageSalary()) {
                        result = 1;
                    } else if (s.getValue().getAverageSalary() < f.getValue().getAverageSalary()) {
                        result = -1;
                    }
                    return result;
                }).findFirst()
                .get();

        System.out.println(String.format("Highest Average Salary: %s", highestAvgSalary.getKey()));
        highestAvgSalary.getValue().getEmployees()
                .stream()
                .sorted((f, s) -> Double.compare(s.getSalary(), f.getSalary()))
                .forEach(employee -> {
                    System.out.println(String.format("%s %.2f %s %d",
                            employee.getName(),
                            employee.getSalary(),
                            employee.getEmail(),
                            employee.getAge()
                    ));
                });

    }
}

