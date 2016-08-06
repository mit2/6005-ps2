package awedoctime;

import java.util.ArrayList;

import org.junit.Assert;

/**
 * Page is a variant of Document ADT.
 * Page is a container for paragraphs and sections to be added later.
 * Page is empty if content to be added is null.
 * Document can consist from more then one Page, in ower case Document will always have 1 Page.
 * 
 * Page Class design is consistent with AwersomeDocumentTime static creation methods.
 * @author win8
 * 
 * LESSON IS TAKEN: ALWAYS BUILD 1ST SMALLER ADT "ELEMENTS"... AS FUTURE IMPLEMENTATIONS DEPENDS ON IT.
 *
 */
public class Page implements Document{
    // Content of the Page include paragraphs and sections
    private final ArrayList<Document> content;
    
     // Rep invariant:
     // Page contain zero or more elements.
         
     // Abstraction Function:
     // represents a list of Document ADT elements.
    
    /** Create Page.
     * @param e is a Document element, required be not null 
     */
    public Page(Document e){                 // null was an easy option to create an empty Document, but null params is better avoid for NullPointerExeption to be raised.
        content = new ArrayList<Document>(); // init empty document Page without content.
        if(!e.toString().contains("Empty document")) content.add(e); // if content don't contain 'empty page' mark, add content to create regular Page.
        
    }
    
    /**
     *  Checking Rep Invariant
     *  "You should certainly call checkRep() to assert the rep invariant at the end of every operation that
     *  creates or mutates the rep – in other words, creators, producers, and mutators."
     *  
     *  Check that the rep invariant is true
     */
    private void checkRep(){
        Assert.assertTrue(content.size() >= 0); 
    }

 // Implement Object  observational equality, as this ADT is immutable.
    /**
     * Compares this Page to the specified object. The result is true if and only if the argument is
     * not null and is a Page object that represents the same sequence of the same Document elements as this object.
     * @overrides equals in Class Object
     * @param thatObj the object to compare this page against.
     * @return true if the given object represents a Page equivalent to this section, false otherwise,
     * for a non-null reference x, x.equals (null) should return false;
     */
    @Override public boolean equals(Object thatObj) {
        if(thatObj == null)return false; // checking client's input for precondition violation, throw new AssertionError(obj)
        if(!(thatObj instanceof Page))
            return false;
        else{
            Page thatPage = (Page)thatObj;
            return this.hashCode() == thatPage.hashCode();       
        }        
    }
    
    /**
     * Before implement equals implement hashCode() as it's important for hash-table handling.
     * Returns hash code for this Page content.
     * 
     */
    @Override public int hashCode(){
        // RECURSIVE IMPLEMENTATION
        // Overall strategy for this hashCode algm is: 
        //hashStrOfDigitsValue = e1.hashCode + e2.hashCode.. + eN.hashCode
        //hashStrOfDigitsValue.hashCode() to int
        String hash = "";
        for (Document e : content) {
            hash += e.hashCode();   // in case if e is instanceof Page calls recursively this hashCode() again. Also if considering 
                                    // that all Documents variants is the variants of the same type -- means recursively calling on 'it-self'
                                    // hashCode()...
        }
        return hash.hashCode();
    }
    
    
    // Adding 2 more paragraphs for toString() testing only, b/c of append() absent implementation...
    public void addTestParagraphs(){
        content.add(new Paragraph("Testing2 test test"));
        content.add(new Paragraph("Testing3 test test"));
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
     * Returns a concise String representation of the Document Page as a shortcut
     * collection of paragraphs and sections.
     * @return page summary as single character string.
     */
    @Override public String toString(){
        System.out.println("PAGE CONCISE CONTENT: \n------------------------------------------------");
        String summary = "";       
        for (Document e : content) {
           summary = summary.concat(e.toString());          
        }
        if(summary.isEmpty()) System.out.println("Empty Document!");
        //System.out.println(summary);
        return summary;
    }

}
