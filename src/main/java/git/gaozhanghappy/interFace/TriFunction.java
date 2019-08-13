package git.gaozhanghappy.interFace;

/**
 * Created by thinkpad on 2019/8/12.
 */
public interface TriFunction<T,U,V,R> {
    R apply(T t,U u,V v);
}
