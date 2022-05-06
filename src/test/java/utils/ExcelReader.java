package utils;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelReader {

    static Workbook book;
    static Sheet sheet;


    //opening the Excel file
    public static void openExcel(String filePath) {
        try {
            FileInputStream fis=new FileInputStream(filePath);
            book= new XSSFWorkbook(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //opening a sheet from Excel file
    public static void getSheet(String sheetName){
        sheet=book.getSheet(sheetName);
    }

    //getting row's count
    public static int getRowCount(){
        return sheet.getPhysicalNumberOfRows();
    }

    //getting column's count
    public static int getColumnsCount(int rowIndex){
        return sheet.getRow(rowIndex).getPhysicalNumberOfCells();
    }

    //getting data from a cell of Excel file
    public static String getCellData(int rowIndex, int columnIndex){
        return sheet.getRow(rowIndex).getCell(columnIndex).toString();
    }

    //getting all data from Excel file
    public static List<Map<String, String>> excelIntoMap(String filePath, String sheetName){
        openExcel(filePath);
        getSheet(sheetName);

        //the whole Excel file is a List of Maps
        List<Map<String,String>> listData=new ArrayList<>();
        for (int row=1; row<getRowCount(); row++){
            //creating a map for every row
            Map <String, String> mapRows=new LinkedHashMap<>();
            for (int column=0; column<getColumnsCount(row);column++){
                mapRows.put(getCellData(0, column), getCellData(row,column));
            }
            listData.add(mapRows);
        }
        return listData;
    }

}
