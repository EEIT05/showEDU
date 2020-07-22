package showEDU.com.web.member.repository;

import java.sql.Connection;
import java.util.List;

import org.springframework.stereotype.Repository;

import showEDU.com.web.member.model.MemberBean;

@Repository
public interface MemberRepository {
	void save(MemberBean member);

	void deleteMember(Integer memberId);

	MemberBean getMemberById(int memberId);

	void updateMember(MemberBean member);

	List<MemberBean> getAllMembers();

	MemberBean login(String account, String pswd);

	
boolean idExists(String account);
  public MemberBean checkIdPassword(String userId, String password);
	public void setConnection(Connection con);
	public MemberBean queryMember(String memberId);
}
