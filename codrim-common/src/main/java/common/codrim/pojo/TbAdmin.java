package common.codrim.pojo;

import java.io.Serializable;

public class TbAdmin implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TbAdmin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TbAdmin(String account, String password, String email, String officeName, Integer type) {
		super();
		this.account = account;
		this.password = password;
		this.email=email;
		this.officeName=officeName;
		this.type = type;
	}

	public TbAdmin(Long id, String account, String password,String email, String officeName, Integer type) {
		super();
		this.id = id;
		this.account = account;
		this.password = password;
		this.email=email;
		this.officeName=officeName;
		this.type = type;
	}

	/**
     * tb_admin.id
     * @ibatorgenerated 2014-11-28 12:43:38
     */
    private Long id;

    /**
     * tb_admin.account (帐号)
     * @ibatorgenerated 2014-11-28 12:43:38
     */
    private String account;

    /**
     * tb_admin.password (密码)
     * @ibatorgenerated 2014-11-28 12:43:38
     */
    private String password;
    /**
     * tb_admin.email (邮箱)
     * @ibatorgenerated 2014-12-24
     */
    private String email;
    /**
     * tb_admin.office_name (职位)
     * @ibatorgenerated 2014-12-24
     */
    private String officeName;

    /**
     * tb_admin.type (帐号类型)
     * @ibatorgenerated 2014-11-28 12:43:38
     */
    private Integer type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

   
}