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

package cn.chenlichao.wmi4j;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.IJIComObject;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.JIObjectFactory;
import org.jinterop.dcom.impls.automation.IJIDispatch;

/**
 * Objects have a security property, which is the SWbemSecurity object.
 * Created by chenlichao on 14-7-28.
 */
abstract public class AbstractSecurityScriptingObject extends AbstractScriptingObject{
    AbstractSecurityScriptingObject(IJIDispatch dispatch) {
        super(dispatch);
    }

    /**
     * The security property is used to read, or set the security settings.
     * This property is an {@link SWbemSecurity} object. The security settings in this object do not indicate the authentication,
     * impersonation, or privilege settings made on a connection to Windows Management Instrumentation (WMI),
     * or the security in effect for the proxy when an object is delivered to a sink in an asynchronous call.
     * <p><strong>Note: </strong> Setting the Security_ property of an SWbemObject object to NULL grants unlimited access to everyone all the time.
     * For more information, see {@link SWbemSecurity}.</p>
     * @return The security settings of this WMI object.
     * @throws WMIException
     */
    public SWbemSecurity getSecurity() throws WMIException {
        try {
            JIVariant result = dispatch.get("Security_");
            IJIComObject comObject = result.getObjectAsComObject();
            IJIDispatch securityDispatch = (IJIDispatch) JIObjectFactory.narrowObject(comObject);
            return new SWbemSecurity(securityDispatch);
        } catch (JIException e) {
            throw new WMIException(e);
        }
    }
}
