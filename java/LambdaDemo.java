package Demo;

import java.util.Arrays;
import java.util.stream.IntStream;

public class LambdaDemo {
  public static void main(String[] args) {


  }
}

/***
 *
 * (参数列表) -> {实现语句}
 * 用lambda 类实现 interface 不用实例化
 * 接口名 addition = (Intger a, Integer b) -> {
 *   return a + b + 0f;
 * }
 *
 * lambda 参数列表 可以忽略参数类型
 * 单行实现代码可以省略大括号与return
 *  只有一个参数可以省略小括号
 *
 *  lambda 只能实现函数式接口
 *  函数式接口 有且只有一个抽象方法的接口
 *
 *  predict 对数据进行判断
 *  Predict.test
 *  Predict<Intger> p = n->n>4;
 *  boolean result = p.test(4);
 *  List<Integer> list = Arrays.asList(1,2,3,4)
 *  由代码决定判断条件
 *  public void filter(List<Integer> list, Predict<Integer>p)
 *  for(Integer num : list){
 *    if(p.test(num))
 *      asdfads
 *  }
 *  consumber只有一个输入参数， 但是不返回任何数值
 *  accept()
 *  consumber不是为了让传入的参数变 而是为了让干什么变
 *  public static void output(Consumer<String> consumer){
 *    consumber.accept("sadfadsfasd");
 *  }
 *  output(s->System.out.println(s));
 *
 *
 *  Function<T, R> 有一个输入 有一个输出
 *  R = apply(T)
 *  定长随机字符串
 *  Function<Integer, String> s = l->{
 *    String chars = "qwertyuiopasdfghjklzxcvbnm1234567890";
 *    StringBuffer stringBuffer = new StringBuffer();
 *    Random random = new Random;
 *    for(int i = 0; i<l; i++){
 *      int z = random.nextInt(chars.length());
 *      StringBuffer.append(chars.charAt(z));
 *    return stringBuffer.toString();
 *  }
 *  String sout = s.apply(10);
 *
 * ()->{asdfasd} supplier 无参输入
 * Stream<Integer> stream = Stream.generate(() -> {new Random().nextInt()})
 * Stream<Integer> stream = Stream.iterate(1, n->n+1);
 *
 *    String str = "abcd";
 *     IntStream si = str.chars();
 */

