/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.util;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.util.Assert;

/**
 * Classpath-based version of {@link ReloadableResourceBundleMessageSource}.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class ReloadableClasspathResourceBundleMessageSource extends ReloadableResourceBundleMessageSource implements ResourceLoaderAware {

    @Override
    public void setBasenames(String... basenames) {
        String[] classpathBaseNames = null;

        if (basenames != null) {
            classpathBaseNames = new String[basenames.length];
            for (int i = 0; i < basenames.length; i++) {
                String basename = basenames[i];
                Assert.hasText(basename, "Basename must not be empty");
                classpathBaseNames[i] = "classpath:" + basename.trim();
            }
        }

        super.setBasenames(classpathBaseNames);
    }
}
