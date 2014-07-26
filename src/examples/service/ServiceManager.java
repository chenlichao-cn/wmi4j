/*
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

package service;

import org.wmi4j.*;
import org.wmi4j.consts.Flags;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenlichao on 14-7-22.
 */
public class ServiceManager {

    public static void main(String[] args) {
        String server = "192.168.1.201";
        String username = "administrator";
        String password = "password";
        String namespace = "root\\cimv2";
        SWbemLocator locator = new SWbemLocator(server,username,password,namespace);

        try {
            SWbemServices services = locator.connectServer(null,null,null,null);
            List<Flags.GetFlag> flags = new ArrayList<Flags.GetFlag>();
            flags.add(Flags.GetFlag.wbemFlagUseAmendedQualifiers);
            SWbemObject object = services.get("Win32_Service.Name='AppMgmt'", null, null);

        } catch (WMIException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
