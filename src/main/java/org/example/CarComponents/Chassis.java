package org.example.CarComponents;

public class Chassis {
    private String chassisModel;

    public Chassis(String chassisModel) {
        this.chassisModel = chassisModel;
    }

    public String getChassisModel() {
        return chassisModel;
    }

    public void setChassisModel(String chassisModel) {
        this.chassisModel = chassisModel;
    }

    public double setCost() {
        switch (chassisModel) {
            case "suv":
                return 45000;
                
            case "sedan":
                return 30000;

            case "esportivo":
                return 70000;

            case "hatchback":
                return 25000;

            case "coupe":
                return 55000;
               
            default:
                return 0;
        }
    }

    public double setWeight() {
        switch (chassisModel) {
            case "suv":
                return 1500;
                
            case "sedan":
                return 1100;

            case "esportivo":
                return 1200;

            case "hatchback":
                return 900;

            case "coupe":
                return 1100;
               
            default:
                return 0;
        }
    }

    public double setHandling() {
        switch (chassisModel) {
            case "suv":
                return 0.4;
                
            case "sedan":
                return 0.75;

            case "esportivo":
                return 1;

            case "hatchback":
                return 0.65;

            case "coupe":
                return 0.9;
               
            default:
                return 0;
        }
    }

    public double setBrake() {
        switch (chassisModel) {
            case "suv":
                return 0.8;
                
            case "sedan":
                return 1;

            case "esportivo":
                return 1.2;

            case "hatchback":
                return 0.9;

            case "coupe":
                return 1.1;
               
            default:
                return 0;
        }
    }

    public String toString() {
        return String.format("\n4 - Chassis Model: %s", chassisModel);
    }
}
