package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.MyDBConnect;
import model.Book;
import model.OBSUser;
import model.PurchaseOrder;

public class PurchaseOrderDao {

	public int purchaseBook(PurchaseOrder order) {
		Connection conn = MyDBConnect.dbConnect();
		int purchaseBookCount = 0;

		try {
			// Close auto commit to ensure all SQL statements have no issue before commit
			conn.setAutoCommit(false);
			PreparedStatement ps = conn
					.prepareStatement("update books set BOOK_STOCK  = book_stock -? where book_id=?");
			ps.setInt(1, order.getQuantity());
			ps.setInt(2, order.getBook().getBook_id());
			ps.executeUpdate();
			ps = conn.prepareStatement("insert into purchase_order values(po_seq.nextval,?,?,?,?,?)");
			ps.setInt(1, order.getBook().getBook_id());
			ps.setString(2, order.getCustomer().getUser_id());
			ps.setInt(3, order.getQuantity());
			ps.setDate(4, new Date(order.getPurchaseDate().getTime()));
			ps.setDouble(5, order.getPrice());

			purchaseBookCount = ps.executeUpdate();
			// No error thrown, commit the changes to database
			conn.commit();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return purchaseBookCount;
	}

	public List<PurchaseOrder> purchaseList(OBSUser user) {
		List<PurchaseOrder> list = new ArrayList<>();
		Connection conn = MyDBConnect.dbConnect();
		try {
			PreparedStatement ps = conn.prepareStatement(
					"select po.order_id, b.book_img, b.book_name, b.book_author, b.book_cat, po.quantity, po.price, obsu.user_id, po.purchase_date from purchase_order po inner join books b on po.book_id = b.book_id inner join obsuser obsu on po.obsuser_id = obsu.user_id where po.obsuser_id=? order by order_id");
			ps.setString(1, user.getUser_id());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				PurchaseOrder order = new PurchaseOrder();
				order.setOrder_id(rs.getInt(1));
				Book book = new Book();
				book.setImage(rs.getBytes(2));
				book.setBook_name(rs.getString(3));
				book.setBook_author(rs.getString(4));
				book.setBook_cat(rs.getString(5));
				order.setBook(book);
				order.setQuantity(rs.getInt(6));
				order.setPrice(rs.getDouble(7));
				order.setCustomer(new OBSUser(rs.getString(8)));
				order.setPurchaseDate(rs.getDate(9));
				list.add(order);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
