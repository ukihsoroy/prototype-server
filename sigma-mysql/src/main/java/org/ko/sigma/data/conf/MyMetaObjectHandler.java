package org.ko.sigma.data.conf;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("create_user", "K.O", metaObject);
        this.setFieldValByName("gmt_create", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("modified_user", "K.O", metaObject);
        this.setFieldValByName("gmt_modified", new Date(), metaObject);
    }
}
