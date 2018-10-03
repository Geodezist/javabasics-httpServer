package ua.com.bpgdev.httpserver.resource;

import org.junit.Before;
import org.junit.Test;
import ua.com.bpgdev.httpserver.exception.ResourceNotFoundException;

import java.io.IOException;

import static org.junit.Assert.*;

public class ResourceReaderTest {

    @Before
    public void setUp()  {
    }

    @Test
    public void testGetResource() throws ResourceNotFoundException {
        ResourceReader resourceReader = new ResourceReader("resource\\webapp");
        System.err.println(resourceReader.getResource("index.html"));
        System.err.println(resourceReader.getResource("css\\style.css"));
    }
}