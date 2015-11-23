package validate.times;

/**
 * 限制属性获取类
 * 
 * @author wuhao
 *
 */
public interface LimitPropertyFactory {

	/**
	 * 根据访问者和访问的地址可以返回相应的限制信息(可以根据不同的策略来执行不同的限制)
	 * 可以根据不同的AppKey和不同的resource来做限制,也可以只针对resource来做限制或者只根据vistor来做限制
	 * 
	 * @param vistor
	 * @param resource
	 * @return
	 */
	LimitProperty getProperty(String vistor, String resource);

}
