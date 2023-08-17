package model;

// Represents a medication with a name a recommended dosage and any additional instructions
public class Medication {

    String name;
    String dosage;
    String instructions;

    //EFFECTS - Initializes the medication with given values
    // MODIFIES - this
    public Medication(String name, String dosage, String instructions) {
        this.name = name;
        this.dosage = dosage;
        this.instructions = instructions;
    }

    public String getName() {
        return name;
    }

    public String getDosage() {
        return dosage;
    }

    public String getInstructions() {
        return instructions;
    }

}
