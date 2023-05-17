import java.sql.DriverManager;
import java.util.concurrent.atomic.AtomicInteger;

public class test {
    public static void main(String[] args) {
        String s = new String("hello");
        String str1 =  s+ "world";
        String str2 = str1.intern();
        String str3 = "helloworld";
        String str4 = new String("helloworld");
        System.out.println(str4 == str3);
    }

}
