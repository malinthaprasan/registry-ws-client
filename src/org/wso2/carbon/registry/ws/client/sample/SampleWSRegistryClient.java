/*
*  Copyright (c) 2005-2010, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
*  WSO2 Inc. licenses this file to you under the Apache License,
*  Version 2.0 (the "License"); you may not use this file except
*  in compliance with the License.
*  You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied.  See the License for the
* specific language governing permissions and limitations
* under the License.
*/
package org.wso2.carbon.registry.ws.client.sample;

import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;
import org.wso2.carbon.base.ServerConfiguration;
import org.wso2.carbon.registry.core.Comment;
import org.wso2.carbon.registry.core.Registry;
import org.wso2.carbon.registry.core.Resource;
import org.wso2.carbon.registry.ws.client.registry.WSRegistryServiceClient;

import java.io.File;

public class SampleWSRegistryClient {

    private static ConfigurationContext configContext = null;
	
    private static final String CARBON_HOME = ".." + File.separator + ".." + File.separator;
    private static final String axis2Repo = CARBON_HOME + File.separator +"repository" +
            File.separator + "deployment" + File.separator + "client";
    private static final String axis2Conf = ServerConfiguration.getInstance().getFirstProperty("Axis2Config.clientAxis2XmlLocation");
    private static final String username = "admin";
    private static final String password = "admin";
    private static final String serverURL = "https://localhost:9443/services/";

    private static WSRegistryServiceClient initialize() throws Exception {

        System.setProperty("javax.net.ssl.trustStore", CARBON_HOME + File.separator + "repository" +
                File.separator + "resources" + File.separator + "security" + File.separator +
                "wso2carbon.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "wso2carbon");
        System.setProperty("javax.net.ssl.trustStoreType", "JKS");
        System.setProperty("carbon.repo.write.mode", "true");
        configContext = ConfigurationContextFactory.createConfigurationContextFromFileSystem(
                axis2Repo, axis2Conf);
        return new WSRegistryServiceClient(serverURL, username, password, configContext);
    }
    
	public static void main(String[] args) throws Exception {
        Registry registry = initialize();
        try {
            Resource resource = registry.newResource();
            resource.setContent("<wsp:Policy xmlns:wsp=\"http://schemas.xmlsoap.org/ws/2004/09/policy\"\n"
                    + "            xmlns:throttle=\"http://www.wso2.org/products/wso2commons/throttle\">\n"
                    + "    <throttle:MediatorThrottleAssertion>\n"
                    + "        <wsp:Policy>\n"
                    + "            <!--It's possible to define a display name for a tier to show in UIs by adding the attribute of throttle:displayName=\"xxxx\" for <throttle:ID>\n"
                    + "             element.\n"
                    + "             For ex:  <throttle:ID throttle:type=\"ROLE\" throttle:displayName=\"xxxx\">Gold</throttle:ID>\n"
                    + "                      ......\n"
                    + "             -->\n"
                    + "            <throttle:ID throttle:type=\"ROLE\">Gold</throttle:ID>\n"
                    + "            <wsp:Policy>\n"
                    + "                <throttle:Control>\n"
                    + "                    <wsp:Policy>\n"
                    + "                        <throttle:MaximumCount>20</throttle:MaximumCount>\n"
                    + "                        <throttle:UnitTime>60000</throttle:UnitTime>\n"
                    + "                        <!--It's possible to define tier level attributes as below for each tier level.For eg:Payment Plan for a tier\n"
                    + "                        <wsp:Policy>\n"
                    + "                        <throttle:Attributes>\n"
                    + "                            <throttle:Attribute1>xxxx</throttle:Attribute1>\n"
                    + "                            <throttle:Attribute2>xxxx</throttle:Attribute2>\n"
                    + "                        </throttle:Attributes>\n"
                    + "                        </wsp:Policy>\n"
                    + "                        -->\n"
                    + "                        <wsp:Policy>\n"
                    + "                            <throttle:Attributes>\n"
                    + "                                <throttle:x-wso2-BillingPlan>FREE</throttle:x-wso2-BillingPlan>\n"
                    + "                                <throttle:x-wso2-StopOnQuotaReach>true</throttle:x-wso2-StopOnQuotaReach>\n"
                    + "                            </throttle:Attributes>\n"
                    + "                        </wsp:Policy>\n"
                    + "\n"
                    + "                    </wsp:Policy>\n"
                    + "                </throttle:Control>\n"
                    + "            </wsp:Policy>\n"
                    + "        </wsp:Policy>\n"
                    + "        <wsp:Policy>\n"
                    + "            <!--It's possible to define a display name for a tier to show in UIs by adding the attribute of throttle:displayName=\"xxxx\" for <throttle:ID>\n"
                    + "            element.\n"
                    + "            For ex:  <throttle:ID throttle:type=\"ROLE\" throttle:displayName=\"xxxx\">Gold</throttle:ID>\n"
                    + "                     ......\n"
                    + "            -->\n"
                    + "            <throttle:ID throttle:type=\"ROLE\">Silver</throttle:ID>\n"
                    + "            <wsp:Policy>\n"
                    + "                <throttle:Control>\n"
                    + "                    <wsp:Policy>\n"
                    + "                        <throttle:MaximumCount>5</throttle:MaximumCount>\n"
                    + "                        <throttle:UnitTime>60000</throttle:UnitTime>\n"
                    + "                        <!--It's possible to define tier level attributes as below for each tier level.For eg:Payment Plan for a tier\n"
                    + "                        <wsp:Policy>\n"
                    + "                        <throttle:Attributes>\n"
                    + "                            <throttle:Attribute1>xxxx</throttle:Attribute1>\n"
                    + "                            <throttle:Attribute2>xxxx</throttle:Attribute2>\n"
                    + "                        </throttle:Attributes>\n"
                    + "                        </wsp:Policy>\n"
                    + "                        -->\n"
                    + "                        <wsp:Policy>\n"
                    + "                            <throttle:Attributes>\n"
                    + "                                <throttle:x-wso2-BillingPlan>FREE</throttle:x-wso2-BillingPlan>\n"
                    + "                                <throttle:x-wso2-StopOnQuotaReach>true</throttle:x-wso2-StopOnQuotaReach>\n"
                    + "                            </throttle:Attributes>\n"
                    + "                        </wsp:Policy>\n"
                    + "\n"
                    + "                    </wsp:Policy>\n"
                    + "                </throttle:Control>\n"
                    + "            </wsp:Policy>\n"
                    + "        </wsp:Policy>\n"
                    + "        <wsp:Policy>\n"
                    + "            <!--It's possible to define a display name for a tier to show in UIs by adding the attribute of throttle:displayName=\"xxxx\" for <throttle:ID>\n"
                    + "            element.\n"
                    + "            For ex:  <throttle:ID throttle:type=\"ROLE\" throttle:displayName=\"xxxx\">Gold</throttle:ID>\n"
                    + "                     ......\n"
                    + "            -->\n"
                    + "            <throttle:ID throttle:type=\"ROLE\">Bronze</throttle:ID>\n"
                    + "            <wsp:Policy>\n"
                    + "                <throttle:Control>\n"
                    + "                    <wsp:Policy>\n"
                    + "                        <throttle:MaximumCount>1</throttle:MaximumCount>\n"
                    + "                        <throttle:UnitTime>60000</throttle:UnitTime>\n"
                    + "                        <!--It's possible to define tier level attributes as below for each tier level.For eg:Payment Plan for a tier\n"
                    + "                        <wsp:Policy>\n"
                    + "                        <throttle:Attributes>\n"
                    + "                            <throttle:Attribute1>xxxx</throttle:Attribute1>\n"
                    + "                            <throttle:Attribute2>xxxx</throttle:Attribute2>\n"
                    + "                        </throttle:Attributes>\n"
                    + "                        </wsp:Policy>\n"
                    + "                        -->\n"
                    + "                        <wsp:Policy>\n"
                    + "                            <throttle:Attributes>\n"
                    + "                                <throttle:x-wso2-BillingPlan>FREE</throttle:x-wso2-BillingPlan>\n"
                    + "                                <throttle:x-wso2-StopOnQuotaReach>true</throttle:x-wso2-StopOnQuotaReach>\n"
                    + "                            </throttle:Attributes>\n"
                    + "                        </wsp:Policy>\n"
                    + "\n"
                    + "                    </wsp:Policy>\n"
                    + "                </throttle:Control>\n"
                    + "            </wsp:Policy>\n"
                    + "        </wsp:Policy>\n"
                    + "        <wsp:Policy>\n"
                    + "            <!--It's possible to define a display name for a tier to show in UIs by adding the attribute of throttle:displayName=\"xxxx\" for <throttle:ID>\n"
                    + "            element.\n"
                    + "            For ex:  <throttle:ID throttle:type=\"ROLE\" throttle:displayName=\"xxxx\">Gold</throttle:ID>\n"
                    + "                     ......\n"
                    + "            -->\n"
                    + "            <throttle:ID throttle:type=\"ROLE\">Unauthenticated</throttle:ID>\n"
                    + "            <wsp:Policy>\n"
                    + "                <throttle:Control>\n"
                    + "                    <wsp:Policy>\n"
                    + "                        <throttle:MaximumCount>10000</throttle:MaximumCount>\n"
                    + "                        <throttle:UnitTime>60000</throttle:UnitTime>\n"
                    + "                        <!--It's possible to define tier level attributes as below for each tier level.For eg:Payment Plan for a tier\n"
                    + "                        <wsp:Policy>\n"
                    + "                        <throttle:Attributes>\n"
                    + "                            <throttle:Attribute1>xxxx</throttle:Attribute1>\n"
                    + "                            <throttle:Attribute2>xxxx</throttle:Attribute2>\n"
                    + "                        </throttle:Attributes>\n"
                    + "                        </wsp:Policy>\n"
                    + "                        -->\n"
                    + "                    </wsp:Policy>\n"
                    + "                </throttle:Control>\n"
                    + "            </wsp:Policy>\n"
                    + "        </wsp:Policy>\n"
                    + "    </throttle:MediatorThrottleAssertion>\n"
                    + "</wsp:Policy>\n");

            String resourcePath = "/_system/governance/apimgt/applicationdata/tiers.xml";
            registry.put(resourcePath, resource);

            System.out.println("A resource added to: " + resourcePath);

            Resource getResource = registry.get("/_system/governance/apimgt/applicationdata/tiers.xml");
            System.out.println("Resource retrived");
            System.out.println("Printing retrieved resource content: " +
                    new String((byte[])getResource.getContent()));

        } finally {
            //Close the session
            ((WSRegistryServiceClient)registry).logut();
        }
		System.exit(0);
		
	}
}
