package Demo;

import java.util.*;


class addService implements Runnable{
  FriendshipService app = FriendshipService.getInstance();


  @Override
  public void run() {
    for(int i = 0; i < 10; i++){
      app.follow(i,i+1);
    }

    //super.notifyAll();
//    System.out.println(app.followersMap + Thread.currentThread().getName());
//    System.out.println(app.followingsMap + Thread.currentThread().getName());
//    System.out.println(app);
  }

}

public class FriendshipServiceAPP {
  public static void main(String[] args) throws InterruptedException {

    addService s = new addService();
    new Thread(s).start();
    new Thread(s).start();


  }

}


class FriendshipService{
  public Map<Integer, List<Integer>> followersMap;
  public Map<Integer, List<Integer>> followingsMap;
  private static volatile FriendshipService friSerApp;
  private FriendshipService(){
    this.followersMap = new HashMap<>();
    this.followingsMap  = new HashMap<>();
  }
  public static FriendshipService getInstance(){
    if (friSerApp == null){
      synchronized (FriendshipService.class){
        if(friSerApp == null){
          friSerApp = new FriendshipService();
        }
      }

    }
    return friSerApp;
  }

  public synchronized  void  follow(int host, int follower){
    List<Integer> followerList = followersMap.getOrDefault(host, new ArrayList<>());
    if(!followerList.contains(follower)){
      followerList.add(follower);
      followersMap.put(host, followerList);
    }


    List<Integer> followingList = followingsMap.getOrDefault(follower, new ArrayList<>());
    if(!followingList.contains(host)){
      followingList.add(host);
      followingsMap.put(follower, followingList);
    }
  }




  public synchronized void unfollow(int host, int follower){
    if(followersMap.containsKey(host)){
      List<Integer> followerList = followersMap.get(host);
      if(followerList.contains(follower)) {
        followerList.remove(followerList.indexOf(follower));
        followersMap.put(host, followerList);
      }else{
        System.out.println("Host" +  host + "do not have this follower" + follower);
      }
    } else{
      System.out.println("NonHostFound Error");
    }

    if(followingsMap.containsKey(follower)){
      List<Integer> followingList = followingsMap.get(follower);
      if (followingList.contains(host)){
        followingList.remove(followingList.indexOf(host));
        followingsMap.put(follower, followingList);
      } else{
        System.out.println("Follower" +  follower + "do not have this host" + host);
      }
    } else{
      System.out.println("NonFollowingFound Error");
    }

  }

  public List<Integer> getFollowers(int host){
    return followersMap.getOrDefault(host, new ArrayList<>());
  }

  public List<Integer> getFollowings(int follower){
    return followingsMap.getOrDefault(follower, new ArrayList<>());
  }

}


