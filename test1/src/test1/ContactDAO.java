/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test1;

import java.util.Locale;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author ASUS
 */
public class ContactDAO {
    Connection conn = null;
    PreparedStatement prepStatement= null;
    Statement statement = null;
    ResultSet resultSet = null;
 
    public ContactDAO() {
        try {
            conn = new ConnectionFactory().getConn();
            statement = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    // Methods to add new custoemr
    public void addContactDAO(ContactDTO ContactDTO) {
        try {
            String query = "SELECT * FROM Contacts WHERE name='"
                    +ContactDTO.getName()
                    + "' AND date='"
                    +ContactDTO.getDate()
                    + "' AND location='"
                    +ContactDTO.getLocation()
                    + "' AND sim='"
                    +ContactDTO.getSim()
                    + "' AND homenetwork='"
                    +ContactDTO.getHomeNetWork()
                    + "' AND phone='"
                    +ContactDTO.getPhone()
                    + "'";
            resultSet = statement.executeQuery(query);
            if (resultSet.next())
                JOptionPane.showMessageDialog(null, "Contact already exists.");
            else
                addFunction(ContactDTO);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addFunction(ContactDTO ContactDTO) {
        try {
            String query = "INSERT INTO Contacts VALUES(?,?,?,?,?,?)";
            prepStatement = conn.prepareStatement(query);
            prepStatement.setString(1, ContactDTO.getName());
            prepStatement.setString(2, ContactDTO.getDate());
            prepStatement.setString(3, ContactDTO.getLocation());
            prepStatement.setString(4, ContactDTO.getSim());
            prepStatement.setString(5, ContactDTO.getHomeNetWork());
            prepStatement.setString(6, ContactDTO.getPhone());
            prepStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "New Contact has been added.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
    }
 
    // Method to edit existing Contact details
public void editContactDAO(ContactDTO contactDTO) {
    try {
        String query = "UPDATE Contacts SET date=?, location=?, sim=?, homenetwork=?, phone=? WHERE name=?";
        prepStatement = conn.prepareStatement(query);
        prepStatement.setString(1, contactDTO.getDate());
        prepStatement.setString(2, contactDTO.getLocation());
        prepStatement.setString(3, contactDTO.getSim());
        prepStatement.setString(4, contactDTO.getHomeNetWork());
        prepStatement.setString(5, contactDTO.getPhone());
        prepStatement.setString(6, contactDTO.getName());  // Matched with the WHERE clause
        prepStatement.executeUpdate();
        JOptionPane.showMessageDialog(null, "Contact details have been updated.");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

 
    // Method to delete existing Contact
    public void deleteContactDAO(String custCode) {
        try {
            String query = "DELETE FROM Contacts WHERE name='" +custCode+ "'";
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Contact removed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        public void deleteContactDAOV2(String custCode) {
        try {
            String query = "DELETE FROM Contacts WHERE name='" +custCode+ "'";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    // Method to retrieve data set to be displayed
    public ResultSet getQueryResult() {
        try {
            String query = "SELECT name,date,location,sim,homenetwork,phone FROM Contacts";
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    public ResultSet getQueryResultV2(String nameArea) {
        try {
            String query = "SELECT name,date,location,sim,homenetwork,phone FROM Contacts WHERE location='"+nameArea+"'";
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
 
    // Method to retrieve search data
    public ResultSet getContactSearch(String text) {
    try {
        String query = "SELECT name, date, location, sim, homenetwork, phone FROM Contacts " +
                "WHERE name LIKE '%" + text + "%' OR date LIKE '%" + text + "%' OR " +
                "location LIKE '%" + text + "%' OR sim LIKE '%" + text + "%' OR " +
                "homenetwork LIKE '%" + text + "%' OR phone LIKE '%" + text + "%'";
        resultSet = statement.executeQuery(query);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return resultSet;
}

    // Method to display data set in tabular form
    public DefaultTableModel buildTableModel(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        Vector<String> columnNames = new Vector<String>();
        int colCount = metaData.getColumnCount();
 
        for (int col=1; col <= colCount; col++){
            columnNames.add(metaData.getColumnName(col).toUpperCase(Locale.ROOT));
        }
 
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (resultSet.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int col=1; col<=colCount; col++) {
                vector.add(resultSet.getObject(col));
            }
            data.add(vector);
        }
        return new DefaultTableModel(data, columnNames);
    }
 
}