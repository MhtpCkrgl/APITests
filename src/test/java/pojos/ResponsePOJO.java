package pojos;

import java.io.Serializable;

public class ResponsePOJO implements Serializable {
	private int id;
	private String name;
	private String author;
	private String isbn;
	private String type;
	private Object price;
	private int currentStock;
	private boolean available;

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setAuthor(String author){
		this.author = author;
	}

	public String getAuthor(){
		return author;
	}

	public void setIsbn(String isbn){
		this.isbn = isbn;
	}

	public String getIsbn(){
		return isbn;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setPrice(Object price){
		this.price = price;
	}

	public Object getPrice(){
		return price;
	}

	public void setCurrentStock(int currentStock){
		this.currentStock = currentStock;
	}

	public int getCurrentStock(){
		return currentStock;
	}

	public void setAvailable(boolean available){
		this.available = available;
	}

	public boolean isAvailable(){
		return available;
	}

	@Override
 	public String toString(){
		return 
			"ResponsePOJO{" + 
			"id = '" + id + '\'' + 
			",name = '" + name + '\'' + 
			",author = '" + author + '\'' + 
			",isbn = '" + isbn + '\'' + 
			",type = '" + type + '\'' + 
			",price = '" + price + '\'' + 
			",current-stock = '" + currentStock + '\'' + 
			",available = '" + available + '\'' + 
			"}";
		}

	public ResponsePOJO() {
	}

	public ResponsePOJO(int id, String name, String author, String isbn, String type, Object price, int currentStock, boolean available) {
		this.id = id;
		this.name = name;
		this.author = author;
		this.isbn = isbn;
		this.type = type;
		this.price = price;
		this.currentStock = currentStock;
		this.available = available;
	}
}