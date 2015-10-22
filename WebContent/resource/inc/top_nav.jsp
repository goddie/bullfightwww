<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- small navbar -->
	<nav
		class="navbar navbar-default navbar-fixed-top bootstrap-admin-navbar-sm"
		role="navigation"> </nav>
	<nav class="navbar navbar-default navbar-fixed-top bootstrap-admin-navbar bootstrap-admin-navbar-under-small" role="navigation">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".main-navbar-collapse">
                                <span class="sr-only">Toggle navigation</span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>
                            <a class="navbar-brand" href="#">后台管理</a>
                        </div>
                        <div class="collapse navbar-collapse main-navbar-collapse">
                            <ul class="nav navbar-nav">
                                
                                <li><a href="${pageContext.request.contextPath}/user/admin/list?p=1">球员管理</a></li>
                                <li><a href="${pageContext.request.contextPath}/team/admin/list?p=1">球队管理</a></li>
                                <li><a href="${pageContext.request.contextPath}/arena/admin/list?p=1">场地管理</a></li>
                                <li><a href="${pageContext.request.contextPath}/matchfight/admin/list?p=1">对战管理</a></li>
                                <li><a href="${pageContext.request.contextPath}/article/admin/list?p=1">文章管理</a></li>
                                <li><a href="${pageContext.request.contextPath}/payrecord/admin/list?p=1">交易记录</a></li>
                                <li><a href="${pageContext.request.contextPath}/page/ucenter_userconfig.jsp">账户设置</a></li>
                            </ul>
                        </div><!-- /.navbar-collapse -->
                    </div>
                </div>
            </div><!-- /.container -->
        </nav>