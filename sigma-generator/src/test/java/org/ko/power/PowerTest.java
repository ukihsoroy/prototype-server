package org.ko.power;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.ko.generator.helper.GeneratorHelper;

import java.util.Objects;

public class PowerTest {

    @Test
    public void test1 () {
        String packages = "org.ko.data";
        String r = converterPackage(packages);
        System.out.println(r);
    }

    @Test
    public void test2 () {
        String name = "name.java.ftl";
        String[] ary = StringUtils.split(name, ".");
        assert ary.length == 3;
    }

    @Test
    public void test3 () {
        System.out.println(formatPath("a", "b", "c"));
    }

    @Test
    public void test4 () {
        String name = GeneratorHelper.buildDomainName("t_user_info");
        System.out.println(name);
    }


    protected String converterPackage (String packages) {
        return packages.replace(".", "/");
    }


    private String formatPath (String... args) {
        StringBuilder path = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            path.append(args[i]);
            if (i != args.length - 1) path.append("/");
        }
        return path.toString();
    }



}
