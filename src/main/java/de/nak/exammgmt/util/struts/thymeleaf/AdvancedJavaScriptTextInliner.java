/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.util.struts.thymeleaf;

import org.thymeleaf.standard.inliner.AbstractStandardScriptingTextInliner;

/**
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class AdvancedJavaScriptTextInliner extends AbstractStandardScriptingTextInliner {

    public static final AdvancedJavaScriptTextInliner INSTANCE = new AdvancedJavaScriptTextInliner();

    private AdvancedJavaScriptTextInliner() {
        super();
    }

    @Override
    protected String formatEvaluationResult(final Object result) {
        return AdvancedJavaScriptUtils.print(result);
    }

}
