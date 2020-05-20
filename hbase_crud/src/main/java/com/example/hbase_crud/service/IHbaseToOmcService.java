package com.example.hbase_crud.service;

import java.io.Serializable;

/**
 * Hbase提供给omc的 API
 * @author ycg
 *
 */
public interface IHbaseToOmcService extends Serializable {

    /**
     * 获取指标表相关信息
     * @param jsonString
     * @return
     * @throws Exception
     */
    Serializable getTargetInfos       (String jsonString)        throws Exception;


}