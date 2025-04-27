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
    <h1 class="text-center">Grids & Circle</h1>
</div>

<div class="card">
    <div class="row">
        <div class="col-md-8 mt-4 d-flex flex-column align-items-start p-4 pt-0">
            <h5 class="mb-3"><b>마이페이지</b></h5>
            <div class="d-flex justify-content-between align-items-center w-100 mb-3">
                <div class="fw-bold">회원 정보</div>
                <a href="edit"><div class="edit-button">수정</div></a>
            </div>

            <div class="d-flex mb-2 w-100"><div class="info-label">id</div><div><c:out value="${info.userId}"/></div></div>
            <div class="d-flex mb-2 w-100"><div class="info-label">email</div><div><c:out value="${info.email}"/></div></div>
            <div class="d-flex mb-2 w-100"><div class="info-label">주소</div><div><c:out value="${info.address}"/></div></div>
            <div class="d-flex mb-2 w-100"><div class="info-label">우편번호</div><div><c:out value="${info.postCode}"/></div></div>

            <a href="/order/list" class="btn mb-2 mt-4">주문 목록 더보기</a>
        </div>

        <%@ include file="/WEB-INF/view/include/cart.jsp" %>

    </div>
</div>
</body>
</html>
