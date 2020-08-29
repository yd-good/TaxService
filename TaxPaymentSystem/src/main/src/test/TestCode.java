/*
信息:
*/
package test;

import org.junit.Test;

import java.lang.reflect.Type;

public class TestCode {

    @Test
    public void test(){
       Type genericSuperclass = Class.class.getGenericSuperclass();
   }
}
