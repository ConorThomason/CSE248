package composite;

import java.util.ArrayList;

public class ItemList {
	private ArrayList<Item> list;
	
	public ItemList() {
		list = new ArrayList<>();
	}
	
	public void add(Item item) {
		this.list.add(item);
	}
	
	public void printAllItems() {
		for(Item i : list) {
			i.print();
		}
	}
}
