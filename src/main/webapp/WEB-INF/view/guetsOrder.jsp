<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/page.jsp" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>비회원 주문조회</title>

  <!-- Bootstrap CSS 포함 -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600&display=swap" rel="stylesheet">

  <%@ include file="/WEB-INF/view/include/static.jsp" %>

  <style>
    body {
      background: #ddd;
      font-family: 'Inter', sans-serif;
    }

    .brand {
      font-weight: 600;
      font-size: 40px;
      text-align: center;
      margin-top: 40px;
      color: #222;
    }

    .table-box {
      background: white;
      border-radius: 30px;
      padding: 50px;
      max-width: 1000px;
      margin: 50px auto;
      box-shadow: 0 6px 20px rgba(0, 0, 0, 0.1);
    }

    .table-title {
      font-size: 36px;
      font-weight: bold;
      text-align: center;
      margin-bottom: 40px;
    }

    .search-bar {
      display: flex;
      justify-content: center;
      align-items: center;
      gap: 1rem;
      margin-bottom: 30px;
    }

    .search-bar label {
      font-size: 18px;
      font-weight: 600;
    }

    .search-bar input {
      width: 300px;
      padding: 0.5rem;
      border-radius: 5px;
      border: 1px solid #ccc;
    }

    .search-bar button {
      padding: 0.5rem 1rem;
      background-color: #0d6efd;
      border: none;
      color: white;
      border-radius: 5px;
      cursor: pointer;
    }

    .order-table .row {
      background-color: #e0e0e0;
      padding: 20px 0;
      margin-bottom: 10px;
      border-radius: 10px;
      text-align: center;
      font-weight: 500;
    }

    .order-table .row.header {
      background-color: #cfcfcf;
      font-weight: 700;
    }

    .order-table .col {
      display: flex;
      flex-direction: column;
      justify-content: center;
    }

    .order-table .col .product-info {
      font-size: 0.85rem;
      color: #666;
    }
  </style>
</head>

<body>
<%@ include file="/WEB-INF/view/include/header.jsp" %>

<div class="container">
  <div class="brand">Grids & Circle</div>

  <div class="table-box">
    <div class="table-title">비회원 주문조회</div>

    <div class="search-bar">
      <label for="email">이메일</label>
      <input type="text" id="email" placeholder="이메일을 입력하세요">
      <button>조회</button>
    </div>

    <div class="order-table">
      <div class="row header">
        <div class="col">주문번호</div>
        <div class="col">배송번호</div>
        <div class="col">주문내역</div>
        <div class="col">배송일</div>
        <div class="col">배송상태</div>
      </div>

      <div class="row">
        <div class="col">123456</div>
        <div class="col">DEL123</div>
        <div class="col">주문1<br><span class="product-info">무슨무슨 커피빈</span></div>
        <div class="col">2024-04-01</div>
        <div class="col">배송완료</div>
      </div>

      <div class="row">
        <div class="col">123457</div>
        <div class="col">DEL124</div>
        <div class="col">주문2<br><span class="product-info">무슨무슨 커피빈</span></div>
        <div class="col">2024-04-05</div>
        <div class="col">배송중</div>
      </div>

      <div class="row">
        <div class="col">123458</div>
        <div class="col">DEL125</div>
        <div class="col">주문3<br><span class="product-info">무슨무슨 커피빈</span></div>
        <div class="col">2024-04-09</div>
        <div class="col">준비중</div>
      </div>

      <div class="row">
        <div class="col">123459</div>
        <div class="col">DEL126</div>
        <div class="col">주문4<br><span class="product-info">무슨무슨 커피빈</span></div>
        <div class="col">2024-04-11</div>
        <div class="col">배송완료</div>
      </div>
    </div>
  </div>
</div>
</body>
</html>
