package com.behindmedia.tcxcorrector;

import org.junit.Test;

import java.io.InputStream;

public class StylizerTest {

    @Test
    public void transform() throws Exception {

        Stylizer stylizer = new Stylizer();

        InputStream styleSheet = StylizerTest.class.getResourceAsStream("/reorder.xslt");
        InputStream inputFile = StylizerTest.class.getResourceAsStream("/test1.xml");

        stylizer.transform(styleSheet, inputFile, System.out);

    }
}