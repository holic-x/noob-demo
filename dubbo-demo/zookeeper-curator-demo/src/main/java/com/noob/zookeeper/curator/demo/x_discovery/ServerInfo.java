package com.noob.zookeeper.curator.demo.x_discovery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 自定义服务信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServerInfo {
    public String host; // host
    public int port; // 端口号
    public String descr; // 描述
}