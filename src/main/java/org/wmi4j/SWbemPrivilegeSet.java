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
import org.wmi4j.consts.WbemPrivilegeEnum;

/**
 * An SWbemPrivilegeSet object is a collection of {@link org.wmi4j.SWbemPrivilege} objects in an
 * {@link org.wmi4j.SWbemSecurity} object that requests specific privileges for a Windows Management Instrumentation (WMI) object.
 * See the list of privileges in Privilege Constants. Items are added to the collection using the Add and AddAsString methods. Items are retrieved from the collection using the Item method, and removed using the Remove method. This object cannot be created by the VBScript CreateObject method call. For more information, see Accessing a Collection.

 An SWbemPrivilegeSet object is a set of privilege override requests for a specific object. When an API call is made using this object, the privilege override requests are attempted. The SWbemPrivilegeSet object does not define the privileges available to the current user or process. In other words, obtaining the privileges for any WMI object does not identify the privilege settings that are made on the connection to WMI, or the privileges in effect when an object is delivered to a sink.
 * Created by chenlichao on 14-7-28.
 */
public class SWbemPrivilegeSet extends AbstractWbemSet<SWbemPrivilege> {
    SWbemPrivilegeSet(IJIDispatch dispatch) {
        super(dispatch);
    }

    /**
     * Adds an {@link org.wmi4j.SWbemPrivilege} object to the collection.
     * If a privilege with the same name already exists in the collection, it is replaced.
     *
     * @param privilege Privilege for a WMI object.
     * @param enabled <strong>[Optional]</strong> Enables or disables this privilege. The default value is TRUE.
     * @return If successful, returns an {@link org.wmi4j.SWbemPrivilege} object that represents the new privilege.
     * Otherwise, a null object is returned.
     * @throws WMIException
     */
    public SWbemPrivilege add(WbemPrivilegeEnum privilege, Boolean enabled) throws WMIException {
        return callMethod(SWbemPrivilege.class, "Add", privilege.getValue(),
                enabled == null ? JIVariant.OPTIONAL_PARAM() : enabled);
    }


    /**
     * You can use the addAsString method to add a privilege to the collection using a privilege string.
     * Use this method to add a privilege or to enable a privilege for {@link org.wmi4j.SWbemSecurity} objects.
     *
     * @param privilege String privilege for a WMI object.
     * @param enabled <strong>[Optional]</strong> Enables or disables this privilege. The default value is TRUE.
     * @return If successful, returns an {@link org.wmi4j.SWbemPrivilege} object that represents the new privilege.
     * Otherwise, a null object is returned.
     * @throws WMIException
     */
    public SWbemPrivilege addAsString(WbemPrivilegeEnum privilege, Boolean enabled) throws WMIException {
        return callMethod(SWbemPrivilege.class, "AddAsString", new JIString(privilege.getStrValue()),
                enabled == null ? JIVariant.OPTIONAL_PARAM() : enabled);
    }

    /**
     * Removes all privileges from the collection, thus emptying it.
     * @throws WMIException
     */
    public void deleteAll() throws WMIException {
        callMethod(null, "DeleteAll");
    }

    /**
     * Get the specified {@link org.wmi4j.SWbemPrivilege} object from the collection.
     * @param privilege Privilege enum.
     * @return the specified {@link org.wmi4j.SWbemPrivilege} object
     * @throws WMIException
     */
    public SWbemPrivilege item(WbemPrivilegeEnum privilege) throws WMIException {
        return callMethod(SWbemPrivilege.class, "Item", privilege.getValue());
    }

    /**
     * Delete the specified {@link org.wmi4j.SWbemPrivilege} object from the collection.
     * @param privilege Privilege enum.
     * @throws WMIException
     */
    public void remove(WbemPrivilegeEnum privilege) throws WMIException {
        callMethod(null, "Remove", privilege.getValue());
    }
}
