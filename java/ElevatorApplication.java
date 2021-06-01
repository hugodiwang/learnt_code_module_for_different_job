package Demo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

enum Direction{
  UP,DOWN;
}


// 这个是策略类的核心  这个地方可以加annotatioN 来取消 set/handle两个函数的耦合
class ElevatorSystem{
  private HandleRequestStrategy strategy;
  private List<Elevator> elevators = new ArrayList<>();

  public void setStrategy(HandleRequestStrategy strategy){
   this.strategy = strategy;
  }
  public void handleRequest(ExternalRequest request) {
    this.strategy.handleRequest(request, elevators);
  }
}


interface HandleRequestStrategy{

  public void handleRequest(ExternalRequest request, List<Elevator> elevators);
}


class RandomHandleRequestStrategy implements HandleRequestStrategy{

  public void handleRequest(ExternalRequest request, List<Elevator> elevators){
    Random rand = new Random();
    int n = rand.nextInt(elevators.size());
    elevators.get(n).handleExternalRequest(request);
  }
}

class Elevator{
  public void handleExternalRequest(ExternalRequest request){

  }

}

class ExternalRequest{
  private Direction d;
  private int l;
  public ExternalRequest(Direction d, int l){
    this.d = d;
    this.l = l;
  }

}

//为什么用工厂模式
//1. 客户端的程序类 不直接 牵扯到对象的实例化管理， 只与接口发生关联
class Factory2{
  private Factory2() {}; //工厂不用实例化，空私有构造
  @SuppressWarnings("unchecked")
  public static <T> T getInstance(String className, Class<T> clazz) throws ClassNotFoundException {
    T strategy = null;
    try {
      strategy = (T) Class.forName(className).getDeclaredConstructor().newInstance();// 为多个接口服务， 改泛型
    }catch(Exception e){
      e.printStackTrace();
    }
    return strategy;
  }
}

//@Retention(RetentionPolicy.RUNTIME)
//@interface StrategyKind{
//  public Class<?> clazz();
//  public String name();
//}
//
//@StrategyKind(clazz=RandomHandleRequestStrategy.class, name="RandomHandleRequestStrategy")
//class StrategyService{
//  private HandleRequestStrategy strategy = null;
//  public StrategyService() throws ClassNotFoundException {
//    StrategyKind kind = StrategyService.class.getAnnotation(StrategyKind.class);
//    HandleRequestStrategy strategy  = (HandleRequestStrategy) Factory2.getInstance(kind.name(), kind.clazz()); // 牵扯到了
//  }
//  public void handRequest(ExternalRequest request){
//    this.strategy.handleRequest(request);
//  }
//
//}

public class ElevatorApplication {
  public static void main(String[] args) throws ClassNotFoundException {
    ElevatorSystem mysystem = new ElevatorSystem();
    HandleRequestStrategy strategy = Factory2.getInstance("RandomHandleRequestStrategy", RandomHandleRequestStrategy.class); // 牵扯到了对象的实例化
    mysystem.setStrategy(strategy);
    ExternalRequest request = new ExternalRequest(Direction.UP, 3);
    mysystem.handleRequest(request);
  }

}
