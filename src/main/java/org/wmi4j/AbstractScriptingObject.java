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

import org.jinterop.dcom.common.IJIUnreferenced;
import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.IJIComObject;
import org.jinterop.dcom.core.JIString;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.JIObjectFactory;
import org.jinterop.dcom.impls.automation.IJIDispatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

/**
 * Abstract class of WMI objects
 * Created by chenlichao on 14-7-17.
 */
abstract class AbstractScriptingObject {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    final IJIDispatch dispatch;

    AbstractScriptingObject(final IJIDispatch dispatch) {
        this.dispatch = dispatch;
        this.dispatch.registerUnreferencedHandler(new IJIUnreferenced() {

            @Override
            public void unReferenced() {
                try {
                    dispatch.addRef();
                    logger.debug("Add reference to {}", dispatch);
                } catch (JIException e) {
                    logger.warn("Exception occurred when add reference to dispatch.", e);
                }
            }

        });
    }

    /**
     * The security property is used to read, or set the security settings.
     * This property is an {@link SWbemSecurity} object. The security settings in this object do not indicate the authentication,
     * impersonation, or privilege settings made on a connection to Windows Management Instrumentation (WMI),
     * or the security in effect for the proxy when an object is delivered to a sink in an asynchronous call.
     * <p><strong>Note: </strong> Setting the Security_ property of an SWbemObject object to NULL grants unlimited access to everyone all the time.
     * For more information, see {@link org.wmi4j.SWbemSecurity}.</p>
     * @return The security settings of this WMI object.
     * @throws WMIException
     */
    public SWbemSecurity getSecurity() throws WMIException {
        try {
            JIVariant result = dispatch.get("Security_");
            IJIComObject comObject = result.getObjectAsComObject();
            IJIDispatch securityDispatch = (IJIDispatch)JIObjectFactory.narrowObject(comObject);
            return new SWbemSecurity(securityDispatch);
        } catch (JIException e) {
            throw new WMIException(e);
        }
    }

    IJIDispatch getDispatch() {
        return dispatch;
    }

    @SuppressWarnings("unchecked")
    <T> T callMethod(Class<?> returnType, String methodName, Object... params) throws WMIException {
        logger.debug("Execute {}.{}.{} method...", this.getClass().getSimpleName(), methodName, formatParams(params));
        T retVal = null;

        try {
            JIVariant[] results = dispatch.callMethodA(methodName, params);
            if(returnType == null) {
                return null;
            }
            if(AbstractScriptingObject.class.isAssignableFrom(returnType)) {
                IJIComObject comObject = JIObjectFactory.narrowObject(results[0].getObjectAsComObject());

                IJIDispatch resultDispatch;
                if(IJIDispatch.IID.equalsIgnoreCase(comObject.getInterfaceIdentifier())) {
                    resultDispatch = (IJIDispatch)comObject;
                } else {
                    IJIComObject d = JIObjectFactory.narrowObject(comObject.queryInterface(IJIDispatch.IID));
                    resultDispatch = (IJIDispatch)d;
                }
                retVal = (T) returnType.getDeclaredConstructor(IJIDispatch.class).newInstance(resultDispatch);
            } else {
                if(Boolean.class.equals(returnType)) {
                    retVal = (T) Boolean.valueOf(results[0].getObjectAsBoolean());
                } else if(String.class.equals(returnType)) {
                    retVal = (T) results[0].getObjectAsString2();
                }
            }
        } catch (JIException e) {
            throw new WMIException(e);
        } catch (InvocationTargetException e) {
            throw new WMIException(0x01000001, "Bug of wmi4j, please send log to reacher-chen@163.com.", e);
        } catch (NoSuchMethodException e) {
            throw new WMIException(0x01000001, "Bug of wmi4j, please send log to reacher-chen@163.com.", e);
        } catch (InstantiationException e) {
            throw new WMIException(0x01000001, "Bug of wmi4j, please send log to reacher-chen@163.com.", e);
        } catch (IllegalAccessException e) {
            throw new WMIException(0x01000001, "Bug of wmi4j, please send log to reacher-chen@163.com.", e);
        }

        return retVal;
    }

    private Object[] formatParams(Object[] ps) {
        Object[] result = new Object[ps.length];
        for(int i=0; i<result.length; i++) {
            if(ps[i] instanceof JIString) {
                result[i] = ((JIString)ps[i]).getString();
            } else if(JIVariant.OPTIONAL_PARAM().toString().equals(ps[i].toString())) {
                result[i] = null;
            } else {
                result[i] = ps[i];
            }
        }
        return result;
    }
}
