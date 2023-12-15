package pojos;

import java.io.Serializable;

public class alp_singleBookInfoPOJO implements Serializable {
	private int id;
	private String name;
	private String author;
	private String type;
	private Object price;
	private int currentstock;
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
		this.currentstock = currentStock;
	}

	public int getCurrentStock(){
		return currentstock;
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
			"ResponseDTO{" + 
			"id = '" + id + '\'' + 
			",name = '" + name + '\'' + 
			",author = '" + author + '\'' + 
			",type = '" + type + '\'' + 
			",price = '" + price + '\'' + 
			",current-stock = '" + currentstock + '\'' +
			",available = '" + available + '\'' + 
			"}";
		}


	public alp_singleBookInfoPOJO() {
	}

	public alp_singleBookInfoPOJO(int id, String name, String author, String type, Object price, int currentStock, boolean available) {
		this.id = id;
		this.name = name;
		this.author = author;
		this.type = type;
		this.price = price;
		this.currentstock = currentStock;
		this.available = available;
	}




}