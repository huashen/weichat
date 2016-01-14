<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>wechat</title>

  <link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.5.7/css/sm.min.css">
  <script type='text/javascript' src='//g.alicdn.com/sj/lib/zepto/zepto.min.js' charset='utf-8'></script>
  <script type='text/javascript' src='//g.alicdn.com/msui/sm/0.5.7/js/sm.min.js' charset='utf-8'></script>

</head>
<body>
<!-- page 容器 -->
<div class="page">
  <!-- 标题栏 -->
  <header class="bar bar-nav">
    <a class="icon icon-me pull-left open-panel"></a>
    <h1 class="title">wechat</h1>
  </header>

  <!-- 工具栏 -->
  <nav class="bar bar-tab">
    <a class="tab-item external active" href="#">
      <span class="icon icon-message"></span>
      <span class="tab-label">消息</span>
    </a>
    <a class="tab-item external" href="http://www.baidu.com">
      <span class="icon icon-friends"></span>
      <span class="tab-label">联系人</span>
    </a>
    <a class="tab-item external" href="#search">
      <span class="icon icon-search"></span>
      <span class="tab-label">发现</span>
    </a>
    <a class="tab-item external" href="#">
      <span class="icon icon-settings"></span>
      <span class="tab-label">我</span>
    </a>
  </nav>

  <!-- 这里是页面内容区 -->
  <div class="content">
    <div class="content-block">这里是content</div>

    <div class="search" id="search">这里是content</div>
  </div>
</div>

<!-- popup, panel 等放在这里 -->
<div class="panel-overlay"></div>
<!-- Left Panel with Reveal effect -->
<div class="panel panel-left panel-reveal">
  <div class="content-block">
    <p>这是一个侧栏</p>
    <p></p>
    <!-- Click on link with "close-panel" class will close panel -->
    <p><a href="#" class="close-panel">关闭</a></p>
  </div>
</div>
</body>
</html>
