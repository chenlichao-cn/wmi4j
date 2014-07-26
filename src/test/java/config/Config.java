package config;/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Configuration for junit tests.
 * Created by chenlichao on 14-7-26.
 */
public class Config {

    private static final Logger logger = LoggerFactory.getLogger(Config.class);

    private static final Config instance = new Config();

    private Properties prop = new Properties();
    private Config() {
        logger.debug("Load test config.");
        InputStream in = Config.class.getClassLoader().getResourceAsStream("TestConfig.properties");
        try {
            prop.load(in);
        } catch (IOException e) {
            logger.error("Load test config failed.", e);
        }
    }

    public static String getServer() {
        return instance.prop.getProperty("host.server");
    }

    public static String getUsername() {
        return instance.prop.getProperty("host.username");
    }

    public static String getPassword() {
        return instance.prop.getProperty("host.password");
    }
}
