package com.dita.dev.memoapp;

import com.dita.dev.memoapp.utility.FileUtils;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class MemoExternalStorageTests {
    private final String[] files = {
            "Adele - Hello.mp3",
            "Professional Android 4 Application Development.pdf",
            "api.txt",
            "Dance_Studio_Choreograhpy_B_boys_B_girls_hd720.mp4"
    };

    @Before
    public void Setup() {
    }

    @Test
    public void testGetGenericFileType() {
        for (String file : files) {
            assertNotNull(FileUtils.getFileType(file));
        }
    }
}
