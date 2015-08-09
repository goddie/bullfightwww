package com.xiaba2.core;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class PageTag extends TagSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
		JspWriter out = pageContext.getOut();
		
		StringBuilder sb = new StringBuilder();

		sb.append("<div class=\"col-md-6\">");
		sb.append("<div class=\"dataTables_info\" id=\"example_info\">Showing");
		sb.append("1 to 50 of 57 entries</div>");
		sb.append("</div>");
		sb.append("<div class=\"col-md-6\">");
		sb.append("<div class=\"dataTables_paginate paging_bootstrap\">");
		sb.append("<ul class=\"pagination\">");
		sb.append("<li class=\"prev disabled\"><a href=\"#\">← Previous</a></li>");
		sb.append("<li class=\"active\"><a href=\"#\">1</a></li>");
		sb.append("<li><a href=\"#\">2</a></li>");
		sb.append("<li class=\"next\"><a href=\"#\">Next → </a></li>");
		sb.append("</ul>");
		sb.append("</div>");
		sb.append("</div>");
		
		try {
			out.print(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return super.doStartTag();
		
	}

	private Page<?> pageData;

	public Page<?> getPageData() {
		return pageData;
	}

	public void setPageData(Page<?> pageData) {
		this.pageData = pageData;
	}
 

}
