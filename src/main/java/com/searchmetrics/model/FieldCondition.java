package com.searchmetrics.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the USER database table.
 * 
 */
@Entity
@Table(name = "FIELD_CONDITION")
@NamedQuery(name = "FieldCondition.findAll", query = "SELECT u FROM FieldCondition u")
public class FieldCondition implements Serializable {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((avgTemperature == null) ? 0 : avgTemperature.hashCode());
		result = prime * result + ((cloudiness == null) ? 0 : cloudiness.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((field == null) ? 0 : field.hashCode());
		result = prime * result + fieldConditionID;
		result = prime * result + ((maxTemperature == null) ? 0 : maxTemperature.hashCode());
		result = prime * result + ((minTemperature == null) ? 0 : minTemperature.hashCode());
		result = prime * result + ((vegetation == null) ? 0 : vegetation.hashCode());
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
		FieldCondition other = (FieldCondition) obj;
		if (avgTemperature == null) {
			if (other.avgTemperature != null)
				return false;
		} else if (!avgTemperature.equals(other.avgTemperature))
			return false;
		if (cloudiness == null) {
			if (other.cloudiness != null)
				return false;
		} else if (!cloudiness.equals(other.cloudiness))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (field == null) {
			if (other.field != null)
				return false;
		} else if (!field.equals(other.field))
			return false;
		if (fieldConditionID != other.fieldConditionID)
			return false;
		if (maxTemperature == null) {
			if (other.maxTemperature != null)
				return false;
		} else if (!maxTemperature.equals(other.maxTemperature))
			return false;
		if (minTemperature == null) {
			if (other.minTemperature != null)
				return false;
		} else if (!minTemperature.equals(other.minTemperature))
			return false;
		if (vegetation == null) {
			if (other.vegetation != null)
				return false;
		} else if (!vegetation.equals(other.vegetation))
			return false;
		return true;
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FIELD_CONDITION_ID")
	private int fieldConditionID;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE", unique = true, nullable = false, length = 10)
	private Date date;

	public Date getDate() {
		return this.date;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FIELD_ID", nullable = false)
	private Field field;

	public Field getField() {
		return this.field;
	}

	@Column(name = "MIN_TEMP")
	private String minTemperature;

	@Column(name = "MAX_TEMP")
	private String maxTemperature;

	@Column(name = "AVG_TEMP")
	private String avgTemperature;

	@Column(name = "CLOUDINESS")
	private String cloudiness;

	@Column(name = "VEGETATION")
	private String vegetation;

	public int getFieldConditionID() {
		return fieldConditionID;
	}

	public void setFieldConditionID(int fieldConditionID) {
		this.fieldConditionID = fieldConditionID;
	}

	public String getMinTemperature() {
		return minTemperature;
	}

	public void setMinTemperature(String minTemperature) {
		this.minTemperature = minTemperature;
	}

	public String getMaxTemperature() {
		return maxTemperature;
	}

	public void setMaxTemperature(String maxTemperature) {
		this.maxTemperature = maxTemperature;
	}

	public String getAvgTemperature() {
		return avgTemperature;
	}

	public void setAvgTemperature(String avgTemperature) {
		this.avgTemperature = avgTemperature;
	}

	public String getCloudiness() {
		return cloudiness;
	}

	public void setCloudiness(String cloudiness) {
		this.cloudiness = cloudiness;
	}

	public String getVegetation() {
		return vegetation;
	}

	public void setVegetation(String vegetation) {
		this.vegetation = vegetation;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setField(Field field) {
		this.field = field;
	}

}
