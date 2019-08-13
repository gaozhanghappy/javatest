package git.gaozhanghappy.interFace;

/**
 * Created by thinkpad on 2019/8/2.
 */
public interface Predicate<T> {
    boolean test(T t);
}
