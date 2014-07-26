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

package org.wmi4j;

import org.jinterop.dcom.core.JIString;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;
import org.wmi4j.consts.WbemCimTypeEnum;

import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * An SWbemPropertySet object is a collection of {@link org.wmi4j.SWbemProperty} objects.
 * You can add items to the collection using the {@link #add(String, org.wmi4j.consts.WbemCimTypeEnum, Boolean, Integer) add()} method,
 * retrieve items from the collection using the {@link #item(String, Integer) item()} method,
 * and remove items from the collection using the {@link #remove(String, Integer) remove()} method.
 *
 * Created by chenlichao on 14-7-26.
 */
public class SWbemPropertySet extends AbstractWbemSet<SWbemProperty> {
    SWbemPropertySet(IJIDispatch dispatch) {
        super(dispatch);
    }

    /**
     * Adds an {@link org.wmi4j.SWbemProperty} object to this collection.
     * If a property with the same name already exists in the collection, its contents are replaced with the new definition.
     *
     * <p><strong>Note: </strong> The value of the added property is NULL (unassigned) after this operation.
     * To set or change the value of a WMI property, you must set the value property of the returned {@link org.wmi4j.SWbemProperty} object.</p>
     *
     * @param name Name of the new property.
     * @param cimType An integer that represents the CIMType qualifier of the new property. See {@link org.wmi4j.consts.WbemCimTypeEnum}
     * @param isArray <strong>[Optional]</strong> Specifies whether the property is an array type. The default value for this parameter is FALSE.
     * @param flags <strong>[Optional]</strong> Reserved and must be zero if specified.
     * @return If successful, this method returns an {@link org.wmi4j.SWbemProperty} object that represents the new property.
     * Otherwise, a null object is returned.
     * @throws WMIException
     */
    public SWbemProperty add(String name, WbemCimTypeEnum cimType, Boolean isArray, Integer flags) throws WMIException {
        if(isEmpty(name)) {
            throw new IllegalArgumentException("Property name is empty.");
        }
        if(cimType == null) {
            throw new IllegalArgumentException("CIM type is empty.");
        }
        if(flags != null && flags != 0) {
            throw new IllegalArgumentException("Flags must be zero.");
        }
        return callMethod(SWbemProperty.class, "Add", new JIString(name), cimType.getValue(),
                (isArray == null) ? JIVariant.OPTIONAL_PARAM() : isArray,
                (flags == null) ? JIVariant.OPTIONAL_PARAM() : flags);
    }

    /**
     * Use default parameters for {@link #add(String, org.wmi4j.consts.WbemCimTypeEnum, Boolean, Integer)}
     */
    public SWbemProperty add(String name, WbemCimTypeEnum cimType) throws WMIException {
        return add(name, cimType, null, null);
    }

    /**
     * Gets a named {@link org.wmi4j.SWbemProperty} from the collection. This is the default method for this object.
     *
     * @param name Name of the property to retrieve.
     * @param flags <strong>[Optional]</strong> Reserved and must be zero if specified.
     * @return If successful, the requested {@link org.wmi4j.SWbemProperty} object is returned.
     * @throws WMIException
     */
    public SWbemProperty item(String name, Integer flags) throws WMIException {
        if(isEmpty(name)) {
            throw new IllegalArgumentException("Property name is empty.");
        }
        if(flags != null && flags != 0) {
            throw new IllegalArgumentException("Flags must be zero.");
        }
        return callMethod(SWbemProperty.class, "Item", new JIString(name), JIVariant.OPTIONAL_PARAM());
    }

    /**
     * Use default parameters for {@link #item(String, Integer)}
     */
    public SWbemProperty item(String name) throws WMIException {
        return item(name, null);
    }

    /**
     * Delete a property from the collection.
     *
     * @param name Name of the property to remove.
     * @param flags <strong>[Optional]</strong> Reserved and must be zero if specified.
     * @throws WMIException
     */
    public void remove(String name, Integer flags) throws WMIException {
        if(isEmpty(name)) {
            throw new IllegalArgumentException("Property name is empty.");
        }
        if(flags != null && flags != 0) {
            throw new IllegalArgumentException("Flags must be zero.");
        }
        callMethod(null, "Remove", new JIString(name), JIVariant.OPTIONAL_PARAM());
    }

    /**
     * Use default parameters for {@link #remove(String, Integer)}
     */
    public void remove(String name) throws WMIException {
        item(name, null);
    }


}
