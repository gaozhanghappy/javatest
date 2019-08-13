package git.gaozhanghappy.interFace;/**
 * Created by thinkpad on 2019/8/2.
 */

import git.gaozhanghappy.pojo.Apple;
import git.gaozhanghappy.pojo.Orange;

import java.security.PublicKey;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;

/**
 * @author gaozhang
 * @ 2019/8/2 9:28
 */

public abstract class AppleMethod {
    //生产苹果的方法
    public static List<Apple> produceApples(){
        List<Apple> appleList=new ArrayList<>();
        for (int i=1;i<=30;i++){
            if (i%2==0){
                Apple apple=new Apple("red",Long.parseLong(Integer.toString(i*10)));
                appleList.add(apple);
            }else {
                Apple apple=new Apple("green",Long.parseLong(Integer.toString(i*10)));
                appleList.add(apple);
            }
        }
        return appleList;
    }

    //把行为抽象出来，这个是第四尝试
    public static void prettyPrintApple(List<Apple> inventpry,AppleFormatter appleFormatter){
        for (Apple apple:inventpry){
            String output=appleFormatter.accept(apple);
            System.out.println(output);
        }
    }
    public static <T> List<T> filter(List<T> list,Predicate<T> predicate){
        List<T> result=new ArrayList<>();
        for (T t:list){
            if (predicate.test(t)){
                result.add(t);
            }
        }
        return result;
    }
   public static List<Apple> map(List<Long> longList, Function<Long,Apple> longAppleFunction) {
        List<Apple> appleList=new ArrayList<>();
        for (Long longl:longList){
            appleList.add(longAppleFunction.apply(longl));
        }
        return  appleList;
    }

    //得到不同类型和重量的水果
    private static Map<String , Function<Long,Fruit>> mapFruit=new HashMap<>();
    static {
        mapFruit.put("apple",Apple::new);
        mapFruit.put("orange", Orange::new);
    }
    public static Fruit giveMeFruit(String fruit,Long weigth){
        return mapFruit.get(fruit.toLowerCase()).apply(weigth);
    }

}
