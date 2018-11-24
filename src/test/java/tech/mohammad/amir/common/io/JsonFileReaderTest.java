package tech.mohammad.amir.common.io;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import tech.mohammad.amir.common.exception.InvalidFileException;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static tech.mohammad.amir.TestUtils.GUEST_FILE_NAME;
import static tech.mohammad.amir.TestUtils.INVALID_FILE_NAME;

@RunWith(MockitoJUnitRunner.class)
public class JsonFileReaderTest {
    @Test
    public void testValidJsonFile() {
        List<Integer> guestList = JsonFileReader.read(GUEST_FILE_NAME);

        assertNotNull(guestList);

        assertEquals(guestList.size(), 10);
    }

    @Test(expected = InvalidFileException.class)
    public void testInvalidValidJsonFile() {
        JsonFileReader.read(INVALID_FILE_NAME);
    }
}
