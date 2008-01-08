/**
 * 
 */
package org.wikimodel.graph;

import org.wikimodel.graph.util.ISectionListener;
import org.wikimodel.graph.util.SectionBuilder;

import junit.framework.TestCase;

/**
 * @author kotelnikov
 */
public class SectionBuilderTest extends TestCase {

    final StringBuffer fBuf = new StringBuffer();

    SectionBuilder fBuilder;

    /**
     * @param name
     */
    public SectionBuilderTest(String name) {
        super(name);
    }

    private void check(String control) {
        assertEquals(control, fBuf.toString());
        fBuf.delete(0, fBuf.length());
    }

    @Override
    protected void setUp() throws Exception {
        ISectionListener listener = new ISectionListener() {

            public void beginLevel(int depth) {
                fBuf.append("<level" + depth + ">");
            }

            public void beginSection(int depth, int level) {
                fBuf.append("<s>");
            }

            public void beginSectionContent(int depth, int level) {
                fBuf.append("<c>");
            }

            public void beginSectionHeader(int depth, int level) {
                fBuf.append("<h" + level + ">");
            }

            public void endLevel(int depth) {
                fBuf.append("</level" + depth + ">");
            }

            public void endSection(int depth, int level) {
                fBuf.append("</s>");
            }

            public void endSectionContent(int depth, int level) {
                fBuf.append("</c>");
            }

            public void endSectionHeader(int depth, int level) {
                fBuf.append("</h" + level + ">");
            }

        };

        fBuilder = new SectionBuilder(listener);
    }

    public void test() {
        fBuilder.beginDocument();
        check("<level0>");
        test(1, "A", "<s><h1>A</h1><c>");
        test(3, "B", "<level1><s><h3>B</h3><c>");
        test(3, "C", "</c></s><s><h3>C</h3><c>");
        test(5, "D", "<level2><s><h5>D</h5><c>");
        test(5, "E", "</c></s><s><h5>E</h5><c>");
        test(3, "F", "</c></s></level2></c></s><s><h3>F</h3><c>");
        fBuilder.endDocument();
        check("</c></s></level1></c></s></level0>");

    }

    private void test(int level, String content, String control) {
        fBuilder.beginHeader(level);
        fBuf.append(content);
        fBuilder.endHeader();
        check(control);
    }

}