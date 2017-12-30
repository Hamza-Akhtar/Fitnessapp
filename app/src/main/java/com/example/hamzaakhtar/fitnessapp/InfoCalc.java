package com.example.hamzaakhtar.fitnessapp;


public class InfoCalc {
    private User user;
    private int totalCals;
    private int waterIntake;
    private int totalProtien;
    private int totalFat;
    private int totalCarbs;

    public InfoCalc (User user) {
        this.user = user;
    }

    public int findTotalCals() {
        if (user.getGender().equals("M")) {
            totalCals = (int) (10 * (user.getWeight() / 2.2) + 6.25 * user.getHeight() - 5 * user.getAge() + 5);
            totalCals = (int) totalCalsActivity(totalCals, user.getActivityLevel(), user.getGoal());
        } else {
            totalCals = (int) (10 * (user.getWeight() / 2.2) + 6.25 * user.getHeight() - 5 * user.getAge() - 161);
            totalCals = (int) totalCalsActivity(totalCals, user.getActivityLevel(), user.getGoal());
        }
        return totalCals;
    }

    public double totalCalsActivity(int cals, String activityLevel, String goal) {
        double calVal = 0;

        if (activityLevel.equals("low")) {
            calVal = cals * 1.2;
        }
        else if (activityLevel.equals("light")) {
            calVal = cals * 1.375;
        }
        else if (activityLevel.equals("moderate")) {
            calVal = cals * 1.55;
        }
        else if (activityLevel.equals("active")) {
            calVal = cals * 1.725;
        }
        else if (activityLevel.equals("extreme")) {
            calVal = cals * 1.9;
        }

        if (goal.equals("gain")) {
            calVal += 300;
        }
        else if (goal.equals("lose")) {
            calVal -= 500;
        }

        return calVal;
    }


    public int findWaterIntake() {
         waterIntake = findTotalCals() * 1; // 1ml per calorie
         waterIntake = waterIntake / 250; // find value in cups

        return waterIntake;
    }

    public int findTotalProtien() {
        totalProtien = user.getWeight();

        if (user.getGoal().equals("maintain")) {
            totalProtien = (int) (totalProtien * 0.825);
        }
        else if (user.getGoal().equals("lose")) {
            totalProtien = (int) (totalProtien * 1.3);
        }

        return totalProtien;
    }

    public int findTotalFat() {
        totalFat = (int) ((findTotalCals() * 0.25) / 9);

        return totalFat;
    }

    public int findTotalCarbs() {
        totalCarbs = findTotalCals() - ((findTotalFat() * 9) + (findTotalProtien() * 4));
        totalCarbs /= 4;

        return totalCarbs;
    }

}
