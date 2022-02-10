package jp.co.sample.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 
 */
/**
 * @author ishida fuya
 *
 */
public class InsertAdministratorForm {
	
	@NotBlank(message="入力は必須です")
	/**  名前 */
	private String name;
	
	@NotBlank(message="入力は必須です")
	/** メールアドレス */
	private String mailAddress;
	
	@NotBlank(message="入力は必須です")
	/** パスワード */
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "InsertAdministratorForm [name=" + name + ", mailAddress=" + mailAddress + ", password=" + password
				+ "]";
	}
	

}
