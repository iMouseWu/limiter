package validate;

/**
 * 验证器
 * 
 * @author wuhao
 *
 */
public interface Validate {

	public void doNextValidate(ValidateContext context, ValidateHandlerChain handlerChain);

}
