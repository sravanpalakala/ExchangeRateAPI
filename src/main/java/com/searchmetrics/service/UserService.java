package com.searchmetrics.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.searchmetrics.model.Field;
import com.searchmetrics.model.FieldCondition;
import com.searchmetrics.model.User;

public class UserService {
	
		
	public List<Object> filterSearchResults(User user) {
		List<Object> finalSearchResults = new ArrayList<Object>();
		user.getUserName();
		finalSearchResults.add(user.getUserName());
		finalSearchResults.add(user.getSpecialisation());
		Set<Field> fld = user.getFields();

		List<Object> fieldList = new ArrayList<Object>(fld);
		Object[] objects = fieldList.toArray();

		for (int i = 0; i < objects.length; i++) {
			Object object = objects[i];
			Field fld1 = (Field) object;
			//System.out.println("field values:" + fld1.getFieldName());
			finalSearchResults.add(fld1.getFieldName());
			finalSearchResults.add(fld1.getCity());

			finalSearchResults.add(fld1.getCropType());

			Set<FieldCondition> fldConditions = fld1.getFieldConditions();
			List<Object> fldConditionList = new ArrayList<Object>(fldConditions);
			Object[] fldCondobjects = fldConditionList.toArray();

			for (int j = 0; j < fldCondobjects.length; j++) {

				Object fldCondobject = fldCondobjects[j];
				FieldCondition fldCond1 = (FieldCondition) fldCondobject;
				//System.out.println("FieldCondition values:" + fldCond1.getMinTemperature());

				finalSearchResults.add(fldCond1.getMinTemperature());
				finalSearchResults.add(fldCond1.getMaxTemperature());
				finalSearchResults.add(fldCond1.getAvgTemperature());
				finalSearchResults.add(fldCond1.getCloudiness());
				finalSearchResults.add(fldCond1.getVegetation());
			}

		}

		return finalSearchResults;
	}
	
	
	
	public List<Object> userSearch(String cropType, String city, String userName, String maxTemp) {

		List<Object> searchResult = new ArrayList<Object>();

		Set<FieldCondition> fieldConditions = new HashSet<FieldCondition>(0);
		FieldCondition fieldCond1 = new FieldCondition();

		fieldCond1.setFieldConditionID(101);

		fieldCond1.setDate(new Date());

		fieldCond1.setMinTemperature("11");

		fieldCond1.setMaxTemperature("20");

		fieldCond1.setAvgTemperature("16");

		fieldCond1.setCloudiness("moderate");
		fieldCond1.setVegetation("high");

		fieldConditions.add(fieldCond1);

		Set<Field> fields = new HashSet<Field>();
		Field field1 = new Field();

		field1.setFieldID(51);

		field1.setFieldName("Field_no_1");

		field1.setCropType("paddy");

		field1.setCity("Berlin");

		field1.setLatitude("103");

		field1.setLongitude("8989");

		field1.setFieldConditions(fieldConditions);

		fields.add(field1);

		User user = new User();

		user.setUserID(11);
		user.setUserName("sravan");
		user.setSpecialisation("Farmer");

		user.setFields(fields);

		
		if (userName.equalsIgnoreCase(user.getUserName())) {

			searchResult.add(user);

			Set<Field> fld = user.getFields();

			List<Object> fieldList = new ArrayList<Object>(fld);
			Object[] objects = fieldList.toArray();

			for (int i = 0; i < objects.length; i++) {
				Object object = objects[i];
				Field fld1 = (Field) object;
				if (cropType.equalsIgnoreCase(fld1.getCropType()) && city.equalsIgnoreCase(fld1.getCity())) {
					searchResult.add(fld1);

				}

				Set<FieldCondition> fldConditions = fld1.getFieldConditions();
				List<Object> fldConditionList = new ArrayList<Object>(fldConditions);
				Object[] fldCondobjects = fldConditionList.toArray();

				for (int j = 0; j < fldCondobjects.length; j++) {

					Object fldCondobject = fldCondobjects[j];
					FieldCondition fldCond1 = (FieldCondition) fldCondobject;
					if (maxTemp.equalsIgnoreCase(fldCond1.getMaxTemperature())) {

						searchResult.add(fldCond1);
					}
				} // end of fld conditions

			} // end of fields

		} // end of user 
		
		
		searchResult = filterSearchResults(user);
		
		return searchResult;
	}// method end
		

	}
