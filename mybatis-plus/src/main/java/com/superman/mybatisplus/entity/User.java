package com.superman.mybatisplus.entity;

    import com.baomidou.mybatisplus.annotation.TableName;
    import com.baomidou.mybatisplus.annotation.IdType;
    import com.baomidou.mybatisplus.extension.activerecord.Model;
    import com.baomidou.mybatisplus.annotation.TableId;
    import com.baomidou.mybatisplus.annotation.TableField;
    import java.io.Serializable;
    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 用户表
    * </p>
*
* @author Ningsc
* @since 2019-07-16
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("sys_user")
    @ApiModel(value="User对象", description="用户表")
    public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

            @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid;

            @ApiModelProperty(value = "用户名")
        @TableField("name")
    private String name;

            @ApiModelProperty(value = "密码")
        @TableField("password")
    private String password;

            @ApiModelProperty(value = "盐值")
        @TableField("salt")
    private String salt;

            @ApiModelProperty(value = "状态")
        @TableField("state")
    private String state;

            @ApiModelProperty(value = "账号")
        @TableField("username")
    private String username;


    @Override
    protected Serializable pkVal() {
        return this.uid;
    }

}
