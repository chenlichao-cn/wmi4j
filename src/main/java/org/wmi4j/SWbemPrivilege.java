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

import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;
import org.wmi4j.consts.WbemPrivilegeEnum;

/**
 * The SWbemPrivilege object represents a single privilege.
 *
 * Created by chenlichao on 14-7-28.
 */
public class SWbemPrivilege extends AbstractScriptingObject {
    SWbemPrivilege(IJIDispatch dispatch) {
        super(dispatch);
    }

    /**
     * Displays a description of an SWbemPrivilege object.
     * For example, the privilege that controls whether a user can execute the shutdown method of an object
     * has the "Shut down the system" string in the displayName property.
     *
     * @return Description string of the privilege.
     * @throws WMIException
     */
    public String getDisplayName() throws WMIException {
        return getProperty(String.class, "DisplayName");
    }

    /**
     * The identifier property of an SWbemPrivilege object is an {@link org.wmi4j.consts.WbemPrivilegeEnum}
     * that represents the privilege that is being set or retrieved.
     *
     * @return {@link org.wmi4j.consts.WbemPrivilegeEnum} identifier of privilege
     * @throws WMIException
     */
    public WbemPrivilegeEnum getIdentifier() throws WMIException {
        int value = (Integer)getProperty(Integer.class, "Identifier");
        return WbemPrivilegeEnum.parse(value);
    }

    /**
     * Set the identifier of privilege object.
     * @param identifier the identifier of privilege object.
     * @throws WMIException
     */
    public void setIdentifier(WbemPrivilegeEnum identifier) throws WMIException {
        putProperty("Identifier", new JIVariant(identifier.getValue()));
    }

    /**
     * If this privilege is enabled or disabled.
     * @return Return true if the privilege is enabled, otherwise return false.
     * @throws WMIException
     */
    public boolean isEnabled() throws WMIException {
        return (Boolean)getProperty(Boolean.class, "IsEnabled");
    }

    /**
     * Enable the privilege or disable it.
     * @param enabled True will enabled the privilege, false will disable.
     * @throws WMIException
     */
    public void setEnabled(boolean enabled) throws WMIException {
        putProperty("IsEnabled", new JIVariant(enabled));
    }

    /**
     * A string that uniquely describes a privilege.
     * For example, the privilege that controls whether a user can execute the shutdown method of an object
     * has the "SeShutdownPrivilege" string in the name property.
     * @return A string that uniquely describes a privilege.
     * @throws WMIException
     */
    public String getName() throws WMIException {
        return getProperty(String.class, "Name");
    }
}
