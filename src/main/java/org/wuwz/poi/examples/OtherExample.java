/**

 * Copyright (c) 2017, 吴汶泽 (wuwz@live.com).

 *

 * Licensed under the Apache License, Version 2.0 (the "License");

 * you may not use this file except in compliance with the License.

 * You may obtain a copy of the License at

 *

 *      http://www.apache.org/licenses/LICENSE-2.0

 *

 * Unless required by applicable law or agreed to in writing, software

 * distributed under the License is distributed on an "AS IS" BASIS,

 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.

 * See the License for the specific language governing permissions and

 * limitations under the License.

 */
package org.wuwz.poi.examples;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;
import org.wuwz.poi.ExcelKit;
import org.wuwz.poi.ExcelType;
import org.wuwz.poi.OnReadDataHandler;
import org.wuwz.poi.OnSettingHanlder;

/**
 * 更多用法示例,本示例只做参考,可能不能直接运行（需要数据支持）
 * @author wuwz
 */
public class OtherExample {
	
	public static void main(String[] args) throws Exception {
		File excelFile = new File("C:\\Users\\Administrator\\Desktop\\excel.xlsx");
		
		//1. 生成本地文件
		ExcelKit.$Builder(User.class).toExcel(Db.getUsers(), "用户信息", new FileOutputStream(excelFile));
		
		//2. 文件导入模版
		ExcelKit.$Builder(User.class).toExcel(null, "用户信息", new FileOutputStream(excelFile));
		
		
		//3. 自定义Excel文件生成/导出(ExcelKit.$Export(class,response))
		ExcelKit.$Builder(User.class).toExcel(Db.getUsers(), "用户信息", ExcelType.EXCEL2007, new OnSettingHanlder() {
			
			@Override
			public CellStyle getHeadCellStyle(Workbook wb) {
				// 设置表头样式
				CellStyle cellStyle = wb.createCellStyle();
				Font font = wb.createFont();
				cellStyle.setAlignment(CellStyle.ALIGN_LEFT);// 对齐
				cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
				cellStyle.setFillBackgroundColor(HSSFColor.GREEN.index);
				font.setBoldweight(Font.BOLDWEIGHT_NORMAL);
				font.setFontHeightInPoints((short) 14);// 字体大小
				font.setColor(HSSFColor.WHITE.index);
				cellStyle.setFont(font);
				//......
				return cellStyle;
			}
			
			@Override
			public String getExportFileName(String sheetName) {
				// 设置导出文件名
				return String.format("导出-%s-%s", sheetName,System.currentTimeMillis());
			}
			
			@Override
			public CellStyle getBodyCellStyle(Workbook wb) {
				return null;
			}
		}, new FileOutputStream(excelFile));
		
		
		//4. 读取指定sheetIndex
		ExcelKit.$Import().readExcel(excelFile, 0, new OnReadDataHandler() {
			
			@Override
			public void handler(List<String> rowData) {
				
			}
		});
		
		//5. 设置空单元格值,默认为：“EMPTY_CELL_VALUE”
		final String emptyValue = "null";
		ExcelKit.$Import().setEmptyCellValue(emptyValue).readExcel(excelFile, new OnReadDataHandler() {
			
			@Override
			public void handler(List<String> rowData) {
				if(emptyValue.equals(rowData.get(0))) {
					//此单元格的值为空,需要额外处理
				}
			}
		});
		
		
		//6. 读取指定sheet、行、单元格
		int sheetIndex = 0;
		int startRowIndex = 0;
		int endRowIndex = 9;
		int startCellIndex = 0;
		int endCellIndex = 3;
		ExcelKit.$Import().readExcel(excelFile, new OnReadDataHandler() {
			
			@Override
			public void handler(List<String> rowData) {
				// TODO Auto-generated method stub
				
			}
		}, sheetIndex, startRowIndex, endRowIndex, startCellIndex, endCellIndex);
		
		
		//.....
	}

}
