package com.laptrinhjavaweb.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import org.json.simple.JSONObject;

import net.sf.jasperreports.engine.JRException;

public interface IReportService {
	String exportReport(JSONObject object) throws FileNotFoundException, JRException, IOException, SQLException, ClassNotFoundException;
}
