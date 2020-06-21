package study.spring.myapp.member.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import study.spring.myapp.member.model.MemberVO;

@Repository
public class MemberRepository implements IMemberRepository {
	
	@Autowired
	MyJdbcTemplate jt;

	@Override
	public void insertMember(MemberVO mem) {
		String sql = "insert into member values(?,?,?,?,?,?)";
		jt.update(sql, mem.getUserId(), mem.getName(), mem.getPassword(), 
				mem.getEmail(), mem.getAddress(), mem.getEnabled());
	}

	@Override
	public void insertAuth(String userId) {
		String sql = "insert into authorities values(?,?)";
		jt.update(sql, userId, "ROLE_USER");
	}

	@Override
	public MemberVO getMember(String userId) {
		String sql = "select m.userid, name, password, email, address, "
				+ "enabled, authority from member m "
				+ "join authorities au "
				+ "on m.userid=au.userid "
				+ "where m.userid=?";
		return jt.query(sql, new ResultSetExtractor<MemberVO>() {
			
			@Override
			public MemberVO extractData(ResultSet rs) throws SQLException {
				if(rs.next()) {
					MemberVO mem = new MemberVO();
					mem.setUserId(rs.getString("userid"));
					mem.setName(rs.getString("name"));
					mem.setPassword(rs.getString("password"));
					mem.setEmail(rs.getString("email"));
					mem.setAddress(rs.getString("address"));
					mem.setEnabled(rs.getInt("enabled"));
					mem.setAuth(rs.getString("authority"));
					return mem;
				} else {
					return null;
				}
			}
		}, userId);
	}

	@Override
	public String getPassword(String userId) {
		String sql = "select password from member where userid=?";
		return jt.queryForNullableObject(sql, String.class, userId);
	}

	@Override
	public void updateMember(MemberVO mem) {
		String sql = "update member set name=?, password=?, email=?, address=? where userid=?";
		jt.update(sql, mem.getName(), mem.getPassword(), mem.getEmail(), mem.getAddress(), mem.getUserId());
	}

	@Override
	public void deleteMember(String userId) {
		String sql = "delete from member where userid=?";
		jt.update(sql, userId);
	}

	@Override
	public void deleteAuth(String userId) {
		String sql = "delete from authorities where userid=?";
		jt.update(sql, userId);
	}

	@Override
	public void updateFile(String userId) {
		String sql = "update files f set f.userid=null where userid=(select userid from member where userid=?)";
		jt.update(sql, userId);
	}

	@Override
	public void deleteFile(String userId) {
		String sql = "delete from files where userid=?";
		jt.update(sql, userId);
	}

	@Override
	public List<MemberVO> getMemberList() {
		String sql = "select m.userid, name, email, address, enabled, a.authority "
				+ "from member m join authorities a on (m.userid=a.userid)";
		return jt.query(sql, new RowMapper<MemberVO>() {
			
			@Override
			public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException{
				MemberVO mem = new MemberVO();
				mem.setUserId(rs.getString("userid"));
				mem.setName(rs.getString("name"));
				mem.setEmail(rs.getString("email"));
				mem.setAddress(rs.getString("address"));
				mem.setEnabled(rs.getInt("enabled"));
				mem.setAuth(rs.getString("authority"));
				return mem;
			}
		});
	}

	@Override
	public void updateAuth(MemberVO member) {
		String sql = "update authorities set authority=? where userid=(select userid from member where userid=?)";
		jt.update(sql, member.getAuth(), member.getUserId());
	}

	@Override
	public void updateEnabled(MemberVO member) {
		String sql = "update member set enabled=? where userid=?";
		jt.update(sql, member.getEnabled(), member.getUserId());
	}

	

}
