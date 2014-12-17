/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.util.struts;

import com.opensymphony.xwork2.config.ConfigurationManager;
import com.opensymphony.xwork2.config.RuntimeConfiguration;
import com.opensymphony.xwork2.config.entities.ActionConfig;
import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.apache.struts2.dispatcher.mapper.DefaultActionMapper;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * An custom ActionMapper that is heavily based on {@link org.apache.struts2.dispatcher.mapper.Restful2ActionMapper}.
 * It allow to define actions as restful resources and alternatively to map action methods to http methods (GET, POST, PUT; DELETE).
 *
 * TODO: copyright
 * TODO: handle null action config
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class Restful3ActionMapper extends DefaultActionMapper {

    private static final String NESTED_CONFIG_PARAM_NAME = "nested";

    public Restful3ActionMapper() {
        setSlashesInActionNames("true");
    }

    public ActionMapping getMapping(HttpServletRequest request, ConfigurationManager configManager) {
        if (!isSlashesInActionNames()) {
            throw new IllegalStateException("This action mapper requires the setting 'slashesInActionNames' to be set to 'true'");
        }
        ActionMapping mapping = super.getMapping(request, configManager);
        RuntimeConfiguration configuration = configManager.getConfiguration().getRuntimeConfiguration();

        if (mapping == null) {
            return null;
        }

        String actionName = mapping.getName();

        // Only try something if the action name is specified
        if (actionName != null && actionName.length() > 0) {

            String id = null;
            boolean nested = false;

            // TODO: strip parameters
            ActionConfig actionConfig = configuration.getActionConfig(mapping.getNamespace(), mapping.getName());

            // Apply settings from action config
            if (actionConfig != null) {
                mapping.setMethod(actionConfig.getMethodName());
                nested = "true".equalsIgnoreCase(actionConfig.getParams().get(NESTED_CONFIG_PARAM_NAME));
            }

            int lastSlashPos = actionName.lastIndexOf('/');
            if (lastSlashPos > -1 && lastSlashPos < actionName.length() - 1) {
                id = actionName.substring(lastSlashPos + 1);
            }

            // If a method hasn't been explicitly named, try to guess using ReST-style patterns
            // TODO gut so, oder lieber inside.out?
            if (mapping.getMethod() == null) {
                String method = null;
                if (id == null) {
                    // Index e.g. foo/
                    if (isGet(request)) {
                        method = getMethod(actionConfig, "get", "index");

                        // Creating a new entry on POST e.g. foo/
                    } else if (isPost(request)){
                        method = getMethod(actionConfig, "post", "create");
                    }
                } else {
                    // Viewing the form to create a new item e.g. foo/new
                    if (isGet(request) && "new".equals(id)) {
                        method = getMethod(actionConfig, "get", "editNew");

                        // Viewing the form the edit
                    } else if (isGet(request) && "edit".equals(id)) {
                        method = getMethod(actionConfig, "get", "edit");
                        // TODO: testen!
                        // Viewing an item e.g. foo/1
                    } else if (isGet(request)) {
                        method = getMethod(actionConfig, "get", "show");

                        // Updating an item e.g. foo/1
                    } else if (isPut(request)) {
                        method = getMethod(actionConfig, "put", "update");

                        // Removing an item e.g. foo/1
                    } else if (isDelete(request)) {
                        method = getMethod(actionConfig, "delete", "remove");
                    } else if (isPost(request)) {
                        method = getMethod(actionConfig, "post", null);
                    }
                }
                mapping.setMethod(method);

                if (method != null && (method.equals("editNew") || method.equals("edit"))) {
                    actionName = actionName.substring(0, lastSlashPos + 1);
                    mapping.setName(actionName);
                    lastSlashPos = actionName.length() - 1;
                }
            }

            // Try to determine parameters from the url before the action name
            int actionSlashPos = actionName.lastIndexOf('/', lastSlashPos - 1);
            if (nested && actionSlashPos > 0 && actionSlashPos < lastSlashPos) {
                String params = actionName.substring(0, actionSlashPos);
                HashMap<String,String> parameters = new HashMap<String,String>();
                try {
                    StringTokenizer st = new StringTokenizer(params, "/");
                    boolean isNameTok = true;
                    String paramName = null;
                    String paramValue;

                    while (st.hasMoreTokens()) {
                        if (isNameTok) {
                            paramName = URLDecoder.decode(st.nextToken(), "UTF-8");
                            isNameTok = false;
                        } else {
                            paramValue = URLDecoder.decode(st.nextToken(), "UTF-8");

                            if (paramName.length() > 0) {
                                parameters.put(paramName, paramValue);
                            }

                            isNameTok = true;
                        }
                    }
                    if (parameters.size() > 0) {
                        if (mapping.getParams() == null) {
                            mapping.setParams(new HashMap<String, Object>());
                        }
                        mapping.getParams().putAll(parameters);
                    }
                } catch (Exception e) {
                   // if (LOG.isWarnEnabled()) {
                     //   LOG.warn("Unable to determine parameters from the url", e);
                   // }
                }
                mapping.setName(actionName.substring(actionSlashPos + 1));
            }
        }

        return mapping;
    }

    private String getMethod(ActionConfig actionConfig, String httpMethod, String restMethod) {
        if (actionConfig != null) {
            String explicitMethod = actionConfig.getParams().get(httpMethod);
            if (explicitMethod != null) {
                return explicitMethod;
            }
        }
        return restMethod;
    }

    protected boolean isGet(HttpServletRequest request) {
        return "get".equalsIgnoreCase(request.getMethod());
    }

    protected boolean isPost(HttpServletRequest request) {
        return "post".equalsIgnoreCase(request.getMethod());
    }

    protected boolean isPut(HttpServletRequest request) {
        return "put".equalsIgnoreCase(request.getMethod());
    }

    protected boolean isDelete(HttpServletRequest request) {
        return "delete".equalsIgnoreCase(request.getMethod());
    }
}
