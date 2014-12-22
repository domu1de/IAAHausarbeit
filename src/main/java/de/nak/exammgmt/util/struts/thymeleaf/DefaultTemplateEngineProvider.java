/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.util.struts.thymeleaf;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

/**
 * Provider for the default template engine.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class DefaultTemplateEngineProvider implements TemplateEngineProvider {

    private TemplateEngine templateEngine;

    public DefaultTemplateEngineProvider() {
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();

        // HTML5 is the future!
        templateResolver.setTemplateMode("LEGACYHTML5");

        // This will convert "home" to "/WEB-INF/templates/home.html"
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF-8");

        templateResolver.setCacheable(false);
        // Set template cache TTL to 1 hour. If not set, entries would live in cache
        // until expelled by LRU
        templateResolver.setCacheTTLMs(3600000L);

        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        templateEngine.setMessageResolver(new StrutsMessageResolver());

        templateEngine.clearDialects();
        templateEngine.addDialect(new CustomStandardDialect());
        templateEngine.addDialect(new Java8TimeDialect());
    }

    @Override
    public TemplateEngine get() {
        return templateEngine;
    }

}
