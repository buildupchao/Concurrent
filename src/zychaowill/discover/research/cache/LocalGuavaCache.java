package zychaowill.discover.research.cache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

public abstract class LocalGuavaCache<K, V> {

	protected int refreshDuration;
	
	protected TimeUnit refreshTimeunitType;
	
	protected int cacheMaximumSize;
	
	private LoadingCache<K, V> cache;
	private ListeningExecutorService backgroundRefreshPools = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(20));
	
	protected abstract void initCacheFields();
	
	protected abstract V getValueWhenExpire(K key) throws Exception;
	
	public abstract V getValue(K key);
	
	private LoadingCache<K, V> getCache() {
		if (cache == null) {
			synchronized (this) {
				if (cache == null) {
					initCacheFields();
					
					cache = CacheBuilder.newBuilder()
							.maximumSize(cacheMaximumSize)
							.refreshAfterWrite(refreshDuration, refreshTimeunitType)
							.build(new CacheLoader<K, V>() {
								@Override
								public V load(K key) throws Exception {
									return getValueWhenExpire(key);
								}
								
								@Override
								public ListenableFuture<V> reload(K key, V oldValue) throws Exception {
									return backgroundRefreshPools.submit(() -> getValueWhenExpire(key));
								}
							});
				}
			}
		}
		return cache;
	}
	
	protected V fetchDataFromCache(K key) throws ExecutionException {
		return getCache().get(key);
	}
}
