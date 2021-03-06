package showEDU.com.web.member.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import showEDU.com.web.member.model.MemberBean;


@Repository
public interface MemberService {
	void save(MemberBean member);

	void deleteMember(Integer memberId);

	MemberBean getMemberById(int memberId);

	void updateMember(MemberBean member);

	List<MemberBean> getAllMembers();

	MemberBean login(String account, String pswd);

	boolean idExists(String account);

	MemberBean queryMember(String account);
	MemberBean checkIdPassword(String userId, String password) ;

	List<String> serchMemberaccount();

	void updatePasswd(String memberEmail);
	
}
