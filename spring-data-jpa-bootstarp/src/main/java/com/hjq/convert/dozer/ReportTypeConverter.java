package com.hjq.convert.dozer;

import org.dozer.DozerConverter;

import com.hjq.entity.enumType.ReportType;

public class ReportTypeConverter extends DozerConverter<ReportType[], String> {

	public ReportTypeConverter() {
		super(ReportType[].class, String.class);

	}

	@Override
	public String convertTo(ReportType[] source,
			String destination) {

		StringBuilder sb = new StringBuilder();
		for (ReportType type : source) {
			sb.append(type.getCode()).append(",");
		}
		destination = sb.toString();
		return destination.substring(0, destination.length() - 1);
	}

	@Override
	public ReportType[] convertFrom(String source,
			ReportType[] destination) {
		// TODO Auto-generated method stub
		return null;
	}

}
