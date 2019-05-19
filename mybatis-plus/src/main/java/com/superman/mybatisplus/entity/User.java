package com.superman.mybatisplus.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;



import com.baomidou.mybatisplus.annotations.Version;

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
