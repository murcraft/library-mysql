package by.htp.library.entity;

import java.io.Serializable;

public class Book implements Serializable{

	private static final long serialVersionUID = 8328951733306588147L;
	private int id;
	private String title;
	private Author author;
	private String yearOfProd;
	
	public Book() {
		super();
	}
	
	public Book(int id, String title, String yearOfProd, Author author) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.setYearOfProd(yearOfProd);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public String getYearOfProd() {
		return yearOfProd;
	}


	public void setYearOfProd(String yearOfProd) {
		this.yearOfProd = yearOfProd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + id;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((yearOfProd == null) ? 0 : yearOfProd.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (id != other.id)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (yearOfProd == null) {
			if (other.yearOfProd != null)
				return false;
		} else if (!yearOfProd.equals(other.yearOfProd))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", yearOfProd=" + yearOfProd + "]";
	}
	
	
	
	

}
