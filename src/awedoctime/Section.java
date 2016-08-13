package awedoctime;

import java.util.ArrayList;

import org.junit.Assert;

/**
 * Section is immutable.
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
     * @param elems section content as other sections or paragraphs
     */
    public Section(String title, Document ... elems){   // adding multiple Document elems thru varargs: 1 or more elems.
        if(title.isEmpty())throw new AssertionError(title);
        header = title;
        for (Document e : elems) {
            content.add(e); 
        }        
    }
    
    
    // Adding 2 more paragraphs for toString() testing only
    public void addTestParagraphs(){
        content.add(new Paragraph("Testing2 test test"));
        content.add(new Paragraph("Testing3 test test"));
    }
    
    
    
    // Adding 1 SubSection for toString() testing only
    public void addTestSubSection(){
        content.add(new Section("Subsection header", new Paragraph("Testing Subseciton paragraph")));
    }
    
    /**
     * Getting section's content.
     * @return
     */
    public ArrayList<Document> getContent(){
        return (ArrayList<Document>) content.clone(); // returning a shallow copy of 'content'  as Section is immutable ADT
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
    
 // Implement Object  observational equality, as this ADT is immutable.
    /**
     * Compares this Section to the specified object. The result is true if and only if the argument is
     * not null and is a Section object that represents the same sequence of the same Document elements as this object.
     * @overrides equals in Class Object
     * @param thatObj the object to compare this section against.
     * @return true if the given object represents a Section equivalent to this section, false otherwise,
     * for a non-null reference x, x.equals (null) should return false;
     */
    @Override public boolean equals(Object thatObj) {
        if(thatObj == null)return false; // checking client's input for precondition violation, throw new AssertionError(obj)
        if(!(thatObj instanceof Section))
            return false;
        else{
            Section thatSection = (Section)thatObj;
            return this.hashCode() == thatSection.hashCode();       
        }        
    }
    
    /**
     * Before implement equals implement hashCode() as it's important for hash-table handling.
     * Returns hash code for this section content, using Build-in Java String Object hashCode() values.
     * (The hash value of the empty string is zero.)
     */
    @Override public int hashCode(){
        // RECURSIVE IMPLEMENTATION
        // Overall strategy for this hashCode algm is: 
        //hashStrOfDigitsValue = headerHashVlaue + p1.hashCode + p2.hashCode.. + pn.hashCode
        //hashStrOfDigitsValue.hashCode() to int
        String hash = "" + header.hashCode();
        for (Document e : content) {
            hash += e.hashCode();   // in case if e is instanceof Section calls recursively this hashCode() again
        }
        return hash.hashCode();
    }
    
    // Implement Required Document Interface
    @Override
    public Document append(Document other) {
        // Append other element to the end of this Section.
        Section s = new Section("empty", new Paragraph("empty"));
        if(other instanceof Page) throw new AssertionError("Append operation fail: trying to append Page to Section");
        // if last element in section content is subsection, recursively find deepest subsection
        if(content.get(content.size()-1) instanceof Section){
            s = (Section) this.getContent().get(content.size()-1).append(other);  // get recursive call          
        }
        
        ArrayList<Document> tempContent = (ArrayList<Document>) content.clone();
        if(s.getContent().size() > 1){  // not 'empty' new subsection, returned from recursive call
            tempContent.remove(tempContent.size()-1);
            tempContent.add(s);
        }
        else tempContent.add(other);    // if recursive call was didn't used
        
        return new Section(header, tempContent.toArray(new Document[]{})); // new compound Section
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
     * Returns a concise String representation of the Document Section as a Section header and shortcut
     * collection of paragraphs. 
     * Required section header to be not empty.
     * @return section summary as single character string.
     */
    @Override public String toString(){
        String summary = header + "\n";        
        for (Document e : content) {
            if(e instanceof Section) summary += "\t";
            summary = summary.concat(e.toString());
            
        }
        
        return summary; 
    }

}
