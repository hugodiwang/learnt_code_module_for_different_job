package Demo;

/***
 * 四大反射类
 * 1 class
 * 2 constructor
 * 3 method
 * 4 field
 *
 * 1 class 是 对类与接口的抽象， 保存类与接口的 结构化信息， 比如 有什么field, method, constructor 等等
 *  故 api：
 *  classObject = Class.forName("xdfasd") 找到对应类并加载到jvm
 *  Object = classObject.newInstance()
 *  采用默认的构造方法
 *
 *
 *
 *  ConstructorObj = classObject.getConstructor()

 *  Constructor constructor = employeeClass.getConstructor(new Class[]{
 *    Integer.class, String.class, Float.class
 *  })
 *  为了辨识是哪一个constructor 要传入类属性数组
 *  *  Obj = ConstructorObj.getInstance(new Object[]{
 *    100,"asdf",12.0
 *  })
 *
 *
 *
 *
 *
 *  Method MethodObje = classObject.getMethod(“methodname”, new Class[]{
 *
 *
 *  })
 *  Obj = methodObj.invoke(instance哪一个对象， 参数 new Obj[]{});
 *
 *  classObject.getField()
 *  Field field = classObject.getField("name")
 *  Object = field.get(instance)
 *
 *
 *
 *
 *  2 constructor 是所有构造方法的抽象， 保存了 所有构造方法的参数信息
 *  getConstructor(new class[]{})
 *  newInstance(new Object[]{})
 *
 *  3 method 是所有方法的抽象， 保存楼参数与返回信息
 *  getMethod("name", new Class[]{})
 *  invoke(instance, new Object[]{})
 *
 *  4 field 是所有field的抽象， 保存楼其 modifier 类型， 初始值等信息。
 *  get(instance)
 *  set(instance , new value)
 *
 */


/**
 * 上面的都是获取public 下面的获取private
 * getDeclaredMethod, constructor, field
 * getDelcaredMethods, constructors, fields
 * Fields[] fields = classObj.getDeclaredFieds()
 * if field.getModfiers() == 1public ==2private
 *
 *
 *
 */


/**
 * 反射： 重用性
 * 根据实例化对象 ---》 对象类型
 * Object。getClass() 获取类信息
 *
 * 返回类型的三种方式
 * 1. object类 java.lang
 * class Person{};
 * Person per = new Person();
 * Class<? extends Person> cls = per.getClass();
 * cls.getName()
 * 缺点：
 * 必须产生指定类实例后才能 得到类型 ， 有上限
 *
 *
 * 2. JVM 支持
 * class Person{};
 * Class<? extends Person> cls = Person.class;
 * 必须导入包 有上限
 *
 * 3. class 类 java.lang
 * public static Class<?> forName(String classNmae) throws ClassNotFundException
 * 不用导包， 无上限， 字符串定义
 * Class<?> cls = Class.forName("");
 *
 *ClassNotFundException ， ClassNotFundError
 * exception 出现在程序执行的时候
 * error 打完包，没有打外部包的时候
 */

///    ****************** 反射实例化

/** 反射对象实例化   example 1
 * 1.9 之前， public T newInstance()
 * 1.9 之后， class.getDeclaredCounstructor().newInsatance()
 * Object obj = cls.newInstance();
 * 调用了无参的构造方法 = new
 * 只能实例化无参， 所以不再用了，描述不准确
 * 改为：
 * cls.getDeclaredConstructor().newInstance();
 *
 * 什么时候用new 什么时候用反射实例化
 * 工厂设计模式：
 * 1. 客户端的程序类 不直接 牵扯到对象的实例化管理， 只与接口发生关联
 * 通过工厂类获取指定接口的实例化对象
 */

//example
//interface IMesage {
//  public void send();
//}
//
//class NetMessage implements  IMesage{
//  public void send(){
//    System.out.println("send");
//  }
//}
//public class Myreflect {
//  public static void main(String[] args) {
//    IMesage msg = new NetMessage();
//  }
//}
/**
 * 耦合问题， 接口为不同的层提供标准
 * 用工厂方法
 */
//interface IMesage {
//  public void send();
//}
//
//class NetMessage implements  IMesage{
//  public void send(){
//    System.out.println("send");
//  }
//}
//
//class Factory{
//  private Factory() {}; //工厂不用实例化，空私有构造
//  public static IMesage getInstance(String className){
//    if ("netmessage".equalsIgnoreCase(className)){
//      return new NetMessage();
//    }
//    return null;
//  }
//}
//
//public class Myreflect {
//  public static void main(String[] args) {
//    IMesage msg = Factory.getInstance("netmessgae");
//  }
//}

/**
 * 上面是静态工厂
 * 缺陷：
 * 一旦有新的子类， 工厂类要重写
 * 所以不能用意图判断
 * 解决：
 * 不用new, 因为new 需要明确的类， 我们得到string 要经过判断
 * 改为：
 */
//class Factory{
//  private Factory() {}; //工厂不用实例化，空私有构造
//  public static IMesage getInstance(String className){
//    try {
//            msg = (IMesage) Class.forName(className).getDeclaredConstructor().newInstance();
//
//            } catch (Exception e) {
//            e.printStackTrace();
//            } finally {
//            return msg;
//            }
//  }
//}

/**
 * 工厂类应该为多个interface 服务，而不是只为imessage服务
 * 改 泛型
 */
//class Factory{
//  private Factory() {}; //工厂不用实例化，空私有构造
//  @SuppressWarnings("unchecked")
//  public static <T> T getInstance(String className,Class<T> clazz) {
//    T msg = null;
//    try {
//      msg = (T) Class.forName(className).getDeclaredConstructor().newInstance();
//    } catch (Exception e){
//      e.printStackTrace();
//    }return msg;
//  }
//}
//IMessage msg = Factory.getInstance("netmessage", IMessage.class);


import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;

import java.awt.print.Pageable;
import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 反射与单例
 * 单例： 类内部的构造方法私有化， 在类的内部创造实例， 用static标记 实例 与 get方法
 *  lazy singleton
 * 多线程 + 单例设计  ---》 产生多个实例化对象
 * 问题：
 * 判断instance 是否为空， 为空，创造实例化
 * 多个线程同时判断为空
 * 为了不影响性能， synchronized 加的位置很重要
 *  这里还不能 用synchronized(this), 因为static 不能用来同步处理
 *  instance + volatile 不用副本 立刻与主内存中的数据对象保持同步
 *
 *  java 中哪里用到单例模式？
 *  runtime, class, spring
 *  懒汉式单例设计的问题
 */
//
//class Singleton{
//  private static Singleton instance = null;
//  private Singleton(){};
//  public static Singleton getInstance(){
//    if (instance == null){
//      synchronized (Singleton.class){
//        if(instance == null){
//          instance = new Singleton();
//        }
//      }
//    }
//  }
//}

///    ****************** 反射类的基本信息
/** class 类----> package 类
 * 获得包名称
 *
 * Class<?> cls = Person.class;
 * Package pack = cls.getPackage();
 * pack.getName()
 *
 *
 * 获取父类
 * Class<?> parent = cls.getSuperclass();
 *
 * 获取父接口
 * public Class<?>[] gerInterface()
 *
 * */



///    ****************** 反射调用构造方法
/** class 类----> java.reflect.constructor类
 * 获取所有 public Constructor<?>[] getDeclaredConstructors() thorw NoSuchMethodException;
 * 获取指定 public Constructor<?> getDeclaredConstructors(Class<?> xx) thorw NoSuchMethodException;
 * 获取所有 public Constructor<?>[] getConstructors() thorw NoSuchMethodException;
 * 获取指定 public Constructor<?> getConstructors(Class<?> xx) thorw NoSuchMethodException;
 * 没有区别
 *
 * 调用指定参数的构造方法， 一般下推荐调用无参构造
 * 用Constructor传入参数
 * Constructor extends Executable ---> Accessible 类
 *
 *
 * // 有参反射实例化
 * 方法 newInstance
 * Constructor<?> constructor = cls.getConstructor(String.class, int.class);
 * Object obj = constructor.newInstance("xX");
 */


///    ****************** 反射调用普通方法
/**
 * Method  * method extends Executable ---> Accessible 类
 * public Method[] getMethods() throws SecurtyException
 * public Method getMethods(String name, ...) throws SecurtyException, NoSuchMethodException
 * 含父类
 * public Method[] getDeclaredMethods() throws SecurtyException
 * public Method getDeclaredMethods(String name, ...) throws SecurtyException, NoSuchMethodException
 * 不含父类
 *
 * 不重要
 * Method methods[] = cls.getMethods();
 * for(Method met: methods){
 *   int mod = met.getModifiers();
 *   Modifiers.toString(mod)
 *   met.getReturnType().getName()
 *   met.getName()
 *   Class<?> params[] met.getParameterTypes();
 *   Class<?> exp [] = met.getExceptionTypes();
 * }
 *
 * 重要 Method 下 invoke 方法 调用
 * public Object invoke(Object obj, Object...args)
 * 在不导入包下， 调用
 * 1. 任何情况下， 如果想要保存类中的属性 或者 调用类中的方法，
 * 都必须要保证存在实例化对象，如果不能导入包， 就要反射实例化
 * Object obj = cls.getDeclaredConstructor().newInstace();
 * 2. 如果要调用， 必须要获得方法的名称
 * String setMethodName = "setName";
 * Method method = cls.getDeclaredMethod(setMethodName, String.class);
 * String value = "xx";
 * method.invoke(obj, value); === Person.setName(value);
 *
 */


///    ****************** 反射获取成员
/**
 * Field extends Accessible
 * public Field[] getFields() throws SecurtyException
* public Field getFields(String name, ...) throws SecurtyException, NoSuchFieldException
* 含父类
 * public Field[] getDeclaredFields() throws SecurtyException
 * public Field getDeclaredFields(String name, ...) throws SecurtyException, NoSuchFieldException
 * 不含父类
 *
 * 需要先得到实例化obj对象， 因为只有实例化后，才有field的初始化
 * public void set(Object obj, Object value);
 * public void get
 * public void setAccessible(boolean flag) 处理private field
 *
 *
 * Object obj = cls.getConstructor().newInstance();
 * Field nameField = cls.getDeclaredField("name");  field对象
 *
 * nameField.setAccessile(true); //解除封装
 *
 * nameField.set(obj,"xxxx"); -- 值
 * nameField.get(obj)   -- 值
 *
 *
 * 重要：
 * public Class<?> getType() 获取成员类型
 * nameField.getType().getSimpleName()
 *
*/


/**
 * unsafe
 * 利用反射获取对象 用C++ 而不是 jvm, 用不了JVM的内存管理机制， GC
 * sun.mics.unsafe
 */

/**  属性自动赋值  --- 反射  --- 类中必须要有无参构造
 * 传统的getter, setter 若有50个属性， 则赋值会特别麻烦
 * 所以 利用反射机制来赋值
 * 1. 属性：内容|属性：内容 。。。。。
 *
 * 框架 code ---> string， class
 * factory use class 进行实例化
 * factory use string 分类 属性名 与 属性内容
 * factory 利用属性名 找到 nameField
 * factory 利用setter 命名规范找到 setter
 * factory 调用 setter method 进行赋值
 * 返回对象
 *
 *
 *
 *
 * 2
 * 拆分factory
 * factory 用来实例化，
 * BeanUtils 用来赋值属性, 同时实现 类型转换 invoke(obj, obj)
 * StringUtils 用来讲输入string 首字母大写 或者 拼 setter
 */
//
//class ClassInstanceFactory{
//  private ClassInstanceFactory(){};
//  public static <T> T create(Class<?> clazz, String value){
//    try{
//      Object obj = clazz.getDeclaredConstructor().newInstance();
//      BeanUtils.setValue(obj, value);
//      return (T) obj;
//    }catch(Exception e){
//      e.printStackTrace();
//      return null;
//    }
//  }
//}
//
//class StringUtils{
//  public static String initcap(String str){
//    if(str == null || "".equals(str)){
//      return str;
//    }
//    if (str.length() == 1){
//      return str.toUpperCase();
//    } else {
//      return str.substring(0,1).toUpperCase() + str.substring(1);
//    }
//  }
//}
//
//class BeanUtils{
//  private BeanUtils(){};
//  public static void setValue(Object obj, String value){
//    String results[] = value.split("\\|");
//    for(int x = 0; x < results.length; x++){
//      String attval[] = results[x].split(":");
//      try{
//        Field field = obj.getClass().getDeclaredField(attval[0]);
//        Method setMethod = obj.getClass().getDeclaredMethod("set" + StringUtils.initcap(attval[0]));
//
//        Object val = BeanUtils.convertValue(field.getType().getName(),attval[1]);
//
//        setMethod.invoke(obj,attval[1] );
//      }catch(Exception e){
//
//      }
//    }
//  }
//
//  public static Object convertValue(String type, String value) throws ParseException {
//    if ("long".equals(type) || "java.lang.Long".equals(type)){
//      return Long.parseLong(value);
//    }
//    if ("int".equals(type) || "java.lang.int".equals(type)){
//      return Integer.parseInt(value);
//    }
//    if ("double".equals(type) || "java.lang.Double".equals(type)){
//      return Double.parseDouble(value);
//    }
//    if ("java.util.Date".equals(type)){
//      SimpleDateFormat sdf = null;
//      if (value.matches("\\d{4}-\\d{2}-\\d{2}")){
//        sdf = new SimpleDateFormat("yyyy-MM-dd");
//      } else if (value.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}\\d{2}")){
//        sdf = new SimpleDateFormat("yyyy-MM-dd");
//      } else{
//        return new Date();
//      }
//      return sdf.parse(value);
//    }
//    return null;
//  }
//
//}

/**
 级联关系的处理
 company.department.name
 1， 判断有无 .
 2、 split 然后 length - 1 级，
 3， 逐级调用getter 看有无 实例化
 4， 若无则 实例化
 */


/**
 * classloader
 * jvm --> classloader --> classpath --> *.class
 * 从class ---> classloader ---> path 然后我们可以加载
 * 1. 系统类的加载器
 *  public ClassLoader getClassLoader();
 *  获取 父类加载器
 *  Person.class().getClassLoader().getParent()
 *
 *  3. AppClassLoader
 *  2. PlateformClassLoader JDK 1.8 及以前 为扩展类加载器 ExtClassLoader
 *  1.8版本以前 有一个 ext目录， 把java包拷贝到这里可以直接执行
 *  不安全， 不提倡使用
 *  1. Bootstrap 系统类加载器
 *
 *
 *  自定义类加载器 所有加载器加载完后， 执行自定义类加载器
 *  extends ClassLoader
 *  好处：
 *  系统提供的都是classpath 位置的
 *  自定义 可以从 网络服务器
 *  得到字节数组  ---> 自定义类加载器
 *
 *  一般情况， 没有打成java包， 没办法从 classpath加载
 *  classloader 里面方法
 *  protect final Class<?> defineClass(className, byte[] , offset, length) throws ClassFormatError
 *
 *  当本地程序要更新时，可以把更新文件放到网络服务器端
 *  本地利用自定义加载器 加载新的类
 *
 *  若自定义的类与系统的类有同样的名字， 自定义类不会被加载
 *
 */
//
//class MyClassLoader extends ClassLoader{
//  private static final String MESSAGE_CLASS_PATH="D:m.class";  //要先编译 javac xxx.java
//  // 指定类的加载
//  public Class<?> loadData(String className) throws Exception{
//    byte[] data = this.loadClassData();
//    if(data != null){
//      return super.defineClass(className, data, 0, data.length);
//    }
//    return null;
//  }
//
//  // 通过文件进行类的加载 返回字节
//  public byte[] loadClassData() throws Exception{
//    InputStream input = null;
//    ByteArrayOutputStream bos = null;
//    byte[] data = null;
//    try{
//      bos = new ByteArrayOutputStream();
//      input = new FileInputStream(new File(MESSAGE_CLASS_PATH));
//      input.transferTo(bos);// 这个方法时1.9版本以后的
//      /**还可以写为
//       * byte[] buffer = new buffer[1024];
//       * while(len = input.read(buffer) != -1)
//       *  bos.write(buffer, 0 , len);
//       */
//      data = bos.toByteArray();
//    } catch (Exception e){
//      e.printStackTrace();
//    } finally {
//        if (input != null){
//          input.close();
//        }
//        if (bos != null){
//          bos.close();
//        }
//    }return data;
//  }
//}
//
//
//class Myreflect{
//  public static void main(String[] args) throws Exception {
//    MyClassLoader classl = new MyClassLoader();
//    Class<?> cls = classl.loadData("cn.mldn.demo.Message");
//    Object obj = cls.getDeclaredConstructor().newInstance();
//    Method method = cls.getMethod("send");
//    method.invoke(obj);
//  }
//}

/** 代理设计模式 与 反射
 *  传统代理
 *  1、基于接口
 *  2、有接口的业务代码 类
 *  3.代理， 代理对象是 业务代码类实例, 代理比业务代码多
 *  4.主类 需要知道 代理 跟 业务代码实力 耦合， 需要用工厂类解耦
 *
 *  上面是静态代理， 若 有3000 个代理类， 则要编写3000个
 */

//interface IMessage{
//  public void send();
//}
//
////业务方法
//class MesageReal implements IMessage{
//  @Override
//  public void send(){
//
//  }
//}

//代理
//class MessageProxy implements IMessage{
//  private IMessage msg;
//  public MessageProxy(IMessage msg){
//    this.msg = msg;
//  }
//  public boolean connect(){
//    return true;
//  }
//
//  public void close(){
//
//  }
//
//  @Override
//  public void send(){
//    if(this.connect()) {
//      this.msg.send();
//      this.close();
//    }
//  }
//}
//
//class Myreflect{
//  public static void main(String[] args) {
//    IMessage msg = new MessageProxy(new MesageReal());
//    msg.send();
//  }
//}

/**
 * 如何让一个代理类满足所有的 接口需求  用反射解决
 * 动态代理 1 能够接受所有 业务代码类
 * 2 返回给 客户端 接口代理对象 是个对象，类型是接口 由代理创建
 * 3 用反射获取接口 obj.getClass().getInterfaces()
 * 4 系统类 根据 接口类型 利用系统类加载器 创造了 一个 接口代理对象 给 客户端
 * 这个系统类叫 Proxy, 利用了 java.lang.reflect.InvocationHadndler interface
 * 这个 handler 是规定了代理方法的执行
 *
 *public Object invoke(Object proxy, Method method, Object[] args)
 *         throws Throwable;
 * }
 *
 * java.reflect.Proxy
 *
 * public static Object newProxyInstance(ClassLoader loader, Class<?>
 *   interfaces, InvocationHandler h)
 *   参数：
 * 1. 获取真实类的classloader
 * 2  获取真实类的接口
 *
 *
 */
//
//
//interface IMessage{
//  public void send();
//}
//
////业务方法
//class MesageReal implements IMessage{
//  @Override
//  public void send(){
//
//  }
//}
//
////代理
//class MessageProxy implements InvocationHandler {
//  private Object target;// 真实业务对象
//  public Object bind(Object target){ //绑定真实业务员对象与代理生产对象
//    this.target = target;
//    return Proxy.newProxyInstance(target.getClass().getClassLoader(),
//            target.getClass().getInterfaces(), this);
//
//  }
//
//  public boolean connect(){
//    return true;
//  }
//
//  public void close(){
//
//  }
//
//  @Override
//  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//    Object returnData = null;
//    if(this.connect()){
//      returnData = method.invoke(this.target, args);
//      this.close();
//    }
//    return returnData;
//  }
//}
//
//class Myreflect{
//  public static void main(String[] args) {
//    IMessage msg = (IMessage) new MessageProxy().bind(new MesageReal());
//    msg.send();
//  }
//}

/**
 * cglab ---- 基于类的代理设置
 */


/**
 * annotation ---  反射
 * constructor/method extends executable extends accessibleObject
 * Field extends AccessibelObject
 * java.lang.reflect.accessibelobject.getAnnotations
 *                                   .getAnnotation
 * annotation interface
 * 不同的annotation  有它自己的存在范围
 *
 * @FunctionalInterface
 * 里面有
 * @Rentention(RententionPolicy.Runtime)运行时生效
 *
 * @SuppressWarning
 * @Retention(RetentionPolicy.Source) 编译时生效
 *
 */
//// annotation
//@FunctionalInterface
//@Deprecated
//interface IMessage{
//  public void send(String msg);
//}
//
//@SuppressWarnings("serial") //无法在程序执行时获取
//class MessageImpl implements IMessage, Serializable {
//  @Override //无法在运行时获取
//  public void send(String msg){
//
//  }
//}
//
//public class Myreflect{
//  public static void main(String[] args) {
//    Annotation annotations[] = IMessage.class.getAnnotations();
//
//  }
//}


/**
 * 自定义annotation
 * 结合反射机制 实现程序的处理， 设置新的annotation
 *
 */
//@Retention(RetentionPolicy.RUNTIME)
//@interface DefaultAnnotation{
//  public String title();
//  public String url() default "www.com";
//}
//
//class Message{
//  @DefaultAnnotation(title="xx")
//  public void send(String msg){
//
//  }
//}
//
//public class Myreflect {
//  public static void main(String[] args) throws NoSuchMethodException {
//    Method method = Message.class.getMethod("send", String.class);
//    DefaultAnnotation anno = method.getAnnotation(DefaultAnnotation.class);
//    System.out.println(anno.title());//,anno.url());
//
//  }
//}

/**
 *
 *   工厂设计模式
 */

interface IMessage{
  public void send(String msg);
}
class MessageImpl implements IMessage{
  @Override
  public void send(String msg){

  }
}

class Factory{
  private Factory(){};
  public static <T> T getInstance(Class<T> clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    return (T) new MessageProxy().bind(clazz.getDeclaredConstructor().newInstance());
  }
}


class MessageProxy implements InvocationHandler{
  private Object target;
  public Object bind(Object target){
    this.target = target;
    return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
  }

  public boolean connect(){
    return true;
  }

  public void close(){

  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    try {
      if (connect()) {
        return method.invoke(this.target, args);
      } else {
        throw new Exception("error");
      }
    } finally {
      this.close();
    }
  }
}

@Retention(RetentionPolicy.RUNTIME)
@interface useMessage{
  public Class<?> clazz();
}


// service 是工厂类的封装， 客户端必须要告知封装类 运行什么类
//这里写死了， 就是 messagepl的类
// 但是我们可以用 annotation 写活这里
@useMessage(clazz=MessageImpl.class) //利用注解来控制
class MessageService{
  private IMessage message;
  //构造
  public MessageService() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    useMessage use = MessageService.class.getAnnotation(useMessage.class);
    this.message = (IMessage) Factory.getInstance(use.clazz());
  }
  public void send(String msg){
    this.message.send(msg);
  }
}

public class Myreflect {
  public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
    MessageService mser = new MessageService();
    mser.send("ss");
  }
}