package org.ko.sigma.core;

import org.junit.Test;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class MessageFormatTests {

    @Test
    public void whenMessageFormatSuccess () {
        String message = MessageFormat
                .format("尊敬的用户您好，您的验证码{0}，没什么问题请拨打电话{1}", "123456", "123");
        System.out.println(message);
    }

    @Test
    public void whenMessageFormatArgArraySuccess () {
        String[] args = new String[]{"1", "2"};
        String message = MessageFormat
                .format("尊敬的用户您好，您的验证码{0}，没什么问题请拨打电话{1}", (Object[]) args);
        System.out.println(message);
    }

    @Test
    public void whenMessageFormatArgListSuccess () {
        List<String> args = new ArrayList<>();
        args.add("1");
        args.add("2");
        String message = MessageFormat
                .format("尊敬的用户您好，您的验证码{0}，没什么问题请拨打电话{1}", args.toArray());
        System.out.println(message);
    }
}
