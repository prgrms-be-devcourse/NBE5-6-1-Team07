<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/page.jsp" %>

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
      margin: auto;
      max-width: 950px;
      width: 90%;
      box-shadow: 0 6px 20px 0 rgba(0, 0, 0, 0.19);
      border-radius: 1rem;
      border: transparent
    }

    .summary {
      background-color: #ddd;
      border-top-right-radius: 1rem;
      border-bottom-right-radius: 1rem;
      padding: 4vh;
      color: rgb(65, 65, 65)
    }

    @media (max-width: 767px) {
      .summary {
        border-top-right-radius: unset;
        border-bottom-left-radius: 1rem
      }
    }

    .row {
      margin: 0
    }

    .title b {
      font-size: 1.5rem
    }

    .col-2,
    .col {
      padding: 0 1vh
    }

    img {
      width: 3.5rem
    }

    hr {
      margin-top: 1.25rem
    }
    .products {
      width: 100%;
    }
    .products .price, .products .action {
      line-height: 38px;
    }
    .products .action {
      line-height: 38px;
    }
    .search-btn {
      height: 38px;
      width: 60px;
      padding: 6px 12px;
      line-height: 1.2;
      white-space: nowrap;
    }
    .pagination-container {
      display: flex;
      justify-content: center;
      margin-top: 20px;
      margin-bottom: 10px;
    }
    .bi{
      color: black;
    }
    .product-name, .product-brand {
      white-space: nowrap;
      overflow: hidden;
      text-overflow: ellipsis;
    }
    .page-link {
      display: flex;
      align-items: center;
      justify-content: center;
      color: black;
      height: 38px;
    }

    .page-link i {
      font-size: 1rem;
      vertical-align: middle;
    }
  </style>
  <title>상품페이지</title>
  <%@include file="/WEB-INF/view/include/static.jsp" %>
</head>
<body>
<%@include file="/WEB-INF/view/include/header.jsp" %>
<div class="row justify-content-center m-4">
  <h1 class="text-center">Grids & Circle</h1>
</div>
<div class="card mt-0 mb-4">
  <div class="row">
    <div class="col-md-8 mt-4 d-flex flex-column align-items-start p-3 pt-0">
      <div class="d-flex justify-content-between align-items-center w-100 mb-3">
        <h5 class="mb-0"><b>상품 목록</b></h5>
        <form action="${pageContext.request.contextPath}/product"
              method="get"
              class="d-flex align-items-center gap-2"
              style="max-width: 400px;">
          <input type="text" name="item" class="form-control" placeholder="상품명을 입력하세요" required>
          <button type="submit" class="btn btn-dark search-btn">검색</button>
        </form>
      </div>
      <ul class="list-group products">
        <c:forEach var="product" items="${page.content()}">
          <li class="list-group-item d-flex mt-3">
            <div class="col-2">
              <c:if test="${empty product.images}">
                <img src="#" alt="thumbnail" class="circle">
              </c:if>
              <c:if test="${not empty product.images}">
                <c:forEach items="${product.images}" var="image">
                  <img src="${image.url}" alt="thumbnail" class="circle">
                </c:forEach>
              </c:if>
            </div>
            <div class="col">
              <div class="row text-muted product-brand">
                  ${product.brand}
              </div>
              <div class="row product-name">
                  ${product.name}
              </div>
            </div>
            <div class="col text-center price">${product.price}원</div>
            <div class="col text-end action">
              <c:choose>
                <c:when test="${product.count > 0}">
                  <form action="${pageContext.request.contextPath}/cart/add" method="post" style="display:inline;">
                    <input type="hidden" name="productId" value="${product.productId}">
                    <input type="hidden" name="page" value="${page.currentNumber()}">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <button type="submit" class="btn btn-small btn-outline-dark">추가</button>
                  </form>
                </c:when>
                <c:otherwise>
                  <button type="button" class="btn btn-small btn-secondary" disabled>품절</button>
                </c:otherwise>
              </c:choose>
            </div>
          </li>
        </c:forEach>
      </ul>

      <div class="pagination-container w-100 mt-4 mb-4 d-flex justify-content-center">
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

    <%@ include file="/WEB-INF/view/include/cart.jsp" %>

  </div>
</div>
</body>
</html>