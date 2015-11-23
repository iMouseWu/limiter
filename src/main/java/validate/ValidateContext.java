package validate;

/**
 * 验证上下文
 * 
 * @author wuhao
 *
 */
public class ValidateContext {

	/**
	 * appKey
	 */
	private String appKey;

	/**
	 * 请求的URL
	 */
	private String url;

	/**
	 * 请求的IP
	 */
	private String ip;

	/**
	 * 验证是否成功
	 */
	private boolean success;

	/**
	 * 附带信息
	 */
	private ErrorInfo message;

	public ErrorInfo getMessage() {
		return message;
	}

	public void setMessage(ErrorInfo message) {
		this.message = message;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public boolean isSuccess() {
		return success;
	}

	public boolean isFail() {
		return !success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
