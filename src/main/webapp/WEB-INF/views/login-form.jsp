<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport"
        content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>login-form.jsp</title>
</head>
<body>

<h1>로그인</h1>
<form action="/pages/login" method="post">
  <div>
    <label for="email">이메일: </label>
    <input type="email" id="email" name="email" placeholder="example@google.com">
  </div>
  <div>
    <label for="password">비밀번호: </label>
    <input type="password" id="password" name="password">
  </div>
  <button type="submit">로그인</button>
</form>

</body>
</html>
