package git.gaozhanghappy;/**
 * Created by thinkpad on 2019/8/14.
 */

import git.gaozhanghappy.java8stream.method.ProductMethod;
import git.gaozhanghappy.java8stream.pojo.Dish;
import git.gaozhanghappy.java8stream.pojo.Trader;
import git.gaozhanghappy.java8stream.pojo.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.joining;

/**
 * @author gaozhang
 * @ 2019/8/14 16:21
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class java8ApplicationStreamPractice {

    @Test
    public void transaction() {
        //找出2011年的所有交易并按交易额排序（从低到高）
        List<Transaction> transactions2011 = ProductMethod.productTransaction().stream()
                .filter(d -> d.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .collect(Collectors.toList());
        System.out.println(transactions2011);
        //交易员都在哪些不同的城市工作过
        final List<String> collect = ProductMethod.productTransaction().stream()
                .map(transaction -> transaction.getTrader().getAddress())
                .distinct()
                .collect(Collectors.toList());
        System.out.println(collect);
        //或者使用set不重复的特性
        final Set<String> collect1 = ProductMethod.productTransaction().stream()
                .map(transaction -> transaction.getTrader().getAddress())
                .collect(Collectors.toSet());
        System.out.println(collect1);
        //查询所有交易员来自剑桥的，并按姓名排序
        final List<Trader> cambridge = ProductMethod.productTransaction().stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getAddress().equals("Cambridge"))
                .distinct()
                .sorted(comparing(Trader::getName))
                .collect(Collectors.toList());
        System.out.println(cambridge);
        //交易员的姓名字符串，按照字母顺序排序
        final String reduce = ProductMethod.productTransaction().stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (a, b) -> a + b + " ");
        System.out.println(reduce);
        //下面是效率较高的解决方案，使用joining
        final String collect2 = ProductMethod.productTransaction().stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .collect(joining(" "));
        System.out.println(collect2);
        //有没有交易员在米兰工作的
        final boolean milan = ProductMethod.productTransaction().stream()
                .anyMatch(transaction -> transaction
                        .getTrader()
                        .getAddress()
                        .equals("Milan"));
        System.out.println(milan);
        //打印生活在剑桥的交易员的所有交易额
        ProductMethod.productTransaction().stream()
                .filter(transaction ->transaction.getTrader().getAddress().equals("Cambridge"))
                .map(Transaction::getValue)
                .sorted()
                .forEach(System.out::println);
        //找到最小的交易
        final Optional<Integer> reduce1 = ProductMethod.productTransaction().stream()
                .map(transaction -> transaction.getValue())
                .reduce(Integer::min);
        System.out.println(reduce1.get());
        //另外一个方法
        final Optional<Transaction> min = ProductMethod.productTransaction().stream()
                .min(comparing(Transaction::getValue));
        System.out.println(min.get().getValue());
    }
    //数值流
    @Test
    public void numStream(){
        final Optional<Integer> reduce = ProductMethod.productDish().stream()
                .map(Dish::getCalories)
                .reduce(Integer::sum);
        System.out.println(reduce);
         int sum = ProductMethod.productDish().stream()
                .mapToInt(Dish::getCalories)
                .sum();
        System.out.println(sum);
    }
}
