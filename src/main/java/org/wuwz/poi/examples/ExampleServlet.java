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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.wuwz.poi.ExcelKit;
import org.wuwz.poi.ExcelType;
import org.wuwz.poi.OnReadDataHandler;

/**
 * 浏览器导出测试
 * 
 * @author wuwz
 */
// @WebServlet("/example")
public class ExampleServlet extends HttpServlet {

	private static final long serialVersionUID = -8791212010764446339L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String t = request.getParameter("t");
		if ("list".equals(t)) {
			// 跳转到列表页
			toListPage(request, response);
			
		} else if ("export".equals(t)) {
			
			// 执行Excel文件导出
			ExcelKit.$Export(User.class, response).toExcel(Db.getUsers(), "用户信息");
		} else if ("downtmpl".equals(t)) {

			// 下载模版文件
			downTemplFile(response);
		} else if ("import".equals(t)) {
			
			// Excel文件导入
			importExcelFile(request, response);
		}
	}

	private void importExcelFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter writer = response.getWriter();
		if (!ServletFileUpload.isMultipartContent(request)) {
		    writer.println("Error: 表单必须包含 enctype=multipart/form-data");
		    writer.flush();
		    return;
		}
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
		factory.setSizeThreshold(1024 * 1024 * 3);
		// 设置临时存储目录
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
 
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 设置最大文件上传值
		upload.setFileSizeMax(1024 * 1024 * 40);
		// 设置最大请求值 (包含文件和表单数据)
		upload.setSizeMax(1024 * 1024 * 50);
 
		// 构造临时路径来存储上传的文件
		String uploadPath = request.getServletContext().getRealPath("./") + File.separator + "upload";
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
		    uploadDir.mkdir();
		}
 
		try {
		    // 解析请求的内容提取文件数据
		    List<FileItem> formItems = upload.parseRequest(request);
 
		    if (formItems != null && formItems.size() > 0) {
		        for (FileItem item : formItems) {
		            if (!item.isFormField()) {
		                String fileName = new File(item.getName()).getName();
		                String filePath = uploadPath + File.separator + fileName;
		                File storeFile = new File(filePath);
		                System.out.println(filePath);
		                item.write(storeFile);
		                
		                //=============开始解析Excel文件并入库=================
		                ExcelKit.$Import().readExcel(storeFile, new OnReadDataHandler() {
							
							@Override
							public void handler(List<String> rowData) {
								User user = new User();
								user.setUid(Integer.parseInt(rowData.get(0)));
								user.setUsername(rowData.get(1));
								user.setPassword(rowData.get(2));
								user.setNickname(rowData.get(3));
								user.setAge(18);
								Db.addUser(user);
							}
						});
		                
		                if(storeFile.exists()) {
		                	storeFile.delete();
		                }
		                // 跳转列表页
		                toListPage(request, response);
		            }
		        }
		    }
		} catch (Exception ex) {
			writer.println("Error: "+ex.getMessage());
			writer.flush();
		}
	}

	private void downTemplFile(HttpServletResponse response) throws FileNotFoundException, IOException {
		File tmplFile = new File(String.format("%s/import_template.xlsx", System.getProperty("java.io.tmpdir")));
		
		if(!tmplFile.exists()) {
			// 构建模版文件
			ExcelKit.$Builder(User.class).toExcel(null, "用户信息", new FileOutputStream(tmplFile));
		}

		// 执行下载
		@SuppressWarnings("deprecation")
		String fileName = URLEncoder.encode(tmplFile.getName());
		response.reset();
		response.setContentType(ExcelKit.$Import().getContentType(ExcelType.EXCEL2007));
		response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

		int fileLength = (int) tmplFile.length();
		response.setContentLength(fileLength);
		if (fileLength != 0) {
			InputStream inStream = new FileInputStream(tmplFile);
			byte[] buf = new byte[4096];
			ServletOutputStream servletOS = response.getOutputStream();
			int readLength;
			while (((readLength = inStream.read(buf)) != -1)) {
				servletOS.write(buf, 0, readLength);
			}
			inStream.close();
			servletOS.flush();
			servletOS.close();
		}
	}

	private void toListPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("users", Db.getUsers());
		request.getRequestDispatcher("/list.jsp").forward(request, response);
	}

}
