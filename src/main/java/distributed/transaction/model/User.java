package distributed.transaction.model;

import java.util.Date;

/**
 * @author Dean
 */
public class User {
	private Long id;
	private String name;
	private Date createTime;

	public User(String name) {
		this.name = name;
		this.createTime = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
