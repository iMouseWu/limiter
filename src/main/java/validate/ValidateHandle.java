package validate;

import java.util.ArrayList;
import java.util.List;

public class ValidateHandle {

    private Validate timeValidate;

    private Validate ipValidate;

    public void setTimeValidate(Validate timeValidate) {
        this.timeValidate = timeValidate;
    }

    public void setIpValidate(Validate ipValidate) {
        this.ipValidate = ipValidate;
    }

    /**
     * 如果返回的是不为null，就说明验证不通过。返回的就是错误的信息
     *
     * @param url
     * @param appKet
     * @return
     */
    public ErrorInfo handle(String appKey, String url) {
        ValidateHandlerChain chain = crateChain();
        ValidateContext context = new ValidateContext();
        context.setAppKey(appKey);
        context.setUrl(url);
        chain.doHandle(context);
        if (context.isFail()) {
            return context.getMessage();
        }
        return null;
    }

    private ValidateHandlerChain crateChain() {
        List<Validate> validates = new ArrayList<Validate>();
        validates.add(timeValidate);
        validates.add(ipValidate);
        return new ValidateHandlerChainImpl(validates);
    }

}
