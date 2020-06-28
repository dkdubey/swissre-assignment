package com.swissre.readfile;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FileReaderTest {


    @Test(expected = NullPointerException.class)
    public void testExceptionWithWrongFileName() {
        FileReader reader = new FileReader();
        String fileName = "wrong_file_name.txt";
        Map<String, Integer> map=  reader.readFile(fileName);
    }

    @Test
    public void testReadFile() {

        FileReader reader = new FileReader();

        String fileName = "bobs_crypto.txt";
        Map<String, Integer> map=  reader.readFile(fileName);
        assertNotNull("Couldn't load file", map);

        Integer expected = map.get("BTC");
        assertEquals(expected.longValue(), 10);
    }




}
