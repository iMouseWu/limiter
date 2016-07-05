package com.limiter.validate.chains;

import java.util.Iterator;
import java.util.List;

//@Service("validateHandlerChain")
public class ValidateHandlerChainImpl implements ValidateHandlerChain {

    private Iterator<Validate> validateIterables;

    public ValidateHandlerChainImpl(List<Validate> validates) {
        validateIterables = validates.iterator();
    }

    @Override
    public void doHandle(ValidateContext context) {
        if (validateIterables.hasNext()) {
            Validate validate = validateIterables.next();
            validate.doNextValidate(context, this);
        }
    }
}
