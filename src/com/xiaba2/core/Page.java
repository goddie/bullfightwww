package com.xiaba2.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

 

public class Page<T> {
	// 公共变量 //
	public static final String ASC = "asc";
	public static final String DESC = "desc";
	// 分页参数 //
	protected int pageNo = 1;
	protected int pageSize = 1;

	protected ArrayList<String[]> orders = new ArrayList<String[]>();

	protected boolean autoCount = true;

	// 返回结果 //
	protected List<T> result = Collections.emptyList();

	protected long totalCount = -1;

	public Page() {
	}

	public Page(final int pageSize) {
		setPageSize(pageSize);
	}

	public Page(final int pageSize, final boolean autoCount) {
		setPageSize(pageSize);
		setAutoCount(autoCount);
	}

	
	/**
	 * 生成分页代码
	 */
	public String genPageHtml(HttpServletRequest request) {
		
		String url = request.getRequestURL().toString();
		
        StringBuffer sb = new StringBuffer();
        
        
		sb.append("<div class=\"col-md-6\">");
		sb.append("<div class=\"dataTables_paginate paging_bootstrap\">");
		sb.append("<ul class=\"pagination\">");
 

        // 判断是否有上一页
        if ( this.isHasPre()) {
        	
        	sb.append("<li class=\"prev\"><a href=\""+updateQueryStringItem(request,"p", String.valueOf(getPageNo()-1))+"\">← 上页</a></li>");
            //sb.append("<a href=\"javascript:void(0)\" onclick=\"findDistResult('"+ String.valueOf(this.getPageNo() - 1) + "');return false;\">上一页</a>");
        } else {
        	sb.append("<li class=\"prev disabled\"><a href=\" \">← 上页</a></li>");
        }

        // 中间显示
        for (int i = 1; i <= this.getTotalPages(); i++) {
            //String spanClzz = "<a href=\"javascript:void(0)\" onclick=\"findDistResult('" + String.valueOf(i) + "');return false;\">" + i + "</a>";

        	String spanClzz = "<li class=\"\"><a href=\""+updateQueryStringItem(request,"p", String.valueOf(i))+"\">"+i+"</a></li>";
        	
            if (this.getPageNo() == i) {
            	spanClzz = "<li class=\"active\"><a href=\""+updateQueryStringItem(request,"p", String.valueOf(i))+"\">"+i+"</a></li>";
            }
            
            sb.append(spanClzz);

            // 当大于9页数的时候才进行分页显示
            if (this.getTotalPages() - 2 > 7) {
                if (i == 5) {
                    i = (int)this.getTotalCount() - 2;
                    sb.append("...");
                }
            }
        }
        // 判断是否有下一页
        if (this.isHasNext()) {
            //sb.append("<a href=\"javascript:void(0)\" onclick=\"findDistResult('"+ String.valueOf(this.getPageNo() + 1)+ "');return false;\">下一页</a>");
            sb.append("<li class=\"next\"><a href=\""+updateQueryStringItem(request,"p", String.valueOf(getPageNo()+1))+"\">下页 → </a></li>");
        } else {
        	sb.append("<li class=\"next disabled\"><a href=\"#\">下页 → </a></li>");
        }
        
		sb.append("</ul>");
		sb.append("</div>");
		sb.append("</div>");
 
        return sb.toString();
        
    }
	
	
	/// <summary>
    /// 更新翻页链接
    /// </summary>
    /// <param name="httpRequest"></param>
    /// <param name="queryStringKey"></param>
    /// <param name="newQueryStringValue"></param>
    /// <returns></returns>
    private String updateQueryStringItem(HttpServletRequest httpRequest, String queryStringKey, String newQueryStringValue)
    {
    	
    	//StringBuffer url_buffer = request.getRequestURL();  
    	//return http://localhost:8080/ssm/ser.do  
    	    	
    	String newURL = httpRequest.getRequestURL().toString()+"?"+httpRequest.getQueryString();

        //QueryString CONTAINS the Key...
        if (httpRequest.getParameter(queryStringKey) != null)
        {
        	String orignalSet = String.format("%s=%s", queryStringKey, httpRequest.getParameter(queryStringKey));
        	String newSet = String.format("%s=%s", queryStringKey, newQueryStringValue);

            //REMOVE the key/value completely since the NewValue is blank
            if (newQueryStringValue.trim().equals(""))
            {
                //Replace Key/Value down the line...
 
            	
            	newURL = Pattern.compile("&" + orignalSet,Pattern.CASE_INSENSITIVE).matcher(newURL).replaceAll("");
            	
            	//newURL = Regex.Replace(newURL, "&" + orignalSet, "", RegexOptions.IgnoreCase);

                //Replace Key/Value at the beginning When there is other
                //key/values after...
            	newURL = Pattern.compile("?" + orignalSet + "&",Pattern.CASE_INSENSITIVE).matcher(newURL).replaceAll("?");
            	//newURL = Regex.Replace(newURL, "?" + orignalSet + "&", "?", RegexOptions.IgnoreCase);

                //Replace Key/Value at the beginning when there 
                //are NO other Key/Values.
            	newURL = Pattern.compile("?" + orignalSet ,Pattern.CASE_INSENSITIVE).matcher(newURL).replaceAll("");
            	//newURL = Regex.Replace(newURL, "?" + orignalSet, "", RegexOptions.IgnoreCase);
            }
            //REPLACE old value with new value.
            else
            {
            	newURL = Pattern.compile(orignalSet ,Pattern.CASE_INSENSITIVE).matcher(newURL).replaceAll(newSet);
            	//newURL = Regex.Replace(newURL, orignalSet, newSet, RegexOptions.IgnoreCase);
            }
        }
        //Only add the key/value IF the new value is not blank.
        else if (!newQueryStringValue.trim().equals(""))
        {
            //QueryString DOES NOT CONTAIN the Key... and DOES NOT HAVE other key/value pairs.
            if (httpRequest.getQueryString().indexOf("?") < 0)
            {
            	newURL += String.format("?%s=%s", queryStringKey, newQueryStringValue);
            }
            //QueryString DOES NOT CONTAIN the Key... and HAS other key/value pairs.
            else
            {
            	newURL += String.format("&%s=%s", queryStringKey, newQueryStringValue);
            }
        }
        
        return newURL;
    }

	
	
	/**
	 * 加入一个排序
	 * 
	 * @param property
	 * @param order
	 */
	public void addOrder(String property, String order) {
		String[] strs = new String[] { property, order };
		orders.add(strs);
	}

	public int getFirst() {
		return ((pageNo - 1) * pageSize) + 1;
	}

	public int getNextPage() {
		if (isHasNext())
			return pageNo + 1;
		else
			return pageNo;
	}

	public ArrayList<String[]> getOrders() {
		return orders;
	}

	public int getPageNo() {
		return pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getPrePage() {
		if (isHasPre())
			return pageNo - 1;
		else
			return pageNo;
	}

	public List<T> getResult() {
		return result;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public long getTotalPages() {
		if (totalCount < 0)
			return -1;
		long count = totalCount / pageSize;
		if (totalCount % pageSize > 0) {
			count++;
		}
		return count;
	}

	public boolean isAutoCount() {
		return autoCount;
	}

	public boolean isHasNext() {
		return (pageNo + 1 <= getTotalPages());
	}

	/**
	 * 是否有上一页
	 * 
	 * @return
	 */
	public boolean isHasPre() {
		return (pageNo - 1 >= 1);
	}

	/**
	 * 是否有排序
	 * 
	 * @return
	 */
	public boolean isOrdered() {
		return (!orders.isEmpty());
	}

	public void setAutoCount(final boolean autoCount) {
		this.autoCount = autoCount;
	}

	public void setOrders(ArrayList<String[]> orders) {
		this.orders = orders;
	}

	public void setPageNo(final int pageNo) {
		this.pageNo = pageNo;
		if (pageNo < 1) {
			this.pageNo = 1;
		}
	}

	public void setPageSize(final int pageSize) {
		this.pageSize = pageSize;
		if (pageSize < 1) {
			this.pageSize = 1;
		}
	}

	public void setResult(final List<T> result) {
		this.result = result;
	}

	public void setTotalCount(final long totalCount) {
		this.totalCount = totalCount;
	}
}
