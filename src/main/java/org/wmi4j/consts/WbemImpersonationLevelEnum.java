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

package org.wmi4j.consts;

/**
 * The WbemImpersonationLevelEnum constants define the security impersonation levels.
 * These constants are used with {@link org.wmi4j.SWbemSecurity}.
 *
 * Created by chenlichao on 14-7-28.
 */
public enum WbemImpersonationLevelEnum {

    /** 1(0x1): Hides the credentials of the caller. Calls to WMI may fail with this impersonation level. */
    Anonymous(0x1),
    /** 2(0x2): Allows objects to query the credentials of the caller.
     * Calls to WMI may fail with this impersonation level. */
    Identify(0x2),
    /** 3(0x3): Allows objects to use the credentials of the caller.
     * This is the recommended impersonation level for Scripting API for WMI calls. */
    Impersonate(0x3),
    /** 4(0x4): Allows objects to permit other objects to use the credentials of the caller.
     * This impersonation will work with Scripting API for WMI calls but may constitute an unnecessary security risk. */
    Delegate(0x4);


    final private int value;
    WbemImpersonationLevelEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static WbemImpersonationLevelEnum parse(int value) {
        for(WbemImpersonationLevelEnum e : WbemImpersonationLevelEnum.values()) {
            if(e.getValue() == value) {
                return e;
            }
        }
        throw new IllegalArgumentException("WbemCimTypeEnum has no constant with the specified value");
    }
}
