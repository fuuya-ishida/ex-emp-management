package jp.co.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Administrator;
import jp.co.sample.repository.AdministratorRepository;


/**
 * 各業務処理メソッドの記載
 * 
 * @author ishida fuya
 *
 */
@Service
@Transactional
public class AdministratorService {
	
	/** administratorRepository */
	@Autowired
	private AdministratorRepository administratorRepository;
	
	
	/**
	 * 従業員情報の追加処理
	 * 
	 * 
	 *
	 */
	public void insert(Administrator administrator) {
		administratorRepository.insert(administrator);
		
	}
	
	
	/**
	 * ログイン処理
	 * 
	 * 
	 *
	 */
	public Administrator login(String mailAddress,String password) {
		return administratorRepository.findByMailAddressAndPassword(mailAddress, password);
		
	}
	
	
	
	
	

}
