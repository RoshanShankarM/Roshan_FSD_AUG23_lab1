package com.lab1;
import java.security.SecureRandom;
import java.util.Scanner;

class Employee {
    private String firstName;
    private String lastName;

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}

class CredentialService {
    private static final String COMPANY = "ShanCompany.com";

    public String generateEmailAddress(Employee employee, String department) {
        return employee.getFirstName().toLowerCase() + employee.getLastName().toLowerCase() +
                "@" + department.toLowerCase() + "." + COMPANY;
    }

    public String generatePassword() {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        String numbers = "0123456789";
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String specialCharacters = "!@#$%^&*()-_=+";

        String allCharacters =   upperCase +numbers + lowerCase + specialCharacters;

        for (int i = 0; i < 8; i++) {
            int randomIndex = random.nextInt(allCharacters.length());
            password.append(allCharacters.charAt(randomIndex));
        }

        return password.toString();
    }

    public void showCredentials(Employee employee, String department) {
        String email = generateEmailAddress(employee, department);
        String password = generatePassword();

        System.out.println("Dear " + employee.getFirstName() + employee.getLastName()+ ", your generated credentials are as follows:");
        System.out.println("Email ---> " + email);
        System.out.println("Password ---> " + password);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter first name: ");
        String firstName = scanner.next();

        System.out.print("Enter last name: ");
        String lastName = scanner.next();

        Employee employee = new Employee(firstName, lastName);

        System.out.print("Enter department (Technical, Admin, Human Resource, Legal): ");
        String department = scanner.next();

        CredentialService credentialService = new CredentialService();

        credentialService.showCredentials(employee, department);

        scanner.close();
    }
}
