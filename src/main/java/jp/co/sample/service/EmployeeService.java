package jp.co.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Employee;
import jp.co.sample.repository.EmployeeRepository;


/**
 * 各業務処理メソッドの記載
 * 
 * @author ishida fuya
 *
 */

@Service
@Transactional
public class EmployeeService {
	
	
	/** employeeRepository */
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	/**
	 * 従業員一覧の表示処理
	 * 
	 * 
	 *
	 */
	public List<Employee> showList(){
		return employeeRepository.findAll();
	}
	
	/**
	 * 従業員情報の取得
	 * 
	 * 
	 *
	 */
	public Employee showDetail(Integer id) {
		return employeeRepository.load(id);
	}
	
	/**
	 * 従業員情報の更新
	 * 
	 * 
	 *
	 */
	public void upDate(Employee employee) {
		employeeRepository.update(employee);
	}
	
	
	
	

}
