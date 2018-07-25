package by.htp.library.dao;

import java.util.List;

import by.htp.library.entity.Book;

public interface BookDao {// DATA acsess object
	
	
	Book read(int id);
	
	List<Book> list();
	
	int insert(Book book);
	
	void delete(Book book);
	
	void update(Book book);

	Book readAll();

	List<Book> getBooks();


}
