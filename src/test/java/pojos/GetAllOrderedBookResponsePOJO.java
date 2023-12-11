package pojos;

import java.io.Serializable;

public class GetAllOrderedBookResponsePOJO implements Serializable {
	private int quantity;
	private String createdBy;
	private String id;
	private String customerName;
	private int bookId;
	private long timestamp;

	public void setQuantity(int quantity){
		this.quantity = quantity;
	}

	public int getQuantity(){
		return quantity;
	}

	public void setCreatedBy(String createdBy){
		this.createdBy = createdBy;
	}

	public String getCreatedBy(){
		return createdBy;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setCustomerName(String customerName){
		this.customerName = customerName;
	}

	public String getCustomerName(){
		return customerName;
	}

	public void setBookId(int bookId){
		this.bookId = bookId;
	}

	public int getBookId(){
		return bookId;
	}

	public void setTimestamp(long timestamp){
		this.timestamp = timestamp;
	}

	public long getTimestamp(){
		return timestamp;
	}

	@Override
 	public String toString(){
		return 
			"GetAllOrderedBookResponsePOJO{" + 
			"quantity = '" + quantity + '\'' + 
			",createdBy = '" + createdBy + '\'' + 
			",id = '" + id + '\'' + 
			",customerName = '" + customerName + '\'' + 
			",bookId = '" + bookId + '\'' + 
			",timestamp = '" + timestamp + '\'' + 
			"}";
		}

	public GetAllOrderedBookResponsePOJO() {
	}

	public GetAllOrderedBookResponsePOJO(int quantity, String createdBy, String id, String customerName, int bookId, long timestamp) {
		this.quantity = quantity;
		this.createdBy = createdBy;
		this.id = id;
		this.customerName = customerName;
		this.bookId = bookId;
		this.timestamp = timestamp;
	}
}