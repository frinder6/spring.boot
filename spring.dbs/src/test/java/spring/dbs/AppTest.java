package spring.dbs;

import spring.util.MethodUtil;

/**
 * Unit test for simple App.
 */
public class AppTest {

    public void test(String name, String sex, int age) {

    }

    public static void main(String[] args) {
        Class clazz = AppTest.class;
        try {
            MethodUtil.getMethodArgName(clazz.getName(), "test");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
