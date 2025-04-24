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
        }

        .card {
           margin: auto;
           max-width: 950px;
           width: 90%;
           box-shadow: 0 6px 20px 0 rgba(0, 0, 0, 0.19);
           border-radius: 1rem;
           border: transparent
         }

        .product-list-scroll {
          max-height: 500px;
          overflow-y: auto;
          width: 100%;
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

    </style>
    <title>Grids & Circle</title>
    <%@include file="/WEB-INF/view/include/static.jsp" %>
</head>
<body>
<%@include file="/WEB-INF/view/include/header.jsp" %>
<div class="row justify-content-center m-4">
    <h1 class="text-center">관리자용 상품 페이지</h1>
</div>
<div class="container-fluid">
<div class="card">
    <div class="row">
        <div class="col-md-8 mt-4 d-flex flex-column align-items-start p-3 pt-0">
            <div class="product-list-scroll">
                <ul class="list-group products">
                    <c:forEach items="${products}" var="product">
                        <li class="list-group-item d-flex mt-2">
                            <div class="col-2">
                                <img class="img-fluid" src="<c:out value='${product.image}'/>" alt="">
                            </div>
                            <div class="col">
                                <div class="row text-muted"><c:out value="${product.brand}" /></div>
                                <div class="row"><c:out value="${product.name}" /></div>
                            </div>
                            <div class="col text-center price">
                                <c:out value="${product.price}" />원
                            </div>
                            <div class="col d-flex justify-content-center align-items-center gap-2">
                                <a class="btn btn-sm btn-outline-dark"
                                   href="/admin/product?id=${product.id}&action=increment"
                                   style="border-color: #ccc;">
                                    +
                                </a>
                                <span style="min-width: 40px; display: inline-block; text-align: center;"><c:out value="${product.count}" /></span>
                                <a class="btn btn-sm btn-outline-dark"
                                   href="/admin/product?id=${product.id}&action=decrement"
                                   style="border-color: #ccc;">
                                    -
                                </a>
                                <a class="btn btn-sm btn-outline-dark px-3" style="min-width: 60px; white-space: nowrap;" href="/admin/product?id=${product.id}">수정</a>
                            </div>

                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <div class="col-md-4 summary p-4">
            <form:form modelAttribute="productInsertForm" action="/admin/product" method="post">
                <form:input path="id" type="hidden" id="id" />
                <div class="mb-3">
                    <label for="name" class="form-label">상품명</label>
                    <form:input path="name" id="name" class="form-control"/>
                    <form:errors path="name" cssClass="text-danger"/>
                </div>
                <div class="mb-3">
                    <label for="brand" class="form-label">종류</label>
                    <form:input path="brand" id="brand" class="form-control"/>
                    <form:errors path="brand" cssClass="text-danger"/>
                </div>
                <div class="mb-3">
                    <label for="price" class="form-label">가격</label>
                    <form:input path="price" id="price" class="form-control"/>
                    <form:errors path="price" cssClass="text-danger"/>
                </div>
                <div class="mb-3">
                    <label for="info" class="form-label">설명</label>
                    <form:input path="info" id="info" class="form-control"/>
                    <form:errors path="info" cssClass="text-danger"/>
                </div>
                <div class="mb-3">
                    <label for="image" class="form-label">이미지</label>
                    <form:input path="image" id="image" class="form-control"/>
                    <form:errors path="image" cssClass="text-danger"/>
                </div>
                <button class="btn btn-dark col-12">등록/수정</button>
            </form:form>
        </div>
    </div>
</div>
</div>
</body>
</html>
