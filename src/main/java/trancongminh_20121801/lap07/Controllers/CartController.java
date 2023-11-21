package trancongminh_20121801.lap07.Controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import trancongminh_20121801.lap07.models.Product;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @GetMapping
    public String viewCart(HttpSession session, Model model) {
        List<Product> cartItems = (List<Product>) session.getAttribute("cart");
        model.addAttribute("cartItems", cartItems);
        return "cart/view";
    }
    @PostMapping("/buy")
    public String buyCart(HttpSession session, Model model) {
        // Lấy giỏ hàng từ session
        List<Product> cartItems = (List<Product>) session.getAttribute("cart");

        // Kiểm tra xem giỏ hàng có sản phẩm không
        if (cartItems == null || cartItems.isEmpty()) {
            model.addAttribute("error", "Giỏ hàng của bạn trống rỗng. Vui lòng thêm sản phẩm vào giỏ hàng trước khi mua.");
            return "redirect:/cart";
        }

        // Xử lý logic mua hàng ở đây (ví dụ: lưu vào cơ sở dữ liệu, gửi email xác nhận, v.v.)

        // Xóa giỏ hàng sau khi mua hàng thành công
        session.removeAttribute("cart");

        // Hiển thị thông báo thành công hoặc chuyển hướng đến trang xác nhận
        model.addAttribute("success", "Đơn hàng của bạn đã được xác nhận và đang được xử lý.");

        return "redirect:/cart";
    }

    @PostMapping("/remove")
    public String removeItemFromCart(@RequestParam Long productId, HttpSession session){
        List<Product> cartItems = (List<Product>) session.getAttribute("cart");
        if (cartItems != null) {
            cartItems.removeIf(item -> {
                Long id = item.getProduct_id();
                return id != null && id.equals(productId);
            });
        }
        return  "redirect:/cart";
    }

}

