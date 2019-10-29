package org.acme.travel;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import javax.inject.Inject;

import org.acme.travels.Address;
import org.acme.travels.Traveller;
import org.acme.travels.Trip;
import org.acme.travels.rules.VisaCheck;
import org.junit.jupiter.api.Test;
import org.kie.api.runtime.KieSession;
import org.kie.kogito.rules.KieRuntimeBuilder;
import org.kie.kogito.rules.RuleUnit;
import org.kie.kogito.rules.RuleUnitInstance;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class VisaRulesTest {


	@Inject
	RuleUnit<VisaCheck> unit;
	
	@Test
	public void testVisaNotRequiredRule() {
		
		assertNotNull(unit);
		
		Traveller traveller = new Traveller("John", "Doe", "john.doe@example.com", "American", new Address("main street", "Boston", "10005", "US"));
        Trip trip = new Trip("New York", "US", new Date(), new Date());

        VisaCheck v = new VisaCheck();
        RuleUnitInstance<VisaCheck> instance = unit.createInstance(v);

        v.getTrips().add(trip);
        v.getTravellers().add(traveller);

        instance.fire();
        
        assertFalse(trip.isVisaRequired());                
	}
	
	@Test
	public void testVisaRequiredRule() {
		
		assertNotNull(unit);
		
		Traveller traveller = new Traveller("Jan", "Kowalski", "jan.kowalski@example.com", "Polish", new Address("polna", "Krakow", "32000", "Poland"));
        Trip trip = new Trip("New York", "US", new Date(), new Date());

        VisaCheck v = new VisaCheck();
        RuleUnitInstance<VisaCheck> instance = unit.createInstance(v);

        v.getTrips().add(trip);
        v.getTravellers().add(traveller);

        instance.fire();
        
        assertTrue(trip.isVisaRequired());                
	}
}
