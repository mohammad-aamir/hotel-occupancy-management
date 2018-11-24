package tech.mohammad.amir.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class GuestDataProviderTest {

    @Test
    public void testLoadData() {
        GuestDataProvider guestDataProvider = GuestDataProvider.getInstance();

        assertNotNull(guestDataProvider);

        assertNotNull(guestDataProvider.getLowPayerGuestList());
        assertEquals(4, guestDataProvider.getLowPayerGuestList().size());

        assertNotNull(guestDataProvider.getHighPayerGuestList());
        assertEquals(6, guestDataProvider.getHighPayerGuestList().size());
    }
}
