/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

/**
 * ResourceLoader implementation that always loads a {@link ClassPathResource}.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class ClasspathResourceLoader extends DefaultResourceLoader {

    public ClasspathResourceLoader() {
        super();
    }

    public ClasspathResourceLoader(ClassLoader classLoader) {
        super(classLoader);
    }

    @Override
    public Resource getResource(String location) {
        return new ClassPathResource(location, getClassLoader());
    }
}
