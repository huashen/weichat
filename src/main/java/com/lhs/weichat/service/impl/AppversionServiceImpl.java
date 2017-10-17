package com.lhs.weichat.service.impl;

import com.lhs.weichat.bean.AppVersion;
import com.lhs.weichat.mapper.AppversionMapper;
import com.lhs.weichat.service.AppversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * AppversionServiceImpl
 *
 * @author longhuashen
 * @since 17/10/17
 */
@Service
public class AppversionServiceImpl implements AppversionService {

    @Autowired
    private AppversionMapper appversionMapper;

    @Override
    public AppVersion getNewestVersion() {
        return appversionMapper.getNewestVersion();
    }
}
