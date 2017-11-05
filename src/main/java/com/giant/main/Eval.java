package com.giant.main;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Eval {
    /*
     * 从java6版本开始，已经支持动态编译了，你可以在运行期直接编译.java文件，执行.class文件，并且能够获得相关的输入输出，
     * 甚至还能监听相关的事件。
     * java的动态编译提供了多种渠道，比如，可以动态编译一个字符串，也可以是文本文件，也可以是编译过的字节码文件(.class文件)，
     * 甚至可以是存放在数据库中的明文代码或字节码，只要是符合java规范的就都可以在运行期动态加载，其实现方式就是实现JavaFileObject
     * 接口，重写getCharContent、openInputStream、openOutputStream，或者实现JDK
     * 已经提供的两个SimpleJavaFileObject、ForwardingJavaFileObject。下面我演示一下，如何动态编译一个字符串。
     */
    /**
     * Java动态编译演示
     */
    public static void main(String[] args) throws Exception {
        // Java源代码
        String sourceStr = "public class Hello{public String sayHello(String name){return \"Main, \"+name;}}";
        // 类及文件名
        String clsName = "Hello";
        // 方法名
        String methodName = "sayHello";
        /*
         * 当前编译器：注意，如果是用的jdk1.6的版本（建议使用jdk1.7，1.7是没有任何问题的），ToolProvider.
         * getSystemJavaCompiler()拿到的对象将会为null，
         * 原因是需要加载的Tools.jar不在jdk安装目录的jre目录下，需要手动将lib目录下的该jar包拷贝到jre下去，详情请参考：
         * http://www.cnblogs.com/fangwenyu/archive/2011/10/12/2209051.html
         */
        JavaCompiler cmp = ToolProvider.getSystemJavaCompiler();
        // Java标准文件管理器
        StandardJavaFileManager fm = cmp.getStandardFileManager(null, null,
                null);
        // Java文件对象
        JavaFileObject jfo = new StringJavaObject(clsName, sourceStr);
        // 编译参数，类似于javac <options> 中的options
        List<String> optionsList = new ArrayList<String>();
        // 编译文件的存放地方，注意：此处是为Eclipse工具特设的
        optionsList.addAll(Arrays.asList("-d", "./target\\classes"));
        // 要编译的单元
        List<JavaFileObject> jfos = Collections.singletonList(jfo);
        // 设置编译环境
        JavaCompiler.CompilationTask task = cmp.getTask(null, fm, null,
                optionsList, null, jfos);
        // 编译成功
        if (task.call()) {
            // 生成对象
            Object obj = Class.forName(clsName).newInstance();
            Class<?> cls = obj.getClass();
            // 调用sayHello方法
            Method m = cls.getMethod(methodName, String.class);
            // 第一个参数是执行该方法的主调，后面若干个参数是执行该方法时传入该方法的实参
            String str = (String) m.invoke(obj, "陈驰");
            System.out.println(str);
        }
    }
}