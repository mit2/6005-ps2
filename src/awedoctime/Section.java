package awedoctime;

import java.util.ArrayList;

import org.junit.Assert;

/**
 * Section is variant of Document ADT.
 * Section is element of Document witch can contain any number of Paragraphs and SubSections.
 * NestedSection is a Section created with other Section content.
 * @author win8
 *
 */
public class Section implements Document{
    // Section text heading.
    private final String header;
    // Section content, including paragraphs and subsections with paragraphs in it.
    private final ArrayList<Document> content = new ArrayList<Document>();
    
    // Rep invariant: how it should be?
    // header.lenght()-1 > 0
    // content is list of Document elements, witch length > 0
   
    // Abstraction Function: what it should be?
    // represents the section content
    
    /**
     * Create Document Section
     * @param title section heading, not empty required
     * @param e section content as other sections or paragraphs
     */
    public Section(String title, Document e){
        header = title;
        content.add(e);
        
    }

    /**
     *  Checking Rep Invariant
     *  "You should certainly call checkRep() to assert the rep invariant at the end of every operation that
     *  creates or mutates the rep – in other words, creators, producers, and mutators."
     *  
     *  Check that the rep invariant is true
     */
    private void checkRep(){
        Assert.assertTrue(header.length() > 0);
        Assert.assertTrue(content.size() > 0);
    }
    
    
    // Implement Required Document Interface
    @Override
    public Document append(Document other) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int bodyWordCount() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Document tableOfContents() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String toLaTeX() throws ConversionException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String toMarkdown() throws ConversionException {
        // TODO Auto-generated method stub
        return null;
    }
    
    /**
     * Returns a concise String representation of the document.
     */
    @Override public String toString(){
        return header; // header + paragph1/2 + parg2/2 + ETC... do concat of strings to repres shortcat of Section.
    }

}
