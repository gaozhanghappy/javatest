package git.gaozhanghappy.implement;/**
 * Created by thinkpad on 2019/8/1.
 */

import git.gaozhanghappy.interFace.ApplePredicate;
import git.gaozhanghappy.pojo.Apple;

/**
 * @author gaozhang
 * @ 2019/8/1 10:13
 */

public class AppleHeavyWeightPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return apple.getWeight()>150L;
    }
}
