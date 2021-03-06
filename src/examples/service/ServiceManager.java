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

package service;

import cn.chenlichao.wmi4j.SWbemLocator;
import cn.chenlichao.wmi4j.SWbemObject;
import cn.chenlichao.wmi4j.SWbemServices;
import cn.chenlichao.wmi4j.WMIException;

import java.net.UnknownHostException;

public class ServiceManager {

    public static void main(String[] args) {
        String server = "192.168.1.201";
        String username = "administrator";
        String password = "password";
        String namespace = "root\\cimv2";
        SWbemLocator locator = new SWbemLocator(server,username,password,namespace);

        try {
            SWbemServices services = locator.connectServer();
            SWbemObject object = services.get("Win32_Service.Name='AppMgmt'");
            //print AppMgmt properties
            System.out.println(object.getObjectText());

            //print AppMgmt service state
            System.out.println(object.getPropertyByName("State").getStringValue());

            //Stop AppMgmt service
            object.execMethod("Stop");
        } catch (WMIException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
