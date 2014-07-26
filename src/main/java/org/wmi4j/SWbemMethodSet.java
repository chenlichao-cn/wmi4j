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

import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * An SWbemMethodSet object is a collection of {@link org.wmi4j.SWbemMethod} objects.
 * Items are retrieved using the {@link #item(String, Integer)} method.
 *
 * <p><strong>Note:</strong>  In this version of the API, write access to method information is not supported.
 * If you want to define methods or modify existing method definitions,
 * you can define the method changes in a MOF file and submit the changes using the MOF Compiler.
 * Alternatively, use the WMI COM API.</p>
 *
 * Created by chenlichao on 14-7-26.
 */
public class SWbemMethodSet extends AbstractWbemSet<SWbemMethod> {
    SWbemMethodSet(IJIDispatch dispatch) {
        super(dispatch);
    }

    /**
     * Returns a named {@link org.wmi4j.SWbemMethod} object from the collection.
     * @param methodName  Name of the method to retrieve.
     * @param flags <strong>[Optional]</strong> Reserved and must be zero if specified.
     * @return A named {@link org.wmi4j.SWbemMethod} object from the collection.
     * @throws WMIException
     */
    public SWbemMethod item(String methodName, Integer flags) throws WMIException {
        if(isEmpty(methodName)) {
            throw new IllegalArgumentException("Method name is empty.");
        }
        if(flags != null && flags != 0) {
            throw new IllegalArgumentException("Flags must be zero.");
        }
        return callMethod(SWbemMethod.class, "Item", JIVariant.OPTIONAL_PARAM());
    }

    /**
     * Use default parameters for {@link #item(String, Integer)}
     */
    public SWbemMethod item(String methodName) throws WMIException {
        return item(methodName, null);
    }
}
