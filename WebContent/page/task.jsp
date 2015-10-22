<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>潮人码头 - 文化产业电子商务平台 - 任务大厅</title>

<jsp:include page="/resource/inc/web_style.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/resource/inc/web_nav.jsp"></jsp:include>
<div class="container">
  <div class="row row-offcanvas row-offcanvas-right">
    <div class="col-xs-12 col-sm-2" id="seriveClassify">
      <h2 class="t-title1">服务分类</h2>
      <div class="redbg">
        <ul class="t-list01">
          <li>
          	<a href="#">影视动画</a>
          	<ul>
            	<li><a href="#">二维动画</a></li>
                <li><a href="#">三维动画</a></li>
                <li><a href="#">影视后期</a></li>
                <li><a href="#">宣传片</a></li>
                <li><a href="#">广告片</a></li>
                <li><a href="#">栏目包装</a></li>
                <li><a href="#">建筑表现</a></li>
                <li><a href="#">其他</a></li>
                <li><a href="#">更多</a></li>
            </ul>
          </li>
          <li>
          	<a href="#">游戏制作</a>
          	<ul>
            	<li><a href="#">原画设计</a></li>
                <li><a href="#">人物建模</a></li>
                <li><a href="#">场景建模</a></li>
                <li><a href="#">动作设计</a></li>
                <li><a href="#">游戏特效</a></li>
                <li><a href="#">UI设计</a></li>
                <li><a href="#">服务端开发</a></li>
                <li><a href="#">客户端开发</a></li>
                <li><a href="#">更多</a></li>
            </ul>
          </li>
          <li>
          	<a href="#">平面设计</a>
            <ul>
            	<li><a href="#">图标设计</a></li>
                <li><a href="#">UI设计</a></li>
                <li><a href="#">LOGO设计</a></li>
                <li><a href="#">字体设计</a></li>
                <li><a href="#">表情设计</a></li>
                <li><a href="#">广告设计</a></li>
                <li><a href="#">海报设计</a></li>
                <li><a href="#">宣传册页</a></li>
                <li><a href="#">招牌/门头设计</a></li>
                <li><a href="#">台历设计</a></li>
                <li><a href="#">更多</a></li>
            </ul>
          </li>
          <li>
          	<a href="#">建筑设计</a>
          	<ul>
            	<li><a href="#">家居装修</a></li>
                <li><a href="#">办公室装修</a></li>
                <li><a href="#">写字楼装修</a></li>
                <li><a href="#">餐厅装修</a></li>
                <li><a href="#">店铺商场装修</a></li>
                <li><a href="#">门面装修</a></li>
                <li><a href="#">酒吧装修</a></li>
                <li><a href="#">会所装修</a></li>
                <li><a href="#">公共建筑设计</a></li>
                <li><a href="#">住宅建筑设计</a></li>
                <li><a href="#">更多</a></li>
            </ul>
          </li>
          <li>
          	<a href="#">网站建设</a>
          	<ul>
            	<li><a href="#">网站制作</a></li>
                <li><a href="#">web应用开发</a></li>
                <li><a href="#">电子商务网站开发</a></li>
                <li><a href="#">网站前端开发</a></li>
                <li><a href="#">数据库设计</a></li>
                <li><a href="#">网站维护</a></li>
                <li><a href="#">网站测试</a></li>
                <li><a href="#">开放平台</a></li>
                <li><a href="#">其他</a></li>
                <li><a href="#">更多</a></li>
            </ul>
          </li>
          <li>
          	<a href="#">软件开发</a>
          	<ul>
            	<li><a href="#">桌面软件</a></li>
                <li><a href="#">多媒体软件</a></li>
                <li><a href="#">企业软件ERP</a></li>
                <li><a href="#">办公自动化OA</a></li>
                <li><a href="#">客户关系管理CRM</a></li>
                <li><a href="#">脚本制作</a></li>
                <li><a href="#">插件制作</a></li>
                <li><a href="#">其他</a></li>
                <li><a href="#">更多</a></li>
            </ul>
          </li>
          <li>
          	<a href="#">移动应用</a>
          	<ul>
            	<li><a href="#">Android开发</a></li>
                <li><a href="#">iphone</a></li>
                <li><a href="#">ipad</a></li>
                <li><a href="#">WindowsPhone</a></li>
                <li><a href="#">应用汉化</a></li>
                <li><a href="#">其他</a></li>
                <li><a href="#">更多</a></li>
            </ul>
          </li>
          <li>
          	<a href="#">方案策划</a>
          	<ul>
            	<li><a href="#">广告策划</a></li>
                <li><a href="#">活动策划</a></li>
                <li><a href="#">促销策划</a></li>
                <li><a href="#">会展策划</a></li>
                <li><a href="#">婚庆策划</a></li>
                <li><a href="#">栏目包装</a></li>
                <li><a href="#">影视策划</a></li>
                <li><a href="#">其他</a></li>
                <li><a href="#">更多</a></li>
            </ul>
          </li>
          <li><a href="#">更多</a></li>
        </ul>
      </div>
    </div>
    <div class="col-xs-12 col-sm-10">
      <div class="m_l20">
        <div class="col-xs-12 col-sm-8 m_b19">
          <div class="c-search graybg3">
            <div class="c-s-select">帖子<a href="#" class="iconXx"></a></div>
            <input name="" type="text" placeholder="请输入关键词">
            <div class="c-s-search"><span class="iconSearch"></span></div>
          </div>
        </div>
        <div class="col-xs-12 col-sm-4"> <a href="#" class="grayBtn2 m_l20">发任务</a><a href="#" class="grayBtn2 m_l10">项目招标</a><a href="#" class="grayBtn2 m_l10">直接雇佣</a> </div>
        <div class="col-xs-12">
          <div id="playBox" style="height:452px;">
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
              <li><a href="http://www.lanrentuku.com/" target="_blank"><img src="${pageContext.request.contextPath}/resource/web/images//t_banner1.jpg"></a></li>
              <li><a href="http://www.lanrentuku.com/" target="_blank"><img src="${pageContext.request.contextPath}/resource/web/images//t_banner2.jpg"></a></li>
              <li><a href="http://www.lanrentuku.com/" target="_blank"><img src="${pageContext.request.contextPath}/resource/web/images//t_banner3.jpg"></a></li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<div class="graybg2">
  <div class="container m_t30">
    <div class="row row-offcanvas row-offcanvas-right m_b19 p_t25">
      <h2 class="t-title2">推荐任务</h2>
      <div class="col-xs-12">
        <div class="col-xs-12 col-sm-4">
          <div class="tjrwBorder m_r35b20">
            <h3 class="t-title3">影视动画任务</h3>
            <ul class="t-list02">
              <li><span class="t-price">-暗标-</span><span class="t-info"><a href="#">XXXXXX项目制作中</a><b class="iconHot"></b></span><span class="t-number"><img src="${pageContext.request.contextPath}/resource/web/images//icon_touxiang.png" width="14" height="14">13人</span></li>
              <li><span class="t-price">-暗标-</span><span class="t-info"><a href="#">XXXXXXBBBB项目制作中</a><b class="iconHot"></b></span><span class="t-number"><img src="${pageContext.request.contextPath}/resource/web/images//icon_touxiang.png" width="14" height="14">13人</span></li>
              <li><span class="t-price">￥65,5000</span><span class="t-info"><a href="#">XXXXXX项目制作中</a><b class="iconHot"></b></span><span class="t-number"><img src="${pageContext.request.contextPath}/resource/web/images//icon_touxiang.png" width="14" height="14">13人</span></li>
              <li><span class="t-price">-暗标-</span><span class="t-info"><a href="#">XXXXXX项目制作中</a><b class="iconHot"></b></span><span class="t-number"><img src="${pageContext.request.contextPath}/resource/web/images//icon_touxiang.png" width="14" height="14">13人</span></li>
              <li><span class="t-price">-暗标-</span><span class="t-info"><a href="#">XXXXXX项目制作中</a><b class="iconHot"></b></span><span class="t-number"><img src="${pageContext.request.contextPath}/resource/web/images//icon_touxiang.png" width="14" height="14">13人</span></li>
            </ul>
          </div>
        </div>
        <div class="col-xs-12 col-sm-4">
          <div class="tjrwBorder m_r35b20">
            <h3 class="t-title3">影视动画任务</h3>
            <ul class="t-list02">
              <li><span class="t-price">-暗标-</span><span class="t-info"><a href="#">XXXXXX项目制作中</a><b class="iconHot"></b></span><span class="t-number"><img src="${pageContext.request.contextPath}/resource/web/images//icon_touxiang.png" width="14" height="14">13人</span></li>
              <li><span class="t-price">-暗标-</span><span class="t-info"><a href="#">XXXXXXBBBB项目制作中</a><b class="iconHot"></b></span><span class="t-number"><img src="${pageContext.request.contextPath}/resource/web/images//icon_touxiang.png" width="14" height="14">13人</span></li>
              <li><span class="t-price">￥65,5000</span><span class="t-info"><a href="#">XXXXXX项目制作中</a><b class="iconHot"></b></span><span class="t-number"><img src="${pageContext.request.contextPath}/resource/web/images//icon_touxiang.png" width="14" height="14">13人</span></li>
              <li><span class="t-price">-暗标-</span><span class="t-info"><a href="#">XXXXXX项目制作中</a><b class="iconHot"></b></span><span class="t-number"><img src="${pageContext.request.contextPath}/resource/web/images//icon_touxiang.png" width="14" height="14">13人</span></li>
              <li><span class="t-price">-暗标-</span><span class="t-info"><a href="#">XXXXXX项目制作中</a><b class="iconHot"></b></span><span class="t-number"><img src="${pageContext.request.contextPath}/resource/web/images//icon_touxiang.png" width="14" height="14">13人</span></li>
            </ul>
          </div>
        </div>
        <div class="col-xs-12 col-sm-4">
          <div class="tjrwBorder">
            <h3 class="t-title3">影视动画任务</h3>
            <ul class="t-list02">
              <li><span class="t-price">-暗标-</span><span class="t-info"><a href="#">XXXXXX项目制作中</a><b class="iconHot"></b></span><span class="t-number"><img src="${pageContext.request.contextPath}/resource/web/images//icon_touxiang.png" width="14" height="14">13人</span></li>
              <li><span class="t-price">-暗标-</span><span class="t-info"><a href="#">XXXXXXBBBB项目制作中</a><b class="iconHot"></b></span><span class="t-number"><img src="${pageContext.request.contextPath}/resource/web/images//icon_touxiang.png" width="14" height="14">13人</span></li>
              <li><span class="t-price">￥65,5000</span><span class="t-info"><a href="#">XXXXXX项目制作中</a><b class="iconHot"></b></span><span class="t-number"><img src="${pageContext.request.contextPath}/resource/web/images//icon_touxiang.png" width="14" height="14">13人</span></li>
              <li><span class="t-price">-暗标-</span><span class="t-info"><a href="#">XXXXXX项目制作中</a><b class="iconHot"></b></span><span class="t-number"><img src="${pageContext.request.contextPath}/resource/web/images//icon_touxiang.png" width="14" height="14">13人</span></li>
              <li><span class="t-price">-暗标-</span><span class="t-info"><a href="#">XXXXXX项目制作中</a><b class="iconHot"></b></span><span class="t-number"><img src="${pageContext.request.contextPath}/resource/web/images//icon_touxiang.png" width="14" height="14">13人</span></li>
            </ul>
          </div>
        </div>
      </div>
      <div class="col-xs-12">
        <div class="col-xs-12 col-sm-4">
          <div class="tjrwBorder m_r35b20">
            <h3 class="t-title3">影视动画任务</h3>
            <ul class="t-list02">
              <li><span class="t-price">-暗标-</span><span class="t-info"><a href="#">XXXXXX项目制作中</a><b class="iconHot"></b></span><span class="t-number"><img src="${pageContext.request.contextPath}/resource/web/images//icon_touxiang.png" width="14" height="14">13人</span></li>
              <li><span class="t-price">-暗标-</span><span class="t-info"><a href="#">XXXXXXBBBB项目制作中</a><b class="iconHot"></b></span><span class="t-number"><img src="${pageContext.request.contextPath}/resource/web/images//icon_touxiang.png" width="14" height="14">13人</span></li>
              <li><span class="t-price">￥65,5000</span><span class="t-info"><a href="#">XXXXXX项目制作中</a><b class="iconHot"></b></span><span class="t-number"><img src="${pageContext.request.contextPath}/resource/web/images//icon_touxiang.png" width="14" height="14">13人</span></li>
              <li><span class="t-price">-暗标-</span><span class="t-info"><a href="#">XXXXXX项目制作中</a><b class="iconHot"></b></span><span class="t-number"><img src="${pageContext.request.contextPath}/resource/web/images//icon_touxiang.png" width="14" height="14">13人</span></li>
              <li><span class="t-price">-暗标-</span><span class="t-info"><a href="#">XXXXXX项目制作中</a><b class="iconHot"></b></span><span class="t-number"><img src="${pageContext.request.contextPath}/resource/web/images//icon_touxiang.png" width="14" height="14">13人</span></li>
            </ul>
          </div>
        </div>
        <div class="col-xs-12 col-sm-4">
          <div class="tjrwBorder m_r35b20">
            <h3 class="t-title3">影视动画任务</h3>
            <ul class="t-list02">
              <li><span class="t-price">-暗标-</span><span class="t-info"><a href="#">XXXXXX项目制作中</a><b class="iconHot"></b></span><span class="t-number"><img src="${pageContext.request.contextPath}/resource/web/images//icon_touxiang.png" width="14" height="14">13人</span></li>
              <li><span class="t-price">-暗标-</span><span class="t-info"><a href="#">XXXXXXBBBB项目制作中</a><b class="iconHot"></b></span><span class="t-number"><img src="${pageContext.request.contextPath}/resource/web/images//icon_touxiang.png" width="14" height="14">13人</span></li>
              <li><span class="t-price">￥65,5000</span><span class="t-info"><a href="#">XXXXXX项目制作中</a><b class="iconHot"></b></span><span class="t-number"><img src="${pageContext.request.contextPath}/resource/web/images//icon_touxiang.png" width="14" height="14">13人</span></li>
              <li><span class="t-price">-暗标-</span><span class="t-info"><a href="#">XXXXXX项目制作中</a><b class="iconHot"></b></span><span class="t-number"><img src="${pageContext.request.contextPath}/resource/web/images//icon_touxiang.png" width="14" height="14">13人</span></li>
              <li><span class="t-price">-暗标-</span><span class="t-info"><a href="#">XXXXXX项目制作中</a><b class="iconHot"></b></span><span class="t-number"><img src="${pageContext.request.contextPath}/resource/web/images//icon_touxiang.png" width="14" height="14">13人</span></li>
            </ul>
          </div>
        </div>
        <div class="col-xs-12 col-sm-4">
          <div class="tjrwBorder">
            <h3 class="t-title3">影视动画任务</h3>
            <ul class="t-list02">
              <li><span class="t-price">-暗标-</span><span class="t-info"><a href="#">XXXXXX项目制作中</a><b class="iconHot"></b></span><span class="t-number"><img src="${pageContext.request.contextPath}/resource/web/images//icon_touxiang.png" width="14" height="14">13人</span></li>
              <li><span class="t-price">-暗标-</span><span class="t-info"><a href="#">XXXXXXBBBB项目制作中</a><b class="iconHot"></b></span><span class="t-number"><img src="${pageContext.request.contextPath}/resource/web/images//icon_touxiang.png" width="14" height="14">13人</span></li>
              <li><span class="t-price">￥65,5000</span><span class="t-info"><a href="#">XXXXXX项目制作中</a><b class="iconHot"></b></span><span class="t-number"><img src="${pageContext.request.contextPath}/resource/web/images//icon_touxiang.png" width="14" height="14">13人</span></li>
              <li><span class="t-price">-暗标-</span><span class="t-info"><a href="#">XXXXXX项目制作中</a><b class="iconHot"></b></span><span class="t-number"><img src="${pageContext.request.contextPath}/resource/web/images//icon_touxiang.png" width="14" height="14">13人</span></li>
              <li><span class="t-price">-暗标-</span><span class="t-info"><a href="#">XXXXXX项目制作中</a><b class="iconHot"></b></span><span class="t-number"><img src="${pageContext.request.contextPath}/resource/web/images//icon_touxiang.png" width="14" height="14">13人</span></li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<div class="container p_t25">
  <div class="row row-offcanvas row-offcanvas-right">
    <h2 class="t-title2">个人服务商</h2>
    <div class="col-xs-12 col-sm-3">
      <div class="grayborder m_lr15_b25"> <a href="#"><img src="${pageContext.request.contextPath}/resource/web/images//pic18.jpg" width="100%" height="126"></a>
        <div class="m_lr15_b25 tac">
          <p class="p_b5 f20"><a class="f_c_0a538e" href="#">Buttons</a></p>
          <p class="p_b10">css按钮样式库</p>
          <p>css按钮样式库按钮样式库按钮样式库按钮样式库按钮样式库按钮样式库按钮样式库</p>
        </div>
      </div>
    </div>
    <div class="col-xs-12 col-sm-3">
      <div class="grayborder m_lr15_b25"> <a href="#"><img src="${pageContext.request.contextPath}/resource/web/images//pic18.jpg" width="100%" height="126"></a>
        <div class="m_lr15_b25 tac">
          <p class="p_b5 f20"><a class="f_c_0a538e" href="#">Buttons</a></p>
          <p class="p_b10">css按钮样式库</p>
          <p>css按钮样式库按钮样式库按钮样式库按钮样式库按钮样式库按钮样式库按钮样式库</p>
        </div>
      </div>
    </div>
    <div class="col-xs-12 col-sm-3">
      <div class="grayborder m_lr15_b25"> <a href="#"><img src="${pageContext.request.contextPath}/resource/web/images//pic18.jpg" width="100%" height="126"></a>
        <div class="m_lr15_b25 tac">
          <p class="p_b5 f20"><a class="f_c_0a538e" href="#">Buttons</a></p>
          <p class="p_b10">css按钮样式库</p>
          <p>css按钮样式库按钮样式库按钮样式库按钮样式库按钮样式库按钮样式库按钮样式库</p>
        </div>
      </div>
    </div>
    <div class="col-xs-12 col-sm-3">
      <div class="grayborder m_lr15_b25"> <a href="#"><img src="${pageContext.request.contextPath}/resource/web/images//pic18.jpg" width="100%" height="126"></a>
        <div class="m_lr15_b25 tac">
          <p class="p_b5 f20"><a class="f_c_0a538e" href="#">Buttons</a></p>
          <p class="p_b10">css按钮样式库</p>
          <p>css按钮样式库按钮样式库按钮样式库按钮样式库按钮样式库按钮样式库按钮样式库</p>
        </div>
      </div>
    </div>
  </div>
</div>
<div class="container p_b40">
  <div class="row row-offcanvas row-offcanvas-right">
    <h2 class="t-title2">企业服务商</h2>
    <div class="col-xs-12 col-sm-3">
      <div class="grayborder m_lr15_b25"> <a href="#"><img src="${pageContext.request.contextPath}/resource/web/images//pic19.jpg" width="100%" height="126"></a>
        <div class="m_lr15_b25 tac">
          <p class="p_b5 f20"><a class="f_c_0a538e" href="#">Buttons</a></p>
          <p class="p_b10">css按钮样式库</p>
          <p>css按钮样式库按钮样式库按钮样式库按钮样式库按钮样式库按钮样式库按钮样式库</p>
        </div>
      </div>
    </div>
    <div class="col-xs-12 col-sm-3">
      <div class="grayborder m_lr15_b25"> <a href="#"><img src="${pageContext.request.contextPath}/resource/web/images//pic19.jpg" width="100%" height="126"></a>
        <div class="m_lr15_b25 tac">
          <p class="p_b5 f20"><a class="f_c_0a538e" href="#">Buttons</a></p>
          <p class="p_b10">css按钮样式库</p>
          <p>css按钮样式库按钮样式库按钮样式库按钮样式库按钮样式库按钮样式库按钮样式库</p>
        </div>
      </div>
    </div>
    <div class="col-xs-12 col-sm-3">
      <div class="grayborder m_lr15_b25"> <a href="#"><img src="${pageContext.request.contextPath}/resource/web/images//pic19.jpg" width="100%" height="126"></a>
        <div class="m_lr15_b25 tac">
          <p class="p_b5 f20"><a class="f_c_0a538e" href="#">Buttons</a></p>
          <p class="p_b10">css按钮样式库</p>
          <p>css按钮样式库按钮样式库按钮样式库按钮样式库按钮样式库按钮样式库按钮样式库</p>
        </div>
      </div>
    </div>
    <div class="col-xs-12 col-sm-3">
      <div class="grayborder m_lr15_b25"> <a href="#"><img src="${pageContext.request.contextPath}/resource/web/images//pic19.jpg" width="100%" height="126"></a>
        <div class="m_lr15_b25 tac">
          <p class="p_b5 f20"><a class="f_c_0a538e" href="#">Buttons</a></p>
          <p class="p_b10">css按钮样式库</p>
          <p>css按钮样式库按钮样式库按钮样式库按钮样式库按钮样式库按钮样式库按钮样式库</p>
        </div>
      </div>
    </div>
  </div>
</div>

<!--footer-->
<jsp:include page="/resource/inc/web_foot.jsp"></jsp:include>

</body>
</html>