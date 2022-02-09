package jp.co.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Employee;
import jp.co.sample.form.UpdateEmployeeForm;
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

		List<Employee> employeeList = employeeService.showList();
		
		model.addAttribute("employeeList",employeeList);
		
		return "employee/list";
		
	}
	
	/**
	 * UpdateEmployeeFormをインスタンス化しそのままreturn
	 * 
	 * 扶養人数更新の際、リクエストパラメータをModelオブジェクトに自動格納させるための処理
	 *
	 */
	@ModelAttribute
	public UpdateEmployeeForm setUpUpdateEmployeeForm() {
		return new UpdateEmployeeForm();
	}
	
	@RequestMapping("/showDetail")
	public String showDetail(String id,Model model) {
		int intid = Integer.parseInt(id);
	
		model.addAttribute("employee",employeeService.showDetail(intid));
		
		return "employee/detail";
	}
	@RequestMapping("/update")
	public String update(UpdateEmployeeForm form) {
		
		int intid = Integer.parseInt(form.getId());
		Employee employee = employeeService.showDetail(intid);
		employeeService.upDate(employee);
		return "redirect:/employee/showList";
	}
	

}
