import com.limiter.TokenBucketContainer;
import com.limiter.config.ConfigLoadImpl;
import com.limiter.config.RuleDaoImpl;
import com.limiter.config.parse.LocalFileParseServiceImpl;
import com.limiter.lock.LockService;
import com.limiter.tokenbucket.ObjectFactory;
import com.limiter.tokenbucket.dao.TokenBucketDAO;
import com.limiter.tokenbucket.dao.impl.LocalConfigCenterImpl;
import com.limiter.tokenbucket.manage.TokenBucketManager;
import com.limiter.tokenbucket.manage.impl.DefaultTokenFilledStrategy;
import com.limiter.tokenbucket.service.TokenBucketService;
import com.limiter.tokenbucket.service.impl.TokenBucketServiceImpl;
import com.limiter.tokenbucket.time.impl.TimeToolsImpl;

import java.util.concurrent.CountDownLatch;

/**
 * @author wuhao
 */
public class APP {

    private static int threadNum = 1000;

    private static CountDownLatch countDownLatch = new CountDownLatch(threadNum);

    public static void main(String[] args) {
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < threadNum; i++) {
            countDownLatch.countDown();
            Thread thread = new Thread(new Inner(), i + "");
            thread.start();
        }
    }

    private static TokenBucketService ceateTokenBucketService() {

        //解析器
        LocalFileParseServiceImpl localFileParseService = new LocalFileParseServiceImpl();
        RuleDaoImpl ruleDao = new RuleDaoImpl();
        ruleDao.setParseService(localFileParseService);
        LocalConfigCenterImpl configCenter = ObjectFactory.getConfigCenterInstance();

        ConfigLoadImpl configLoad = new ConfigLoadImpl();
        configLoad.setConfigCenter(configCenter);
        configLoad.setRuleDao(ruleDao);
        configLoad.load();

        TokenBucketServiceImpl tokenBucketService = new TokenBucketServiceImpl();
        TokenBucketManager tokenBucketManager = new TokenBucketManager();
        LockService lockService = com.limiter.lock.ObjectFactory.getLocalLockServiceInstance();
        TokenBucketDAO tokenBucketDAO = ObjectFactory.getTokenBucketDAOInstance();
        tokenBucketManager.setLockService(lockService);
        tokenBucketManager.setTokenBucketDAO(tokenBucketDAO);

        DefaultTokenFilledStrategy tokenFilledStrategy = new DefaultTokenFilledStrategy();
        tokenFilledStrategy.setTimeTools(new TimeToolsImpl());

        tokenBucketService.setTokenBucketManager(tokenBucketManager);
        tokenBucketService.setTokenFilledStrategy(tokenFilledStrategy);
        tokenBucketService.setConfigCenter(configCenter);
        tokenBucketService.setTimeTools(new TimeToolsImpl());
        return tokenBucketService;
    }

    private static class Inner implements Runnable {

        @Override
        public void run() {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            TokenBucketContainer tokenBucketContainer = new TokenBucketContainer("test_" + "getTest");
            tokenBucketContainer.setTokenBucketService(ceateTokenBucketService());
            boolean success = tokenBucketContainer.consume();
            System.out.println(Thread.currentThread().getName() + ":" + success);

        }
    }

}
