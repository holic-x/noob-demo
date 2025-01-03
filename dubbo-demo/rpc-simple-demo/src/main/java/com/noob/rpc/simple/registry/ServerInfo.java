package com.noob.rpc.simple.registry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 服务信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServerInfo implements Serializable {

    private String host;

    private int port;

}