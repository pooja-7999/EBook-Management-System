package com.DAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.BookDtls;

public class BookDAOImpl implements BookDAO {

	private Connection conn;

	public BookDAOImpl(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public boolean addBooks(BookDtls b) {
		boolean f = false;
		try {
			String sql = "INSERT INTO book_dtls(bookName, author, price, bookCategory, status, photo, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, b.getBookName());
			ps.setString(2, b.getAuthor());

			// Handle price conversion
			String priceStr = b.getPrice();
			BigDecimal price = priceStr == null || priceStr.isEmpty() ? BigDecimal.ZERO : new BigDecimal(priceStr);
			ps.setBigDecimal(3, price);

			ps.setString(4, b.getBookCategory());
			ps.setString(5, b.getStatus());
			ps.setString(6, b.getPhotoName());
			ps.setString(7, b.getEmail());

			int i = ps.executeUpdate();
			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	@Override
	public List<BookDtls> getAllBooks() {
		List<BookDtls> list = new ArrayList<>();
		try {
			String sql = "SELECT * FROM book_dtls";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				BookDtls b = new BookDtls();
				b.setBookId(rs.getInt("bookId"));
				b.setBookName(rs.getString("bookName"));
				b.setAuthor(rs.getString("author"));

				// Retrieve price as BigDecimal
				BigDecimal price = rs.getBigDecimal("price");
				b.setPrice(price == null ? "0.00" : price.toString());

				b.setBookCategory(rs.getString("bookCategory"));
				b.setStatus(rs.getString("status"));
				b.setPhotoName(rs.getString("photo"));
				b.setEmail(rs.getString("email"));
				list.add(b);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public BookDtls getBookById(int id) {
		BookDtls b = null;
		try {
			String sql = "SELECT * FROM book_dtls WHERE bookId=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				b = new BookDtls();
				b.setBookId(rs.getInt("bookId"));
				b.setBookName(rs.getString("bookName"));
				b.setAuthor(rs.getString("author"));
				b.setPrice(rs.getString("price"));
				b.setBookCategory(rs.getString("bookCategory"));
				b.setStatus(rs.getString("status"));
				b.setPhotoName(rs.getString("photo"));
				b.setEmail(rs.getString("email"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public boolean updateEditBooks(BookDtls b) {
		boolean f = false;
		try {
			String sql = "UPDATE book_dtls SET bookName=?, author=?, price=?, status=? WHERE bookId=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, b.getBookName());
			ps.setString(2, b.getAuthor());

			// Handle price conversion
			String priceStr = b.getPrice();
			BigDecimal price = priceStr == null || priceStr.isEmpty() ? BigDecimal.ZERO : new BigDecimal(priceStr);
			ps.setBigDecimal(3, price);

			ps.setString(4, b.getStatus());
			ps.setInt(5, b.getBookId());

			int i = ps.executeUpdate();
			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	@Override
	public boolean deleteBooks(int id) {
		boolean f = false;
		try {
			String sql = "DELETE FROM book_dtls WHERE bookId=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			int i = ps.executeUpdate();
			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	@Override
	public List<BookDtls> getNewBook() {
		List<BookDtls> list = new ArrayList<>();
		try {
			String sql = "SELECT * FROM book_dtls WHERE bookCategory = ? AND status = ? ORDER BY bookId DESC LIMIT 4";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "New");
			ps.setString(2, "Active");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				BookDtls b = new BookDtls();
				b.setBookId(rs.getInt("bookId"));
				b.setBookName(rs.getString("bookName"));
				b.setAuthor(rs.getString("author"));
				b.setPrice(rs.getString("price"));
				b.setBookCategory(rs.getString("bookCategory"));
				b.setStatus(rs.getString("status"));
				b.setPhotoName(rs.getString("photo"));
				b.setEmail(rs.getString("email"));
				list.add(b);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<BookDtls> getRecentBooks() {
		List<BookDtls> list = new ArrayList<>();
		try {
			String sql = "SELECT * FROM book_dtls WHERE status = ? ORDER BY bookId DESC LIMIT 4";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "Active");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				BookDtls b = new BookDtls();
				b.setBookId(rs.getInt("bookId"));
				b.setBookName(rs.getString("bookName"));
				b.setAuthor(rs.getString("author"));
				b.setPrice(rs.getString("price"));
				b.setBookCategory(rs.getString("bookCategory"));
				b.setStatus(rs.getString("status"));
				b.setPhotoName(rs.getString("photo"));
				b.setEmail(rs.getString("email"));
				list.add(b);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<BookDtls> getOldBooks() {
		List<BookDtls> list = new ArrayList<>();
		try {
			String sql = "SELECT * FROM book_dtls WHERE bookCategory = ? AND status = ? ORDER BY bookId DESC LIMIT 4";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "Old");
			ps.setString(2, "Active");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				BookDtls b = new BookDtls();
				b.setBookId(rs.getInt("bookId"));
				b.setBookName(rs.getString("bookName"));
				b.setAuthor(rs.getString("author"));
				b.setPrice(rs.getString("price"));
				b.setBookCategory(rs.getString("bookCategory"));
				b.setStatus(rs.getString("status"));
				b.setPhotoName(rs.getString("photo"));
				b.setEmail(rs.getString("email"));
				list.add(b);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<BookDtls> getAllRecentBooks() {
		List<BookDtls> list = new ArrayList<>();
		try {
			String sql = "SELECT * FROM book_dtls WHERE status = ? ORDER BY bookId DESC";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "Active");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				BookDtls b = new BookDtls();
				b.setBookId(rs.getInt("bookId"));
				b.setBookName(rs.getString("bookName"));
				b.setAuthor(rs.getString("author"));
				b.setPrice(rs.getString("price"));
				b.setBookCategory(rs.getString("bookCategory"));
				b.setStatus(rs.getString("status"));
				b.setPhotoName(rs.getString("photo"));
				b.setEmail(rs.getString("email"));
				list.add(b);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<BookDtls> getAllNewBooks() {
		List<BookDtls> list = new ArrayList<>();
		try {
			String sql = "SELECT * FROM book_dtls WHERE bookCategory = ? AND status = ?  ORDER BY bookId DESC";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "New");
			ps.setString(2, "Active");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				BookDtls b = new BookDtls();
				b.setBookId(rs.getInt("bookId"));
				b.setBookName(rs.getString("bookName"));
				b.setAuthor(rs.getString("author"));
				b.setPrice(rs.getString("price"));
				b.setBookCategory(rs.getString("bookCategory"));
				b.setStatus(rs.getString("status"));
				b.setPhotoName(rs.getString("photo"));
				b.setEmail(rs.getString("email"));
				list.add(b);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<BookDtls> getAllOldBooks() {
		List<BookDtls> list = new ArrayList<>();
		try {
			String sql = "SELECT * FROM book_dtls WHERE bookCategory = ? AND status = ? ORDER BY bookId DESC";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "Old");
			ps.setString(2, "Active");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				BookDtls b = new BookDtls();
				b.setBookId(rs.getInt("bookId"));
				b.setBookName(rs.getString("bookName"));
				b.setAuthor(rs.getString("author"));
				b.setPrice(rs.getString("price"));
				b.setBookCategory(rs.getString("bookCategory"));
				b.setStatus(rs.getString("status"));
				b.setPhotoName(rs.getString("photo"));
				b.setEmail(rs.getString("email"));
				list.add(b);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<BookDtls> getBookByOld(String email, String cate) {

		List<BookDtls> list = new ArrayList<BookDtls>();
		BookDtls b = null;

		try {

			String sql = "select * from book_dtls where bookCategory=? and email=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, cate);
			ps.setString(2, email);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				b = new BookDtls();
				b.setBookId(rs.getInt("bookId"));
				b.setBookName(rs.getString("bookName"));
				b.setAuthor(rs.getString("author"));
				b.setPrice(rs.getString("price"));
				b.setBookCategory(rs.getString("bookCategory"));
				b.setStatus(rs.getString("status"));
				b.setPhotoName(rs.getString("photo"));
				b.setEmail(rs.getString("email"));
				list.add(b);
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return list;

	}

	@Override
	public boolean oldBookDelete(String email, String cat, int id) {

		boolean f = false;
		try {
			String sql = "delete from book_dtls where bookCategory=? and email=? and bookId=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, cat);
			ps.setString(2, email);
			ps.setInt(3, id);

			int i = ps.executeUpdate();
			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;
	}

	@Override
	public List<BookDtls> getBookBySearch(String ch) {
	    List<BookDtls> list = new ArrayList<>();
	    BookDtls b = null;

	    try {
	        String sql = "select * from book_dtls where (bookName like ? or author like ? or bookCategory like ?) and status = ?";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setString(1, "%" + ch + "%");
	        ps.setString(2, "%" + ch + "%");
	        ps.setString(3, "%" + ch + "%");
	        ps.setString(4, "Active");

	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            b = new BookDtls();
	            b.setBookId(rs.getInt("bookId"));
	            b.setBookName(rs.getString("bookName"));
	            b.setAuthor(rs.getString("author"));
	            b.setPrice(rs.getString("price"));
	            b.setBookCategory(rs.getString("bookCategory"));
	            b.setStatus(rs.getString("status"));
	            b.setPhotoName(rs.getString("photo"));
	            b.setEmail(rs.getString("email"));
	            list.add(b);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return list;
	}


	
	

}
