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

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.IJIComObject;
import org.jinterop.dcom.core.JIArray;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.JIObjectFactory;
import org.jinterop.dcom.impls.automation.IJIDispatch;
import org.jinterop.dcom.impls.automation.IJIEnumVariant;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Iterator;

/**
 * Abstract object collection.
 * Created by chenlichao on 14-7-23.
 */
abstract class AbstractWbemSet<E extends AbstractScriptingObject> extends AbstractScriptingObject {

    AbstractWbemSet(IJIDispatch dispatch) {
        super(dispatch);
    }

    /**
     * Get how many items are in this SWbemObjectSet collection. This property is read-only.
     *
     * <p>One thing to be careful of when using Count is that WMI does not keep a running tally of the number of items in a collection.
     * If you request Count for a collection, WMI cannot instantly respond with a number;
     * instead, it must literally count the items, enumerating the entire collection.
     * For a collection that has relatively few items, such as services, this enumeration likely takes less than a second.
     * Counting the number of events in an event log collection, however, can take considerably longer.</p>
     * <p>And then suppose you want to display the property values for every event in the collection.
     * If so, WMI will have to enumerate the entire collection a second time.</p>
     *
     * <p><strong>Note: </strong>If you attempt to get this property from an SWbemObjectSet object
     * that is returned from a method where the specified flags are included the wbemFlagForwardOnly flag,
     * you will get an wbemErrFailed error.</p>
     * @return The count of items in this SWbemObjectSet collection.
     * @throws WMIException
     */
    public int getCount() throws WMIException {
        try {
            JIVariant result = dispatch.get("Count");
            if(result == null) {
                throw new WMIException(0, "Get count property failed.");
            }
            return result.getObjectAsInt();
        } catch (JIException e) {
            throw new WMIException(e);
        }
    }

    /**
     * Create a iterator of this object set.
     * @return Iterator instance.
     * @throws WMIException
     */
    public Iterator<E> iterator() throws WMIException {
        try {
            JIVariant variant = dispatch.get("_NewEnum");
            IJIComObject setObj = variant.getObjectAsComObject();
            final IJIEnumVariant enumVariant = (IJIEnumVariant) JIObjectFactory.narrowObject(setObj.queryInterface(IJIEnumVariant.IID));
            final int count = getCount();
            final Class<?> elementClass = getElementType();
            return new Iterator<E>() {
                private int index = 0;
                @Override
                public boolean hasNext() {
                    return index < count;
                }

                @Override
                public E next() {
                    try {
                        Object[] objs = enumVariant.next(1);
                        JIArray array = (JIArray)objs[0];
                        Object[] arrayObj = (Object[])array.getArrayInstance();
                        JIVariant ele = (JIVariant)arrayObj[0];
                        IJIDispatch dispatch = (IJIDispatch) JIObjectFactory.narrowObject(ele.getObjectAsComObject());
                        @SuppressWarnings("unchecked")
                        E result = (E)elementClass.getDeclaredConstructor(IJIDispatch.class).newInstance(dispatch);
                        index++;
                        return result;
                   } catch (Exception e) {
                        index = count;
                        throw new IllegalStateException(e.getMessage(), e);
                    }
                }

                @Override
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        } catch (JIException e) {
            throw new WMIException(e);
        }
    }

    private Class<?> getElementType() {
        Type superClass = this.getClass().getGenericSuperclass();
        Type returnType = ((ParameterizedType) superClass).getActualTypeArguments()[0];
        return (Class<?>)returnType;
    }
}
