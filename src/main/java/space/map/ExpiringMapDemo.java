package space.map;

import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;

import java.util.concurrent.TimeUnit;

public class ExpiringMapDemo {
    /**
     * ExpirationPolicy.CREATED：在每次更新元素时，过期时间同时清零。
     * ExpirationPolicy.ACCESSED：在每次访问元素时，过期时间同时清零。
     * .expiration(2000)：2秒后过期
     */
    public static ExpiringMap<String, String> expiringMap = ExpiringMap.builder().expiration(2000,TimeUnit.MILLISECONDS)
            .expirationPolicy(ExpirationPolicy.CREATED)
            .maxSize(5000)
            .build();

    public static void main(String[] args) {
        expiringMap.get("jack");
        System.out.println(expiringMap.get("jack"));
    }
}
