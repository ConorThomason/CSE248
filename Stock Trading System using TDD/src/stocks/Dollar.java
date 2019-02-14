package stocks;

public class Dollar {
	private String currency;
	private double price;
	private double rate;
	
	public Dollar(String currency, double price, double rate) {
		this.currency = currency;
		this.price = price;
		this.rate = rate;
	}
	public double getDollarAmount() {
		return price / rate;
	}
}
