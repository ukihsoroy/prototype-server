package org.ko.framework.core.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.base.Strings;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.ko.framework.core.env.UserHolder;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

/**
 * 
 * BaseModel
 *
 * @author <A>chent</A>
 *
 */
@Getter
@Setter
@ToString
public abstract class BaseModel extends BaseObject {

    private static final long serialVersionUID = -9046521976022244224L;

    // 主键ID
    protected String id;
    // 创建时间
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    protected Timestamp createAt;
    // 更新时间
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    protected Timestamp updateAt;
    // 是否删除
    protected boolean isDelete;
    //创建人
    protected String createBy;
    //修改人
    protected String updateBy;

    public void preInsert(){
        BaseUser baseUser = UserHolder.getCurrentUser();
        if(Strings.isNullOrEmpty(id)){
            this.id = UUID.randomUUID().toString();
            this.createAt = new Timestamp(new Date().getTime());
            this.updateAt = this.createAt;
            this.isDelete = false;
            if(baseUser!=null){
                this.createBy = baseUser.getId();
                this.updateBy = this.createBy;
            }
        }
        else {
            this.updateAt = new Timestamp(new Date().getTime());
            if(baseUser!=null) {
                this.updateBy = baseUser.getId();
            }
        }
    }

}
