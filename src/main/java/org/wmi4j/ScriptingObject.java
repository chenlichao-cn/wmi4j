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
abstract class ScriptingObject {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected IJIDispatch dispatch;

    ScriptingObject(final IJIDispatch dispatch) {
        this.dispatch = dispatch;
        this.dispatch.registerUnreferencedHandler(new IJIUnreferenced() {

            @Override
            public void unReferenced() {
                logger.info("{}对象引用数为0，将被垃圾回收.", dispatch);
            }

        });
    }

    IJIDispatch getDispatch() {
        return dispatch;
    }

    @SuppressWarnings("unchecked")
    protected <T> T callMethod(Class<? extends ScriptingObject> returnType, String methodName, Object ...params) throws WMIException {
        logger.debug("Execute {}.{}.{} method...", this.getClass().getSimpleName(), methodName, formatParams(params));
        T retVal = null;

        try {
            JIVariant[] results = dispatch.callMethodA(methodName, params);
            IJIComObject comObject = JIObjectFactory.narrowObject(results[0].getObjectAsComObject());

            IJIDispatch resultDispatch = null;
            if(IJIDispatch.IID.equalsIgnoreCase(comObject.getInterfaceIdentifier())) {
                resultDispatch = (IJIDispatch)comObject;
            } else {
                IJIComObject d = JIObjectFactory.narrowObject(comObject.queryInterface(IJIDispatch.IID));
                resultDispatch = (IJIDispatch)d;
            }
            retVal = (T) returnType.getDeclaredConstructor(IJIDispatch.class).newInstance(resultDispatch);
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
