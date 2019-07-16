package com.superman.mybatisplus.entity;

    import com.baomidou.mybatisplus.annotation.TableName;
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
    * 用户角色表
    * </p>
*
* @author Ningsc
* @since 2019-07-16
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("sys_user_role")
    @ApiModel(value="UserRole对象", description="用户角色表")
    public class UserRole extends Model<UserRole> {

    private static final long serialVersionUID = 1L;

        @TableField("role_id")
    private Integer roleId;

        @TableField("uid")
    private Integer uid;

            @TableId("id")
    private Integer id;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
