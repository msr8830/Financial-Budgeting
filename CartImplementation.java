import javax.swing.JOptionPane;
public class CartImplementation {

    public static void main(String[] args) {
        final int MAX_ITEMS = 50;// Constant to limit the amount of items in cart
        final double MAX_SUBTOTAL = 1500.00;// Constant to limit amount of total cost of all items
        Item[] shoppingCart = new Item[MAX_ITEMS];// instantiating an array to to store items in shoppingCart
        String option = "";
        String ItemOptions = "";
        // loop for the options in drop down until quit is selected
        do {
            option = DropDownMenu();

            switch (option) {// switch case statement to show dropdown selection
                case "Add Item":
                    do {
                        ItemOptions = ItemDropDownMenu();
                        double totalSub = 0.0;// variable created to add total amounts to this variable
                        switch (ItemOptions) {
                            case "Item":
                                for (int i = 0; i < Item.getNoOfItems(); i++) {//looping through array of objects to add total to totalSub
                                    totalSub += shoppingCart[i].subWithWarranty();
                                }
                                if (Item.getNoOfItems() < MAX_ITEMS && totalSub < MAX_SUBTOTAL) {// adding items to shopping cart array element index
                                    shoppingCart[Item.getNoOfItems()] = addItems();
                                } else {// display a error message if the max items or max total has reached
                                    JOptionPane.showMessageDialog(null, "You have reached maximum number of items or amount allowed!", "GMU Seminar", JOptionPane.INFORMATION_MESSAGE);
                                }
                                break;
                            case "Warranty Item":
                                for (int i = 0; i < WarrantyItem.getNoOfItems(); i++) {// looping through array of objects to add total to totalSub 
                                    totalSub += shoppingCart[i].subWithWarranty();
                                }
                                if (WarrantyItem.getNoOfItems() < MAX_ITEMS && totalSub < MAX_SUBTOTAL) {// adding warranty items to shopping cart array element index
                                    shoppingCart[WarrantyItem.getNoOfItems()] = addWarrantyItem();
                                } else {// display a error message if the max items or max total has reached
                                    JOptionPane.showMessageDialog(null, "You have reached maximum number of items or amount allowed!", "GMU Seminar", JOptionPane.INFORMATION_MESSAGE);
                                }
                                break;

                            default:// display a error message if the no option is selected
                                JOptionPane.showMessageDialog(null, "You must select one from the given options.", "GMU Seminar", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } while (JOptionPane.showConfirmDialog(null, "Do you want to add more items?", "GMU Seminar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
                    break;
                case "Remove Item":
                    if (Item.getNoOfItems() == 0) {// if no items were added display this error message
                        JOptionPane.showMessageDialog(null, "There were no items added!", "GMU Seminar", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        removeItem(shoppingCart, Item.getNoOfItems()); // calling remove method to remove an item from an array of shoppingCart
                    }
                    break;

                case "Print Cart Subtotal":
                    if (Item.getNoOfItems() == 0) {// if no items were added display this error message 
                        JOptionPane.showMessageDialog(null, "There were no items added!", "GMU Seminar", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        printSubtotal(shoppingCart);// calling printSubtotal method to display subtotal of items 
                    }
                    break;

                case "Print Total Savings Amount":
                    if (Item.getNoOfItems() == 0) {// if no items were added display this error message
                        JOptionPane.showMessageDialog(null, "There were no items added!", "GMU Seminar", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        printTotalSavings(shoppingCart); 
                    }
                    break;
                    
                case "Quit":// if quit is selected, thanking the student for using the application
                    JOptionPane.showMessageDialog(null, "Thank you for using Financial Responsibility Program!", "GMU Seminar", JOptionPane.INFORMATION_MESSAGE);
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "You must select one from the given options.", "GMU Seminar", JOptionPane.INFORMATION_MESSAGE); // re-prompting to select a option if none is selected
            }
        } while (!option.equalsIgnoreCase("Quit"));// ending do while loop once quit is selected

    }
    //Creates Drop Down Menu
    public static String DropDownMenu() {// creating the initial dropdown menu for selection
        String[] options = {
            "Add Item",
            "Remove Item",
            "Print Cart Subtotal",
            "Print Total Savings Amount",
            "Quit"
        };

        return (String) JOptionPane.showInputDialog(null, "Please select from given options", "GMU Seminar", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
    }

    public static String ItemDropDownMenu() {// creating dropdown for once add item is selected from initial menu

        String[] ItemOptions = {
            "Item",
            "Warranty Item"
        };

        return (String) JOptionPane.showInputDialog(null, "Please select an item type: ", "GMU Seminar", JOptionPane.PLAIN_MESSAGE, null, ItemOptions, ItemOptions[0]);
    }

    public static Item addItems() {// add item method to create aItem object and return aItem
        Item aItem = null;// instantiating an object and setting to null for later use

        do {// doing a do while loop and adding try catch statement to take input from user and send it to constructor
            try {
                aItem = new Item(JOptionPane.showInputDialog(null, "Enter the Item Name: ", "GMU Seminar", JOptionPane.PLAIN_MESSAGE),
                    Double.parseDouble(JOptionPane.showInputDialog(null, "Enter the Actual Cost of the Item: ", "GMU Seminar", JOptionPane.PLAIN_MESSAGE)));

                aItem.setAmazonPrice(Double.parseDouble(JOptionPane.showInputDialog(null, "Item: " + aItem.getItemName() + "\nActual Price: " + String.format("$%,.2f", aItem.getAcutalPrice()) +
                    "\nEnter the price at Amazon for this item: ", "GMU Seminar", JOptionPane.PLAIN_MESSAGE)));

                aItem.setTargetPrice(Double.parseDouble(JOptionPane.showInputDialog(null, "Item: " + aItem.getItemName() + "\nActual Price: " + String.format("$%,.2f", aItem.getAcutalPrice()) +
                    "\nEnter the price at Target for this item: ", "GMU Seminar", JOptionPane.PLAIN_MESSAGE)));

                aItem.setWalmartPrice(Double.parseDouble(JOptionPane.showInputDialog(null, "Item: " + aItem.getItemName() + "\nActual Price: " + String.format("$%,.2f", aItem.getAcutalPrice()) +
                    "\nEnter the price at Walmart for this item: ", "GMU Seminar", JOptionPane.PLAIN_MESSAGE)));

            } catch (IllegalArgumentException e) {// catching the error message
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

        } while (aItem == null);
        return aItem;// returning a object
    }

    public static WarrantyItem addWarrantyItem() {// warrantyItem method to instaniate and return a object
        WarrantyItem aItem = null;

        do {// do while loop to ask user for input and sending it to constructor
            try {
                aItem = new WarrantyItem(JOptionPane.showInputDialog(null, "Enter the Item Name: ", "GMU Seminar", JOptionPane.PLAIN_MESSAGE),
                    Double.parseDouble(JOptionPane.showInputDialog(null, "Enter the Actual Cost of the Item: ", "GMU Seminar", JOptionPane.PLAIN_MESSAGE)),
                    Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the warranty coverage in months of 12, 24, or 36: ", "GMU Seminar", JOptionPane.PLAIN_MESSAGE)));

                aItem.setAmazonPrice(Double.parseDouble(JOptionPane.showInputDialog(null, "Item: " + aItem.getItemName() + "\nActual Price: " + String.format("$%,.2f", aItem.getAcutalPrice()) +
                    "\nEnter the price at Amazon for this item: ", "GMU Seminar", JOptionPane.PLAIN_MESSAGE)));

                aItem.setTargetPrice(Double.parseDouble(JOptionPane.showInputDialog(null, "Item: " + aItem.getItemName() + "\nActual Price: " + String.format("$%,.2f", aItem.getAcutalPrice()) +
                    "\nEnter the price at Target for this item: ", "GMU Seminar", JOptionPane.PLAIN_MESSAGE)));

                aItem.setWalmartPrice(Double.parseDouble(JOptionPane.showInputDialog(null, "Item: " + aItem.getItemName() + "\nActual Price: " + String.format("$%,.2f", aItem.getAcutalPrice()) +
                    "\nEnter the price at Walmart for this item: ", "GMU Seminar", JOptionPane.PLAIN_MESSAGE)));

            } catch (IllegalArgumentException e) {// catch and display an error
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

        } while (aItem == null);

        return aItem;// returning a object created
    }

    public static Item removeItem(Item[] shoppingCart, int numOfItems) {// this method removes an item from an array of objects
        Item aItem = new Item();
        String itemToRemove = "";
        do {// do while loop to ask for item name from using and keep asking while one is not given
            itemToRemove = JOptionPane.showInputDialog(null, "Enter the Item name to remove: ", "GMU Seminar", JOptionPane.INFORMATION_MESSAGE);
        } while (itemToRemove == null || itemToRemove.equals(""));

        for (int i = 0; i < numOfItems; i++) {// looping through array and see if the given item name is in the array
            if (shoppingCart[i].getItemName().equalsIgnoreCase(itemToRemove)) {
                
                for (int j = i; j < shoppingCart.length - 1; j++) {
                    shoppingCart[j] = shoppingCart[j + 1];// removing an item from an array by looping through it
                }
                break;
            }else{// error displayed if the given item name was not found
               JOptionPane.showMessageDialog(null, "Error! Given Item name doesn't exist!, Please try again!", "GMU Seminar", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        return aItem;
    }
    public static void printSubtotal(Item[] shoppingCart) {

        String summary = "Your Shopping Cart\n*********************\n\n";

        double subtotal = 0.0;
        int ItemNum = 1; 
        for (int i = 0; i < Item.getNoOfItems(); i++) { // using loop to print the array of object element
            summary += "Item No. " + ItemNum + " - " + shoppingCart[i].toString() + "\n\n";
            ItemNum++; //incrementing item number for each item
            subtotal += shoppingCart[i].subWithWarranty();
        }

        summary += "Number of Items in the cart: " + Item.getNoOfItems() + "\nSubtotal: " + String.format("$%,.2f", subtotal);

        JOptionPane.showMessageDialog(null, summary, "GMU Seminar", JOptionPane.INFORMATION_MESSAGE); // printing final message

    }

    public static void printTotalSavings(Item[] shoppingCart) {

        String summary = "Your Savings\n****************\n\n";

        double totalSaved = 0.0;
        int ItemNum = 1; // variable created to show item number
        for (int i = 0; i < Item.getNoOfItems(); i++) { // using loop to print the array of object element
            summary += "No. " + ItemNum + "\n" + shoppingCart[i].savingsString() + "\n\n";
            ItemNum++; //incrementing item number for each item
            totalSaved += shoppingCart[i].savingsCost();
        }

        summary += "Total Amount Saved: " + String.format("$%,.2f", totalSaved);

        JOptionPane.showMessageDialog(null, summary, "GMU Seminar", JOptionPane.INFORMATION_MESSAGE); // printing final message

    }
}
