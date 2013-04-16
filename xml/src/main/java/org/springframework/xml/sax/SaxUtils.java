/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.xml.sax;

import java.io.Closeable;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.core.io.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.InputSource;

import javax.xml.transform.Source;

/**
 * Convenient utility methods for dealing with SAX.
 *
 * @author Arjen Poutsma
 * @since 1.0.0
 */
public abstract class SaxUtils {

    private static final Log logger = LogFactory.getLog(SaxUtils.class);

    /**
     * Creates a SAX <code>InputSource</code> from the given resource. Sets the system identifier to the resource's
     * <code>URL</code>, if available.
     *
     * @param resource the resource
     * @return the input source created from the resource
     * @throws IOException if an I/O exception occurs
     * @see InputSource#setSystemId(String)
     * @see #getSystemId(org.springframework.core.io.Resource)
     */
    public static CloseableInputSource createInputSource(Resource resource) throws IOException {
        CloseableInputSource inputSource = new CloseableInputSource(resource.getInputStream());
        inputSource.setSystemId(getSystemId(resource));
        return inputSource;
    }

    /** Retrieves the URL from the given resource as System ID. Returns <code>null</code> if it cannot be opened. */
    public static String getSystemId(Resource resource) {
        try {
            return new URI(resource.getURL().toExternalForm()).toString();
        }
        catch (IOException ex) {
            logger.debug("Could not get System ID from [" + resource + "], ex");
            return null;
        }
        catch (URISyntaxException e) {
            logger.debug("Could not get System ID from [" + resource + "], ex");
            return null;
        }
    }

    public static void closeSource(Source definitionSource) {
        if (definitionSource instanceof Closeable){
            try {
                ((Closeable)definitionSource).close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
