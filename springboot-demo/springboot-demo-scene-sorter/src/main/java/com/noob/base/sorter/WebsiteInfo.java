package com.noob.base.sorter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 核查网站数据
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WebsiteInfo implements Serializable {

    // id 主键ID
    private Integer id;

    // web_key 网站key
    private String webKey;

    // web_name 网站名称
    private String webName;

    // is_private_robot 是否支持私有工位
    private String isPrivateRobot;

    // is_need_id_no 核查网站是否需要idNo字段（Y-需要；N-不需要）
    private String isNeedIdNo;

    // web_sign (核查网站属性：1-非地方核查、2-地方核查）
    private Integer webSign;

    // config_status (配置状态（是否可用）：0-禁用；1-启用）
    private Integer configStatus;

    // ---------- 其他信息 ----------

    // create_time
    private Date createTime;

    // update_time
    private Date updateTime;

}