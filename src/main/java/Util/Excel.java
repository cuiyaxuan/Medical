package Util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

public class Excel {

	public static List<List<List<String>>> excelToForeignHouseExport(InputStream file) {
		InputStream is;
		XSSFWorkbook xssfWorkbook = null;//07版
		List<List<List<String>>> data = new ArrayList<List<List<String>>>();
		try {
			is = file;
			xssfWorkbook = new XSSFWorkbook(is);
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<List<String>> list = new ArrayList<List<String>>();

		for (int iii = 1 ; iii<6;iii++){
		XSSFSheet sh = xssfWorkbook.getSheet(iii+"");
		if (null != sh) {
			//获取有多少横列项
			XSSFRow firstRow;
			if (iii==1||iii==5)
			{
				firstRow = sh.getRow(1);}
			else{
			 	firstRow = sh.getRow(3);}
			int cellNumStart = 1;
			int cellNumend = firstRow.getLastCellNum();

			for (int i = 4; i <= sh.getLastRowNum(); i++) {
				if (sh.getRow(i).getCell(cellNumStart).toString() != "") {
					List<String> columnList = new ArrayList<String>();
					for (int j = cellNumStart; j < cellNumend; j++) {
						if (sh.getRow(i).getCell(j) != null) {
							if (j == cellNumStart)
								columnList.add(clearkongge(sh.getRow(i).getCell(j).toString()));
							else
								columnList.add(sh.getRow(i).getCell(j).toString());
						} else
							columnList.add(" ");
					}
					list.add(columnList);
				}
			}
		}
	}
		data.add(list);

		try {
			xssfWorkbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}


	static  String clearkongge(String str){
		String str1 = "abc ";
		String kongge =String.valueOf(str1.charAt(3));
		if (kongge.equals(String.valueOf(str.charAt(str.length()-1)))){
			str = clearkongge(str.substring(0,str.length()-1));
		}
		else if (kongge.equals(String.valueOf(str.charAt(0)))){
			str=   clearkongge(str.substring(1,str.length()));
		}
		return str ;
	}


	}

