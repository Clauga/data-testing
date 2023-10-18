package com.wcs.datatesting.firemanTest;

import com.wcs.datatesting.entity.Fire;
import com.wcs.datatesting.entity.Fireman;
import com.wcs.datatesting.repository.FireRepository;
import com.wcs.datatesting.repository.FiremanRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class FiremanTest {
    @Autowired
    private FiremanRepository firemanRepository;
    @Autowired
    private FireRepository fireRepository;

    @Test
    public void testInsertFiresAndFireman() {
        Fireman fireman = new Fireman();
        fireman.setName("Fireman1");

        Fire fire1 = new Fire();
        fire1.setLocation("Location1");
        Fire fire2 = new Fire();
        fire2.setLocation("Location2");

        fireman.addFire(fire1);
        fireman.addFire(fire2);


        firemanRepository.save(fireman);


        Long firemanId = fireman.getId();


        fireRepository.save(fire1);
        fireRepository.save(fire2);

        Fireman retrievedFireman = firemanRepository.findById(fireman.getId()).orElse(null);

        assertNotNull(retrievedFireman);
        assertEquals("Fireman1", retrievedFireman.getName());
        assertEquals(2, retrievedFireman.getFires().size());
    }

}
