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

import cn.chenlichao.wmi4j.*;
import cn.chenlichao.wmi4j.consts.WbemCimTypeEnum;

import java.net.UnknownHostException;

public class ProcessManager {

    private SWbemLocator locator;

    private SWbemServices wbemServices;

    public ProcessManager() throws UnknownHostException, WMIException {
        locator = new SWbemLocator("192.168.1.201","administrator","password","root\\cimv2");

        wbemServices = locator.connectServer();
    }

    void startNotepad() throws WMIException {
        SWbemObject startConfig = wbemServices.get("Win32_ProcessStartup").spawnInstance();
        startConfig.getProperties().item("ShowWindow").setValue(new WMIVariant(1));
        startConfig.getProperties().add("PriorityClass", WbemCimTypeEnum.Uint32).setValue(new WMIVariant(128));
        //System.out.println(startConfig.getObjectText());
        //System.out.println(startConfig.getPath().getRelPath());
        //startConfig.put();

        SWbemObject process = wbemServices.get("Win32_Process");

        SWbemObject inParams = process.getMethods().item("Create").getInParameters().spawnInstance();
        inParams.getProperties().item("CommandLine").setValue(new WMIVariant("notepad.exe"));
        inParams.getProperties().item("CurrentDirectory").setValue(new WMIVariant("C://"));
        inParams.getProperties().item("ProcessStartupInformation").setValue(new WMIVariant(startConfig));

        System.out.print(inParams.getObjectText());

        SWbemObject result = process.execMethod("Create", inParams);

        System.out.println("ReturnValue: " + result.getPropertyByName("ReturnValue").getIntValue());
        System.out.println("ProcessID: " + result.getPropertyByName("ProcessID").getIntValue());

    }

    void printProcessInfo(int processId) throws WMIException {
        SWbemObject notepadProcess = wbemServices.get("Win32_Process.Handle=\"" + processId + "\"");
        System.out.println(notepadProcess.getObjectText());
    }

    public static void main(String[] args) throws Exception {

        ProcessManager manager = new ProcessManager();

        //manager.printProcessInfo(132);
        manager.startNotepad();
    }


}
