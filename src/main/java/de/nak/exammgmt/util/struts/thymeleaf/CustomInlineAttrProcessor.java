/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.util.struts.thymeleaf;

import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Element;
import org.thymeleaf.dom.NestableNode;
import org.thymeleaf.dom.Node;
import org.thymeleaf.exceptions.TemplateProcessingException;
import org.thymeleaf.processor.ProcessorResult;
import org.thymeleaf.processor.attr.AbstractAttrProcessor;
import org.thymeleaf.standard.StandardDialect;
import org.thymeleaf.standard.inliner.IStandardTextInliner;
import org.thymeleaf.standard.inliner.StandardDartTextInliner;
import org.thymeleaf.standard.inliner.StandardTextTextInliner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Own inline attr processor version, as thymeleaf allows no modification.
 *
 * COPYRIGHT: Based on {@link org.thymeleaf.standard.processor.attr.StandardInlineAttrProcessor}.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class CustomInlineAttrProcessor extends AbstractAttrProcessor {

    public static final String TEXT_INLINE = "text";
    public static final String JAVASCRIPT_INLINE = "javascript";
    public static final String DART_INLINE = "dart";
    public static final String NONE_INLINE = "none";

    public static final int ATTR_PRECEDENCE = 1000;
    public static final String ATTR_NAME = "inline";

    public CustomInlineAttrProcessor() {
        super(ATTR_NAME);
    }

    @Override
    public int getPrecedence() {
        return ATTR_PRECEDENCE;
    }

    @Override
    public final ProcessorResult processAttribute(final Arguments arguments, final Element element, final String attributeName) {
        final IStandardTextInliner textInliner = getTextInliner(element, attributeName);

        final Map<String,Object> localVariables = new HashMap<String,Object>(2, 1.0f);
        localVariables.put(StandardDialect.INLINER_LOCAL_VARIABLE, textInliner);

        // This is probably unnecessary...
        ensureChildrenArePrecomputed(element);

        element.removeAttribute(attributeName);

        return ProcessorResult.setLocalVariablesAndProcessTextNodes(localVariables, true);
    }

    protected IStandardTextInliner getTextInliner(final Element element, final String attributeName) {
        final String attributeValue = element.getAttributeValue(attributeName);

        if (attributeValue != null) {
            if (JAVASCRIPT_INLINE.equals(attributeValue.toLowerCase())) {
                return AdvancedJavaScriptTextInliner.INSTANCE;
            } else if (DART_INLINE.equals(attributeValue.toLowerCase())) {
                return StandardDartTextInliner.INSTANCE;
            } else if (TEXT_INLINE.equals(attributeValue.toLowerCase())) {
                return StandardTextTextInliner.INSTANCE;
            } else if (NONE_INLINE.equals(attributeValue.toLowerCase())) {
                return null;
            }
        }

        throw new TemplateProcessingException(
                "Cannot recognize value for \"" + attributeName + "\". Allowed values are " +
                        "\"" + TEXT_INLINE + "\", \"" + JAVASCRIPT_INLINE + "\", " +
                        "\"" + DART_INLINE + "\" and \"" + NONE_INLINE + "\"");
    }

    private static void ensureChildrenArePrecomputed(final Node node) {
        if (node != null) {
            node.setRecomputeProcessorsImmediately(true);
            if (node instanceof NestableNode) {
                final List<Node> children = ((NestableNode)node).getChildren();
                for (final Node child : children) {
                    ensureChildrenArePrecomputed(child);
                }
            }
        }
    }
}
