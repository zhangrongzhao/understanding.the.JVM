package com.zrz.chapter08;


import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;

public class MethodInvokeTest {

     class GrandFather{
        void thinking(){
            System.out.println("i am grandfather");
        }
    }

     class Father extends GrandFather{
        void thinking(){
            System.out.println("i am father");
        }
    }

     class Son extends Father{
        void thinking(){
            try{
                MethodType methodType = MethodType.methodType(void.class);
                Field  lookupImpl= MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
                lookupImpl.setAccessible(true);
                //MethodHandle methodHandle = java.lang.invoke.MethodHandles.lookup().findSpecial(GrandFather.class,"thinking",methodType,getClass());
                MethodHandle methodHandle=((MethodHandles.Lookup)lookupImpl.get(null)).findSpecial(GrandFather.class,"thinking",methodType,getClass());
                methodHandle.invoke(this);
            }catch(NoSuchMethodException ex){

            }catch(IllegalAccessException ex){

            }catch(Exception ex){

            }catch(Throwable ex){

            }
        }
    }

    public static void main(String[] args){
        ((new MethodInvokeTest()).new Son()).thinking();
    }
}
