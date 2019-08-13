package git.gaozhanghappy.implement;/**
 * Created by thinkpad on 2019/8/2.
 */

import git.gaozhanghappy.interFace.AppleFormatter;
import git.gaozhanghappy.pojo.Apple;

/**
 * @author gaozhang
 * @ 2019/8/2 9:26
 */

public class AppleSimpleFormatter implements AppleFormatter {
    @Override
    public String accept(Apple apple) {

        return "An apple of "+ apple.getWeight()+"g";
    }
}
