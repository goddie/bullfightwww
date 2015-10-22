<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>潮人码头 - 文化产业电子商务平台 - 创意社区</title>

<jsp:include page="/resource/inc/web_style.jsp"></jsp:include>
<script type="text/javascript">
	function showTab_b(num) {
		for (i = 0; i < 5; i++) {
			document.getElementById("tab" + i).style.display = "none";
			document.getElementById("id" + i).className = "noborder";

		}
		document.getElementById("tab" + num).style.display = "block";
		document.getElementById("id" + num).className = "orangeborder";
	}
</script>
</head>
<body>
	<jsp:include page="/resource/inc/web_nav.jsp"></jsp:include>
	<div class="container">
		<div class="row row-offcanvas row-offcanvas-right">
			<div class="col-xs-6 col-sm-3">
				<div class="graybg2">
					<ul class="c-list01">
						<li>
							<dl>
								<dt>创意分享汇</dt>
								<dd>
									<a href="#">模型分享</a><a href="#">视频分享</a><a href="#">教程分享</a><a
										href="#">创意分享</a>
								</dd>
							</dl>
						</li>
						<li>
							<dl>
								<dt>创意大师馆</dt>
								<dd>
									<a href="#">大师讲堂</a><a href="#">大师作品</a><a href="#">大师面对面</a>
								</dd>
							</dl>
						</li>
						<li>
							<dl>
								<dt>创意专题馆</dt>
								<dd>
									<a href="#">影视专题</a><a href="#">动漫专题</a><a href="#">游戏专题</a><a
										href="#">渲染专题</a>
								</dd>
							</dl>
						</li>
						<li>
							<dl>
								<dt>创意嘉年华</dt>
								<dd>
									<a href="#">创意活动区</a><a href="#">水区</a>
								</dd>
							</dl>
						</li>
						<li>
							<dl>
								<dt>创意征集令</dt>
								<dd>
									<a href="#">创意征集令</a>
								</dd>
							</dl>
						</li>
						<li>
							<dl>
								<dt>站务管理</dt>
							</dl>
						</li>
					</ul>
				</div>
			</div>
			<div class="col-xs-12 col-sm-9">
				<div class="m_l20">
					<div class="c-search bluebg m_b19">
						<div class="c-s-select">
							帖子<a href="#" class="iconXx"></a>
						</div>
						<input name="" type="text" placeholder="请输入关键词">
						<div class="c-s-search">
							<span class="iconSearch"></span>
						</div>
					</div>
					<div class="c-focuspic">
						<div id="playBox" style="height: 514px;">
							<div class="pre"></div>
							<div class="next"></div>
							<div class="smalltitle">
								<ul>
									<li class="thistitle"></li>
									<li></li>
									<li></li>
								</ul>
							</div>
							<ul class="oUlplay">
								<li><a href="http://www.lanrentuku.com/" target="_blank"><img
										src="${pageContext.request.contextPath}/resource/web/images/c_banner1.jpg"></a></li>
								<li><a href="http://www.lanrentuku.com/" target="_blank"><img
										src="${pageContext.request.contextPath}/resource/web/images/c_banner1a.jpg"></a></li>
								<li><a href="http://www.lanrentuku.com/" target="_blank"><img
										src="${pageContext.request.contextPath}/resource/web/images/c_banner1b.jpg"></a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<!--/.sidebar-offcanvas-->
		</div>
		<!--/row-->
	</div>
	<div class="container">
		<div class="row row-offcanvas row-offcanvas-right">
			<div class="m_t50">
				<div class="col-xs-12 col-sm-9 p_b20">
					<div class="m_r20">
						<div class="tab">
							<a id="id0" onclick="showTab_b(0)" class="orangeborder">影视</a>|<a
								id="id1" onclick="showTab_b(1)" class="noborder">动漫</a>|<a
								id="id2" onclick="showTab_b(2)" class="noborder">游戏</a>|<a
								id="id3" onclick="showTab_b(3)" class="noborder">设计</a>|<a
								id="id4" onclick="showTab_b(4)" class="noborder">资讯</a>
						</div>
						<div class="tab_content" id="tab0" style="display: block;">
							<ul class="detaillist">
								<li><span class="img-t"><img src="${pageContext.request.contextPath}/resource/web/images/pic10.jpg"
										width="181" height="114"> </span>
									<dl class="info">
										<dt>
											<span class="f_c_0a538e f20">票房爆炸力!《柯南》新剧场版成绩创历史</span>
										</dt>
										<dd>
											<span class="f_c_6cbef5">谢先生</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2015-02-01
										</dd>
										<dd class="p_t20b25">由于此前票房成绩的最高纪录为2014年4月上映的《名侦探柯南
											异次元的狙击手》(41.1亿)，因此在本作品尚未下档之前，打破系列最高票房已成定局。考虑到此前2013年上映的《绝海的侦探》以及2014年的《异次元的狙击手》接连刷新票房纪录，这样一种连续3年实现突破的情况实属罕见。</dd>
										<dd>
											<a href="#" class="f_c_6cbef5">查看全文 》</a>
										</dd>
									</dl></li>
								<li><span class="img-t"><img src="${pageContext.request.contextPath}/resource/web/images/pic10.jpg"
										width="181" height="114"> </span>
									<dl class="info">
										<dt>
											<span class="f_c_0a538e f20">票房爆炸力!《柯南》新剧场版成绩创历史</span>
										</dt>
										<dd>
											<span class="f_c_6cbef5">谢先生</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2015-02-01
										</dd>
										<dd class="p_t20b25">由于此前票房成绩的最高纪录为2014年4月上映的《名侦探柯南
											异次元的狙击手》(41.1亿)，因此在本作品尚未下档之前，打破系列最高票房已成定局。考虑到此前2013年上映的《绝海的侦探》以及2014年的《异次元的狙击手》接连刷新票房纪录，这样一种连续3年实现突破的情况实属罕见。</dd>
										<dd>
											<a href="#" class="f_c_6cbef5">查看全文 》</a>
										</dd>
									</dl></li>
								<li><span class="img-t"><img src="${pageContext.request.contextPath}/resource/web/images/pic10.jpg"
										width="181" height="114"> </span>
									<dl class="info">
										<dt>
											<span class="f_c_0a538e f20">票房爆炸力!《柯南》新剧场版成绩创历史</span>
										</dt>
										<dd>
											<span class="f_c_6cbef5">谢先生</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2015-02-01
										</dd>
										<dd class="p_t20b25">由于此前票房成绩的最高纪录为2014年4月上映的《名侦探柯南
											异次元的狙击手》(41.1亿)，因此在本作品尚未下档之前，打破系列最高票房已成定局。考虑到此前2013年上映的《绝海的侦探》以及2014年的《异次元的狙击手》接连刷新票房纪录，这样一种连续3年实现突破的情况实属罕见。</dd>
										<dd>
											<a href="#" class="f_c_6cbef5">查看全文 》</a>
										</dd>
									</dl></li>
								<li><span class="img-t"><img src="${pageContext.request.contextPath}/resource/web/images/pic10.jpg"
										width="181" height="114"> </span>
									<dl class="info">
										<dt>
											<span class="f_c_0a538e f20">票房爆炸力!《柯南》新剧场版成绩创历史</span>
										</dt>
										<dd>
											<span class="f_c_6cbef5">谢先生</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2015-02-01
										</dd>
										<dd class="p_t20b25">由于此前票房成绩的最高纪录为2014年4月上映的《名侦探柯南
											异次元的狙击手》(41.1亿)，因此在本作品尚未下档之前，打破系列最高票房已成定局。考虑到此前2013年上映的《绝海的侦探》以及2014年的《异次元的狙击手》接连刷新票房纪录，这样一种连续3年实现突破的情况实属罕见。</dd>
										<dd>
											<a href="#" class="f_c_6cbef5">查看全文 》</a>
										</dd>
									</dl></li>
								<li><span class="img-t"><img src="${pageContext.request.contextPath}/resource/web/images/pic10.jpg"
										width="181" height="114"> </span>
									<dl class="info">
										<dt>
											<span class="f_c_0a538e f20">票房爆炸力!《柯南》新剧场版成绩创历史</span>
										</dt>
										<dd>
											<span class="f_c_6cbef5">谢先生</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2015-02-01
										</dd>
										<dd class="p_t20b25">由于此前票房成绩的最高纪录为2014年4月上映的《名侦探柯南
											异次元的狙击手》(41.1亿)，因此在本作品尚未下档之前，打破系列最高票房已成定局。考虑到此前2013年上映的《绝海的侦探》以及2014年的《异次元的狙击手》接连刷新票房纪录，这样一种连续3年实现突破的情况实属罕见。</dd>
										<dd>
											<a href="#" class="f_c_6cbef5">查看全文 》</a>
										</dd>
									</dl></li>
								<li><span class="img-t"><img src="${pageContext.request.contextPath}/resource/web/images/pic10.jpg"
										width="181" height="114"> </span>
									<dl class="info">
										<dt>
											<span class="f_c_0a538e f20">票房爆炸力!《柯南》新剧场版成绩创历史</span>
										</dt>
										<dd>
											<span class="f_c_6cbef5">谢先生</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2015-02-01
										</dd>
										<dd class="p_t20b25">由于此前票房成绩的最高纪录为2014年4月上映的《名侦探柯南
											异次元的狙击手》(41.1亿)，因此在本作品尚未下档之前，打破系列最高票房已成定局。考虑到此前2013年上映的《绝海的侦探》以及2014年的《异次元的狙击手》接连刷新票房纪录，这样一种连续3年实现突破的情况实属罕见。</dd>
										<dd>
											<a href="#" class="f_c_6cbef5">查看全文 》</a>
										</dd>
									</dl></li>
							</ul>
							<div class="pages p_b40 fr">
								<a href="#" class="pCurrent">1</a><a href="#">2</a><a href="#">3</a><a
									href="#">4</a>
							</div>
						</div>
						<div class="tab_content" id="tab1">
							<ul class="detaillist">
								<li><span class="img-t"><img src="${pageContext.request.contextPath}/resource/web/images/pic11.jpg"
										width="181" height="114"> </span>
									<dl class="info">
										<dt>
											<span class="f_c_0a538e f20">票房爆炸力!《柯南》新剧场版成绩创历史</span>
										</dt>
										<dd>
											<span class="f_c_6cbef5">谢先生</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2015-02-01
										</dd>
										<dd class="p_t20b25">由于此前票房成绩的最高纪录为2014年4月上映的《名侦探柯南
											异次元的狙击手》(41.1亿)，因此在本作品尚未下档之前，打破系列最高票房已成定局。考虑到此前2013年上映的《绝海的侦探》以及2014年的《异次元的狙击手》接连刷新票房纪录，这样一种连续3年实现突破的情况实属罕见。</dd>
										<dd>
											<a href="#" class="f_c_6cbef5">查看全文 》</a>
										</dd>
									</dl></li>
								<li><span class="img-t"><img src="${pageContext.request.contextPath}/resource/web/images/pic11.jpg"
										width="181" height="114"> </span>
									<dl class="info">
										<dt>
											<span class="f_c_0a538e f20">票房爆炸力!《柯南》新剧场版成绩创历史</span>
										</dt>
										<dd>
											<span class="f_c_6cbef5">谢先生</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2015-02-01
										</dd>
										<dd class="p_t20b25">由于此前票房成绩的最高纪录为2014年4月上映的《名侦探柯南
											异次元的狙击手》(41.1亿)，因此在本作品尚未下档之前，打破系列最高票房已成定局。考虑到此前2013年上映的《绝海的侦探》以及2014年的《异次元的狙击手》接连刷新票房纪录，这样一种连续3年实现突破的情况实属罕见。</dd>
										<dd>
											<a href="#" class="f_c_6cbef5">查看全文 》</a>
										</dd>
									</dl></li>
								<li><span class="img-t"><img src="${pageContext.request.contextPath}/resource/web/images/pic11.jpg"
										width="181" height="114"> </span>
									<dl class="info">
										<dt>
											<span class="f_c_0a538e f20">票房爆炸力!《柯南》新剧场版成绩创历史</span>
										</dt>
										<dd>
											<span class="f_c_6cbef5">谢先生</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2015-02-01
										</dd>
										<dd class="p_t20b25">由于此前票房成绩的最高纪录为2014年4月上映的《名侦探柯南
											异次元的狙击手》(41.1亿)，因此在本作品尚未下档之前，打破系列最高票房已成定局。考虑到此前2013年上映的《绝海的侦探》以及2014年的《异次元的狙击手》接连刷新票房纪录，这样一种连续3年实现突破的情况实属罕见。</dd>
										<dd>
											<a href="#" class="f_c_6cbef5">查看全文 》</a>
										</dd>
									</dl></li>
								<li><span class="img-t"><img src="${pageContext.request.contextPath}/resource/web/images/pic11.jpg"
										width="181" height="114"> </span>
									<dl class="info">
										<dt>
											<span class="f_c_0a538e f20">票房爆炸力!《柯南》新剧场版成绩创历史</span>
										</dt>
										<dd>
											<span class="f_c_6cbef5">谢先生</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2015-02-01
										</dd>
										<dd class="p_t20b25">由于此前票房成绩的最高纪录为2014年4月上映的《名侦探柯南
											异次元的狙击手》(41.1亿)，因此在本作品尚未下档之前，打破系列最高票房已成定局。考虑到此前2013年上映的《绝海的侦探》以及2014年的《异次元的狙击手》接连刷新票房纪录，这样一种连续3年实现突破的情况实属罕见。</dd>
										<dd>
											<a href="#" class="f_c_6cbef5">查看全文 》</a>
										</dd>
									</dl></li>
								<li><span class="img-t"><img src="${pageContext.request.contextPath}/resource/web/images/pic11.jpg"
										width="181" height="114"> </span>
									<dl class="info">
										<dt>
											<span class="f_c_0a538e f20">票房爆炸力!《柯南》新剧场版成绩创历史</span>
										</dt>
										<dd>
											<span class="f_c_6cbef5">谢先生</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2015-02-01
										</dd>
										<dd class="p_t20b25">由于此前票房成绩的最高纪录为2014年4月上映的《名侦探柯南
											异次元的狙击手》(41.1亿)，因此在本作品尚未下档之前，打破系列最高票房已成定局。考虑到此前2013年上映的《绝海的侦探》以及2014年的《异次元的狙击手》接连刷新票房纪录，这样一种连续3年实现突破的情况实属罕见。</dd>
										<dd>
											<a href="#" class="f_c_6cbef5">查看全文 》</a>
										</dd>
									</dl></li>
								<li><span class="img-t"><img src="${pageContext.request.contextPath}/resource/web/images/pic11.jpg"
										width="181" height="114"> </span>
									<dl class="info">
										<dt>
											<span class="f_c_0a538e f20">票房爆炸力!《柯南》新剧场版成绩创历史</span>
										</dt>
										<dd>
											<span class="f_c_6cbef5">谢先生</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2015-02-01
										</dd>
										<dd class="p_t20b25">由于此前票房成绩的最高纪录为2014年4月上映的《名侦探柯南
											异次元的狙击手》(41.1亿)，因此在本作品尚未下档之前，打破系列最高票房已成定局。考虑到此前2013年上映的《绝海的侦探》以及2014年的《异次元的狙击手》接连刷新票房纪录，这样一种连续3年实现突破的情况实属罕见。</dd>
										<dd>
											<a href="#" class="f_c_6cbef5">查看全文 》</a>
										</dd>
									</dl></li>
							</ul>
							<div class="pages p_b40 fr">
								<a href="#" class="pCurrent">1</a><a href="#">2</a><a href="#">3</a><a
									href="#">4</a>
							</div>
						</div>
						<div class="tab_content" id="tab2">
							<ul class="detaillist">
								<li><span class="img-t"><img src="${pageContext.request.contextPath}/resource/web/images/pic14.jpg"
										width="181" height="114"> </span>
									<dl class="info">
										<dt>
											<span class="f_c_0a538e f20">票房爆炸力!《柯南》新剧场版成绩创历史</span>
										</dt>
										<dd>
											<span class="f_c_6cbef5">谢先生</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2015-02-01
										</dd>
										<dd class="p_t20b25">由于此前票房成绩的最高纪录为2014年4月上映的《名侦探柯南
											异次元的狙击手》(41.1亿)，因此在本作品尚未下档之前，打破系列最高票房已成定局。考虑到此前2013年上映的《绝海的侦探》以及2014年的《异次元的狙击手》接连刷新票房纪录，这样一种连续3年实现突破的情况实属罕见。</dd>
										<dd>
											<a href="#" class="f_c_6cbef5">查看全文 》</a>
										</dd>
									</dl></li>
								<li><span class="img-t"><img src="${pageContext.request.contextPath}/resource/web/images/pic14.jpg"
										width="181" height="114"> </span>
									<dl class="info">
										<dt>
											<span class="f_c_0a538e f20">票房爆炸力!《柯南》新剧场版成绩创历史</span>
										</dt>
										<dd>
											<span class="f_c_6cbef5">谢先生</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2015-02-01
										</dd>
										<dd class="p_t20b25">由于此前票房成绩的最高纪录为2014年4月上映的《名侦探柯南
											异次元的狙击手》(41.1亿)，因此在本作品尚未下档之前，打破系列最高票房已成定局。考虑到此前2013年上映的《绝海的侦探》以及2014年的《异次元的狙击手》接连刷新票房纪录，这样一种连续3年实现突破的情况实属罕见。</dd>
										<dd>
											<a href="#" class="f_c_6cbef5">查看全文 》</a>
										</dd>
									</dl></li>
								<li><span class="img-t"><img src="${pageContext.request.contextPath}/resource/web/images/pic14.jpg"
										width="181" height="114"> </span>
									<dl class="info">
										<dt>
											<span class="f_c_0a538e f20">票房爆炸力!《柯南》新剧场版成绩创历史</span>
										</dt>
										<dd>
											<span class="f_c_6cbef5">谢先生</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2015-02-01
										</dd>
										<dd class="p_t20b25">由于此前票房成绩的最高纪录为2014年4月上映的《名侦探柯南
											异次元的狙击手》(41.1亿)，因此在本作品尚未下档之前，打破系列最高票房已成定局。考虑到此前2013年上映的《绝海的侦探》以及2014年的《异次元的狙击手》接连刷新票房纪录，这样一种连续3年实现突破的情况实属罕见。</dd>
										<dd>
											<a href="#" class="f_c_6cbef5">查看全文 》</a>
										</dd>
									</dl></li>
								<li><span class="img-t"><img src="${pageContext.request.contextPath}/resource/web/images/pic14.jpg"
										width="181" height="114"> </span>
									<dl class="info">
										<dt>
											<span class="f_c_0a538e f20">票房爆炸力!《柯南》新剧场版成绩创历史</span>
										</dt>
										<dd>
											<span class="f_c_6cbef5">谢先生</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2015-02-01
										</dd>
										<dd class="p_t20b25">由于此前票房成绩的最高纪录为2014年4月上映的《名侦探柯南
											异次元的狙击手》(41.1亿)，因此在本作品尚未下档之前，打破系列最高票房已成定局。考虑到此前2013年上映的《绝海的侦探》以及2014年的《异次元的狙击手》接连刷新票房纪录，这样一种连续3年实现突破的情况实属罕见。</dd>
										<dd>
											<a href="#" class="f_c_6cbef5">查看全文 》</a>
										</dd>
									</dl></li>
								<li><span class="img-t"><img src="${pageContext.request.contextPath}/resource/web/images/pic14.jpg"
										width="181" height="114"> </span>
									<dl class="info">
										<dt>
											<span class="f_c_0a538e f20">票房爆炸力!《柯南》新剧场版成绩创历史</span>
										</dt>
										<dd>
											<span class="f_c_6cbef5">谢先生</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2015-02-01
										</dd>
										<dd class="p_t20b25">由于此前票房成绩的最高纪录为2014年4月上映的《名侦探柯南
											异次元的狙击手》(41.1亿)，因此在本作品尚未下档之前，打破系列最高票房已成定局。考虑到此前2013年上映的《绝海的侦探》以及2014年的《异次元的狙击手》接连刷新票房纪录，这样一种连续3年实现突破的情况实属罕见。</dd>
										<dd>
											<a href="#" class="f_c_6cbef5">查看全文 》</a>
										</dd>
									</dl></li>
								<li><span class="img-t"><img src="${pageContext.request.contextPath}/resource/web/images/pic14.jpg"
										width="181" height="114"> </span>
									<dl class="info">
										<dt>
											<span class="f_c_0a538e f20">票房爆炸力!《柯南》新剧场版成绩创历史</span>
										</dt>
										<dd>
											<span class="f_c_6cbef5">谢先生</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2015-02-01
										</dd>
										<dd class="p_t20b25">由于此前票房成绩的最高纪录为2014年4月上映的《名侦探柯南
											异次元的狙击手》(41.1亿)，因此在本作品尚未下档之前，打破系列最高票房已成定局。考虑到此前2013年上映的《绝海的侦探》以及2014年的《异次元的狙击手》接连刷新票房纪录，这样一种连续3年实现突破的情况实属罕见。</dd>
										<dd>
											<a href="#" class="f_c_6cbef5">查看全文 》</a>
										</dd>
									</dl></li>
							</ul>
							<div class="pages p_b40 fr">
								<a href="#" class="pCurrent">1</a><a href="#">2</a><a href="#">3</a><a
									href="#">4</a>
							</div>
						</div>
						<div class="tab_content" id="tab3">
							<ul class="detaillist">
								<li><span class="img-t"><img src="${pageContext.request.contextPath}/resource/web/images/pic13.jpg"
										width="181" height="114"> </span>
									<dl class="info">
										<dt>
											<span class="f_c_0a538e f20">票房爆炸力!《柯南》新剧场版成绩创历史</span>
										</dt>
										<dd>
											<span class="f_c_6cbef5">谢先生</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2015-02-01
										</dd>
										<dd class="p_t20b25">由于此前票房成绩的最高纪录为2014年4月上映的《名侦探柯南
											异次元的狙击手》(41.1亿)，因此在本作品尚未下档之前，打破系列最高票房已成定局。考虑到此前2013年上映的《绝海的侦探》以及2014年的《异次元的狙击手》接连刷新票房纪录，这样一种连续3年实现突破的情况实属罕见。</dd>
										<dd>
											<a href="#" class="f_c_6cbef5">查看全文 》</a>
										</dd>
									</dl></li>
								<li><span class="img-t"><img src="${pageContext.request.contextPath}/resource/web/images/pic13.jpg"
										width="181" height="114"> </span>
									<dl class="info">
										<dt>
											<span class="f_c_0a538e f20">票房爆炸力!《柯南》新剧场版成绩创历史</span>
										</dt>
										<dd>
											<span class="f_c_6cbef5">谢先生</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2015-02-01
										</dd>
										<dd class="p_t20b25">由于此前票房成绩的最高纪录为2014年4月上映的《名侦探柯南
											异次元的狙击手》(41.1亿)，因此在本作品尚未下档之前，打破系列最高票房已成定局。考虑到此前2013年上映的《绝海的侦探》以及2014年的《异次元的狙击手》接连刷新票房纪录，这样一种连续3年实现突破的情况实属罕见。</dd>
										<dd>
											<a href="#" class="f_c_6cbef5">查看全文 》</a>
										</dd>
									</dl></li>
								<li><span class="img-t"><img src="${pageContext.request.contextPath}/resource/web/images/pic13.jpg"
										width="181" height="114"> </span>
									<dl class="info">
										<dt>
											<span class="f_c_0a538e f20">票房爆炸力!《柯南》新剧场版成绩创历史</span>
										</dt>
										<dd>
											<span class="f_c_6cbef5">谢先生</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2015-02-01
										</dd>
										<dd class="p_t20b25">由于此前票房成绩的最高纪录为2014年4月上映的《名侦探柯南
											异次元的狙击手》(41.1亿)，因此在本作品尚未下档之前，打破系列最高票房已成定局。考虑到此前2013年上映的《绝海的侦探》以及2014年的《异次元的狙击手》接连刷新票房纪录，这样一种连续3年实现突破的情况实属罕见。</dd>
										<dd>
											<a href="#" class="f_c_6cbef5">查看全文 》</a>
										</dd>
									</dl></li>
								<li><span class="img-t"><img src="${pageContext.request.contextPath}/resource/web/images/pic13.jpg"
										width="181" height="114"> </span>
									<dl class="info">
										<dt>
											<span class="f_c_0a538e f20">票房爆炸力!《柯南》新剧场版成绩创历史</span>
										</dt>
										<dd>
											<span class="f_c_6cbef5">谢先生</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2015-02-01
										</dd>
										<dd class="p_t20b25">由于此前票房成绩的最高纪录为2014年4月上映的《名侦探柯南
											异次元的狙击手》(41.1亿)，因此在本作品尚未下档之前，打破系列最高票房已成定局。考虑到此前2013年上映的《绝海的侦探》以及2014年的《异次元的狙击手》接连刷新票房纪录，这样一种连续3年实现突破的情况实属罕见。</dd>
										<dd>
											<a href="#" class="f_c_6cbef5">查看全文 》</a>
										</dd>
									</dl></li>
								<li><span class="img-t"><img src="${pageContext.request.contextPath}/resource/web/images/pic13.jpg"
										width="181" height="114"> </span>
									<dl class="info">
										<dt>
											<span class="f_c_0a538e f20">票房爆炸力!《柯南》新剧场版成绩创历史</span>
										</dt>
										<dd>
											<span class="f_c_6cbef5">谢先生</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2015-02-01
										</dd>
										<dd class="p_t20b25">由于此前票房成绩的最高纪录为2014年4月上映的《名侦探柯南
											异次元的狙击手》(41.1亿)，因此在本作品尚未下档之前，打破系列最高票房已成定局。考虑到此前2013年上映的《绝海的侦探》以及2014年的《异次元的狙击手》接连刷新票房纪录，这样一种连续3年实现突破的情况实属罕见。</dd>
										<dd>
											<a href="#" class="f_c_6cbef5">查看全文 》</a>
										</dd>
									</dl></li>
								<li><span class="img-t"><img src="${pageContext.request.contextPath}/resource/web/images/pic13.jpg"
										width="181" height="114"> </span>
									<dl class="info">
										<dt>
											<span class="f_c_0a538e f20">票房爆炸力!《柯南》新剧场版成绩创历史</span>
										</dt>
										<dd>
											<span class="f_c_6cbef5">谢先生</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2015-02-01
										</dd>
										<dd class="p_t20b25">由于此前票房成绩的最高纪录为2014年4月上映的《名侦探柯南
											异次元的狙击手》(41.1亿)，因此在本作品尚未下档之前，打破系列最高票房已成定局。考虑到此前2013年上映的《绝海的侦探》以及2014年的《异次元的狙击手》接连刷新票房纪录，这样一种连续3年实现突破的情况实属罕见。</dd>
										<dd>
											<a href="#" class="f_c_6cbef5">查看全文 》</a>
										</dd>
									</dl></li>
							</ul>
							<div class="pages p_b40 fr">
								<a href="#" class="pCurrent">1</a><a href="#">2</a><a href="#">3</a><a
									href="#">4</a>
							</div>
						</div>
						<div class="tab_content" id="tab4">
							<ul class="detaillist">
								<li><span class="img-t"><img src="${pageContext.request.contextPath}/resource/web/images/pic15.jpg"
										width="181" height="114"> </span>
									<dl class="info">
										<dt>
											<span class="f_c_0a538e f20">票房爆炸力!《柯南》新剧场版成绩创历史</span>
										</dt>
										<dd>
											<span class="f_c_6cbef5">谢先生</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2015-02-01
										</dd>
										<dd class="p_t20b25">由于此前票房成绩的最高纪录为2014年4月上映的《名侦探柯南
											异次元的狙击手》(41.1亿)，因此在本作品尚未下档之前，打破系列最高票房已成定局。考虑到此前2013年上映的《绝海的侦探》以及2014年的《异次元的狙击手》接连刷新票房纪录，这样一种连续3年实现突破的情况实属罕见。</dd>
										<dd>
											<a href="#" class="f_c_6cbef5">查看全文 》</a>
										</dd>
									</dl></li>
								<li><span class="img-t"><img src="${pageContext.request.contextPath}/resource/web/images/pic15.jpg"
										width="181" height="114"> </span>
									<dl class="info">
										<dt>
											<span class="f_c_0a538e f20">票房爆炸力!《柯南》新剧场版成绩创历史</span>
										</dt>
										<dd>
											<span class="f_c_6cbef5">谢先生</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2015-02-01
										</dd>
										<dd class="p_t20b25">由于此前票房成绩的最高纪录为2014年4月上映的《名侦探柯南
											异次元的狙击手》(41.1亿)，因此在本作品尚未下档之前，打破系列最高票房已成定局。考虑到此前2013年上映的《绝海的侦探》以及2014年的《异次元的狙击手》接连刷新票房纪录，这样一种连续3年实现突破的情况实属罕见。</dd>
										<dd>
											<a href="#" class="f_c_6cbef5">查看全文 》</a>
										</dd>
									</dl></li>
								<li><span class="img-t"><img src="${pageContext.request.contextPath}/resource/web/images/pic15.jpg"
										width="181" height="114"> </span>
									<dl class="info">
										<dt>
											<span class="f_c_0a538e f20">票房爆炸力!《柯南》新剧场版成绩创历史</span>
										</dt>
										<dd>
											<span class="f_c_6cbef5">谢先生</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2015-02-01
										</dd>
										<dd class="p_t20b25">由于此前票房成绩的最高纪录为2014年4月上映的《名侦探柯南
											异次元的狙击手》(41.1亿)，因此在本作品尚未下档之前，打破系列最高票房已成定局。考虑到此前2013年上映的《绝海的侦探》以及2014年的《异次元的狙击手》接连刷新票房纪录，这样一种连续3年实现突破的情况实属罕见。</dd>
										<dd>
											<a href="#" class="f_c_6cbef5">查看全文 》</a>
										</dd>
									</dl></li>
								<li><span class="img-t"><img src="${pageContext.request.contextPath}/resource/web/images/pic15.jpg"
										width="181" height="114"> </span>
									<dl class="info">
										<dt>
											<span class="f_c_0a538e f20">票房爆炸力!《柯南》新剧场版成绩创历史</span>
										</dt>
										<dd>
											<span class="f_c_6cbef5">谢先生</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2015-02-01
										</dd>
										<dd class="p_t20b25">由于此前票房成绩的最高纪录为2014年4月上映的《名侦探柯南
											异次元的狙击手》(41.1亿)，因此在本作品尚未下档之前，打破系列最高票房已成定局。考虑到此前2013年上映的《绝海的侦探》以及2014年的《异次元的狙击手》接连刷新票房纪录，这样一种连续3年实现突破的情况实属罕见。</dd>
										<dd>
											<a href="#" class="f_c_6cbef5">查看全文 》</a>
										</dd>
									</dl></li>
								<li><span class="img-t"><img src="${pageContext.request.contextPath}/resource/web/images/pic15.jpg"
										width="181" height="114"> </span>
									<dl class="info">
										<dt>
											<span class="f_c_0a538e f20">票房爆炸力!《柯南》新剧场版成绩创历史</span>
										</dt>
										<dd>
											<span class="f_c_6cbef5">谢先生</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2015-02-01
										</dd>
										<dd class="p_t20b25">由于此前票房成绩的最高纪录为2014年4月上映的《名侦探柯南
											异次元的狙击手》(41.1亿)，因此在本作品尚未下档之前，打破系列最高票房已成定局。考虑到此前2013年上映的《绝海的侦探》以及2014年的《异次元的狙击手》接连刷新票房纪录，这样一种连续3年实现突破的情况实属罕见。</dd>
										<dd>
											<a href="#" class="f_c_6cbef5">查看全文 》</a>
										</dd>
									</dl></li>
								<li><span class="img-t"><img src="${pageContext.request.contextPath}/resource/web/images/pic15.jpg"
										width="181" height="114"> </span>
									<dl class="info">
										<dt>
											<span class="f_c_0a538e f20">票房爆炸力!《柯南》新剧场版成绩创历史</span>
										</dt>
										<dd>
											<span class="f_c_6cbef5">谢先生</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2015-02-01
										</dd>
										<dd class="p_t20b25">由于此前票房成绩的最高纪录为2014年4月上映的《名侦探柯南
											异次元的狙击手》(41.1亿)，因此在本作品尚未下档之前，打破系列最高票房已成定局。考虑到此前2013年上映的《绝海的侦探》以及2014年的《异次元的狙击手》接连刷新票房纪录，这样一种连续3年实现突破的情况实属罕见。</dd>
										<dd>
											<a href="#" class="f_c_6cbef5">查看全文 》</a>
										</dd>
									</dl></li>
							</ul>
							<div class="pages p_b40 fr">
								<a href="#" class="pCurrent">1</a><a href="#">2</a><a href="#">3</a><a
									href="#">4</a>
							</div>
						</div>
					</div>
				</div>
				<div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">
					<div id="jpzt">
						<h2 class="c-title">精品专题</h2>
						<a href="#"><img src="${pageContext.request.contextPath}/resource/web/images/pic16.jpg" width="300"
							height="90"></a>
						<ul class="c-list02">
							<li><a href="#">石泰峰、周乃翔调研蓝海创意云</a></li>
							<li><a href="#">石泰峰、周乃翔调研蓝海创意云</a></li>
							<li><a href="#">石泰峰、周乃翔调研蓝海创意云</a></li>
							<li><a href="#">石泰峰、周乃翔调研蓝海创意云</a></li>
							<li><a href="#">石泰峰、周乃翔调研蓝海创意云</a></li>
							<li><a href="#">石泰峰、周乃翔调研蓝海创意云</a></li>
							<li><a href="#">石泰峰、周乃翔调研蓝海创意云</a></li>
						</ul>
					</div>
					<div id="rywt">
						<h2 class="c-title">热议问题</h2>
						<ul class="c-list02">
							<li><a href="#">石泰峰、周乃翔调研蓝海创意云</a></li>
							<li><a href="#">石泰峰、周乃翔调研蓝海创意云</a></li>
							<li><a href="#">石泰峰、周乃翔调研蓝海创意云</a></li>
							<li><a href="#">石泰峰、周乃翔调研蓝海创意云</a></li>
							<li><a href="#">石泰峰、周乃翔调研蓝海创意云</a></li>
							<li><a href="#">石泰峰、周乃翔调研蓝海创意云</a></li>
							<li><a href="#">石泰峰、周乃翔调研蓝海创意云</a></li>
						</ul>
					</div>
				</div>
			</div>
			<!--/.sidebar-offcanvas-->
		</div>
		<!--/row-->
	</div>
	<jsp:include page="/resource/inc/web_foot.jsp"></jsp:include>
</body>
</html>