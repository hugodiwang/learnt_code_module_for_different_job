package Demo;


/** stringbuffer
 *string
 * 每个字串常量都属于以各String 类匿名对象，不可更改
 * String有两个常量池，静态， 运行
 * disadv: 内容不可修改
 *
 * stringbuffer可以实现内容的修改
 *  StringBuffer buf = new StringBuffer(str); 构造可为空
 *  .append/ .toString()
 *  .insert(int offset, 数据类型 b)
 *  .delete(int start, int end)
 *  .reverse()
 *  String 的 + 在编译时转变为 append 方法
 *
 * StringBuilder 与stringbuffer 功能相同
 * stringbuffer 加了 synchronized 标注 而 stringbuilder 没加
 */

/**
 * charSequence interface
 * 是string, stringBuffer, stringBuilder的共同接口
 * charSequence csq = "hello"  子例向接口实例化
 * 含有 方法 subSequences, length, charAt
 *
 */

/**
 * AutoClosable 自动关闭资源
 * 要实现 autoclosable
 * 1. override close() threw exception ;
 * 2. 用try {
 *
 * } (catch Exception e) {
 * }
 *
 */

/**
 * runtime
 * 与jvm运行状态有关的类， 私有化， 单例设计， 每个jvm只有一个runtime 类
 * public static Runtime getRuntime();
 * Runtime run = Runtime.getRuntime();
 * 1. run.availableProcessor()
 * 2. 获取最大可用内存空间 long = maxMemory  1/4 of machine memory
 * 3. 获取空闲内存空间 long = freeMemory   1/64 of machine memory
 * 4. 获取可用内存空间 long = totalMemory
 * 5 手工进行GC void = gc
 */

/**
 * system 类
 * system.arraycopy(Object src, int srcPos, ....)
 * system.gc() 执行runtime.gc() 只有这一个手动处理方法
 * long = system.currentTimeMillis() 操作耗时的统计
 */


/** cleaner 类
 * 对象清理，为了防止延迟，cleaner利用了另一个线程，中间创建
 * 了 一个 autoclosable 对象，写了一个runnable 关闭类，
 */

/**
 * clone protected Object clone() throws CloneNotSupportedException
 * 不是所有的类都能克隆， 这个类必须要 implement Cloneable
 * 并且 要override
 * protected Object clone() throws CloneNotSupprtedException{
 *   return super.clone();
 * }
 * 返回的时object 外部还要 强转类型
 */


/**
 * java.util.Random
 * new Random() . nextInt(bound)
 */

/** 正则
 * str.matches(regex)
 * str.replaceAll(regex, replacement)
 * str.replaceFirst(regex, replacement)
 * str.split(regex)
 * str.split(regex, int limit) 多少个
 *
 * . 任意字符
 * \\ 一个\
 * [abc] a,b,c中一个
 * [^abc] 不是a,b,c中的一个
 * \d 0-9
 * \D 非0-9
 * \s 空格 换行 或制表符
 * \S 非空格 换行 制表符
 * \w 字母数字下换线
 * ? 0/1次
 * * 0，1，多次
 * + 1次或多次
 */


/**
 * comparable
 * implemeents comparable<T>
 * override
 * public int compareTo(<T> t){
 *   return this - t
 * }
 *
 * comparator
 * 把排序策略 与 类的编写 分离
 *  1. 函数式接口， lambda
 *  2. int = compare(T O1, T O2)
 *  3. static 方法
 *
 *  class myComparator implements Comparator<T>{
 *    @Override
 *    public int compare(T o1, T o2){
 *      return o1 - o2
 *    }
 *
 *    Array.sort(xx, new myComparator())
 *  }
 */

public class MyAPI {
  public static void main(String[] args) {

  }

}
