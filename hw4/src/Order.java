import java.util.Comparator;

public class Order implements Comparator<Order> {
	private Integer time;
	private String user;
	private int price;
	private int quantity;
	/*
	Constructor
	 */
	public Order(Integer time, String user, int price, int quantity) {
		super();
		this.time = time;
		this.user = user;
		this.price = price;
		this.quantity = quantity;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public int compare(Order o1, Order o2) {
		return o2.getTime() - o1.getTime();
	}

}
