package com.grepp.team07.app.model.cart;

import com.grepp.team07.app.model.cart.dto.CartDto;
import com.grepp.team07.app.model.cart.dto.CartProductDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Override
    public void addProduct(int productId, int count, HttpSession session, String userId) {
        if (userId == null) {
            // 비회원
            Map<Integer, Integer> guestCart = (Map<Integer, Integer>) session.getAttribute("guestCart");
            if (guestCart == null) {
                guestCart = new HashMap<>();
            }
            guestCart.put(productId, guestCart.getOrDefault(productId, 0) + count);
            session.setAttribute("guestCart", guestCart);
        } else {
            // 회원
            CartDto cart = cartRepository.findActiveCart(userId)
                .orElseGet(() -> {
                    cartRepository.createCart(userId);
                    return cartRepository.findActiveCart(userId).orElseThrow();
                });

            CartProductDto existing = cartRepository.getProduct(cart.getCartId(), productId);
            if (existing != null) {
                cartRepository.updateCount(existing.getCartProductId(), existing.getCount() + count);
            } else {
                cartRepository.addProduct(cart.getCartId(), productId, count);
            }
        }
    }

    @Override
    public List<CartProductDto> getCartItems(HttpSession session, String userId) {
        if (userId == null) {
            // 비회원
            Map<Integer, Integer> guestCart = (Map<Integer, Integer>) session.getAttribute("guestCart");
            List<CartProductDto> result = new ArrayList<>();
            if (guestCart != null) {
                for (Map.Entry<Integer, Integer> entry : guestCart.entrySet()) {
                    CartProductDto dto = new CartProductDto();
                    dto.setProductId(entry.getKey());
                    dto.setCount(entry.getValue());
                    result.add(dto);
                }
            }
            return result;
        } else {
            // 회원
            return cartRepository.getProducts(userId);
        }
    }

    @Override
    public void clearCart(HttpSession session, String userId) {
        if (userId == null) {
            // 비회원
            session.removeAttribute("guestCart");
        } else {
            // 회원
            cartRepository.deactivateCart(userId);
            cartRepository.createCart(userId);
        }
    }

    @Override
    public void increaseCount(int productId, HttpSession session, String userId) {
        if (userId == null) {
            // 비회원
            Map<Integer, Integer> guestCart = (Map<Integer, Integer>) session.getAttribute("guestCart");
            if (guestCart != null && guestCart.containsKey(productId)) {
                guestCart.put(productId, guestCart.get(productId) + 1);
                session.setAttribute("guestCart", guestCart);
            }
        } else {
            // 회원
            CartDto cart = cartRepository.findActiveCart(userId)
                .orElseThrow();
            CartProductDto item = cartRepository.getProduct(cart.getCartId(), productId);
            if (item != null) {
                cartRepository.updateCount(item.getCartProductId(), item.getCount() + 1);
            }
        }
    }

    @Override
    public void decreaseCount(int productId, HttpSession session, String userId) {
        if (userId == null) {
            // 비회원
            Map<Integer, Integer> guestCart = (Map<Integer, Integer>) session.getAttribute("guestCart");
            if (guestCart != null && guestCart.containsKey(productId)) {
                int currentCount = guestCart.get(productId);
                if (currentCount > 1) {
                    guestCart.put(productId, currentCount - 1);
                } else {
                    guestCart.remove(productId);
                }
                session.setAttribute("guestCart", guestCart);
            }
        } else {
            // 회원
            CartDto cart = cartRepository.findActiveCart(userId)
                .orElseThrow();
            CartProductDto item = cartRepository.getProduct(cart.getCartId(), productId);
            if (item != null) {
                if (item.getCount() > 1) {
                    cartRepository.updateCount(item.getCartProductId(), item.getCount() - 1);
                } else {
                    cartRepository.deleteProduct(item.getCartProductId());
                }
            }
        }
    }

    @Override
    public void removeProduct(int productId, HttpSession session, String userId) {
        if (userId == null) {
            // 비회원
            Map<Integer, Integer> guestCart = (Map<Integer, Integer>) session.getAttribute("guestCart");
            if (guestCart != null) {
                guestCart.remove(productId);
                session.setAttribute("guestCart", guestCart);
            }
        } else {
            // 회원
            CartDto cart = cartRepository.findActiveCart(userId)
                .orElseThrow();
            CartProductDto item = cartRepository.getProduct(cart.getCartId(), productId);
            if (item != null) {
                cartRepository.deleteProduct(item.getCartProductId());
            }
        }
    }
}
