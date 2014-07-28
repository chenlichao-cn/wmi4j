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

import org.jinterop.dcom.impls.automation.IJIDispatch;

/**
 * todo implement
 * Created by chenlichao on 14-7-28.
 */
public class SWbemLastError extends AbstractSecurityScriptingObject {
    SWbemLastError(IJIDispatch dispatch) {
        super(dispatch);
    }

    /**
     * Returns an {@link org.wmi4j.SWbemPropertySet} object that is a collection of the properties for the current class or instance.
     * This property is read-only.
     *
     * @return An {@link org.wmi4j.SWbemPropertySet} object that is a collection of the properties for the current class or instance.
     * @throws WMIException
     */
    public SWbemPropertySet getProperties() throws WMIException {
        return getProperty(SWbemPropertySet.class, "Properties_");
    }

    /**
     * Get the variant value of the specified WMI property.
     * @param propertyName Name of the property.
     * @return The variant value of the specified WMI property.
     * @throws WMIException
     */
    public WMIVariant getPropertyByName(String propertyName) throws WMIException {
        return getProperties().item(propertyName).getValue();
    }
}
