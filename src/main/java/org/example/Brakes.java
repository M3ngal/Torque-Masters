package org.example;

public class Brakes {
    private String brakeType;

    Brakes(String brakeType) {
        this.brakeType = brakeType;
    }

    public String getBrakeType() {
        return brakeType;
    }

    public void setBrakeType(String brakeType) {
        this.brakeType = brakeType;
    }

    public double setWeight() {
        switch (brakeType) {
            case "popular":
                return 50;
                
            case "esportivo":
                return 40;
            
            case "corrida":
                return 30;
            
            case "ceramica":
                return 20;
               
            default:
                return 0;
        }
    }

    public double setCost() {
        switch (brakeType) {
            case "popular":
                return 500;
                
            case "esportivo":
                return 1000;
            
            case "corrida":
                return 2000;
            
            case "ceramica":
                return 3000;
               
            default:
                return 0;
        }
    }

    public double setBrake() {
        switch (brakeType) {
            case "popular":
                return 1;
                
            case "esportivo":
                return 1.5;
            

            case "corrida":
                return 2;
            

            case "ceramica":
                return 2;
               
            default:
                return 0;
        }
    }
    
    public String toString() {
        return String.format("\n2 - Brakes: %s", brakeType);
    }
}
