import java.util.HashMap;
import java.util.Map;

public class Product {
    /*
    member variables
     */
    private String product;
    private SinglyLinkedList<Seller> list = new SinglyLinkedList<Seller>();
    private Map<String, Seller> map = new HashMap<String, Seller>();

    /*
    constructor function
     */
    public Product(String product) {
        this.product = product;
    }

    public String getProduct() {
        return product;
    }

    public SinglyLinkedList<Seller> getList() {
        return list;
    }

    public void setList(SinglyLinkedList<Seller> list) {
        this.list = list;
    }

    public void setProduct(String product) {
        this.product = product;
    }
    /*
    according the seller name put the seller object into map
     */
    public void putSeller(String name, double shippingCost, double minimumForFreeShipping){
        Seller seller = new Seller(name, shippingCost, minimumForFreeShipping);
        map.put(name,seller);
    }
    /*
    add the seller into list
     */
    public void addSeller(String name){
        Seller seller = map.get(name);
        list.addFirst(seller);
    }
    /*
    set seller price
     */
    public void setSellerPrice(String name, double price){
        Seller seller = map.get(name);
        seller.setPrice(price);
    }
    /*
    increase inventory
     */
    public String increaseInvestory(String name, int quantity){
        Seller seller = map.get(name);
        seller.setQuantity(quantity);
        return "" + quantity;
    }
    /*
    customer purchase the product when the Inventory is enough and update
    the Inventory number after customer purchased
     */
    public String customerPurchase(String name, int quantity){
        Seller seller = map.get(name);
        int all = seller.getQuantity();
        if(all < quantity){
            return "NotEnoughInventoryError";
        }else{
            int result = all - quantity;
            seller.setQuantity(result);
            return "" + result;
        }
    }

}
