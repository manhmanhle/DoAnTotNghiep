package com.laptrinhjavaweb.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.service.IReportService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRProperties;
import net.sf.jasperreports.export.SimpleDocxReportConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
@Service
public class ReportService implements IReportService{

	@Override
	public String exportReport(JSONObject object)
			throws FileNotFoundException, JRException, IOException, SQLException, ClassNotFoundException {
		   String pathPdf = "C:\\file\\";
	        String pathJP = "C:\\file\\jasper\\";
	        String b64="";
	        String tenfile =(String) object.get("tenfile");
	        String type =(String) object.get("type");
	        String iddv_check=(String) object.get("iddv");
	        String macb=(String) object.get("macb");
	        Long iddv =Long.parseLong(iddv_check);
	        //get file local
	        InputStream reportStream = new FileInputStream(pathJP+"gv_cb.jrxml");
	        //set fonts
			  String defaultPDFFont = "Times New Roman";
			  JRProperties.setProperty("net.sf.jasperreports.awt.ignore.missing.font","true"); 
			  JRProperties.setProperty("net.sf.jasperreports.default.font.name", defaultPDFFont);
			//gener report
	        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
	        Map<String,Object> parameters = new HashMap<>();  
	        if(iddv != 0)
	        {
	          parameters.put("iddv",iddv);
	        }
	        if(macb.equals("") == false)
	        {
	        	parameters.put("macb",macb);
	        }
	        Connection connect = null;
	        Class.forName("com.mysql.jdbc.Driver");
	        connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/springmvc","root","123456");
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connect);
	        if (type.equalsIgnoreCase("xlsx")) {
	        	File xlsFile = new File(pathPdf+tenfile+".xlsx");
	            JRXlsxExporter Xlsxexporter = new JRXlsxExporter();
	            Xlsxexporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	            Xlsxexporter.setParameter(JRExporterParameter.OUTPUT_FILE, xlsFile);
	            Xlsxexporter.exportReport();
	            byte[] inFileBytes = Files.readAllBytes(Paths.get(pathPdf+tenfile+".xlsx")); 
	            b64 = java.util.Base64.getEncoder().encodeToString(inFileBytes);
	        }
	        if (type.equalsIgnoreCase("pdf")) {
	            JasperExportManager.exportReportToPdfFile(jasperPrint, pathPdf+tenfile+".pdf");
	            byte[] inFileBytes = Files.readAllBytes(Paths.get(pathPdf+tenfile+".pdf")); 
	            b64 = java.util.Base64.getEncoder().encodeToString(inFileBytes);
	        }
	        if (type.equalsIgnoreCase("docx")) {
	        	File xlsFile = new File(pathPdf+tenfile+".docx");
	        	JRDocxExporter export = new JRDocxExporter();
	        	export.setExporterInput(new SimpleExporterInput(jasperPrint));
	        	export.setExporterOutput(new SimpleOutputStreamExporterOutput(xlsFile));

	        	SimpleDocxReportConfiguration config = new SimpleDocxReportConfiguration();
	        	//config.setFlexibleRowHeight(true); //Set desired configuration

	        	export.setConfiguration(config);            
	        	export.exportReport();
	        	 byte[] inFileBytes = Files.readAllBytes(Paths.get(pathPdf+tenfile+".docx")); 
	             b64 = java.util.Base64.getEncoder().encodeToString(inFileBytes);
	        }
	        return b64;
	}
	
}
