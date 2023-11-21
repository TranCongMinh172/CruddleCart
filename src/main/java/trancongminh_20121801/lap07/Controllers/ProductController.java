package trancongminh_20121801.lap07.Controllers;

import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import trancongminh_20121801.lap07.enums.ProductStatus;
import trancongminh_20121801.lap07.models.Product;
import trancongminh_20121801.lap07.services.ProductService;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public String getAll(Model model, Optional<Integer> pageNumber){
        int page = pageNumber.orElse((0));
        Pageable pageable = PageRequest.of(page,10);
        Page<Product> products = productService.getAllProductNotTerminated(pageable);
        model.addAttribute("products", products);
        model.addAttribute("pageSize", products.getTotalPages());
        return "product/products";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model){
    Optional<Product> productOptional = productService.findById(id);
    Product product =productOptional.orElse(new Product());
    model.addAttribute("product",product);
    return "product/update";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product updateProduct){
        System.out.println(id);
            productService.update(id, updateProduct);
            return "redirect:/product";
    }
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id, Model model){
        productService.deleteProduct(id);
        return  "redirect:/product";
    }
    @GetMapping("/insert")
    public String showInsertForm(Model model){
        model.addAttribute("newProduct",new Product());
        return "product/add";
    }
    @PostMapping("/insert")
    public String insertProduct(@ModelAttribute Product product){
        product.setStatus(ProductStatus.ACTIVE);
        productService.insert(product);
        return "redirect:/product";
    }

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable Long id, HttpSession session ){
        Product product = productService.findById(id).orElse(null);
        if(product != null){
            List<Product> cartItem = (List<Product>) session.getAttribute("cart");
            if(cartItem == null){
                cartItem = new ArrayList<>();
            }
            cartItem.add(product);
            session.setAttribute("cart", cartItem);
        }
        return "redirect:/cart";
    }
}
