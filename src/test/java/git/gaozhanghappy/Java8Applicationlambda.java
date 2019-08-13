package git.gaozhanghappy;


import git.gaozhanghappy.implement.AppleFancyFormatter;
import git.gaozhanghappy.implement.AppleGreenColorPredicate;
import git.gaozhanghappy.implement.AppleRedColorAndWeigtPredicate;
import git.gaozhanghappy.implement.AppleSimpleFormatter;
import git.gaozhanghappy.interFace.*;
import git.gaozhanghappy.pojo.Apple;
import git.gaozhanghappy.pojo.AppleProduce;
import git.gaozhanghappy.pojo.Color;
import git.gaozhanghappy.pojo.Letter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.nio.file.DirectoryStream;
import java.util.*;
import java.util.function.*;
import java.util.function.Predicate;

import static java.util.Comparator.comparing;


@RunWith(SpringRunner.class)
@SpringBootTest
public class Java8Applicationlambda {

    @Test
    public void contextLoads() {
    }

    public static List<Apple> filterApples(List<Apple> apples, ApplePredicate applePredicate) {
        List<Apple> appleList = new ArrayList<>();
        for (Apple apple : apples) {
            if (applePredicate.test(apple)) {
                appleList.add(apple);
            }
        }
        return appleList;
    }

    @Test
    public void apple() {
        AppleProduce appleProduce = new AppleProduce();
        List<Apple> apples = appleProduce.apples();
        List<Apple> appleList = filterApples(apples, new AppleGreenColorPredicate());
        System.out.println(appleList);
        System.out.println("-------------------");
        List<Apple> applesReds = filterApples(apples, new AppleRedColorAndWeigtPredicate());
        System.out.println(applesReds);
    }

    //实现prettyPrintApple
    @Test
    public void prettyPrintApple() {
        AppleProduce appleProduce = new AppleProduce();
        AppleMethod.prettyPrintApple(appleProduce.apples(), new AppleFancyFormatter());
        System.out.println("--------------------------------------------------");
        AppleMethod.prettyPrintApple(appleProduce.apples(), new AppleSimpleFormatter());

    }

    //使用匿名内部类实现
    @Test
    public void applePredicate() {
        AppleProduce appleProduce = new AppleProduce();
        List<Apple> apples = appleProduce.apples();
        List<Apple> appleList = filterApples(apples, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return "red".equals(apple.getColor()) && apple.getWeight() > 100L;
            }
        });
        System.out.println(appleList);
        System.out.println("--------------------------------------------------");

        //使用lambda表达式
        List<Apple> result = filterApples(apples, (Apple apple) -> "red".equals(apple.getColor()));
        System.out.println(result);
    }

    //第七次尝试，使用抽象类
    @Test
    public void abstact() {

        List<Apple> reApples = AppleMethod.filter(AppleMethod.produceApples(),
                (apple ->
                        "red".equals(apple.getColor()) &&
                                apple.getWeight() > 150L));
        System.out.println(reApples);
        //能被2整除的数据
        System.out.println("-----------------------------------------------------");
        List<Integer> listNo = Arrays.asList(12, 1, 3, 4, 6, 8);
        List<Integer> integers = AppleMethod.filter(listNo, integer -> integer % 2 == 0);
        System.out.println(integers);
    }

    //comparator排序功能
    @Test
    public void comparatorTest() {
        List<Apple> appleList = AppleMethod.produceApples();
        appleList.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return -o1.getWeight().compareTo(o2.getWeight());
            }
        });
        System.out.println(appleList);
        System.out.println("-----------------------");
        //使用lambda表达式
        appleList.sort((a, b) -> a.getWeight().compareTo(b.getWeight()));
        System.out.println(appleList);
        System.out.println("-----------------------");
        //runnable
        Thread t = new Thread(() -> System.out.println(
                "hello word"
        ));
        t.start();
    }

    //正式玩lambda表达式
    @Test
    public void lambdaTest() {
        Consumer<String> consumer = (String s) -> s.length();
        System.out.println(consumer);
        System.out.println("---------------------");
        DirectoryStream.Filter<Apple> appleFilter = (Apple a) -> a.getWeight() > 150L;
        System.out.println(appleFilter);
    }

    //方法引用
    @Test
    public void functionTest() {
        //案例1

       /* List<String> str=Arrays.asList("a","b","A","B");
        //str.sort((s1,s2)->s1.compareToIgnoreCase(s2));
        System.out.println(str);
        str.sort(String::compareToIgnoreCase);
       System.out.println(str);*/

        //案例2

       /* Function<String,Integer> stringTOInteger=(s -> Integer.parseInt(s));
        Integer apply = stringTOInteger.apply("21");
        System.out.println(apply);*/
        Function<String, Integer> stringTOInteger = Integer::parseInt;
        Integer apply = stringTOInteger.apply("21");
        System.out.println(apply);
        List<String> list = Arrays.asList("1", "2", "3");
        /*BiPredicate<List<String>,String> contains=(list,element)->list.contains(element);
        boolean test = contains.test(list, "2");*/
        BiPredicate<List<String>, String> contains = List::contains;
        boolean test = contains.test(list, "1");
        System.out.println(test);
    }

    //构造函数
    @Test
    public void gouZao() {
        Supplier<Apple> c1 = Apple::new;
        Apple apple = c1.get();
        System.out.println(apple);
        //等价于
        Supplier<Apple> c2 = () -> new Apple();
        Apple apple1 = c2.get();
        Function<String, Apple> c3 = Apple::new;
        Apple red = c3.apply("red");
        System.out.println(red);
        BiFunction<String, Long, Apple> biFunction = Apple::new;
        Apple green = biFunction.apply("green", 100L);
        System.out.println(green);
        //给一组苹果的重量
        List<Long> longList = Arrays.asList(1l, 3l, 5l);
        List<Apple> appleList = AppleMethod.map(longList, Apple::new);
        System.out.println(appleList);
    }

    @Test
    public void giveMeFruit() {
        Fruit apple = AppleMethod.giveMeFruit("apple", 20L);
        System.out.println(apple);
        Fruit orange = AppleMethod.giveMeFruit("orange", 50L);
        System.out.println(orange);
    }

    //三个参数的构造函数引用
    @Test
    public void triFunction() {
        TriFunction<Integer, Integer, Integer, Color> colorTriFunction = Color::new;
        Color apply = colorTriFunction.apply(1, 2, 3);
        System.out.println(apply);
    }

    //lambda表达式传递代码
    @Test
    public void lambdaCode() {
        List<Apple> appleList = AppleMethod.produceApples();
        //appleList.sort((a,b)->-a.getWeight().compareTo(b.getWeight()));
        appleList.add(new Apple("BLAK", 270L));
        appleList.sort(comparing(Apple::getWeight).reversed());
        System.out.println(appleList);
    }

    //谓语复合 negate ,and ,or
    @Test
    public void netgate() {
        //Predicate<Apple> notRedApple= notRedApple.negate();
        List<Apple> appleList = AppleMethod.produceApples();
        Apple apple = new Apple("black", 100L);
        appleList.add(apple);
        AppleMethod.filter(appleList, (a -> "red".equals(a.getColor())));

        //判断是否为红色苹果

        // Predicate<Apple> negate = isAppleB.negate();
        //boolean test = negate.test(apple);
        List<Apple> isNotRedApple = new ArrayList<>();
        for (Apple a : appleList) {
            //先确定为红苹果的条件
            Predicate<Apple> isRedApple = apple1 -> "red".equals(apple1.getColor());
            //然后带入红苹果的测试
            boolean test = isRedApple.negate().and(apple1 -> apple1.getWeight() > 100L).test(a);
            if (test) {
                isNotRedApple.add(a);
            }
        }
        System.out.println(isNotRedApple);
        //即是红苹果又要大于150
        Predicate<Apple> isRedAppleAndHeavy = apple1 -> "red".equals(apple1.getColor());
        Predicate<Apple> and = isRedAppleAndHeavy.and(apple1 -> apple1.getWeight() > 100L)
                .or(apple1 -> "black".equals(apple1.getColor()));
        for (Apple apple1:appleList){
           if (and.test(apple1)){
               System.out.println(apple1);
           }
        }
    }

    //函数复合
    @Test
    public void hanshu(){
        Function<Integer,Integer> function1=x->x+1;
        Function<Integer,Integer> function2=x->x*2;
        Function<Integer,Integer> function3=function1.andThen(function2);
        Function<Integer,Integer> function4=function1.compose(function2);
        Integer apply1 = function4.apply(2);//类似先乘法后加法，先里后外烦顺序计算；
        Integer apply = function3.apply(2);//先外后里，按照从左到右的顺序执行；
        System.out.println(apply1);
        System.out.println(apply);
    }
    //函数复合例子，实例在信的使用中执行
    //创建一个信的类
    @Test
    public void letter(){
        //抬头、检查、落款
        Function<String,String> addHeader= Letter::addHeader;
        Function<String,String> letter2=addHeader
                .andThen(Letter::checkSpelling)
                .andThen(Letter::addFooter);
        String hello = letter2.apply("lambda");
        System.out.println(hello);
        //抬头、落款，不做检查
        Function<String,String> addHeader2=Letter::addHeader;
        Function<String,String> addFooter=addHeader2.andThen(Letter::addFooter);
        String s = addFooter.apply("21");
        System.out.println(s);
    }


}
