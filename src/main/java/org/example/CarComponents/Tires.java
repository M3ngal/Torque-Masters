package org.example.CarComponents;

public class Tires {
    private String tireType;

    public Tires(String tireType) {
        this.tireType = tireType;
    }

    public String getTireType() {
        return tireType;
    }

    public void setTireType(String tireType) {
        this.tireType = tireType;
    }

    public double setDiameter() {
        switch (tireType) {
            case "popular":
                return 35;
                
            case "esportivo":
                return 45;
            
            case "corrida":
                return 60;
            
            case "off-road":    
                return 50;
               
            default:
                return 0;
        }
    }

    public double setWeight() {
        switch (tireType) {
            case "popular":
                return 24;
                
            case "esportivo":
                return 32;
            
            case "corrida":
                return 32;
            
            case "off-road":    
                return 40;
               
            default:
                return 0;
        }
    }

    public double setCost() {
        switch (tireType) {
            case "popular":
                return 4000;
                
            case "esportivo":
                return 8000;
            
            case "corrida":
                return 16000;
            
            case "off-road":
                return 8000;
               
            default:
                return 0;
        }
    }

    //INCOMPLETO
    public double setBrake() {
        switch (tireType) {
            case "popular":
                return 0.5;
                
            case "esportivo":
                return 1;
            
            case "corrida":
                return 1.5;
            
            case "off-road":
                return 2;
               
            default:
                return 0;
        }
    }

    //INCOMPLETO
    public double setHandling() {
        switch (tireType) {
            case "popular":
                return 0.6;
                
            case "esportivo":
                return 0.8;

            case "corrida":
                return 1;
            
            case "off-road":
                return 0.9;
               
            default:
                return 0;
        }
    }

    public String toString() {
        return String.format("\n3 - Tires: %s", tireType);
    }
}
