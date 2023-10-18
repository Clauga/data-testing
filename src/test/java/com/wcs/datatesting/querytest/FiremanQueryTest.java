package com.wcs.datatesting.querytest;

import com.wcs.datatesting.entity.Fire;
import com.wcs.datatesting.entity.Fireman;
import com.wcs.datatesting.repository.FireRepository;
import com.wcs.datatesting.repository.FiremanRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FiremanQueryTest {
    @Autowired
    private FiremanRepository firemanRepository;
    @Autowired
    private FireRepository fireRepository;

    @Test
    public void testGetVeteranWithMultipleFiremen() {
        Fireman veteranFireman = createFiremanWithMostFires(5); // Crear un Fireman con 5 Fires
        Fireman anotherFireman = createFiremanWithFewerFires(2); // Crear otro Fireman con 2 Fires

        Optional<Fireman> result = firemanRepository.getVeteran();

        assertTrue(result.isPresent());
        assertEquals(veteranFireman.getId(), result.get().getId());
    }

//    @Test
//    public void testGetVeteranWithSingleFireman() {
//        Fireman singleFireman = createFiremanWithFewFires(1); // Crear un Fireman con solo 1 Fire
//
//        Optional<Fireman> result = firemanRepository.getVeteran();
//        assertEquals(singleFireman.getId(), result.get().getId());
//    }

    @Test
    public void testGetVeteranWithNoFiremen() {
        Optional<Fireman> result = firemanRepository.getVeteran();
        assert(!result.isPresent());
    }

    private Fireman createFiremanWithMostFires(int numberOfFires) {
        Fireman fireman = new Fireman();
        fireman.setName("FiremanWithMostFires");
        firemanRepository.save(fireman);

        for (int i = 0; i < numberOfFires; i++) {
            Fire fire = new Fire();
            fire.setLocation("FireLocation" + i);
            fire.setFireman(fireman);
            fireRepository.save(fire);
        }

        return fireman;
    }


    private Fireman createFiremanWithFewerFires(int numberOfFires) {
        Fireman fireman = new Fireman();
        fireman.setName("FiremanWithFewerFires");
        Set<Fire> fires = new HashSet<>();

        for (int i = 0; i < numberOfFires; i++) {
            Fire fire = new Fire();
            fire.setLocation("FireLocation" + i);
            fire.setFireman(fireman);
            fires.add(fire);
        }

        fireman.setFires(fires);

        firemanRepository.save(fireman);

        return fireman;
    }

}
