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
          height: 65vh;
          display: flex;
          flex-direction: column;
          box-shadow: 0 6px 20px 0 rgba(0, 0, 0, 0.19);
          border-radius: 1rem;
          border: transparent;
          overflow: hidden;
        }

        .product-list-scroll {
          flex: 1;
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
    <div class="row" style="height: 100%;">
        <div class="col-md-8 mt-2 d-flex flex-column align-items-start p-3 pt-0" style="height: 100%;">
            <div class="justify-content-end d-flex w-30">
                    <form method="get" action="/admin/admin-product" class="d-flex w-30 justify-content-end">
                        <input class="form-control me-1" type="search" name="keyword" placeholder="상품명 검색" value="${keyword}">
                        <button class="btn" type="submit" style="border: none; background: none; outline: none;">
                            <i class="bi bi-search"></i>
                        </button>
                    </form>
                </div>
                <div class="product-list-scroll">
                    <ul class="list-group products">
                        <c:forEach items="${adminProducts}" var="product">
                            <li class="list-group-item d-flex mt-2">
                                <c:if test="${empty product.images}">
                                    <img src="#" alt="thumbnail" class="circle">
                                </c:if>
                                <c:if test="${not empty product.images}">
                                    <c:forEach items="${product.images}" var="image">
                                        <img src="${image.url}" alt="thumbnail" class="circle">
                                    </c:forEach>
                                </c:if>
                                <div class="col">
                                    <div class="row text-muted"><c:out value="${product.brand}" /></div>
                                    <div class="row"><c:out value="${product.name}" /></div>
                                </div>
                                <div class="col text-center price">
                                    <c:out value="${product.price}" />원
                                </div>
                                <div class="col d-flex justify-content-center align-items-center gap-2">
                                    <form method="post" action="/admin/admin-product/action" style="display:inline;">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                        <input type="hidden" name="id" value="${product.productId}" />
                                        <input type="hidden" name="action" value="increment" />
                                        <c:if test="${not empty param.keyword}">
                                            <input type="hidden" name="keyword" value="${param.keyword}" />
                                        </c:if>
                                        <button type="submit" class="btn btn-sm btn-outline-dark" style="border-color: #ccc;">+</button>
                                    </form>

                                    <span style="min-width: 40px; display: inline-block; text-align: center;">
                                        <c:out value="${product.count}" />
                                    </span>

                                    <form method="post" action="/admin/admin-product/action" style="display:inline;">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                        <input type="hidden" name="id" value="${product.productId}" />
                                        <input type="hidden" name="action" value="decrement" />
                                        <c:if test="${not empty param.keyword}">
                                            <input type="hidden" name="keyword" value="${param.keyword}" />
                                        </c:if>
                                        <button type="submit" class="btn btn-sm btn-outline-dark" style="border-color: #ccc;">-</button>
                                    </form>

                                    <form method="get" action="/admin/admin-product" style="display: inline;">
                                        <input type="hidden" name="id" value="${product.productId}" />
                                        <c:if test="${not empty param.keyword}">
                                            <input type="hidden" name="keyword" value="${param.keyword}" />
                                        </c:if>
                                        <button type="submit" class="btn btn-sm btn-outline-dark px-2"
                                                style="min-width: 40px; white-space: nowrap;">
                                            <i class="bi bi-pencil"></i>
                                        </button>
                                    </form>
                                    <form method="post" action="/admin/admin-product/action" style="display: inline;">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                        <input type="hidden" name="id" value="${product.productId}" />
                                        <input type="hidden" name="action" value="delete" />
                                        <c:if test="${not empty param.keyword}">
                                            <input type="hidden" name="keyword" value="${param.keyword}" />
                                        </c:if>
                                        <button type="submit" class="btn btn-sm btn-outline-dark px-2"
                                                style="min-width: 40px; white-space: nowrap; border-color: red; color: red;">
                                            <i class="bi bi-trash3"></i>
                                        </button>
                                    </form>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
            <div class="col-md-4 summary p-4">
                <form:form modelAttribute="productInsertForm" action="/admin/admin-product" method="post" enctype="multipart/form-data">
                    <form:input path="productId" type="hidden" id="productId" />
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
                        <label for="thumbnail" class="form-label fw-bold">이미지</label>
                        <div class="input-group">
                            <input type="file" class="form-control" id="thumbnail" name="thumbnail" aria-label="Upload">
                        </div>
                    </div>
                    <button class="btn btn-dark col-12">등록/수정</button>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
