package showEDU.com.web.member.service.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import showEDU.com.web.member.model.AdministratorBean;
import showEDU.com.web.member.repository.AdministratorRepository;
import showEDU.com.web.member.service.AdministratorService;

@Service
@Transactional
public class AdministratorServiceImpl implements AdministratorService {

	@Autowired
	SessionFactory factory;
	
	@Autowired
	AdministratorRepository AdministratorDao;
	
	@Override
	public void save(AdministratorBean adm) {
		int count = 0;
		AdministratorDao.save(adm);
		count++;
	}

	@Override
	public void deleteAdm(Integer admId) {
		AdministratorDao.deleteAdm(admId);
	}

	@Override
	public AdministratorBean getAdmById(int admId) {
		
		return AdministratorDao.getAdmById(admId);
	}

	@Override
	public void updateAdm(AdministratorBean adm) {
		AdministratorDao.updateAdm(adm);

	}

	@Override
	public List<AdministratorBean> getAlladms() {

		return AdministratorDao.getAlladms();
	}

	@Override
	public AdministratorBean login(String adaccount, String adpswd) {
		
		return AdministratorDao.login(adaccount, adpswd);
	}

}
