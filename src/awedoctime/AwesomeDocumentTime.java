package awedoctime;

/**
 * Awesome Document Time functions.
 * 
 * This is a required class.
 * You MUST NOT change the provided method specifications (including their
 * names, parameter types, and return types).
 * You may add additional methods, but you must not expose your rep when you 
 * write the specs of your additional methods.
 */
public class AwesomeDocumentTime {
    
    /**
     * Creates a new empty document.
     * @return a new empty document
     */
    public static Document empty() {
        //throw new UnsupportedOperationException("empty() unimplemented");
        
        return new Page(new Paragraph("empty document"));
    }
    
    /**
     * Creates a new document with one paragraph.
     * @param text paragraph text containing English-language characters and
     *             punctuation, may not contain newlines
     * @return a new paragraph
     */
    public static Document paragraph(String text) {
        //throw new UnsupportedOperationException("paragraph(..) unimplemented");
        
        return new Paragraph(text);
    }
    
    /**
     * Creates a new document with one top-level section.
     * @param heading section heading containing English-language characters
     *                and punctuation, may not contain newlines
     * @param contents contents of the section
     * @return a new section
     */
    public static Document section(String heading, Document contents) {
        //throw new UnsupportedOperationException("section(..) unimplemented");
        
        return new Section(heading, contents);
    }
}
