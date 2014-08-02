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

import org.jinterop.dcom.impls.automation.IJIDispatch;

/**
 * You can use the properties of the SWbemMethod object to inspect a single method definition of a WMI object.
 * <p>This object can be used to inspect the definitions of methods.
 * To invoke the methods, you should use either direct access on an {@link org.wmi4j.SWbemObject} (which is the recommended mechanism) object,
 * or the {@link org.wmi4j.SWbemServices#execMethod(String, String, SWbemObject, Integer, SWbemNamedValueSet) SWbemServices.execMethod()} call. </p>
 *
 * <p><strong>Note:</strong>  In this version of the API, write access to method information is not supported.
 * If you want to define methods or modify existing method definitions,
 * you can define the method changes in a MOF file and submit the changes using the MOF Compiler.
 * Alternatively, use the COM API for WMI.</p>
 *
 * Created by chenlichao on 14-7-26.
 */
public class SWbemMethod extends AbstractScriptingObject {
    SWbemMethod(IJIDispatch dispatch) {
        super(dispatch);
    }

    /**
     * Get an {@link org.wmi4j.SWbemObject} object whose properties define the input parameters for this method.
     * This property is read-only. Note that any changes that are made to this object are not reflected in the underlying method definition.
     *
     * @return An {@link org.wmi4j.SWbemObject} object whose properties define the input parameters for this method.
     * @throws WMIException
     */
    public SWbemObject getInParameters() throws WMIException {
        return getProperty(SWbemObject.class, "InParameters");
    }

    /**
     * Return a string that describes the name of this method. This property is read-only.
     * @return A string that describes the name of this method.
     * @throws WMIException
     */
    public String getName() throws WMIException {
        return getProperty(String.class, "Name");
    }

    /**
     * Retrieves the name of the WMI class in which this method was introduced.
     * For classes with deep inheritance hierarchies, it is often desirable to know which methods were declared in which classes.
     * If the method is local (see {@link SWbemQualifier#isLocal()}),
     * this value is the same as the owning class. This property is read-only.
     * @return The name of the WMI class in which this method was introduced.
     * @throws WMIException
     */
    public String getOrigin() throws WMIException {
        return getProperty(String.class, "Origin");
    }

    /**
     * Get an SWbemObject object whose properties define the out parameters and return type of this method. This property is read-only.
     * @return An SWbemObject object whose properties define the out parameters and return type of this method.
     * @throws WMIException
     */
    public SWbemObject getOutParameters() throws WMIException {
        return getProperty(SWbemObject.class, "OutParameters");
    }

    /**
     * Returns an {@link org.wmi4j.SWbemQualifierSet} object that is a collection of qualifiers for this WMI method.
     * This property is read-only.
     * @return An {@link org.wmi4j.SWbemQualifierSet} object that is a collection of qualifiers for this WMI method.
     * @throws WMIException
     */
    public SWbemQualifierSet getQualifiers() throws WMIException {
        return getProperty(SWbemQualifierSet.class, "Qualifiers_");
    }
}
