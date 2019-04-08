package composite;

public class Demo {

	public static void main(String[] args) {
		Ticket t1 = new Ticket("John", "Left 5", "4/2", 10.00);
		Wine w1 = new Wine("Pinot", "2018", 50.00);
		
		ItemList list = new ItemList();
		list.add(t1);
		list.add(w1);
		list.printAllItems();
	}

}
