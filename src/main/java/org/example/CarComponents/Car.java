package org.example.CarComponents;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Car {
    Scanner sc = new Scanner(System.in);

    //Componentes do carro
    private Engine engine;
    private Brakes brakes;
    private Tires tires;
    private Chassis chassis;
    private Suspension suspension;
    private BodyPaint bodyPaint;
    private String carName;

    //Atributos do carro
    private double cost;
    private double consumption;
    private double weight;
    private double maxSpeed;
    private double acceleration;
    private double torque;
    private double power;
    private double handling;
    private double brakesPower;

    //Construtor
    public Car(Engine engine, Brakes brakes, Tires tires, Chassis chassis, Suspension suspension, BodyPaint bodyPaint, String carName) {
        this.engine = engine;
        this.brakes = brakes;
        this.tires = tires;
        this.chassis = chassis;
        this.suspension = suspension;
        this.bodyPaint = bodyPaint;
        this.carName = carName;
    }

    public void setBrakes(Brakes brakes) {
        this.brakes = brakes;
    }

    public void setTires(Tires tires) {
        this.tires = tires;
    }

    public void setChassis(Chassis chassis) {
        this.chassis = chassis;
    }

    public void setSuspension(Suspension suspension) {
        this.suspension = suspension;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    //Calcular o valor dos atributos
    public void setStats() {
        cost = engine.setCost() + brakes.setCost() + tires.setCost() + chassis.setCost() + suspension.setCost() + bodyPaint.setCost();
        consumption = engine.setConsumption();
        weight = engine.setWeight() + brakes.setWeight() + tires.setWeight() + chassis.setWeight() + suspension.setWeight();
        power = engine.setHorsePower();
        torque = (((Math.pow(power - 470, 2)) / 800) + power - 200) * (4.2 / 3);
        maxSpeed = (power / torque) * tires.setDiameter() * 8;
        handling = (engine.setHandling() + tires.setHandling() + chassis.setHandling() + suspension.setHandling()) / 4;
        acceleration = engine.setTractionCoef() * (torque / (tires.setDiameter() / 100)) / weight;

        double frictionCoef = (brakes.setBrake() + tires.setBrake() + chassis.setBrake() + suspension.setBrake()) / 4;
        brakesPower = (160 / frictionCoef) / 55.56;
    }

    //Metodo para exibição no terminal
    public String toString() {
        return "------------------------------<"+ carName + ">------------------------------\n" +
                engine.toString() + brakes.toString() + tires.toString() + chassis.toString() + suspension.toString() + bodyPaint.toString() +
                "\n------------------------------Stats------------------------------\n" + 
                String.format("A - Cost: %.0f\nB - Consumption: %.0f\nC - Weight: %.0f\n" + 
                "D - Max Speed: %.0f\nE - Acceleration: %.0f\nF - Handling: %.0f\nG - Brakes: %.0f\nH - Power: %.0f\nI - Torque: %.0f",
                cost, consumption, weight, maxSpeed, acceleration, handling, brakesPower, power, torque);
    }

    public void incluir(Connection conn){
        String sqlInsert = "INSERT INTO Cars(eng_id, brakes, tires, chassis, suspension, name) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stm = null;

        try{
            conn.setAutoCommit(false);
            stm = conn.prepareStatement(sqlInsert);
            stm.setInt(1, engine.getId());
            stm.setString(2, brakes.getBrakeType());
            stm.setString(3, tires.getTireType());
            stm.setString(4, chassis.getChassisModel());
            stm.setString(5, suspension.getSuspensionType());
            stm.setString(6, carName);
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

    public void excluir(Connection conn, int id){
        String sqlDelete = "DELETE FROM Cars WHERE id = ?";
        PreparedStatement stm = null;

        try{
            conn.setAutoCommit(false);
            stm = conn.prepareStatement(sqlDelete);
            stm.setInt(1, id);
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

    public void atualizar(Connection conn, int id){
        String sqlUpdate = "UPDATE Cars SET id_engine = ?, brakes = ?, tires = ?, chassis = ?, suspension = ? WHERE id = ?";
        PreparedStatement stm = null;

        try{
            conn.setAutoCommit(false);
            stm = conn.prepareStatement(sqlUpdate);
            stm.setInt(1, engine.getId());
            stm.setString(2, brakes.getBrakeType());
            stm.setString(3, tires.getTireType());
            stm.setString(4, chassis.getChassisModel());
            stm.setString(5, suspension.getSuspensionType());
            stm.setInt(0, id);
            stm.execute();
            conn.commit();
        }
        catch(Exception e){
            e.printStackTrace();
            try{
                conn.rollback();
            }
            catch(SQLException e1){
                System.out.print(e1.getStackTrace());
            }
        }
        finally{
            if(stm != null){
                try{
                    stm.close();
                }
                catch(SQLException e1){
                    System.out.print(e1.getStackTrace());
                }
            }
        }
    }

    public void carregar(Connection conn, int id) {
        String sqlSelect = "SELECT id_engine, brakes, tires, chassis, suspension FROM Cars WHERE id = ?";
        PreparedStatement stm = null;
        ResultSet rs = null;

        try{
            stm = conn.prepareStatement(sqlSelect);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            if(rs.next()){
                this.brakes.setBrakeType(rs.getString(2));
                this.tires.setTireType(rs.getString(3));
                this.chassis.setChassisModel(rs.getString(4));
                this.suspension.setSuspensionType(rs.getString(5));
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