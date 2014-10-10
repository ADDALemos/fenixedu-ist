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
package org.fenixedu.parking.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sourceforge.fenixedu.domain.ExecutionSemester;
import net.sourceforge.fenixedu.domain.Person;
import net.sourceforge.fenixedu.domain.Teacher;
import net.sourceforge.fenixedu.domain.degree.DegreeType;
import net.sourceforge.fenixedu.domain.person.RoleType;
import net.sourceforge.fenixedu.domain.phd.PhdIndividualProgramProcess;
import net.sourceforge.fenixedu.domain.student.Student;
import net.sourceforge.fenixedu.util.FenixStringTools;

import org.fenixedu.bennu.core.domain.Bennu;

public class ParkingRequestSearch implements Serializable {
    private ParkingRequestState parkingRequestState;

    private PartyClassification partyClassification;

    private String personName;

    private String carPlateNumber;

    private List<ParkingRequest> searchResult;

    public PartyClassification getPartyClassification() {
        return partyClassification;
    }

    public void setPartyClassification(PartyClassification partyClassification) {
        this.partyClassification = partyClassification;
    }

    public ParkingRequestState getParkingRequestState() {
        return parkingRequestState;
    }

    public void setParkingRequestState(ParkingRequestState parkingRequestState) {
        this.parkingRequestState = parkingRequestState;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getCarPlateNumber() {
        return carPlateNumber;
    }

    public void setCarPlateNumber(String carPlateNumber) {
        this.carPlateNumber = carPlateNumber;
    }

    public void doSearch() {
        final List<ParkingRequest> parkingRequests = new ArrayList<ParkingRequest>();

        if (personName != null && !personName.isEmpty()) {
            final Collection<Person> people = Person.findPerson(personName);
            for (final Person person : people) {
                final ParkingParty parkingParty = person.getParkingParty();
                if (parkingParty != null) {
                    for (final ParkingRequest parkingRequest : parkingParty.getParkingRequestsSet()) {
                        if (satisfiedPersonClassification(parkingRequest) && satisfiedRequestState(parkingRequest)
                                && satisfiedCarPlateNumber(parkingRequest)) {
                            parkingRequests.add(parkingRequest);
                        }
                    }
                }
            }
        } else if (parkingRequestState != null || partyClassification != null || carPlateNumber != null) {
            for (ParkingRequest request : Bennu.getInstance().getParkingRequestsSet()) {
                if (satisfiedPersonClassification(request) && satisfiedPersonName(request) && satisfiedRequestState(request)
                        && satisfiedCarPlateNumber(request)) {
                    parkingRequests.add(request);
                }
            }
        }

        setSearchResult(parkingRequests);
    }

    private boolean satisfiedCarPlateNumber(ParkingRequest request) {
        if (org.apache.commons.lang.StringUtils.isEmpty(getCarPlateNumber())) {
            return true;
        }
        return request.hasVehicleContainingPlateNumber(getCarPlateNumber());
    }

    private boolean satisfiedRequestState(ParkingRequest request) {
        return getParkingRequestState() == null || request.getParkingRequestState() == getParkingRequestState();
    }

    private boolean satisfiedPersonClassification(ParkingRequest request) {
        final ParkingParty parkingParty = request.getParkingParty();
        if (getPartyClassification() != null) {
            DegreeType degreeType = null;
            try {
                degreeType = DegreeType.valueOf(getPartyClassification().name());
            } catch (IllegalArgumentException e) {
            }
            if (degreeType != null && request.getRequestedAs() != null && request.getRequestedAs().equals(RoleType.STUDENT)) {
                final Student student = ((Person) parkingParty.getParty()).getStudent();
                if (degreeType.equals(DegreeType.BOLONHA_ADVANCED_SPECIALIZATION_DIPLOMA)) {
                    for (PhdIndividualProgramProcess phdIndividualProgramProcess : student.getPerson()
                            .getPhdIndividualProgramProcessesSet()) {
                        if (phdIndividualProgramProcess.getActiveState().isPhdActive()) {
                            return true;
                        }
                    }
                }
                return student.getActiveRegistrationByDegreeType(degreeType) != null;
            } else if (PartyClassification.getPartyClassification(parkingParty.getParty()) == getPartyClassification()) {
                if (getPartyClassification() == PartyClassification.TEACHER) {
                    final Teacher teacher = ((Person) parkingParty.getParty()).getTeacher();
                    return teacher == null || !teacher.isMonitor(ExecutionSemester.readActualExecutionSemester());
                }
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean satisfiedPersonName(ParkingRequest request) {
        return org.apache.commons.lang.StringUtils.isEmpty(getPersonName())
                || FenixStringTools.verifyContainsWithEquality(request.getParkingParty().getParty().getName(), getPersonName());
    }

    public List<ParkingRequest> getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(List<ParkingRequest> result) {
        this.searchResult = result;
    }
}
