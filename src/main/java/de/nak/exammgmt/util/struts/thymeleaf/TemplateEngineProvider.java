/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.util.struts.thymeleaf;

import org.thymeleaf.TemplateEngine;

/**
 * Contract for a TemplateEngineProvider.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public interface TemplateEngineProvider {

    TemplateEngine get();

}
