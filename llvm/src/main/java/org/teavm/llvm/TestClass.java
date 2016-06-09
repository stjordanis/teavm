/*
 *  Copyright 2016 Alexey Andreev.
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
package org.teavm.llvm;

import java.math.BigInteger;

public class TestClass {
    private TestClass() {
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println("Hello, world!");

        BigInteger a = BigInteger.ZERO;
        BigInteger b = BigInteger.ONE;
        for (int i = 0; i < 200; ++i) {
            System.out.println(a);
            BigInteger c = a.add(b);
            a = b;
            b = c;
        }

        long end = System.currentTimeMillis();
        System.out.println("Operation took " + (end - start) + " milliseconds");
    }
}
