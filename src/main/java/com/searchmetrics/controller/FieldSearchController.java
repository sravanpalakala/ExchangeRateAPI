package com.searchmetrics.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.searchmetrics.service.UserService;

@RestController
public class FieldSearchController {
	
	@GetMapping("/api/getfieldsearchresults")
	@ResponseBody
	public String getFieldSearchResults(@RequestParam String cropType,@RequestParam String city, @RequestParam String userName,@RequestParam String maxTemp)throws Exception{

		System.out.println(" in API CALL");
		//userSer.userSearch("paddy", "Berlin", "sravan", "20");
		UserService  service  = new UserService();
		
		   List<Object> list=      service.userSearch(cropType, city, userName, maxTemp);
			System.out.println(" AFTER SERVICE called");
		   ObjectMapper mapper = new ObjectMapper();
		
		   String simpleJSON = mapper.writeValueAsString(list);
		   
		   return simpleJSON;
		
		
	}
}
