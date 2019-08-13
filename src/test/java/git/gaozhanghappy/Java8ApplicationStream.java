package git.gaozhanghappy;/**
 * Created by thinkpad on 2019/8/12.
 */

import git.gaozhanghappy.java8stream.method.ProductMethod;
import git.gaozhanghappy.java8stream.pojo.Dish;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.Stream;

import static java.lang.System.*;
import static java.util.stream.Collectors.toList;

/**
 * @author gaozhang
 * @ 2019/8/12 18:29
 * 什么是流
 * 集合与流
 * 内部迭代与外部迭代
 * 中间操作与终端操作
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Java8ApplicationStream {

    @Test
    public void test() {
        out.println("第二个测试类");
    }
    //小试牛刀
    @Test
    public void test2(){
        List<Dish> menu= ProductMethod.productDish();
        List<String> filterList=new ArrayList<>();
        List<String> threeHighCaloricDishNames=menu.stream()
                .filter(d->d.getCalories()>300)
                .map(Dish::getName)
                .limit(3)
                .collect(toList());
        out.println(threeHighCaloricDishNames);
        List<Integer> pork = menu.stream()
                .filter(a -> a.equals("pork"))
                .map(Dish::getCalories)
                .collect(toList());
        out.println(pork);
    }
    //流只能遍历一遍，即消费一次
    @Test
    public void StreamOne(){
        List<String> title = Arrays.asList("java8","in","action");
        Stream<String> stream=title.stream();
        stream.forEach(out::print);
        out.println("");
        //stream.forEach(System.out::print);

    }
    //内部迭代的内容展示
    @Test
    public void inIterator(){
        List<String> names=ProductMethod.productDish().stream()
                .filter(d->{
                    out.println("filter："+ d.getName());
                    return d.getCalories()>300;
                    })
                .map(d->{
                    out.println("mapping:"+d.getName());
                    return d.getName();
                    })
                .limit(9)
                .collect(toList());
        out.println(names);
    }
    //终端操作
    @Test
    public void end(){
       /*ProductMethod.productDish().stream()  //这一步是获取流
          .forEach(System.out::print);*/

    //中间操作和终端操作
        List<Dish> menu= ProductMethod.productDish();
        List<String> filterList=new ArrayList<>();
        long count = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .map(Dish::getName)
                .limit(3)
                .count();
        System.out.println(count);
    }
    //使用流:筛选是否是素菜
    @Test
    public void useStream(){
        List<Dish> collect = ProductMethod.productDish()  //创建流之前的数据源
                .stream()                    //开始使用流
                .filter(Dish::isVegetarian)  //一系列的配置
                .collect(toList());//创建流：终结流
        System.out.println(collect.get(1));
    }
    @Test
    public void filterElement(){
        List<Integer> numbers=Arrays.asList(1,2,3,4,5,6,7,8,8,9);//源
         numbers.stream() //创建数值流
                .filter(integer -> integer % 2 == 0) //设置配置：过滤
                .distinct()                          //设置配置：去重
                .forEach(System.out::print);
    }
    //截短流limit
    @Test
    public void limitTest(){
        //这个是有序的流
        ProductMethod.productDish().stream()
                .filter(dish -> dish.getCalories()>300)
                .map(dish -> dish.getName())
                .limit(3)
                .forEach(System.out::println);
        System.out.println("----------------------------------");
        //下面设置无序流
        ProductMethod.productSet().stream()
                .filter(d->d%2==0)
                .limit(4)
                .forEach(System.out::print);
    }
    //跳过元素：skip
    @Test
    public void skipTest(){
        ProductMethod.productDish().stream()
                .filter(dish -> dish.getCalories()>300)
                .skip(4)  //跳过前面的4个，limit（2）筛选前两个
                .forEach(System.out::print);
    }
    //映射
    @Test
    public void mapping(){
        List<String> collect = ProductMethod.productDish().stream()
                .map(Dish::getName)
                .collect(toList());
        System.out.println(collect);
    }
}
