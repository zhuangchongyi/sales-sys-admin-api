package com.dc.common.utils;

import org.apache.commons.lang3.StringUtils;

public class ObjectUtil extends org.apache.commons.lang3.ObjectUtils {
    public static String toString(final Object obj) {
        return obj == null ? StringUtils.EMPTY : obj.toString();
    }
}
