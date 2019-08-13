package git.gaozhanghappy.test;/**
 * Created by thinkpad on 2019/8/2.
 */

/**
 * @author gaozhang
 * @ 2019/8/2 10:47
 */

public class MeaningOfThis {
    public final  int value=4;
    public void doIf(){
        int value=6;
        Runnable r=new Runnable() {
            public final int value=5;
            @Override
            public void run() {
                int value=10;
                System.out.println(this.value);
            }
        };
        r.run();
    }

    public static void main(String[] args) {
        MeaningOfThis meaningOfThis=new MeaningOfThis();
        meaningOfThis.doIf();
    }
}
