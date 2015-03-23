/**
 * Copyright © 2013 Instituto Superior Técnico
 *
 * This file is part of FenixEdu IST Integration.
 *
 * FenixEdu IST Integration is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FenixEdu IST Integration is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FenixEdu IST Integration.  If not, see <http://www.gnu.org/licenses/>.
 */
package pt.ist.fenixedu.integration.service.services.manager.organizationalStructureManagement;

import static org.fenixedu.academic.predicate.AccessControl.check;

import org.fenixedu.academic.domain.exceptions.DomainException;
import org.fenixedu.academic.predicate.RolePredicates;
import org.fenixedu.academic.service.services.exceptions.FenixServiceException;

import pt.ist.fenixedu.contracts.domain.organizationalStructure.Function;
import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.FenixFramework;

public class AddParentInherentFunction {

    @Atomic
    public static void run(String functionID, String parentInherentFunctionID) throws FenixServiceException, DomainException {
        check(RolePredicates.MANAGER_PREDICATE);

        Function parentInherentFunction = (Function) FenixFramework.getDomainObject(parentInherentFunctionID);

        if (parentInherentFunction == null) {
            throw new FenixServiceException("error.no.parentInherentFunction");
        }

        Function function = (Function) FenixFramework.getDomainObject(functionID);
        if (function == null) {
            throw new FenixServiceException("error.noFunction");
        }

        function.addParentInherentFunction(parentInherentFunction);
    }
}