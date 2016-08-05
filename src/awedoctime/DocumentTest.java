package awedoctime;

import static awedoctime.AwesomeDocumentTime.*;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for Document.
 * 
 * Recursive data type definition for Document ADT:
 * Document = Page(content:Document) + Paragraph(text:String) + Section(header:String, content:Document)
 * 
 * The Page()container initially is empty. NestedSection is a Section created with section content.
 * You SHOULD create additional test classes to unit-test variants of Document.
 * You MAY strengthen the specs of Document and test those specs.
 * 
 * NOTE: The one problem that you can't predict how particular Document ADT Design will affect future operations implementations...
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
    // toString() tests for Document elements.
    // PARAGRAPH
    @Test public void testToStringParagraph_EmptyContent(){
        Paragraph p = new Paragraph("");
        assertNotNull(p);
        assertEquals("", p.toString());
    }
    
    @Test public void testToStringParagraph_ContainText(){
        Paragraph p = new Paragraph("test test test"); // 14 charString
        assertNotNull(p);
        assertEquals("test te...\n", p.toString());
    }
    
    // SECTION
    @Test public void testToStringSection_EmptyContent(){
        Section s = new Section("Section Header", new Paragraph(""));
        assertNotNull(s);
        assertEquals("Section Header\n", s.toString());
        
        
    }
    
    @Test public void testToStringSection_OneParagraph(){
        Section s = new Section("Section Header", new Paragraph("test test test"));
        assertNotNull(s);
        assertEquals("Section Header\ntest te...\n", s.toString());
        
        
    }
    
    @Test public void testToStringSection_MultipleParagraphs(){
        Section s = new Section("Section Header", new Paragraph("test test test"));
        s.addTestParagraphs();
        assertNotNull(s);
        assertEquals("Section Header\ntest te...\nTesting2 ...\nTesting3 ...\n", s.toString());
        
        
    }
    
    @Test public void testToStringSection_NestedSection(){
        Section s = new Section("Section Header", new Paragraph("test test test"));
        s.addTestSubSection();
        assertNotNull(s);
        assertEquals("Section Header\ntest te...\n\tSubsection header\nTesting Subsec...\n", s.toString());
        
        
    }
    
    // PAGE
    @Test public void testToStringPage_EmptyContent(){
        Page pg = new Page(new Paragraph("Empty document"));
        assertNotNull(pg);
        assertEquals("", pg.toString());
    }
    
    @Test public void testToStringPage_SingleParagraph(){
        Page pg = new Page(new Paragraph("test test test"));
        assertNotNull(pg);
        assertEquals("test te...\n", pg.toString());
    }
    
    @Test public void testToStringPage_SingleSection(){
        Page pg = new Page(new Section("Section Header", new Paragraph("test test test")));
        assertNotNull(pg);
        assertEquals("Section Header\ntest te...\n", pg.toString());
    }
    
    @Test public void testToStringPage_MultipleElements(){
        Page pg = new Page(new Section("Section Header", new Paragraph("test test test")));
        pg.addParagraphs();
        assertNotNull(pg);
        assertEquals("Section Header\ntest te...\nTesting2 ...\nTesting3 ...\n", pg.toString());
    }
    
    @Test public void testToStringPage_NestedElements(){
        Section s = new Section("Section Header", new Paragraph("test test test"));
        s.addTestSubSection();
        
        Page pg = new Page(s);
        pg.addParagraphs();
        assertNotNull(pg);
        assertEquals("Section Header\ntest te...\n\tSubsection header\nTesting Subsec...\nTesting2 ...\nTesting3 ...\n", pg.toString());
    }
    
    /** equals() & hashCode() tests for Document elements. */
    // equal() tests
    // PARAGRAPH
    @Test public void testParagraphEqual_SameTypeObjects_Empty(){
        Paragraph p1 = new Paragraph("");
        Paragraph p2 = new Paragraph("");
        assertNotNull(p1);
        assertNotNull(p2);
        assertTrue(p1.equals(p1));  // reflexive
        assertTrue(p2.equals(p2));  // reflexive
        assertTrue(p1.equals(p2));  // symmetry
        assertTrue(p2.equals(p1));
    }
    
    @Test public void testParagraphEqual_SameTypeObjects_EqualContent(){
        Paragraph p1 = new Paragraph("test");
        Paragraph p2 = new Paragraph("test");
        assertNotNull(p1);
        assertNotNull(p2);
        assertTrue(p1.equals(p1));  // reflexive
        assertTrue(p2.equals(p2));  // reflexive
        assertTrue(p1.equals(p2));  // symmetry
        assertTrue(p2.equals(p1));
    }
    
    @Test public void testParagraphEqual_SameTypeObjects_DiffContent(){
        Paragraph p1 = new Paragraph("test1");
        Paragraph p2 = new Paragraph("test2");
        assertNotNull(p1);
        assertNotNull(p2);
        assertTrue(p1.equals(p1));  // reflexive
        assertTrue(p2.equals(p2));  // reflexive
        assertFalse(p1.equals(p2));  // symmetry
        assertFalse(p2.equals(p1));
    }
    
    @Test public void testParagraphEqual_DiffTypeObjects(){
        Paragraph p1 = new Paragraph("test1");
        Object o2 = new Object();
        assertNotNull(p1);
        assertNotNull(o2);
        assertTrue(p1.equals(p1));  // reflexive
        assertTrue(o2.equals(o2));  // reflexive
        assertFalse(p1.equals(o2));  // symmetry
        assertFalse(o2.equals(p1));
    }
    // SECTION    
    @Test public void testSectionEqual_SameTypeObjects_EqualContent(){
        Section s1 = new Section("Test1", new Paragraph("test"));
        Section s2 = new Section("Test1", new Paragraph("test"));
        assertNotNull(s1);
        assertNotNull(s2);
        assertTrue(s1.equals(s1));  // reflexive
        assertTrue(s2.equals(s2));  // reflexive
        assertTrue(s1.equals(s2));  // symmetry
        assertTrue(s2.equals(s2));
    }
    
    @Test public void testSectionEqual_SameTypeObjects_DiffContent(){
        Section s1 = new Section("Test1", new Paragraph("test"));
        Section s2 = new Section("Test2", new Paragraph("test2"));
        assertNotNull(s1);
        assertNotNull(s2);
        assertTrue(s1.equals(s1));  // reflexive
        assertTrue(s2.equals(s2));  // reflexive
        assertFalse(s1.equals(s2));  // symmetry
        assertFalse(s2.equals(s1));
    }
    
    @Test public void testSectionEqual_DiffTypeObjects(){
        Section s1 = new Section("Test1", new Paragraph("test"));
        Object o2 = new Object();
        assertNotNull(s1);
        assertNotNull(o2);
        assertTrue(s1.equals(s1));  // reflexive
        assertTrue(o2.equals(o2));  // reflexive
        assertFalse(s1.equals(o2));  // symmetry
        assertFalse(o2.equals(s1));
    }
    
    
    // hashCode() tests
    // PARAGRAPH
    @Test public void testParagraphHashCode_SameObjects(){
        Paragraph p1 = new Paragraph("test");
        Paragraph p2 = new Paragraph("test");
        assertEquals(p1.hashCode(), p2.hashCode());
      
    }
    
    @Test public void testParagraphHashCode_DiffObjects(){
        Paragraph p1 = new Paragraph("test1");
        Paragraph p2 = new Paragraph("test2");
        assertNotEquals(p1.hashCode(), p2.hashCode());
      
    }
    
    // SECTION
    @Test public void testSectionHashCode_SameObjects(){
        Section s1 = new Section("Test1", new Paragraph("test"));
        Section s2 = new Section("Test1", new Paragraph("test"));
        assertEquals(s1.hashCode(), s2.hashCode());
    }
    
    @Test public void testSectionHashCode_DiffObjects(){
        Section s1 = new Section("Test1", new Paragraph("test1"));
        Section s2 = new Section("Test2", new Paragraph("test2"));
        assertNotEquals(s1.hashCode(), s2.hashCode());
    }
    
     @Test public void testSectionHashCode_SameObjects_NestedSection(){
        Section s1 = new Section("Test1", new Paragraph("test"));
        s1.addTestSubSection();
        Section s2 = new Section("Test1", new Paragraph("test"));
        s2.addTestSubSection();
        assertEquals(s1.hashCode(), s2.hashCode());
    }
    
    
}
