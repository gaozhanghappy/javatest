package git.gaozhanghappy.implement;/**
 * Created by thinkpad on 2019/8/2.
 */

import git.gaozhanghappy.interFace.AppleFormatter;
import git.gaozhanghappy.pojo.Apple;

/**
 * @author gaozhang
 * @ 2019/8/2 9:22
 */

public class AppleFancyFormatter implements AppleFormatter {
    @Override
    public String accept(Apple apple) {
        String charcteristic=apple.getWeight()>150L ? "heavy" : "light";
        return "A "+ charcteristic + " "+ apple.getColor()+" apple";
    }
}
