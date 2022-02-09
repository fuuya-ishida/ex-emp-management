package jp.co.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.service.EmployeeService;

/**
 * 従業員情報を検索する
 * 
 * @author ishida fuya
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	/** employeeService */
	@Autowired
	private EmployeeService employeeService;
	
	
	/**
	 * 従業員一覧を取得する処理
	 * 
	 * requestスコープに取得した従業員リストを格納
	 * employee/list.htmlへフォワード
	 */
	@RequestMapping("/showList")
	public String showList(Model model) {

		
		model.addAttribute("employeeList",employeeService.showList());
		
		return "employee/list";
	}
	
	

}
