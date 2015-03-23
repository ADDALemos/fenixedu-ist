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
/**
 * 
 */
package pt.ist.fenixedu.integration.service.services;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import org.apache.commons.beanutils.PropertyUtils;
import org.fenixedu.academic.predicate.AccessControl;
import org.fenixedu.academic.predicate.RolePredicates;
import org.fenixedu.academic.service.services.exceptions.FenixServiceException;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.DomainObject;
import pt.ist.fenixframework.core.WriteOnReadError;

/**
 * @author - Shezad Anavarali (shezad@ist.utl.pt)
 * 
 */
public class TransferDomainObjectProperty {

    @Atomic
    public static void run(DomainObject srcObject, DomainObject dstObject, String slotName) throws FenixServiceException {
        AccessControl.check(RolePredicates.MANAGER_PREDICATE);
        try {
            Object srcProperty = PropertyUtils.getSimpleProperty(srcObject, slotName);

            if (srcProperty != null && srcProperty instanceof Collection) {
                Collection srcCollection = (Collection) srcProperty;

                Object dstProperty = PropertyUtils.getSimpleProperty(dstObject, slotName);
                if (dstProperty instanceof Collection) {
                    Collection dstCollection = (Collection) dstProperty;
                    dstCollection.addAll(srcCollection);
                }

            } else {
                PropertyUtils.setSimpleProperty(dstObject, slotName, srcProperty);
            }
        } catch (InvocationTargetException e) {
            if (e.getTargetException() != null) {
                if (e.getTargetException() instanceof WriteOnReadError) {
                    throw ((WriteOnReadError) e.getTargetException());
                }
                throw new FenixServiceException(e.getTargetException());
            }
            throw new FenixServiceException(e);
        } catch (IllegalAccessException e) {
            throw new FenixServiceException(e);
        } catch (NoSuchMethodException e) {
            throw new FenixServiceException(e);
        }

    }

}