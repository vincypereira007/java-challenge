package jp.co.axa.apidemo.config;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import jp.co.axa.apidemo.constants.ApplicationConstants;


/**
 * The Class CachingConfig.
 */
@Configuration
@EnableScheduling
public class CachingConfig {

  /**
   * Report cache evict.
   */
  @CacheEvict(allEntries = true, value = {ApplicationConstants.CACHE_EMP_INFO, ApplicationConstants.CACHE_EMP_INFO_BY_ID})
  @Scheduled(fixedDelay = 5 * 60 * 1000 ,  initialDelay = 500)
  public void reportCacheEvict() {
    System.out.println("Flush Cache");
  }

}