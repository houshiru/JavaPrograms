/*

  Author: Shiru Hou
  Email: shou2015@my.fit.edu
  Course: cse2010
  Section: 3
  Description: This assignment using the SinglyLinedList manage three products such as appleIphone,
  hdmi2VgaAdapter and USBdrive. These product from different sellers and has different price and
  shipping cost. Separately manage the seller list in ascending oreder of total cost for each product.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Arrays;

public class HW1
{
    /*
      member variables stand for three products
    */
    static Product appleIphone = new Product("appleIphone");
    static Product hdmi2VgaAdapter = new Product("hdmi2VgaAdapter");
    static Product USBdrive = new Product("USBdrive");

    /*
        choose product
     */
    static Product chooseProduct(String product){
        if ("appleIphone".equals(product)) {
            return appleIphone;
        } else if ("hdmi2VgaAdapter".equals(product)){
            return hdmi2VgaAdapter;
        } else if ("USBdrive".equals(product)){
            return USBdrive;
        } else{
            return null;
        }
    }

    public static void main(String[] args) throws IOException {
        /*
        use BufferRead to read the test data
         */
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String bfread;
        for (bfread = bufferedReader.readLine(); bfread != null; bfread = bufferedReader.readLine() ) {
            String strs[] = bfread.split(" ");
            if (strs[0].equals("SetProductPrice")){
                SetProductPrice(strs[1], strs[2], strs[3]);
            }else if (strs[0].equals("IncreaseInventory")) {
                IncreaseInventory(strs[1], strs[2], strs[3]);
            }else if(strs[0].equals("SetShippingCost")){
                SetShippingCost(strs[1], strs[2], strs[3]);
            }else if (strs[0].equals("CustomerPurchase")){
                CustomerPurchase(strs[1], strs[2], strs[3]);
            }else if (strs[0].equals("DisplaySellerList")){
                DisplaySellerList(strs[1]);
            }
            else {
            }
        }
    }

    /*
    Set the product price including product name, seller, and price
     */
    public static void SetProductPrice(String product, String seller, String price){
        Product p = chooseProduct(product);
        p.setSellerPrice(seller, Double.parseDouble(price));
        p.addSeller(seller);
        System.out.println("SetProductPrice " + product + " " + seller + " " + price);
    }
    /*
    set the shipping cost for each product
     */
    public static void SetShippingCost(String seller, String shippingCost, String minimumForFreeShipping){
        appleIphone.putSeller(seller, Double.parseDouble(shippingCost), Double.parseDouble(minimumForFreeShipping));
        hdmi2VgaAdapter.putSeller(seller,Double.parseDouble(shippingCost), Double.parseDouble(minimumForFreeShipping));
        USBdrive.putSeller(seller,Double.parseDouble(shippingCost), Double.parseDouble(minimumForFreeShipping));
        System.out.println("SetShippingCost " + seller + " " + shippingCost + " " + minimumForFreeShipping);
    }
    /*
    increase inventory function
     */
    public static void IncreaseInventory(String product, String seller, String quantity){
        Product p = chooseProduct(product);
        String result = p.increaseInvestory(seller, Integer.parseInt(quantity));
        System.out.println("IncreaseInventory " + product + " " + seller + " " + quantity + " " + result);
    }
    /*
    customer purchase function
     */
    public static void CustomerPurchase(String product, String seller, String quantity){
        Product p = chooseProduct(product);
        String result = p.customerPurchase(seller, Integer.parseInt(quantity));
        System.out.println("CustomerPurchase " + product + " " + seller + " " + quantity + " " + result);
    }
    /*
    display the seller list
     */
    public static void DisplaySellerList(String product){
        System.out.println("DisplaySellerList " + product);
        Product p = chooseProduct(product);
        System.out.println("    seller  productPrice  shippingCost  totalCost");
        Object[] list = p.getList().toArray();
        //Set the precision
        DecimalFormat df = new DecimalFormat("0.00");
        Arrays.sort(list); //Sort in ascending order
        for (int i = 0; i <list.length ; i++) {
            Seller seller = (Seller) list[i];
            if (seller == null) {
                continue;
            }
            //formatted output
            System.out.print(String.format("%10s", seller.getSeller()));
            System.out.print(String.format("%14s", df.format(seller.getPrice())));
            System.out.print(String.format("%14s", df.format(seller.getShippingCost())));
            System.out.println(String.format("%11s", df.format(seller.getTotal_price())));
        }

    }

}