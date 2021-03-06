package study.spring.myapp.file.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import study.spring.myapp.file.model.FileVO;

@Repository
public class FileRepository implements IFileRepository {
	
	@Autowired
	@Qualifier("jdbcTemplate")
	JdbcTemplate jdbctemplate;
	
	RowMapper<FileVO> fileMapper = new RowMapper<FileVO>() {
		
		@Override
		public FileVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			FileVO file = new FileVO();
			file.setFileId(rs.getInt("file_id"));
			file.setDirectoryName(rs.getString("directory_name"));
			file.setFileName(rs.getString("file_name"));
			file.setFileSize(rs.getLong("file_size"));
			file.setFileContentType(rs.getString("file_content_type"));
			file.setFileUploadDate(rs.getTimestamp("file_upload_date"));
			file.setUserId(rs.getString("userid"));
			return file;
		}
	};

	@Override
	public int getMaxFileId() {
		String sql = "select nvl(max(file_id), 0) from files";
		return jdbctemplate.queryForObject(sql, Integer.class);
	}

	@Override
	public void uploadFile(FileVO file) {
		String sql = "insert into files (file_id, directory_name, file_name, file_size, "
				+ "file_content_type, file_upload_date, file_data, userid) "
				+ "values(?,?,?,?,?,sysdate,?,?)";
		jdbctemplate.update(sql, file.getFileId(), file.getDirectoryName(), file.getFileName(), 
				file.getFileSize(), file.getFileContentType(), file.getFileData(), file.getUserId());
				
	}

	@Override
	public FileVO getFile(int fileId) {
		String sql = "select file_id, directory_name, file_name, "
				+ "file_size, file_content_type, file_upload_date, file_data, userid "
				+ "from files where file_id=?";
		return jdbctemplate.queryForObject(sql, new RowMapper<FileVO>() {
			
			@Override
			public FileVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				FileVO file = new FileVO();
				file.setFileId(rs.getInt("file_id"));
				file.setDirectoryName(rs.getString("directory_name"));
				file.setFileName(rs.getString("file_name"));
				file.setFileSize(rs.getLong("file_size"));
				file.setFileContentType(rs.getString("file_content_type"));
				file.setFileUploadDate(rs.getTimestamp("file_upload_date"));
				file.setFileData(rs.getBytes("file_data"));
				file.setUserId(rs.getString("userid"));
				return file;
			}
		}, fileId);			
	}

	@Override
	public void deleteFile(int fileId) {
		String sql = "delete from files where file_id=?";
		jdbctemplate.update(sql, fileId);	
	}

	@Override
	public List<FileVO> getFileList(String directoryName) {
		String sql = "select file_id, directory_name, file_name, "
				+ "file_size, file_content_type, file_upload_date, userid "
				+ "from files "
				+ "where directory_name=? "
				+ "order by file_upload_date desc";
		return jdbctemplate.query(sql, fileMapper, directoryName);
	}

	@Override
	public List<FileVO> getAllFileList() {
		String sql = "select file_id, directory_name, file_name, "
				+ "file_size, file_content_type, file_upload_date, userid "
				+ "from files "
				+ "order by file_upload_date desc";
		return jdbctemplate.query(sql, fileMapper);
	}

	@Override
	public void updateDirectory(HashMap<String, Object> map) {
		String sql = "update files set directory_name=? where file_id=?";
		jdbctemplate.update(sql, map.get("directoryName"), map.get("fileId"));
	}

	@Override
	public void updateFile(FileVO file) {
		String sql = "update files set directory_name=?, file_name=?, file_size=?, "
				+ "file_content_type=?, file_data=? where file_id=?";
		jdbctemplate.update(sql, file.getDirectoryName(), file.getFileName(), file.getFileSize(), 
				file.getFileContentType(), file.getFileData(), file.getFileId());
	}

}
