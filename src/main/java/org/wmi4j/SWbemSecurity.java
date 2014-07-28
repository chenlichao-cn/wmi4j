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
import org.wmi4j.consts.WbemAuthenticationLevelEnum;
import org.wmi4j.consts.WbemImpersonationLevelEnum;

/**
 * The SWbemSecurity object gets or sets security settings, such as privileges, COM impersonations, and authentication levels
 * assigned to an object. The {@link org.wmi4j.SWbemLocator}, {@link org.wmi4j.SWbemServices}, {@link org.wmi4j.SWbemObject},
 * {@link org.wmi4j.SWbemObjectSet}, {@link org.wmi4j.SWbemObjectPath}, {@link SWbemLastError},
 * and {@link org.wmi4j.SWbemEventSource} objects have a security property, which is the SWbemSecurity object.
 * When you retrieve an instance or view the WMI security log, you might need to set the properties of the SWbemSecurity object.
 *
 * Created by chenlichao on 14-7-23.
 */
public class SWbemSecurity extends AbstractScriptingObject{

    SWbemSecurity(IJIDispatch dispatch) {
        super(dispatch);
    }

    /**
     * Get the COM Authentication level that is assigned to this object.
     * This setting determines how you protect information sent from WMI.
     *
     * @return The COM Authentication level that is assigned to this object.
     * @throws WMIException
     */
    public WbemAuthenticationLevelEnum getAuthenticationLevel() throws WMIException {
        int value = (Integer)getProperty(Integer.class, "AuthenticationLevel");
        return WbemAuthenticationLevelEnum.parse(value);
    }

    /**
     * Set the COM Authentication level that is assigned to this object.
     * @param level the COM Authentication level that is assigned to this object.
     * @throws WMIException
     */
    public void setAuthenticationLevel(WbemAuthenticationLevelEnum level) throws WMIException {
        putProperty("AuthenticationLevel", new JIVariant(level.getValue()));
    }

    /**
     * Get the COM impersonation level that is assigned to this object.
     * This setting determines if processes owned by Windows Management Instrumentation (WMI)
     * can detect or use your security credentials when making calls to other processes.
     *
     * @return the COM impersonation level that is assigned to this object.
     * @throws WMIException
     */
    public WbemImpersonationLevelEnum getImpersonationLevel() throws WMIException {
        int value = (Integer)getProperty(Integer.class, "ImpersonationLevel");
        return WbemImpersonationLevelEnum.parse(value);
    }

    /**
     * Set the COM impersonation level that is assigned to this object.
     * @param level the COM impersonation level that is assigned to this object.
     * @throws WMIException
     */
    public void setImpersonationLevel(WbemImpersonationLevelEnum level) throws WMIException {
        putProperty("ImpersonationLevel", new JIVariant(level.getValue()));
    }

    /**
     * The privileges property is an SWbemPrivilegeSet object.
     * This property is used to enable or disable specific Windows privileges.
     * You may need to set one of these privileges to perform specific tasks using the Windows Management Instrumentation (WMI) API.
     *
     * <h3>Remark</h3>
     * <p>This setting allows you to grant or revoke privileges as part of a WMI moniker string.
     * For the complete list of applicable values, see {@link org.wmi4j.consts.WbemPrivilegeEnum} </p>
     *
     * <p>You can change the privileges defined for the {@link org.wmi4j.SWbemServices},
     * {@link org.wmi4j.SWbemObject}, {@link org.wmi4j.SWbemObjectSet}, {@link org.wmi4j.SWbemObjectPath},
     * and {@link org.wmi4j.SWbemLocator} objects by adding {@link org.wmi4j.SWbemPrivilege} objects to the privileges property.</p>
     *
     * <p>There are fundamental differences in how different versions of Windows handle changes to privileges.
     * If you are developing an application that is only used on Windows platforms, you can set or revoke privileges at any time.</p>
     *
     * @return An SWbemPrivilegeSet object.
     * @throws WMIException
     */
    public SWbemPrivilegeSet getPrivileges() throws WMIException {
        return getProperty(SWbemPrivilegeSet.class, "Privileges");
    }
}
