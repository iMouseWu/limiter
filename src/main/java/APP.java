import tokenbucket.TokenBucketContainer;
import tokenbucket.dao.TokenBucketDAO;
import tokenbucket.dao.impl.LocalTokenBucketDAOImpl;
import tokenbucket.lock.LockService;
import tokenbucket.lock.impl.LocalLockServiceImpl;
import tokenbucket.manage.TokenBucketManager;
import tokenbucket.manage.TokenFilledStrategy;
import tokenbucket.manage.impl.DefaultTokenFilledStrategy;
import tokenbucket.service.TokenBucketService;
import tokenbucket.service.impl.TokenBucketServiceImpl;

import java.util.concurrent.CountDownLatch;

/**
 * @author wuhao
 */
public class APP {

    private static int threadNum = 1000;

    private static CountDownLatch countDownLatch = new CountDownLatch(threadNum);

    public static void main(String[] args) {

        TokenBucketContainer tokenBucketContainer = new TokenBucketContainer("test1");
        tokenBucketContainer.setTokenBucketService(ceateTokenBucketService());
        tokenBucketContainer.tryConsume();


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
        TokenBucketServiceImpl tokenBucketService = new TokenBucketServiceImpl();

        TokenBucketManager tokenBucketManager = new TokenBucketManager();
        LockService lockService = LocalLockServiceImpl.getInstance();
        TokenBucketDAO tokenBucketDAO = LocalTokenBucketDAOImpl.getInstance();
        tokenBucketManager.setLockService(lockService);
        tokenBucketManager.setTokenBucketDAO(tokenBucketDAO);

        TokenFilledStrategy tokenFilledStrategy = new DefaultTokenFilledStrategy();
        tokenBucketService.setTokenBucketManager(tokenBucketManager);
        tokenBucketService.setTokenFilledStrategy(tokenFilledStrategy);
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
            TokenBucketContainer tokenBucketContainer = new TokenBucketContainer("test1");
            tokenBucketContainer.setTokenBucketService(ceateTokenBucketService());
            boolean success = tokenBucketContainer.tryConsume();
            System.out.println(Thread.currentThread().getName() + ":" + success);

        }
    }

}
