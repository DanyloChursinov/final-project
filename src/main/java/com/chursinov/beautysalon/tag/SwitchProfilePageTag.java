package com.chursinov.beautysalon.tag;

import com.chursinov.beautysalon.entity.Role;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class SwitchProfilePageTag extends TagSupport {
    private Role role;

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            String url = null;
            if (Role.ADMIN.equalsTo(role)) {
                url = "admin-profile";
            } else if (Role.MASTER.equalsTo(role)) {
                url = "master-profile";
            } else if (Role.CLIENT.equalsTo(role)) {
                url = "client-profile";
            }
            out.print(url);
        } catch (IOException e) {
            throw new JspException("Error: " + e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return SKIP_BODY;
    }
}
