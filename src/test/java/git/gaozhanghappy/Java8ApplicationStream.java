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
    public void test2() {
        List<Dish> menu = ProductMethod.productDish();
        List<String> filterList = new ArrayList<>();
        List<String> threeHighCaloricDishNames = menu.stream()
                .filter(d -> d.getCalories() > 300)
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
    public void StreamOne() {
        List<String> title = Arrays.asList("java8", "in", "action");
        Stream<String> stream = title.stream();
        stream.forEach(out::print);
        out.println("");
        //stream.forEach(System.out::print);

    }

    //内部迭代的内容展示
    @Test
    public void inIterator() {
        List<String> names = ProductMethod.productDish().stream()
                .filter(d -> {
                    out.println("filter：" + d.getName());
                    return d.getCalories() > 300;
                })
                .map(d -> {
                    out.println("mapping:" + d.getName());
                    return d.getName();
                })
                .limit(9)
                .collect(toList());
        out.println(names);
    }

    //终端操作
    @Test
    public void end() {
       /*ProductMethod.productDish().stream()  //这一步是获取流
          .forEach(System.out::print);*/

        //中间操作和终端操作
        List<Dish> menu = ProductMethod.productDish();
        List<String> filterList = new ArrayList<>();
        long count = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .map(Dish::getName)
                .limit(3)
                .count();
        System.out.println(count);
    }

    //使用流:筛选是否是素菜
    @Test
    public void useStream() {
        List<Dish> collect = ProductMethod.productDish()  //创建流之前的数据源
                .stream()                    //开始使用流
                .filter(Dish::isVegetarian)  //一系列的配置
                .collect(toList());//创建流：终结流
        System.out.println(collect.get(1));
    }

    @Test
    public void filterElement() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 8, 9);//源
        numbers.stream() //创建数值流
                .filter(integer -> integer % 2 == 0) //设置配置：过滤
                .distinct()                          //设置配置：去重
                .forEach(System.out::print);
    }

    //截短流limit
    @Test
    public void limitTest() {
        //这个是有序的流
        ProductMethod.productDish().stream()
                .filter(dish -> dish.getCalories() > 300)
                .map(dish -> dish.getName())
                .limit(3)
                .forEach(System.out::println);
        System.out.println("----------------------------------");
        //下面设置无序流
        ProductMethod.productSet().stream()
                .filter(d -> d % 2 == 0)
                .limit(4)
                .forEach(System.out::print);
    }

    //跳过元素：skip
    @Test
    public void skipTest() {
        ProductMethod.productDish().stream()
                .filter(dish -> dish.getCalories() > 300)
                .skip(4)  //跳过前面的4个，limit（2）筛选前两个
                .forEach(System.out::print);
    }

    //映射
    @Test
    public void mapping() {
        List<String> collect = ProductMethod.productDish().stream()
                .map(Dish::getName)
                .collect(toList());
        System.out.println(collect);

        ProductMethod.productDish().stream()
                .map(Dish::getName)
                .map(String::length)
                .forEach(System.out::println);
    }

    //流的扁平化
    @Test
    public void flagStream() {
        String[] strings = {"GoodBye", "World", "World"};
        //Stream<String> stream = Arrays.stream(strings);//将数组流转成字符流
        Arrays.stream(strings)
                .map(word -> {
                    System.out.println(word);
                    return word.split("");
                }) //将每个单词转换为由其字母构成的数组
                //让每个数组变成单独的流,将两个单词映射成两个流对象
                .distinct() //将流去重
                .forEach(w ->   //操作两个流
                        Arrays.stream(w)
                                .distinct() //将其中每个流数据进行去重操作
                                .map(a -> a.toString()) //打印出来
                );

      /*以上得到的还是一个流，我们最终得到的是想是一个去重的字母数组*/
        List<String> collect = Arrays.stream(strings)
                .map(w -> {
                    System.out.println(w);
                    return w.split("");
                })
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());
        System.out.println(collect);
    }

    //给定一个一组数，返回这些数的平方
    @Test
    public void square() {
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4);
        List<Integer> collect = integerList.stream()
                .map(d -> d * d)
                .collect(toList());
        System.out.println(collect);
    }

    //思考题
    //1,2,3和3,4进行组合：1,3；2,3；3,3和1,4；2,4；3,4
    @Test
    public void number() {
        List<Integer> num1 = Arrays.asList(1, 2, 3);
        List<Integer> num2 = Arrays.asList(3, 4);
        List<int[]> collect = num1.stream()
                .flatMap(i -> num2.stream().map(j -> new int[]{i, j}))
                .collect(toList());
        List<String> collect1 = collect.stream().map(d -> Arrays.toString(d)).collect(toList());
        System.out.println("---------------------------------");
        //返回只能被3整除的数对
        List<int[]> collect2 = num1.stream()
                .flatMap(i -> num2.stream()
                        .filter(j -> (i + j) % 3 == 0)
                        .map(d -> new int[]{i, d})
                )
                .collect(toList());
        List<String> collect3 = collect2.stream().map(d -> Arrays.toString(d)).collect(toList());
        System.out.println(collect3);

    }

    //查找和匹配
    @Test
    public void matte() {
        //至少匹配一个元素
        if (ProductMethod.productDish().stream().anyMatch(Dish::isVegetarian)) {
            ProductMethod.productDish().stream().filter(Dish::isVegetarian).forEach(System.out::println);
        }
        //匹配所有元素
        boolean b = ProductMethod.productDish()
                .stream().allMatch(d -> d.getCalories() < 1000);
        System.out.println(b);
        //没有任何元素与给定的谓词匹配
        final boolean b1 = ProductMethod.productDish().stream()
                .noneMatch(dish -> dish.getCalories() >= 1000);
        System.out.println(b1);
    }

    //查找元素
    @Test
    public void findAny() {
        final Optional<Dish> any = ProductMethod.productDish().stream()
                .filter(Dish::isVegetarian)
                .findAny();
        System.out.println(any);// findAny这里返回了复合条件的第一个数据，
                                // 只要找到结果就返回，不管后面是否存在其他符合条件的数据

        any.ifPresent(d-> System.out.println(d));
    }
    //归约
    @Test
    public void reduced(){
        List<Integer> integerList=Arrays.asList(1,2,3,4,5);
        //所有数值之和
        final Integer reduce = integerList.stream().reduce(0, (a, b) -> a + b);
        System.out.println(reduce);
        //
        final Integer reduce1 = integerList.stream().reduce(0, Integer::sum);
        System.out.println(reduce);
        final Optional<Integer> reduce2 = integerList.stream().reduce((a, b) -> a + b);
        System.out.println(reduce2.get());
        System.out.println("--------------------------------");
        //计算最大数和最小数
        final Optional<Integer> reduce3 = integerList.stream().reduce(Integer::max);
        System.out.println(reduce3.get());
        //统计数目
        final Optional<Integer> reduce4 = ProductMethod.productDish().stream()
                .map(d -> 1)
                .reduce(Integer::sum);
        final long count = ProductMethod.productDish().stream().count();
        System.out.println("count:"+count);
        System.out.println(reduce4.get());


    }
    @Test
    public void max(){

    }
}
