import com.limiter.open.TokenBucketContainer;
import com.limiter.open.config.ConfigLoadImpl;
import com.limiter.open.config.RuleDaoImpl;
import com.limiter.open.config.parse.LocalFileParseServiceImpl;
import com.limiter.open.lock.LockService;
import com.limiter.open.lock.impl.LocalLockServiceImpl;
import com.limiter.open.tokenbucket.core.TokenBucketDAO;
import com.limiter.open.tokenbucket.config.impl.LocalConfigCenterImpl;
import com.limiter.open.tokenbucket.core.impl.LocalTokenBucketDAOImpl;
import com.limiter.open.tokenbucket.core.TokenBucketManager;
import com.limiter.open.tokenbucket.core.impl.DefaultTokenFilledStrategy;
import com.limiter.open.tokenbucket.core.TokenBucketService;
import com.limiter.open.tokenbucket.core.impl.TokenBucketServiceImpl;
import com.limiter.open.tokenbucket.core.impl.TimeToolsImpl;

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
        LocalConfigCenterImpl configCenter = new LocalConfigCenterImpl();

        ConfigLoadImpl configLoad = new ConfigLoadImpl();
        configLoad.setConfigCenter(configCenter);
        configLoad.setRuleDao(ruleDao);
        configLoad.load();

        TokenBucketServiceImpl tokenBucketService = new TokenBucketServiceImpl();
        TokenBucketManager tokenBucketManager = new TokenBucketManager();
        LockService lockService = new LocalLockServiceImpl();
        TokenBucketDAO tokenBucketDAO = new LocalTokenBucketDAOImpl();
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
