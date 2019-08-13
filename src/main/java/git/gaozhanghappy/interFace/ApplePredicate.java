package git.gaozhanghappy.interFace;/**
 * Created by thinkpad on 2019/8/1.
 */

import git.gaozhanghappy.pojo.Apple;

/**
 * @author gaozhang
 * @ 2019/8/1 10:11
 */

public interface ApplePredicate {
    boolean test(Apple apple);
}
