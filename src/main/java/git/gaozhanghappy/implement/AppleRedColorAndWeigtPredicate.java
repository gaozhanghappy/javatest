package git.gaozhanghappy.implement;/**
 * Created by thinkpad on 2019/8/1.
 */

import git.gaozhanghappy.interFace.ApplePredicate;
import git.gaozhanghappy.pojo.Apple;

/**
 * @author gaozhang
 * @ 2019/8/1 11:14
 */

public class AppleRedColorAndWeigtPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {

        return "red".equals(apple.getColor())&& apple.getWeight()<150L;
    }
}
