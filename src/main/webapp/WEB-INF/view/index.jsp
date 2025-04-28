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
        display: flex;
        margin: 0 auto;
        max-width: 950px;
        min-width: 400px;
        max-height: 600px;
        min-height: 300px;
        width: 90%;
        box-shadow: 0 6px 20px 0 rgba(0, 0, 0, 0.19);
        border-radius: 1rem;
        border: transparent;
        padding: 2rem 2rem 2rem 2rem;
      }

      .row {
        display: flex;
        justify-content: center;
      }

      .product-link-container{
        display: flex;
        justify-content: right;
      }

      .product-link{

        color: black;
        text-decoration: none;
      }

      img.circle {
        width: 200px;
        height: 200px;
        object-fit: cover;
      }

      .title b {
        font-size: 1.5rem
      }

      .col{
        margin: 1rem 0.5rem 0 0.5rem;

      }

      img {
        width: 3.5rem
      }

      hr {
        margin-top: 1.25rem
      }

    </style>
    <title>Grids & Circle</title>
    <%@include file="/WEB-INF/view/include/static.jsp" %>
</head>
<body>
<%@include file="/WEB-INF/view/include/header.jsp" %>
<div class="container text-center">
    <c:if test="${param.logout == 'true'}">
        <div class="alert alert-success text-center" role="alert">
            로그아웃되었습니다.
        </div>
    </c:if>
    <h1 class="my-4 mb-5">오늘의 인기 상품</h1>
    <div class="card">
        <h5><b>인기 3종 세트</b></h5>
        <div class="product-link-container">
        <a class="product-link" href="/product"><b>상품 더보기</b></a>
        </div>
        <div class="row g-1">
            <c:forEach var="product" items="${products}">
                <div class="col">
                    <c:if test="${empty product.images}">
                        <img src="#" alt="thumbnail" class="circle">
                    </c:if>
                    <c:if test="${not empty product.images}">
                        <c:forEach items="${product.images}" var="image">
                            <img src="${image.url}" alt="thumbnail" class="circle">
                        </c:forEach>
                    </c:if>
                    <p style="margin-top: 4px"><c:out value="${product.name}"/></p>
                    <p><c:out value="${product.price}"/></p>
                    <p><c:out value="${product.brand}"/></p>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>