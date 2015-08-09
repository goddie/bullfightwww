package com.xiaba2.cms.domain;

import javax.persistence.*;

import com.xiaba2.core.BaseUUIDEntity;

/**
 * @author goddie 文章
 */
@Entity
@Table(name = "db_cms_article_body")
public class ArticleBody extends BaseUUIDEntity {

	/**
	 * 文章分类
	 */
	@OneToOne(cascade = CascadeType.ALL)
	private Article article;

	/**
	 * 内容
	 */
	@Column(length = 1000)
	private String body;

	/**
	 * 自定义模板
	 */
	@Column
	private String template;

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}


	

	

}
