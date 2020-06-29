package com.swissre.file;

import com.swissre.exception.PortfolioException;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class FileReaderTest {

    private FileReader reader = new FileReader();

    @Test(expected = PortfolioException.class)
    public void shouldThrowExceptionWithWrongFileName() {
        String fileName = "wrong_file_name.txt";
        reader.readFile(fileName);
    }

    @Test(expected = PortfolioException.class)
    public void shouldThrowExceptionWithNullFileName() {
        reader.readFile(null);
    }

    @Test
    public void shouldReadFile() {
        Map<String, Integer> fileContents = reader.readFile("src/test/resources/bobs_crypto.txt");

        assertThat(fileContents, notNullValue());
        assertThat(fileContents.size(), is(3));
        assertThat(fileContents.get("BTC"), is(10));
        assertThat(fileContents.get("ETH"), is(5));
        assertThat(fileContents.get("XRP"), is(2000));
    }
}
