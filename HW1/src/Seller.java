/*
this class define seller information for each product such as price, shippingcost,
miniumumForFreeShipping, quantity, and get total cost.
 */
public class Seller implements Comparable<Seller>{
    /*
     * member variables.
     */
    private String seller; //Seller name : walmart, bestbuy, amazon
    private double price;
    private double shippingCost;
    private double minimumForFreeShipping;
    private int quantity;
    private double total_price;

    /*
        Constructor function
     */
    public Seller(String seller) {
        this.seller = seller;
    }
    /*
        Constructor function
     */
    public Seller(String seller, double shippingCost, double minimumForFreeShipping) {
        this.seller = seller;
        this.shippingCost = shippingCost;
        this.minimumForFreeShipping = minimumForFreeShipping;
    }
    /*
    return the selle name
     */
    public String getSeller() {
        return seller;
    }
    /*
    set the seller name
     */
    public void setSeller(String seller) {
        this.seller = seller;
    }
    /*
    get price
     */
    public double getPrice() {
        return price;
    }
    /*
    Total price equal to the product price plus shipping cost
     */
    public void setPrice(double price) {
        this.price = price;
        this.total_price = price;
        if (this.price < minimumForFreeShipping){
            this.total_price += this.shippingCost;
        }
    }

    /*
     *Shipping cost is zero if the product price is at least minimumForFreeShipping
     */
    public double getShippingCost() {
        if(price >= minimumForFreeShipping){
            return 0;
        }else {
            return shippingCost;
        }
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public double getMinimumForFreeShipping() {
        return minimumForFreeShipping;
    }

    public void setMinimumForFreeShipping(double minimumForFreeShipping) {
        this.minimumForFreeShipping = minimumForFreeShipping;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    /*
    get total price
     */
    public double getTotal_price() {
        return total_price;
    }


    /*
     * Over write compareto method that used to sort the Seller subject according to the total price
     */
    @Override
    public int compareTo(Seller o) {
        double total1 = this.getTotal_price();
        double total2 = o.getTotal_price();
        if (total1 - total2 > 0){
            return 1;
        }else if (total1 - total2 < 0){
            return -1;
        }else  {
            return 0;
        }
    }
}
