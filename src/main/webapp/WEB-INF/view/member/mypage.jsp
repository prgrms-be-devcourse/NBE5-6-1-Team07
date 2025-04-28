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
            height: 68vh;
            display: flex;
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

        hr {
            margin-top: 1.25rem
        }

        .info-label {
            width: 100px;
            font-weight: 600;
        }

        .edit-button {
            border: 1.5px solid #000;
            background: white;
            font-weight: 600;
            border-radius: 8px;
            padding: 6px 14px;
        }
    </style>
    <title>마이페이지</title>
    <%@include file="/WEB-INF/view/include/static.jsp" %>
</head>
<body>
<%@include file="/WEB-INF/view/include/header.jsp" %>

<div class="row justify-content-center m-4">
    <h1 class="text-center">마이페이지</h1>
</div>

<div class="container-fluid">
<div class="card">
    <div class="row">
        <div class="col-md-8 mt-4 d-flex flex-column align-items-start p-4 pt-0">
            <h5 class="mb-4"><a href="edit" class="fw-bold">기본 정보 수정 > </a></h5>
            <div class="d-flex justify-content-between align-items-center w-100 mb-3 mt-2">
                <div class="fw-bold">회원 정보</div>
            </div>

            <div class="d-flex mt-1 mb-4 w-100"><div class="info-label">id</div><div><c:out value="${info.userId}"/></div></div>
            <div class="d-flex mb-4 w-100"><div class="info-label">email</div><div><c:out value="${info.email}"/></div></div>
            <div class="d-flex mb-4 w-100"><div class="info-label">주소</div><div><c:out value="${info.address}"/></div></div>
            <div class="d-flex mb-4 w-100"><div class="info-label">우편번호</div><div><c:out value="${info.postCode}"/></div></div>

            <h5 class="mt-5"><a href="/order/list" class="fw-bold">주문 목록 더보기 > </a></h5>
        </div>

        <sec:authorize access="hasRole('ROLE_USER')">
            <%@ include file="/WEB-INF/view/include/cart.jsp" %>
        </sec:authorize>

    </div>
</div>
</div>
</body>
</html>
