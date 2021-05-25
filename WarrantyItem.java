package Prog.Assignments;
public class WarrantyItem extends Item {

    private int warrantyDuration;// instance variable to store duration of a warranty for an item


    public WarrantyItem(String ItemName, double ActualPrice, int warrantyDuration) {// specific constructor 
        super(ItemName, ActualPrice);// calling the contructor from superclass using inheritance concept
        if (!setWarrantyDuration(warrantyDuration)) {// validating user input
            throw new IllegalArgumentException("Please enter 12, 24, or 36 months for warranty coverage!");
        }
    }

   // Accessors
    public int getWarrantyDuration() {
        return this.warrantyDuration;
    }
   // Mutators
    public boolean setWarrantyDuration(int warrantyDuration) {
        if (warrantyDuration == 12 || warrantyDuration == 24 || warrantyDuration == 36) {
            this.warrantyDuration = warrantyDuration;
            return true;
        }
        return false;
    }
   //Special Purpose method
    public double calcWarranty() {

        double warrantyCost = getWarrantyDuration() * 4;

        return warrantyCost;
    }

    public double subWithWarranty() {

        return super.subWithWarranty() + calcWarranty();

    }

    public String toString() {

        String warrantyReport = super.toString();

        warrantyReport += "\n* Item Warranty - " + getWarrantyDuration() + " months.";

        return warrantyReport;
    }

}