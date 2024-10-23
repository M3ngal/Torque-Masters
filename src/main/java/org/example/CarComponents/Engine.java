package org.example.CarComponents;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Engine {
    private int userID;
    private String engineType;
    private int cylindersAmmount;
    private double cylinders;
    private String aspiration;
    private String fuel;
    private String engineMaterial;
    private String traction;

    public Engine(int userID, String engineType, int cylindersAmmount, double cylinders, String aspiration, String fuel, String engineMaterial, String traction) {
        //passagem de valores para os atributos do construtores
        this.userID = userID;
        this.engineType = engineType;
        this.cylindersAmmount = cylindersAmmount;
        this.cylinders = cylinders;
        this.aspiration = aspiration;
        this.fuel = fuel;
        this.engineMaterial = engineMaterial;
        this.traction = traction;
    }

    public String getEngineType() {
        return engineType;
    }

    public int getCylindersAmmount() {
        return cylindersAmmount;
    }

    public double getCylinders() {
        return cylinders;
    }

    public String getAspiration() {
        return aspiration;
    }

    public String getFuel() {
        return fuel;
    }

    public String getEngineMaterial() {
        return engineMaterial;
    }

    public String getTraction() {
        return traction;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public void setCylindersAmmount(int cylindersAmmount) {
        this.cylindersAmmount = cylindersAmmount;
    }

    public void setCylinders(double cylinders) {
        this.cylinders = cylinders;
    }

    public void setAspiration(String aspiration) {
        this.aspiration = aspiration;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public void setEngineMaterial(String engineMaterial) {
        this.engineMaterial = engineMaterial;
    }

    public void setTraction(String traction) {
        this.traction = traction;
    }

    public double setRadius() {
        switch (engineType) {
            case "em linha":
                return 1.2;

            case "boxer":
                return 1.3;

            case "V":
                return 1.4;
        
            default:
                return 0;
        }
    }

    public double setWeight() {
        double carWeight = 0;

        switch (aspiration) {
            case "turbo compressor":
                carWeight += 13;
                break;
            
            case "super compressor":
                carWeight += 22;
                break;
        
            default:
                break;
        }

        switch (engineMaterial) {
            case "ferro fundido":
                carWeight += 150 + (5 * cylindersAmmount * cylinders);
                break;

            case "liga de aluminio":
                carWeight += 130 + (3 * cylindersAmmount * cylinders);
                break;

            case "liga de titanio":
                carWeight += 110 + (2 * cylindersAmmount * cylinders);
        
            default:
                break;
        }

        switch (traction) {
            case "traseira":
                carWeight += 50;
                break;

            case "dianteira":
                carWeight += 50;
                break;
            
            case "integral":
                carWeight += 80;
                break;
        
            default:
                break;
        }

        return carWeight;
    }

    public double setHorsePower() {
        double carHorsePower = 0;

        switch (aspiration) {
            case "turbo compressor":
                carHorsePower += 15 * cylindersAmmount;
                break;
            
            case "super compressor":
                carHorsePower += 25 * cylindersAmmount;
                break;
        
            default:
                break;
        }

        switch (fuel) {
            case "gasolina":
                carHorsePower += 10 * cylindersAmmount;
                break;

            case "diesel":
                carHorsePower += 8 * cylindersAmmount;
                break;
        
            default:
                break;
        }

        switch (engineMaterial) {
            case "ferro fundido":
                carHorsePower += 10 * cylindersAmmount;
                break;

            case "liga de aluminio":
                carHorsePower += 11 * cylindersAmmount;
                break;

            case "liga de titanio":
                carHorsePower += 12 * cylindersAmmount;
        
            default:
                break;
        }

        return (carHorsePower + (cylinders * cylindersAmmount * 10));
    }

    public double setHandling() {
        switch (traction) {
            case "traseira":
                return 0.7;

            case "dianteira":
                return 0.85;
            
            case "integral":
                return 1;
        
            default:
                return 0;
        }
    }

    public double setConsumption() {
        double carConsumption = 0;

        switch (fuel) {
            case "gasolina":
                carConsumption += 1;
                break;

            case "diesel":
                carConsumption += 0.9;
                break;
        
            default:
                break;
        }

        switch (engineMaterial) {
            case "ferro fundido":
                carConsumption += 1;
                break;

            case "liga de aluminio":
                carConsumption += 0.95;
                break;

            case "liga de titanio":
                carConsumption += 0.9;
        
            default:
                break;
        }

        return (20 / (carConsumption / 2)) - (setWeight() / 1000) - ((cylinders * cylindersAmmount) / 4);
    }

    public double setCost() {
        double carCost = 0;

        switch (aspiration) {
            case "turbo compressor":
                carCost += 5000;
                break;
            
            case "super compressor":
                carCost += 8000;
                break;
        
            default:
                break;
        }

        switch (engineMaterial) {
            case "ferro fundido":
                carCost += 10000 + 10 * (150 + (5 * cylindersAmmount * cylinders));
                break;

            case "liga de aluminio":
                carCost += 15000 + 15 * (130 + (3 * cylindersAmmount * cylinders));
                break;

            case "liga de titanio":
                carCost += 25000 + 25 * (110 + (2 * cylindersAmmount * cylinders));
        
            default:
                break;
        }

        switch (traction) {
            case "traseira":
                carCost += 8000;
                break;

            case "dianteira":
                carCost += 8000;
                break;
            
            case "integral":
                carCost += 15000;
                break;
        
            default:
                break;
        }

        return carCost;
    }

    public double setTractionCoef() {
        switch (traction) {
            case "traseira":
                return 2.5;

            case "dianteira":
                return 2;
            
            case "integral":
                return 4;
        
            default:
                return 0;
        }
    }

    public String toString() {
        return String.format("Engine -> Type: %s; Cylinder Ammount: %d; " +
        "Cylinders: %.1f; Aspiration: %s; Fuel: %s; Material: %s; Traction: %s",
        engineType, cylindersAmmount, cylinders, aspiration, fuel, engineMaterial, traction);
    }

    public void incluir(Connection conn){
        String sqlInsert = "INSERT INTO Engs(user_id, enginetype, cylamt, cyl, aspiration, fuel, enginematerial, traction) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stm = null;

        try{
            conn.setAutoCommit(false);
            stm = conn.prepareStatement(sqlInsert);
            stm.setInt(1, userID);
            stm.setString(2, getEngineType());
            stm.setInt(3, getCylindersAmmount());
            stm.setDouble(4, getCylinders());
            stm.setString(5, getAspiration());
            stm.setString(6, getFuel());
            stm.setString(7, getEngineMaterial());
            stm.setString(8, getTraction());
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

    public void carregar(Connection conn, int id) {
        String sqlSelect = "SELECT enginetype, cylamt, cyl, aspiration, fuel, enginematerial, traction FROM Engs WHERE id = ?";
        PreparedStatement stm = null;
        ResultSet rs = null;

        try{
            stm = conn.prepareStatement(sqlSelect);
            stm.setInt(0, id);
            rs = stm.executeQuery();
            if(rs.next()){
                setEngineType(rs.getString(1));
                setCylindersAmmount(rs.getInt(2));
                setCylinders(rs.getDouble(3));
                setAspiration(rs.getString(4));
                setFuel(rs.getString(5));
                setEngineMaterial(rs.getString(6));
                setTraction(rs.getString(7));
            }
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
            if(rs != null){
                try{
                    rs.close();
                }
                catch (SQLException e1){
                    System.out.print(e1.getStackTrace());
                }
            }
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
