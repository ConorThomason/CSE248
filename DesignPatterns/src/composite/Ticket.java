package composite;

public class Ticket implements Item {
	private String name;
	private String location;
	private String date;
	private double price;
	
	
	
	public Ticket(String name, String location, String date, double price) {
		super();
		this.name = name;
		this.location = location;
		this.date = date;
		this.price = price;
	}

	public void print() {
		System.out.println(name + "\t" + location + "\t" + date + "\t" + price);
		
	}

}
