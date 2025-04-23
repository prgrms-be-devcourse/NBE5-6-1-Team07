<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/include/page.jsp" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
  <style>
    body {
      background: #ddd;
      display: flex;
      justify-content: center;
      align-items: center;
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
</head>
<div class="container-fluid">
  <div class="row justify-content-center m-4">
    <h1 class="text-center">Grids & Circle</h1>
  </div>

  <div class="row justify-content-center">
    <div class="card p-5 pr-7 pl-7">
      <div class="text-center mb-1">
        <h2><b>회원 정보 수정</b></h2>
      </div>
      <form>
        <div class="mx-auto mb-1" style="max-width: 500px; width: 100%;">
          <label for="id" class="form-label">아이디</label>
          <input type="text" class="form-control bg-light" id="id" placeholder="이메일을 입력하세요">
        </div>

        <div class="mx-auto mt-1 mb-1" style="max-width: 500px; width: 100%;">
          <label for="email" class="form-label">이메일</label>
          <input type="text" class="form-control bg-light" id="email" placeholder="이메일을 입력하세요">
        </div>

        <div class="mx-auto mt-1 mb-1" style="max-width: 500px; width: 100%;">
          <label for="password" class="form-label">비밀번호</label>
          <input type="text" class="form-control bg-light" id="password" placeholder="비밀번호를 입력하세요">
        </div>

        <div class="mx-auto mt-1 mb-1" style="max-width: 500px; width: 100%;">
          <label for="address" class="form-label">주소</label>
          <input type="text" class="form-control bg-light" id="address" placeholder="주소를 입력하세요">
        </div>

        <div class="mx-auto mt-1 mb-1" style="max-width: 500px; width: 100%;">
          <label for="postCode" class="form-label">우편번호</label>
          <input type="text" class="form-control bg-light" id="postCode" placeholder="우편번호를 입력하세요">
        </div>

        <div class="d-flex justify-content-center gap-3 mt-2 pt-2">
          <button type="button" class="btn btn-outline-secondary" style="width: 150px;">취소</button>
          <button type="submit" class="btn btn-primary" style="width: 150px;">저장</button>
        </div>
      </form>
    </div>
  </div>
</div>
<script>
  window.addEventListener('DOMContentLoaded', () => {
    document.getElementById('id').value = 'hong123';
    document.getElementById('email').value = 'hong@example.com';
    document.getElementById('password').value = '12345';
    document.getElementById('address').value = '서울특별시 강남구 태해란로123길';
    document.getElementById('postCode').value = '02010';
  });
</script>
</html>