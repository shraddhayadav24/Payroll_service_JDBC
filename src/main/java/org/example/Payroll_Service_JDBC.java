package org.example;

import java.sql.*;

public class Payroll_Service_JDBC {
   public static  void main (String args[]){

   String url="jdbc:mysql://localhost:3306/payroll_service";
   String username="root";
   String password="root";
   String query = "select * from employee_payroll";
   String UpdateQuery = "update employee_payroll set salary = ? where name = ?";

 {

      try {
         Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection=DriverManager.getConnection(url,username,password);




          PreparedStatement preparedStatement = connection.prepareStatement(UpdateQuery);
          preparedStatement.setDouble(1,30000.0);
          preparedStatement.setString(2,"Shardul");
          preparedStatement.executeUpdate();
          System.out.println("Salary is updated");



            ResultSet resultSet =  preparedStatement.executeQuery(query);

         while(resultSet.next()) {
             int id =  resultSet.getInt("id");
             String name= resultSet.getString("name");
             double salary = resultSet.getDouble("salary");
             String start_date = resultSet.getString("start");
             String gender = resultSet.getString("gender");
             String phone_no= resultSet.getString("phone");
             String address = resultSet.getString("address");
             String department = resultSet.getString("department");
             int basic_pay = resultSet.getInt("Basic_pay");
             int deduction = resultSet.getInt("Deduction");
             int taxable_pay = resultSet.getInt("Taxable_pay");
             int income_tax = resultSet.getInt("Income_tax");
             int net_pay = resultSet.getInt("Net_pay");

              System.out.println(id + " " + name + " " + salary + " " + start_date + " " + gender + " " + phone_no + " " +
                      address + " " + department + " " + basic_pay + " " + deduction + " " + taxable_pay + " " +
                      income_tax + " " + net_pay);

              System.out.println();

          }
          connection.close();

      } catch (ClassNotFoundException e) {
          e.printStackTrace();
      } catch (SQLException e) {
          System.out.println("connection failed");
          e.printStackTrace();
      }

     //  return connection;
   }

   }
}