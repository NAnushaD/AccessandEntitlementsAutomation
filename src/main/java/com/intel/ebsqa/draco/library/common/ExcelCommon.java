package com.intel.ebsqa.draco.library.common;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.intel.ebsqa.listeners.customEventListener;
import com.intel.ebsqa.wrappers.LoggerWrapper;

public class ExcelCommon{

	static LoggerWrapper log = new LoggerWrapper(customEventListener.class);

	public FileInputStream fileinput = null;
	public FileOutputStream fileoutput = null;
	public XSSFWorkbook workbook = null;
	public XSSFSheet sheet = null;
	public XSSFRow row = null;
	public XSSFCell cell = null;
	String xlFilePath; 
	private DataFormatter formatter = null;


	public ExcelCommon(String xlFilePath) throws IOException
	{
		formatter = new DataFormatter();
		this.xlFilePath =xlFilePath;
		fileinput = new FileInputStream(xlFilePath);
		workbook = new XSSFWorkbook (fileinput);
	}



	/**
	 * To get single cell value from excel sheet
	 * @param sheetName - Name of the excel sheet from which we want to retrieve data
	 * @param colName - Name of the column name which will act as search element(column header)
	 * @param rowNum - Row Number from where the cell value will be extracted
	 * @author unatarax
	 * @since 2019-12-10
	 */

	public String GetCellValue(String sheetName, String colName, int rowNum)
	{
		int colNum = -1;  
		try
		{		
			sheet = workbook.getSheet(sheetName);
			row =sheet.getRow(0);
			for(int i =0; i <row.getLastCellNum(); i++) 
			{
				if (row.getCell(i).getStringCellValue().trim().equals(colName)) {
					colNum=i;  
				}
			}
			row = sheet.getRow(rowNum -1);  cell = row.getCell(colNum); 
			if(cell.getCellType()== CellType.STRING) {
				return cell.getStringCellValue(); 
			}
			else if(cell.getCellType()== CellType.NUMERIC || cell.getCellType()==CellType.FORMULA)
			{
				String cellValue = String.valueOf(cell.getNumericCellValue());
				if (DateUtil.isCellDateFormatted(cell))
				{								
					log.info(cell.getDateCellValue());
					cellValue =cell.getDateCellValue().toString();
				}
				return cellValue;
			}
			else if(cell.getCellType()== CellType.BLANK) {
				return ""; 
			}
			else 
			{
				return String.valueOf(cell.getBooleanCellValue());
			}
		}
		catch (Exception ex)
		{			
			log.info(ex.getMessage());
			return "No Matched Value";
		}
	}
	
	
	

	/**
	 * To update single cell value of excel sheet
	 * @param - filePath - file path of the excel sheet
	 * @param dataSheetName - Name of the excel sheet from which we want to retrieve data
	 * @param searchcolumnName - Name of the column name which will act as search element(column header)
	 * @param searchcolumnValue - ColumnValue of search element
	 * @param updateColumnName - ColumnName of update element
	 * @param value - value to update in the cell
	 * @throws IOException
	 * @author unatarax
	 * @since 2019-12-10
	 */
	public void UpdateCellValue(String filePath, String dataSheetName, String searchColumnName, String searchColumnValue, String updateColumnName, String value) throws IOException
	{
		int searchColNum=-1,updateColNum = -1; 
		String cellvalue = null ;
		try
		{
			Sheet sheet = workbook.getSheet(dataSheetName);
			Row row = sheet.getRow(0);
			row = sheet.getRow(0);	
			for (int i=0; i<row.getLastCellNum(); i++)
			{
				Cell headerCell =  row.getCell(i);  
				cellvalue = getCellValuebyType(headerCell, i); 
				if(cellvalue.equals(searchColumnName)) 
				{
					searchColNum=i;  	
				}
				else if (cellvalue.equals(updateColumnName)) { 
					updateColNum=i; 	
				}
			}
			int searchrow=0;	
			for (Row row1 : sheet) { 	
				if(row1.getCell(searchColNum).getStringCellValue().equals(searchColumnValue)){ 
					searchrow = row1.getRowNum(); 
				}
			}	
			Row updateRow = sheet.getRow(searchrow); 
			Cell updateCell = updateRow.getCell(updateColNum); 
			if (updateCell==null) 
			{
				updateCell= updateRow.createCell(updateColNum);
			}
			updateCell.setCellValue(value);	
			fileoutput = new FileOutputStream(filePath);
			workbook.write(fileoutput);
			fileoutput.close();
		}
		catch (Exception ex) {
			log.error(ex.getMessage());
		}
	}
       
	


	/**
	 * To check the cell type
	 * @param cell
	 * @param cellIndex
	 * @return cellvalue
	 * @author unatarax
	 * @since 2019-12-12
	 */
	private String getCellValuebyType(Cell cell, int CellIndex) {
		String cellValue = null;
		try  {		
			CellType type = cell.getCellType();
			if(type.name().equals("STRING")) {
				log.info(cell.getStringCellValue());
				cellValue= cell.getStringCellValue();
			}
			else if (DateUtil.isCellDateFormatted(cell)) {
				log.info(cell.getDateCellValue());
				cellValue= cell.getDateCellValue().toString();
			}
			else if (type.name().equals("NUMERIC")) {
				log.info(cell.getNumericCellValue());
				cellValue= Double.toString(cell.getNumericCellValue());
			}		
		}
		catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return cellValue;
	}




	/**
	 * To get entire row from excel sheet
	 * @param - filePath - file path of the excel sheet
	 * @param sheetName - Name of the excel sheet from which we want to retrieve data
	 * @param rowName -  Name of the row to retrieve the data
	 * @param columnName - Name of the column which will act as search element(column header)
	 * @author unatarax
	 * @throws IOException
	 * @since 2019-12-12
	 */
	public ArrayList<String> GetRowFromExcelSheet(String filePath, String sheetName, String rowName, String columnName) throws IOException
	{	
		ArrayList<String> arrayList = null;
		int k=0;int column =0;
		boolean sheetExists=false;
		try {
			arrayList=new ArrayList<String>();
			FileInputStream filestream=new FileInputStream(filePath);
			XSSFWorkbook workbook=new XSSFWorkbook(filestream);		
			int sheets = workbook.getNumberOfSheets();	
			for (int i=0; i<sheets; i++)
			{
				if(workbook.getSheetName(i).equalsIgnoreCase(sheetName))
				{
					sheetExists=true;
					XSSFSheet sheet = workbook.getSheetAt(i);				
					Iterator<Row> rows = sheet.iterator();
					Row firstrow = rows.next();				
					Iterator<Cell> cellvalue = firstrow.cellIterator();						
					while(cellvalue.hasNext())
					{
						Cell value = cellvalue.next();
						if (!value.getStringCellValue().trim().equalsIgnoreCase(columnName.trim()))
						{
							k++;	
						}	
						column = k;
						log.info("column Name - '"+columnName+"' Not Available in Sheet");
						break;
					}
					log.info("column index is : " + column);	
					while(rows.hasNext())
					{
						Row r =rows.next();						
						CellType cell = r.getCell(column).getCellType();						
						switch(cell) {
							case STRING:											
								if(r.getCell(column).getStringCellValue().equalsIgnoreCase(rowName))									
								{
									Iterator<Cell> celldata = r.cellIterator();
									while (celldata.hasNext())
									{
										Cell c = celldata.next();										
										arrayList.add(getCellValuebyType(c, column));										
									}
								}
								break;
							case NUMERIC:							
								String numValue = NumberToTextConverter.toText(r.getCell(column).getNumericCellValue());							
								if(rowName.contains(numValue))
								{
									Iterator<Cell> celldata = r.cellIterator();
									while (celldata.hasNext())
									{
										Cell c = celldata.next();										
										arrayList.add(getCellValuebyType(c, column));										
									}
								}
								break;
							default:
								break;
						}
					}	
				}
				if(!sheetExists) {
					log.info("Shee Name - '"+sheetName+"' not exists in file");
				}
				workbook.close();	
			}
		}
		catch (Exception ex) {
			log.error(ex.getMessage());
		}	
		return arrayList;
	}


	/** Select TestCases which are set to Yes in excel sheet
	 * @param - filePath - file path of the excel sheet
	 * @param sheetName - Name of the excel sheet from which we want to retrieve data
	 * @param rowValue - rowValue is the value of the cell in each row being searched
	 * @param columnName - Name of the column which will act as search element(column header)
	 * @author unatarax
	 * @since 2019-12-15
	 * @throws IOException
	 * @return columnName
	 */	
	public String GetTestCasesWhichAreSetToYes(String filePath, String sheetName, String rowValue, String columnName) throws IOException
	{	
		int k=0;int column =0;
		Cell value = null;	
		boolean sheetExists=false;
		try {			
			
			FileInputStream filestream=new FileInputStream(filePath);
			XSSFWorkbook workbook=new XSSFWorkbook(filestream);		
			int sheets = workbook.getNumberOfSheets();
			for (int i=0; i<sheets; i++)
			{
				if(workbook.getSheetName(i).equalsIgnoreCase(sheetName))
				{
					sheetExists=true;
					XSSFSheet sheet = workbook.getSheetAt(i);				
					Iterator<Row> rows = sheet.iterator();
					Row firstrow = rows.next();		//scans the first row		
					Iterator<Cell> cellvalue = firstrow.cellIterator();					
					while(cellvalue.hasNext())
					{
						value = cellvalue.next();
						if (value.getStringCellValue().trim().equalsIgnoreCase(columnName.trim()))
						{
							column = k;	
							break;	
						}
						k++;	
					}
					log.info("column index is : " + column);
					int Count = 0; 	
					while(rows.hasNext())
					{					
						Row r =rows.next();
						if(r.getCell(column).getStringCellValue().equalsIgnoreCase(rowValue))
						{													
							for (int j=0;j<=sheet.getLastRowNum(); j++)
							{
								Cell Cell = r.getCell(j);
								getCellValuebyType(Cell, column);
							}
							Count = Count + 1;									
						}	
					}
					log.info("Total Number of occurrences are  " + Count);									
				}
				if(!sheetExists) {
					log.info("Shee Name - '"+sheetName+"' not exists in file");
				}
				workbook.close();
			}			
		}
		catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return columnName;
	}
	
	
	
	/** Get Single cell value from excel sheet
	 * @param filePath - file path of the excel sheet
	 * @param columnHeader - Name of the column which will act as a search element
	 * @param parameters - List of cell values are available in parameters
	 * @param dataSheet - Data is inserted into the DataSheet
	 * @author unatarax
	 * @since 2019-12-16
	 * @throws IOException 
	 */	
	public  Map<String, Map<String,  Map<Integer, String>>> GetCellValueBasedOnFilters(String filePath, String columnHeader, List<String>parameters, String DataSheet) throws IOException
	{
		Map<String, Map<String, Map<Integer, String>>> excelFileMap = null;
		try {
			int columnHeaderPosition=0;
			FileInputStream fileInput = new FileInputStream(filePath);
			Workbook workbook = new XSSFWorkbook(fileInput);
								
			Sheet sheet = workbook.getSheetAt(0);
			excelFileMap = new HashMap<String, Map<String, Map<Integer, String>>>();
				
			Map<String, Map<Integer, String>> dataMap = new HashMap<String, Map<Integer, String>>();
			Map<Integer, String> columnData=new HashMap<Integer, String>();
			String columnHeaderName, cellValue;
				
			Row columnHeaderRow=sheet.getRow(0);
			for(int j=0;j<columnHeaderRow.getLastCellNum() ; j++) 
			{
				columnHeaderName = columnHeaderRow.getCell(j).getStringCellValue().trim();
				if(columnHeaderName.equalsIgnoreCase(columnHeader)) 
				{
					columnHeaderPosition=j;
					break;
				}
			}	
											
			for(int i =0; i<=sheet.getLastRowNum(); i++)
			{
				String numValue =null;
				Row row=sheet.getRow(i);
				CellType cell = row.getCell(columnHeaderPosition).getCellType();
				switch(cell) {
					case STRING:
						cellValue = row.getCell(columnHeaderPosition).getStringCellValue().trim();
						if(parameters.contains(cellValue))
						{
							columnData.put(i, cellValue);						
							dataMap.put(columnHeader,  columnData);	
						}
						break;					
					case NUMERIC:
						numValue = NumberToTextConverter.toText(row.getCell(columnHeaderPosition).getNumericCellValue());
						if(parameters.contains(numValue))
						{
							columnData.put(i, numValue);						
							dataMap.put(columnHeader,  columnData);	
						}
						break;
					default:
						break;		
				}			
			}	
			excelFileMap.put(DataSheet, dataMap);
			workbook.close();
		}
		catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return excelFileMap;
	}
	
	/** 
	 * @param filePath - file path of the excel sheet
	 * @param key - Position of the parameter
	 * @param searchValue - Value of the cell which is being searched
	 * @Param DataSheet - Data will be inserted to the DataSheet
	 * @author unatarax
	 * @since 2019-12-16
	 * @throws IOException 
	 * @return keyValue
	 */			
	public  Map<Integer, String> getMapData( String filePath, String key, String searchValue, String DataSheet) throws IOException
	{
		Map<Integer, String> keyvalue = null;
		try {
			List<String> data=new ArrayList<String>();
			data.add(searchValue);
			Map<String, Map<Integer, String>> m = GetCellValueBasedOnFilters(filePath, key, data, DataSheet).get(DataSheet);
			keyvalue = m.get(key);
			return keyvalue;
		}
		catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return keyvalue;
	}
	
	/**
	* To get Excel Sheet from Excel Workbok
	* @param filePath  - Provide file path 
	* @param dataSheetName - Provide dataSheetName for which you want to 
	* @return Excel Sheet
	* @author rkanurx
	* @since 13-Dec-2019
	*/	
	public XSSFSheet GetSheetFromExcel(String filePath, String dataSheetName ) {
		boolean isExists=false;
		try {
			workbook = new XSSFWorkbook(new FileInputStream(filePath));
			int numberOfSheets = workbook.getNumberOfSheets();
		    for (int i = 0; i < numberOfSheets; i++) {
		    	if(workbook.getSheetAt(i).getSheetName().trim().equalsIgnoreCase(dataSheetName.trim().toLowerCase())) {
		    		sheet= workbook.getSheet(dataSheetName);
		    		isExists=true;
		    		log.info("Sheet Name '"+dataSheetName+"' exists in Excel File");
		    	}
		    }		    
		    if(!isExists) {
		    	log.info("Sheet Name '"+dataSheetName+"' is not exists in Excel File. Please check manually.");
		    }		    
		    workbook.close();			
		}catch(Exception e) {
			log.info("Exception in GetSheetFromExcel - " +e.getMessage());
		}	
		
		return sheet;		
	}
	
	
	/**
	* To get Excel Sheet from Excel Workbok
	* @param filePath  - Provide file path 
	* @param dataSheetName - Provide dataSheetName for which you want to 
	* @return Excel Sheet
	* @author rkanurx
	* @since 13-Dec-2019
	*/	
	public List<String> GetDataRowFromDataTable(String filePath, String dataSheetName, String columnName, String columnValue ) {
		boolean isExists=false;
		List<String> rowValues = new ArrayList<String>();
		try {
			workbook = new XSSFWorkbook(new FileInputStream(filePath));
			int numberOfSheets = workbook.getNumberOfSheets();
		    for (int i = 0; i < numberOfSheets; i++) {
		    	if(workbook.getSheetAt(i).getSheetName().trim().equalsIgnoreCase(dataSheetName.trim().toLowerCase())) {
		    		sheet= workbook.getSheet(dataSheetName);
		    		isExists=true;
		    		log.info("Sheet Name '"+dataSheetName+"' exists in Excel File");
		    	}
		    }
		    
		    if(isExists) {
		    	for (int rn=1; rn<=sheet.getLastRowNum(); rn++) {
		    		   Row row = sheet.getRow(rn);
		    		   if (row == null) {
		    			   log.info("Whole Row is empty for Row Number = '"+rn+"'");
		    		      continue;
		    		   }
		    		   
		    		   for (int cn=0; cn<row.getLastCellNum(); cn++) {
		    		      Cell cell = row.getCell(cn);
		    		      String val = null;
		    		      if (cell != null) 
		    		      { 
		    		    	  val = formatter.formatCellValue(cell);
		    		      }
		    		      if (val == null || val.isEmpty()) 
		    		      {
		    		    	  log.info("Cell value is empty location - Row Number = '"+rn+"', Column Number = '"+cn+"'");
		    		      }
		    		      rowValues.add(val);
		    		   }
		    		}		    	
		    }else {
		    	log.info("Sheet Name '"+dataSheetName+"' is not exists in Excel File. Please check manually.");
		    }		    
		    workbook.close();
			
		}catch(Exception e) {
			log.info("Exception in GetSheetFromExcel - " +e.getMessage());
		}	
		
		return rowValues;
	}
	
	
	/**
	* To convert Excel Sheet to Result Set
	* @param filePath  - Provide file path 
	* @param dataSheetName - Provide dataSheetName for which you want to 
	* @return Excel Sheet
	* @author rkanurx
	* @since 13-Dec-2019
	*/	
//	public ResultSet ConvertExcelToResultSet(String filePath) {
//		try {
//
//            FileInputStream file = new FileInputStream(filePath);
////            ResultSet rs = workbook.easy_ReadXLSXActiveSheet_AsResultSet(file);
//      
//            // Display imported ResultSet values
//            int columnCount = rs.getMetaData().getColumnCount();
//            int row = 0;
//            while (rs.next()){
//                for (int column=1; column<columnCount+1; column++)
//                    System.out.println("At row " + (row + 1) + ", column " + (column) +
//                            " the value is '" + rs.getString(column) + "'");
//                row++;
//            }
//
//            // Dispose memory
//            workbook.close();
//        }
//        catch (Exception ex) {
//            ex.printStackTrace();
//        }
//	}
	

}













