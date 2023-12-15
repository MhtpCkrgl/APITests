package stepDefinition.fatma;



import java.io.Serializable;

public class BookPOJO implements Serializable {

	private int id;
	private String name;
	private String author;
	private String type;
	private Object price;
	private String currentStock;
	private boolean available;

	public BookPOJO() {
	}

	public BookPOJO(int id, String name, String author, String type, Object price, String currentStock, Boolean available) {
		this.id = id;
		this.name = name;
		this.author = author;
		this.type = type;
		this.price = price;
		this.currentStock = currentStock;
		this.available = available;
	}

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

	public void setCurrentStock(String currentStock){
		this.currentStock = currentStock;
	}

	public String getCurrentStock(){
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
			"BookPOJO{" + 
			"id = '" + id + '\'' + 
			",name = '" + name + '\'' + 
			",author = '" + author + '\'' + 
			",type = '" + type + '\'' + 
			",price = '" + price + '\'' + 
			",currentStock = '" + currentStock + '\'' +
			",available = '" + available + '\'' + 
			"}";
		}
}