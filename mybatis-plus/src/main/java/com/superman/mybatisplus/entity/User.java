package com.superman.mybatisplus.entity;

    import com.baomidou.mybatisplus.annotation.TableName;
    import com.baomidou.mybatisplus.extension.activerecord.Model;
    import java.io.Serializable;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 用户表
    * </p>
*
* @author Ningsc
* @since 2019-05-20
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("sys_user")
    public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    private Integer uid;

    private String name;

    private String password;

    private String salt;

    private String state;

    private String username;


    @Override
    protected Serializable pkVal() {
        return this.uid;
    }

}
