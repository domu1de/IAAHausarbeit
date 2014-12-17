/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.util.struts;

import com.opensymphony.xwork2.util.NamedVariablePatternMatcher;

import java.util.regex.Pattern;

/**
 * Extended version of {@link NamedVariablePatternMatcher} that considers restful features.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class RestfulPatternMatcher extends NamedVariablePatternMatcher {

    @Override
    // TODO: gut so oder lieber kopieren?
    public CompiledPattern compilePattern(String data) {
        CompiledPattern compiledPattern = super.compilePattern(data);
        if (compiledPattern == null) {
            return null;
        }
        String newPattern = compiledPattern.getPattern().pattern() + "(?:/edit)?";
        return new CompiledPattern(Pattern.compile(newPattern), compiledPattern.getVariableNames());
    }

}
