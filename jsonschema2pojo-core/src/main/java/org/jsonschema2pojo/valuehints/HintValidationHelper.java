package org.jsonschema2pojo.valuehints;

import com.fasterxml.jackson.databind.JsonNode;
import com.sun.codemodel.JFieldVar;
import org.jsonschema2pojo.rules.TypeRule;

public class HintValidationHelper {

    public static String JRG_PROPERTIES = "jrg.properties";
    public static String JRG_OPTIONS    = "options";
    public static String JRG_ITERATOR   = "iterator";
    public static String JRG_DECIMAL    = "decimal";
    public static String JRG_REGEX      = "regex";
    public static String JRG_LENGTH     = "length";
    public static String JRG_PREFIX     = "prefix";
    public static String JRG_POSTFIX    = "postfix";
    public static String JRG_RANGE      = "range";

    public static void checkHintAppliance(JsonNode node) throws RuntimeException {

        JsonNode jrgProps = node.get(JRG_PROPERTIES);
        if ("number".equals(TypeRule.getTypeName(node)) && (jrgProps.has(JRG_LENGTH) || jrgProps.has(JRG_REGEX)))
            throw new RuntimeException("jrg.properties ERROR. Cannot use length or regex with type number. " +jrgProps);

        else if ("string".equals(TypeRule.getTypeName(node)) && jrgProps.has(JRG_RANGE))
            throw new RuntimeException("jrg.properties ERROR. Cannot use range with type string. "+jrgProps);

        else if ("string".equals(TypeRule.getTypeName(node)) && jrgProps.has(JRG_DECIMAL))
            throw new RuntimeException("jrg.properties ERROR. Cannot use decimal with type string. "+jrgProps);
    }
}
