package awedoctime;

import org.junit.Assert;

/**
 * Paragraph is immutable.
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
    
    // Implement Object  observational equality, as this ADT is immutable.
    /**
     * Compares this Paragraph to the specified object. The result is true if and only if the argument is
     * not null and is a Paragraph object that represents the same sequence of characters as this object.
     * @overrides equals in Class Object
     * @param thatObj the object to compare this paragraph against.
     * @return true if the given object represents a Paragraph equivalent to this paragraph, false otherwise,
     * for a non-null reference x, x.equals (null) should return false;
     */
    @Override public boolean equals(Object thatObj) {
        if(thatObj == null)return false; // checking client's input for precondition violation, throw new AssertionError(obj)
        if(!(thatObj instanceof Paragraph))
            return false;
        else{
            Paragraph thatParagraph = (Paragraph)thatObj;
            return this.hashCode() == thatParagraph.hashCode();       
        }        
    }
    
    /**
     * Before implement equals implement hashCode() as it's important for hash-table handling.
     * Returns hash code for this paragraph content, using Build-in Java String Object hashCode() values.
     * (The hash value of the empty string is zero.)
     */
    @Override public int hashCode(){
        return content.hashCode();
    }
    
    /**
     * Getting paragraphs content.
     * @return
     */
    public String getContent(){
        return content;
    }
    
    // Implement Required Document Interface
    /**
     * Returns a document which has the contents of this followed by the
     * contents of other. Top-level sections in other become top-level sections
     * in the new document.
     * @param other document to append
     * @return concatenation of this and other
     */
    @Override
    public Document append(Document other) {
        // TODO Auto-generated method stub
        if(other instanceof Section || other instanceof Page) throw new AssertionError("Append operation fail: trying to contcat different  types");
        Paragraph p = (Paragraph)other;        
        return new Paragraph(content + p.getContent()); // As Paragraph is immutable ADT
    }

    @Override
    public int bodyWordCount() {
        int state = 0; // 1 is state INWORD, 0 is state OUTWORD
        int wc = 0; // wordCounter
        
        // Algm idea taken from C Language K & R
        for (int i = 0; i < content.length(); i++) {
            if(content.charAt(i) == ' ' || content.charAt(0) == '\t'){
                state = 0;
            }else if(state == 0){
                state = 1;               
                ++wc;
            }
        }
        return wc;
    }

    @Override
    public Document tableOfContents() {
        // According to specs in Interface for tableOfContents() it "returns Document Page containing one (result) paragraph for
        // every section heading." Paragraph itself not section. So it should return empty Page, telling that Paragraph has no
        // section elements in it.
        System.out.println("Return an Empty Page, b/c Paragraph has no section elements in it.");
        return new Page(new Paragraph("Empty page"));
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
            return content.substring(0, content.length()/2) + "...\n"; // /2 gives half of content
        else
            return content;
    }

}
