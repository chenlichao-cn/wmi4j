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

import static org.apache.commons.lang3.StringUtils.isEmpty;

import org.apache.commons.lang3.StringUtils;
import org.jinterop.dcom.core.JIString;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import java.util.List;

/**
 * <p>You can use the methods of an SWbemServices object to perform operations against a namespace
 * on either a local host or a remote host. SWbemServices object can be initialized by call
 * {@linkplain org.wmi4j.SWbemLocator#connectServer(String, String, org.wmi4j.SWbemLocator.SecurityFlag, SWbemNamedValueSet) SWbemLocator.connectServer()}
 * method only.</p>
 * Created by chenlichao on 14-7-17.
 */
public class SWbemServices extends AbstractScriptingObject {
    //TODO 异步方法实现
    SWbemServices(IJIDispatch dispatch) {
        super(dispatch);
    }

    /**
     * Returns a collection of objects (classes or instances) called endpoints that are associated with a specified object.
     * This method performs the same function that the ASSOCIATORS OF WQL query performs.
     * This method is called in the semisynchronous mode by default.
     *
     * @param objectPath String that contains the object path of the source class or instance.
     * @param assocClass <strong>[Optional]</strong> String that contains an association class. If specified, this parameter indicates that
     *                   the returned endpoints must be associated with the source through the specified association class
     *                   or a class that is derived from this association class.
     * @param resultClass <strong>[Optional]</strong> String that contains a class name. If specified, this optional parameter indicates that
     *                    the returned endpoints must belong to or be derived from the class specified in this parameter.
     * @param resultRole <strong>[Optional]</strong> String that contains a property name.
     *                   If specified, this parameter indicates that the returned endpoints must play a particular
     *                   role in their association with the source object. The role is defined by the name of
     *                   a specified property (which must be a reference property) of an association.
     * @param role <strong>[Optional]</strong> String that contains a property name.
     *             If specified, this parameter indicates that the returned endpoints must participate in an association
     *             with the source object in which the source object plays a particular role. The role is defined by
     *             the name of a specified property (which must be a reference property) of an association.
     * @param classesOnly <strong>[Optional]</strong> Boolean value that indicates whether a list of class names should be returned rather than
     *                    actual instances of the classes. These are the classes to which the endpoint instances belong.
     *                    The default value for this parameter is FALSE.
     * @param schemaOnly <strong>[Optional]</strong> Boolean value that indicates whether the query applies to the schema rather than the data.
     *                   The default value for this parameter is FALSE. It can only be set to TRUE if the strObjectPath
     *                   parameter specifies the object path of a class. When set to TRUE, the set of returned
     *                   endpoints represent classes that are suitably associated with the source class in schema.
     * @param requiredAssocQualifier <strong>[Optional]</strong> String that contains a qualifier name.
     *                               If specified, this parameter indicates that the returned endpoints must be
     *                               associated with the source object through an association class that includes the
     *                               specified qualifier.
     * @param requiredQualifier <strong>[Optional]</strong> String that contains a qualifier name.
     *                          If specified, this parameter indicates that the returned endpoints must include
     *                          the specified qualifier.
     * @param flags <strong>[Optional]</strong> Integer that specifies additional flags to the operation.
     *              The default value for this parameter is {@linkplain AssociatorsOfFlag#wbemFlagReturnImmediately wbemFlagReturnImmediately},
     *              which calls the method in the semisynchronous mode. This parameter can accept the following values.
     * @param objwbemNamedValueSet <strong>[Optional]</strong> Typically, this is undefined.
     *                             Otherwise, this is an {@link org.wmi4j.SWbemNamedValueSet} object whose elements represent the context
     *                             information that can be used by the provider that is servicing the request.
     *                             A provider that supports or requires such information must document the recognized
     *                             value names, data type of the value, allowed values, and semantics.
     * @return
     * @throws WMIException
     */
    public SWbemObjectSet associatorsOf(String objectPath, String assocClass, String resultClass,
              String resultRole, String role, Boolean classesOnly, Boolean schemaOnly, String requiredAssocQualifier,
              String requiredQualifier, List<AssociatorsOfFlag> flags, SWbemNamedValueSet objwbemNamedValueSet) throws WMIException {
        if(StringUtils.isEmpty(objectPath)) {
            throw new IllegalArgumentException("Object path is empty.");
        }
        Integer iFlags = null;
        if(flags != null) {
            for(AssociatorsOfFlag flag : flags) {
                if(iFlags == null) {
                    iFlags = flag.getValue();
                } else {
                    iFlags += flag.getValue();
                }
            }
        }
        return callMethod(SWbemObjectSet.class, "AssociatorsOf",
                new JIString(objectPath),
                (isEmpty(assocClass)) ? JIVariant.OPTIONAL_PARAM() : new JIString(assocClass),
                (isEmpty(resultClass)) ? JIVariant.OPTIONAL_PARAM() : new JIString(resultClass),
                (isEmpty(resultRole)) ? JIVariant.OPTIONAL_PARAM() : new JIString(resultRole),
                (isEmpty(role)) ? JIVariant.OPTIONAL_PARAM() : new JIString(role),
                (classesOnly == null) ? JIVariant.OPTIONAL_PARAM() : classesOnly,
                (schemaOnly == null) ? JIVariant.OPTIONAL_PARAM() : schemaOnly,
                (isEmpty(requiredAssocQualifier)) ? JIVariant.OPTIONAL_PARAM() : new JIString(requiredAssocQualifier),
                (isEmpty(requiredQualifier)) ? JIVariant.OPTIONAL_PARAM() : new JIString(requiredQualifier),
                (iFlags == null) ? JIVariant.OPTIONAL_PARAM() : iFlags,
                (objwbemNamedValueSet == null) ? JIVariant.OPTIONAL_PARAM() : objwbemNamedValueSet.getDispatch());
    }

    /**
     * Use default parameters for method {@link #associatorsOf(String, String, String, String, String, Boolean, Boolean, String, String, List, SWbemNamedValueSet)}
     */
    public SWbemObjectSet associatorsOf(String objectPath) throws WMIException {
        return associatorsOf(objectPath, null, null, null, null, null, null, null, null, null, null);
    }

    /**
     * deletes the class or instance that is specified in the object path. You can only delete objects in the current namespace.
     * If a dynamic provider supplies the class or instance, you cannot delete this object
     * unless the provider supports class or instance deletion.
     * This method is called in the synchronous mode.
     *
     * @param objectPath  String that contains the object path to the object that you want to delete.
     * @param flags <strong>[Optional]</strong> Reserved. This value must be zero.
     * @param objWbemNamedValueSet <strong>[Optional]</strong> Typically, this is undefined.
     *                             Otherwise, this is an {@link org.wmi4j.SWbemNamedValueSet} object whose elements represent the context
     *                             information that can be used by the provider that is servicing the request.
     *                             A provider that supports or requires such information must document the recognized
     *                             value names, data type of the value, allowed values, and semantics.
     * @throws WMIException
     */
    public void delete(String objectPath, Integer flags, SWbemNamedValueSet objWbemNamedValueSet) throws WMIException {
        if(isEmpty(objectPath)) {
            throw new IllegalArgumentException("Object path is empty.");
        }
        if(flags != null && flags != 0) {
            throw new IllegalArgumentException("Flags must be zero.");
        }
        callMethod(null, "Delete", new JIString(objectPath), 0,
                (objWbemNamedValueSet == null) ? JIVariant.OPTIONAL_PARAM() : objWbemNamedValueSet.getDispatch());
    }

    /**
     * Use default parameters for method {@link #delete(String, Integer, SWbemNamedValueSet)}
     */
    public void delete(String objectPath) throws WMIException {
        delete(objectPath, null, null);
    }

    /**
     * Executes a method that is exported by a method provider.
     * This method blocks while the method that is forwarded to the appropriate provider executes.
     * The information and status are then returned. The provider, rather than WMI, implements the method.
     * This method is called in the synchronous mode.
     *
     * @param objectPath String that contains the object path of the object for which the method is executed.
     * @param methodName Name of the method for the object.
     * @param inParameters <strong>[Optional]</strong> An {@link org.wmi4j.SWbemObject} object that contains the input parameters for the method being executed.
     *                     By default, this parameter is undefined.
     * @param flags <strong>[Optional]</strong> Reserved. This value must be zero.
     * @param objWbemNamedValueSet <strong>[Optional]</strong> Typically, this is undefined.
     *                             Otherwise, this is an {@link org.wmi4j.SWbemNamedValueSet} object whose elements represent the context
     *                             information that can be used by the provider that is servicing the request.
     *                             A provider that supports or requires such information must document the recognized
     *                             value names, data type of the value, allowed values, and semantics.
     * @return If the method is successful, an {@link org.wmi4j.SWbemObject} object is returned.
     * The returned object contains the out parameters and return value for the method that is being executed.
     * @throws WMIException
     */
    public SWbemObject execMethod(String objectPath, String methodName, SWbemObject inParameters, Integer flags,
                                  SWbemNamedValueSet objWbemNamedValueSet) throws WMIException {
        if(isEmpty(objectPath)) {
            throw new IllegalArgumentException("Object path is empty.");
        }
        if(isEmpty(methodName)) { throw new IllegalArgumentException("Method name is empty."); }
        if(flags != null && flags != 0) {
            throw new IllegalArgumentException("Flags must be zero.");
        }
        return callMethod(SWbemObject.class, "ExecMethod", new JIString(objectPath), new JIString(methodName),
                (inParameters == null) ? JIVariant.OPTIONAL_PARAM() : inParameters.getDispatch(),
                JIVariant.OPTIONAL_PARAM(),
                (objWbemNamedValueSet == null) ? JIVariant.OPTIONAL_PARAM() : objWbemNamedValueSet.getDispatch());
    }

    /**
     * Use default parameters for method {@link #execMethod(String, String, SWbemObject, Integer, SWbemNamedValueSet)}
     */
    public SWbemObject execMethod(String objectPath, String methodName) throws WMIException {
        return execMethod(objectPath, methodName, null, null, null);
    }

    /**
     * Executes a query to receive events. The call returns immediately.
     * The user can poll the returned enumerator for events as they arrive.
     * The method is called in the semisynchronous mode.
     *
     * @param queryString String that contains the text of the event-related query. This parameter cannot be blank.
     *                    For more information on building WMI query strings,
     *                    see <a href="http://msdn.microsoft.com/en-us/library/aa392902(v=vs.85).aspx" target="_blank">Querying with WQL</a>
     *                    and the <a href="http://msdn.microsoft.com/en-us/library/aa394606(v=vs.85).aspx" target="_blank">WQL reference</a>.
     * @param queryLanguage <strong>[Optional]</strong> String that contains the query language to be used. If specified, this value must be "WQL".
     * @param flags <strong>[Optional]</strong> This is an integer that determines the behavior of the query. If specified, this value must be 48.
     * @param objWbemNamedValueSet <strong>[Optional]</strong> Typically, this is undefined.
     *                             Otherwise, this is an {@link org.wmi4j.SWbemNamedValueSet} object whose elements represent the context
     *                             information that can be used by the provider that is servicing the request.
     *                             A provider that supports or requires such information must document the recognized
     *                             value names, data type of the value, allowed values, and semantics.
     * @return If no error occurs, this method returns an {@link org.wmi4j.SWbemEventSource} object.
     * You can call the {SWbemEventSource.NextEvent} method to retrieve events as they arrive. TODO link SWbemEventSource.NextEvent
     * @throws WMIException
     */
    public SWbemEventSource execNotificationQuery(String queryString, String queryLanguage,
                                                  Integer flags,
                                                  SWbemNamedValueSet objWbemNamedValueSet) throws WMIException {
        if(isEmpty(queryString)) { throw new IllegalArgumentException("Query string is empty."); }
        if(StringUtils.isNotEmpty(queryLanguage) && !"WQL".equals(queryLanguage)) {
            throw new IllegalArgumentException("Query language must be \"WQL\".");
        }
        if(flags != null && flags != 48) {
            throw new IllegalArgumentException("Flags must be 48.");
        }
        return callMethod(SWbemEventSource.class, "ExecNotificationQuery", new JIString(queryString),
                JIVariant.OPTIONAL_PARAM(), JIVariant.OPTIONAL_PARAM(),
                (objWbemNamedValueSet == null) ? JIVariant.OPTIONAL_PARAM() : objWbemNamedValueSet.getDispatch());
    }

    /**
     * Use default parameters for {@link #execNotificationQuery(String, String, Integer, SWbemNamedValueSet)}
     */
    public SWbemEventSource execNotificationQuery(String queryString) throws WMIException {
        return execNotificationQuery(queryString, null, null, null);
    }

    /**
     * Executes a query to retrieve objects. These objects are available through the returned
     * {@link SWbemObjectSet} collection.
     * @param queryString String that contains the text of the query. This parameter cannot be blank.
     *                    For more information on building WMI query strings,
     *                    see <a href="http://msdn.microsoft.com/en-us/library/aa392902(v=vs.85).aspx" target="_blank">Querying with WQL</a>
     *                    and the <a href="http://msdn.microsoft.com/en-us/library/aa394606(v=vs.85).aspx" target="_blank">WQL reference</a>.
     * @param queryLanguage <strong>[Optional]</strong> String that contains the query language to be used. If specified, this value must be "WQL".
     * @param flags <strong>[Optional]</strong> Integer that determines the behavior of the query and determines whether this call returns immediately.
     *              The default value for this parameter is {@linkplain org.wmi4j.SWbemServices.ExecQueryFlag#wbemFlagReturnImmediately wbemFlagReturnImmediately}.
     * @param objWbemNamedValueSet <strong>[Optional]</strong> Typically, this is undefined.
     *                             Otherwise, this is an {@link org.wmi4j.SWbemNamedValueSet} object whose elements represent the context
     *                             information that can be used by the provider that is servicing the request.
     *                             A provider that supports or requires such information must document the recognized
     *                             value names, data type of the value, allowed values, and semantics.
     * @return If no error occurs, this method returns an {@link SWbemObjectSet} object. This is an object collection that contains the result set of the query.
     * @throws WMIException
     */
    public SWbemObjectSet execQuery(String queryString, String queryLanguage, List<ExecQueryFlag> flags, SWbemNamedValueSet objWbemNamedValueSet) throws WMIException {
        if(StringUtils.isEmpty(queryString)) {
            throw new IllegalArgumentException("QueryString is empty.");
        }
        if(queryLanguage != null && !"WQL".equals(queryLanguage)) {
            throw new IllegalArgumentException("QueryLanguage must be \"WQL\".");
        }
        Integer iFlags = null;
        if(flags != null) {
            for(ExecQueryFlag flag : flags) {
                if(iFlags == null) {
                    iFlags = flag.getValue();
                } else {
                    iFlags += flag.getValue();
                }
            }
        }
        return callMethod(SWbemObjectSet.class, "ExecQuery",
                new JIString(queryString), JIVariant.OPTIONAL_PARAM(),
                (iFlags == null) ? JIVariant.OPTIONAL_PARAM() : iFlags,
                (objWbemNamedValueSet == null) ? JIVariant.OPTIONAL_PARAM() : objWbemNamedValueSet.getDispatch());
    }

    /**
     * Use default parameters for execQuery method {@link #execQuery(String, String, List, SWbemNamedValueSet)}
     */
    public SWbemObjectSet execQuery(String queryString) throws WMIException {
        return execQuery(queryString, null, null, null);
    }

    /**
     * Retrieves an object, that is either a class definition or an instance, based on the object path.
     * This method retrieves only objects from the namespace that is associated with the current SWbemServices object.
     *
     * @param objectPath <strong>[Optional]</strong> String that contains the object path of the object to retrieve.
     *                   If this value is empty, the empty object that is returned can become a new class.
     * @param flags <strong>[Optional]</strong> Integer that determines the behavior of the query.
     * @param objWbemNamedValueSet <strong>[Optional]</strong> Typically, this is undefined.
     *                             Otherwise, this is an {@link org.wmi4j.SWbemNamedValueSet} object whose elements represent the context
     *                             information that can be used by the provider that is servicing the request.
     *                             A provider that supports or requires such information must document the recognized
     *                             value names, data type of the value, allowed values, and semantics.
     * @return If successful, this method returns an {@link SWbemObject} object that represents the requested object.
     * @throws WMIException
     */
    public SWbemObject get(String objectPath, List<GetFlag> flags, SWbemNamedValueSet objWbemNamedValueSet) throws WMIException{
        Integer iFlags = null;
        if(flags != null) {
            for(GetFlag flag : flags) {
                if(iFlags == null) {
                    iFlags = flag.getValue();
                } else {
                    iFlags += flag.getValue();
                }
            }
        }
        return callMethod(SWbemObject.class, "Get",
                StringUtils.isEmpty(objectPath) ? JIVariant.OPTIONAL_PARAM() : new JIString(objectPath),
                (iFlags == null) ? JIVariant.OPTIONAL_PARAM() : iFlags,
                (objWbemNamedValueSet == null) ? JIVariant.OPTIONAL_PARAM() : objWbemNamedValueSet.getDispatch());
    }

    /**
     * Use default parameters for get method {@link #get(String, List, SWbemNamedValueSet)}
     */
    public SWbemObject get(String objectPath) throws WMIException {
        return get(objectPath, null, null);
    }

    /**
     * Creates an enumerator that
     * returns the instances of a specified class according to the user-specified selection criteria.
     * This method implements a simple query. More complex queries may require the use of
     * {@linkplain #execQuery(String, String, java.util.List, SWbemNamedValueSet) SWbemService.execQuery} method.
     * @param className String that contains the name of the class for which instances are desired. This parameter cannot be blank.
     * @param flags This parameter determines how detailed the call enumerates and if this call returns immediately.
     *              The default value for this parameter is {@linkplain org.wmi4j.SWbemServices.InstancesOfFlag#wbemFlagReturnImmediately wbemFlagReturnImmediately}.
     * @param objWbemNamedValueSet <strong>[Optional]</strong> Typically, this is undefined.
     *                             Otherwise, this is an {@link org.wmi4j.SWbemNamedValueSet} object whose elements represent the context
     *                             information that can be used by the provider that is servicing the request.
     *                             A provider that supports or requires such information must document the recognized
     *                             value names, data type of the value, allowed values, and semantics.
     * @return If successful, the method returns an {@link SWbemObjectSet}.
     * @throws WMIException
     */
    public SWbemObjectSet instancesOf(String className, List<InstancesOfFlag> flags, SWbemNamedValueSet objWbemNamedValueSet) throws WMIException {
        if(StringUtils.isEmpty(className)) {
            throw new IllegalArgumentException("Class name is empty.");
        }
        Integer iFlags = null;
        if(flags != null) {
            for(InstancesOfFlag flag : flags) {
                if(iFlags == null) {
                    iFlags = flag.getValue();
                } else {
                    iFlags += flag.getValue();
                }
            }
        }
        return callMethod(SWbemObjectSet.class, "InstancesOf",
                new JIString(className),
                (iFlags == null) ? JIVariant.OPTIONAL_PARAM() : iFlags,
                (objWbemNamedValueSet == null) ? JIVariant.OPTIONAL_PARAM() : objWbemNamedValueSet.getDispatch());
    }

    /**
     * Use default parameters for method {@link #instancesOf(String, List, SWbemNamedValueSet)}
     */
    public SWbemObjectSet instancesOf(String className) throws WMIException {
        return instancesOf(className, null, null);
    }

    /**
     * Returns a collection of all association classes or instances that refer to a specific source class or instance.
     * This method performs the same function that the REFERENCES OF WQL query performs.
     * This method is called in the semisynchronous mode.
     *
     * @param objectPath String that contains the object path of the source for this method.
     * @param resultClass <strong>[Optional]</strong> String that contains a class name. If specified, this optional parameter indicates that
     *                    the returned endpoints must belong to or be derived from the class specified in this parameter.
     * @param role <strong>[Optional]</strong> String that contains a property name.
     *             If specified, this parameter indicates that the returned endpoints must participate in an association
     *             with the source object in which the source object plays a particular role. The role is defined by
     *             the name of a specified property (which must be a reference property) of an association.
     * @param classesOnly <strong>[Optional]</strong> Boolean value that indicates whether a list of class names should be returned rather than
     *                    actual instances of the classes. These are the classes to which the endpoint instances belong.
     *                    The default value for this parameter is FALSE.
     * @param schemaOnly <strong>[Optional]</strong> Boolean value that indicates whether the query applies to the schema rather than the data.
     *                   The default value for this parameter is FALSE. It can only be set to TRUE if the strObjectPath
     *                   parameter specifies the object path of a class. When set to TRUE, the set of returned
     *                   endpoints represent classes that are suitably associated with the source class in schema.
     * @param requiredQualifier <strong>[Optional]</strong> String that contains a qualifier name.
     *                          If specified, this parameter indicates that the returned endpoints must include
     *                          the specified qualifier.
     * @param flags <strong>[Optional]</strong> Integer that specifies additional flags to the operation.
     *              The default value for this parameter is {@linkplain ReferenceToFlag#wbemFlagReturnImmediately wbemFlagReturnImmediately},
     *              which calls the method in the semisynchronous mode. This parameter can accept the following values.
     * @param objWbemNamedValueSet <strong>[Optional]</strong> Typically, this is undefined.
     *                             Otherwise, this is an {@link org.wmi4j.SWbemNamedValueSet} object whose elements represent the context
     *                             information that can be used by the provider that is servicing the request.
     *                             A provider that supports or requires such information must document the recognized
     *                             value names, data type of the value, allowed values, and semantics.
     * @return
     * @throws WMIException
     */
    public SWbemObjectSet referencesTo(String objectPath, String resultClass, String role,
                                       Boolean classesOnly, Boolean schemaOnly, String requiredQualifier,
                                       List<ReferenceToFlag> flags, SWbemNamedValueSet objWbemNamedValueSet) throws WMIException {
        if(isEmpty(objectPath)) {
            throw new IllegalArgumentException("Object path is empty.");
        }
        Integer iFlags = null;
        if(flags != null) {
            for(ReferenceToFlag flag : flags) {
                if(iFlags == null) {
                    iFlags = flag.getValue();
                } else {
                    iFlags += flag.getValue();
                }
            }
        }
        return callMethod(SWbemObjectSet.class, "ReferencesTo", new JIString(objectPath),
                (isEmpty(resultClass)) ? JIVariant.OPTIONAL_PARAM() : new JIString(resultClass),
                (isEmpty(role)) ? JIVariant.OPTIONAL_PARAM() : new JIString(role),
                (classesOnly == null) ? JIVariant.OPTIONAL_PARAM() : classesOnly,
                (schemaOnly == null) ? JIVariant.OPTIONAL_PARAM() : schemaOnly,
                (isEmpty(requiredQualifier)) ? JIVariant.OPTIONAL_PARAM() : new JIString(requiredQualifier),
                (iFlags == null) ? JIVariant.OPTIONAL_PARAM() : iFlags,
                (objWbemNamedValueSet == null) ? JIVariant.OPTIONAL_PARAM() : objWbemNamedValueSet.getDispatch());
    }

    /**
     * Use default parameters for method {@link #referencesTo(String, String, String, Boolean, Boolean, String, List, SWbemNamedValueSet)}
     */
    public SWbemObjectSet referencesTo(String objectPath) throws WMIException {
        return referencesTo(objectPath, null, null, null, null, null, null, null);
    }

    /**
     * Returns an {@link org.wmi4j.SWbemObjectSet} object. This object is a collection of subclasses of a specified class.
     * Items in the returned collection can be obtained using standard collection methods.
     * This method only works for class objects.
     * The method is called in the semisynchronous mode.
     *
     * @param superClass <strong>[Optional]</strong> Specifies a parent class name.
     *                   Only subclasses of this class return in the enumerator.
     *                   If you leave this parameter blank, and if flags is {@link org.wmi4j.SWbemServices.SubclassesOfFlag#wbemQueryFlagShallow wbemQueryFlagShallow},
     *                   only the top-level classes are returned (that is, classes that have no parent class).
     *                   If this parameter is blank and flags is {@link org.wmi4j.SWbemServices.SubclassesOfFlag#wbemQueryFlagDeep wbemQueryFlagDeep},
     *                   all classes within the namespace are returned.
     * @param flags <strong>[Optional]</strong> Determines how detailed the call enumerates.
     *              The default values for this parameter are {@link org.wmi4j.SWbemServices.SubclassesOfFlag#wbemFlagReturnImmediately wbemFlagReturnImmediately}
     *              and {@link org.wmi4j.SWbemServices.SubclassesOfFlag#wbemQueryFlagDeep wbemQueryFlagDeep}.
     * @param objWbemNamedValueSet <strong>[Optional]</strong> Typically, this is undefined.
     *                             Otherwise, this is an {@link org.wmi4j.SWbemNamedValueSet} object whose elements represent the context
     *                             information that can be used by the provider that is servicing the request.
     *                             A provider that supports or requires such information must document the recognized
     *                             value names, data type of the value, allowed values, and semantics.
     * @return
     * @throws WMIException
     */
    public SWbemObjectSet subclassesOf(String superClass, List<SubclassesOfFlag> flags, SWbemNamedValueSet objWbemNamedValueSet) throws WMIException {
        Integer iFlags = null;
        if(flags != null) {
            for(SubclassesOfFlag flag : flags) {
                if(iFlags == null) {
                    iFlags = flag.getValue();
                } else {
                    iFlags += flag.getValue();
                }
            }
        }
        return callMethod(SWbemObjectSet.class, "SubclassesOf",
                (isEmpty(superClass)) ? JIVariant.OPTIONAL_PARAM() : new JIString(superClass),
                (iFlags == null) ? JIVariant.OPTIONAL_PARAM() : iFlags,
                (objWbemNamedValueSet == null) ? JIVariant.OPTIONAL_PARAM() : objWbemNamedValueSet.getDispatch());
    }

    /**
     * Optional values for flags parameter of
     * {@linkplain #get(String, List, SWbemNamedValueSet) SWbemServices.get()} method.
     */
    public enum GetFlag{
        /**
         * Causes WMI to return class amendment data with the base class definition.
         */
        wbemFlagUseAmendedQualifiers(0x20000);

        private int value;
        GetFlag(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * Optional values for flags parameter of
     * {@linkplain #instancesOf(String, List, SWbemNamedValueSet) SWbemServices.instancesOf()} method.
     */
    public enum InstancesOfFlag {
        /**
         * 32(0x20): Causes a forward-only enumerator to be returned.
         * Forward-only enumerators are generally much faster and use less memory than conventional enumerators,
         * but they do not allow calls to {@link SWbemObject#clone()}.
         */
        wbemFlagForwardOnly(0x20),

        /**
         * 0(0x0): Causes WMI to retain pointers to objects of the enumeration until the client releases the enumerator.
         */
        wbemFlagBidirectional(0x0),

        /**
         * 16(0x10): Causes the call to return immediately.
         */
        wbemFlagReturnImmediately(0x10),

        /**
         * 0(0x0): Causes this call to block until the query is complete. This flag calls the method in the synchronous mode.
         */
        wbemFlagReturnWhenComplete(0x0),

        /**
         * 1(0x1): Forces the enumeration to include only immediate subclasses of the specified parent class.
         */
        wbemQueryFlagShallow(0x1),

        /**
         * 0(0x0): Default value for this parameter. This value forces the enumeration to include all classes in the hierarchy.
         */
        wbemQueryFlagDeep(0x0),

        /**
         * 131072(0x20000): Causes WMI to return class amendment data with the base class definition.
         */
        wbemFlagUseAmendedQualifiers(0x20000);;

        private int value;
        InstancesOfFlag(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * Optional values for flags parameter of
     * {@linkplain #execQuery(String, String, List, SWbemNamedValueSet) SWbemServices.execQuery()} method.
     */
    public enum ExecQueryFlag {
        /**
         * 32(0x20): Causes a forward-only enumerator to be returned.
         * Forward-only enumerators are generally much faster and use less memory than conventional enumerators,
         * but they do not allow calls to {@link SWbemObject#clone()}.
         */
        wbemFlagForwardOnly(0x20),

        /**
         * 0(0x0): Causes WMI to retain pointers to objects of the enumeration until the client releases the enumerator.
         */
        wbemFlagBidirectional(0x0),

        /**
         * 16(0x10): Causes the call to return immediately.
         */
        wbemFlagReturnImmediately(0x10),

        /**
         * 0(0x0): Causes this call to block until the query is complete. This flag calls the method in the synchronous mode.
         */
        wbemFlagReturnWhenComplete(0x0),

        /**
         * 2(0x2): Used for prototyping. This flag stops the query from happening and returns an object that looks like a typical result object.
         */
        wbemQueryFlagPrototype(0x2),

        /**
         * 131072(0x20000): Causes WMI to return class amendment data with the base class definition.
         */
        wbemFlagUseAmendedQualifiers(0x20000);

        private int value;
        ExecQueryFlag(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     *
     * Optional values for flags parameter of
     * {@linkplain #associatorsOf(String, String, String, String, String, Boolean, Boolean, String, String, List, SWbemNamedValueSet)} method.
     *
     */
    public enum AssociatorsOfFlag {
        /**
         * 32(0x20): Causes a forward-only enumerator to be returned.
         * Forward-only enumerators are generally much faster and use less memory than conventional enumerators,
         * but they do not allow calls to {@link SWbemObject#clone()}.
         */
        wbemFlagForwardOnly(0x20),

        /**
         * 0(0x0): Causes WMI to retain pointers to objects of the enumeration until the client releases the enumerator.
         */
        wbemFlagBidirectional(0x0),

        /**
         * 16(0x10): Causes the call to return immediately.
         */
        wbemFlagReturnImmediately(0x10),

        /**
         * 0(0x0): Causes this call to block until the query is complete. This flag calls the method in the synchronous mode.
         */
        wbemFlagReturnWhenComplete(0x0),

        /**
         * 131072(0x20000): Causes WMI to return class amendment data with the base class definition.
         */
        wbemFlagUseAmendedQualifiers(0x20000);
        private int value;
        AssociatorsOfFlag(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * Optional values for parameter flags of {@link #referencesTo(String, String, String, Boolean, Boolean, String, List, SWbemNamedValueSet)}
     */
    public enum ReferenceToFlag {
        /**
         * 32(0x20): Causes a forward-only enumerator to be returned.
         * Forward-only enumerators are generally much faster and use less memory than conventional enumerators,
         * but they do not allow calls to {@link SWbemObject#clone()}.
         */
        wbemFlagForwardOnly(0x20),

        /**
         * 0(0x0): Causes WMI to retain pointers to objects of the enumeration until the client releases the enumerator.
         */
        wbemFlagBidirectional(0x0),

        /**
         * 16(0x10): Causes the call to return immediately.
         */
        wbemFlagReturnImmediately(0x10),

        /**
         * 0(0x0): Causes this call to block until the query is complete. This flag calls the method in the synchronous mode.
         */
        wbemFlagReturnWhenComplete(0x0),

        /**
         * 131072(0x20000): Causes WMI to return class amendment data with the base class definition.
         */
        wbemFlagUseAmendedQualifiers(0x20000);
        private int value;
        ReferenceToFlag(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum SubclassesOfFlag {
        /**
         * 1(0x1): Forces the enumeration to include only immediate subclasses of the specified parent class.
         */
        wbemQueryFlagShallow(0x1),

        /**
         * 0(0x0): Default value for this parameter. This value forces the enumeration to include all classes in the hierarchy.
         */
        wbemQueryFlagDeep(0x0),
        /**
         * 16(0x10): Causes the call to return immediately.
         */
        wbemFlagReturnImmediately(0x10),

        /**
         * 0(0x0): Causes this call to block until the query is complete. This flag calls the method in the synchronous mode.
         */
        wbemFlagReturnWhenComplete(0x0),

        /**
         * 131072(0x20000): Causes WMI to return class amendment data with the base class definition.
         */
        wbemFlagUseAmendedQualifiers(0x20000);

        private int value;
        SubclassesOfFlag(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
