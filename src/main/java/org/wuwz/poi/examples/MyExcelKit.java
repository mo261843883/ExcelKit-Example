/**

 * Copyright (c) 2017, Œ‚„Î‘Û (wuwz@live.com).

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

import org.wuwz.poi.ExcelKit;
import org.wuwz.poi.OnReadDataHandler;

public class MyExcelKit extends ExcelKit {
	
	@Override
	public void readExcel(File excelFile, int sheetIndex, OnReadDataHandler handler) {
		System.out.println("ºÃ≥–÷ÿ–¥...");
		super.readExcel(excelFile, sheetIndex, handler);
	}
}
