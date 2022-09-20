package model;

import java.util.Date;

public class PurchaseOrder {
	private int order_id, quantity;
	private double price;
	private Book book;
	private OBSUser customer;
	private Date purchaseDate;

	public PurchaseOrder() {

	}

	public PurchaseOrder(Book book, int quantity, OBSUser customer, double price, Date purchaseDate) {
		this.book = book;
		this.quantity = quantity;
		this.customer = customer;
		this.price = price;
		this.purchaseDate = purchaseDate;

	}

	public PurchaseOrder(int id, Book book, int quantity, OBSUser customer, double price, Date purchaseDate) {
		this.order_id = id;
		this.quantity = quantity;
		this.purchaseDate = purchaseDate;
		this.book = book;
		this.customer = customer;
		this.price = price;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public OBSUser getCustomer() {
		return customer;
	}

	public void setCustomer(OBSUser customer) {
		this.customer = customer;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

}
