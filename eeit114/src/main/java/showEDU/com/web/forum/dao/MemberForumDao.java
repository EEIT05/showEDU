package showEDU.com.web.forum.dao;

import java.util.List;

import showEDU.com.web.member.model.MemberBean;

public interface MemberForumDao {
	MemberBean queryMember(Integer memberId);
	
	List<MemberBean> getAllMembers();
}
