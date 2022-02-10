package jp.co.sample.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
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
	public String insert(@Validated InsertAdministratorForm form,BindingResult result,Model model) {
		if(result.hasErrors()) {
			return "/administrator/insert";
		}
		Administrator administrator = new Administrator();
		
		administrator.setName(form.getName());
		
		administrator.setMailAddress(form.getMailAddress());
		
		administrator.setPassword(form.getPassword());
		
		administratorService.insert(administrator);
		return "redirect:/";
	}
	
	
	/**
	 * LoginFormをインスタンス化しそのままreturn
	 * 
	 * ログインする際、リクエストパラメータをModelオブジェクトに自動格納させるための処理
	 *
	 */
	@ModelAttribute
	public LoginForm setUpLoginForm() {
		return new LoginForm();
	}
	
	/**
	 * administrator/login.htmlへフォワード
	 * 
	 * 
	 *
	 */
	@RequestMapping("/")
	public String toLogin() {
		return "administrator/login";
	}
	
	@Autowired
	private HttpSession session;
	
	
	/**
	 * ログイン処理
	 * 
	 * 
	 *
	 */
	@RequestMapping("/login")
	public String login(LoginForm form,Model model) {
		Administrator administrator = administratorService.login(form.getMailAddress(), form.getPassword());
		
		if(administrator == null) {
			model.addAttribute("message","メールアドレスまたはパスワードが不正です。");
			model.addAttribute("param","error");
			return "administrator/login";
		}else{
			
		session.setAttribute("administratorName",administrator.getName());
			
		return "forward:/employee/showList";
		}
	}
	
	/**
	 * ログアウト処理
	 * 
	 * 
	 *
	 */
	@RequestMapping("/logout")
	public String logout() {
		session.invalidate();
		
		return "redirect:/";
	}
	
	
	
	
	
}

