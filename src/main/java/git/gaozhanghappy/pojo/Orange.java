package git.gaozhanghappy.pojo;/**
 * Created by thinkpad on 2019/8/12.
 */

import git.gaozhanghappy.interFace.Fruit;

/**
 * @author gaozhang
 * @ 2019/8/12 13:24
 */
public class Orange implements Fruit {
    private String color;
    private Long weigth;

    public Orange(Long aLong) {
        this.weigth = aLong;
    }

    public Orange() {
    }

    public Orange(String color, Long weigth) {
        this.color = color;
        this.weigth = weigth;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Long getWeigth() {
        return weigth;
    }

    public void setWeigth(Long weigth) {
        this.weigth = weigth;
    }

    @Override
    public String toString() {
        return "Orange{" +
                "color='" + color + '\'' +
                ", weigth=" + weigth +
                '}';
    }

    @Override
    public Fruit Fruit() {
        return Orange::new;
    }
}
