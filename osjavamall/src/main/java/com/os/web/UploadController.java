package com.os.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.os.util.ProgressEntity;

@Controller
@RequestMapping("/upload")
public class UploadController {
	@RequestMapping("/list")
	public String list(Model model) {
		return "success";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// 如果只是上传一个文件，则只需要MultipartFile类型接收文件即可，而且无需显式指定@RequestParam注解
		// 如果想上传多个文件，那么这里就要用MultipartFile[]类型来接收文件，并且还要指定@RequestParam注解
		// 并且上传多个文件时，前台表单中的所有<input
		// type="file"/>的name都应该是myfiles，否则参数里的myfiles无法获取到所有上传的文件
		// for (MultipartFile myfile : myfiles) {
		// if (myfile.isEmpty()) {
		// System.out.println("文件未上传");
		// } else {
		// System.out.println("文件长度: " + myfile.getSize());
		// System.out.println("文件类型: " + myfile.getContentType());
		// System.out.println("文件名称: " + myfile.getName());
		// System.out.println("文件原名: " + myfile.getOriginalFilename());
		// System.out.println("========================================");
		// //
		// 如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中
		// String realPath = request.getSession().getServletContext()
		// .getRealPath("/WEB-INF/upload");
		// //
		// 这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的
		// FileUtils.copyInputStreamToFile(myfile.getInputStream(),
		// new File(realPath, myfile.getOriginalFilename()));
		// }
		// }
		try {
			String realPath = request.getSession().getServletContext()
					.getRealPath("/WEB-INF/upload/");

			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest
					.getFile("filedata");
			// filedata 是表单的名字

			InputStream stream = file.getInputStream();
			String fileName = file.getOriginalFilename();
			// System.out.println(fileName);
			String fileNameFull = realPath + File.separator + fileName;
			System.out.println(fileNameFull);
			OutputStream bos = new FileOutputStream(fileNameFull);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}

			bos.close();
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/list.html";
	}

	@RequestMapping(value = "/getProcess", method = RequestMethod.POST)
	public @ResponseBody
	Object process(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		ProgressEntity progressEntity = (ProgressEntity) request.getSession()
				.getAttribute("upload_ps");
		// PrintWriter out = response.getWriter();
		// out.println(progressEntity.getPercent());
		String s = "{'process':" + progressEntity.getPercent() + "}";
		System.out.println(JSONObject.toJSON(s));
		return JSONObject.toJSON(s);
	}


}
