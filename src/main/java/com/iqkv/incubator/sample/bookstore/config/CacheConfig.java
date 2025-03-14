/*
 * Copyright 2025 IQKV Team.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.iqkv.incubator.sample.bookstore.config;

import java.time.Duration;

import com.iqkv.boot.cache.CacheProperties;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.jsr107.Eh107Configuration;
import org.hibernate.cache.jcache.ConfigSettings;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
class CacheConfig {

  private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

  CacheConfig(CacheProperties cacheProperties) {
    final var ehcacheProperties = cacheProperties.getEhcache();
    jcacheConfiguration =
        Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder
                .newCacheConfigurationBuilder(Object.class,
                    Object.class,
                    ResourcePoolsBuilder.heap(ehcacheProperties.getMaxEntries()))
                .withExpiry(
                    ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(
                        ehcacheProperties.getTimeToLiveSeconds()
                    ))
                )
                .build()
        );
  }

  @Bean
  HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
    return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
  }

  @Bean
  JCacheManagerCustomizer cacheManagerCustomizer() {
    return cm -> {
      createCache(cm, com.iqkv.incubator.sample.bookstore.entity.Product.class.getName());
      createCache(cm, com.iqkv.incubator.sample.bookstore.entity.ProductCategory.class.getName());
      createCache(cm, com.iqkv.incubator.sample.bookstore.entity.GeoCountry.class.getName());
      createCache(cm, com.iqkv.incubator.sample.bookstore.entity.GeoState.class.getName());
      createCache(cm, com.iqkv.incubator.sample.bookstore.entity.Address.class.getName());
      createCache(cm, com.iqkv.incubator.sample.bookstore.entity.Customer.class.getName());
      createCache(cm, com.iqkv.incubator.sample.bookstore.entity.Order.class.getName());
      createCache(cm, com.iqkv.incubator.sample.bookstore.entity.OrderItem.class.getName());
    };
  }

  private void createCache(javax.cache.CacheManager cm, String cacheName) {
    javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
    if (cache != null) {
      cache.clear();
    } else {
      cm.createCache(cacheName, jcacheConfiguration);
    }
  }
}
