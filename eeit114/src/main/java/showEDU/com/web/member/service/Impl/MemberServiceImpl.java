package showEDU.com.web.member.service.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import showEDU.com.web.member.model.MemberBean;
import showEDU.com.web.member.repository.MemberRepository;
import showEDU.com.web.member.service.MemberService;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

	@Autowired
	SessionFactory factory;

	@Autowired
	MemberRepository memberDao;

	@Override
	public void save(MemberBean member) {
		int count = 0;
		memberDao.save(member);
		count ++;
		
	}

	@Override
	public void deleteMember(Integer memberId) {
		memberDao.deleteMember(memberId);
		
	}

	@Override
	public MemberBean getMemberById(int memberId) {
		return memberDao.getMemberById(memberId);
	}

	@Override
	public void updateMember(MemberBean member) {
		memberDao.updateMember(member);
		
	}

	@Override
	public List<MemberBean> getAllMembers() {
		
		return memberDao.getAllMembers();
	}

	@Override
	public MemberBean login(String account, String pswd) {
		return memberDao.login(account, pswd);
	}

	@Override
	public boolean idExists(String account) {
		boolean exist = false;
		exist = memberDao.idExists(account);
		return exist;
	}


	


}
