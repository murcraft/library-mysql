package by.htp.library.dao;

import java.util.List;

import by.htp.library.entity.Author;

public interface AuthorDao {
	
	Author read(int id);
	
	List<Author> list();
	
	int insert(Author author);
	
	void delete(Author author);
	
	void update(Author author);

	Author readAll();

	List<Author> getAuthors();

}
