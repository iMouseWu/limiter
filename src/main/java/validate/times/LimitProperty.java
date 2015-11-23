package validate.times;

import validate.ErrorInfo;

/**
 * 访问属性。暂时请先保证getVisitCount/getSecond可以整除。谢谢
 * @author wuhao
 *
 */
public interface LimitProperty {
	
	/**
	 * 获取限制的条件的时间。比如说限制条件是每分钟120次。那么这里返回的值就是1*60
	 * @return
	 */
	int getSecond();
	
	/**
	 * 获取限制的条件的时间内访问次数。比如说限制条件是每分钟120次。那么这里返回的值就是120
	 * @return
	 */
	int getVisitLimitCount();
	
	
	ErrorInfo getErrorMessage();
}
