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
    * 角色权限表
    * </p>
*
* @author Ningsc
* @since 2019-07-16
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("sys_role_permission")
    @ApiModel(value="RolePermission对象", description="角色权限表")
    public class RolePermission extends Model<RolePermission> {

    private static final long serialVersionUID = 1L;

        @TableField("permission_id")
    private Integer permissionId;

        @TableField("role_id")
    private Integer roleId;

            @TableId("id")
    private Integer id;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
