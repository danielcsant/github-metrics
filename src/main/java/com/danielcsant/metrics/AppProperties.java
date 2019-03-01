/**
 * Copyright © 2019 Daniel (danielcarrozasantana@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.danielcsant.metrics;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppProperties {

    private static Properties prop;

    private static Properties getPropertiesInstance() throws IOException {

        if (prop == null){
            prop = new Properties();
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream stream = loader.getResourceAsStream("config.properties");
            prop.load(stream);
        }

        return prop;
    }

    public static String getProperty(String prop){
        String value = "";

        try {
            value = getPropertiesInstance().getProperty(prop);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return value;
    }

}
