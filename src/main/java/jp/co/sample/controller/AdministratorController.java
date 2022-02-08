package jp.co.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.service.AdministratorService;


/**
 * 管理者登録画面を表示する
 * 
 * @author ishida fuya
 *
 */

@Controller
@RequestMapping("/")
public class AdministratorController {
	
	
	/** administratorService */
	@Autowired
	private AdministratorService administratorService;
	
	/**
	 * InsertAdministratorFormをインスタンス化しそのままreturn
	 * 
	 * 従業員登録の際、リクエストパラメータをModelオブジェクトに自動格納させるための処理
	 *
	 */
	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {
		return new InsertAdministratorForm();
	}
	
	/**
	 * administrator/insert.htmlへフォワード
	 * 
	 * 
	 *
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert";
	}
	
	@RequestMapping("insert")
	public String insert(InsertAdministratorForm form ) {
		Administrator administrator = new Administrator();
		
		administrator.setName(form.getName());
		
		administrator.setMailAddress(form.getMailAddress());
		
		administrator.setPassword(form.getPassword());
		
		administratorService.insert(administrator);
		return "redirect:/";
	}
	

}
