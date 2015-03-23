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
package pt.ist.fenixedu.cmscomponents.domain.unit.components;

import org.fenixedu.academic.domain.organizationalStructure.Unit;
import org.fenixedu.cms.domain.Page;
import org.fenixedu.cms.domain.Site;
import org.fenixedu.cms.domain.component.CMSComponent;
import org.fenixedu.cms.exceptions.ResourceNotFoundException;

import pt.ist.fenixedu.cmscomponents.domain.unit.UnitSite;

public abstract class UnitSiteComponent implements CMSComponent {

    protected Unit unit(Page page) {
        if (page.getSite() instanceof UnitSite) {
            return ((UnitSite) page.getSite()).getUnit();
        }
        throw new ResourceNotFoundException();
    }

    public static boolean supportsSite(Site site) {
        return site instanceof UnitSite;
    }

}
