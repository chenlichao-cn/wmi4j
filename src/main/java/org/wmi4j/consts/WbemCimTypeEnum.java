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
 * The WbemCimtypeEnum constants define the valid CIM types of a property value.
 * Created by chenlichao on 14-7-26.
 */
public enum WbemCimTypeEnum {
    /** 2(0x2): Signed 16-bit integer */
    Sint16(0x2),
    /** 3(0x3): Signed 32-bit integer */
    Sint32(0x3),
    /** 4(0x4): 32-bit real number */
    Real32(0x4),
    /** 5(0x5): 64-bit real number */
    Real64(0x5),
    /** 8(0x8): String */
    String(0x8),
    /** 11(0xB): Boolean value*/
    Boolean(0xB),
    /** 13(0xD): CIM object */
    Object(0xD),
    /** 16(0x10): Signed 8-bit integer */
    Sint8(0x10),
    /** 17(0x11): Unsigned 8-bit integer */
    Uint8(0x11),
    /** 18(0x12): Unsigned 16-bit integer */
    Uint16(0x12),
    /** 19(0x13): Unsigned 32-bit integer */
    Uint32(0x13),
    /** 20(0x14): Signed 64-bit integer */
    Sint64(0x14),
    /** 21(0x15): Unsigned 64-bit integer */
    Uint64(0x15),
    /** 101(0x65): Date/time value */
    Datetime(0x65),
    /** 102(0x66): Reference to a CIM object */
    Reference(0x66),
    /** 103(0x67): 16-bit character */
    Char16(0x67);

    final private int value;
    WbemCimTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        WbemCimTypeEnum.valueOf("");
        return value;
    }

    public static WbemCimTypeEnum parse(int value) {
        for(WbemCimTypeEnum e : WbemCimTypeEnum.values()) {
            if(e.getValue() == value) {
                return e;
            }
        }
        throw new IllegalArgumentException("WbemCimTypeEnum has no constant with the specified value");
    }
}
