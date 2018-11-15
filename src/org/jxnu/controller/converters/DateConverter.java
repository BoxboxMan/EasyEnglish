package org.jxnu.controller.converters;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * 自定义参数绑定器，把String类型转换为Date类型
 * @author MR.S
 *
 */
public class DateConverter implements Converter<String, Date> {

	@Override
	public Date convert(String source){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");
		try {
			return simpleDateFormat.parse(source);
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}

}
