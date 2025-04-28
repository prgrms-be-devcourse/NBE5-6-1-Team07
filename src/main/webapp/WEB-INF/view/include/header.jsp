<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<header class="header">
  <nav class="navbar navbar-expand-lg bg-white shadow-sm">
    <div class="container-fluid">
      <a class="navbar-brand text-secondary" href="/">Grids & Circle</a>
        <ul class="navbar-nav flex-row mb-2 mb-lg-0" style="gap: 1rem;">
          <sec:authorize access="isAnonymous()">
            <li class="nav-item">
              <a class="nav-link text-secondary" href="/order/list">비회원 주문 조회</a>
            </li>
            <li class="nav-item">
              <a class="nav-link text-secondary" href="<c:url value='/member/signin' />">로그인</a>
            </li>
          </sec:authorize>


          <sec:authorize access="isAuthenticated()">
            <li class="nav-item">
              <a class="nav-link text-secondary" href="/member/mypage">
                <sec:authentication property="name" />님
              </a>
            </li>
            <li class="nav-item">
              <form id="logoutForm" action="<c:url value='/logout' />" method="post" style="display: none;">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
              </form>
              <a class="nav-link text-secondary" href="#" onclick="document.getElementById('logoutForm').submit(); return false;">로그아웃</a>
            </li>
          </sec:authorize>

          <sec:authorize access="hasRole('ROLE_ADMIN')">
            <li class="nav-item">
              <a class="nav-link text-secondary" href="/admin/orders">주문 관리</a>
            </li>
            <li class="nav-item">
              <a class="nav-link text-secondary" href="/admin/admin-product">상품 관리</a>
            </li>
          </sec:authorize>
        </ul>
      </div>
  </nav>
</header>
