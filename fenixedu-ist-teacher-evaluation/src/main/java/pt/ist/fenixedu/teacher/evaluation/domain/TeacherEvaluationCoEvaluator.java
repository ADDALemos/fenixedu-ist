/**
 * Copyright © 2013 Instituto Superior Técnico
 *
 * This file is part of FenixEdu IST Teacher Evaluation.
 *
 * FenixEdu IST Teacher Evaluation is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FenixEdu IST Teacher Evaluation is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FenixEdu IST Teacher Evaluation.  If not, see <http://www.gnu.org/licenses/>.
 */
package pt.ist.fenixedu.teacher.evaluation.domain;

import org.fenixedu.bennu.core.domain.Bennu;

public abstract class TeacherEvaluationCoEvaluator extends TeacherEvaluationCoEvaluator_Base {

    public TeacherEvaluationCoEvaluator() {
        super();
        setRootDomainObject(Bennu.getInstance());
    }

    public void delete() {
        setTeacherEvaluationProcessFromEvaluee(null);
        setRootDomainObject(null);
        deleteDomainObject();
    }

    public abstract String getDescription();

}
