package com.cxlsky.utils;

import org.apache.commons.lang3.StringUtils;

public class WordUtil {

    public static int wordCount(String string) {
        if (StringUtils.isEmpty(string)) {
            return 0;
        }
        String withOutBlankStr = string.replaceAll("\\s*", "");
        String englishString = withOutBlankStr.replaceAll("[\u4e00-\u9fa5]", "");
        String[] englishWords = englishString.split("[\\p{P}\\p{S}\\p{Z}\\s]+");
        int chineseWordCount = withOutBlankStr.length() - englishString.length();
        int otherWordCount = englishWords.length;
        if (englishWords.length > 0 && englishWords[0].length() < 1) {
            otherWordCount--;
        }
        if (englishWords.length > 1 && englishWords[englishWords.length - 1].length() < 1) {
            otherWordCount--;
        }
        return chineseWordCount + otherWordCount;
    }

}
