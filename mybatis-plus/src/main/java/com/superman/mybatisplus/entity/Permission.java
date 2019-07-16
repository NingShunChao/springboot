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
    * 权限表
    * </p>
*
* @author Ningsc
* @since 2019-07-16
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("sys_permission")
    @ApiModel(value="Permission对象", description="权限表")
    public class Permission extends Model<Permission> {

    private static final long serialVersionUID = 1L;

            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

        @TableField("name")
    private String name;

        @TableField("permission")
    private String permission;

        @TableField("url")
    private String url;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
