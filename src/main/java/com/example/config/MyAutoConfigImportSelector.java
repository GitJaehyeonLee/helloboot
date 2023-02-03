package com.example.config;

import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

public class MyAutoConfigImportSelector implements DeferredImportSelector {

    private final ClassLoader classLoader;

    public MyAutoConfigImportSelector(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        List<String> autoConfigs = new ArrayList<>();

        // Type 1
//        for(String candidate :  ImportCandidates.load(MyAutoConfiguration.class, classLoader)) {
//            autoConfigs.add(candidate);
//        }
        // Type 2
//        ImportCandidates.load(MyAutoConfiguration.class, classLoader).forEach(candidate ->
//                autoConfigs.add(candidate)
//        );
        // Type 3
        ImportCandidates.load(MyAutoConfiguration.class, classLoader).forEach(autoConfigs::add);
        System.out.println(autoConfigs);
        return autoConfigs.toArray(new String[0]);
//        return new String[]{"com.example.config.autoconfig.TomcatWebServerConfig", "com.example.config.autoconfig.DispatcherServletConfig"};
    }
}
