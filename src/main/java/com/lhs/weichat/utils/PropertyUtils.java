package com.lhs.weichat.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * PropertyUtils
 *
 * @author longhuashen
 * @since 15/10/8
 */
@Component
@PropertySource("classpath:netty.properties")
public class PropertyUtils extends PropertyPlaceholderConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(PropertyUtils.class);

    private static Map<String, String> propertyMap;


    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        propertyMap = new HashMap<>();
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            propertyMap.put(keyStr, value);
        }
    }

    public static String getValue(String name) {
        String value = propertyMap.get(name);
        if (StringUtils.isBlank(value)) {
            return "";
        } else {
            return value;
        }
    }
}
