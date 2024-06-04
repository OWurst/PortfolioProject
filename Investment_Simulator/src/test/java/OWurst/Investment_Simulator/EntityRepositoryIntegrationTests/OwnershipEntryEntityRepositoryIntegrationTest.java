package OWurst.Investment_Simulator.EntityRepositoryIntegrationTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

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

    @Test
    public void testFindOneByOwnershipId() {
        int firstOwnershipId = ownershipEntriesUser1.get(0).getOwnershipId();
        OwnershipEntry ownershipEntry = ownershipEntryRepository.findOneByOwnershipId(firstOwnershipId);
        assertEquals(ownershipEntriesUser1.get(0), ownershipEntry);
    }

    @Test
    public void testFindOneByUserIdAndStockId() {
        OwnershipEntry expectedOwnershipEntry = ownershipEntriesUser1.get(0);
        OwnershipEntry actualOwnershipEntry = ownershipEntryRepository
                .findOneByUserIdAndStockId(expectedOwnershipEntry.getUserId(), expectedOwnershipEntry.getStockId());
        assertEquals(ownershipEntriesUser1.get(0), actualOwnershipEntry);

        expectedOwnershipEntry = ownershipEntriesUser2.get(0);
        actualOwnershipEntry = ownershipEntryRepository
                .findOneByUserIdAndStockId(expectedOwnershipEntry.getUserId(), expectedOwnershipEntry.getStockId());
    }

    @Test
    public void testFindOneByUserIdAndStockIdReturnsNullIfNoMatchOnStockId() {
        OwnershipEntry ownershipEntry = ownershipEntryRepository.findOneByUserIdAndStockId(1, 100);
        assertNull(ownershipEntry);
    }

    @Test
    public void testFindOneByUserIdAndStockIdReturnsNullIfNoMatchOnUserId() {
        OwnershipEntry ownershipEntry = ownershipEntryRepository.findOneByUserIdAndStockId(100, 1);
        assertNull(ownershipEntry);
    }

    @Test
    public void testFindOneByUserIdAndStockIdReturnsNullIfNoMatchOnUserIdAndStockId() {
        OwnershipEntry ownershipEntry = ownershipEntryRepository.findOneByUserIdAndStockId(100, 100);
        assertNull(ownershipEntry);
    }

    @Test
    public void testFindOneByOwnershipIdReturnsNullIfNoMatch() {
        OwnershipEntry ownershipEntry = ownershipEntryRepository.findOneByOwnershipId(100);
        assertNull(ownershipEntry);
    }

    @Test
    public void testFindByUserIdReturnsEmptyListIfNoMatch() {
        List<OwnershipEntry> ownershipEntries = ownershipEntryRepository.findByUserId(100);
        assertEquals(0, ownershipEntries.size());
    }

    @Test
    public void testDeleteElements() {
        ownershipEntryRepository.deleteAll();
        List<OwnershipEntry> ownershipEntries = ownershipEntryRepository.findAll();
        assertEquals(0, ownershipEntries.size());
    }

    @Test
    public void testDeleteFirstEntry() {
        OwnershipEntry ownershipEntry = ownershipEntriesUser1.get(0);
        ownershipEntryRepository.delete(ownershipEntry);
        List<OwnershipEntry> ownershipEntries = ownershipEntryRepository.findAll();
        assertEquals(14, ownershipEntries.size());
    }

    @Test
    public void testDeleteSpecificEntry() {
        List<OwnershipEntry> entries = ownershipEntryRepository.findByUserId(1);
        OwnershipEntry ownershipEntry = entries.get(5);
        ownershipEntryRepository.delete(ownershipEntry);

        List<OwnershipEntry> ownershipEntries = ownershipEntryRepository.findByUserId(1);
        assertEquals(9, ownershipEntries.size());
        OwnershipEntry ownershipEntry2 = ownershipEntries.get(5);
        assertNotEquals(ownershipEntry, ownershipEntry2);
    }

    @Test
    public void testDeleteEntryWithSpecificId() {
        List<OwnershipEntry> allEntries = ownershipEntryRepository.findAll();
        OwnershipEntry ownershipEntry = allEntries.get(5);
        int id = ownershipEntry.getOwnershipId();

        ownershipEntryRepository.delete(ownershipEntry);
        OwnershipEntry deletedEntry = ownershipEntryRepository.findOneByOwnershipId(id);
        assertNull(deletedEntry);
    }

    @Test
    public void testAddStockToUser() {
        OwnershipEntry ownershipEntry = new OwnershipEntry(11, 1, 10);
        ownershipEntryRepository.save(ownershipEntry);
        List<OwnershipEntry> ownershipEntries = ownershipEntryRepository.findByUserId(1);
        assertEquals(11, ownershipEntries.size());
    }

    @Test
    public void testUpdateOwnershipEntry() {
        OwnershipEntry ownershipEntry = ownershipEntriesUser1.get(0);
        ownershipEntry.setNumShares(20);
        ownershipEntryRepository.save(ownershipEntry);

        OwnershipEntry updatedOwnershipEntry = ownershipEntryRepository
                .findOneByOwnershipId(ownershipEntry.getOwnershipId());
        assertEquals(20, updatedOwnershipEntry.getNumShares());
    }

    @Test
    public void testUpdateOnIncrementShares() {
        OwnershipEntry ownershipEntry = ownershipEntriesUser1.get(0);
        ownershipEntry.incrementShares(10);
        ownershipEntryRepository.save(ownershipEntry);

        OwnershipEntry updatedOwnershipEntry = ownershipEntryRepository
                .findOneByOwnershipId(ownershipEntry.getOwnershipId());
        assertEquals(20, updatedOwnershipEntry.getNumShares());
    }

    @Test
    public void testUpdateOnDecrementShares() {
        OwnershipEntry ownershipEntry = ownershipEntriesUser1.get(0);
        ownershipEntry.decrementShares(5);
        ownershipEntryRepository.save(ownershipEntry);

        OwnershipEntry updatedOwnershipEntry = ownershipEntryRepository
                .findOneByOwnershipId(ownershipEntry.getOwnershipId());
        assertEquals(5, updatedOwnershipEntry.getNumShares());
    }
}
