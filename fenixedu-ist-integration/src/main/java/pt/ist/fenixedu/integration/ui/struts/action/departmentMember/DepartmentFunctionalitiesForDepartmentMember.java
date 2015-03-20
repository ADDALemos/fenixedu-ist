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
package pt.ist.fenixedu.integration.ui.struts.action.departmentMember;

import org.fenixedu.academic.ui.struts.action.departmentMember.DepartmentMemberApp.DepartmentMemberMessagingApp;
import org.fenixedu.bennu.struts.annotations.Mapping;
import org.fenixedu.bennu.struts.portal.StrutsFunctionality;

import pt.ist.fenixedu.integration.ui.struts.action.department.DepartmentFunctionalities;

@StrutsFunctionality(app = DepartmentMemberMessagingApp.class, path = "manage-files", titleKey = "label.manageFiles")
@Mapping(module = "departmentMember", path = "/departmentFunctionalities")
public class DepartmentFunctionalitiesForDepartmentMember extends DepartmentFunctionalities {

}