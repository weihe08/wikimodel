/* Generated By:JavaCC: Do not edit this line. XWikiScanner.java */
/*******************************************************************************
 * Copyright (c) 2005,2007 Cognium Systems SA and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, Version 2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Contributors:
 *     Cognium Systems SA - initial API and implementation
 *******************************************************************************/
package org.wikimodel.wem.xwiki.xwiki21.javacc;

import org.wikimodel.wem.IWikiReferenceParser;
import org.wikimodel.wem.WikiParameters;
import org.wikimodel.wem.WikiReference;
import org.wikimodel.wem.WikiStyle;
import org.wikimodel.wem.impl.IWikiScannerContext;
import org.wikimodel.wem.impl.WikiScannerUtil;
import org.wikimodel.wem.xwiki.xwiki20.XWikiReferenceParser;
import org.wikimodel.wem.xwiki.xwiki20.XWikiScannerUtil;
import org.wikimodel.wem.xwiki.xwiki20.XWikiWikiParameters;

import java.util.Stack;

/**
 * This is the internal wiki page parser generated from the grammar file.
 * 
 * @author kotelnikov
 * @author thomas.mortagne
 * @author Andreas Jonsson
 */
public class XWikiScanner implements XWikiScannerConstants {

    private IWikiScannerContext fContext;

    private IWikiReferenceParser fReferenceParser = new XWikiReferenceParser();

    public void parse(IWikiScannerContext context) throws ParseException {
        fContext = context;
        doParse();
    }

    protected WikiParameters newWikiParameters(String str) {
        str = WikiScannerUtil.extractSubstring(str, "(%", "%)", '~', false);
        return new XWikiWikiParameters(str);
    }

    private WikiParameters wikiParameters = WikiParameters.EMPTY;

    protected void setWikiParameters(String str) {
        wikiParameters = newWikiParameters(str);
    }

    protected WikiParameters consumeWikiParameters() {
       WikiParameters params = wikiParameters;
       wikiParameters = WikiParameters.EMPTY;
       return params;
    }

    protected void consumeRemainingParameters() {
        if (wikiParameters != WikiParameters.EMPTY) {
            fContext.beginParagraph(wikiParameters);
            fContext.endParagraph();
            wikiParameters = WikiParameters.EMPTY;
        }
    }

    private int emptyLinesCount = 0;

    private void endBlock() {
        emptyLinesCount = 0;
    }

    // TODO: No empty lines event is generated if there are
    //       exactly two new lines before something that is
    //       not a paragraph, macro block, or verbatim block.
    private void startBlock() {
        if (emptyLinesCount > 2) {
            fContext.onEmptyLines(emptyLinesCount-1);
        }
        emptyLinesCount = 0;
    }

    private void startBlockBeforeParagraph() {
        if (emptyLinesCount > 1) {
            fContext.onEmptyLines(emptyLinesCount-1);
        }
        emptyLinesCount = 0;
    }

    private void endDocument() {
        consumeRemainingParameters();
        if (emptyLinesCount > 1) {
            fContext.onEmptyLines(emptyLinesCount);
        }
    }

    private void processMacro(String start, String content, boolean inline) {
        String name;
        String paramStr = "";
        int paramStrPos = start.indexOf(" ");
        if (paramStrPos > 0) {
            paramStr = start.substring(paramStrPos);
            name = start.substring(0, paramStrPos);
        } else {
            name = start;
        }
        name = name.trim();

        WikiParameters params = new XWikiWikiParameters(paramStr);
        if (inline) {
            fContext.onMacro(name, params, content, inline);
        } else {
            WikiParameters wikiParams = consumeWikiParameters();
            if (wikiParams != WikiParameters.EMPTY) {
                fContext.beginParagraph(wikiParams);
                fContext.onMacro(name, params, content, true);
            } else {
                fContext.onMacro(name, params, content);
            }
        }
    }


    protected String normalizeMacroContent(StringBuilder content) {
        if (content == null) {
            return null;
        }

        if (content.length() == 0) {
            return "";
        }

        int startIndex = 0;
        if (content.charAt(0) == '\u005cn') {
            ++startIndex;
        } else if (content.length() >= 2 && content.charAt(0) == '\u005cr') {
            ++startIndex;
            if (content.charAt(1) == '\u005cn') {
                ++startIndex;
            }
        }

        int endIndex = content.length();
        if ((content.length() - startIndex) >= 1) {
            if (content.charAt(content.length() - 1) == '\u005cn') {
                --endIndex;
                if ((content.length() - startIndex) >= 2 && content.charAt(content.length() - 2) == '\u005cr') {
                    --endIndex;
                }
            } else if (content.charAt(content.length() - 1) == '\u005cr') {
                --endIndex;
            }
        }

        return content.substring(startIndex, endIndex);
    }

  final public void doParse() throws ParseException {
        fContext.beginDocument();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NL:
    case BLOCK_END:
      emptyLine();
                             emptyLinesCount--;
      break;
    default:
      ;
    }
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case HEADER_BEGIN:
      case LIST_ITEM:
      case HORLINE:
      case TABLE_ROW:
      case PARAMETERS_BEGINNING_OF_LINE:
      case BLOCK_PARAMETERS:
      case QUOT_LINE_BEGIN:
      case DOC_PARAMETERS:
      case INLINE_PARAMETERS:
      case DOC_BEGIN:
      case D_REFERENCE:
      case VERBATIM_START:
      case MACRO_EMPTY:
      case MACRO_START:
      case STRONG:
      case EM:
      case STRIKE:
      case INS:
      case SUP:
      case SUB:
      case MONO:
      case D_IMAGE:
      case D_ATTACH:
      case BR:
      case D_XWIKI_URI:
      case XWIKI_SPACE:
      case NL:
      case WORD:
      case XWIKI_SPECIAL_SYMBOL:
      case BLOCK_END:
        ;
        break;
      default:
        break label_1;
      }
      docElements();
    }
    jj_consume_token(0);
        endDocument();
        fContext.endDocument();
  }

  final public void parametersBeginningOfLine() throws ParseException {
    jj_consume_token(PARAMETERS_BEGINNING_OF_LINE);
                                     setWikiParameters(token.image);
  }

  final public void inlineParameters() throws ParseException {
    jj_consume_token(INLINE_PARAMETERS);
                         fContext.onFormat(newWikiParameters(token.image));
  }

  final public void blockParameters() throws ParseException {
    jj_consume_token(BLOCK_PARAMETERS);
                         setWikiParameters(token.image);
  }

  final public void docElements() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case HEADER_BEGIN:
    case LIST_ITEM:
    case HORLINE:
    case TABLE_ROW:
    case BLOCK_PARAMETERS:
    case QUOT_LINE_BEGIN:
      blockStart();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case HEADER_BEGIN:
        header();
        break;
      case BLOCK_PARAMETERS:
        blockParameters();
        break;
      case LIST_ITEM:
        list();
        break;
      case QUOT_LINE_BEGIN:
        quot();
        break;
      case HORLINE:
        horline();
        break;
      case TABLE_ROW:
        table();
        blockEnd();
        break;
      default:
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    case PARAMETERS_BEGINNING_OF_LINE:
    case DOC_PARAMETERS:
    case INLINE_PARAMETERS:
    case DOC_BEGIN:
    case D_REFERENCE:
    case VERBATIM_START:
    case MACRO_EMPTY:
    case MACRO_START:
    case STRONG:
    case EM:
    case STRIKE:
    case INS:
    case SUP:
    case SUB:
    case MONO:
    case D_IMAGE:
    case D_ATTACH:
    case BR:
    case D_XWIKI_URI:
    case XWIKI_SPACE:
    case WORD:
    case XWIKI_SPECIAL_SYMBOL:
      blockStartBeforeParagraph();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MACRO_EMPTY:
      case MACRO_START:
        macro(false);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case NL:
          jj_consume_token(NL);
          break;
        default:
          ;
        }
        break;
      case VERBATIM_START:
        verbatimBlock(false);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case NL:
          jj_consume_token(NL);
          break;
        default:
          ;
        }
        break;
      case DOC_PARAMETERS:
      case DOC_BEGIN:
        embeddedDocument();
        break;
      case PARAMETERS_BEGINNING_OF_LINE:
      case INLINE_PARAMETERS:
      case D_REFERENCE:
      case STRONG:
      case EM:
      case STRIKE:
      case INS:
      case SUP:
      case SUB:
      case MONO:
      case D_IMAGE:
      case D_ATTACH:
      case BR:
      case D_XWIKI_URI:
      case XWIKI_SPACE:
      case WORD:
      case XWIKI_SPECIAL_SYMBOL:
        paragraph();
        break;
      default:
        jj_consume_token(-1);
        throw new ParseException();
      }
      blockEnd();
      break;
    case NL:
    case BLOCK_END:
      emptyLine();
      break;
    default:
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void header() throws ParseException {
    jj_consume_token(HEADER_BEGIN);
        int level = token.image.trim().length();
        fContext.beginHeader(level, consumeWikiParameters());
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NL:
      newLine();
      break;
    default:
      ;
    }
    block();
        fContext.endHeader();
  }

  final public void macro(boolean inline) throws ParseException {
   Token start = null;
   StringBuilder content = new StringBuilder();
   boolean empty = false;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case MACRO_EMPTY:
      start = jj_consume_token(MACRO_EMPTY);
                                empty = true;
      break;
    case MACRO_START:
      start = jj_consume_token(MACRO_START);
      label_2:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case MACRO_CONTENT:
          ;
          break;
        default:
          break label_2;
        }
        jj_consume_token(MACRO_CONTENT);
                                              content.append(token.image);
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MACRO_END:
        jj_consume_token(MACRO_END);
        break;
      default:
        ;
      }
      break;
    default:
      jj_consume_token(-1);
      throw new ParseException();
    }
      {
         String s = start.image.trim();
         if (empty) {
             s = s.substring("{{".length(), s.length() - "/}}".length());
         } else {
             s = s.substring("{{".length(), s.length() - "}}".length());
         }
         String c = null;
         if (!empty) {
             c = normalizeMacroContent(content);
         }
         processMacro(s, c, inline);
      }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NL:
      newLine();
      break;
    default:
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INLINE_PARAMETERS:
    case D_REFERENCE:
    case VERBATIM_START:
    case MACRO_EMPTY:
    case MACRO_START:
    case STRONG:
    case EM:
    case STRIKE:
    case INS:
    case SUP:
    case SUB:
    case MONO:
    case D_IMAGE:
    case D_ATTACH:
    case BR:
    case D_XWIKI_URI:
    case XWIKI_SPACE:
    case WORD:
    case XWIKI_SPECIAL_SYMBOL:
      lines();
      break;
    default:
      ;
    }
           fContext.endParagraph();
  }

  final public void list() throws ParseException {
    String str = "";
        fContext.beginList(consumeWikiParameters());
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LIST_ITEM:
        listItem();
        break;
      case BLOCK_PARAMETERS:
        blockParameters();
        break;
      default:
        jj_consume_token(-1);
        throw new ParseException();
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LIST_ITEM:
      case BLOCK_PARAMETERS:
        ;
        break;
      default:
        break label_3;
      }
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case BLOCK_END:
      jj_consume_token(BLOCK_END);
                             emptyLinesCount++;
      break;
    default:
      ;
    }
        fContext.endList();
  }

  final public void listItem() throws ParseException {
    String str;
    WikiParameters params;
                params = consumeWikiParameters();
    jj_consume_token(LIST_ITEM);
                str = token.image.trim();
                str = str.replace(".", "");
                str = str.replace('1', '#');
                fContext.beginListItem(str, params);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NL:
      newLine();
      break;
    default:
      ;
    }
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case DOC_PARAMETERS:
      case INLINE_PARAMETERS:
      case DOC_BEGIN:
      case D_REFERENCE:
      case VERBATIM_START:
      case MACRO_EMPTY:
      case MACRO_START:
      case STRONG:
      case EM:
      case STRIKE:
      case INS:
      case SUP:
      case SUB:
      case MONO:
      case D_IMAGE:
      case D_ATTACH:
      case BR:
      case D_XWIKI_URI:
      case XWIKI_SPACE:
      case WORD:
      case XWIKI_SPECIAL_SYMBOL:
        ;
        break;
      default:
        break label_4;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case DOC_PARAMETERS:
      case DOC_BEGIN:
        embeddedDocument();
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case NL:
          jj_consume_token(NL);
          break;
        default:
          ;
        }
        break;
      case INLINE_PARAMETERS:
      case D_REFERENCE:
      case VERBATIM_START:
      case MACRO_EMPTY:
      case MACRO_START:
      case STRONG:
      case EM:
      case STRIKE:
      case INS:
      case SUP:
      case SUB:
      case MONO:
      case D_IMAGE:
      case D_ATTACH:
      case BR:
      case D_XWIKI_URI:
      case XWIKI_SPACE:
      case WORD:
      case XWIKI_SPECIAL_SYMBOL:
        lines();
        break;
      default:
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
            fContext.endListItem();
  }

  final public void table() throws ParseException {
   String str = "";
        fContext.beginTable(consumeWikiParameters());
    label_5:
    while (true) {
      tableRow();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case TABLE_ROW:
        ;
        break;
      default:
        break label_5;
      }
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case TABLE_END_EMPTY_LINE:
      tableEnd();
      break;
    default:
      ;
    }
        fContext.endTable();
  }

  final public void tableRow() throws ParseException {
    WikiParameters rowParams = WikiParameters.EMPTY;
    WikiParameters cellParams = WikiParameters.EMPTY;
    jj_consume_token(TABLE_ROW);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INLINE_PARAMETERS:
      jj_consume_token(INLINE_PARAMETERS);
                          rowParams = newWikiParameters(token.image);
      break;
    default:
      ;
    }
    tableFirstCell(rowParams);
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case TABLE_HCELL:
      case TABLE_CELL:
        ;
        break;
      default:
        break label_6;
      }
      tableCell();
    }
        fContext.endTableRow();
  }

  final public void tableCell() throws ParseException {
    boolean head = false;
    WikiParameters cellParams = WikiParameters.EMPTY;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case TABLE_CELL:
      jj_consume_token(TABLE_CELL);
      break;
    case TABLE_HCELL:
      jj_consume_token(TABLE_HCELL);
                                 head=true;
      break;
    default:
      jj_consume_token(-1);
      throw new ParseException();
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case DOC_PARAMETERS:
    case INLINE_PARAMETERS:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case INLINE_PARAMETERS:
        jj_consume_token(INLINE_PARAMETERS);
        break;
      case DOC_PARAMETERS:
        jj_consume_token(DOC_PARAMETERS);
        break;
      default:
        jj_consume_token(-1);
        throw new ParseException();
      }
                                                         cellParams = newWikiParameters(token.image);
      break;
    default:
      ;
    }
        fContext.onTableCell(head, cellParams);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NL:
      newLine();
      break;
    default:
      ;
    }
    block();
  }

  final public void tableFirstCell(WikiParameters rowParams) throws ParseException {
    boolean head = false;
    WikiParameters cellParams = WikiParameters.EMPTY;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case TABLE_CELL:
      jj_consume_token(TABLE_CELL);
      break;
    case TABLE_HCELL:
      jj_consume_token(TABLE_HCELL);
                                 head=true;
      break;
    default:
      jj_consume_token(-1);
      throw new ParseException();
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case DOC_PARAMETERS:
    case INLINE_PARAMETERS:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case INLINE_PARAMETERS:
        jj_consume_token(INLINE_PARAMETERS);
        break;
      case DOC_PARAMETERS:
        jj_consume_token(DOC_PARAMETERS);
        break;
      default:
        jj_consume_token(-1);
        throw new ParseException();
      }
                                                        cellParams = newWikiParameters(token.image);
      break;
    default:
      ;
    }
        fContext.beginTableRow(head, rowParams, cellParams);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NL:
      newLine();
      break;
    default:
      ;
    }
    block();
  }

  final public void verbatimBlock(boolean inline) throws ParseException {
    StringBuilder buf = new StringBuilder();
    jj_consume_token(VERBATIM_START);
    label_7:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case VERBATIM_CONTENT:
        ;
        break;
      default:
        break label_7;
      }
      jj_consume_token(VERBATIM_CONTENT);
                         buf.append(token.image);
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case VERBATIM_END:
      jj_consume_token(VERBATIM_END);
      break;
    default:
      ;
    }
        {
            String content = XWikiScannerUtil.unescapeVerbatim(buf.toString());
            if (inline) {
                fContext.onVerbatim(content, inline, consumeWikiParameters() );
            } else {
                fContext.onVerbatim(content, consumeWikiParameters() );
            }
        }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INLINE_PARAMETERS:
    case D_REFERENCE:
    case VERBATIM_START:
    case MACRO_EMPTY:
    case MACRO_START:
    case STRONG:
    case EM:
    case STRIKE:
    case INS:
    case SUP:
    case SUB:
    case MONO:
    case D_IMAGE:
    case D_ATTACH:
    case BR:
    case D_XWIKI_URI:
    case XWIKI_SPACE:
    case WORD:
    case XWIKI_SPECIAL_SYMBOL:
      line();
      break;
    default:
      ;
    }
  }

  final public void horline() throws ParseException {
    jj_consume_token(HORLINE);
        fContext.onHorizontalLine(consumeWikiParameters());
  }

  final public void quot() throws ParseException {
        fContext.beginQuot(consumeWikiParameters());
    label_8:
    while (true) {
      quotLine();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case QUOT_LINE_BEGIN:
        ;
        break;
      default:
        break label_8;
      }
    }
        fContext.endQuot();
  }

  final public void quotLine() throws ParseException {
    String str;
    jj_consume_token(QUOT_LINE_BEGIN);
        str = token.image.trim();
        int depth = str.length();
        fContext.beginQuotLine(depth);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INLINE_PARAMETERS:
    case D_REFERENCE:
    case VERBATIM_START:
    case MACRO_EMPTY:
    case MACRO_START:
    case STRONG:
    case EM:
    case STRIKE:
    case INS:
    case SUP:
    case SUB:
    case MONO:
    case D_IMAGE:
    case D_ATTACH:
    case BR:
    case D_XWIKI_URI:
    case XWIKI_SPACE:
    case WORD:
    case XWIKI_SPECIAL_SYMBOL:
      line();
      break;
    default:
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NL:
      jj_consume_token(NL);
      break;
    default:
      ;
    }
        fContext.endQuotLine();
  }

  final public void headerEnd() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case HEADER_END:
      jj_consume_token(HEADER_END);
      break;
    case HEADER_END_EMPTY_LINE:
      jj_consume_token(HEADER_END_EMPTY_LINE);
                               emptyLinesCount++;
      break;
    default:
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void tableEnd() throws ParseException {
    jj_consume_token(TABLE_END_EMPTY_LINE);
                              emptyLinesCount++;
  }

  final public void blockStart() throws ParseException {
      startBlock();
  }

  final public void blockStartBeforeParagraph() throws ParseException {
      startBlockBeforeParagraph();
  }

  final public void block() throws ParseException {
    label_9:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case DOC_PARAMETERS:
      case INLINE_PARAMETERS:
      case DOC_BEGIN:
      case D_REFERENCE:
      case VERBATIM_START:
      case MACRO_EMPTY:
      case MACRO_START:
      case STRONG:
      case EM:
      case STRIKE:
      case INS:
      case SUP:
      case SUB:
      case MONO:
      case D_IMAGE:
      case D_ATTACH:
      case BR:
      case D_XWIKI_URI:
      case XWIKI_SPACE:
      case NL:
      case WORD:
      case XWIKI_SPECIAL_SYMBOL:
        ;
        break;
      default:
        break label_9;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case DOC_PARAMETERS:
      case DOC_BEGIN:
        embeddedDocument();
        break;
      case INLINE_PARAMETERS:
      case D_REFERENCE:
      case VERBATIM_START:
      case MACRO_EMPTY:
      case MACRO_START:
      case STRONG:
      case EM:
      case STRIKE:
      case INS:
      case SUP:
      case SUB:
      case MONO:
      case D_IMAGE:
      case D_ATTACH:
      case BR:
      case D_XWIKI_URI:
      case XWIKI_SPACE:
      case WORD:
      case XWIKI_SPECIAL_SYMBOL:
        lines();
        break;
      case NL:
        jj_consume_token(NL);
        break;
      default:
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case HEADER_END:
    case HEADER_END_EMPTY_LINE:
    case BLOCK_END:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case BLOCK_END:
        jj_consume_token(BLOCK_END);
                               emptyLinesCount++;
        break;
      case HEADER_END:
      case HEADER_END_EMPTY_LINE:
        headerEnd();
        break;
      default:
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    default:
      ;
    }
  }

  final public void blockEnd() throws ParseException {
       endBlock();
  }

  final public void paragraph() throws ParseException {
      fContext.beginParagraph(consumeWikiParameters());
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case PARAMETERS_BEGINNING_OF_LINE:
      jj_consume_token(PARAMETERS_BEGINNING_OF_LINE);
                                       fContext.onFormat(newWikiParameters(token.image));
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case INLINE_PARAMETERS:
      case D_REFERENCE:
      case VERBATIM_START:
      case MACRO_EMPTY:
      case MACRO_START:
      case STRONG:
      case EM:
      case STRIKE:
      case INS:
      case SUP:
      case SUB:
      case MONO:
      case D_IMAGE:
      case D_ATTACH:
      case BR:
      case D_XWIKI_URI:
      case XWIKI_SPACE:
      case WORD:
      case XWIKI_SPECIAL_SYMBOL:
        lines();
        break;
      default:
        ;
      }
      break;
    case INLINE_PARAMETERS:
    case D_REFERENCE:
    case VERBATIM_START:
    case MACRO_EMPTY:
    case MACRO_START:
    case STRONG:
    case EM:
    case STRIKE:
    case INS:
    case SUP:
    case SUB:
    case MONO:
    case D_IMAGE:
    case D_ATTACH:
    case BR:
    case D_XWIKI_URI:
    case XWIKI_SPACE:
    case WORD:
    case XWIKI_SPECIAL_SYMBOL:
      lines();
      break;
    default:
      jj_consume_token(-1);
      throw new ParseException();
    }
      fContext.endParagraph();
  }

  final public void lines() throws ParseException {
    line();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NL:
      jj_consume_token(NL);
      break;
    default:
      ;
    }
    label_10:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case INLINE_PARAMETERS:
      case D_REFERENCE:
      case VERBATIM_START:
      case MACRO_EMPTY:
      case MACRO_START:
      case STRONG:
      case EM:
      case STRIKE:
      case INS:
      case SUP:
      case SUB:
      case MONO:
      case D_IMAGE:
      case D_ATTACH:
      case BR:
      case D_XWIKI_URI:
      case XWIKI_SPACE:
      case WORD:
      case XWIKI_SPECIAL_SYMBOL:
        ;
        break;
      default:
        break label_10;
      }
            fContext.onNewLine();
      line();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case NL:
        jj_consume_token(NL);
        break;
      default:
        ;
      }
    }
  }

  final public void emptyLine() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NL:
      jj_consume_token(NL);
      break;
    case BLOCK_END:
      jj_consume_token(BLOCK_END);
                        endBlock();
      break;
    default:
      jj_consume_token(-1);
      throw new ParseException();
    }
       emptyLinesCount++;
       WikiParameters params = consumeWikiParameters();
       /*
        * TODO: Should be removed to apply these parameters to
        * the following paragraph, table or header.
        */
       if (!params.equals(WikiParameters.EMPTY)) {
           fContext.beginParagraph(params);
           fContext.endParagraph();
       }
  }

  final public void newLine() throws ParseException {
    jj_consume_token(NL);
            fContext.onNewLine();
  }

  final public void newLineSkip() throws ParseException {
    jj_consume_token(NL);
  }

  final public void line() throws ParseException {
    label_11:
    while (true) {
      inline();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case INLINE_PARAMETERS:
      case D_REFERENCE:
      case VERBATIM_START:
      case MACRO_EMPTY:
      case MACRO_START:
      case STRONG:
      case EM:
      case STRIKE:
      case INS:
      case SUP:
      case SUB:
      case MONO:
      case D_IMAGE:
      case D_ATTACH:
      case BR:
      case D_XWIKI_URI:
      case XWIKI_SPACE:
      case WORD:
      case XWIKI_SPECIAL_SYMBOL:
        ;
        break;
      default:
        break label_11;
      }
    }
  }

  final public void inline() throws ParseException {
    String str = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case WORD:
      jj_consume_token(WORD);
            fContext.onWord(token.image.replaceAll("~", ""));
      break;
    case XWIKI_SPACE:
      jj_consume_token(XWIKI_SPACE);
            fContext.onSpace(token.image.replaceAll("~", ""));
      break;
    case XWIKI_SPECIAL_SYMBOL:
      jj_consume_token(XWIKI_SPECIAL_SYMBOL);
            if (token.image.length() == 2) {
                // The first char is an escape symbol, only send the second one
                fContext.onSpecialSymbol("" + token.image.charAt(1));
            } else {
                //It's a '~' when it's the last character of the content
                if (token.image.charAt(0) != '~') {
                    fContext.onSpecialSymbol(token.image);
                }
            }
      break;
    case STRONG:
      jj_consume_token(STRONG);
                     fContext.onFormat(IWikiScannerContext.STRONG);
      break;
    case EM:
      jj_consume_token(EM);
                     fContext.onFormat(IWikiScannerContext.EM);
      break;
    case STRIKE:
      jj_consume_token(STRIKE);
                     fContext.onFormat(IWikiScannerContext.STRIKE);
      break;
    case INS:
      jj_consume_token(INS);
                     fContext.onFormat(IWikiScannerContext.INS);
      break;
    case SUP:
      jj_consume_token(SUP);
                     fContext.onFormat(IWikiScannerContext.SUP);
      break;
    case SUB:
      jj_consume_token(SUB);
                     fContext.onFormat(IWikiScannerContext.SUB);
      break;
    case MONO:
      jj_consume_token(MONO);
                     fContext.onFormat(IWikiScannerContext.MONO);
      break;
    case BR:
      jj_consume_token(BR);
                     fContext.onLineBreak();
      break;
    case MACRO_EMPTY:
    case MACRO_START:
      macro(true);
      break;
    case D_XWIKI_URI:
      jj_consume_token(D_XWIKI_URI);
                          fContext.onReference(token.image);
      break;
    case D_IMAGE:
      jj_consume_token(D_IMAGE);
                          fContext.onImage(token.image.substring("image:".length()));
      break;
    case D_ATTACH:
      jj_consume_token(D_ATTACH);
                          fContext.onReference(token.image);
      break;
    case INLINE_PARAMETERS:
      inlineParameters();
      break;
    case D_REFERENCE:
      jj_consume_token(D_REFERENCE);
            str = token.image;
            if (str.startsWith("[[")) {
                str = str.substring(2, str.length() - 2);
            }
            WikiReference ref = fReferenceParser.parse(str);
            fContext.onReference(ref);
      break;
    case VERBATIM_START:
      verbatimBlock(true);
      break;
    default:
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void embeddedDocument() throws ParseException {
    WikiParameters params = WikiParameters.EMPTY;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case DOC_PARAMETERS:
      jj_consume_token(DOC_PARAMETERS);
                       params = newWikiParameters(token.image);
      break;
    default:
      ;
    }
    jj_consume_token(DOC_BEGIN);
        fContext.beginDocument(params);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NL:
    case BLOCK_END:
      emptyLine();
                             emptyLinesCount--;
      break;
    default:
      ;
    }
    label_12:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case HEADER_BEGIN:
      case LIST_ITEM:
      case HORLINE:
      case TABLE_ROW:
      case PARAMETERS_BEGINNING_OF_LINE:
      case BLOCK_PARAMETERS:
      case QUOT_LINE_BEGIN:
      case DOC_PARAMETERS:
      case INLINE_PARAMETERS:
      case DOC_BEGIN:
      case D_REFERENCE:
      case VERBATIM_START:
      case MACRO_EMPTY:
      case MACRO_START:
      case STRONG:
      case EM:
      case STRIKE:
      case INS:
      case SUP:
      case SUB:
      case MONO:
      case D_IMAGE:
      case D_ATTACH:
      case BR:
      case D_XWIKI_URI:
      case XWIKI_SPACE:
      case NL:
      case WORD:
      case XWIKI_SPECIAL_SYMBOL:
      case BLOCK_END:
        ;
        break;
      default:
        break label_12;
      }
      docElements();
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case DOC_END:
      jj_consume_token(DOC_END);
      break;
    case 0:
      jj_consume_token(0);
      break;
    default:
      jj_consume_token(-1);
      throw new ParseException();
    }
        endDocument();
        fContext.endDocument();
  }

  /** Generated Token Manager. */
  public XWikiScannerTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;

  /** Constructor with InputStream. */
  public XWikiScanner(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public XWikiScanner(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new XWikiScannerTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
  }

  /** Constructor. */
  public XWikiScanner(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new XWikiScannerTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
  }

  /** Constructor with generated Token Manager. */
  public XWikiScanner(XWikiScannerTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
  }

  /** Reinitialise. */
  public void ReInit(XWikiScannerTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      return token;
    }
    token = oldToken;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  /** Generate ParseException. */
  public ParseException generateParseException() {
    Token errortok = token.next;
    int line = errortok.beginLine, column = errortok.beginColumn;
    String mess = (errortok.kind == 0) ? tokenImage[0] : errortok.image;
    return new ParseException("Parse error at line " + line + ", column " + column + ".  Encountered: " + mess);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}