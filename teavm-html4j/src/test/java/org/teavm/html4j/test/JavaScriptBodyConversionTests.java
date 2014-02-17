/*
 *  Copyright 2014 Alexey Andreev.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.teavm.html4j.test;

import static org.junit.Assert.*;
import net.java.html.js.JavaScriptBody;
import org.junit.Test;

/**
 *
 * @author Alexey Andreev <konsoletyper@gmail.com>
 */
public class JavaScriptBodyConversionTests {
    @Test
    public void convertsInteger() {
        assertEquals(23, returnAsInt(23));
    }

    @JavaScriptBody(args = { "value" }, body = "return value;")
    private native int returnAsInt(Object value);
}
