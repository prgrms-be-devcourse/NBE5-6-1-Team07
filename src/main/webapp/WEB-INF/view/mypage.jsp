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
                <a href="mypage/edit"><div class="edit-button">수정</div></a>
            </div>

            <div class="d-flex mb-2 w-100"><div class="info-label">id</div><div><c:out value="${info.userId}"/></div></div>
            <div class="d-flex mb-2 w-100"><div class="info-label">email</div><div><c:out value="${info.email}"/></div></div>
            <div class="d-flex mb-2 w-100"><div class="info-label">주소</div><div><c:out value="${info.address}"/></div></div>
            <div class="d-flex mb-2 w-100"><div class="info-label">우편번호</div><div><c:out value="${info.postCode}"/></div></div>

            <a href="/order/list" class="btn mb-2 mt-4">주문 목록 더보기</a>
        </div>

        <div class="col-md-4 summary p-4">
            <div>
                <h5 class="m-0 p-0"><b>Summary</b></h5>
            </div>
            <hr>
            <div class="row">
                <h6 class="p-0">Columbia Nariñó <span class="badge bg-dark">2개</span></h6>
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
