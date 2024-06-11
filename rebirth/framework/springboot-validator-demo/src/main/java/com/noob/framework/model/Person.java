package com.noob.framework.model;



import com.noob.framework.group.Create;
import com.noob.framework.group.Update;
import com.noob.framework.validator.IdentityCardNumber;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.GroupSequence;
import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户添加实体类定义
 */
@Data
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    // 用户ID
    @NotNull(message = "用户ID不能为空", groups = {Update.class})
    private Integer id;

    // 用户名
    @NotNull(message = "用户名不能为空")
    @Length(min = 6,max = 20,message = "用户名设定在6-20个字符", groups = {Create.class, Update.class})
    private String username;

    @NotNull(message = "创建时间不能为空")// 走默认分组
    @Future(message = "时间必须是将来时间", groups = {Create.class})
    private Date createTime;

}
