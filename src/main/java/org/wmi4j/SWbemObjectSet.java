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

import org.apache.commons.lang3.StringUtils;
import org.jinterop.dcom.core.JIString;
import org.jinterop.dcom.impls.automation.IJIDispatch;

/**
 * An SWbemObjectSet object is a collection of {@link SWbemObject} objects.
 *
 * You can get an SWbemObjectSet object by calling any of the following methods or their asynchronous equivalents:
 *
 * <ul>
 *     <li>SWbemObject.Associators_</li> TODO link
 *     <li>SWbemObject.Instances_</li> TODO link
 *     <li>SWbemObject.References_</li> TODO link
 *     <li>SWbemObject.Subclasses_</li> TODO link
 *     <li>{@link org.wmi4j.SWbemServices#associatorsOf(String, String, String, String, String, Boolean, Boolean, String, String, java.util.List, SWbemNamedValueSet) SWbemService.associatorsOf}</li>
 *     <li>{@linkplain org.wmi4j.SWbemServices#execQuery(String, String, java.util.List, SWbemNamedValueSet) SWbemServices.execQuery}</li>
 *     <li>{@linkplain org.wmi4j.SWbemServices#instancesOf(String, java.util.List, SWbemNamedValueSet) SWbemServices.instancesOf}</li>
 *     <li>{@link org.wmi4j.SWbemServices#referencesTo(String, String, String, Boolean, Boolean, String, java.util.List, SWbemNamedValueSet) SWbemServices.referencesTo}</li>
 *     <li>{@link org.wmi4j.SWbemServices#subclassesOf(String, java.util.List, SWbemNamedValueSet) SWbemServices.subclassesOf}</li>
 * </ul>
 *
 * <p><strong>Note:</strong>  The SWbemObjectSet object does not support the optional Add and Remove collection methods.</p>
 * <p><strong>Note:</strong>  Because the call-back to the sink might not be returned at the same authentication level as the client requires, it is recommended that you use semisynchronous instead of asynchronous communication. For more information, see Calling a Method.</p>
 *
 * Created by chenlichao on 14-7-22.
 */
public class SWbemObjectSet extends AbstractWbemSet {
    SWbemObjectSet(IJIDispatch dispatch) {
        super(dispatch);
    }

    /**
     * The Item method of the SWbemObjectSet object gets an {@link SWbemObject} from the collection.
     * @param objectPath Relative path of the object to retrieve from the collection. Example: Win32_LogicalDisk="C:"
     * @return If successful, the requested {@link SWbemObject} object returns.
     * @throws WMIException
     * @exception  IllegalArgumentException Object path is empty.
     */
    public SWbemObject item(String objectPath) throws WMIException {
        if(StringUtils.isEmpty(objectPath)) {
            throw new IllegalArgumentException("Object path is empty.");
        }
        return callMethod(SWbemObject.class, "Item", new JIString(objectPath));
    }

    /**
     * The itemIndex method returns the {@link SWbemObject} associated with the specified index into the collection.
     * The index indicates the position of the element within the collection. Collection numbering starts at zero.
     * <p><strong>Important: This method only supported by Windows Server 2008 or higher versions.</strong></p>
     * @param index Index of the item in the collection.
     * @return If successful, the requested {@link SWbemObject} object returns.
     * @throws WMIException
     */
    public SWbemObject itemIndex(int index) throws WMIException {
        if(index < 0) {
            throw new IllegalArgumentException("Index starts with zero.");
        }
        return callMethod(SWbemObject.class, "ItemIndex", index);
    }
}
