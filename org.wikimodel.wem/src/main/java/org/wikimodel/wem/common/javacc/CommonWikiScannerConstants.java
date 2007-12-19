/* Generated By:JavaCC: Do not edit this line. CommonWikiScannerConstants.java */
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
package org.wikimodel.wem.common.javacc;

public interface CommonWikiScannerConstants {

  int EOF = 0;
  int INTERNAL_VERBATIM_START = 1;
  int INTERNAL_VERBATIM_END = 2;
  int INTERNAL_VERBATIM_CONTENT = 3;
  int INTERNAL_MACRO_START = 4;
  int INTERNAL_MACRO_END = 5;
  int INTERNAL_MACRO_CONTENT = 6;
  int LI = 7;
  int DL = 8;
  int LIST_ITEM = 9;
  int PROPERTY = 10;
  int PROPERTY_DOC = 11;
  int DOC_BEGIN = 12;
  int DOC_END = 13;
  int VERBATIM_START = 14;
  int VERBATIM_END = 15;
  int ESCAPED = 16;
  int VERBATIM_CONTENT = 17;
  int MACRO_NAME = 18;
  int MACRO_START = 19;
  int MACRO_END = 20;
  int MACRO_CONTENT = 21;
  int INFO = 22;
  int EXTENSION = 23;
  int PARAMS = 24;
  int HEADER = 25;
  int BR = 26;
  int ESCAPE = 27;
  int VERBATIM_INLINE = 28;
  int REFERENCE = 29;
  int HORLINE = 30;
  int CELL = 31;
  int QUOT_BLOCK = 32;
  int QUOT_LINE = 33;
  int FORMAT_SYMBOL = 34;
  int SPECIAL_SYMBOLS = 35;
  int NEW_LINE = 36;
  int SPACE = 37;
  int SPECIAL_SYMBOL = 38;
  int CHAR = 39;
  int URI = 40;
  int ALPHA = 41;
  int DIGIT = 42;
  int HEXDIG = 43;
  int URI_GEN_DELIMS = 44;
  int URI_SUB_DELIMS = 45;
  int URI_UNRESERVED = 46;
  int URI_RESERVED = 47;
  int URI_SCHEME = 48;
  int URI_SCHEME_COMPOSITE = 49;
  int URI_PCT_ENCODED = 50;
  int URI_PCHAR_FIRST = 51;
  int URI_PCHAR = 52;
  int URI_QUERY = 53;
  int URI_FRAGMENT = 54;
  int URI_HIER_PART = 55;
  int URI_AUTHORITY = 56;
  int URI_USERINFO = 57;
  int URI_PATH_ABEMPTY = 58;
  int URI_PATH_ABSOLUTE = 59;
  int URI_PATH_ROOTLESS = 60;
  int URI_SEGMENT = 61;
  int URI_SEGMENT_NZ = 62;
  int URI_SEGMENT_NZ_NC = 63;
  int URI_PORT = 64;
  int URI_HOST = 65;
  int URI_REG_NAME = 66;
  int I_DOC_BEGIN = 67;
  int I_DOC_END = 68;
  int I_BR = 69;
  int I_ESCAPE = 70;
  int I_HORLINE = 71;
  int I_TABLE_ROW = 72;
  int I_TABLE_CELL = 73;
  int I_LIST_ITEM = 74;
  int I_VERBATIM_INLINE = 75;
  int I_VERBATIM_START = 76;
  int I_MACRO_BLOCK_START = 77;
  int I_MACRO_INLINE_START = 78;
  int I_QUOT_BLOCK = 79;
  int I_QUOT_LINE = 80;
  int I_INFO = 81;
  int I_EXTENSION_BLOCK = 82;
  int I_EXTENSION_INLINE = 83;
  int I_PROPERTY = 84;
  int I_PROPERTY_DOC = 85;
  int I_PROPERTY_INLINE = 86;
  int I_HEADER_BEGIN = 87;
  int I_REFERENCE = 88;
  int I_HEADER_END = 89;
  int I_FORMAT_SYMBOL = 90;
  int I_BLOCK_PARAMS = 91;
  int I_SPECIAL_SYMBOLS = 92;
  int I_URI = 93;
  int I_NL = 94;
  int I_SPACE = 95;
  int I_WORD = 96;
  int I_SPECIAL_SYMBOL = 97;
  int D_DOC_BEGIN = 98;
  int D_DOC_END = 99;
  int D_BR = 100;
  int D_ESCAPE = 101;
  int D_HORLINE = 102;
  int D_TABLE_ROW = 103;
  int D_TABLE_CELL = 104;
  int D_LIST_ITEM = 105;
  int D_VERBATIM_INLINE = 106;
  int D_VERBATIM_START = 107;
  int D_MACRO_BLOCK_START = 108;
  int D_MACRO_INLINE_START = 109;
  int D_QUOT_BLOCK = 110;
  int D_QUOT_LINE = 111;
  int D_INFO = 112;
  int D_EXTENSION_BLOCK = 113;
  int D_EXTENSION_INLINE = 114;
  int D_PROPERTY = 115;
  int D_PROPERTY_DOC = 116;
  int D_PROPERTY_INLINE = 117;
  int D_HEADER_BEGIN = 118;
  int D_REFERENCE = 119;
  int D_HEADER_END = 120;
  int D_FORMAT_SYMBOL = 121;
  int D_BLOCK_PARAMS = 122;
  int D_SPECIAL_SYMBOLS = 123;
  int D_URI = 124;
  int D_NL = 125;
  int D_SPACE = 126;
  int D_WORD = 127;
  int D_SPECIAL_SYMBOL = 128;

  int VERBATIM_CONTEXT = 0;
  int MACRO_CONTEXT = 1;
  int DEFAULT = 2;
  int INITIAL_CONTEXT = 3;

  String[] tokenImage = {
    "<EOF>",
    "<INTERNAL_VERBATIM_START>",
    "<INTERNAL_VERBATIM_END>",
    "<INTERNAL_VERBATIM_CONTENT>",
    "<INTERNAL_MACRO_START>",
    "<INTERNAL_MACRO_END>",
    "<INTERNAL_MACRO_CONTENT>",
    "<LI>",
    "<DL>",
    "<LIST_ITEM>",
    "<PROPERTY>",
    "<PROPERTY_DOC>",
    "<DOC_BEGIN>",
    "<DOC_END>",
    "\"{{{\"",
    "\"}}}\"",
    "<ESCAPED>",
    "<VERBATIM_CONTENT>",
    "<MACRO_NAME>",
    "<MACRO_START>",
    "<MACRO_END>",
    "<MACRO_CONTENT>",
    "<INFO>",
    "<EXTENSION>",
    "<PARAMS>",
    "<HEADER>",
    "<BR>",
    "<ESCAPE>",
    "<VERBATIM_INLINE>",
    "<REFERENCE>",
    "<HORLINE>",
    "<CELL>",
    "<QUOT_BLOCK>",
    "<QUOT_LINE>",
    "<FORMAT_SYMBOL>",
    "<SPECIAL_SYMBOLS>",
    "<NEW_LINE>",
    "<SPACE>",
    "<SPECIAL_SYMBOL>",
    "<CHAR>",
    "<URI>",
    "<ALPHA>",
    "<DIGIT>",
    "<HEXDIG>",
    "<URI_GEN_DELIMS>",
    "<URI_SUB_DELIMS>",
    "<URI_UNRESERVED>",
    "<URI_RESERVED>",
    "<URI_SCHEME>",
    "<URI_SCHEME_COMPOSITE>",
    "<URI_PCT_ENCODED>",
    "<URI_PCHAR_FIRST>",
    "<URI_PCHAR>",
    "<URI_QUERY>",
    "<URI_FRAGMENT>",
    "<URI_HIER_PART>",
    "<URI_AUTHORITY>",
    "<URI_USERINFO>",
    "<URI_PATH_ABEMPTY>",
    "<URI_PATH_ABSOLUTE>",
    "<URI_PATH_ROOTLESS>",
    "<URI_SEGMENT>",
    "<URI_SEGMENT_NZ>",
    "<URI_SEGMENT_NZ_NC>",
    "<URI_PORT>",
    "<URI_HOST>",
    "<URI_REG_NAME>",
    "<I_DOC_BEGIN>",
    "<I_DOC_END>",
    "<I_BR>",
    "<I_ESCAPE>",
    "<I_HORLINE>",
    "<I_TABLE_ROW>",
    "<I_TABLE_CELL>",
    "<I_LIST_ITEM>",
    "<I_VERBATIM_INLINE>",
    "<I_VERBATIM_START>",
    "<I_MACRO_BLOCK_START>",
    "<I_MACRO_INLINE_START>",
    "<I_QUOT_BLOCK>",
    "<I_QUOT_LINE>",
    "<I_INFO>",
    "<I_EXTENSION_BLOCK>",
    "<I_EXTENSION_INLINE>",
    "<I_PROPERTY>",
    "<I_PROPERTY_DOC>",
    "<I_PROPERTY_INLINE>",
    "<I_HEADER_BEGIN>",
    "<I_REFERENCE>",
    "<I_HEADER_END>",
    "<I_FORMAT_SYMBOL>",
    "<I_BLOCK_PARAMS>",
    "<I_SPECIAL_SYMBOLS>",
    "<I_URI>",
    "<I_NL>",
    "<I_SPACE>",
    "<I_WORD>",
    "<I_SPECIAL_SYMBOL>",
    "<D_DOC_BEGIN>",
    "<D_DOC_END>",
    "<D_BR>",
    "<D_ESCAPE>",
    "<D_HORLINE>",
    "<D_TABLE_ROW>",
    "<D_TABLE_CELL>",
    "<D_LIST_ITEM>",
    "<D_VERBATIM_INLINE>",
    "<D_VERBATIM_START>",
    "<D_MACRO_BLOCK_START>",
    "<D_MACRO_INLINE_START>",
    "<D_QUOT_BLOCK>",
    "<D_QUOT_LINE>",
    "<D_INFO>",
    "<D_EXTENSION_BLOCK>",
    "<D_EXTENSION_INLINE>",
    "<D_PROPERTY>",
    "<D_PROPERTY_DOC>",
    "<D_PROPERTY_INLINE>",
    "<D_HEADER_BEGIN>",
    "<D_REFERENCE>",
    "<D_HEADER_END>",
    "<D_FORMAT_SYMBOL>",
    "<D_BLOCK_PARAMS>",
    "<D_SPECIAL_SYMBOLS>",
    "<D_URI>",
    "<D_NL>",
    "<D_SPACE>",
    "<D_WORD>",
    "<D_SPECIAL_SYMBOL>",
  };

}
