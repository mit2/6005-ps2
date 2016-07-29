package awedoctime;

import org.junit.Assert;

/**
 * Paragraph is variant of Document ADT.
 * Paragraph is an element of Document witch can contain only text as one single string.
 * @author win8
 *
 */
public class Paragraph implements Document{
    // Paragraph's containing text
    private final String content;
    
    // Rep invariant:
    // string content.length()-1 > 0
   
    // Abstraction Function:
    // represents paragraph as a single character string.
    
    /**
     * Create Paragraph with wanted text in it.
     * @param text not empty character string
     */
    public Paragraph(String text) {
        // TODO Auto-generated constructor stub
        content = text;
    }
    
    /**
     *  Checking Rep Invariant
     *  "You should certainly call checkRep() to assert the rep invariant at the end of every operation that
     *  creates or mutates the rep – in other words, creators, producers, and mutators."
     *  
     *  Check that the rep invariant is true
     */
    private void checkRep(){
        Assert.assertTrue(content.length() > 0);
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
     * Returns a concise String representation of the Paragraph.
     * @return paragraphs summary as single character string.
     */
    @Override public String toString(){
        if(!content.isEmpty())
            return content.substring(0, content.length()/2) + "...\n";
        else
            return content;
    }

}
