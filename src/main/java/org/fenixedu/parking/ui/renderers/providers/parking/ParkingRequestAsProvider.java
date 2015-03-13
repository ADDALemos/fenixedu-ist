/**
 * Copyright © 2014 Instituto Superior Técnico
 *
 * This file is part of Fenix Parking.
 *
 * Fenix Parking is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Fenix Parking is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Fenix Parking.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.fenixedu.parking.ui.renderers.providers.parking;

import org.fenixedu.parking.domain.ParkingRequest.ParkingRequestFactory;

import pt.ist.fenixWebFramework.renderers.DataProvider;
import pt.ist.fenixWebFramework.renderers.components.converters.Converter;

public class ParkingRequestAsProvider implements DataProvider {

    @Override
    public Object provide(Object source, Object currentValue) {
        ParkingRequestFactory parkingRequestFactory = (ParkingRequestFactory) source;
        return parkingRequestFactory.getParkingParty().getSubmitAsRoles();
    }

    @Override
    public Converter getConverter() {
        return null;
    }
}
