package org.ko.generator.helper;

import org.apache.commons.lang3.StringUtils;
import org.ko.generator.generator.Generator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;

public final class GeneratorHelper {

    private static final Logger _LOGGER = LoggerFactory.getLogger(GeneratorHelper.class);

    public static void run (Class clazz, String[] args) {
        SpringApplication.run(clazz, args).getBean(Generator.class).generator();
    }

    public static String buildDomainName(String table){
        String[] elements = StringUtils.split(table, "_");
        String domainName = "";
        if(elements.length == 1){
            domainName = StringUtils.capitalize(elements[0]);
        }else{
            for(int i = 1; i < elements.length; i++){
                domainName += StringUtils.capitalize(elements[i]);
            }
        }
        return domainName;
    }

    public static String buildVariableName(String table) {
        String[] elements = StringUtils.split(table, "_");
        String variableName;
        do {
            variableName = elements[0];
            if (elements.length == 1) break;
            variableName = elements[1];
            if (elements.length == 2) break;
            for(int i = 2; i < elements.length; i++) variableName += StringUtils.capitalize(elements[i]);
        } while (false);
        return variableName;
    }

    public static String converterPackage (String packages) {
        return packages.replace(".", "/");
    }

    public static int convertToInt(Object obj){
        int value = 0;

        try{
            value = obj != null && obj instanceof Integer ? ((Integer)obj).intValue() : Integer.valueOf(String.valueOf(obj));
        }catch(NumberFormatException  e){
            _LOGGER.trace(e.getMessage());
        }

        return value;
    }


    public static String getAbbr(String table){
        String abbr = "";

        String[] elements = table.split("_");
        if(elements.length > 1){
            for(int i = 1; i < elements.length; i++){
                abbr += elements[i].substring(0, 1);
            }
        }else{
            abbr = elements[0];
        }

        return abbr;
    }

    public static String formatPath (String... args) {
        StringBuilder path = new StringBuilder();
        for (String arg : args) {
            path.append(arg);
            path.append("/");
        }
        return path.toString();
    }

    private GeneratorHelper() {}
}
