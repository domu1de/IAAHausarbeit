/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.util.struts.thymeleaf;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;
import com.opensymphony.xwork2.inject.Inject;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsConstants;
import org.thymeleaf.Configuration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.ProcessingContext;
import org.thymeleaf.fragment.IFragmentSpec;
import org.thymeleaf.standard.StandardDialect;
import org.thymeleaf.standard.fragment.StandardFragment;
import org.thymeleaf.standard.fragment.StandardFragmentProcessor;
import org.thymeleaf.standard.processor.attr.StandardFragmentAttrProcessor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Custom ThymeleafResult to connect Struts and Thymeleaf.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class ThymeleafResult implements Result {

    /**
     * The result parameter name to set the name of the template to.
     * <p/>
     * IMPORTANT! Struts2 will look for this field reflectively to determine which
     * parameter is the default. This allows us to have a simplified result
     * configuration. Don't remove it!
     */
    public static final String DEFAULT_PARAM = "templateName";

    private String defaultEncoding = "UTF-8";  //TODO use
    private TemplateEngineProvider templateEngineProvider;
    private String templateName;
    private IFragmentSpec fragmentSpec;

    public ThymeleafResult() {
    }

    public ThymeleafResult(String templateName) {
        this.templateName = templateName;
    }

    @Override
    public void execute(ActionInvocation actionInvocation) throws Exception {
        TemplateEngine templateEngine = templateEngineProvider.get();

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ServletContext servletContext = ServletActionContext.getServletContext();

        Object action = actionInvocation.getAction();
        StrutsContext context = new StrutsContext(request, response, servletContext, action);
        response.setContentType("text/html");

        processTemplateName(templateEngine, context);

        templateEngine.process(templateName, context, fragmentSpec, response.getWriter());
    }

    private void processTemplateName(TemplateEngine templateEngine, StrutsContext context) {
        if (!templateName.contains("::")) {
            return;
        }

        if (!templateEngine.isInitialized()) {
            templateEngine.initialize();
        }

        Configuration configuration = templateEngine.getConfiguration();
        ProcessingContext processingContext = new ProcessingContext(context);

        StandardFragment fragment =
                StandardFragmentProcessor.computeStandardFragmentSpec(
                        configuration, processingContext, templateName, StandardDialect.PREFIX, StandardFragmentAttrProcessor.ATTR_NAME);

        templateName = fragment.getTemplateName();
        fragmentSpec = fragment.getFragmentSpec();
    }

    @Inject(StrutsConstants.STRUTS_I18N_ENCODING)
    public void setDefaultEncoding(String defaultEncoding) {
        this.defaultEncoding = defaultEncoding;
    }

    @Inject
    public void setTemplateEngineProvider(TemplateEngineProvider templateEngineProvider) {
        this.templateEngineProvider = templateEngineProvider;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

}
