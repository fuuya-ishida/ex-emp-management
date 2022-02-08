package jp.co.sample.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;

/**
 * 管理者情報の操作処理の設定
 * 
 * @author ishida fuya
 *
 */

@Repository
public class AdministratorRepository {
	/** RowMapperの定義 */
	private static final RowMapper<Administrator> Administrator_ROW_MAPPER = (rs,i) -> {
		Administrator administrator = new Administrator();
		
		administrator.setId(rs.getInt("id"));
		administrator.setName(rs.getString("name"));
		administrator.setMailAddress(rs.getString("mailAddress"));
		administrator.setPassword(rs.getString("password"));
		
		return administrator;
	};
	
	/** template */
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	
	/**
	 * 管理者情報を挿入する
	 * 
	 * 
	 *
	 */
	public void  insert(Administrator administrator) {
		String insertSql = "INSERT INTO administrator(name,mail_address,password"
				+ " VALUE(:name,:mailAddress,:password)";
		
		SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
		
		template.update(insertSql, param);
	}
	
	/**
	 * メールアドレスとパスワードから管理者情報を取得する
	 * 
	 * 管理者情報が１件も存在しない場合はnullを戻り値として設定する
	 *
	 */
	public Administrator findByMailAddressAndPassword(String mailAddress,String password) {
		
		String sql = "SELECT id,name,mail_address,password WHERE mail_address =:mailAddress "
				+ "AND password =:password";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("mail_address", mailAddress)
				.addValue("password", password);
		
		//一件も検索されなかった場合はnullを返す。
		try {
		return template.queryForObject(sql, param,Administrator_ROW_MAPPER);
		}catch(Exception e){
			return null;
		}
		
	}
	
	
}


