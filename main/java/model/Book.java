package model;

import com.util.FileUtils;

public class Book {
	private int book_id, book_stock;
	private String book_name, book_author, book_cat;
	private double price;
	private byte[] image;

	public Book(int book_id, int book_stock, String book_name, String book_author, String book_cat, double price,
			byte[] image) {
		this.book_id = book_id;
		this.book_stock = book_stock;
		this.book_name = book_name;
		this.book_author = book_author;
		this.book_cat = book_cat;
		this.price = price;
		this.image = image;
	}

	public Book(String book_name, String book_author, int stock, double price, String category, byte[] image) {
		this.book_name = book_name;
		this.book_author = book_author;
		this.book_stock = stock;
		this.price = price;
		this.book_cat = category;
		this.image = image;
	}

	public Book(int book_id, int stock) {
		this.book_id = book_id;
		this.book_stock = stock;

	}

	public Book(int book_id, double price) {
		this.book_id = book_id;
		this.price = price;
	}

	public Book() {

	}

	public String getEncodedImage() {
		return FileUtils.toBase64(this.image);
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public int getBook_stock() {
		return book_stock;
	}

	public void setBook_stock(int book_stock) {
		this.book_stock = book_stock;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getBook_author() {
		return book_author;
	}

	public void setBook_author(String book_author) {
		this.book_author = book_author;
	}

	public String getBook_cat() {
		return book_cat;
	}

	public void setBook_cat(String book_cat) {
		this.book_cat = book_cat;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

}
