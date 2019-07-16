package com.superman.mybatis.entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "sys_role")
public class SysRoleEntity implements Serializable {
    @Id
    private Integer id;

    private String available;

    private String description;

    private String role;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return available
     */
    public String getAvailable() {
        return available;
    }

    /**
     * @param available
     */
    public void setAvailable(String available) {
        this.available = available;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role
     */
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", available=").append(available);
        sb.append(", description=").append(description);
        sb.append(", role=").append(role);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}