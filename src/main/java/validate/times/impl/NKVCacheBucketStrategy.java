package validate.times.impl;

import validate.times.TimeCountStrategy;

//@Service("nkvCacheBucketStrategy")
public class NKVCacheBucketStrategy implements TimeCountStrategy {
    @Override
    public int getCount(String vistor, String resource) {
        return 0;
    }

    @Override
    public boolean visit(String vistor, String resource) {
        return false;
    }

//	@Autowired
//	private DefaultExtendNkvClient defaultExtendNkvClient;
//
//	@Resource(name = "limitPropertyFactoryImpl")
//	private LimitPropertyFactory limitPropertyAchieve;
//
//	private static final String BUCKET_PREFIX_KEY = "XIUPIN_OPENAPI_BUCKET_";
//
//	private static final String LOCK_PREFIX_KEY = "XIUPIN_OPENAPI_LOCK_";
//
//	private static final Logger log = LoggerFactory.getLogger(NKVCacheBucketStrategy.class);
//
//	/**
//	 * 调用NKV超时时间
//	 */
//	private static final long NKV_TIMEOUT = 5000L;
//
//	/**
//	 * redis中操作一个资源的超时时间6S
//	 */
//	private static final int LOCK_EXPIRETIME = 6;
//
//	/**
//	 * 等待一个锁的时间
//	 */
//	private static final long WAIT_LOCK_TIME = 7000L;
//
//	@Override
//	public int getCount(String vistor, String resource) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public boolean visit(String vistor, String resource) {
//		LimitProperty visitProperty = limitPropertyAchieve.getProperty(vistor, resource);
//		int capacity = visitProperty.getVisitLimitCount();
//		int refillTokens = capacity / visitProperty.getSecond();
//
//		String childKey = generateKey(vistor, resource);
//		String lockKey = LOCK_PREFIX_KEY + childKey;
//		boolean locksuccess = lock(lockKey);
//		try {
//			if (locksuccess) {
//				String bucketKey = BUCKET_PREFIX_KEY + childKey;
//				return consume(bucketKey, capacity, refillTokens);
//			}
//		} finally {
//			unLock(lockKey);
//		}
//		return false;
//	}
//
//	private String generateKey(String vistor, String resource) {
//		return vistor + "_" + resource;
//	}
//
//	private boolean lock(String lockKey) {
//		long startTime = System.currentTimeMillis();
//		try {
//			do {
//				String value = System.currentTimeMillis() + "";
//				@SuppressWarnings("deprecation")
//				Result<Void> result = defaultExtendNkvClient.putnx(NkvConfiguration.NKV_RDB_COMMON_NAMESPACE,
//						lockKey.getBytes(), value.getBytes(), new NkvOption(NKV_TIMEOUT, (short) 0, LOCK_EXPIRETIME));
//				if (null != result && ResultCode.OK.equals(result.getCode())) {
//					return true;
//				}
//				Thread.sleep(300);
//			} while (System.currentTimeMillis() < startTime + WAIT_LOCK_TIME);
//		} catch (NkvRpcError | NkvFlowLimit | NkvTimeout | InterruptedException e) {
//			log.error("lockerror the key is " + lockKey, e);
//		} catch (Exception e) {
//			log.error("ExceptionError the key is " + lockKey, e);
//		}
//		return false;
//	}
//
//	@SuppressWarnings("deprecation")
//	private void unLock(String lockKey) {
//		try {
//			defaultExtendNkvClient.remove(NkvConfiguration.NKV_RDB_COMMON_NAMESPACE, lockKey.getBytes(), new NkvOption(
//					NKV_TIMEOUT, (short) 0));
//		} catch (NkvRpcError | NkvFlowLimit | NkvTimeout | InterruptedException e) {
//			log.error("lockerror the key is " + lockKey, e);
//		} catch (Exception e) {
//			log.error("ExceptionunLock the key is " + lockKey, e);
//		}
//	}
//
//	/**
//	 * 调用此方法前提必须调用lock方法。防止多线程问题
//	 *
//	 * @param bucketKey
//	 * @param bucketNum
//	 * @param refillTokens
//	 * @return
//	 */
//	@SuppressWarnings("deprecation")
//	private boolean consume(String bucketKey, long capacity, int refillTokens) {
//		try {
//			TokenContainer tokenContainer = null;
//			Result<byte[]> result = defaultExtendNkvClient.get(NkvConfiguration.NKV_RDB_COMMON_NAMESPACE,
//					bucketKey.getBytes(), new NkvOption(NKV_TIMEOUT, (short) 0));
//			if (null != result && ResultCode.OK.equals(result.getCode())) {
//				tokenContainer = (TokenContainer) SerializationUtils.deserialize(result.getResult());
//			} else if (null != result && ResultCode.NOTEXISTS.equals(result.getCode())) {
//				tokenContainer = new TokenContainer(bucketKey, capacity, capacity, TimeUnit.SECONDS.toMillis(1));
//			} else {
//				log.info("consumeError the result is{}", JSON.toJSON(result));
//				return false;
//			}
//			TokenBucket tokenBucket = TokenBuckets.builder().withTokenContainer(tokenContainer)
//					.withFixedIntervalRefillStrategy(refillTokens, tokenContainer).build();
//
//			// 重要:调用tryConsume方法会改变tokenContainer的Size,lastRefillTime,nextRefillTime的值
//			boolean consumeSucccess = tokenBucket.tryConsume();
//			defaultExtendNkvClient.put(NkvConfiguration.NKV_RDB_COMMON_NAMESPACE, bucketKey.getBytes(),
//					SerializationUtils.serialize(tokenContainer), new NkvOption(NKV_TIMEOUT, (short) 0));
//			return consumeSucccess;
//		} catch (NkvRpcError | NkvFlowLimit | NkvTimeout | InterruptedException e) {
//			log.error("consumeerror the key is " + bucketKey, e);
//		} catch (Exception e) {
//			log.error("ExceptionError the key is " + bucketKey, e);
//		}
//		return false;
//	}
}
