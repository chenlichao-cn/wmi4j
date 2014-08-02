/*
 * Copyright 2014-2014 Chen Lichao
 *
 * Licensed to the Apache  Software Foundation (ASF) under one or more
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

package org.wmi4j;

import config.Config;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.Assert.*;

public class SWbemObjectTest {

    private static final Logger logger = LoggerFactory.getLogger(SWbemObjectTest.class);

    private static SWbemLocator locator;
    private static SWbemServices services;

    @BeforeClass
    public static void setUpClass() throws Exception {
        locator = new SWbemLocator(Config.getServer(),Config.getUsername(), Config.getPassword(), "root\\cimv2");
        services = locator.connectServer();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        locator.disconnect();
    }

    @Test
    public void testCompareTo() throws Exception {
        SWbemObject dnsCache = services.get("Win32_Service.Name='Dnscache'");
        assertFalse("Null wbemObject parameter.", dnsCache.compareTo(null));

        assertTrue("The same java object of SWbemObject.", dnsCache.compareTo(dnsCache));

        SWbemObject dnsCache2 = services.get("Win32_Service.Name='Dnscache'");
        assertTrue("Same wbemObject but not same java object.", dnsCache.compareTo(dnsCache2));

        SWbemObject appMgmt = services.get("Win32_Service.Name='AppMgmt'");
        assertFalse("Diffrent wbemObject.", dnsCache.compareTo(appMgmt));
    }

    @Test
    public void testGetObjectText() throws Exception {
        SWbemObject dnsCache = services.get("Win32_Service.Name='Dnscache'");
        String objText = dnsCache.getObjectText();
        //System.out.println(objText);
        assertNotNull(objText);
    }

    @Test
    public void testInstances() throws Exception {
        SWbemObject serviceClass = services.get("Win32_Service");
        SWbemObjectSet serviceSet = serviceClass.instances();
        assertTrue(serviceSet.getCount() > 0);

    }

    @Test
    public void testGetDerivation() throws Exception {
        SWbemObject win32Service = services.get("Win32_Service");
        List<String> result = win32Service.getDerivation();
        logger.debug("Win32_Service derivation: {} ", result.toString());
        assertNotNull(result);
        assertTrue(result.size() > 0);

        SWbemObject dnsCache = services.get("Win32_Service.Name='Dnscache'");
        List<String> instanceDerivation = dnsCache.getDerivation();
        logger.debug("Win32_Service instance derivation: {}", instanceDerivation);
        assertNotNull(instanceDerivation);
        assertTrue(instanceDerivation.size() > 0);
    }

    @Test
    public void testGetMethods() throws Exception {
        SWbemObject dnsCache = services.get("Win32_Service.Name='Dnscache'");
        SWbemMethodSet methodSet = dnsCache.getMethods();
        int methodCount = methodSet.getCount();
        logger.debug("Dnscache service method size: {}", methodCount);
        assertTrue(methodCount > 0);
    }
}