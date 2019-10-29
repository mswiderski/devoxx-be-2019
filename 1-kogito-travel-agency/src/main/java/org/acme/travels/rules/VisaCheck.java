package org.acme.travels.rules;

import org.acme.travels.Traveller;
import org.acme.travels.Trip;
import org.kie.kogito.rules.DataSource;
import org.kie.kogito.rules.DataStore;
import org.kie.kogito.rules.RuleUnitMemory;

public class VisaCheck implements RuleUnitMemory {
    DataStore<Trip> trips = DataSource.createStore();
    DataStore<Traveller> travellers = DataSource.createStore();

    /**
     * @return the trips
     */
    public DataStore<Trip> getTrips() {
        return trips;
    }

    /**
     * @return the travellers
     */
    public DataStore<Traveller> getTravellers() {
        return travellers;
    }
}