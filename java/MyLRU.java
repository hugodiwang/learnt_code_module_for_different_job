package Demo;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

class LRUCache<K, V> extends LinkedHashMap<K, V> {
  private int cacheSize;
  public LRUCache(int cacheSize) {
          super(Math.max(16,(int) Math.ceil (cacheSize / 0.75f) + 1), 0.75f, true);
          this.cacheSize = cacheSize;
          }
  protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
          return size() > cacheSize;
          }
}

public class MyLRU {
  public static void main(String[] args){
    LRUCache<String,Integer> lrucache = new LRUCache<String,Integer>(3);
    lrucache.put("aaa", 1);
    System.out.println(lrucache);
    lrucache.put("bbb", 2);
    System.out.println(lrucache);
    lrucache.put("ccc", 3);
    System.out.println(lrucache);
    lrucache.put("ddd", 4);
    System.out.println(lrucache);
    lrucache.put("eee", 5);
    System.out.println(lrucache);
  }
}
