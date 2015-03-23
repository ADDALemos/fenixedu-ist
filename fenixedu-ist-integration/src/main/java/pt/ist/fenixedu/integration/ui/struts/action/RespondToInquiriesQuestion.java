/**
 * Copyright © ${project.inceptionYear} Instituto Superior Técnico
 *
 * This file is part of Fenix IST.
 *
 * Fenix IST is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Fenix IST is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Fenix IST.  If not, see <http://www.gnu.org/licenses/>.
 */
package pt.ist.fenixedu.integration.ui.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.fenixedu.academic.domain.inquiries.CurricularCourseInquiryTemplate;
import org.fenixedu.academic.domain.inquiries.StudentInquiryTemplate;
import org.fenixedu.academic.ui.struts.action.base.FenixDispatchAction;
import org.fenixedu.bennu.struts.annotations.Mapping;

import pt.ist.fenixWebFramework.servlets.filters.contentRewrite.GenericChecksumRewriter;

@Mapping(path = "/respondToInquiriesQuestion")
public class RespondToInquiriesQuestion extends FenixDispatchAction {

    public final ActionForward showQuestion(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        final StudentInquiryTemplate inquiryTemplate = CurricularCourseInquiryTemplate.getCurrentTemplate();
        request.setAttribute("inquiryTemplate", inquiryTemplate);

        return new ActionForward("/respondToInquiriesQuestion.jsp");
    }

    private ActionForward forward(final String path) {
        final ActionForward actionForward = new ActionForward();
        actionForward.setPath(path);
        actionForward.setRedirect(true);
        return actionForward;
    }

    public final ActionForward registerStudentResponseRespondLater(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        return forward("/home.do");
    }

    public final ActionForward respondNow(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        final String path = "/student/studentInquiry.do?method=showCoursesToAnswer&page=0";
        return forward(path + "&_request_checksum_="
                + GenericChecksumRewriter.calculateChecksum(request.getContextPath() + path, request.getSession(false)));
    }

}