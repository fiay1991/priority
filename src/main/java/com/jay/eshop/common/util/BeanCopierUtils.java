package com.jay.eshop.common.util;

import java.util.HashMap;
import java.util.Map;

import net.sf.cglib.beans.BeanCopier;

/**
 * eanCopier工具类
 * @author jayjluo
 *
 */
public class BeanCopierUtils {

	/**
	 * BeanCopier缓存
	 */
	public static Map<String, BeanCopier> beanCopierCacheMap = new HashMap<String, BeanCopier>();
	
	public static void copyProperties(Object source, Object target) {
		String cacheKey = source.getClass().toString() + 
						target.getClass().toString();
		
		BeanCopier beancopier = null;
		
		if(!beanCopierCacheMap.containsKey(cacheKey)) {
			synchronized (BeanCopierUtils.class) {
				if(!beanCopierCacheMap.containsKey(cacheKey)) {
					beancopier = BeanCopier.create(source.getClass(), target.getClass(), false);
					beanCopierCacheMap.put(cacheKey, beancopier);
				}
			}
		}else {
			beancopier = beanCopierCacheMap.get(cacheKey);
		}
		
		beancopier.copy(source, target, null);
	}
}
