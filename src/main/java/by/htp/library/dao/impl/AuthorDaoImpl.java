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

import by.htp.library.dao.AuthorDao;
import by.htp.library.dao.BookDao;
import by.htp.library.entity.Author;
import by.htp.library.entity.Book;

/*
 * дореализовать методы
 */

public class AuthorDaoImpl implements AuthorDao{
	
	private static final String SELECT_AUTHOR_BY_ID = "SELECT a.Name, a.Surname, a.Birthday, b.Title, FROM Author a JOIN Book b on a.id_author = b.id_author WHERE a.id_author = ?";
	private static final String SELECT_ALL_AUTHORS = "SELECT * FROM Author;";// b JOIN Author a on a.id_author = b.id_author";
	private static final String INSERT_AUTHOR = "INSERT INTO Author(Name, Surname, Birthday) VALUES(?, ?, ?);";

	private List <Author> authors = new ArrayList<Author>();
	
	@Override
	public Author read(int id) {
		Author author = null;
		
		try(Connection connection = DriverManager.getConnection(getUrl(), getLogin(), getPass())){
			PreparedStatement ps = connection.prepareStatement(SELECT_AUTHOR_BY_ID);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
	
			if(rs.next()) {
				author = buildAuthor(rs);
			}
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return author;
	}
	
	@Override
	public List<Author> list() {
		return null;
	}
	
	@Override
	public int insert(Author author) {
		int a = -1;
		
		try(Connection connection = DriverManager.getConnection(getUrl(), getLogin(), getPass())){
			PreparedStatement ps = connection.prepareStatement(INSERT_AUTHOR, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, author.getName());
			ps.setString(2, author.getSurname());
			ps.setDate(3, author.getBirthDate());
	
			ps.execute();
			
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
	public void delete(Author author) {
	}
	@Override
	public void update(Author author) {
	}
	@Override
	public Author readAll() {
		return null;
	}
	@Override
	public List<Author> getAuthors() {
		return null;
	}
	
	private Author buildAuthor(ResultSet rs) throws SQLException {
		Author author = new Author();
		author.setName(rs.getString("Name"));
		author.setSurname(rs.getString("Surname"));
		author.setBirthDate(rs.getDate("Birthday"));
		return author;
	}



}
