package org.example.CarComponents;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Car {
    Scanner sc = new Scanner(System.in);

    //Componentes do carro
    private int userID;
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
    public Car(int userID, Engine engine, Brakes brakes, Tires tires, Chassis chassis,
               Suspension suspension, BodyPaint bodyPaint, String carName) {
        this.userID = userID;
        this.engine = engine;
        this.brakes = brakes;
        this.tires = tires;
        this.chassis = chassis;
        this.suspension = suspension;
        this.bodyPaint = bodyPaint;
        this.carName = carName;
    }

    //Calcular o valor dos atributos
    public void setStats() {
        this.cost = this.engine.setCost() + this.brakes.setCost() + this.tires.setCost() + this.chassis.setCost() + this.suspension.setCost() + this.bodyPaint.setCost();
        this.consumption = this.engine.setConsumption();
        this.weight = this.engine.setWeight() + this.brakes.setWeight() + this.tires.setWeight() + this.chassis.setWeight() + this.suspension.setWeight();
        this.torque = this.engine.setTorque();
        this.power = -0.1466 * Math.pow(this.engine.setTorque(), 2) + 22.86 * this.engine.setTorque() - 93.5;
        this.maxSpeed = 230 * Math.exp(0.0008347 * (this.power - 80));
        this.handling = (this.tires.setHandling() / 3 + this.engine.setHandling() + this.suspension.setHandling() + 2) - this.weight / 100;
        this.acceleration = 21 - (((this.weight * 27.78) / (this.power * 35)) * this.tires.setDiameter() * this.engine.setTractionCoef());
        this.brakesPower = this.tires.setHandling() + this.brakes.setBrake() + 2 - this.weight / 100;
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
        String sqlInsert = "INSERT INTO Cars(user_id, brakes, tires, chassis, suspension, name) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stm = null;

        try{
            conn.setAutoCommit(false);
            stm = conn.prepareStatement(sqlInsert);
            stm.setInt(1, userID);
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

    public String getBrakes() {
        return brakes.getBrakeType();
    }

    public String getTires() {
        return tires.getTireType();
    }

    public String getChassis() {
        return chassis.getChassisModel();
    }

    public String getSuspension() {
        return suspension.getSuspensionType();
    }

    public String getCarName() {
        return this.carName;
    }

    public String getEngineType() {
        return engine.getEngineType();
    }

    public int getCylinderAmount() {
        return engine.getCylindersAmmount();
    }

    public double getCylinders() {
        return engine.getCylinders();
    }

    public String getAspiration() {
        return engine.getAspiration();
    }

    public String getFuel() {
        return engine.getFuel();
    }

    public String getMaterial() {
        return engine.getEngineMaterial();
    }

    public String getTraction() {
        return engine.getTraction();
    }

    public double getCost() {
        return cost;
    }

    public double getConsumption() {
        return consumption;
    }

    public double getWeight() {
        return weight;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public double getTorque() {
        return torque;
    }

    public double getPower() {
        return power;
    }

    public double getHandling() {
        return handling;
    }

    public double getBrakesPower() {
        return brakesPower;
    }
}