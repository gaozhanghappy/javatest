package git.gaozhanghappy.pojo;/**
 * Created by thinkpad on 2019/8/12.
 */

/**
 * @author gaozhang
 * @ 2019/8/12 14:48
 */

public class Color {
    private Integer integer;
    private Integer integer2;
    private Integer integer3;

    public Color(Integer integer, Integer integer2, Integer integer3) {
        this.integer = integer;
        this.integer2 = integer2;
        this.integer3 = integer3;
    }

    public Color() {
    }

    public Integer getInteger() {
        return integer;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    public Integer getInteger2() {
        return integer2;
    }

    public void setInteger2(Integer integer2) {
        this.integer2 = integer2;
    }

    public Integer getInteger3() {
        return integer3;
    }

    public void setInteger3(Integer integer3) {
        this.integer3 = integer3;
    }

    @Override
    public String toString() {
        return "Color{" +
                "integer=" + integer +
                ", integer2=" + integer2 +
                ", integer3=" + integer3 +
                '}';
    }
}
