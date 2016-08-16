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
        assertEquals(0, pg.getContent().size());
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
        pg.addTestParagraphs();
        assertNotNull(pg);
        assertEquals("Section Header\ntest te...\nTesting2 ...\nTesting3 ...\n", pg.toString());
    }
    
    @Test public void testToStringPage_NestedElements(){
        Section s = new Section("Section Header", new Paragraph("test test test"));
        s.addTestSubSection();
        
        Page pg = new Page(s);
        pg.addTestParagraphs();
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
        assertTrue(s2.equals(s1));
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
    // PAGE
    @Test public void testPageEqual_SameTypeObjects_EqualContent(){
        Section s1 = new Section("Test1", new Paragraph("test"));
        s1.addTestSubSection();
        Section s2 = new Section("Test1", new Paragraph("test"));
        s2.addTestSubSection();
        
        Page p1 = new Page(s1);
        Page p2 = new Page(s2);
        p1.addTestParagraphs();
        p2.addTestParagraphs();
        
        assertNotNull(p1);
        assertNotNull(p2);
        assertTrue(p1.equals(p1));  // reflexive
        assertTrue(p2.equals(p2));  // reflexive
        assertTrue(p1.equals(p2));  // symmetry
        assertTrue(p2.equals(p1));
    }
    
    @Test public void testPageEqual_SameTypeObjects_DiffContent(){
        Section s1 = new Section("Test1", new Paragraph("test"));
        s1.addTestSubSection();
        Section s2 = new Section("Test2", new Paragraph("test2"));
        s2.addTestSubSection();
        
        Page p1 = new Page(s1);
        Page p2 = new Page(s2);
        p1.addTestParagraphs();
        p2.addTestParagraphs();
        
        assertNotNull(s1);
        assertNotNull(s2);
        assertTrue(p1.equals(p1));  // reflexive
        assertTrue(p2.equals(p2));  // reflexive
        assertFalse(p1.equals(p2));  // symmetry
        assertFalse(p2.equals(p1));
    }
    
    @Test public void testPageEqual_DiffTypeObjects(){
        Page p1 = new Page(new Paragraph("test"));
        Object o2 = new Object();
        assertNotNull(p1);
        assertNotNull(o2);
        assertTrue(p1.equals(p1));  // reflexive
        assertTrue(o2.equals(o2));  // reflexive
        assertFalse(p1.equals(o2));  // symmetry
        assertFalse(o2.equals(p1));
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
     
    // PAGE
     @Test public void testPageHashCode_SameObjects(){
         Page p1 = new Page(new Paragraph("test"));
         Page p2 = new Page(new Paragraph("test"));
         p1.addTestParagraphs();
         p2.addTestParagraphs();
        
         assertEquals(p1.hashCode(), p2.hashCode());
     }
     
     @Test public void testPageHashCode_DiffObjects(){
         Page p1 = new Page(new Paragraph("test1"));
         Page p2 = new Page(new Paragraph("test2"));
         p1.addTestParagraphs();
         p2.addTestParagraphs();
         assertNotEquals(p1.hashCode(), p2.hashCode());
     }
     
      @Test public void testPageHashCode_SameObjects_NestedSection(){
          Section s1 = new Section("Test1", new Paragraph("test"));
          s1.addTestSubSection();
          Section s2 = new Section("Test1", new Paragraph("test"));
          s2.addTestSubSection();
          
          Page p1 = new Page(s1);
          Page p2 = new Page(s2);
          p1.addTestParagraphs();
          p2.addTestParagraphs();
         
         assertEquals(p1.hashCode(), p2.hashCode());
     }
      
     // append() tests
     // PARAGRAPH
      @Test public void testParagraphAppend_OtherParagraph(){
          Paragraph p1 = new Paragraph("test1");
          Paragraph p2 = new Paragraph("test2");
          Paragraph p3 = new Paragraph("test1test2");
          assertTrue(p3.equals(p1.append(p2)));
      }
      
      @Test public void testParagraphAppend_Section(){
          Paragraph p1 = new Paragraph("test1");
          p1.append(new Section("Test1", new Paragraph("test")));
          /// Expected AssertionError Exception!
      }
      
      @Test public void testParagraphAppend_Page(){
          Paragraph p1 = new Paragraph("test1");
          p1.append(new Page(new Paragraph("test")));
          /// Expected AssertionError Exception!
      }
      // SECTION
      @Test public void testSectionAppend_Section(){
          // s2 append to s1
          Section s1 = new Section("Test1", new Paragraph("test"));
          Section s2 = new Section("Test2", new Paragraph("test2"));
          Section s3 = (Section) s1.append(s2);
          assertTrue(s3.getContent().contains(s2));
          assertEquals(s2.hashCode(), s3.getContent().get(1).hashCode());
         
      }
      
      @Test public void testSectionAppend_SectonWithNestedSection(){
          // s2 append to s1
          Section s1 = new Section("Test1", new Paragraph("test"));
          Section s2 = new Section("Test2", new Paragraph("test2"));
          s2.addTestSubSection();
          Section s3 = (Section) s1.append(s2);
          assertTrue(s3.getContent().contains(s2));
          assertEquals(s2.hashCode(), s3.getContent().get(1).hashCode());
         
      }
      
      @Test public void testSectionAppend_Paragraph(){
          // p2 append to s1
          Section s1 = new Section("Test1", new Paragraph("test"));
          Paragraph p2 = new Paragraph("Test2");
          
          Section s3 = (Section) s1.append(p2);
          assertTrue(s3.getContent().contains(p2));
          assertEquals(p2.hashCode(), s3.getContent().get(1).hashCode());
         
      }
      
      @Test public void testSectionAppend_Page(){
          // p2 append to s1
          Section s1 = new Section("Test1", new Paragraph("test"));
          Page p2 = new Page(new Paragraph("test"));
          
          Section s3 = (Section) s1.append(p2);
          /// Expected AssertionError Exception!
         
      }
      
      @Test public void testSectonWithNestedSectionAppend_Section(){
          // s2 append to sn1
          // s2 become last deepest nested section for sn1
          
          // create nested section
          Section sn1 = new Section("Test1", new Paragraph("test"));
          sn1.addTestParagraphs();
          sn1.addTestSubSection();
          //System.out.println(sn1.getContent().size());  // 4
          // create section to be appended
          Section s2 = new Section("TestDeepestSubsection", new Paragraph("test4"));
          // append s2 to sn1
          Section s3 = (Section) sn1.append(s2);
          // extract last subsection from s3 to check, if that subsection contain another subsection
          Section sub = (Section) s3.getContent().get(3);
          
          assertTrue(sub.getContent().contains(s2));
          assertEquals(s2.hashCode(), sub.getContent().get(1).hashCode());
         
      }
      
      @Test public void testSectonWithNestedSectionAppend_SectionWithNestedSection(){
          // s2 append to sn1
          // s2 become last deepest nested section for sn1
          
          // create nested section
          Section sn1 = new Section("Test1", new Paragraph("test"));
          sn1.addTestParagraphs();
          sn1.addTestSubSection();
          //System.out.println(sn1.getContent().size());  // 4
          // create section to be appended
          Section s2 = new Section("TestDeepestSubsection_WithNestedSection", new Paragraph("test4"));
          s2.addTestSubSection();
          // append s2 to sn1
          Section s3 = (Section) sn1.append(s2);
          // extract last subsection from s3 to check, if that subsection contain another subsection
          Section sub = (Section) s3.getContent().get(3); // getting subsection with another subsection
          
          assertTrue(sub.getContent().contains(s2));
          assertEquals(s2.hashCode(), sub.getContent().get(1).hashCode());
         
      }
      
      @Test public void testSectonWithNestedSectionAppend_Paragraph(){
          // s2 append to sn1
          // s2 become last deepest nested section for sn1
          
          // create nested section
          Section sn1 = new Section("Test1", new Paragraph("test"));
          sn1.addTestParagraphs();
          sn1.addTestSubSection();
          //System.out.println(sn1.getContent().size());  // 4
          // create paragraph to be appended
          Paragraph p1 = new Paragraph("Paragraph_test1");
          // append p1 to sn1
          Section s3 = (Section) sn1.append(p1);
          // extract last subsection from s3 to check, if that subsection contain appended paragraph
          Section sub = (Section) s3.getContent().get(3);
          
          assertTrue(sub.getContent().contains(p1));
          assertEquals(p1.hashCode(), sub.getContent().get(1).hashCode());
          System.out.println(sub.toString());
         
      }
      
      @Test public void testSectonWithNestedSectionAppend_Page(){          
          // create nested section
          Section sn1 = new Section("Test1", new Paragraph("test"));
          sn1.addTestParagraphs();
          sn1.addTestSubSection();
          Page pg = new Page(new Paragraph("test"));
          
          Section s3 = (Section) sn1.append(pg);
          /// Expected AssertionError Exception!
         
      }
      // PAGE
      @Test public void testEmptyPageAppend_EmptyPage(){
          Page p1 = new Page(new Paragraph("Empty document"));
          Page p2 = new Page(new Paragraph("Empty document"));
          Page p3 = (Page) p1.append(p2);
          
          assertTrue(p3.getContent().isEmpty());
      }
      
      @Test public void testEmptyPageAppend_Pagargraph(){
          Page p1 = new Page(new Paragraph("Empty document"));
          Paragraph p2 = new Paragraph("Test1");
          Page p3 = (Page) p1.append(p2);
          
          assertEquals(1, p3.getContent().size());
          assertEquals(p2.hashCode(), p3.getContent().get(0).hashCode());
      }
      
      @Test public void testEmptyPageAppend_Section_NestedSection(){
          Page p1 = new Page(new Paragraph("Empty document"));
          Section sn1 = new Section("Test1", new Paragraph("test"));
          sn1.addTestParagraphs();
          sn1.addTestSubSection();
          Page p3 = (Page) p1.append(sn1);
          
          assertEquals(1, p3.getContent().size());
          assertEquals(sn1.hashCode(), p3.getContent().get(0).hashCode());
      }
      
      @Test public void testPageWithOnlyPagargraphsAppend_EmptyPage(){
          Page p1 = new Page(new Paragraph("Paragraph1"));          
          Page p2 = new Page(new Paragraph("Empty document"));
          Page p3 = (Page) p1.append(p2);
         
          assertEquals(1, p3.getContent().size());
          assertEquals(p1.hashCode(), p3.hashCode());
      }
      
      @Test public void testPageWithOnlyPagargraphsAppend_PageWithOnlyParagraphs(){
          Page p1 = new Page(new Paragraph("Paragraph1"));
          p1.addTestParagraphs();
          Page p2 = new Page(new Paragraph("Paragraph4"));
          p2.addTestParagraphs();
          
          Page p3 = (Page) p1.append(p2);
         
          assertEquals(6, p3.getContent().size());
          // test the order how elems will appear in the new Page
          assertEquals(p3.getContent().get(2).hashCode(), p1.getContent().get(2).hashCode());
          assertEquals(p3.getContent().get(5).hashCode(), p2.getContent().get(2).hashCode());
      }
      
      @Test public void testPageWithOnlyPagargraphsAppend_PageWithSection_NestedSection(){
          Page p1 = new Page(new Paragraph("Paragraph1"));
          p1.addTestParagraphs();
          Section s = new Section("Test1", new Paragraph("test"));
          s.addTestParagraphs();
          s.addTestSubSection();
          
          Page p3 = (Page) p1.append(s);
         
          assertEquals(4, p3.getContent().size());
          // test the order how elems will appear in the new Page
          assertEquals(p3.getContent().get(3).hashCode(), s.hashCode());
      }
      
      // Will append Paragraph to the lowest NestedSection in last Page content element, if it is a section
      @Test public void testPageWithSectionNestedSectionAppend_PageWithParagraphOrSectionOrBouth(){
          Page p1 = new Page(new Paragraph("Test"));          
          Section sn = new Section("Test1", new Paragraph("test"));
          sn.addTestParagraphs();
          sn.addTestSubSection();
          Page p2 = (Page) p1.append(sn);   // append nestedSection to Page
          
          Page pg = new Page(new Paragraph("Test to deepest subsection")); 
          Section pgs = new Section("Test", new Paragraph("Test"));
          Page pg2 = (Page) pg.append(pgs);
          
          Page p3 = (Page) p2.append(pg2);
          
          assertEquals(3, p3.getContent().size());
          // test the order how elems will appear in the new Page
          /// CHECK HOW THIS TEST IS COVERED IN CODE, I MEAN WHITCH BRANCH AND SO ON.
          Section s = (Section) p3.getContent().get(1);     // last elem is section
          Section sub = (Section) s.getContent().get(3); // last elem is section also
          Paragraph ph = new Paragraph("Test to deepest subsection");
          assertEquals(ph.hashCode(), sub.getContent().get(1).hashCode());
          
          assertEquals(pgs.hashCode(), p3.getContent().get(2).hashCode());    // test if appended top-level section(s)         
      }
      
      // bodyWordCount() tests
      // PARAGRAPH
      @Test public void testParagraphBodyWordCount_OneWord(){
          Paragraph p = new Paragraph("test");
          assertEquals(1, p.bodyWordCount());
      }
      
      @Test public void testParagraphBodyWordCount_TwoWords(){
          Paragraph p = new Paragraph("test test");
          assertEquals(2, p.bodyWordCount());
      }
      
      @Test public void testParagraphBodyWordCount_MultipleWords(){
          Paragraph p = new Paragraph("test test test aka waka one");
          assertEquals(6, p.bodyWordCount());
      }
      // SECTION
      @Test public void testSectionBodyWordCount_OneParagraph(){
          Section s = new Section("test", new Paragraph("test it"));
          assertEquals(2, s.bodyWordCount());
      }
      
      @Test public void testSectionBodyWordCount_MultipleParagraphs(){
          Section s = new Section("test", new Paragraph("test it"));
          s.addTestParagraphs();
          assertEquals(8, s.bodyWordCount());
      }
      
      @Test public void testSectionBodyWordCount_MultipleParagraphsWithSubsection(){
          Section s = new Section("test", new Paragraph("test it"));
          s.addTestParagraphs();
          s.addTestSubSection();
          assertEquals(11, s.bodyWordCount());
      }
      // PAGE
      @Test public void testPageBodyWordCount_EmptyPage(){
          Page p = new Page(new Paragraph("Empty page"));
          assertEquals(0, p.bodyWordCount());
      }
      
      @Test public void testPageBodyWordCount_OneParagraph(){
          Page p = new Page(new Paragraph("test it"));
          assertEquals(2, p.bodyWordCount());
      }
      
      @Test public void testPageBodyWordCount_MultipleParagraphs(){
          Page p = new Page(new Paragraph("test it."));
          Page p1 = (Page) p.append(new Paragraph("aka waka."));
          Page p2 = (Page) p1.append(new Paragraph("aka waka one."));
          assertEquals(7, p2.bodyWordCount());
      }
      
      @Test public void testPageBodyWordCount_MultipleParagraphsWithSubsection(){
          Page p = new Page(new Paragraph("test it."));
          Page p1 = (Page) p.append(new Paragraph("aka waka."));
          Page p2 = (Page) p1.append(new Paragraph("aka waka one."));
          
          Section s = new Section("test", new Paragraph("test it"));
          s.addTestSubSection();
          
          Page p3 = (Page) p2.append(s);
          assertEquals(12, p3.bodyWordCount());
      }
      
    
}
