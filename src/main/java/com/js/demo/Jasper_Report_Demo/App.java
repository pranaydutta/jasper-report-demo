package com.js.demo.Jasper_Report_Demo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.exception.DRException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ClassNotFoundException
    {
    	
    	System.out.println("Hello");
//Added comment
    	JasperReportBuilder report = DynamicReports.report();
    	report
  	  .columns(
  	      Columns.column("Customer Id", "id", DataTypes.integerType()),
  	      Columns.column("First Name", "first_name", DataTypes.stringType()),
  	      Columns.column("Last Name", "last_name", DataTypes.stringType()),
  	      Columns.column("Date", "ORDER_DATE", DataTypes.stringType()))
  	  .title(//title of the report
  	      Components.text("SimpleReportExample")
  		  .setHorizontalAlignment(HorizontalAlignment.CENTER))
  		  .pageFooter(Components.pageXofY())//show page number on the page footer
  		  .setDataSource("SELECT id, first_name, last_name, ORDER_DATE FROM customer", 
  				establishConnection());
    	
    	try {
            //show the report
	report.show();

            //export the report to a pdf file
	report.toPdf(new FileOutputStream("D:/report.pdf"));
} catch (DRException e) {
	e.printStackTrace();
} catch (FileNotFoundException e) {
	e.printStackTrace();
}
    }
    
    
    public static Connection establishConnection() throws ClassNotFoundException
    {
    Connection connection = null;
    try
    {
    Class.forName("oracle.jdbc.driver.OracleDriver");
    String oracleURL = "jdbc:oracle:thin:@localhost:1521:orcl";
    connection = DriverManager.getConnection(oracleURL,"pranay","dutta");
    connection.setAutoCommit(false);
    }
    catch(SQLException exception)
    {
    exception.printStackTrace();
    }
    return connection;

    }
    
    
}
