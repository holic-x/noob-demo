package com.noob.base.batchHandle.entity.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * T_LIMIT 数据表
 * @TableName t_limit
 */
@TableName(value ="t_limit")
@Data
public class TLimit implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * key1
     */
    @TableField(value = "key1")
    private String key1;

    /**
     * key2
     */
    @TableField(value = "key2")
    private Integer key2;

    /**
     * key3
     */
    @TableField(value = "key3")
    private String key3;

    /**
     * keyPart1
     */
    @TableField(value = "key_part1")
    private String keyPart1;

    /**
     * keyPart2
     */
    @TableField(value = "key_part2")
    private String keyPart2;

    /**
     * keyPart3
     */
    @TableField(value = "key_part3")
    private String keyPart3;

    /**
     * commonField
     */
    @TableField(value = "common_field")
    private String commonField;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TLimit other = (TLimit) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getKey1() == null ? other.getKey1() == null : this.getKey1().equals(other.getKey1()))
            && (this.getKey2() == null ? other.getKey2() == null : this.getKey2().equals(other.getKey2()))
            && (this.getKey3() == null ? other.getKey3() == null : this.getKey3().equals(other.getKey3()))
            && (this.getKeyPart1() == null ? other.getKeyPart1() == null : this.getKeyPart1().equals(other.getKeyPart1()))
            && (this.getKeyPart2() == null ? other.getKeyPart2() == null : this.getKeyPart2().equals(other.getKeyPart2()))
            && (this.getKeyPart3() == null ? other.getKeyPart3() == null : this.getKeyPart3().equals(other.getKeyPart3()))
            && (this.getCommonField() == null ? other.getCommonField() == null : this.getCommonField().equals(other.getCommonField()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getKey1() == null) ? 0 : getKey1().hashCode());
        result = prime * result + ((getKey2() == null) ? 0 : getKey2().hashCode());
        result = prime * result + ((getKey3() == null) ? 0 : getKey3().hashCode());
        result = prime * result + ((getKeyPart1() == null) ? 0 : getKeyPart1().hashCode());
        result = prime * result + ((getKeyPart2() == null) ? 0 : getKeyPart2().hashCode());
        result = prime * result + ((getKeyPart3() == null) ? 0 : getKeyPart3().hashCode());
        result = prime * result + ((getCommonField() == null) ? 0 : getCommonField().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", key1=").append(key1);
        sb.append(", key2=").append(key2);
        sb.append(", key3=").append(key3);
        sb.append(", keyPart1=").append(keyPart1);
        sb.append(", keyPart2=").append(keyPart2);
        sb.append(", keyPart3=").append(keyPart3);
        sb.append(", commonField=").append(commonField);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}