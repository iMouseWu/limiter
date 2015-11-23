package validate.buket;

import java.io.Serializable;

/**
 * 令牌信息类
 * 
 * @author wuhao
 *
 */
public class TokenContainer implements Serializable {

	private static final long serialVersionUID = 5608078652467720930L;

	/**
	 * 令牌最大数目
	 */
	private long capacity;

	/**
	 * 拥有令牌的数目(初始化容器拥有的令牌数目为0)
	 */
	private long size = 0;

	/**
	 * 令牌拥有者
	 */
	private String owner;

	/**
	 * 令牌桶上一次填充的时间
	 */
	private long lastRefillTime;

	/**
	 * 令牌桶下一次填充的时间
	 */
	private long nextRefillTime;

	/**
	 * 每添加一个令牌需要的时间
	 */
	private long periodDurationInMillis;

	public TokenContainer(String owner, long capacity, long size, long periodDurationInMillis) {
		this.capacity = capacity;
		this.size = size;
		this.owner = owner;
		this.periodDurationInMillis = periodDurationInMillis;
		this.nextRefillTime = -periodDurationInMillis;
		this.lastRefillTime = -periodDurationInMillis;
	}

	public TokenContainer(String owner, long capacity) {
		this.capacity = capacity;
		this.owner = owner;
	}

	public TokenContainer() {
		super();
	}

	public long getCapacity() {
		return capacity;
	}

	public void setCapacity(long capacity) {
		this.capacity = capacity;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public long getLastRefillTime() {
		return lastRefillTime;
	}

	public void setLastRefillTime(long lastRefillTime) {
		this.lastRefillTime = lastRefillTime;
	}

	public long getNextRefillTime() {
		return nextRefillTime;
	}

	public void setNextRefillTime(long nextRefillTime) {
		this.nextRefillTime = nextRefillTime;
	}

	public long getPeriodDurationInMillis() {
		return periodDurationInMillis;
	}

	public void setPeriodDurationInNanos(long periodDurationInMillis) {
		this.periodDurationInMillis = periodDurationInMillis;
	}

}
