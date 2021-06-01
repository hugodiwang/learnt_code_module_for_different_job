package Demo;

/**
 * 对象在 heap 里， 内存里， ---> serialize ---> disk
 * 不是所有的对象 都能序列化， 类必须要implement Java.io.Serializable interface
 * 这个接口没有任何方法， 描述的时能力、标记
 * java 中有两个 这样的 serializable/ clonable
 * 在不同的vm 上为了防止版本问题， 加序列化编号
 * 如果没有这个问题可以 压制
 * SuppressWarnings("serial")
 *
 *
 * ObjectOutputSteram 序列化
 * extends outputsteram
 * 构造 ObjectOutputSteram(outputSteram out) 用户自定义位置 同printStream
 * void writeObject(Object obj)
 *
 *
 * ObjectInputSream 反序列化
 * extends outputsteram
 * 构造 ObjectInputSream(inputStream input)
 * Object readObject()
 *
 * 很少自己实现， 我们只用implement serializable
 * 会有容器帮助我们实现 objectInputSteram,objectInputStream
 *
 * 若有一些属性 不想序列化 在这个属性上加transient 关键字
 * public transient String  name;
 * 这样保存的时默认值
 *
 */








public class MySeralization {
}
