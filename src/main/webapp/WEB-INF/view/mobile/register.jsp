<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>注册</title>

  <link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.5.7/css/sm.min.css">
  <script type='text/javascript' src='//g.alicdn.com/sj/lib/zepto/zepto.min.js' charset='utf-8'></script>
  <script type='text/javascript' src='//g.alicdn.com/msui/sm/0.5.7/js/sm.min.js' charset='utf-8'></script>

</head>
<body>
<div class="page">
  <header class="bar bar-nav">
    <h1 class='title'>注册</h1>
  </header>
  <div class="content">
    <form action="${pageContext.request.contextPath}/admin/user/register.json" method="post" id="Form">
      <input type="hidden" name="avatarId" value="1">
      <input type="hidden" name="signature" value="signature">
      <div class="list-block">
        <ul>
          <li>
            <div class="item-content">
              <div class="item-media"><i class="icon icon-form-name"></i></div>
              <div class="item-inner">
                <div class="item-title label">昵称</div>
                <div class="item-input">
                  <input type="text" placeholder="昵称" name="name">
                </div>
              </div>
            </div>
          </li>

          <li>
            <div class="item-content">
              <div class="item-media"><i class="icon icon-form-name"></i></div>
              <div class="item-inner">
                <div class="item-title label">账号</div>
                <div class="item-input">
                  <input type="text" placeholder="账号" name="account">
                </div>
              </div>
            </div>
          </li>

          <li>
            <div class="item-content">
              <div class="item-media"><i class="icon icon-form-password"></i></div>
              <div class="item-inner">
                <div class="item-title label">密码</div>
                <div class="item-input">
                  <input type="password" placeholder="密码" name="password">
                </div>
              </div>
            </div>
          </li>

          <li>
            <div class="item-content">
              <div class="item-media"><i class="icon icon-form-gender"></i></div>
              <div class="item-inner">
                <div class="item-title label">性别</div>
                <div class="item-input">
                  <select name="gender">
                    <option value="1">男</option>
                    <option value="0">女</option>
                  </select>
                </div>
              </div>
            </div>
          </li>

          <li>
            <div class="item-content">
              <div class="item-media"><i class="icon icon-form-calendar"></i></div>
              <div class="item-inner">
                <div class="item-title label">生日</div>
                <div class="item-input">
                  <input type="date" placeholder="Birth day" value="2014-04-30" name="birthday">
                </div>
              </div>
            </div>
          </li>
        </ul>
      </div>
      <div class="content-block">
        <div class="row">
          <div class="col-100"><a class="button button-big button-fill button-success" onclick="submit();">提交</a></div>
        </div>
      </div>
   </form>
  </div>
</div>

<script>
    function submit() {
      $.ajax({
           url:"${pageContext.request.contextPath}/admin/user/register.json",
           type:"post",
           data:$('#Form').serialize(),
           dataType:"json",
           success: function(msg) {
              console.log(msg);
              if(msg.success) {

              } else {
                alert(msg.message);
              }
           },
           error: function () {
             alert("服务器出错！");
           }
      });
    }
</script>
</body>
</html>
