package org.example;

import java.sql.*;

public class Payroll_Service_JDBC {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/payroll_service";
        String username = "root";
        String password = "root";
        String query = "SELECT * FROM employee_payroll WHERE start BETWEEN CAST('2023-09-02' AS DATE) AND CURDATE()";

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");


            Connection connection = DriverManager.getConnection(url, username, password);


            Statement statement = connection.createStatement();


            ResultSet resultSet = statement.executeQuery(query);


            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double salary = resultSet.getDouble("salary");
                String start_date = resultSet.getString("start");
                String gender = resultSet.getString("gender");
                String phone_no = resultSet.getString("phone");
                String address = resultSet.getString("address");
                String department = resultSet.getString("department");
                int basic_pay = resultSet.getInt("Basic_pay");
                int deduction = resultSet.getInt("Deduction");
                int taxable_pay = resultSet.getInt("Taxable_pay");
                int income_tax = resultSet.getInt("Income_tax");
                int net_pay = resultSet.getInt("Net_pay");

                System.out.println(id + " " + name + " " + salary + " " + start_date + " " + gender + " " +
                        phone_no + " " + address + " " + department + " " + basic_pay + " " + deduction + " " +
                        taxable_pay + " " + income_tax + " " + net_pay);

                System.out.println();
            }

            // Query to get total salary by gender
            String query2 = "SELECT gender, SUM(salary) AS Total_salary FROM employee_payroll WHERE gender='F' OR gender='M' GROUP BY gender";
            ResultSet resultSet1 = statement.executeQuery(query2);

            // Print total salary by gender
            while (resultSet1.next()) {
                System.out.println("Gender: " + resultSet1.getString("gender"));
                System.out.println("Total Salary: " + resultSet1.getDouble("Total_salary"));
                System.out.println();
            }

            String query3 = "SELECT gender, MAX(salary) AS Max_salary FROM employee_payroll WHERE gender='F' OR gender='M' GROUP BY gender";
            ResultSet resultSet2= statement.executeQuery(query3);
            while (resultSet2.next()){
                System.out.println("Gender: " + resultSet2.getString("gender"));
                System.out.println("Max Slary: " + resultSet2.getDouble("Max_salary"));
                System.out.println();
            }

            String query4 = "SELECT gender, MIN(salary) AS Min_salary FROM employee_payroll WHERE gender='F' OR gender='M' GROUP BY gender";
            ResultSet resultSet3= statement.executeQuery(query4);
            while (resultSet3.next()){
                System.out.println("Gender: " + resultSet3.getString("gender"));
                System.out.println("Min salary: " + resultSet3.getDouble("Min_salary"));
                System.out.println();
            }

            String query5 = "SELECT gender, AVG(salary) AS Average_salary FROM employee_payroll WHERE gender='F' OR gender='M' GROUP BY gender";
            ResultSet resultSet4= statement.executeQuery(query5);
            while (resultSet4.next()){
                System.out.println("Gender: " + resultSet4.getString("gender"));
                System.out.println("Average salary: " + resultSet4.getDouble("Average_salary"));
                System.out.println();
            }

            String query6 = "SELECT gender, COUNT(*) AS Employee_Count FROM employee_payroll WHERE gender='F' OR gender='M' GROUP BY gender";
            ResultSet resultSet5= statement.executeQuery(query6);
            while (resultSet5.next()){
                System.out.println("Gender: " + resultSet5.getString("gender"));
                System.out.println("Employee Count: " + resultSet5.getDouble("Employee_Count"));
                System.out.println();
            }



            // Close resources
            resultSet1.close();
            resultSet2.close();
            resultSet3.close();
            resultSet4.close();
            resultSet5.close();
            resultSet.close();
            statement.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
        }
    }
}
