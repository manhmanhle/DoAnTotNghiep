package com.laptrinhjavaweb.api.web;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.service.INewService;
import com.laptrinhjavaweb.service.IReportService;

import net.sf.jasperreports.engine.JRException;

@RestController(value = "reportAPIOfAdmin")
public class ReportAPI {
	
	@Autowired
	private IReportService IReportService;
   	
	@PostMapping("/api/report")
	public String createNew(@RequestBody String list) throws FileNotFoundException, ClassNotFoundException, JRException, IOException, SQLException {
		Object obj=JSONValue.parse(list);     
		JSONObject jsonObject = (JSONObject) obj; 
		return IReportService.exportReport(jsonObject);
	}

}
