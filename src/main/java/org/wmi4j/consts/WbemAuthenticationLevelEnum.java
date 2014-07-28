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
 * Define the security authentication levels. These constants are used with {@link org.wmi4j.SWbemSecurity} and in moniker connections to WMI.
 *
 * Created by chenlichao on 14-7-28.
 */
public enum WbemAuthenticationLevelEnum {

    /** 0(0x0): WMI uses the default Windows Authentication setting. */
    Default(0x0),
    /** 1(0x1): Uses no authentication. */
    None(0x1),
    /** 2(0x2): Authenticates the credentials of the client only when the client establishes a relationship with the server. */
    Connect(0x2),
    /** 3(0x3): Authenticates only at the beginning of each call when the server receives the request. */
    Call(0x3),
    /** 4(0x4): Authenticates that all data received is from the expected client. */
    Pkt(0x4),
    /** 5(0x5): Authenticates and verifies that none of the data transferred between client and server has been modified. */
    PktIntegrity(0x5),
    /** 6(0x6): Authenticates all previous impersonation levels and encrypts the argument value of each remote procedure call. */
    PktPrivacy(0x6);

    final private int value;
    WbemAuthenticationLevelEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static WbemAuthenticationLevelEnum parse(int value) {
        for(WbemAuthenticationLevelEnum e : WbemAuthenticationLevelEnum.values()) {
            if(e.getValue() == value) {
                return e;
            }
        }
        throw new IllegalArgumentException("WbemCimTypeEnum has no constant with the specified value");
    }
}
