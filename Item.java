package Prog.Assignments;
public class Item {
   // Instance Variables
    private String ItemName;
    private double ActualPrice;
    private double AmazonPrice;
    private double TargetPrice;
    private double WalmartPrice;

   //Static  Variable
    public static int noOfItems;

    public Item() {// default Constructor
        noOfItems--;
    }
    public Item(String ItemName, double ActualPrice) {// Specific Constructor

        setItemName(ItemName);
        setAcutalPrice(ActualPrice);

        noOfItems++;
    }

    //Accessors
    public String getItemName() {
        return this.ItemName;
    }
    public double getAcutalPrice() {
        return this.ActualPrice;
    }
    public double getAmazonPrice() {
        return this.AmazonPrice;
    }
    public double getTargetPrice() {
        return this.TargetPrice;
    }
    public double getWalmartPrice() {
        return this.WalmartPrice;
    }

    public static int getNoOfItems() {
        return noOfItems;
    }

    //Mutators
    public void setItemName(String ItemName) {
        if (ItemName == null || ItemName.equals("")) {
            throw new IllegalArgumentException("Item name must be provided!");
        }
        if (ItemName.length() < 3) {
            throw new IllegalArgumentException("Item Name must be at least 3 characters long.");
        }
        for (int i = 0; i < ItemName.length(); i++) {
            if (Character.isDigit(ItemName.charAt(i))) {
                throw new IllegalArgumentException("Item name can only be letters!");
            }
        }
        this.ItemName = ItemName;
    }

    public void setAcutalPrice(double ActualPrice) {
        if (ActualPrice <= 0.0) {
            throw new IllegalArgumentException("Actual Price must me greater than 0.");
        }
        this.ActualPrice = ActualPrice;
    }

    public void setAmazonPrice(double AmazonPrice) {
        if (AmazonPrice <= 0.0) {
            throw new IllegalArgumentException("Actual Price must me greater than 0.");
        }
        this.AmazonPrice = AmazonPrice;
    }
    public void setTargetPrice(double TargetPrice) {
        if (TargetPrice <= 0.0) {
            throw new IllegalArgumentException("Actual Price must me greater than 0.");
        }
        this.TargetPrice = TargetPrice;

    }
    public void setWalmartPrice(double WalmartPrice) {
        if (WalmartPrice <= 0.0) {
            throw new IllegalArgumentException("Actual Price must me greater than 0.");
        }
        this.WalmartPrice = WalmartPrice;

    }
    // Special Purpose
    public double calcLowestPrice() {

        double lowCost = getAcutalPrice();

        if (lowCost > getAmazonPrice()) {

            lowCost = getAmazonPrice();

        } else if (lowCost > getTargetPrice()) {

            lowCost = getTargetPrice();

        } else if (lowCost > getWalmartPrice()) {

            lowCost = getWalmartPrice();
        }

        return lowCost;
    }

    public double subWithWarranty() {
        return calcLowestPrice();
    }

    public double savingsCost() {

        double savedCost = (getAcutalPrice() - calcLowestPrice());

        return savedCost;
    }

    public String savingsString() {
        String saveReport = "";

        saveReport += "Item: " + getItemName() + "\nSaved on this item: " + String.format("$%,.2f", savingsCost());

        return saveReport;
    }
    public String toString() {
        String report = "";

        report += getItemName() + "\nItem Cost: " + String.format("$%,.2f", calcLowestPrice()) + "\nAmazon Price: " + String.format("$%,.2f", getAmazonPrice()) +
            "\nTarget Price: " + String.format("$%,.2f", getTargetPrice()) + "\nWalmart Price: " + String.format("$%,.2f", getWalmartPrice());
        return report;
    }

}