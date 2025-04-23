<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/page.jsp" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>관리자용 주문 리스트 페이지</title>

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
    <div class="table-title">관리자용 주문 리스트 페이지</div>
    <div class="row header">
      <div class="col">주문번호</div>
      <div class="col">배송번호</div>
      <div class="col">주문내역</div>
      <div class="col">배송일</div>
      <div class="col">배송상태</div>
    </div>
    <ul class="order-table">
      <c:forEach items="${orders}" var="order">
        <li class="row">
          <div class="col"><c:out value="${order.orderId}"/></div>
          <div class="col"><c:out value="${order.orderId}"/></div>
          <div class="col">주문1<br><span class="product-info">무슨무슨 커피빈</span></div>
          <div class="col">2024-04-01</div>
          <div class="col">배송완료</div>
        </li>
      </c:forEach>
    </ul>
  </div>
</div>
</body>
</html>
