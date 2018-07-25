package by.htp.library.entity;

import java.util.Calendar;

public class Employee {
	
	private String name;
	private String surname;
	private Calendar readingBookDate;
	
	public Employee() {
		super();
	}
	
	public Employee(String name, String surname, Calendar readingBookDate) {
		super();
		this.name = name;
		this.surname = surname;
		this.readingBookDate = readingBookDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Calendar getReadingBookDate() {
		return readingBookDate;
	}

	public void setReadingBookDate(Calendar readingBookDate) {
		this.readingBookDate = readingBookDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((readingBookDate == null) ? 0 : readingBookDate.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
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
		Employee other = (Employee) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (readingBookDate == null) {
			if (other.readingBookDate != null)
				return false;
		} else if (!readingBookDate.equals(other.readingBookDate))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee " + name + " " + surname + ", date of reading" + readingBookDate + "]";
	}

}
