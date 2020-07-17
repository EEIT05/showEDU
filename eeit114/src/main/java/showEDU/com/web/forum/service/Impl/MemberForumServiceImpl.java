package showEDU.com.web.forum.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import showEDU.com.web.forum.dao.MemberForumDao;
import showEDU.com.web.forum.service.MemberForumService;
import showEDU.com.web.member.model.MemberBean;
@Service
public class MemberForumServiceImpl implements MemberForumService {
	
	@Autowired
	MemberForumDao MemberForumDao;
	
	@Transactional
	@Override
	public MemberBean queryMember(Integer memberId) {
		return MemberForumDao.queryMember(memberId);
	}
	@Transactional
	@Override
	public List<MemberBean> getAllMembers() {
		return MemberForumDao.getAllMembers();
	}

}
