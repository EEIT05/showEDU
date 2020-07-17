package showEDU.com.web.forum.service;

import java.util.List;

import showEDU.com.web.member.model.MemberBean;

public interface MemberForumService {
	MemberBean queryMember(Integer memberId);
	
	List<MemberBean> getAllMembers();

}
