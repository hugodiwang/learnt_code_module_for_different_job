package Demo;

/**
 * 动态数据结构
 * 核心接口：
 * Collection, List, Map, Set, Iterator, Enumeration,
 * Queue, ListEnumeration
 *
 * java.utils.Collection
 * boolean add(E e)
 * boolean addAll(Collection<? extends E> e)
 * void clear()
 * boolean contains(Obejct o)
 * boolean isEmpty()
 * boolean remove(Object o)
 * int size()
 * Object[] toArray()
 * Interator<E> iterator() 转为iterator 接口 数据输出
 *
 * List/Set extends Collection
 * Collection extends Iterable extends Iterator
 */


/**
 * List
 * E get(Int index)
 * E set(int index , e element)
 * listiterator<e> listiterator() 返回listeriterator的接口对象
 *
 * 子类:
 * ArrayList
 * LinkedList
 * Vector
 *
 * List中的静态方法
 * .of("sdfa","sdfa"); --- > []
 * Object mylist [] = List.of(xcvx).toArray()
 */


/**
 * ArrayList
 * implements List, RandomAccess, Cloneable, Serializable
 * extends AbstractList
 *
 * AbstractList implements List
 * extends abstractCollection
 *
 * abstractCollection implements Collection
 * 
 * ArrayList 是用数组写的，而不是List
 * 构造方法
 * 1 无参
 * public ArrayList(){
 *   this.elementData = DEFAULT_EMELEMTN_DATA; {}
 * }
 *
 * 2 有参（int capacity）
 *
 * 若实例化时没有传递初始化长度，则默认下使用空数组。若用了
 * add方法后，发现数组容量不足，会用max(当前容量, 10)的容量
 * 当长度不足时，用成倍的容量增长，并copy
 *
 * 在使用List保存自定义类对象时，若用到contains, remove要确保复写了equalts
 * public boolean equals(Object obj){
 *   if(this == obj) return true;
 *   if(obj == null) return false;
 *   if(!(obj instanceof Person)) return false;
 *   Person per = (Person) obj;
 *   return this.name.equals(per.name) && (this.age).equals(per.age);
 * }
 *
 */


/**
 * linkedList
 *  extends AbstractSequentialList
 *  implements List, Deque, Cloneable, Serializable
 *
 *  Deque extends Queue extends Collection
 *
 *  只有无参构造
 *  什么都能存， 连null都行
 *  用LIST构造
 */

/**
 * vector
 * 是古老版本的arrayList
 * 若使用无参构造，则初始大小为10
 * qie vector 采用都是 sybchronized 方法
 *
 */

/**
 * set
 * extends Collectons extends Iterable extends iterator
 * 无 get(index)方法
 * Set.of（“xxx”,"xxx）; 1.9后增加了 static 方法
 *
 * 子类
 * hashSet, TreeSet
 */


/**
 * hashSet
 * extends AbstractSet
 * implements Set, cloneable, serializable
 *  无序存储
 *  用的是HashMap实现的， 也涉及 loadfactor increase size， 只有keyset
 */

/**
 * treeSet
 * 保存有序
 * extends AbstractSet
 * implements NavigableSet, cloneable, serializable
 *
 * navigableset extends sortedset extends set
 *
 * 自定义类时， 必须实现 comparable 接口
 * treeSet 用 treeMap来实现
 * Treset 需要对类中的所有属性进行比对 确定重复项
 * 当类属性很多时，所以用hashset
 * hashset 判断重复不是 利用 compare 来实现的
 * ！！！
 * 利用的是对象编码处理  object.hashcode()
 * 如果编码不存在，则不重复； 若存在， 则进一步对象比较
 */


/**
 * 集合输出 4接口
 * iterator: 迭代 从前到后
 * ListIterator: 双向迭代
 * Enumerator: 枚举
 * forEach:
 *
 *
 * array的遍历要用itoerator,因为涉及 index
 *iterator
 * boolean hasNext()
 * E next()
 * default void remove() 删除当前数据， 在循环里
 *
 * 也可以用 new Arrays<>(Set)
 * Iterator<String> iter = Set.of("xxx").iterator();
 * while(iter.hasNext()){
 *   String str = iter.next();
 * }
 *
 * remove 与 collection.remove的不同
 * 在循环里不能用 collection.remove（“xx”）
 * 会报 concurrenctException
 *
 *
 *
 *
 * ListIterator
 * 用list 接口实例化 意思是 new ArrayList<>().listIterator()
 * 而上面的iterator 可以是任意的collection.Iterator()
 * hasPrevious()
 * Previous()
 * hasNext()
 * next()
 *
 *
 * Enumeration 对 vector 服务， 用vector.elements()
 * hasMoreElements()
 * nextElement()
 */


/**
 * Map<k,v> interface
 * v put(k,v)
 * v get(k)
 * set entrySet()
 * boolean containsKey(k)
 * set keySet()
 * v remove(k)
 *
 * 1.9后扩充楼static 方法
 * of
 * Map("one",1,"two",2)  同set一样用of, 有重复报错
 *
 *
 *
 *
 * #####################################
 *HashMap 子类
 * 无序存储
 * extends AbstracteMap
 * implements Map, cloneable, serializable
 * 用新key 覆盖旧key, 也可以保存为null(k,v均可以)
 * null = put(k,v) 不存在
 * privous = put(k,v) 旧k
 *
 * 当使用无参构造时，
 * loadFator : 0.75
 * public v put(k key,v value){
 *   return  putVal(hash(key), key, value, false, true);
 * }
 * putVal 用node的节点类来保存数据
 * 调用resize, 对容量进行扩充
 *
 * 如何扩容
 * 初始化容量 DEFAYLT_INIT_cAPACITY = 1<<4 = 16
 * 当我们保存的内容的容量 > loadfactor*16时，就进行扩充 2 倍
 *
 *
 * hashmap的工作原理
 * 存储利用node 存， 两种node , 链表（n）， 二叉树(logn)
 * treeify_threshold = 8
 * 用hashmap 进行数据保存时， 如果数据个数 < 8 用链表的形式保存，> 8 用
 * 红黑树的形式
 *
 *
 *#################################3
 * linkedHashMap
 * 保存数据为 添加顺序
 * 数据量不易过大
 * extends HashMap
 * implements Map
 *
 *
 *
 *
 *#######################################3
 * hashtable
 * 早期， 同 vector, enumeration
 * HashTable extends Dictinary(abstract)
 * implements Map, cloneable, serializable
 *
 * k,v 都不许为mull, --> NullpointerException
 * hashtable 与 hashmap的区别：
 * 也有0.75
 * hashmap 异步操作，非线程安全， 允许有null
 * hashtable synchronized ， 不能有null
 *
 *
 *###############################
 * hashMap 内部 node implements Map.Entry 接口
 * static class Node<K,V> implemnts Map.Entry<K,V>
 *
 * 作为 k,v的包装
 * public static interface Entry<k,v>{
 *   getKey
 *   getValue
 * }
 * Map.Entry<String, Integer> entry = Map.entry("ones",1);
 * entry.getKey()
 *
 *
 */

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * map 输出  iterator
 * map的方法里没有直接返回 iterator的
 * 但是有entrySet() ---> 转为 collection 集合 ---> 转为iterator
 * 里面每一个都是 Map.entry(k,v)----> getkey,value
 *
 *     Set<Map.Entry<String, Integer>> set = map.entrySet();
 *     Iterator<Map.Entry<String, Integer>> iter = set.iterator();
 *     while(iter.hasNext()){
 *       Map.Entry<String, Integer> me = iter.next();
 *       me.getKey(), ,me.getValue()
 *
 * for each 也要转为 set 集合
 * 也是为什么我要 转  keySet, valSet
 *
 * 自定义 key
 * map的get/put 都用hash 处理过key
 * public v get(key){
 *   Node<k,v> e;
 *   return getNode(hash(key, key) == null ? null: e.value);
 * }
 *
 * getNode里面有比较操作compareTo
 *
 * 所以自定义key的hashcode, equals
 *
 * 当在进行hashmap时出现了hash冲突， 如何解决？
 * 1， 冲突的地方，用链表保存
 *
 */


/**
 * stack 栈  class
 * extends vector(synchronized array)
 * e push(e)
 * e pop()
 * elementData[count] ...
 *
 * Queue 队列 class
 * 用linkedlist 实现
 * linkedlist类 implements list/ dequeue
 * dequeue extends queue
 * list/queue extends collections
 * boolean offer(e)
 * e peek() poll()
 * e
 *
 * priorityQueue class
 * 有排序， 跟comparable 有关
 * extends abstracqueue
 *
 *
 */


/**
 * properties 只能操作string
 * extends hashtable
 *
 * setProperty
 * getProperty
 * save/load ---> outputStream
 *
 */

/**
 * collections工具类 implements Collection/map/list/set/queue
 * 支持
 * Collections.addAll(all//这个是实例化上述类型,  “xxx”,"xxxxx")
 * binarySearch
 * sort
 * reverse
 *
 */


/**
 * steram  接口
 * collection 接口含有 .stream方法
 * Stream<String> stream = all.stream();
 * stream.count()
 * stream.filter(predicate 断言接口)
 * lazy, .count()
 *       .collect() 里面输入 collector 接口
 *                  Collectors.tolist() 返回 collector接口
 *
 * stream 允许分页操作
 * public Stream<T> limit(long maxSize) 设置取出的最大数据量
 * public Stream<T> skip(long n) 跳过指定的数据量
 *
 *
 *
 * mapreduce；
 * DoubleSummaryStatistics stat = mapToDouble((x)->x...).summaryStatistics()
 *  stat.getCount()
 *  stat.getSum()
 *  stat.getAverage()
 *
 */

//public class MyCollection {
//  public static void main(String[] args) {
//    Stack<String> s = new Stack<String>();
//    s.add("JK");
//    Stream<String> st = s.stream();
//    Stream s2 = st.filter((ele) -> ele.toLowerCase().contains("j"));
//    List<String> out =(List<String>) s2.collect(Collectors.toList());
//  }
//}

/**
 * enum
 * 使用enum定义的枚举类默认继承了java.lang.Enum类，而不是默认继承Object类，
 * 因此枚举类不能显示继承其他父类。其中java.lang.Enum类实现了
 * java.lang.Serializable和java.lang.Comparable两个接口。
 *
 *     //私有构造函数
 *     private Day(String s, int i)
 *     {
 *         super(s, i);
 *     }
 *
 *     Enum 抽象类
 *         protected Enum(String name, int ordinal) {
 *         this.name = name;
 *         this.ordinal = ordinal;
 *     }
 *
 *
 *enum  Weekday {
 *sa, MON, TUE, WED, THU, FRI, SAT;
 *}
 * 不是string, 直接用variable
 *
 *
 * //反编译Day.class
 * final class Day extends Enum
 *
 *
 * ordinal()方法返回一个int值，这个每个enum实例在声明时的次序，
 * 从0开始。可以使用==比较enum实例，
 * 编译器自动为你提供了equals()和hashCode()方法。
 * Enum类实现了Comparable接口和Serializable接口。
 *
 */
public class MyCollection {
  public static void main(String[] args) {
    Set<Character> x = new HashSet<Character>();

    Weekday day = Weekday.sa;
    if (day == Weekday.SAT || day == Weekday.sa) {
      System.out.println("Work at home!");
    } else {
      System.out.println("Work at office!");
    }
  }
}

enum  Weekday {
  sa, MON, TUE, WED, THU, FRI, SAT;
}