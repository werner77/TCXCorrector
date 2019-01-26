package com.behindmedia.tcxcorrector;

import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

class Stylizer {

    void transform(InputStream stylesheet, InputStream inputStream, OutputStream outputStream) throws Exception {

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            //factory.setNamespaceAware(true);
            //factory.setValidating(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new TrimmingInputStream(inputStream));

            // Use a Transformer for output
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            StreamSource styleSource = new StreamSource(stylesheet);
            Transformer transformer = transformerFactory.newTransformer(styleSource);

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(outputStream);
            transformer.transform(source, result);
        } finally {
            stylesheet.close();
            inputStream.close();
            outputStream.close();
        }
    }
}

final class TrimmingInputStream extends InputStream {

    private boolean started = false;
    private InputStream wrapped;

    TrimmingInputStream(InputStream wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public int read() throws IOException {

        if (started) {
            return wrapped.read();
        }

        int i;
        while ((i = wrapped.read()) != -1) {
            if (!Character.isWhitespace((char)i)) {
                started = true;
                return i;
            }
        }
        return -1;
    }
}