/*

  Author: Shiru Hou
  Email: shou2015@my.fit.edu
  Course: cse2010
  Section: 03
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class HW4 {
	// the queue to store the buyer orders
	HeapAdaptablePriorityQueue<String, Order> buyerOrders = new HeapAdaptablePriorityQueue<String, Order>();
	// the queue to store the seller orders
	HeapAdaptablePriorityQueue<String, Order> sellerOrders = new HeapAdaptablePriorityQueue<String, Order>();

	public static void main(String[] args) {
		new HW4().run(args[0]);
	}

	/**
	 * run the exchange system
	 * 
	 * @param fileName
	 *            input file name
	 */
	public void run(String fileName) {
		// read the command lines from the input file
		ArrayList<String> commandLines = readInput(fileName);
		// execute the commands
		for (String commandLine : commandLines) {
			if (commandLine.startsWith("EnterBuyOrder")) {
				EnterBuyOrder(commandLine);
			} else if (commandLine.startsWith("CancelBuyOrder")) {
				CancelBuyOrder(commandLine);
			} else if (commandLine.startsWith("ChangeBuyOrder")) {
				ChangeBuyOrder(commandLine);
			} else if (commandLine.startsWith("EnterSellOrder")) {
				EnterSellOrder(commandLine);
			} else if (commandLine.startsWith("CancelSellOrder")) {
				CancelSellOrder(commandLine);
			} else if (commandLine.startsWith("ChangeSellOrder")) {
				ChangeSellOrder(commandLine);
			} else if (commandLine.startsWith("DisplayHighestBuyOrder")) {
				DisplayHighestBuyOrder(commandLine);
			} else if (commandLine.startsWith("DisplayLowestSellOrder")) {
				DisplayLowestSellOrder(commandLine);
			} else if (commandLine.startsWith("ExecuteBuySellOrders")) {
				ExecuteBuySellOrders(commandLine);
			}
		}
	}

	/**
	 * Read the command lines from the input file
	 * 
	 * @param fileName
	 *            the input file name
	 * @return a list of the command lines
	 */
	public ArrayList<String> readInput(String fileName) {
		// a list to store the command lines
		ArrayList<String> commandLines = new ArrayList<String>();
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				commandLines.add(tempString);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception e2) {
				}
			}
		}
		return commandLines;
	}

	/**
	 * enter the buyer order
	 * 
	 * @param commandLine
	 *            the command line
	 */
	private void EnterBuyOrder(String commandLine) {
		String[] arrs = commandLine.split("\\s+");
		Integer time = Integer.parseInt(arrs[1]);
		String buyer = arrs[2];
		int price = Integer.parseInt(arrs[3]);
		int quantity = Integer.parseInt(arrs[4]);
		// check if the buyer has exists
		for (int i = 0; i < this.buyerOrders.heap.size(); i++) {
			if (buyerOrders.heap.get(i).getKey().equals(buyer)) {
				System.out.println(commandLine + " ExistingBuyerError");
				return;
			}
		}
		System.out.println(commandLine);
		Order buyOrder = new Order(time, buyer, price, quantity);
		// insert the new buyer order
		this.buyerOrders.insert(buyer, buyOrder);
	}

	/**
	 * cancel the buy order
	 * 
	 * @param commandLine
	 *            the command line
	 */
	private void CancelBuyOrder(String commandLine) {
		String[] arrs = commandLine.split("\\s+");
		String buyer = arrs[2];
		for (int i = 0; i < this.buyerOrders.heap.size(); i++) {
			if (buyerOrders.heap.get(i).getKey().equals(buyer)) {
				System.out.println(commandLine);
				// remove the buyer order
				buyerOrders.heap.remove(i);
				return;
			}
		}
		// the buyer order doesn't exist
		System.out.println(commandLine + " noBuyerError");
	}

	/**
	 * change the buyer order
	 * 
	 * @param commandLine
	 *            the command line
	 */
	private void ChangeBuyOrder(String commandLine) {
		String[] arrs = commandLine.split("\\s+");
		Integer time = Integer.parseInt(arrs[1]);
		String buyer = arrs[2];
		int price = Integer.parseInt(arrs[3]);
		int quantity = Integer.parseInt(arrs[4]);
		for (int i = 0; i < this.buyerOrders.heap.size(); i++) {
			if (buyerOrders.heap.get(i).getKey().equals(buyer)) {
				// change the buyer order
				buyerOrders.remove(buyerOrders.heap.get(i));
				Order buyOrder = new Order(time, buyer, price, quantity);
				buyerOrders.insert(buyer, buyOrder);
				System.out.println(commandLine);
				return;
			}
		}
		// the buyer order doesn't exist
		System.out.println(commandLine + " noBuyerError");
	}

	/**
	 * enter a sell order
	 * 
	 * @param commandLine
	 *            the command line
	 */
	private void EnterSellOrder(String commandLine) {
		String[] arrs = commandLine.split("\\s+");
		Integer time = Integer.parseInt(arrs[1]);
		String seller = arrs[2];
		int price = Integer.parseInt(arrs[3]);
		int quantity = Integer.parseInt(arrs[4]);
		// check if the sell order has exists
		for (int i = 0; i < this.sellerOrders.heap.size(); i++) {
			if (sellerOrders.heap.get(i).getKey().equals(seller)) {
				System.out.println(commandLine + " ExistingSellerError");
				return;
			}
		}
		System.out.println(commandLine);
		// insert the new sell order
		Order sellerOrder = new Order(time, seller, price, quantity);
		this.sellerOrders.insert(seller, sellerOrder);
	}

	/**
	 * cancel the seller order
	 * 
	 * @param commandLine
	 *            the command line
	 */
	private void CancelSellOrder(String commandLine) {
		String[] arrs = commandLine.split("\\s+");
		String seller = arrs[2];
		for (int i = 0; i < this.sellerOrders.heap.size(); i++) {
			if (sellerOrders.heap.get(i).getKey().equals(seller)) {
				System.out.println(commandLine);
				// remove the seller order from the heap
				sellerOrders.heap.remove(i);
				return;
			}
		}
		// the seller order doesn't exist
		System.out.println(commandLine + " noSellerError");
	}

	/**
	 * change the seller order
	 * 
	 * @param commandLine
	 *            the command line
	 */
	private void ChangeSellOrder(String commandLine) {
		String[] arrs = commandLine.split("\\s+");
		Integer time = Integer.parseInt(arrs[1]);
		String seller = arrs[2];
		int price = Integer.parseInt(arrs[3]);
		int quantity = Integer.parseInt(arrs[4]);
		for (int i = 0; i < this.sellerOrders.heap.size(); i++) {
			if (sellerOrders.heap.get(i).getKey().equals(seller)) {
				// change the seller order
				sellerOrders.remove(sellerOrders.heap.get(i));
				Order sellerOrder = new Order(time, seller, price, quantity);
				sellerOrders.insert(seller, sellerOrder);
				System.out.println(commandLine);
				return;
			}
		}
		// the seller order doesn't exist
		System.out.println(commandLine + " noSellerError");
	}

	/**
	 * Display the highest price of the buy order
	 * 
	 * @param commandLine
	 *            the command line
	 */
	private void DisplayHighestBuyOrder(String commandLine) {
		System.out.print(commandLine + " ");
		if (this.buyerOrders.heap.size() > 0) {
			Order hightest = this.buyerOrders.heap.get(0).getValue();
			int hightPrice = hightest.getPrice();
			for (int i = 1; i < this.buyerOrders.heap.size(); i++) {
				Order tempOrder = this.buyerOrders.heap.get(i).getValue();
				// if the temp price is higher than the higest price
				if (tempOrder.getPrice() > hightPrice) {
					hightPrice = tempOrder.getPrice();
					hightest = tempOrder;
				}
			}
			System.out.print(hightest.getUser() + " " + hightest.getTime() + " " + hightest.getPrice() + " "
					+ hightest.getQuantity());
		}
		System.out.println();
	}

	/**
	 * Display the lowest price of the seller order
	 * 
	 * @param commandLine
	 *            the command line
	 */
	private void DisplayLowestSellOrder(String commandLine) {
		System.out.print(commandLine + " ");
		if (this.sellerOrders.heap.size() > 0) {
			Order lowest = this.sellerOrders.heap.get(0).getValue();
			int lowestPrice = lowest.getPrice();
			for (int i = 1; i < this.sellerOrders.heap.size(); i++) {
				Order tempOrder = this.sellerOrders.heap.get(i).getValue();
				// if the temp price is lower the lowest price
				if (tempOrder.getPrice() < lowestPrice) {
					lowestPrice = tempOrder.getPrice();
					lowest = tempOrder;
				}
			}
			System.out.print(
					lowest.getUser() + " " + lowest.getTime() + " " + lowest.getPrice() + " " + lowest.getQuantity());
		}
		System.out.println();
	}

	/**
	 * Execute the buy and sell order
	 * 
	 * @param commandLine
	 *            the command line
	 */
	private void ExecuteBuySellOrders(String commandLine) {
		System.out.println(commandLine);
		String[] arrs = commandLine.split("\\s+");
		int price = Integer.parseInt(arrs[1]);
		int quantity = Integer.parseInt(arrs[2]);
		Entry<String, Order> buyEntity = null;
		Entry<String, Order> sellEntity = null;
		Order buyOrder = null;
		Order sellOrder = null;
		for (int i = 0; i < this.buyerOrders.heap.size(); i++) {
			Order tempOrder = buyerOrders.heap.get(i).getValue();
			if (tempOrder.getPrice() == price) {
				// get the buyOrder and buyEntity
				if (buyOrder == null) {
					buyEntity = buyerOrders.heap.get(i);
					buyOrder = tempOrder;
				} else {
					// get the earlier buyer order
					if (tempOrder.getTime() < buyOrder.getTime()) {
						buyOrder = tempOrder;
						buyEntity = buyerOrders.heap.get(i);
					}
				}
			}
		}
		for (int i = 0; i < this.sellerOrders.heap.size(); i++) {
			Order tempOrder = sellerOrders.heap.get(i).getValue();
			if (tempOrder.getPrice() == price) {
				// get the sellOrder and sellEntity
				if (sellOrder == null) {
					sellOrder = tempOrder;
					sellEntity = sellerOrders.heap.get(i);
				} else {
					// get the earlier seller order
					if (tempOrder.getTime() < sellOrder.getTime()) {
						sellOrder = tempOrder;
						sellEntity = sellerOrders.heap.get(i);
					}
				}
			}
		}
		// if the buyer order and seller order exists
		if (buyOrder != null && sellOrder != null) {
			// execute the buyer order
			if (buyOrder.getQuantity() <= quantity) {
				// the remaining buy order is zero,cancel the buy order
				System.out.println("Buyer: " + buyOrder.getUser() + " 0");
				this.buyerOrders.remove(buyEntity);
			} else {
				// the remaining buyer order is over zero,change the buyer order
				System.out.println("Buyer: " + buyOrder.getUser() + " " + (buyOrder.getQuantity() - quantity));
				Order newBuyOrder = new Order(buyOrder.getTime(), buyOrder.getUser(), buyOrder.getPrice(),
						buyOrder.getQuantity() - quantity);
				this.buyerOrders.remove(buyEntity);
				this.buyerOrders.insert(buyOrder.getUser(), newBuyOrder);
			}
			if (sellOrder.getQuantity() <= quantity) {
				// the remaining seller order is zero,cancel the seller order
				System.out.println("Seller: " + sellOrder.getUser() + " 0");
				this.sellerOrders.remove(sellEntity);
			} else {
				// the remaining seller order is over zero,change the selle order
				System.out.println("Seller: " + sellOrder.getUser() + " " + (sellOrder.getQuantity() - quantity));
				Order newSellerOrder = new Order(sellOrder.getTime(), sellOrder.getUser(), sellOrder.getPrice(),
						sellOrder.getQuantity() - quantity);
				this.sellerOrders.remove(sellEntity);
				this.sellerOrders.insert(sellOrder.getUser(), newSellerOrder);
			}
		}
	}

}
