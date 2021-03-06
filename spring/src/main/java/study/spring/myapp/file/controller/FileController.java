package study.spring.myapp.file.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import study.spring.myapp.file.model.FileVO;
import study.spring.myapp.file.service.IFileService;

@Controller
public class FileController {
	
	@Autowired
	IFileService fileService;
	
	@RequestMapping("/file")
	public ModelAndView fileHome() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/file/index");
		return mav;
	}
	
	@GetMapping("/file/new")
	public String uploadFile(Model model) {
		model.addAttribute("dir", "/");
		return "/file/form";
	}
	
	@PostMapping("/file/new")
	public String uploadFile(@RequestParam(value="dir", required=false, defaultValue="/") String dir,
			@RequestParam MultipartFile file, RedirectAttributes redirectAttrs) {
		
		try {
			if(file != null && !file.isEmpty()) {
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				FileVO newFile = new FileVO();
				newFile.setDirectoryName(dir);
				newFile.setFileName(file.getOriginalFilename());
				newFile.setFileSize(file.getSize());
				newFile.setFileContentType(file.getContentType());
				newFile.setFileData(file.getBytes());
				newFile.setUserId(authentication.getName());
				fileService.uploadFile(newFile);
			}
		} catch(IOException e) {
			e.printStackTrace();
			redirectAttrs.addFlashAttribute("message", e.getMessage());
		}
		return "redirect:/file/list";
	}
	
	@GetMapping("/file/info")
	public void getFileInfo(int fileId, Model model) {
		model.addAttribute("file", fileService.getFile(fileId));
	}
	
	@GetMapping({"/img/{fileId}", "/pds/{fileId}"})
	public ResponseEntity<byte[]> getImageFile(@PathVariable int fileId) {
		FileVO file = fileService.getFile(fileId);
		final HttpHeaders headers = new HttpHeaders();
		
		if(file != null) {
			String[] mtypes = file.getFileContentType().split("/");
			headers.setContentType(new MediaType(mtypes[0], mtypes[1]));
			headers.setContentDispositionFormData("attachment", file.getFileName());
			headers.setContentLength(file.getFileSize());
			return new ResponseEntity<byte[]>(file.getFileData(), headers, HttpStatus.OK);
		} else {
			return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping("/file/delete/{fileId}")
	public String deleteFile(@PathVariable int fileId) {
		fileService.deleteFile(fileId);
		return "redirect:/file/list";
	}
	
	@RequestMapping("/file/list")
	public String getFileList(Model model) {
		model.addAttribute("fileList", fileService.getAllFileList());
		return "/file/list";
	}
	
	@RequestMapping("/file/list/{dir}")
	public String getFileListByDir(@PathVariable String dir, Model model) {
		model.addAttribute("fileList", fileService.getFileList("/"+dir));
		return "/file/list";
	}
	
	@RequestMapping("/file/updateDir")
	public String updateDirectory(int[] fileIds, String directoryName) {
		fileService.updateDirectory(fileIds, directoryName);
		return "redirect:/file/list";
	}
	
	

	
	
	
	
	
	

}
