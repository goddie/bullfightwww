package com.xiaba2.util;

import java.util.List;

public class ListUtil {

	/**
	 * 是否是空数组
	 * @param list
	 * @return
	 */
	public static Boolean isEmpty(List<?> list) {

		if (list == null || list.isEmpty()) {
			return true;
		}

		return false;

	}

}
