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
      padding: 50px 60px 20px 30px;
      max-width: 1000px;
      min-width: 400px;
      margin: 20px auto;
      box-shadow: 0 6px 20px rgba(0, 0, 0, 0.1);
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
    .order-table .col-1 {
      display: flex;
      width: 100px;
      flex-direction: column;
      align-self: center;
      justify-content: left;
    }
    .order-table .col-2 {
      display: flex;
      width: 200px;
      flex-direction: column;
      align-self: center;
      justify-content: left;
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
    .pagination-container{
      display: flex;
      justify-content: center;
      margin-top: 12px;
    }
    .bi{
      color: black;
    }
    .page-link{
      color: black;
    }
  </style>
</head>

<body>
<%@ include file="/WEB-INF/view/include/header.jsp" %>

<div class="container">
  <div class="brand">관리자용 주문 목록</div>

  <div class="table-box">
    <ul class="order-table">
      <li class="row header">
        <div class="col-1">주문번호</div>
        <div class="col-2">이메일</div>
        <div class="col">주문내역</div>
        <div class="col">주문일</div>
        <div class="col">도착예정일</div>
        <div class="col">배송상태</div>
      </li>
      <c:forEach items="${page.content()}" var="order">
        <li class="row">
          <div class="col-1"><c:out value="${order.orderId}"/></div>
          <div class="col-2"><c:out value="${order.email}"/></div>
          <c:choose>
            <c:when test="${order.orderedProducts.size() == 1}">
              <div class="col"><c:out value="${order.orderedProducts[0].name}"/></div>
            </c:when>
            <c:otherwise>
              <span class="col"><c:out value="${order.orderedProducts[0].name}"/> 외 <c:out value="${order.orderedProducts.size() - 1}"/> </span>
            </c:otherwise>
          </c:choose>
          <div class="col"><c:out value="${order.orderedDate}"/></div>
          <div class="col"><c:out value="${order.deliveredDate}"/></div>
          <div class="col"><c:out value="${order.status}"/></div>
          <a href="<c:url value='/delivery/send/${order.orderId}' />">발송</a>
        </li>
      </c:forEach>
    </ul>
    <div class="pagination-container">
      <nav>
        <ul class="pagination">
          <li class="page-item <c:if test='${page.currentNumber() == 1}'>disabled</c:if>'">
            <a class="page-link" href="${page.url()}?page=${page.prevPage()}">
              <i class="bi bi-chevron-double-left"></i>
            </a>
          </li>

          <c:forEach var="i" begin="${page.startNumber()}" end="${page.endNumber()}">
            <li class="page-item <c:if test='${page.currentNumber() == i}'>active</c:if>'">
              <a class="page-link" href="${page.url()}?page=${i}">${i}</a>
            </li>
          </c:forEach>

          <li class="page-item <c:if test='${page.currentNumber() == page.endNumber()}'>disabled</c:if>'">
            <a class="page-link" href="${page.url()}?page=${page.nextPage()}">
              <i class="bi bi-chevron-double-right"></i>
            </a>
          </li>

        </ul>
      </nav>
    </div>

  </div>
</div>
</body>
</html>
