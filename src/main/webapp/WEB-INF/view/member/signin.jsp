<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/include/page.jsp" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
          crossorigin="anonymous">
    <style>
      body {
        background: #ddd;
      }

      .card {
        margin-top: 3%;
        max-width: 900px;
        width: 90%;
        min-height: 500px;
        box-shadow: 0 6px 20px 0 rgba(0, 0, 0, 0.19);
        border-radius: 1rem;
        border: transparent;
        padding: 2rem;
        background-color: #fff;
      }

      .title b {
        font-size: 1.5rem
      }

      hr {
        margin-top: 1.25rem
      }

      .custom-link {
        color: gray;
        text-decoration: underline;
      }

      .custom-link:hover {
        color: darkgray;
      }

    </style>
    <title>로그인</title>
    <%@include file="/WEB-INF/view/include/static.jsp" %>
</head>
<body>
<%@include file="/WEB-INF/view/include/header.jsp" %>
<div class="container-fluid">

    <div class="row justify-content-center">
        <div class="card p-5 pr-7 pl-7">
            <div class="text-center mb-3">
                <h2><b>로그인</b></h2>
            </div>
            <div class="text-center mt-3">
                <c:if test="${param.error eq 'true'}">
                    <div class="alert alert-danger" role="alert">
                        아이디 또는 비밀번호가 일치하지 않습니다.
                    </div>
                </c:if>
            </div>

            <form method="post" action="/member/signin">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                <div class="mx-auto mt-5 mb-4" style="max-width: 500px; width: 100%;">
                    <label for="id" class="form-label">아이디</label>
                    <input type="text" class="form-control bg-light" id="id" name="userId"
                           placeholder="아이디를 입력하세요">
                </div>

                <div class="mx-auto mt-3 mb-4" style="max-width: 500px; width: 100%;">
                    <label for="password" class="form-label">비밀번호</label>
                    <input type="password" class="form-control bg-light" id="password" name="password" placeholder="비밀번호를 입력하세요">
                </div>

                <div class="mx-auto mt-3 mb-4" style="max-width: 500px; width: 100%;">
                    <div class="form-check mt-3" >
                        <input class="form-check-input" type="checkbox" name="remember-me" id="remember-me">
                        <label class="form-check-label" for="remember-me">로그인 유지</label>
                    </div>
                </div>

                <div class="text-center mt-4">
                    <div><a href="<c:url value='/member/signup' />" class="text-decoration-none">Grids & Circle 이 처음이라면?</a></div>
                </div>
                <div class="text-center mt-2">
                    <div><a href="<c:url value='/' />" class="custom-link">비회원으로 계속하기</a></div>
                </div>

                <div class="d-flex justify-content-center gap-3 mt-5 pt-2 mb-2">
                    <button type="button" class="btn btn-outline-secondary" style="width: 150px;">취소</button>
                    <button type="submit" class="btn btn-primary" style="width: 150px;">로그인</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
