package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.MyDBConnect;
import model.Book;

public class BookDao {

	public int add(Book book) {
		Connection conn = MyDBConnect.dbConnect();
		int addBookCount = 0;
		try {
			PreparedStatement ps = conn.prepareStatement("insert into books values(BOOK_SEQ.nextVal,?,?,?,?,?,?)");
			ps.setString(1, book.getBook_name());
			ps.setString(2, book.getBook_author());
			ps.setInt(3, book.getBook_stock());
			ps.setDouble(4, book.getPrice());
			ps.setString(5, book.getBook_cat());
			ps.setBytes(6, book.getImage());
			addBookCount = ps.executeUpdate();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return addBookCount;
	}

	public int updateStock(Book book) {
		Connection conn = MyDBConnect.dbConnect();
		int updateBookCount = 0;

		try {
			PreparedStatement ps = conn.prepareStatement("update books set BOOK_STOCK =? where BOOK_ID =?");
			ps.setInt(1, book.getBook_stock());
			ps.setInt(2, book.getBook_id());

			updateBookCount = ps.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return updateBookCount;
	}

	public int updatePrice(Book book) {
		Connection conn = MyDBConnect.dbConnect();
		int updateBookCount = 0;

		try {
			PreparedStatement ps = conn.prepareStatement("update books set PRICE =? where BOOK_ID =?");
			ps.setDouble(1, book.getPrice());
			ps.setInt(2, book.getBook_id());

			updateBookCount = ps.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return updateBookCount;
	}

	public List<Book> viewAllBooksbyId() {
		Connection conn = MyDBConnect.dbConnect();
		List<Book> bookList = new ArrayList<>();

		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM BOOKS ORDER BY BOOK_ID");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Book book = new Book();
				book.setBook_id(rs.getInt(1));
				book.setBook_name(rs.getString(2));
				book.setBook_author(rs.getString(3));
				book.setBook_stock(rs.getInt(4));
				book.setPrice(rs.getDouble(5));
				book.setBook_cat(rs.getString(6));
				book.setImage(rs.getBytes(7));
				bookList.add(book);
			}
			conn.close();
		} catch (SQLException e) {

		}
		return bookList;
	}

	public List<Book> viewAllBooksbyBookname() {
		Connection conn = MyDBConnect.dbConnect();
		List<Book> bookList = new ArrayList<>();

		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM BOOKS ORDER BY book_name");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Book book = new Book();
				book.setBook_id(rs.getInt(1));
				book.setBook_name(rs.getString(2));
				book.setBook_author(rs.getString(3));
				book.setBook_stock(rs.getInt(4));
				book.setPrice(rs.getDouble(5));
				book.setBook_cat(rs.getString(6));
				book.setImage(rs.getBytes(7));
				bookList.add(book);
			}
			conn.close();
		} catch (SQLException e) {

		}
		return bookList;
	}

	public List<Book> viewAllBooksbyAuthor() {
		Connection conn = MyDBConnect.dbConnect();
		List<Book> bookList = new ArrayList<>();

		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM BOOKS ORDER BY book_author");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Book book = new Book();
				book.setBook_id(rs.getInt(1));
				book.setBook_name(rs.getString(2));
				book.setBook_author(rs.getString(3));
				book.setBook_stock(rs.getInt(4));
				book.setPrice(rs.getDouble(5));
				book.setBook_cat(rs.getString(6));
				book.setImage(rs.getBytes(7));
				bookList.add(book);
			}
			conn.close();
		} catch (SQLException e) {

		}
		return bookList;
	}

	public List<Book> viewAllBooksbyPrice() {
		Connection conn = MyDBConnect.dbConnect();
		List<Book> bookList = new ArrayList<>();

		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM BOOKS ORDER BY price");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Book book = new Book();
				book.setBook_id(rs.getInt(1));
				book.setBook_name(rs.getString(2));
				book.setBook_author(rs.getString(3));
				book.setBook_stock(rs.getInt(4));
				book.setPrice(rs.getDouble(5));
				book.setBook_cat(rs.getString(6));
				book.setImage(rs.getBytes(7));
				bookList.add(book);
			}
			conn.close();
		} catch (SQLException e) {

		}
		return bookList;
	}

	public List<Book> viewAllBooksbyStock() {
		Connection conn = MyDBConnect.dbConnect();
		List<Book> bookList = new ArrayList<>();

		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM BOOKS ORDER BY book_stock");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Book book = new Book();
				book.setBook_id(rs.getInt(1));
				book.setBook_name(rs.getString(2));
				book.setBook_author(rs.getString(3));
				book.setBook_stock(rs.getInt(4));
				book.setPrice(rs.getDouble(5));
				book.setBook_cat(rs.getString(6));
				book.setImage(rs.getBytes(7));
				bookList.add(book);
			}
			conn.close();
		} catch (SQLException e) {

		}
		return bookList;
	}

	public List<Book> viewBooksbyCategory(String category) {
		Connection conn = MyDBConnect.dbConnect();
		List<Book> bookList = new ArrayList<>();
		String cat = category.toLowerCase();

		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM BOOKS WHERE lower(BOOK_CAT)='" + cat + "'");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Book book = new Book();
				book.setBook_id(rs.getInt(1));
				book.setBook_name(rs.getString(2));
				book.setBook_author(rs.getString(3));
				book.setBook_stock(rs.getInt(4));
				book.setPrice(rs.getDouble(5));
				book.setBook_cat(rs.getString(6));
				book.setImage(rs.getBytes(7));
				bookList.add(book);

				conn.close();
			}

		} catch (SQLException e) {

		}
		return bookList;
	}

	public Book bookChoosen(int bookid) {
		Connection conn = MyDBConnect.dbConnect();
		Book book = new Book();

		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM BOOKS WHERE book_id = " + bookid + "");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				book.setBook_id(rs.getInt(1));
				book.setBook_name(rs.getString(2));
				book.setBook_author(rs.getString(3));
				book.setBook_stock(rs.getInt(4));
				book.setPrice(rs.getDouble(5));
				book.setBook_cat(rs.getString(6));
				book.setImage(rs.getBytes(7));

				conn.close();
			}
		} catch (SQLException e) {

		}

		return book;
	}

	public List<Book> searchBook(String searching) {
		Connection conn = MyDBConnect.dbConnect();
		List<Book> bookList = new ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from books where lower(book_name) like ? ");
			ps.setString(1, "%" + searching.toLowerCase() + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Book book = new Book();
				book.setBook_id(rs.getInt(1));
				book.setBook_name(rs.getString(2));
				book.setBook_author(rs.getString(3));
				book.setBook_stock(rs.getInt(4));
				book.setPrice(rs.getDouble(5));
				book.setBook_cat(rs.getString(6));
				book.setImage(rs.getBytes(7));
				bookList.add(book);
			}
			conn.close();

		} catch (SQLException e) {

		}
		return bookList;
	}

}