package com.noob.framework.model;



import com.noob.framework.validator.IdentityCardNumber;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

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
public class UserAddDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    // 用户名
    @NotNull(message = "用户名不能为空")
    @Length(min = 6,max = 20,message = "用户名设定在6-20个字符")
    @Pattern(regexp = "^[\\u4E00-\\u9FA5A-Za-z0-9\\*]*$", message = "用户昵称限制：最多20字符，包含文字、字母和数字")
    private String username;

    // 用户密码
    @NotNull(message = "用户密码不能为空")
    private String password;

    // 邮箱
    @NotNull(message = "邮箱不能为空")
    @Email(message = "邮箱格式错误")
    private String email;

    // 联系方式
    @NotNull(message = "联系方式不能为空")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误")
    private String phone;

    @NotNull(message = "创建时间不能为空")
    @Future(message = "时间必须是将来时间")
    private Date createTime;

    @NotNull(message = "idCard不能为空")
    @IdentityCardNumber(message = "idCard校验失败")
    private String idCard;

}
