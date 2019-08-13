package git.gaozhanghappy.pojo;/**
 * Created by thinkpad on 2019/8/12.
 */

/**
 * @author gaozhang
 * @ 2019/8/12 17:04
 */

public class Letter {
    public static String addHeader (String text){
        return "From Raoul,Mario and Alan:" +text;
    }
    public static String addFooter(String text){
        return text+" Kind regards";
    }
    public static String checkSpelling(String text){
       /* String str=null;
        if(!text.equals("lambda")){
            str="lambda";
        }
        return str.replaceAll(text, str);*/
       return text.replaceAll("labda","lambda");
    }
}
