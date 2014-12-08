/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.util.struts.thymeleaf;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.LocalizedTextUtil;
import org.thymeleaf.Arguments;
import org.thymeleaf.messageresolver.AbstractMessageResolver;
import org.thymeleaf.messageresolver.MessageResolution;

/**
 * Custom MessageResolver to access messages in Thymeleaf using Struts.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class StrutsMessageResolver extends AbstractMessageResolver {

    @Override
    public MessageResolution resolveMessage(Arguments arguments, String key, Object[] messageParameters) {
        String message = LocalizedTextUtil.findDefaultText(key, ActionContext.getContext().getLocale(), messageParameters);
        return new MessageResolution(message);
    }

}
