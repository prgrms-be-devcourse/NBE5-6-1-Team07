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
  <title>회원 가입</title>
  <%@include file="/WEB-INF/view/include/static.jsp" %>
</head>
<body>
<%@include file="/WEB-INF/view/include/header.jsp" %>
<main>
<div class="container-fluid">
  <div class="row justify-content-center">
    <div class="card p-5 pr-7 pl-7">
      <div class="text-center mb-1">
        <h2><b>회원가입</b></h2>
      </div>
      <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger" role="alert">
            ${errorMessage}
        </div>
      </c:if>
      <form:form modelAttribute="signupForm" action="/member/signup" method="post" id="signupForm">
        <div class="mx-auto mb-1" style="max-width: 500px; width: 100%;">
          <label for="userId" class="form-label">아이디</label>
          <form:input class="form-control bg-light" id="userId" path="userId" placeholder="아이디를 입력하세요"/>
          <form:errors path="userId" cssClass="helper-text text-danger"/>
          <span class="helper-text" id="idCheckMsg" style="display: none;"></span>
        </div>

        <div class="mx-auto mt-1 mb-1" style="max-width: 500px; width: 100%;">
          <label for="email" class="form-label">이메일</label>
          <form:input class="form-control bg-light" id="email" path="email" placeholder="이메일을 입력하세요"/>
          <form:errors path="email" cssClass="helper-text text-danger"/>
        </div>

        <div class="mx-auto mt-1 mb-1" style="max-width: 500px; width: 100%;">
          <label for="password" class="form-label">비밀번호</label>
          <form:password class="form-control bg-light" id="password" path="password" placeholder="비밀번호를 입력하세요"/>
          <form:errors path="password" cssClass="helper-text text-danger"/>
        </div>

        <div class="mx-auto mt-1 mb-1" style="max-width: 500px; width: 100%;">
          <label for="address" class="form-label">주소</label>
          <form:input class="form-control bg-light" id="address" path="address" placeholder="주소를 입력하세요"/>
          <form:errors path="address" cssClass="helper-text text-danger"/>
        </div>

        <div class="mx-auto mt-1 mb-1" style="max-width: 500px; width: 100%;">
          <label for="postCode" class="form-label">우편번호</label>
          <form:input class="form-control bg-light" id="postCode" path="postCode" placeholder="우편번호를 입력하세요"/>
          <form:errors path="postCode" cssClass="helper-text text-danger"/>
        </div>

        <div class="d-flex justify-content-center gap-3 mt-2 pt-2">
          <button type="button" class="btn btn-outline-secondary" style="width: 150px;" onclick="history.back()">취소</button>
          <button type="submit" class="btn btn-primary" style="width: 150px;">회원가입</button>
        </div>
      </form:form>
    </div>
  </div>
</div>
</main>
<script>
  const validElement = document.querySelector('#idCheckMsg');
  document.querySelector('#userId').addEventListener('focusout', async ev => {
    const id = ev.target.value;
    if(!id) return;
    const response = await fetch('/api/member/exists/' + id);
    const data = await response.json();
    validElement.style.display = 'block';
    validElement.textContent = data.data ? '사용이 불가능한 아이디 입니다.' : '사용 가능한 아이디 입니다.';
  });

  document.querySelector('#signupForm').addEventListener('submit', async ev => {
    // form tag 의 기본 이벤트 차단
    ev.preventDefault();

    const id = document.querySelector('#userId').value;
    if(!id) return;
    const response = await fetch('/api/member/exists/' + id);
    const data = await response.json();

    if(data.data){
      document.querySelector('#userId').focus();
      validElement.textContent = '사용이 불가능한 아이디 입니다.';
      return;
    }

    ev.target.submit();
  });
</script>
</body>
</html>
