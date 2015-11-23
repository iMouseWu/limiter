package validate.times.impl;

import validate.ErrorInfo;
import validate.times.LimitProperty;

/**
 * 可以通过走配置来完善这个需求
 * 
 * @author wuhao
 *
 */
public class OrderLimitProperty implements LimitProperty {

	@Override
	public int getSecond() {
		return 60;
	}

	@Override
	public int getVisitLimitCount() {
		return 120;
	}

	@Override
	public ErrorInfo getErrorMessage() {
		return null;
	}

}
