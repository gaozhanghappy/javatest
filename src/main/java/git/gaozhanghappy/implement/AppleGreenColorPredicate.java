package git.gaozhanghappy.implement;/**
 * Created by thinkpad on 2019/8/1.
 */

import git.gaozhanghappy.interFace.ApplePredicate;
import git.gaozhanghappy.pojo.Apple;
import git.gaozhanghappy.pojo.AppleProduce;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gaozhang
 * @ 2019/8/1 10:14
 */

public class AppleGreenColorPredicate implements ApplePredicate{
        @Override
       public boolean test(Apple apple) {
           return "green".equals(apple.getColor());
   }
}
