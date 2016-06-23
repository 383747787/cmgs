package com.zstu.bysj.cmgs.controller;

import com.zstu.bysj.cmgs.util.ResultWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * 用户
 * 
 * @author irving
 *
 */
@Controller
public class UploadController {

	private String root = "D:/workspace/school/cmgs/src/main/webapp";
	private String path = "/resources/img/img";
	private String tomcat = "D:/Tomcat8.0.15/webapps/ROOT/resources/img/img";

	@RequestMapping(value = "/upload/img", method = RequestMethod.POST)
	public @ResponseBody String upload(@RequestParam("file") MultipartFile file) {
		if (file == null) {
			return ResultWrapper.success("上传失败：文件为空");
		}
		if (file.getSize() > 10000000)
		{
			return ResultWrapper.success("上传失败：文件大小不能超过10M");
		}

		String extension = file.getOriginalFilename().split("\\.")[1];
		String filename = createName() + "." + extension;

		if (file.getSize() > 0) {
			try {
				saveFile(file.getInputStream(), tomcat, filename);
				saveFile(file.getInputStream(), root + path, filename);
			} catch (IOException e) {
				System.out.println(e.getMessage());
				return null;
			}
		}
		return ResultWrapper.success(path + "/" + filename, "上传成功");
	}

	/**
	 * 唯一命名
	 */
	private String createName() {
		Date now = new Date();
		return now.getTime() + "";
	}

	/**
	 * 保存文件
	 */
	private void saveFile(InputStream stream, String path, String filename) throws IOException {
		FileOutputStream fs = new FileOutputStream(path + "/" + filename);
		byte[] buffer = new byte[1024 * 1024];
		@SuppressWarnings("unused")
		int bytesum = 0;
		int byteread = 0;
		while ((byteread = stream.read(buffer)) != -1)
		{
			bytesum += byteread;
			fs.write(buffer, 0, byteread);
			fs.flush();
		}
		fs.close();
		stream.close();
	}

}
