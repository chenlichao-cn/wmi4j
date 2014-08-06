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

import cn.chenlichao.wmi4j.SWbemLocator;
import cn.chenlichao.wmi4j.SWbemServices;
import config.Config;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SWbemLocatorTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private SWbemLocator locator;

    @Before
    public void setUp() throws Exception {
        locator = new SWbemLocator(Config.getServer(), Config.getUsername(), Config.getPassword(), "root\\cimv2");
    }

    @After
    public void tearDown() throws Exception {
        locator.disconnect();
    }

    @org.junit.Test
    public void test003_ConnectServer() throws Exception {
        SWbemServices services = locator.connectServer();
        assertNotNull(services);
    }

    @org.junit.Test
    public void test001_GetSWbemServices_nonConnect() throws Exception {
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("Please connect to the server first.");
        SWbemServices services = locator.getSWbemServices();
        assertNull(services);
    }

    @Test
    public void test004_GetSWbemServices() throws Exception {
        locator.connectServer();
        assertNotNull(locator.getSWbemServices());
    }

    @org.junit.Test
    public void test006_Disconnect() throws Exception {
        locator.connectServer();
        locator.disconnect();
        assertFalse(locator.isConnected());
    }

    @Test
    public void test002_Disconnect_neverConnect() throws Exception {
        locator.disconnect();
    }

    @org.junit.Test
    public void test005_IsConnected() throws Exception {
        assertFalse(locator.isConnected());
        locator.connectServer();
        assertTrue(locator.isConnected());
    }
}