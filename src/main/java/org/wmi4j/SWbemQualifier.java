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

import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

/**
 * You can use the properties of the SWbemQualifier object to represent a single qualifier of
 * a WMI class, instance, property, or method parameter.
 *
 * Created by chenlichao on 14-7-26.
 */
public class SWbemQualifier extends AbstractScriptingObject {
    SWbemQualifier(IJIDispatch dispatch) {
        super(dispatch);
    }

    /**
     * Get a Boolean value that indicates if this qualifier has been localized using a merge operation.
     * @return A Boolean value that indicates if this qualifier has been localized using a merge operation.
     * @throws WMIException
     */
    public boolean isAmended() throws WMIException {
        return (Boolean)getProperty(Boolean.class, "IsAmended");
    }

    /**
     * Indicates if this qualifier is local.
     * @return True if this qualifier is local, false if this qualifier is inherited from supper class;
     * @throws WMIException
     */
    public boolean isLocal() throws WMIException {
        return (Boolean)getProperty(Boolean.class, "IsLocal");
    }

    /**
     * Indicates if this qualifier can be overridden when propagated.
     * @return True if can be override, otherwise return false.
     * @throws WMIException
     */
    public boolean isOverridable() throws WMIException {
        return (Boolean)getProperty(Boolean.class, "IsOverridable");
    }

    /**
     * Set if this qualifier can be overridden when propagated.
     * @param isOverridable If can be overridden
     * @throws WMIException
     */
    public void setOverridable(boolean isOverridable) throws WMIException {
        putProperty("IsOverridable", new JIVariant(isOverridable));
    }

    /**
     * Get the name of this qualifier. This property is read-only.
     * @return the name of this qualifier.
     * @throws WMIException
     */
    public String getName() throws WMIException {
        return getProperty(String.class, "Name");
    }

    /**
     * Indicates if this qualifier can be propagated to an instance.
     * @return True if can, otherwise false.
     * @throws WMIException
     */
    public boolean getPropagatesToInstance() throws WMIException {
        return (Boolean)getProperty(Boolean.class, "PropagatesToInstance");
    }

    /**
     * Set if this qualifier can be propagated to an instance.
     * @param propagatesToInstance True if can be propagated, otherwise false
     * @throws WMIException
     */
    public void setPropagatesToInstance(boolean propagatesToInstance) throws WMIException {
        putProperty("PropagatesToInstance", new JIVariant(propagatesToInstance));
    }

    /**
     * Indicates if this qualifier can be propagated to an subclass.
     * @return True if can, otherwise false.
     * @throws WMIException
     */
    public boolean getPropagatesToSubClass() throws WMIException {
        return (Boolean)getProperty(Boolean.class, "PropagatesToSubClass");
    }

    /**
     * Get the variant value of this qualifier.
     * @return The variant value of this qualifier.
     * @throws WMIException
     */
    public WMIVariant getValue() throws WMIException {
        return getProperty(WMIVariant.class, "Value");
    }

    /**
     * Set the variant value of this qualifier.
     * @param variant the variant value of this qualifier.
     * @throws WMIException
     */
    public void setValue(WMIVariant variant) throws WMIException {
        putProperty("Value", variant.getVariant());
    }
}
