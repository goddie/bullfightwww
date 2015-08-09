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
                                <li class="active"><a href="${pageContext.request.contextPath}/page/ucenter_userconfig.jsp">账户设置</a></li>
                                <li><a href="${pageContext.request.contextPath}/page/admin_user.jsp">用户管理</a></li>
                                <li><a href="${pageContext.request.contextPath}/page/admin_fund.jsp">基金管理</a></li>
                                <li><a href="${pageContext.request.contextPath}/page/admin_traderecord.jsp">交易记录</a></li>
                                <li><a href="${pageContext.request.contextPath}/page/admin_popular.jsp">投资猫达人</a></li>
                            </ul>
                        </div><!-- /.navbar-collapse -->
                    </div>
                </div>
            </div><!-- /.container -->
        </nav>