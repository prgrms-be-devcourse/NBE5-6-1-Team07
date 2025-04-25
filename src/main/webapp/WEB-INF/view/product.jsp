<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/page.jsp" %>

<html lang="ko">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">

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

  </style>
  <title>Hello, world!</title>
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
              <div class="row text-muted">${product.brand}</div>
              <div class="row">${product.name}</div>
            </div>
            <div class="col text-center price">${product.price}원</div>
            <div class="col text-end action">
              <a class="btn btn-small btn-outline-dark" href="javascript:addToCart(${product.productId})">추가</a>
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
    <div class="col-md-4 summary p-4">
      <div>
        <h5 class="m-0 p-0"><b>Summary</b></h5>
      </div>
      <hr>
      <div class="row">
        <h6 class="p-0">Columbia Nariñó <span class="badge bg-dark text-">2개</span></h6>
      </div>
      <div class="row">
        <h6 class="p-0">Brazil Serra Do Caparaó <span class="badge bg-dark">2개</span></h6>
      </div>
      <div class="row">
        <h6 class="p-0">Columbia Nariñó <span class="badge bg-dark">2개</span></h6>
      </div>
      <form>
        <div class="mb-3">
          <label for="email" class="form-label">이메일</label>
          <input type="email" class="form-control mb-1" id="email">
        </div>
        <div class="mb-3">
          <label for="address" class="form-label">주소</label>
          <input type="text" class="form-control mb-1" id="address">
        </div>
        <div class="mb-3">
          <label for="postcode" class="form-label">우편번호</label>
          <input type="text" class="form-control" id="postcode">
        </div>
        <div>당일 오후 2시 이후의 주문은 다음날 배송을 시작합니다.</div>
      </form>
      <div class="row pt-2 pb-2 border-top">
        <h5 class="col">총금액</h5>
        <h5 class="col text-end">15000원</h5>
      </div>
      <button class="btn btn-dark col-12">결제하기</button>
    </div>
  </div>
</div>
</body>
</html>