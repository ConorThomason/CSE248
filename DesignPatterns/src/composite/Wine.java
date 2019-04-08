package composite;

public class Wine implements Item {
	private String producer;
	private String vintage;
	private double price;
	
	
	
	public Wine(String producer, String vintage, double price) {
		super();
		this.producer = producer;
		this.vintage = vintage;
		this.price = price;
	}

	@Override
	public void print() {
		System.out.println(producer + "\t" + vintage + "\t" + price);
	}

}
