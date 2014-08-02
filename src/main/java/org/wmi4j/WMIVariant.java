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

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.JIObjectFactory;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import java.util.Date;

/**
 * WMI Variant
 *
 * Created by chenlichao on 14-7-26.
 */
public class WMIVariant {

    private JIVariant variant;

    WMIVariant(JIVariant variant) {
        this.variant = variant;
    }

    JIVariant getVariant() {
        return variant;
    }

    public WMIVariant() {
        this.variant = JIVariant.NULL();
    }

    public WMIVariant(String value) {
        this.variant = new JIVariant(value);
    }

    public WMIVariant(int value) {
        this.variant = new JIVariant(value);
    }

    public WMIVariant(float value) {
        this.variant = new JIVariant(value);
    }

    public WMIVariant(boolean value) {
        this.variant = new JIVariant(value);
    }

    public WMIVariant(double value) {
        this.variant = new JIVariant(value);
    }

    public WMIVariant(short value) {
        this.variant = new JIVariant(value);
    }

    public WMIVariant(char value) {
        this.variant = new JIVariant(value);
    }

    public WMIVariant(Date date) {
        this.variant = new JIVariant(date);
    }

    public WMIVariant(AbstractScriptingObject wmiObject) {
        this.variant = new JIVariant(wmiObject.getDispatch());
    }

    public String getStringValue() throws WMIException {
        try {
            return variant.getObjectAsString2();
        } catch (JIException e) {
            throw new WMIException(e);
        }
    }

    public int getIntValue() throws WMIException {
        try {
            return variant.getObjectAsInt();
        } catch (JIException e) {
            throw new WMIException(e);
        }
    }

    public long getLongValue() throws WMIException {
        try {
            return variant.getObjectAsLong();
        } catch (JIException e) {
            throw new WMIException(e);
        }
    }

    public float getFloatValue() throws WMIException {
        try {
            return variant.getObjectAsFloat();
        } catch (JIException e) {
            throw new WMIException(e);
        }
    }

    public double getDoubleValue() throws WMIException {
        try {
            return variant.getObjectAsDouble();
        } catch (JIException e) {
            throw new WMIException(e);
        }
    }

    public short getShortValue() throws WMIException {
        try {
            return variant.getObjectAsShort();
        } catch (JIException e) {
            throw new WMIException(e);
        }
    }

    public boolean getBooleanValue() throws WMIException {
        try {
            return variant.getObjectAsBoolean();
        } catch (JIException e) {
            throw new WMIException(e);
        }
    }

    public char getCharValue() throws WMIException {
        try {
            return variant.getObjectAsChar();
        } catch (JIException e) {
            throw new WMIException(e);
        }
    }

    public Date getDateValue() throws WMIException {
        try {
            return variant.getObjectAsDate();
        } catch (JIException e) {
            throw new WMIException(e);
        }
    }

    public SWbemObject getSWbemObjectValue() throws WMIException {
        try {
            IJIDispatch dispatch = (IJIDispatch) JIObjectFactory.narrowObject(variant.getObjectAsComObject());
            return new SWbemObject(dispatch);
        } catch (JIException e) {
            throw new WMIException(e);
        }
    }
}
