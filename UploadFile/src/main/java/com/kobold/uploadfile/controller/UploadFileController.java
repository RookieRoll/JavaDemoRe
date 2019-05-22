package com.kobold.uploadfile.controller;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @author kobold
 */
@RestController
@RequestMapping("/upload")
@CrossOrigin
public class UploadFileController {

	@Value("${myfilelocation}")
	private String Path;

	@GetMapping("/index")
	public String Index() {
		return "hello";
	}

	/**
	 * check the file is exit
	 *
	 * @param md5File
	 * @return
	 */
	@PostMapping("/checkFile")
	@ResponseBody
	public Boolean checkFile(@RequestParam("md5File") String md5File) {
		Boolean isExit = false;

		return isExit;
	}

	/**
	 * @author kobold
	 * 检查分片存在与否
	 */
	@PostMapping("checkChunk")
	@ResponseBody
	public Boolean checkChunk(@RequestParam(value = "md5File") String md5File,
	                          @RequestParam(value = "chunk") Integer chunk) {
		Boolean exist = false;
		//分片存放目录
		String path =Path+md5File+"/";

		//分片名
		String chunkName = chunk+ ".tmp";
		File file = new File(path+chunkName);
		if (file.exists()) {
			exist = true;
		}
		return exist;
	}

	/**
	 * upload and save chunk file
	 *
	 * @param file
	 * @param md5File
	 * @param chunk
	 * @return
	 */
	@PostMapping("/uploadfile")
	@ResponseBody
	public Boolean upload(@RequestParam("file") MultipartFile file,
	                      @RequestParam("md5File") String md5File,
	                      @RequestParam(value = "chunk", required = false) Integer chunk) {
		String path = Path + md5File + "/";
		File dirFile = new File(path);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		String chunkName = chunk == null ? "0.tmp" : chunk + ".tmp";
		String filePath = path + chunkName;
		File saveFile = new File(filePath);
		try {
			if (!saveFile.exists()) {
				saveFile.createNewFile();
			}
			file.transferTo(saveFile);
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	@PostMapping("/merge")
	@ResponseBody
	public Boolean merge(@RequestParam(value = "chunks", required = false) Integer chunks,
	                     @RequestParam(value = "md5File") String md5File,
	                     @RequestParam(value = "name") String name) throws Exception {
		FileOutputStream fileStream = new FileOutputStream(Path + "/" + name);
		try {
			byte[] buf=new byte[1024];
			for(int i=0;i<chunks;i++){
				String chunkName=i+".tmp";
				File file=new File(Path+md5File+"/"+chunkName);
				InputStream inputStream=new FileInputStream(file);
				int len = 0;
				while((len=inputStream.read(buf))!=-1){
					fileStream.write(buf,0,len);
				}
				inputStream.close();

			}
			File file=new File(Path+md5File);
			FileUtils.cleanDirectory(file);
			FileUtils.deleteDirectory(file);

		} catch (Exception ex) {
			return false;
		} finally {
			fileStream.close();
		}
		return true;

	}

}
