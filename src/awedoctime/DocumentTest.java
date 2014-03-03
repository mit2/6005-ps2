package awedoctime;

import static awedoctime.AwesomeDocumentTime.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests for Document.
 * 
 * You SHOULD create additional test classes to unit-test variants of Document.
 * You MAY strengthen the specs of Document and test those specs.
 */
public class DocumentTest {
    
    @Test public void testBodyWordCountEmpty() {
        Document doc = empty();
        assertEquals(0, doc.bodyWordCount());
    }
    
    @Test public void testBodyWordCountParagraph() {
        Document doc = paragraph("Hello, world!");
        assertEquals(2, doc.bodyWordCount());
    }
    
    @Test public void testBodyWordCountSectionParagraphs() {
        Document paragraphs = paragraph("Hello, world!").append(paragraph("Goodbye."));
        Document doc = section("Section One", paragraphs);
        assertEquals(3, doc.bodyWordCount());
    }
    
    // TODO write additional tests for Document
}
