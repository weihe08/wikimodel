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
package org.wikimodel.wem.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kotelnikov
 */
public class WikiEntityUtil {

    private static Map fHtmlToWiki = new HashMap();

    private static String[] fIdToWiki = new String[65535];

    private static Map fWikiToHtml = new HashMap();

    static {
        add("<", "lt", 8249);
        add(">", "gt", 8250);
        add("&", "amp", 38); // ???

        add("\'", "rsquo", 8217);
        add("(tm)", "trade", 8482);
        add("(TM)", "trade", 8482);
        add("(No)", "8470", 8470);
        add(" -- ", "ndash", 8211);
        add("---", "mdash", 8212);
        add(" --- ", "mdash", 8212);
        add("...", "hellip", 8230);
        add("(*)", "bull", 8226);
        add("(R)", "reg", 174);
        add("(r)", "reg", 174);
        add("(o)", "deg", 176);
        add("(C)", "copy", 169);
        add("(p)", "para", 182);
        add("(P)", "para", 182);
        add("(s)", "sect", 167);
        add("()", "nbsp", 160);
        add("<<", "laquo", 171);
        add(">>", "raquo", 187);
        // add("<", "lsaquo", 8249);
        // add(">", "rsaquo", 8250);

        // Currency
        add("(c)", "cent", 162);
        add("(E)", "euro", 8364);
        add("(O)", "curren", 164);
        add("(L)", "pound", 163);
        add("(Y)", "yen", 165);
        add("(f)", "fnof", 402);

        // Math
        add("+/-", "plusmn", 177);
        add("(S)", "sum", 8721);
        add("(/)", "divide", 247);
        add("(x)", "times", 215);
        add("(8)", "infin", 8734);
        add("(~)", "sim", 8764);
        add("!=", "ne", 8800);

        add("->", "rarr", 8594);
        add("-->", "rarr", 8594);
        add("--->", "rarr", 8594);
        
        add("<-", "larr", 8592);
        add("<--", "larr", 8592);
        add("<---", "larr", 8592);
        
        add("<->", "harr", 8596);
        add("<-->", "harr", 8596);
        add("<--->", "harr", 8596);
        
        add("=>", "rArr", 8658);
        add("==>", "rArr", 8658);
        add("===>", "rArr", 8658);

        add("<=", "lArr", 8658);
        add("<==", "lArr", 8658);
        add("<===", "lArr", 8658);

        add("<=>", "hArr", 8660);
        add("<==>", "hArr", 8660);
        add("<===>", "hArr", 8660);

        add("<=", "le", 8804);
        add(">=", "ge", 8805);
        add("!=", "ne", 8800);
        add("~=", "asymp", 8776);
    }

    private static void add(String wikiEnity, String htmlEntity, int id) {
        fWikiToHtml.put(wikiEnity, htmlEntity);
        fHtmlToWiki.put(htmlEntity, wikiEnity);
        fIdToWiki[id] = wikiEnity;
    }

    /**
     * @param ch for this character the corresponding html entity will be
     *        returned
     * @return an html entity corresponding to the given character
     */
    public static String getHtmlSymbol(char ch) {
        String wikiSymbol = fIdToWiki[ch];
        return (String) ((wikiSymbol != null)
            ? fWikiToHtml.get(wikiSymbol)
            : null);
    }

    /**
     * @param wikiEntity for this wiki entity the corresponding html entity will
     *        be returned
     * @return an html entity corresponding to the given wiki symbol
     */
    public static String getHtmlSymbol(String wikiEntity) {
        return (String) fWikiToHtml.get(wikiEntity);
    }

    /**
     * @param ch for this character the corresponding wiki entity will be
     *        returned
     * @return an wiki entity corresponding to the given character
     */
    public static String getWikiSymbol(char ch) {
        return fIdToWiki[ch];
    }

    /**
     * @param htmlEntity for this html entity the corresponding wiki entity will
     *        be returned
     * @return an wiki entity corresponding to the given html symbol
     */
    public static String getWikiSymbol(String htmlEntity) {
        return (String) fHtmlToWiki.get(htmlEntity);
    }
}