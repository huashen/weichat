package com.lhs.weichat.service;

import com.lhs.weichat.bean.AppVersion;

/**
 * AppversionService
 *
 * @author longhuashen
 * @since 17/10/17
 */
public interface AppversionService {

    /**
     * 获取新版本
     *
     * @return
     */
    AppVersion getNewestVersion();
}
