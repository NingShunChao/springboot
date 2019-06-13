package com.superman.mybatisplus.entity;

    import com.baomidou.mybatisplus.annotation.TableName;
    import com.baomidou.mybatisplus.extension.activerecord.Model;
    import java.io.Serializable;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 角色表
    * </p>
*
* @author Ningsc
* @since 2019-05-20
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("sys_role")
    public class Role extends Model<Role> {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String available;

    private String description;

    private String role;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
