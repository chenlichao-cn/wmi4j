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

/**
 * Use the methods and properties of the SWbemObjectPath object to construct and validate an object path.
 * <p/>
 * Created by chenlichao on 14-7-26.
 */
public class SWbemObjectPath extends AbstractSecurityScriptingObject {
    SWbemObjectPath(IJIDispatch dispatch) {
        super(dispatch);
    }

    /**
     * Forces the path to address a WMI class.
     * @throws WMIException
     */
    public void setAsClass() throws WMIException {
        callMethod(null, "SetAsClass");
    }

    /**
     * Forces the path to address a singleton WMI instance of a class.
     * A singleton class is a class that can never have more than one instance.
     * @throws WMIException
     */
    public void setAsSingleton() throws WMIException {
        callMethod(null, "SetAsSingleton");
    }

    /**
     * Get the Authority component of the object path.
     * @return the Authority component of the object path.
     * @throws WMIException
     */
    public String getAuthority() throws WMIException {
        return getProperty(String.class, "Authority");
    }

    /**
     * Set the Authority component of the object path.
     * @param authority the Authority component of the object path.
     * @throws WMIException
     */
    public void setAuthority(String authority) throws WMIException {
        putProperty("Authority", new JIVariant(authority));
    }

    /**
     * Get the name of the class that is part of the object path.
     * @return the name of the class that is part of the object path.
     * @throws WMIException
     */
    public String getClazz() throws WMIException {
        return getProperty(String.class, "Class");
    }

    /**
     * Set the name of the class that is part of the object path.
     * @param className the name of the class that is part of the object path.
     * @throws WMIException
     */
    public void setClass(String className) throws WMIException {
        putProperty("Class", new JIVariant(className));
    }

    /**
     * Get the path in a form that can be used as a moniker display name.
     * @return the path in a form that can be used as a moniker display name.
     * @throws WMIException
     */
    public String getDisplayName() throws WMIException {
        return getProperty(String.class, "DisplayName");
    }

    /**
     * Set the path in a form that can be used as a moniker display name.
     * @param displayName the path in a form that can be used as a moniker display name.
     * @throws WMIException
     */
    public void setDisplayName(String displayName) throws WMIException {
        putProperty("DisplayName", new JIVariant(displayName));
    }

    /**
     * Indicates if this path represents a class. This property is read-only.
     * @return True if this path represents a class, otherwise return false.
     * @throws WMIException
     */
    public boolean isClass() throws WMIException {
        return (Boolean)getProperty(Boolean.class, "IsClass");
    }

    /**
     * Indicates if this path represents a singleton instance.
     * A singleton instance is an instance of a class that can never have more than one instance.
     * This property is read-only.
     *
     * @return True if this path represents a singleton instance, otherwise return false.
     * @throws WMIException
     */
    public boolean isSingleton() throws WMIException {
        return (Boolean)getProperty(Boolean.class, "IsSingleton");
    }

    /**
     * Get an {@link org.wmi4j.SWbemObjectSet} object that contains the key value bindings. This property is read-only.
     * @return an {@link org.wmi4j.SWbemObjectSet} object that contains the key value bindings.
     * @throws WMIException
     */
    public SWbemNamedValueSet getKeys() throws WMIException {
        return getProperty(SWbemNamedValueSet.class, "Keys");
    }

    /**
     * The Locale property of the SWbemObjectPath object contains the locale of object path.
     *
     * <p>When the locale property is part of a standalone SWbemObjectPath object,
     * it is read/write and can be used to set the locale component of the moniker.</p>
     *
     * <p>When the locale property is accessed as part of a {@link org.wmi4j.SWbemObject#getPath()} property,
     * it is read-only and reports the value of the locale used in binding to the namespace from which the object was obtained.</p>
     *
     * <p>For Microsoft locale identifiers, the format of the string is "MS_xxxx",
     * where xxxx is a string in hexadecimal form that indicates the LCID.
     * For example, the American English locale identifier is "MS_409".</p>
     *
     * @return the locale of object path.
     * @throws WMIException
     */
    public String getLocale() throws WMIException {
        return getProperty(String.class, "Locale");
    }

    /**
     * Set the locale of object path.
     * <p>When the locale property is accessed as part of a {@link org.wmi4j.SWbemObject#getPath()} property,
     * it is read-only and reports the value of the locale used in binding to the namespace from which the object was obtained.</p>
     *
     * @param locale the locale of object path.
     * @throws WMIException
     */
    public void setLocale(String locale) throws WMIException {
        putProperty("Locale", new JIVariant(locale));
    }

    /**
     * Get the name of the namespace that is part of the object path.
     * For example, the following path shows the namespace property that returns root\cimv2: <br />
     * <code>
     *     \\computer\root\cimv2:win32_logicaldisk="a:"
     * </code>
     * @return the name of the namespace that is part of the object path.
     * @throws WMIException
     */
    public String getNamespace() throws WMIException {
        return getProperty(String.class, "Namespace");
    }

    /**
     * Set the name of the namespace that is part of the object path.
     * @param namespace the name of the namespace that is part of the object path.
     * @throws WMIException
     */
    public void setNamespace(String namespace) throws WMIException {
        putProperty("Namespace", new JIVariant(namespace));
    }

    /**
     * Get the name of the parent namespace that is part of the object path. This property is read-only.
     * @return the name of the parent namespace that is part of the object path.
     * @throws WMIException
     */
    public String getParentNamespace() throws WMIException {
        return getProperty(String.class, "ParentNamespace");
    }

    /**
     * Get the absolute path.
     * @return the absolute path.
     * @throws WMIException
     */
    public String getPath() throws WMIException {
        return getProperty(String.class, "Path");
    }

    /**
     * Set the absolute path.
     * @param path the absolute path.
     * @throws WMIException
     */
    public void setPath(String path) throws WMIException {
        putProperty("Path", new JIVariant(path));
    }

    /**
     * Get the relative path from the complete path.
     * @return the relative path from the complete path.
     * @throws WMIException
     */
    public String getRelPath() throws WMIException {
        return getProperty(String.class, "RelPath");
    }

    /**
     * Set the relative path from the complete path.
     * @param relPath the relative path from the complete path.
     * @throws WMIException
     */
    public void setRelPath(String relPath) throws WMIException {
        putProperty("RelPath", new JIVariant(relPath));
    }

    /**
     * Get the name of the server that is part of the object path.
     * @return the name of the server that is part of the object path.
     * @throws WMIException
     */
    public String getServer() throws WMIException {
        return getProperty(String.class, "Server");
    }

    /**
     * Set the name of the server that is part of the object path.
     * @param server the name of the server that is part of the object path.
     * @throws WMIException
     */
    public void setServer(String server) throws WMIException {
        putProperty("Server", new JIVariant(server));
    }
}
