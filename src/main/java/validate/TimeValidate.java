package validate;

import validate.times.LimitProperty;
import validate.times.LimitPropertyFactory;
import validate.times.TimeCountStrategy;

/**
 * 调用次数验证器
 *
 * @author wuhao
 */
public class TimeValidate implements Validate {

    private TimeCountStrategy timeCountStrategy;

    private LimitPropertyFactory visitPropertyAchieve;

    @Override
    public void doNextValidate(ValidateContext context, ValidateHandlerChain handlerChain) {
        boolean success = timeCountStrategy.visit(context.getAppKey(), context.getUrl());
        if (success) {
            handlerChain.doHandle(context);
        } else {
            doFailed(context);
        }
    }

    private void doFailed(ValidateContext context) {
        LimitProperty visitProperty = visitPropertyAchieve.getProperty(context.getAppKey(), context.getUrl());
        context.setSuccess(false);
        context.setMessage(visitProperty.getErrorMessage());
    }

}
