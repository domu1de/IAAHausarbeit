/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.util.struts.thymeleaf;

import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;
import org.thymeleaf.standard.processor.attr.StandardInlineAttrProcessor;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class CustomStandardDialect extends StandardDialect {

    @Override
    public Set<IProcessor> getProcessors() {
        final Set<IProcessor> processors = createCustomProcessorsSet();
        final Set<IProcessor> dialectAdditionalProcessors = getAdditionalProcessors();

        if (dialectAdditionalProcessors != null) {
            processors.addAll(dialectAdditionalProcessors);
        }

        return new LinkedHashSet<IProcessor>(processors);
    }

    public static Set<IProcessor> createCustomProcessorsSet() {
        final Set<IProcessor> standardProcessors = StandardDialect.createStandardProcessorsSet();
        final Set<IProcessor> processors = new LinkedHashSet<IProcessor>(35);

        for (final IProcessor standardProcessor : standardProcessors) {
            // We want to replace the StandardInlineAttrProcessor with an advanced version
            if (!(standardProcessor instanceof StandardInlineAttrProcessor)) {
                processors.add(standardProcessor);
            } else {
                processors.add(new CustomInlineAttrProcessor());
            }
        }

        return processors;
    }
}
