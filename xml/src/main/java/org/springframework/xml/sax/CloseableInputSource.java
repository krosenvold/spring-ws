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

import org.xml.sax.InputSource;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Kristian Rosenvold
 */
public class CloseableInputSource extends InputSource implements Closeable {
    private final InputStream inputStream;

    public CloseableInputSource(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void close() throws IOException {
        inputStream.close();
    }
}
