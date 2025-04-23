<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/include/page.jsp" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
  <style>
    body {
      background: #ddd;
    }

    .card {
      margin-top: 3%;
      max-width: 900px;
      width: 90%;
      min-height: 500px;
      box-shadow: 0 6px 20px 0 rgba(0, 0, 0, 0.19);
      border-radius: 1rem;
      border: transparent;
      padding: 2rem;
      background-color: #fff;
    }

    .title b {
      font-size: 1.5rem
    }

    hr {
      margin-top: 1.25rem
    }
  </style>
  <title>회원 정보 수정</title>
  <%@include file="/WEB-INF/view/include/static.jsp" %>
</head>
<body>
<%@include file="/WEB-INF/view/include/header.jsp" %>
<div class="container-fluid">
  <div class="row justify-content-center">
    <div class="card p-5 pr-7 pl-7">
      <div class="text-center mb-1">
        <h2><b>회원 정보 수정</b></h2>
      </div>

      <form action="saveUser.jsp" method="post">
        <div class="mx-auto mb-1" style="max-width: 500px; width: 100%;">
          <label for="id" class="form-label">아이디</label>
          <input type="text" class="form-control bg-light" id="id" name="id" value="${id}" placeholder="아이디를 입력하세요">
        </div>

        <div class="mx-auto mt-1 mb-1" style="max-width: 500px; width: 100%;">
          <label for="email" class="form-label">이메일</label>
          <input type="text" class="form-control bg-light" id="email" name="email" value="${email}" placeholder="이메일을 입력하세요">
        </div>

        <div class="mx-auto mt-1 mb-1" style="max-width: 500px; width: 100%;">
          <label for="password" class="form-label">비밀번호</label>
          <input type="password" class="form-control bg-light" id="password" name="password" value="${password}" placeholder="비밀번호를 입력하세요">
        </div>

        <div class="mx-auto mt-1 mb-1" style="max-width: 500px; width: 100%;">
          <label for="address" class="form-label">주소</label>
          <input type="text" class="form-control bg-light" id="address" name="address" value="${address}" placeholder="주소를 입력하세요">
        </div>

        <div class="mx-auto mt-1 mb-1" style="max-width: 500px; width: 100%;">
          <label for="postCode" class="form-label">우편번호</label>
          <input type="text" class="form-control bg-light" id="postCode" name="postCode" value="${postCode}" placeholder="우편번호를 입력하세요">
        </div>

        <div class="d-flex justify-content-center gap-3 mt-2 pt-2">
          <button type="reset" class="btn btn-outline-secondary" style="width: 150px;">취소</button>
          <button type="submit" class="btn btn-primary" style="width: 150px;">저장</button>
        </div>
      </form>
    </div>
  </div>
</div>
</body>
</html>
