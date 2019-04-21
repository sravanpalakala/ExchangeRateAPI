package com.searchmetrics.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * The persistent class for the USER database table.
 * 
 */
@Entity
@Table(name = "USER")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM USER u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private int userID;

	
	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "SPECIALISATION")
	private String specialisation;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "User_Field", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "FIELD_ID") })
	Set<Field> fields = new HashSet<Field>();
	
	public User() {
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSpecialisation() {
		return specialisation;
	}

	public void setSpecialisation(String specialisation) {
		this.specialisation = specialisation;
	}
	
	public Set<Field> getFields() {
		return fields;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fields == null) ? 0 : fields.hashCode());
		result = prime * result + ((specialisation == null) ? 0 : specialisation.hashCode());
		result = prime * result + userID;
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		User other = (User) obj;
		if (fields == null) {
			if (other.fields != null)
				return false;
		} else if (!fields.equals(other.fields))
			return false;
		if (specialisation == null) {
			if (other.specialisation != null)
				return false;
		} else if (!specialisation.equals(other.specialisation))
			return false;
		if (userID != other.userID)
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	public void setFields(Set<Field> fields) {
		this.fields = fields;
	}


	

}