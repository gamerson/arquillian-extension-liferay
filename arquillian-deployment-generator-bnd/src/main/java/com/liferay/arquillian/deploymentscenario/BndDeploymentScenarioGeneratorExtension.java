/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.liferay.arquillian.deploymentscenario;

import com.liferay.arquillian.processor.NoOpArchiveApplicationProcessor;
import org.jboss.arquillian.container.test.spi.client.deployment.ApplicationArchiveProcessor;
import org.jboss.arquillian.container.test.spi.client.deployment.DeploymentScenarioGenerator;
import org.jboss.arquillian.core.spi.LoadableExtension;

/**
 * @author Carlos Sierra Andrés
 */
public class BndDeploymentScenarioGeneratorExtension implements LoadableExtension {

	@Override
	public void register(ExtensionBuilder builder) {
		builder.service(DeploymentScenarioGenerator.class, BndDeploymentScenarioGenerator.class);
        if (Validate.classExists("org.jboss.arquillian.container.osgi.OSGiApplicationArchiveProcessor")) {
            Class<ApplicationArchiveProcessor> osgiApplicationArchiveProcessorClass = null;
            try {
                osgiApplicationArchiveProcessorClass = (Class<ApplicationArchiveProcessor>) Class.forName(
                        "org.jboss.arquillian.container.osgi.OSGiApplicationArchiveProcessor");

                builder.override(ApplicationArchiveProcessor.class, osgiApplicationArchiveProcessorClass,
                        NoOpArchiveApplicationProcessor.class);
            } catch (ClassNotFoundException e) {
                //Ignored
            }

        }
	}
}
