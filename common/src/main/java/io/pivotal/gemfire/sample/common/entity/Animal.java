package io.pivotal.gemfire.sample.common.entity;

public class Animal {	
	private int id;
	int age;
	boolean isPet;
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public boolean isPet() {
		return isPet;
	}
	public void setPet(boolean isPet) {
		this.isPet = isPet;
	}	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Animal other = (Animal) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
}
