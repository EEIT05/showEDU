package showEDU.com.web.member.repository;

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
}
