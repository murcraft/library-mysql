package by.htp.library.run;

import java.sql.Date;

import by.htp.library.dao.AuthorDao;
import by.htp.library.dao.BookDao;
import by.htp.library.dao.impl.AuthorDaoImpl;
import by.htp.library.dao.impl.BookDaoImpl;
import by.htp.library.entity.Author;
import by.htp.library.entity.Book;

public class MainLibraryController {

	public static void main(String[] args) {
		
		BookDao daoB = new BookDaoImpl();
		AuthorDao daoA = new AuthorDaoImpl();

//		Book book = dao.read(1);
//		System.out.println(book);
		
		
		
		Book book = daoB.readAll();
		System.out.println(daoB.getBooks());
		
//		Author author = new Author(1, "J", "Horstman", new Date(1965-10-04));
//		daoA.insert(author);
//		System.out.println();
		
		Book book1 = new Book(16, "Мама мыла раму", "2016", new Author(2, "Ф", "Корнеев", new Date(1965-10-04)));
//		int a = daoB.insert(book1); 
//		System.out.println(a);
//		daoB.delete(book1);
		daoB.update(book1);
		
	}

}
