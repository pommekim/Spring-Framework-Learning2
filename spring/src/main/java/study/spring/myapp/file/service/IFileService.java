package study.spring.myapp.file.service;

import java.util.List;

import study.spring.myapp.file.model.FileVO;

public interface IFileService {
	
	void uploadFile(FileVO file);
	FileVO getFile(int fileId);
	void deleteFile(int fileId);
	List<FileVO> getFileList(String directoryName);
	List<FileVO> getAllFileList();
	void updateDirectory(int[] fileIds, String directoryName);
	void updateFile(FileVO file);

}
