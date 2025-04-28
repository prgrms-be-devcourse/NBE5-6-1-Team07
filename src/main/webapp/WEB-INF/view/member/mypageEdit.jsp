<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/include/page.jsp" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="_csrf" content="${_csrf.token}"/>
  <meta name="_csrf_header" content="${_csrf.headerName}"/>

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
  <style>
    body {
      background: #ddd;
    }

    .card {
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
  <title>Hello, world!</title>
  <%@include file="/WEB-INF/view/include/static.jsp" %>
</head>
<body>
<%@include file="/WEB-INF/view/include/header.jsp" %>
<div class="container-fluid">
  <div class="row justify-content-center m-4">
    <h1 class="text-center">Grids & Circle</h1>
  </div>

  <div class="row justify-content-center">
    <div class="card p-5 pr-7 pl-7">
      <div class="text-center mb-1">
        <h2><b>회원 정보 수정</b></h2>
      </div>
      <form:form method="post" action="/member/edit" enctype="multipart/form-data" modelAttribute="customerEditForm">
        <div class="mx-auto mb-1" style="max-width: 500px; width: 100%;">
          <label class="form-label">아이디</label>
          <form:input path="userId" cssClass="form-control bg-light" readonly="true"/>
        </div>

        <div class="mx-auto mt-1 mb-1" style="max-width: 500px; width: 100%;">
          <label for="email" class="form-label">이메일</label>
          <form:input path="email" cssClass="form-control bg-light" placeholder="이메일을 입력하세요"/>
        </div>

        <div class="mx-auto mt-1 mb-1" style="max-width: 500px; width: 100%;">
          <label for="checkpassword" class="form-label">현재 비밀번호</label>
          <div class="input-group">
            <form:input path="checkpassword" type="password" cssClass="form-control bg-light" placeholder="현재 비밀번호를 입력하세요" autocomplete="current-password"/>
            <button type="button" class="btn btn-outline-secondary" id="checkPasswordBtn">비밀번호 확인</button>
          </div>
          <div id="passwordCheckResult" class="mt-2"></div>
        </div>
        <div class="mx-auto mt-1 mb-1" style="max-width: 500px; width: 100%;">
          <label for="password" class="form-label">새 비밀번호</label>
          <form:input path="password" type="password" cssClass="form-control bg-light" placeholder="새 비밀번호를 입력하세요" autocomplete="new-password"/>
        </div>

        <div class="mx-auto mt-1 mb-1" style="max-width: 500px; width: 100%;">
          <label for="address" class="form-label">주소</label>
          <form:input path="address" cssClass="form-control bg-light" placeholder="주소를 입력하세요"/>
        </div>

        <div class="mx-auto mt-1 mb-1" style="max-width: 500px; width: 100%;">
          <label for="postCode" class="form-label">우편번호</label>
          <form:input path="postCode" cssClass="form-control bg-light" placeholder="우편번호를 입력하세요"/>
        </div>

        <div class="d-flex justify-content-center gap-3 mt-2 pt-2">
          <a href="/member/mypage"><button type="button" class="btn btn-outline-secondary" style="width: 150px;">취소</button></a>
          <button type="submit" class="btn btn-primary" style="width: 150px;">저장</button>
        </div>
      </form:form>
    </div>
  </div>
</div>
</body>
<script>
  document.addEventListener('DOMContentLoaded', function() {
    const submitBtn = document.querySelector('button[type="submit"]');
    submitBtn.disabled = true;

    document.getElementById('checkPasswordBtn').addEventListener('click', function() {
      const password = document.getElementsByName('checkpassword')[0].value;
      const userId = document.getElementsByName('userId')[0].value;
      if (!userId || !password) {
        document.getElementById('passwordCheckResult').innerText = '아이디와 비밀번호를 입력하세요.';
        submitBtn.disabled = true;
        return;
      }
      const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
      const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');
      fetch('/member/check-password', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          [csrfHeader]: csrfToken
        },
        body: JSON.stringify({ userId, password })
      })
              .then(res => res.json())
              .then(data => {
                if (data.valid) {
                  document.getElementById('passwordCheckResult').innerText = '비밀번호가 일치합니다.';
                  submitBtn.disabled = false; // 활성화
                } else {
                  document.getElementById('passwordCheckResult').innerText = '비밀번호가 일치하지 않습니다.';
                  submitBtn.disabled = true; // 비활성화 유지
                }
              })
              .catch(() => {
                document.getElementById('passwordCheckResult').innerText = '오류가 발생했습니다.';
                submitBtn.disabled = true;
              });
    });
  });
</script>
</html>