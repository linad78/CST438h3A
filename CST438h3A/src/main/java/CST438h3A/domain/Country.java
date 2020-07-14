package CST438h3A.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="country")
public class Country {
	
	@Id
	private String code;
	private String name;
	
	public Country() {
		code = "code";
		name = "name";
	}
	
	public Country(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		
	}
	

	@Override
	public String toString() {
		return "Country [code=" + code + ", name=" + name + "]";
	}

}