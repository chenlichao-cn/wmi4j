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
import org.jinterop.dcom.common.JISystem;
import org.jinterop.dcom.core.*;
import org.jinterop.dcom.impls.JIObjectFactory;
import org.jinterop.dcom.impls.automation.IJIDispatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.UnknownHostException;
import java.util.logging.Level;

/**
 * <p>
 * You can use the methods of the {@link SWbemLocator} object to obtain an SWbemServices object that represents
 * a connection to a namespace on either a local computer or a remote host computer.
 * You can then use the methods of the {@link org.wmi4j.SWbemServices} object to access WMI.
 * </p>
 * Created by chenlichao on 14-7-17.
 */
public class SWbemLocator {

    private static final Logger logger = LoggerFactory.getLogger(SWbemLocator.class);

    private static final String WMI_CLSID = "76A6415B-CB41-11d1-8B02-00600806D9B6";

    private JISession session;

    private SWbemServices services;
    private IJIDispatch servicesDispatch;

    private String server;
    private String username;
    private String password;
    private String namespace;

    /**
     *
     * @param server Computer name or ip to which you are connecting.
     * @param username User name to use to connect. The string can be in the form of either a user name or a Domain\Username.
     * @param password String that specifies the password to use when attempting to connect.
     * @param namespace String that specifies the namespace to which you log on.
     *                  <p><strong>Note: Optional parameter, if you set it to null, then use the default namespace root\CIMv2</strong></p>
     */
    public SWbemLocator(String server, String username, String password, String namespace) {
        this.server = server;
        this.username = username;
        this.password = password;
        this.namespace = namespace;


    }

    /**
     * <p>The ConnectServer method of the {@link org.wmi4j.SWbemLocator} object connects to the namespace on the computer
     * that is specified in the strServer parameter. The target computer can be either local or remote,
     * but it must have WMI installed. For examples and a comparison with the moniker type of connection,
     * see Creating a WMI Script.</p>
     *
     * <p>Starting with Windows Vista, SWbemLocator.connectServer
     * can connect with computers running IPv6 using an IPv6 address in the strServer parameter.
     * For more information, see IPv6 and IPv4 Support in WMI.</p>
     *
     *<p><strong>Windows Server 2003 and Windows XP:  You only can use SWbemLocator.connectServer
     * to connect to computers with IPv4 addresses.</strong></p>
     *
     * @param locale <strong>Optional.</strong> String that specifies the localization code. If you want to use the current locale, set to null.
     *               If not null, this parameter must be a string that indicates the desired locale where information must be retrieved.
     *               For Microsoft locale identifiers, the format of the string is "MS_xxxx",
     *               where xxxx is a string in the hexadecimal form that indicates the LCID.
     *               For example, American English would appear as "MS_409".
     * @param authority <strong>Optional.</strong> This parameter is optional. However,
     *                  if it is specified, only Kerberos or NTLMDomain can be used.
     *                  <p>If the strAuthority parameter begins with the string "Kerberos:",
     *                  then Kerberos authentication is used and this parameter should contain a Kerberos principal name.
     *                  The Kerberos principal name is specified as Kerberos:domain, such as Kerberos:fabrikam
     *                  where fabrikam is the server to which you are attempting to connect. <br />
     *                  Example: "Kerberos:DOMAIN"</p>
     *                  <p>To use NT Lan Manager (NTLM) authentication, you must specify it as NTLMDomain:domain,
     *                  such as NTLMDomain:fabrikam where fabrikam is the name of the domain. <br />
     *                  Example: "NTLMDomain:DOMAIN"</p>
     * @param securityFlag <strong>Optional.</strong> Used to pass flag values to connectServer method.
     * @param objwbemNamedValueSet <strong>Optional.</strong> Typically, this is undefined.
     *                             Otherwise, this is an {@link org.wmi4j.SWbemNamedValueSet} object whose elements
     *                             represent the context information that can be used by the provider that is servicing
     *                             the request. A provider that supports or requires such information must document
     *                             the recognized value names, data type of the value, allowed values, and semantics.
     * @return If successful, WMI returns an {@link org.wmi4j.SWbemServices} object that is bound to the namespace
     *              that is specified in namespace parameter on the computer that is specified in server parameter.
     * @throws WMIException Failed to connect to the server.
     * @throws UnknownHostException
     */
    public SWbemServices connectServer(String locale, String authority, SecurityFlag securityFlag,
                                       SWbemNamedValueSet objwbemNamedValueSet) throws WMIException, UnknownHostException {
        if(services != null) {
            return services;
        }
        String hostPath = "\\\\" + server + "\\" + namespace;
        logger.info("Connect to {} ...", hostPath);
        try {
            // Initialize Session
            try {
                JISystem.setAutoRegisteration(true);
                JISystem.setInBuiltLogHandler(false);
                JISystem.getLogger().setLevel(Level.FINEST);
            } catch (Exception e) {
                logger.warn("Exception occurred when disable integrated log.");
            }
            String userDomain = "";
            String user = username;
            if(username.contains("\\")) {
                String[] du = username.split("\\\\");
                if(du.length != 2) {
                    throw new IllegalArgumentException("Invalid username: " + username);
                }
                userDomain = du[0];
                user = du[1];
            }
            session = JISession.createSession(userDomain, user, password);
            session.useSessionSecurity(true);
            session.setGlobalSocketTimeout(5000);

            //Obtain WbemScripting.SWbemLocator object
            JIComServer comStub = new JIComServer(JIProgId.valueOf("WbemScripting.SWbemLocator"), server, session);
            IJIComObject unknown = comStub.createInstance();
            IJIComObject wbemLocatorObj = unknown.queryInterface(WMI_CLSID);
            IJIDispatch wbemLocatorDispatch =  (IJIDispatch) JIObjectFactory.narrowObject(wbemLocatorObj.queryInterface(IJIDispatch.IID));

            // Call WbemScripting.SWbemLocator.ConnectServer method，obtain SWbemServices object
            JIVariant[] results = wbemLocatorDispatch.callMethodA("ConnectServer", new Object[]{
                    JIVariant.OPTIONAL_PARAM(),
                    (namespace == null) ? JIVariant.OPTIONAL_PARAM() : new JIString(namespace)
                    , JIVariant.OPTIONAL_PARAM(), JIVariant.OPTIONAL_PARAM(),
                    (locale == null) ? JIVariant.OPTIONAL_PARAM() : new JIString(locale),
                    (authority == null) ? JIVariant.OPTIONAL_PARAM() : new JIString(authority),
                    (securityFlag == null) ? 0 : securityFlag.getValue(),
                    (objwbemNamedValueSet == null) ? JIVariant.OPTIONAL_PARAM() : objwbemNamedValueSet.getDispatch()
            });
            servicesDispatch = (IJIDispatch)JIObjectFactory.narrowObject(results[0].getObjectAsComObject());

            //增加引用数，防止引用数为０时被垃圾回收清理资源，导致连接中断
            servicesDispatch.addRef();

            services = new SWbemServices(servicesDispatch);
        } catch (JIException e) {
            throw new WMIException(e.getErrorCode(), e.getMessage(), e.getCause());
        }
        return services;
    }

    /**
     * Obtain {@link org.wmi4j.SWbemServices} object;
     * @return {@link org.wmi4j.SWbemServices} object
     * @exception IllegalStateException If call before connect to the server.
     */
    public SWbemServices getSWbemServices() {
        if(services == null) {
            throw new IllegalStateException("Please connect to the server first.");
        }
        return services;
    }

    /**
     * Disconnect from server and release resources
     * @throws WMIException
     */
    public void disconnect() throws WMIException {
        try {
            services = null;
            servicesDispatch = null;
            JISession.destroySession(session);
        } catch (JIException e) {
            throw new WMIException(e);
        }
    }

    /**
     * Verify if the connection is living.
     * @return true if living, false if dead.
     */
    public boolean isConnected() {
        if(services == null || servicesDispatch == null) {
            return false;
        }
        try {
            servicesDispatch.queryInterface(IJIDispatch.IID);
            return true;
        } catch (JIException e) {
            if(e.getErrorCode() == 0x8001FFFF) {
                logger.debug("Connection has been disconnected.");
                return false;
            } else {
              throw new IllegalStateException();
            }
        }
    }

    /**
     * securityFlag parameter of {@linkplain #connectServer(String, String, org.wmi4j.SWbemLocator.SecurityFlag, SWbemNamedValueSet) connectServer()} optional values;
     */
    public enum SecurityFlag {
        /**
         * A value of 0 for this parameter causes the call to {@linkplain #connectServer(String, String, org.wmi4j.SWbemLocator.SecurityFlag, SWbemNamedValueSet) connectServer()}
         * to return only after the connection to the server is established.
         * This could cause your program to stop responding indefinitely if the connection cannot be established.
         */
        wbemConnectFlagWhenComplete(0x0),
        /**
         * The {@linkplain #connectServer(String, String, org.wmi4j.SWbemLocator.SecurityFlag, SWbemNamedValueSet) connectServer()} call is guaranteed
         * to return in 2 minutes or less. Use this flag to prevent your program from ceasing to respond indefinitely
         * if the connection cannot be established.
         */
        wbemConnectFlagUseMaxWait(0x80);

        private int value;
        SecurityFlag(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

}
