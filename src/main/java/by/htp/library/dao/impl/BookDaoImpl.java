package by.htp.library.dao.impl;

import static by.htp.library.dao.util.MySqlPropertyManager.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.htp.library.dao.BookDao;
import by.htp.library.entity.Author;
import by.htp.library.entity.Book;

/*
 * дореализовать методы
 */

public class BookDaoImpl implements BookDao{
	
	private static final String SELECT_BOOK_BY_ID = "SELECT b.Title, a.Name, a.Surname, a.Birthday FROM Book b JOIN Author a on a.id_author = b.id_author WHERE b.id_book = ?";
	private static final String SELECT_ALL_BOOK = "SELECT * FROM Book b JOIN Author a on a.id_author = b.id_author";
	private static final String INSERT_BOOK = "INSERT INTO Book(id_book, Title, Prod_year) VALUES(?,?,?);";// INSERT INTO Author(Name, Surname, Birthday) VALUES(?, ?, ?);";
	private static final String INSERT_AUTHOR =  "INSERT INTO Author(Name, Suranme, Birthday) VALUES(?, ?, ?);";
	private static final String SELECT_ID_BOOK = "SELECT * from Book ";
	private static final String DELETE_ID_BOOK = "DELETE from Book where id_book = ?";
	private static final String UPDATE_ID_BOOK = "UPDATE Book SET id_book = ?, Title = ?, Prod_year = ? where id_book = ?";
	private List <Book> books = new ArrayList<Book>();
	


	@Override
	public Book read(int id) {
		Author author = null;
		Book book = null;
		
		try(Connection connection = DriverManager.getConnection(getUrl(), getLogin(), getPass())){
			PreparedStatement ps = connection.prepareStatement(SELECT_BOOK_BY_ID);
			
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
	
			if(rs.next()) {
				book = buildBook(rs);
			}
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return book;
	}
	
	@Override
	public Book readAll() {
		Author author = null;
		Book book = null;
		
		try(Connection connection = DriverManager.getConnection(getUrl(), getLogin(), getPass())){
			
			PreparedStatement psAll = connection.prepareStatement(SELECT_ALL_BOOK);
			ResultSet rsAll = psAll.executeQuery();
			
			while(rsAll.next()) {
				book = buildBook(rsAll);
				books.add(book);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return book;
	}

	@Override
	public List<Book> list() {
		List<Book> books = new ArrayList<Book>();
		return books;
	}
	
	public int insertAuthor(Author author) {
		int a = -1;
		
		try(Connection connection = DriverManager.getConnection(getUrl(), getLogin(), getPass())){
			
			
			PreparedStatement ps = connection.prepareStatement(INSERT_AUTHOR);//, Statement.RETURN_GENERATED_KEYS);
		
		ps.setString(1, author.getName());
		ps.setString(2, author.getSurname());
		ps.setDate(3, author.getBirthDate());
		
		ResultSet rs = ps.getGeneratedKeys();
		if(rs.next()) {
			a = rs.getInt(1);
		}
		return a;
		
	} catch(SQLException e) {
		e.printStackTrace();
	}
	return a;
	}

	@Override
	public int insert(Book book) {
		int a = -1;
		boolean isExistId = false;
		try(Connection connection = DriverManager.getConnection(getUrl(), getLogin(), getPass())){
			PreparedStatement psSel = connection.prepareStatement(SELECT_ID_BOOK);
			
			ResultSet rsAll = psSel.executeQuery();
			int id = book.getId();
			while(rsAll.next()) {
				if(rsAll.getInt("id_book") == id) {
					System.out.println("The id is existing in database");
					isExistId = true;
					break;
				}
			}
			
			if(isExistId == false) {
				PreparedStatement ps = connection.prepareStatement(INSERT_BOOK);
			
				ps.setInt(1, book.getId());
				ps.setString(2, book.getTitle());
				ps.setString(3, book.getYearOfProd());
		
				ps.executeUpdate();
				System.out.println(book + " was inserted");
				
	//			a = ps.;//.executeUpdate();
//				ResultSet rs = ps.getGeneratedKeys();
		
				return a;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return a;
	}

	@Override
	public void delete(Book book) {
	
		boolean isExistId = true;
		
		try(Connection connection = DriverManager.getConnection(getUrl(), getLogin(), getPass())){
			PreparedStatement psSel = connection.prepareStatement(SELECT_ID_BOOK);
			
			ResultSet rsAll = psSel.executeQuery();
			int id = book.getId();
			
			while(rsAll.next()) {
				System.out.println(rsAll.getInt("id_book"));
				if(rsAll.getInt("id_book") == id) {
					isExistId = true;
					break;
				} else {
					isExistId = false;
				}
			}
			
			if(isExistId == false) {
				System.out.println("There is not  such row with id = " + id);
			}
			
			if(isExistId == true) {
				PreparedStatement ps = connection.prepareStatement(DELETE_ID_BOOK);
				ps.setInt(1, id);
				ps.executeUpdate();
				System.out.println(book + " was deleted");
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(Book book) {
		
		boolean isExistId = true;
		
		try(Connection connection = DriverManager.getConnection(getUrl(), getLogin(), getPass())){
			PreparedStatement psSel = connection.prepareStatement(SELECT_ID_BOOK);
			
			ResultSet rsAll = psSel.executeQuery();
			int id = book.getId();
			
			while(rsAll.next()) {
				if(rsAll.getInt("id_book") == id) {
					isExistId = true;
					break;
				} else {
					isExistId = false;
				}
			}
			
			if(isExistId == false) {
				System.out.println("There is not  such row with id = " + id);
			}
			
			if(isExistId == true) {
				PreparedStatement ps = connection.prepareStatement(UPDATE_ID_BOOK);
				
				ps.setInt(1, book.getId());
				ps.setString(2, book.getTitle());
				ps.setString(3, book.getYearOfProd());
				ps.setInt(4, id);
		
				ps.executeUpdate();
				System.out.println(book + " was updated");
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	private Book buildBook(ResultSet rs) throws SQLException {
		Book book = new Book();
		book.setId(rs.getInt("id_book"));
		book.setTitle(rs.getString("Title"));
		book.setYearOfProd(rs.getString("Prod_year"));
		book.setAuthor(buildAuthor(rs));
		return book;
	}
	
	private Author buildAuthor(ResultSet rs) throws SQLException {
		Author author = new Author();
		author.setName(rs.getString("Name"));
		author.setSurname(rs.getString("Surname"));
		author.setBirthDate(rs.getDate("Birthday"));
		return author;
	}
	
	public List<Book> getBooks() {
		return books;
	}

}
