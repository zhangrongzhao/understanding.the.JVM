package com.zrz.chapter09;

import java.lang.reflect.Method;

/***
 * Javaclass执行工具
 *
 * **/
public class JavaclassExecuter {

    /**
     * 执行外部传过来的代表一个Java类的Byte数组
     * 将输入类的byte数组中代表java.lang.system的CONSTANT_utf8_info修改为挟持后的HackSystem类
     * 执行方法为该类的static main(String[] args)方法，输入结果为该类的System.out/err输出的信息
     * @Param classByte代表一个java类的数组
     * @return 执行结果
     * ***/
    public static String execute(byte[] classByte){
         HackSystem.clearBuffer();
         ClassModifier cm=new ClassModifier(classByte);
         byte[] modiBytes=cm.modifyUTF8Constant("java/lang/system","com/zrz/chaper09/HackSystem");
         HotSwapClassLoader loader=new HotSwapClassLoader();
         Class clazz = loader.loadByte(modiBytes);
         try{
             Method method=clazz.getMethod("main",new Class[]{String[].class});
             method.invoke(null,new String[]{null});
         }catch(Throwable e){
             e.printStackTrace(HackSystem.out);
         }
         return HackSystem.getBufferString();
    }
}
