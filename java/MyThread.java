package Demo;
// 1. extend thread, override run(main code)
// call thread with MyThread.start() instead of run
/**
 if (threadStatus != 0)
 throw new IllegalThreadStateException();
 runtime exception, multi times running will intricate the
 error

 use start0
 private native void start0();
 native jin(java native interface) depends on somme functions
 of the operating system. JVM will implement start0


 public class MyThread extends Thread {
  #construct
  public MyThread(){

  }
  @Override
  public void run(){

  }


}
 两种使用方式， 1 new MyThread().run() 2 new MyThread().start()
 .start()是调用.run()
 区别： .run() 先直接执行 mythread里面的代码，再继续执行主程序代码
 .start() 则交替执行 mythread 与 主程序代码


 题： 启动线程的三种方式
 1 implements runnable
 2 extends Thread
 3 先启动线程池 Executors.newCachedThreadPool()

 t1.join() 等别人执行完再执行t1
 Thread.yield() 此线程返回等待队列，给别的线程 执行的可能





 */

//2.  implements runnable interface
// extends only one, thus we sometiems can not use extends Thread
// thus we do not have start multi thread
// thread's contruct can be runnnable
/**
public class MyThread implements Runnable {
  //construct
  public MyThread(){

  }

  @Override
  public void run(){

  }

}

  public static void main(String[] args) {
    Thread thread = new Thread(new MyThread());
    thread.start();
  }
 */

//3 use lambda implements runnable
/**
public static void main(String[]args){
  Runnable run = () -> {
    //xxxxx

  }
  new Thread(run).start();
}

or
  new Thread(Runnable run = () -> {
    / xxxx
 }).start();

 */

//4.thread vs runnable
//class Thread implements Runnable {

// 5. this is a client design pattern
//where thread is a client in charge of jin
//where mythred is main code
//thread(mythread).start()

//consruct thread ---> save(runnable target) --->
// start ---> call target.run
// public synchronized void start() {


//6 runnable ----> resource
// thread -----> multi thread instance

//demo sale tickets
/**
class MyThreadClass implements  Runnable{
  private int ticket = 5;

  @Override
  public void run() {
    for(int i = 0; i < 100; i++){
      if(ticket > 0)
      System.out.println("sale tocket=" + this.ticket --);
    }

  }
}

class MyThread{
  public static void main(String[] args) {
    MyThreadClass mt = new MyThreadClass();
    new Thread(mt).start();
    new Thread(mt).start();
    new Thread(mt).start();
  }

}
// multi thread --> save the same target --> start -->
// the same runnable resource mt

 */

// 6 callable
// disadv: non returns

//public interface Callable<V> {
  //V call() throws Exception;

/**
 * futureTask(c) ---> callable(i)  <---- main code
 *            ---> runnableFuture(i) --->  runnable(i)/future(i)
 *
 * implements Callable<> -- > override call return xx
 * mian: new FutureTask<>(xx callable)
 * New Thread(Futuretask)
 */
/**
class MyThreadClass implements Callable<String>{
  @Override
  public String call() throws Exception{
    return "s";

  }
}

public class MyThread{
  public static void main(String[] args) {
    FutureTask<String> task = new FutureTask<>(new MyThreadClass());
    new Thread(task).start();
  }
}
*/

// difference java.lang.runnable has run() no return
// java.util.concurrence.callable has call() with return
// call throws exception
// call generate the future(interface) futuretask(class). future has api, get, isDone, cancel

//thread status 线程状态
// start() --- > ready status --->等到资源调度， 当调度成功
// 开始执行， 执行一段时间后，让出资源，进入阻塞状态 重新回归 ready status
// 执行要看操作形态的脸色


//name thread 命名
//public Thread(Runnable target, String name)
//public final void setName(String name)
//public final String getName(String name)
// 1.
// new Thread(mt, "nameA")
// static :  Thread.currentThread().getName();
// 没有命名的，最用static var 自动命名 0，1，2，3，4

//每当用java执行程序时， 就启动了一个JVM 进程， 一个进程会把耗时的
//分给多个线程， 不会影响主线程的进度。主线程负责处理整体流程。



//线程的休眠
//public static void sleep() 会产生 中断异常 InterruptException miliseconds


//线程的中断
//if(!mt.isInterrupted()) mt.interrupt();


//线程的强制执行
//一直独占资源知道线程的程序结束
//有时 主线程跟子线程交替进行， 我们可以让主线程强制执行
//mainThread = Thread.currentThread();
//Thread mt = new Thread(() -> {
// mainThread.join()
//})


//线程的礼让
//mt.yield() 每次调用只让一次

//线程的优先级
//Thread.setPriority(x) / get
// x: MIN/MAX/NORM_PRIORITY
//理论，实际上未必


/**
 * reentrantlock 与 synchronized 的区别
 * public class ReentrantLockTest extends Thread {
 *
 *     public static ReentrantLock lock = new ReentrantLock();
 *     public static int i = 0;
 *
 *     public ReentrantLockTest(String name) {
 *         super.setName(name);
 *     }
 *
 *     @Override
 *     public void run() {
 *         for (int j = 0; j < 10000000; j++) {
 *             lock.lock();
 *             try {
 *                 System.out.println(this.getName() + " " + i);
 *                 i++;
 *             } finally {
 *                 lock.unlock();
 *             }
 *         }
 *     }
 *
 *
 * 1  java中已经有了内置锁：synchronized,synchronized的特点是使用简单，一切交给JVM去处理,不需要显示释放
 * 从用法上可以看出，与synchronized相比， ReentrantLock就稍微复杂一点。因为必须在finally中进行解锁操作，如果不在 finally解锁，有可能代码出现异常锁没被释放，
 *  ReentrantLock并不是一种替代内置加锁的方法，而是作为一种可选择的高级功能。
 * 相比于synchronized，ReentrantLock在功能上更加丰富，它具有可重入、可中断、可限时、公平锁等特点。 api 巨多
 *
 *  reentrantlock inplement interface lock
 *
 *  1. 可重入（其实synchronized 也是可重入的）
 * lock.lock();
 * lock.lock();
 * try
 * {
 *     i++;
 *
 * }
 * finally
 * {
 *     lock.unlock();
 *     lock.unlock();
 * }
 *由于ReentrantLock是重入锁，所以可以反复得到相同的一把锁，它有一个与锁相关的获取计数器，如果拥有锁的某个线程再次得到锁，那么获取计数器就加1，然后锁需要被释放两次才能获得真正释放(重入锁)。
 *
 *
 * 2可中断
 * 与synchronized不同的是，ReentrantLock对中断是有响应的.synchronized一旦尝试获取锁就会一直等待直到获取到锁。
 *
 *
 * 3可限时
 * lock.tryLock(long timeout, TimeUnit unit)来实现可限时锁，参数为时间和单位。
 *
 * 4 公平锁
 * 一般意义上的锁是不公平的，不一定先来的线程能先得到锁，后来的线程就后得到锁。不公平的锁可能会产生饥饿现象。
 *
 * 公平锁的意思就是，这个锁能保证线程是先来的先得到锁。虽然公平锁不会产生饥饿现象，但是公平锁的性能会比非公平锁差很多。
 *
 */





//线程的同步与死锁
//synchronized 锁的是object, T.class() 可以是。class的object, 底层是对象 marker head上的两位 00， 01， 10， 11



//1
//public void run(){
//  synchronized(this){
        //整体性功能下降
//  }
//}

//2
//public synchronized void xx(){

//}
//过多的同步会造成死锁
// 同步只能保持数据一致，不能确保重复操作
// 把同步交给message
// 使用等待唤醒机制
//object 类
//1 public final void wait() throws InterruptedException
//2 public final void notify() 唤醒第一个
//3 public final void notifyAll() 唤醒所有的
/**
class Message{
  private String title;
  private String content;
  private boolean flag;
  // flag = true produce
  // flag = false consume
  public synchronized void set(String title, String content) throws InterruptedException {
    if(!this.flag){
      super.wait();
    }
    this.title = title;
    this.content = content;
    this.flat = false;
    super.notify(); / or notifyAll()
  }

  public synchronized String get() throws InterruptedException {
   if(this.flag){
     super.wait();;
   }
   try{
      return this.title + "" +this.content;
    } finally{
      this.flag = true;
      super.notify(); / or notifyAll()
    }
  }
}


class Producer implements Runnable{
  private Message msg;
  public Producer(Message msg){
    this.msg = msg;
  }
  @Override
  public void run() {

  }
}

class Consumer implements Runnable{
  private Message msg;
  public Consumer(Message msg){
    this.msg = msg;
  }
  @Override
  public void run() {

  }
}




public class MyThread{
  public static void main(String[] args) {
    Message msg = new Message();
    new Thread(new Producer(msg)).start();
    new Thread(new Consumer(msg)).start();
  }
}
 */

//柔性停止线程
/**
 * boolean flag = true;
 * new Thread(() -> {
 *   while(flag){
 *     main code
 *   }
 * }).start()
 * flag=false;
 *
 *
 */



//守护线程
//public final void setDaemon(boolean on)
//public final voolean isDaemin()
//当程序执行完毕了， 则守护线程关闭 eg: GC

//volatile
//正常的变量操作： 1获取变量内容副本， 2利用副本进行计算
// 3计算变量后保存到原始空间
// volatile 直接使用原始数据
// 定义在 属性上， 而synchronization 用块 用方法 可以处理
//同步问题

//生产销售电脑

class Computer{
  private static int count = 0;
  private volatile String name;
  private int price;
  public Computer(String name, int price){
    this.name = name;
    this.price = price;
    System.out.println("no" + count++ +"computer" + this.name + this.price);
  }
}

class Resource{
  private Computer com = null;
  public synchronized void generate(String name, int price) throws InterruptedException {
    if(com != null){
      super.wait();
    }
    System.out.println("generate");
    com = new Computer(name, price);
    super.notifyAll();
  }
  public synchronized void fetch() throws InterruptedException {
    if(com == null){
      super.wait();
    }
    System.out.println("fetch");
    com = null;
    super.notifyAll();
  }
}

class Producer implements Runnable{
  private Resource resource;
  private String name;
  private int price;
  public Producer(Resource resource){
    this.resource = resource;
  }

  @Override
  public void run() {
    // 生产50台
    try {
      this.resource.generate(this.name,this.price);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

class Consumer implements Runnable{
  private Resource resource;
  public Consumer(Resource resource){
    this.resource = resource;
  }

  @Override
  public void run() {
    // 生产50台
    try {
      this.resource.fetch();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}


