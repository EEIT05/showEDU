package showEDU.com.web.member.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import showEDU.com.web.member.model.AdministratorBean;

@Repository
public interface AdministratorService {


	void save(AdministratorBean adm);

	void deleteAdm(Integer admId);

	AdministratorBean getAdmById(int admId);

	void updateAdm(AdministratorBean adm);

	List<AdministratorBean> getAlladms();

	AdministratorBean login(String adaccount, String adpswd);
	
}
