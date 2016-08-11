package com.limiter.open.validate.chains;

/**
 * 验证器
 *
 * @author wuhao
 */
public interface Validate {

    public void doNextValidate(ValidateContext context, ValidateHandlerChain handlerChain);

}
