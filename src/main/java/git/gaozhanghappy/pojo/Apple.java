package git.gaozhanghappy.pojo;/**
 * Created by thinkpad on 2019/8/1.
 */

import git.gaozhanghappy.interFace.Fruit;

import java.util.function.Predicate;

/**
 * @author gaozhang
 * @ 2019/8/1 10:09
 */

public class Apple implements Fruit {
    private String color;
    private Long weight;

    public Apple(String color, Long weight) {
        this.color = color;
        this.weight = weight;
    }

    public Apple() {

    }

    public Apple(long integer) {
        this.weight = integer;
    }

    public Apple(String s) {
        this.color = s;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }

    @Override
    public Fruit Fruit() {
        return Apple::new;
    }


}
