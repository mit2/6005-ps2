package awedoctime;

import java.util.ArrayList;

import org.junit.Assert;

/**
 * Page is immutable.
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
    private final ArrayList<Document> content = new ArrayList<Document>();
    
     // Rep invariant:
     // Page contain zero or more elements.
         
     // Abstraction Function:
     // represents a list of Document ADT elements.
    
    /** Create Page.
     * @param e is a Document element, required be not null 
     */
    public Page(Document ... elem){                 // null was an easy option to create an empty Document, but null params is better avoid for NullPointerExeption to be raised.     
           for (Document e : elem) {
               if(e.toString().toLowerCase().contains("empty")) return; // init empty document Page without content.
               else content.add(e); 
           }  
    
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
    
    /**
     * Getting page content.
     * @return
     */
    public ArrayList<Document> getContent(){
        return (ArrayList<Document>) content.clone();   // returning a shallow copy of 'content'  as Page is immutable ADT
    }
    
   
    
    // Implement Required Document Interface
    @Override
    public Document append(Document other) {
        // Append other element to the end of this Page. Page behavior similar to Section, just don't have heading.
        // As always 'smaller elements' was implementing before bigger ones.
        Section s = new Section("empty", new Paragraph("empty"));
        
        
        if(other instanceof Page){
            ArrayList<Document> tempContent1; 
            Page otherPage = (Page)other;
            if(otherPage.getContent().isEmpty() && this.getContent().isEmpty())
                return new Page(new Paragraph("Empty document"));  // return new empty page.
            else if(otherPage.getContent().isEmpty()){
                tempContent1 = (ArrayList<Document>) content.clone();
                return new Page(tempContent1.toArray(new Document[]{})); 
            }else{
                // if last top-level element in this PageContent is paragraph
                if(content.get(content.size()-1) instanceof Paragraph){
                    tempContent1 = (ArrayList<Document>) content.clone();
                    ArrayList<Document> tempContentOherPage = (ArrayList<Document>) otherPage.getContent().clone();
                    tempContent1.addAll(tempContentOherPage);
                    return new Page(tempContent1.toArray(new Document[]{}));
                }else{
                    //if last top-level element in this PageContent is section
                    ArrayList<Document> contentFromOther = otherPage.getContent();
                    s = (Section) this.getContent().get(content.size()-1);
                    for (Document e : contentFromOther) {
                        if(e instanceof Paragraph)
                            s = (Section) s.append(e);    // append one by one paragraphs to deepest subsection.                        
                    }
                    
                    
                    ArrayList<Document> tempContent2 = (ArrayList<Document>) content.clone();
                    if(s.getContent().size() > 1){  // not 'empty' new subsection, returned from recursive call
                        tempContent2.remove(tempContent2.size()-1); // remove last elem in content list
                        tempContent2.add(s);
                        for (Document e : contentFromOther) {   // if OtherPage contain sections to be appended
                            if(e instanceof Section) tempContent2.add(e);
                        }
                    }
                    
                    
                    return new Page(tempContent2.toArray(new Document[]{})); // new compound Section 
                }                
            }
        }
        else if(other instanceof Paragraph || other instanceof Section){            
            // APPEND ONLY PARAGRAPH OR SECTION CODE
            if(this.getContent().isEmpty()) return new Page(other);                    
                 
            // if last element in page content is section, recursively find deepest subsection
            if(content.get(content.size()-1) instanceof Section){
                 s = (Section) this.getContent().get(content.size()-1).append(other);  // clone content from 'this Page' and get recursive call             
            }
                    
            ArrayList<Document> tempContent2 = (ArrayList<Document>) content.clone();
            if(s.getContent().size() > 1){  // not 'empty' new subsection, returned from recursive call
                tempContent2.remove(tempContent2.size()-1); // remove last elem in content list
                tempContent2.add(s);
            }
            else
                tempContent2.add(other);    // if recursive call was didn't used  
            
            return new Page(tempContent2.toArray(new Document[]{})); // new compound Section 
            
        }else{
            System.out.println("OthererType ExeptionErrof!");            
        }
        
        return null;    // stub
        
    }

    @Override
    // Derived from Section implementation
    public int bodyWordCount() {
        int wc = 0;
        for (Document e : content) {
            wc += e.bodyWordCount();    // if e is subSeciton, recursively call itself
        }
        return wc;
    }
 
    @Override
    // Derived from Section implementation
    public Document tableOfContents() {
        int sc = 0; // section count number
        int pc = 0; // paragraph count number
        Page pg = new Page(new Paragraph("Empty document"));
        Page pg2 = new Page(new Paragraph("Empty document"));
        
        for (Document e : content) {
            if(e instanceof Paragraph) pc++;            
        }
        pg = (Page) pg.append(new Paragraph("Page TopLevel:  (" + pc + " paragraphs)"));
        
        for (Document e : content) {            
            if(e instanceof Section){
                sc ++;
                Page pageSec = (Page) e.tableOfContents(); // the easiest way to pass as param secNum to tableOfContents(), but its forbidden to change method signature by Plm specs
                                                            // BELLOW IS SOLUTION TO SOLVE IT
                //Replace all "0." for "sc." in paragraphs string...                
                for (Document elem : pageSec.getContent()) {
                    String s = ((Paragraph) elem).getContent(); // cast e to get it content, otherwise not compiler not allowed
                    //replace str
                    s = s.replaceFirst("0.", sc+".");
                    pg2 = (Page) pg2.append(new Paragraph(s));
                }
                
            }
        }
        
        return (Page) pg.append(pg2);           
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
        //System.out.println(summary.length());
        if(summary.isEmpty()) System.out.println("Empty Document!");
        
        return summary;
    }

}
