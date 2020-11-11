package com.dc.framework.cache;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author zhuangcy
 * @date 2020/11/4 9:43
 * @description 自定义缓存类（LRU算法：最近最久未使用算法）
 * TODO<zhuangcy> 感觉还可以做一个全局缓存管理器，让spring管理
 * 用ConcurrentHashMap做缓存也行
 */
public class CustomLRUCache<K, V> {
    // 缓存最大容量
    private int maxSize;
    private LinkedHashMap<K, V> cacheMap;
    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    //获取读锁
    private final ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
    //获取写锁
    private final ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

    public CustomLRUCache(int maxSize) {
        this.maxSize = maxSize;
        this.cacheMap = new LinkedHashMap<K, V>(maxSize, 0.75f, true) {
            private static final long serialVersionUID = -4982533463846617770L;

            @Override
            protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
                return size() > maxSize;
            }
        };
    }

    public int getMaxSize() {
        return maxSize;
    }

    public int size() {
        return cacheMap.size();
    }

    public V put(K key, V value) {
        try {
            writeLock.lock();
            return cacheMap.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    public V get(K key) {
        try {
            readLock.lock();
            return cacheMap.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public V remove(K key) {
        try {
            writeLock.lock();
            return cacheMap.remove(key);
        } finally {
            writeLock.unlock();
        }
    }

    public void clear() {
        try {
            writeLock.lock();
            cacheMap.clear();
        } finally {
            writeLock.unlock();
        }
    }

    public Set<K> keys() {
        try {
            readLock.lock();
            return cacheMap.keySet();
        } finally {
            readLock.unlock();
        }
    }

    public Collection<V> values() {
        try {
            readLock.lock();
            return cacheMap.values();
        } finally {
            readLock.unlock();
        }
    }

    public boolean isEmpty() {
        try {
            readLock.lock();
            return cacheMap.isEmpty();
        } finally {
            readLock.unlock();
        }
    }

    public boolean containsKey(K key) {
        try {
            readLock.lock();
            return cacheMap.containsKey(key);
        } finally {
            readLock.unlock();
        }
    }

}
