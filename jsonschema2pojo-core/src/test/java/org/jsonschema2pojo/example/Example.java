/**
 * Copyright © 2010-2020 Nokia
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

package org.jsonschema2pojo.example;

import com.sun.codemodel.JCodeModel;
import org.jsonschema2pojo.*;
import org.jsonschema2pojo.rules.RuleFactory;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Example {

    public static void main(String[] args) throws IOException {

        JCodeModel codeModel = new JCodeModel();
        URL source = Example.class.getResource("/schema/complex.schema.json");
        GenerationConfig config = new DefaultGenerationConfig() {
            @Override
            public boolean isGenerateBuilders() { // set config option by overriding method
                return false;
            }
            @Override
            public boolean isIncludeJsr303Annotations() {
                return true;
            }
            @Override
            public boolean isIncludeToString() {
                return false;
            }
            @Override
            public boolean isIncludeHashcodeAndEquals() {
                return false;
            }

            @Override
            public boolean isUseLongIntegers() {
                return true;
            }

            @Override
            public boolean isInitializeCollections() {
                return false;
            }

            @Override
            public boolean isUseBigDecimals() {
                return true;
            }
        };

        SchemaMapper mapper = new SchemaMapper(new RuleFactory(config, new Jackson2Annotator(config), new SchemaStore()), new SchemaGenerator());
        mapper.generate(codeModel, "ClassName", "pojos", source);

        File file = new File("c:\\myDir");
        codeModel.build(file);
    }
}
