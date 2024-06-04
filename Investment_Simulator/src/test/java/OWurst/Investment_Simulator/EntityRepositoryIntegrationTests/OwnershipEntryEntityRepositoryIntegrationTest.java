package OWurst.Investment_Simulator.EntityRepositoryIntegrationTests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Before;

import OWurst.Investment_Simulator.Repository.OwnershipEntryRepository;
import OWurst.Investment_Simulator.Entity.OwnershipEntry;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
public class OwnershipEntryEntityRepositoryIntegrationTest {
    @Autowired
    OwnershipEntryRepository ownershipEntryRepository;

    List<OwnershipEntry> ownershipEntriesUser1;
    List<OwnershipEntry> ownershipEntriesUser2;

    @Before
    public void setUp() {
        ownershipEntriesUser1 = new ArrayList<OwnershipEntry>();
        ownershipEntriesUser2 = new ArrayList<OwnershipEntry>();

        for (int i = 0; i < 10; i++) {
            OwnershipEntry ownershipEntry = new OwnershipEntry(i, 1, 10);
            ownershipEntriesUser1.add(ownershipEntry);
            ownershipEntryRepository.save(ownershipEntry);
        }

        for (int i = 0; i < 5; i++) {
            OwnershipEntry ownershipEntry = new OwnershipEntry(i, 2, 10);
            ownershipEntriesUser2.add(ownershipEntry);
            ownershipEntryRepository.save(ownershipEntry);
        }
    }

    @Test
    public void testFindByUserIdReturnsCorrectSizeList() {
        List<OwnershipEntry> ownershipEntries = ownershipEntryRepository.findByUserId(1);
        assertEquals(10, ownershipEntries.size());

        ownershipEntries = ownershipEntryRepository.findByUserId(2);
        assertEquals(5, ownershipEntries.size());
    }

    @Test
    public void testFindByUserIdReturnsCorrectList() {
        List<OwnershipEntry> ownershipEntries = ownershipEntryRepository.findByUserId(1);
        assertEquals(ownershipEntriesUser1, ownershipEntries);

        ownershipEntries = ownershipEntryRepository.findByUserId(2);
        assertEquals(ownershipEntriesUser2, ownershipEntries);
    }
}
