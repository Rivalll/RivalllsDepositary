package redisson.lock;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;

import java.util.concurrent.TimeUnit;

/**
 * RedLock ,
 * Redis based distributed reentrant Lock object for Java and implements java.util.concurrent.locks.Lock interface.
 * */
public class RedissonRLock {

    private static Config config = new Config();
    private static RedissonClient redissonClient;
    public static void init(){
//        config.setTransportMode(TransportMode.EPOLL);
        config.setLockWatchdogTimeout(1000);
        config.useReplicatedServers()
                .addNodeAddress("redis://127.0.0.1:6379");
        redissonClient = Redisson.create(config);
    }
    public static void main(String[] args){
        init();
//        lock();
        multiThreadsLock();
    }

    private static void multiThreadsLock(){
        for(int i = 0 ; i < 5; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    lock();
//                    test();
                }
            }).start();
        }
    }

    private static void test(){
        System.out.println("test");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void lock(){
        org.redisson.api.RLock rLock = redissonClient.getLock("lock");

        rLock.lock();
        long threadId = Thread.currentThread().getId();
        try {
            System.out.println(threadId+"加锁");
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println(threadId+"解锁");
            rLock.unlock();
        }
    }

}
