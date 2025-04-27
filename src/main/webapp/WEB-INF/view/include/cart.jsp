<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="col-md-4 summary p-4">
    <div>
        <h5 class="m-0 p-0"><b>Cart</b></h5>
    </div>
    <hr>
    <c:forEach var="item" items="${cartItems}">
        <div class="row align-items-center mb-2">
            <div class="col-8">
                <h6 class="p-0 m-0">
                        ${productNames[item.productId]}
                </h6>
            </div>
            <div class="col-4 d-flex align-items-center justify-content-end gap-1">
                <form action="${pageContext.request.contextPath}/cart/decrease" method="post" style="display:inline;">
                    <input type="hidden" name="productId" value="${item.productId}" />
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <button type="submit" class="btn btn-sm btn-outline-dark" style="border-color: #ccc; margin-top: 4px;">-</button>
                </form>
                <span class="badge bg-dark">${item.count}개</span>
                <form action="${pageContext.request.contextPath}/cart/increase" method="post" style="display:inline;">
                    <input type="hidden" name="productId" value="${item.productId}" />
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <button type="submit" class="btn btn-sm btn-outline-dark" style="border-color: #ccc; margin-top: 4px;">+</button>
                </form>
                <form action="${pageContext.request.contextPath}/cart/remove" method="post" style="display:inline;">
                    <input type="hidden" name="productId" value="${item.productId}" />
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <button type="submit" class="btn btn-sm btn-outline-dark px-2"
                            style="min-width: 30px; white-space: nowrap; border-color: red; color: red; margin-top: 4px;">
                        <i class="bi bi-trash3"></i>
                    </button>
                </form>
            </div>
        </div>
    </c:forEach>

    <form id="orderForm" action="${pageContext.request.contextPath}/order/create" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <div class="mb-3">
            <label for="email" class="form-label">이메일</label>
            <input type="email" class="form-control mb-1" id="email" name="email"
                   value="${loginEmail}" ${not empty loginEmail ? 'readonly' : ''} required>
        </div>
        <div class="mb-3">
            <label for="address" class="form-label">주소</label>
            <input type="text" class="form-control mb-1" id="address" name="address"
                   value="${loginAddress}" required>
        </div>
        <div class="mb-3">
            <label for="postcode" class="form-label">우편번호</label>
            <input type="text" class="form-control" id="postcode" name="postCode"
                   value="${loginPostCode}" required>
        </div>
        <div>당일 오후 2시 이후의 주문은 다음날 배송을 시작합니다.</div>

        <div class="row pt-2 pb-2 border-top">
            <h5 class="col">총금액</h5>
            <h5 class="col text-end">
                <c:set var="totalPrice" value="0" />
                <c:forEach var="item" items="${cartItems}">
                    <c:set var="itemPrice" value="${productPrices[item.productId]}" />
                    <c:set var="subtotal" value="${item.count * itemPrice}" />
                    <c:set var="totalPrice" value="${totalPrice + subtotal}" />
                </c:forEach>
                <fmt:formatNumber value="${totalPrice}" pattern="#,###"/>원
            </h5>
        </div>

        <button type="submit" class="btn btn-dark col-12 mt-2">결제하기</button>
    </form>
</div>