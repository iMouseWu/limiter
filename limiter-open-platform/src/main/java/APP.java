import com.limiter.TokenBucketContainer;
import com.limiter.config.ConfigLoadImpl;
import com.limiter.config.RuleDaoImpl;
import com.limiter.config.parse.LocalFileParseServiceImpl;
import com.limiter.lock.LockService;
import com.limiter.lock.impl.LocalLockServiceImpl;
import com.limiter.tokenbucket.config.LocalConfigCenterImpl;
import com.limiter.tokenbucket.dao.TokenBucketDAO;
import com.limiter.tokenbucket.dao.impl.LocalTokenBucketDAOImpl;
import com.limiter.tokenbucket.manage.TokenBucketManager;
import com.limiter.tokenbucket.manage.TokenFilledStrategy;
import com.limiter.tokenbucket.manage.impl.DefaultTokenFilledStrategy;
import com.limiter.tokenbucket.service.TokenBucketService;
import com.limiter.tokenbucket.service.impl.TokenBucketServiceImpl;

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
        LockService lockService = LocalLockServiceImpl.getInstance();
        TokenBucketDAO tokenBucketDAO = LocalTokenBucketDAOImpl.getInstance();
        tokenBucketManager.setLockService(lockService);
        tokenBucketManager.setTokenBucketDAO(tokenBucketDAO);

        TokenFilledStrategy tokenFilledStrategy = new DefaultTokenFilledStrategy();
        tokenBucketService.setTokenBucketManager(tokenBucketManager);
        tokenBucketService.setTokenFilledStrategy(tokenFilledStrategy);
        tokenBucketService.setConfigCenter(configCenter);
        return tokenBucketService;
    }

    public static class Inner implements Runnable {

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
