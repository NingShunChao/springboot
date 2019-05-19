package com.superman.mybatisplus.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;



import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户角色表
 * </p>
 *
 * @author Ningsc
 * @since 2019-05-20
 */
@Data
@Accessors(chain = true)
@TableName("sys_user_role")
public class UserRole extends Model<UserRole> {

    private static final long serialVersionUID = 1L;

    @TableField("role_id")
    private Integer roleId;

    private Integer uid;

    private Integer id;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
