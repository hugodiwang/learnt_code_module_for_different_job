package Demo;

import java.io.*;

/**
 * OutputStream/InputStream ｛abstract｝
 * Writer/Reader  {abstract}
 *流程：
 * 1， 流的操作属于资源操作， 要关闭
 * 2.  因为是抽象类， 要通过子类对父类实例化
 *
 *
 * outputsream
 * implements closeable, flushable
 * public void write(byte[] b) throws IOException
 * 只能接受字节， 输入需要用 .getBytes()
 *
 * 子类：
 * FileOutputStream
 * 构造方法：
 * （File file）
 *  只创建目录
 */
//public class MyIO{
//  public static void main(String[] args) throws IOException {
//    File file = new File("D:" + File.separator + "hello.txt");
//    if(!file.getParentFile().exists()){
//      file.getParentFile().mkdir();
//    }
//    try (OutputStream output = new FileOutputStream(file)){
//    String str = "hellp\r\n";
//    output.write(str.getBytes());
//    } catch (IOException){
//      e.printStackTrace();
//    }
//  }
//}


/**
 * InputStream
 *  implements closable
 *  int = read(byte[] b) throws IOException
 *  返回length  -1为空
 */
//public class MyIO{
//  public static void main(String[] args) throws IOException {
//    File file = new File("D:" + File.separator + "hello.txt");
//    if(!file.getParentFile().exists()){
//      file.getParentFile().mkdir();
//    }
//    try (InputStream input = new FileInputStream(file)){
//    byte[] data = new byte[1244];
//    int length = input.read(data);
//    String res = new String(data,0,length);
//    } catch (IOException e){
//      e.printStackTrace();
//    }
//  }
//}

/**
 * Writer 输出char  implements flushable, closable, appenable
 * public void write(String str) throws IOException
 * Writer out = new FileWriter(file);
 * out.write(str)
 * out.close()
 *
 * Reader implement readable, closable
 * int length = read(char[] cbuf)
 *
 *
 * OutputStreamWriter 是 writer子类
 * OutputStreamWriter(outputstream output)
 *
 * InputStreamReader 是 reader子类
 * InputStreamReader(inputstream input)
 *
 *
 * 字节流 字符流
 * writer 使用了缓冲区， 当用close方法时，强制刷新缓冲区
 * 若忘记关 close 则文件丢失.
 * 字符流 更适合处理中文
 */


/**
 * 内存操作流
 * 分为字节， 字符
 * ByteArrayOutputStream,ByteArrayinputStream
 * CharArrayWriter, CharArrayReader
 *
 * byte[] bufffer = new byte[1024];
 * len = file.read(buffer);
 * baos.write(buffer, 0, len);
 * baos.toByteArray()
 * baos.toString()
 *
 * file.transferTo(baos)
 */





/**
 * pipline
 * 发送信息线程    《------------》   接受信息线程
 * 也分字节，字符 管道流
 * PipedOutStream,  PipedInputStream
 * PipedWriter, PipedReader
 */

//class SendThread implements Runnable{
//  private PipedOutputStream output;
//  public SendThread(){
//    this.output = new PipedOutputStream();
//  }
//  @Override
//  public void run(){
//
//  }
//  public PipedOutputStream getOutput(){
//    return output;
//  }
//}
//
//class ReceiveThread implements Runnable{
//  private PipedInputStream input;
//  public ReceiveThread(){
//    this.input = new PipedInputStream();
//  }
//  @Override
//  public void run(){
//
//  }
//  public PipedInputStream getInput(){
//    return input;
//  }
//}
//
//public class MyIO{
//  public static void main(String[] args) throws IOException {
//    SendThread send = new SendThread();
//    ReceiveThread receive = new ReceiveThread();
//    send.getOutput().connect(receive.getInput());
//    new Thread(send, "send").start();
//    new Thread(receive, "receive").start();
//  }
//}


/**
 * printSteram, printWriter
 * 核心是outputStream 但是功能单一， 外部采用装设器设计模式
 * 扩展了一些额外功能的类
 * outputStream ---> filterOutputStream ----> pintStream
 * printStream(outputStream out)
 * 代理设计 围绕接口展开， 调用的方法时接口的方法
 *
 * printWriter(outputsream/ writer)
 * 且提供了格式化输出方法
 * instance.printf("xx %s", var)
 *
 *
 * system里面提供了 out/err方法输出的都是printStream
 */

/**
 *BufferedReader 字符输入流  用户数据输入
 *public String readline() throws IOException(){
 *
 *}
 *
 *
 */




