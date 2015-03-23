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
package pt.ist.fenixedu.integration.service.services.manager.organizationalStructureManagement;

import static org.fenixedu.academic.predicate.AccessControl.check;

import org.fenixedu.academic.domain.exceptions.DomainException;
import org.fenixedu.academic.predicate.RolePredicates;
import org.fenixedu.academic.service.services.exceptions.FenixServiceException;
import org.joda.time.YearMonthDay;

import pt.ist.fenixedu.contracts.domain.organizationalStructure.Function;
import pt.ist.fenixedu.contracts.domain.organizationalStructure.FunctionType;
import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.FenixFramework;
import pt.utl.ist.fenix.tools.util.i18n.MultiLanguageString;

public class EditFunction {

    @Atomic
    public static void run(String functionID, MultiLanguageString functionName, YearMonthDay begin, YearMonthDay end,
            FunctionType type) throws FenixServiceException, DomainException {
        check(RolePredicates.MANAGER_PREDICATE);

        Function function = (Function) FenixFramework.getDomainObject(functionID);
        if (function == null) {
            throw new FenixServiceException("error.noFunction");
        }

        function.edit(functionName, begin, end, type);
    }
}