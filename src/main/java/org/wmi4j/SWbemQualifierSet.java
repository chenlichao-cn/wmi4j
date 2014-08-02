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

import org.jinterop.dcom.core.JIString;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * A collection of {@link org.wmi4j.SWbemQualifier} objects.
 * Items are added to the collection using the {@link #add(String, WMIVariant, Boolean, Boolean, Boolean, Integer) add()} method,
 * retrieved from the collection using the {@link #item(String, Integer) item()} method,
 * and removed from the collection using the {@link #remove(String, Integer) remove()} method.
 * <br />
 *
 * Created by chenlichao on 14-7-26.
 */
public class SWbemQualifierSet extends AbstractWbemSet<SWbemQualifier> {
    SWbemQualifierSet(IJIDispatch dispatch) {
        super(dispatch);
    }

    /**
     * Add an {@link org.wmi4j.SWbemQualifier} object to the SWbemQualifierSet collection.
     * If a qualifier with the same name already exists in the collection, it is replaced
     *
     * @param name Name of the new qualifier.
     * @param value Variant value of the new qualifier.
     * @param propagatesToSubclasses <strong>[Optional]</strong> If this new qualifier is propagated to subclasses. The default value is TRUE.
     * @param propagatesToInstances <strong>[Optional]</strong> If this new qualifier is propagated to instances. The default value is TRUE.
     * @param overridable <strong>[Optional]</strong> If this qualifier can be overridden when propagated. The default value is TRUE.
     * @param flags <strong>[Optional]</strong> Reserved. The default value is 0.
     * @return If successful, this method returns the new qualifier. Otherwise, a null object is returned.
     * @throws WMIException
     */
    public SWbemQualifier add(String name, WMIVariant value, Boolean propagatesToSubclasses,
                              Boolean propagatesToInstances, Boolean overridable, Integer flags) throws WMIException {
        if(isEmpty(name)) {
            throw new IllegalArgumentException("Qualifier name is empty.");
        }
        if(value == null || value.getVariant().equals(JIVariant.NULL())) {
            throw new IllegalArgumentException("Qualifier value is null.");
        }
        if(flags != null && flags != 0) {
            throw new IllegalArgumentException("Flags must be zero.");
        }
        return callMethod(SWbemQualifier.class, "Add", new JIString(name), value.getVariant(),
                (propagatesToSubclasses == null) ? JIVariant.OPTIONAL_PARAM() : propagatesToSubclasses,
                (propagatesToInstances == null) ? JIVariant.OPTIONAL_PARAM() : propagatesToInstances,
                (overridable == null) ? JIVariant.OPTIONAL_PARAM() : overridable,
                JIVariant.OPTIONAL_PARAM());
    }

    /**
     * Use default parameters for {@link #add(String, WMIVariant, Boolean, Boolean, Boolean, Integer)}
     */
    public SWbemQualifier add(String name, WMIVariant value) throws WMIException {
        return add(name, value, null, null, null, null);
    }

    /**
     * Gets a named {@link org.wmi4j.SWbemQualifier} from the collection.
     *
     * @param name Name of the qualifier to retrieve.
     * @param flags <strong>[Optional]</strong> Reserved and must be zero if specified.
     * @return If successful, the requested {@link org.wmi4j.SWbemQualifier} object is returned.
     * @throws WMIException
     */
    public SWbemQualifier item(String name, Integer flags) throws WMIException {
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
    public SWbemQualifier item(String name) throws WMIException {
        return item(name, null);
    }

    /**
     * Delete a qualifier from the collection.
     *
     * @param name Name of the qualifier to remove.
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
        remove(name, null);
    }
}
