package com.giant.main;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class dynamicLoad {

    private static void dynamicLoad() throws Exception {
        String classpath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        classpath = classpath.substring(1);
        classpath = URLDecoder.decode(classpath, StandardCharsets.UTF_8.name());
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        System.out.println(classpath);
//        String[] args2 = new String[]{"-d",classpath, "C:\\Users\\15756\\Desktop\\temp\\HelloWorld.java"};
//        compiler.run(null, null, null, args2);
        StandardJavaFileManager standardFileManager = compiler.getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> iterable = standardFileManager.getJavaFileObjects("C:\\Users\\15756\\Desktop\\temp\\$HelloWorld.java");
        JavaCompiler.CompilationTask task = compiler.getTask(null, standardFileManager, null, Arrays.asList("-d", classpath), null, iterable);
        task.call();
        Class<?> aClass = Class.forName("com.giant.main.$HelloWorld");
        aClass.getConstructor().newInstance();
    }
}
