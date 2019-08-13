package git.gaozhanghappy.pojo;/**
 * Created by thinkpad on 2019/8/1.
 */

import java.util.Arrays;
import java.util.List;

/**
 * @author gaozhang
 * @ 2019/8/1 10:22
 */

public class AppleProduce {

    public List<Apple> apples (){
        List<Apple> apples= Arrays.asList(
                new Apple("red",100L),
                new Apple("red",180L),
                new Apple("green",180L),
                new Apple("green",80L));
        return apples;
    }
}
