package org.example.CarComponents;

public class Suspension {
    private String suspensionType;

    public Suspension(String suspensionType) {
        this.suspensionType = suspensionType;
    }

    public String getSuspensionType(){
        return suspensionType;
    }

    public void setSuspensionType(String suspensionType) {
        this.suspensionType = suspensionType;
    }

    public double setWeight() {
        switch (suspensionType) {
            case "popular":
                return 60;
                
            case "esportivo":
                return 40;

            case "corrida":
                return 40;

            case "rally":
                return 80;
               
            default:
                return 0;
        }
    }

    public double setBrake() {
        switch (suspensionType) {
            case "popular":
                return 0.9;
                
            case "esportivo":
                return 1;

            case "corrida":
                return 1.1;

            case "rally":
                return 0.8;
               
            default:
                return 0;
        }
    }

    public double setCost() {
        switch (suspensionType) {
            case "popular":
                return 2000;
                
            case "esportivo":
                return 3000;

            case "corrida":
                return 5000;

            case "rally":
                return 3000;
               
            default:
                return 0;
        }
    }

    //INCOMPLETO
    public double setHandling() {
        switch (suspensionType) {
            case "popular":
                return 0.6;
                
            case "esportivo":
                return 0.8;

            case "corrida":
                return 1;

            case "rally":
                return 0.8;
               
            default:
                return 0;
        }
    }

    //INCOMPLETO
    public double setAcceleration() {
        switch (suspensionType) {
            case "popular":
                return 0.9;
                
            case "esportivo":
                return 1.05;

            case "corrida":
                return 1.15;

            case "rally":
                return 0.85;
               
            default:
                return 0;
        }
    }

    public String toString() {
        return String.format("\n5 - Suspension: %s", suspensionType);
    }
}
