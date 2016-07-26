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
        if(!e.toString().contains("empty document")) content.add(e); // if content don't contain 'empty page' mark, add content to create regular Page.
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

}
