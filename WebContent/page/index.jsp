<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>潮人码头 - 文化产业电子商务平台</title>
<jsp:include page="/resource/inc/web_style.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function() {
		$(".slideGroup .slideBox").slide({
			mainCell : "ul",
			vis : 3,
			autoPlay : true,
			prevCell : ".new-c-prev",
			nextCell : ".new-c-next",
			effect : "leftLoop"
		});
	});
</script>
</head>
<body>
	<jsp:include page="/resource/inc/web_nav.jsp"></jsp:include>
	<div id="wrapper">
		<div id="yc-mod-slider">
			<div class="wrapper">
				<div id="slideshow" class="box_skitter fn-clear">
					<ul>
						<li><a href="http://www.baidu.com" target="_self"><img
								src="${pageContext.request.contextPath}/resource/web/images/banner1.jpg"
								width="100%" height="100%" /></a></li>
						<li><a href="http://www.qq.com" target="_self"><img
								src="${pageContext.request.contextPath}/resource/web/images/banner2.jpg"
								width="100%" height="100%" /></a></li>
					</ul>
				</div>
				<script type="text/javascript"
					src="${pageContext.request.contextPath}/resource/web/js/slideshow.js"></script>
			</div>
		</div>
		<div class="c-task">
			<div class="task-menu m_tb16 clearfix">
				<div class="m_b16 clearfix">
					<h2 class="c-t-title fl">任务大厅</h2>
					<div class="c-t-btn fr">
						<div class="searchbg clearfix">
							<input name="" type="text" placeholder="快速搜索" /> <span
								class="iconsearch"></span>
						</div>
						<a href="#" target="_self" class="grayBtn1a">发任务</a> <a href="#"
							target="_self" class="grayBtn2a">找任务</a>
					</div>
				</div>
				<div class="c-hall-lst">
					<div class="c-warp" style="height: 990px;">
						<ul class="c-hall-carousel task-carousel">
							<li><a target="_self" href="#"
								class="c-hall-logo release-logo" style="left: 0; top: 0;"><img
									src="${pageContext.request.contextPath}/resource/web/images/icon_gongju1.png"
									width="71" height="71" /> <br /> 发任务 </a>
								<div
									style="position: absolute; left: 254px; top: 0; width: 692px; height: 492px;">
									<a target="_self" href="#" class="c-hall-lst-goods"
										style="position: relative;"> <img class="lazy"
										src="${pageContext.request.contextPath}/resource/web/images/pic1.jpg"
										data-original="images/pic1.jpg" width="692">
									</a> <span class="c-hall-lst-goods-info">
										<p class="c-info-t">魔兽斗场(角色成长类)</p>
										<p class="c-info-t">￥3500</p>
										<div class="new-c-personal">
											<div class="new-c-photo">
												<a target="_self" href="#"> <img
													src="${pageContext.request.contextPath}/resource/web/images/touxiang.jpg"
													style="box-sizing: content-box; -moz-box-sizing: content-box; -ms-box-sizing: content-box; width: 31px; height: 31px; border-radius: 15px 15px;">
												</a>
											</div>
											<p>
												<a target="_self" href="#">himan</a>
											</p>
										</div>
									</span>
								</div> <a class="c-hall-logo release-task"
								style="left: 962px; top: 254px;">
									<p class="release-task-t">
										<i class="c-icon-l c-icon-task-s"></i>任务大厅
									</p> <span class="release-task-txt"> 用户发布和承接任务的专业
										平台，支持创意悬赏和项目定制的在线 分包与承接，提供更多创意选择和更多 工作机会。 </span>
							</a> <a target="_self" href="#" class="c-hall-lst-goods"
								style="left: 0px; top: 254px; width: 238px; height: 492px;">
									<img
									src="${pageContext.request.contextPath}/resource/web/images/pic2.jpg">
									<span class="c-hall-lst-goods-info1"
									style="background-color: #baa298;">
										<p class="c-info-t">创意云.任务APP下载 v1.0</p>
										<p class="c-info-txt">触摸文创，掌握天下</p>
										<p class="c-info-txt">每日签到，更有好礼相送</p>
								</span>
							</a> <a target="_self" href="#" class="c-hall-lst-goods"
								style="left: 254px; top: 508px; width: 238px; height: 238px;">
									<img class="lazy"
									src="${pageContext.request.contextPath}/resource/web/images/pic3.jpg"
									data-original="images/pic3.jpg" width="238" height="238">
									<div class="c-hall-lst-goods-bg"></div>
									<div class="c-hall-lst-goods-word">
										<div class="table-cell">
											<p class="c-hall-lst-goods-title">《机械类游戏角色设计》招标</p>
											<p class="c-hall-lst-goods-price">招标：暗标</p>
										</div>
									</div>
							</a> <a target="_self" href="#" class="c-hall-lst-goods"
								style="left: 0px; top: 762px; width: 492px; height: 238px;">
									<img class="lazy"
									src="${pageContext.request.contextPath}/resource/web/images/pic5.jpg"
									data-original="images/pic5.jpg" width="492" height="238">
									<div class="c-hall-lst-goods-bg"></div>
									<div class="c-hall-lst-goods-word">
										<div class="table-cell">
											<p class="c-hall-lst-goods-title">做一个微信的应用</p>
											<p class="c-hall-lst-goods-price">&yen;2,000</p>
										</div>
									</div>
							</a>
								<div
									style="position: absolute; left: 508px; top: 508px; width: 692px; height: 492px;">
									<a target="_self" href="#" class="c-hall-lst-goods"
										style="position: relative;"> <img class="lazy"
										src="${pageContext.request.contextPath}/resource/web/images/pic4.jpg"
										data-original="images/pic4.jpg" width="692">
									</a> <span class="c-hall-lst-goods-info"
										style="background-color: #9ca299;">
										<p class="c-info-t">网页小游戏</p>
										<p class="c-info-t">￥2,500</p>
										<div class="new-c-personal">
											<div class="new-c-photo">
												<a target="_self" href="#"> <img
													src="${pageContext.request.contextPath}/resource/web/images/touxiang2.jpg"
													style="box-sizing: content-box; -moz-box-sizing: content-box; -ms-box-sizing: content-box; width: 31px; height: 31px; border-radius: 15px 15px;">
												</a>
											</div>
											<p>
												<a target="_self" href="#">Lucky</a>
											</p>
										</div>
									</span>
								</div> <a href="javascript:;" class="c-hall-pager-new i-next">下一组<br />
									<img
									src="${pageContext.request.contextPath}/resource/web/images/icon_gongju2.png"
									width="88" height="75" /></a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div class="c-create">
			<img
				src="${pageContext.request.contextPath}/resource/web/images/zxcz.jpg"
				width="100%" height="600" />
			<div class="c-c-info">
				<p class="zxcz">在线创作</p>
				<a href="#" class="redBtn" target="_self">开始体验</a>
			</div>
		</div>
		<div class="c-case clearfix">
			<!--成功案例-->
			<div class="new-c-case slideGroup">
				<div class="new-c-casetitle">
					<h2 class="c-t-title p_t50b20 tac">成功案例</h2>
					<p class="p1 tac">潮人码头与行业众多知名企业保持长期合作关系，积累了丰富的渲染经验，具备其他平台不可比拟的优势。</p>
				</div>
				<div class="new-c-castlist slideBox">
					<a class="new-c-prev" href="javascript:void(0)"></a>
					<ul>
						<li><a target="_self" href="#"> <img class="flazy"
								src="${pageContext.request.contextPath}/resource/web/images/16192314665551a559848c5.jpg"
								width="393" height="252">
								<div class="new-c-caseintro">
									<p class="new-c-casename">赛尔号3之战神联盟</p>
									<p>每个夏天万众瞩目的《赛尔号》系列动画电影中的票房冠军，以1500万成本创造7500万票房收益，而让它能在那个夏天如约而至的正是创意云渲染的逆天效率。</p>
									<span class="new-c-case-bask"></span> <span
										class="new-c-case-year">2013</span>
								</div>
						</a></li>
						<li><a target="_self" href="#"> <img class="flazy"
								src="${pageContext.request.contextPath}/resource/web/images/13931958895551a5a1728ce.jpg"
								width="393" height="252">
								<div class="new-c-caseintro">
									<p class="new-c-casename">龙之谷：破晓奇兵</p>
									<p>由国内顶尖动画制作团队联合好莱坞级制片历时2年倾心打造，创意云为云渲染服务提供商，中美一线原创动画团队强强联手，集魔幻、冒险、爱情于一身。</p>
									<span class="new-c-case-bask"></span> <span
										class="new-c-case-year">2014</span>
								</div>
						</a></li>
						<li><a target="_self" href="#"> <img class="flazy"
								src="${pageContext.request.contextPath}/resource/web/images/17753327125551a87b9162c.jpg"
								width="393" height="252">
								<div class="new-c-caseintro">
									<p class="new-c-casename">美食大冒险</p>
									<p>曾经风靡网络的动画短片，今天在少年儿童中影响深远的动画连续剧，中国的美食文化源远流长，而这些因创意云的小伙伴一个春节前的疯狂变得惟妙惟肖。</p>
									<span class="new-c-case-bask"></span> <span
										class="new-c-case-year">2014</span>
								</div>
						</a></li>
						<li><a target="_self" href="#"> <img class="flazy"
								src="${pageContext.request.contextPath}/resource/web/images/16745730215551a64408edf.jpg"
								width="393" height="252">
								<div class="new-c-caseintro">
									<p class="new-c-casename">神游海空</p>
									<p>观赏《神游海空》即可领略埃菲尔铁塔的巍峨、万里长城的雄伟，飞翔在蓝天之中，漫游于苍茫大海之上，创意云用一夜服务的努力换千万人的沉浸与留恋</p>
									<span class="new-c-case-bask"></span> <span
										class="new-c-case-year">2014</span>
								</div>
						</a></li>
						<li><a target="_self" href="#"> <img class="flazy"
								src="${pageContext.request.contextPath}/resource/web/images/12680122645551a6a5761fa.jpg"
								width="393" height="252">
								<div class="new-c-caseintro">
									<p class="new-c-casename">会飞的恐龙</p>
									<p>中国4D科普电影开山之作，技术、艺术与科学的完美融合，使用创意云提供的云渲染服务，见证恐龙向飞鸟的蜕变，感悟每个生命都是一个不朽的传奇。</p>
									<span class="new-c-case-bask"></span> <span
										class="new-c-case-year">2015</span>
								</div>
						</a></li>
					</ul>
					<a class="new-c-next" href="javascript:void(0)"></a>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/resource/inc/web_foot.jsp"></jsp:include>

</body>
</html>

