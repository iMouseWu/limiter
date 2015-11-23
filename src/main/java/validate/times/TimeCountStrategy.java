package validate.times;

/**
 * 访问次数计算策略
 * 
 * @author wuhao
 *
 */
public interface TimeCountStrategy {

	/**
	 * 获取某一个vistor的访问资源resource次数(需要保证线程安全)
	 * 
	 * @param key
	 * @return
	 */
	int getCount(String vistor, String resource);

	/**
	 * vistor访问某个resource,如果可以访问则访问次数加1,并且返回true.如果访问失败返回false
	 * 暂时只返回true和false。如果有需求,可以返回具体的信息
	 * 
	 * @param key
	 * @return
	 */
	boolean visit(String vistor, String resource);

}
