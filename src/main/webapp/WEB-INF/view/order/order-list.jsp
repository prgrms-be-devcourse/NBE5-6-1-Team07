<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/page.jsp" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>주문 조회</title>

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">

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
      margin: 30px auto;
      box-shadow: 0 6px 20px rgba(0, 0, 0, 0.1);
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

    .order-table .col-7 {
      display: flex;
      width: 150px;
      flex-direction: column;
      align-self: flex-start;
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

    .no-order-message {
      display: flex;
      justify-content: center;
      align-items: center;
      height: 200px;
      color: gray;
      font-size: 18px;
    }

  </style>
</head>

<body>
<%@ include file="/WEB-INF/view/include/header.jsp" %>

<div class="container">
  <div class="brand">
    <sec:authorize access="isAnonymous()">
      비회원 주문조회
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
      <sec:authentication property="name" />님 주문조회
    </sec:authorize>
  </div>

  <div class="table-box">
    <sec:authorize access="isAnonymous()">
      <div class="search-bar">
        <label id="email" for="email">이메일</label>
        <form method="get" action="/order/list">
          <input type="search" name="email" placeholder="이메일을 입력하세요" value="${email}" />
          <button class="btn" type="submit" style="border: none; background: none; outline: none;">
            <i class="bi bi-search"></i>
          </button>
        </form>
      </div>
    </sec:authorize>

    <div class="order-table">
      <ul class="order-table">
        <li class="row header">
          <div class="col-1">주문번호</div>
          <div class="col-2">주문내역</div>
          <div class="col">주문일</div>
          <div class="col-7">주문금액</div>
          <div class="col">도착예정일</div>
          <div class="col">배송상태</div>
        </li>
        <c:if test="${empty page.content()}">
          <sec:authorize access="isAnonymous()">
            <c:choose>
              <c:when test="${empty email}">
                <div class="no-order-message">주문 정보가 없습니다</div>
              </c:when>

              <c:otherwise>
                <div class="no-order-message">
                  올바른 이메일을 입력해주세요<br> (회원이면 로그인을 해주세요)
                </div>
              </c:otherwise>
            </c:choose>
          </sec:authorize>
          <sec:authorize access="isAuthenticated()">
            <div class="no-order-message">아직 주문한 내역이 없습니다</div>
          </sec:authorize>
        </c:if>
        <c:forEach items="${page.content()}" var="item">
        <li class="row">
          <div class="col-1"><c:out value="${item.orderId}"/></div>
          <c:choose>
            <c:when test="${item.orderedProducts.size() == 1}">
              <div class="col-2"><c:out value="${item.orderedProducts[0].name}"/></div>
            </c:when>
            <c:otherwise>
              <span class="col-2"><c:out value="${item.orderedProducts[0].name}"/> 외 <c:out value="${item.orderedProducts.size() - 1}"/> </span>
            </c:otherwise>
          </c:choose>
          <div class="col"><c:out value="${item.orderedDate}"/></div>
          <div class="col-7"><c:out value="${item.totalPrice}"/> 원</div>
          <div class="col"><c:out value="${item.deliveredDate}"/></div>
          <div class="col"><c:out value="${item.status}"/></div>
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
</div>
</body>
</html>
