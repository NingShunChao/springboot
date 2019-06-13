package com.superman.mybatisplus.entity;

    import com.baomidou.mybatisplus.annotation.TableName;
    import com.baomidou.mybatisplus.extension.activerecord.Model;
    import java.io.Serializable;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 角色权限表
    * </p>
*
* @author Ningsc
* @since 2019-05-20
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("sys_role_permission")
    public class RolePermission extends Model<RolePermission> {

    private static final long serialVersionUID = 1L;

    private Integer permissionId;

    private Integer roleId;

    private Integer id;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
