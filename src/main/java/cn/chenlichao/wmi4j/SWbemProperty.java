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

package cn.chenlichao.wmi4j;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;
import cn.chenlichao.wmi4j.consts.WbemCimTypeEnum;

/**
 * Represents a single WMI property of a managed object.
 *
 * Created by chenlichao on 14-7-26.
 */
public class SWbemProperty extends AbstractScriptingObject {
    SWbemProperty(IJIDispatch dispatch) {
        super(dispatch);
    }

    /**
     * Return an integer that can be used to determine the CIM type of this property. This property is read-only.
     * @return An integer that can be used to determine the CIM type of this property
     * @throws WMIException
     */
    public WbemCimTypeEnum getCIMType() throws WMIException {
        int type = (Integer)getProperty(Integer.class, "CIMType");
        return WbemCimTypeEnum.parse(type);
    }

    /**
     * Boolean value that can be used to determine if this property has an array type. This property is read-only.
     * @return Boolean value that can be used to determine if this property has an array type.
     * @throws WMIException
     */
    public boolean isArray() throws WMIException {
        return (Boolean)getProperty(Boolean.class, "IsArray");
    }

    /**
     * Boolean value that can be used to determine if this property is local.
     * A value of FALSE indicates that this property has been inherited from another class.
     * This property is read-only.
     *
     * @return True if this property is local, false if this property is inherited from supper class;
     * @throws WMIException
     */
    public boolean isLocal() throws WMIException {
        return (Boolean)getProperty(Boolean.class, "IsLocal");
    }

    /**
     * Returns the name of this WMI property. This property is read-only.
     * @return The name of this WMI property.
     * @throws WMIException
     */
    public String getName() throws WMIException {
        return getProperty(String.class, "Name");
    }

    /**
     * Retrieves the name of the WMI class in which this property was introduced.
     * For classes with deep inheritance hierarchies, it is often desirable to know which properties were declared in which classes.
     * If the property is local (see {@link SWbemQualifier#isLocal() SWbemQualifier.isLocal()}),
     * this value is the same as the owning class. This property is read-only.
     * @return The name of the WMI class in which this property was introduced.
     * @throws WMIException
     */
    public String getOrigin() throws WMIException {
        return getProperty(String.class, "Origin");
    }

    /**
     * Returns an {@link SWbemQualifierSet} object that is a collection of qualifiers for this WMI property.
     * This property is read-only.
     * @return An {@link SWbemQualifierSet} object that is a collection of qualifiers for this WMI property.
     * @throws WMIException
     */
    public SWbemQualifierSet getQualifiers() throws WMIException {
        return getProperty(SWbemQualifierSet.class, "Qualifiers_");
    }

    /**
     * Get the variant value of the WMI property. This is the default automation property of this object.
     * @return The variant value of the WMI property
     * @throws WMIException
     */
    public WMIVariant getValue() throws WMIException {
        try {
            JIVariant jiVariant = dispatch.get("Value");
            return new WMIVariant(jiVariant);
        } catch (JIException e) {
            throw new WMIException(e);
        }
    }

    /**
     * Set the variant value of the WMI property. This is the default automation property of this object.
     * @param variant The variant value of the WMI property
     * @throws WMIException
     */
    public void setValue(WMIVariant variant) throws WMIException {
        if(variant == null) {
            putProperty("Value", JIVariant.NULL());
        }
        putProperty("Value", variant.getVariant());
    }

    /**
     * Get the variant value of the WMI property as string.
     * @return String value of the WMI property
     * @throws WMIException If the actual CIMType is not string.
     */
    public String getValueAsString() throws WMIException {
        return getProperty(String.class, "Value");
    }

    /**
     * Get the variant value of the WMI property as boolean value.
     * @return Boolean value of the WMI property
     * @throws WMIException If the actual CIMType is not Boolean.
     */
    public boolean getValueAsBoolean() throws WMIException {
        return (Boolean)getProperty(Boolean.class, "Value");
    }

    /**
     * Get the variant value of the WMI property as integer.
     * @return integer value of the WMI property
     * @throws WMIException If the actual CIMType is not integer.
     */
    public int getValueAsInteger() throws WMIException {
        return (Integer)getProperty(Integer.class, "Value");
    }

}
