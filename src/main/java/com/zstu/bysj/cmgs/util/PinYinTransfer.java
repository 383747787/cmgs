package com.zstu.bysj.cmgs.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 拼音工具类
 * 
 * @author irving
 *
 */
public class PinYinTransfer {
	private static volatile PinYinTransfer instance = null;

	public static PinYinTransfer single() {
		if (instance == null) {
			synchronized (PinYinTransfer.class) {
				if (instance == null) {
					instance = new PinYinTransfer();
				}
			}
		}
		return instance;
	}

	// 默认拼音输出格式
	private HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();

	private PinYinTransfer() {
		defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public String toPinyin(String name) {
		return this.toPinyin(name, true);
	}

	/**
	 * 
	 * @param name
	 * @param keepAcii
	 * @return
	 */
	public String toPinyin(String name, boolean keepAcii) {
		return this.toPinyin(name, keepAcii, defaultFormat);
	}

	/**
	 * 汉字转拼音的方法
	 * 
	 * @param name
	 *            汉字
	 * @param keepAcii
	 *            是否保留字母
	 * @return 拼音
	 */
	public String toPinyin(String name, boolean keepAcii, HanyuPinyinOutputFormat format) {
		StringBuilder pinyin = new StringBuilder();
		char[] nameChar = name.toCharArray();
		try {
			for (int i = 0; i < nameChar.length; i++) {
				String[] py = PinyinHelper.toHanyuPinyinStringArray(nameChar[i], format);
				if (py != null) {// 找到拼音字符的

					// 多音字“长”的处理
					if (nameChar[i] == '长') {
						pinyin.append(py[1]);
					} else {
						pinyin.append(py[0]);
					}

				} else {
					if (keepAcii) {// 非中文字符保留
						pinyin.append(nameChar[i]);
					}
				}
			}
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			throw new IllegalArgumentException("请设置拼音输出格式，参见：HanyuPinyinOutputFormat");
		}
		return pinyin.toString();
	}

}
