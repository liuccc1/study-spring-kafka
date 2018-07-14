package distributed.transaction.model;

/**
 * 代金券
 *
 * @author Dean
 */
public class Voucher {
	private Long id;
	private Long userId;
	/**
	 * 代金券类型
	 */
	private String type;
	/**
	 * 金额
	 */
	private Double amount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
}
