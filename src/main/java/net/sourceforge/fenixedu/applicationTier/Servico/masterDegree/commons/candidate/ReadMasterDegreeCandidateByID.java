/**
 * Copyright © 2002 Instituto Superior Técnico
 *
 * This file is part of FenixEdu Core.
 *
 * FenixEdu Core is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FenixEdu Core is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FenixEdu Core.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.fenixedu.academic.service.services.masterDegree.commons.candidate;

import org.fenixedu.academic.dto.InfoMasterDegreeCandidate;
import org.fenixedu.academic.dto.InfoMasterDegreeCandidateWithInfoPerson;
import org.fenixedu.academic.domain.MasterDegreeCandidate;
import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.FenixFramework;

public class ReadMasterDegreeCandidateByID {

    @Atomic
    public static InfoMasterDegreeCandidate run(String candidateID) {
        MasterDegreeCandidate masterDegreeCandidate = FenixFramework.getDomainObject(candidateID);
        return (masterDegreeCandidate != null) ? InfoMasterDegreeCandidateWithInfoPerson.newInfoFromDomain(masterDegreeCandidate) : null;
    }

}