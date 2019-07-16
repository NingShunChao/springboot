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
    * 角色表
    * </p>
*
* @author Ningsc
* @since 2019-07-16
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("sys_role")
    @ApiModel(value="Role对象", description="角色表")
    public class Role extends Model<Role> {

    private static final long serialVersionUID = 1L;

            @TableId("id")
    private Integer id;

        @TableField("available")
    private String available;

        @TableField("description")
    private String description;

        @TableField("role")
    private String role;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
