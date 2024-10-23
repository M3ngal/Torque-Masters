package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Users {
    private String username;
    private String password;

    public Users() {
        this.username = "";
        this.password = "";
    }

    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Users> readUser () {
        String sql = "SELECT * FROM Login";
        List<Users> users = new ArrayList<Users>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rSet = null;

        try {
            conn = Conector.conectar();
            ps = conn.prepareStatement(sql);
            rSet = ps.executeQuery();

            while (rSet.next()) {
                Users user = new Users(rSet.getString("username"), rSet.getString("password"));
                users.add(user);
            }

        } catch (Exception e) {
            System.out.println("Prepared statement error...");
            e.printStackTrace();

        } finally {
            try {
                if (rSet != null)
                    rSet.close();

                if (ps != null)
                    ps.close();

                if (conn != null)
                    conn.close();

            } catch (Exception e) {
                System.out.println("Connections termination error...");
                e.printStackTrace();
            }
        }

        return users;
    }

    public void addUser(Connection conn){
        String sqlInsert = "INSERT INTO Login(username, password) VALUES (?, ?)";
        PreparedStatement stm = null;

        try{
            conn.setAutoCommit(false);
            stm = conn.prepareStatement(sqlInsert);
            stm.setString(1, username);
            stm.setString(2, password);
            stm.execute();
            conn.commit();
        }
        catch(Exception e){
            e.printStackTrace();
            try{
                conn.rollback();
            }
            catch (SQLException e1){
                System.out.print(e1.getStackTrace());
            }
        }
        finally{
            if(stm != null){
                try{
                    stm.close();
                }
                catch (SQLException e1){
                    System.out.print(e1.getStackTrace());
                }
            }
        }
    }
}

