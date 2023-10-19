package com.ecommerce.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ecommerce.entity.CartItem;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.Purchase;
import com.ecommerce.entity.PurchaseItem;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.PurchaseItemService;
import com.ecommerce.service.PurchaseService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class CartController {

	@Autowired
	private ProductService productService;
	@Autowired
	private PurchaseItemService purchaseItemService;
	@Autowired
	private PurchaseService purchaseService;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	    public String cart(ModelMap map, HttpServletRequest request) 
	    {
		  // check if user is logged in
		  HttpSession session = request.getSession();
		  if (session.getAttribute("user_id") == null) {
			  map.addAttribute("error", "Error, You need to login before adding items to cart");
		  } else {
			  // if cart is already in session then retrieve it else create a new cart list and 
			  // save it to session
			  List<CartItem> cartItems = new ArrayList<CartItem>();
			  if (session.getAttribute("cart_items") != null)
				  cartItems = (List<CartItem>) session.getAttribute("cart_items");
			  
			  // get total of all cart items
			  BigDecimal totalValue = getCartValue(cartItems);
			  map.addAttribute("cartValue", totalValue);
			  map.addAttribute("cartItems", cartItems);
		  }
		  
		  map.addAttribute("pageTitle", "SPORTY SHOES - YOUR CART");
	        return "cart"; 
	    }

	/**
	   * Get total value of items in cart
	   * @param list
	   * @return
	   */
	  private BigDecimal getCartValue(List<CartItem> list) {
		  BigDecimal total = new BigDecimal(0.0);
		  
		  for(CartItem item: list) {
			  BigDecimal dprice = item.getRate().multiply(new BigDecimal(item.getQty()));
			  total= total.add(dprice);
		   }
		  return total;
	  }

	/**
	   * Check if an item is already in the cart
	   * @param list
	   * @param item
	   * @return
	   */
	  private boolean isItemInCart(List<CartItem> list, long item) {
		  boolean retVal = false;
		  
		  for(CartItem thisItem: list) {
			  if (item == thisItem.getProductId()) {
				  retVal = true;
				  break;
			  }
		  }
		  return retVal;
	  }

	@SuppressWarnings("unchecked")
		@RequestMapping(value = "/cartadditem", method = RequestMethod.GET)
		    public String cartAddItem(ModelMap map, HttpServletRequest request,
		    		@RequestParam(value="id", required=true) String productId,
		    		RedirectAttributes redir) 
		    {
			  
			  String page="redirect:cart";
			  
			  // check if user is logged in
			  HttpSession session = request.getSession();
			  if (session.getAttribute("user_id") == null) {
	//		  if (user_id == null) {
				  redir.addFlashAttribute("error", "Error, You need to login before adding items to cart");
	//			  map.addAttribute("error", "Error, You need to login before adding items to cart");
	//			  new DispatcherServlet();
	//			  new HomeController().home(map, request);
				  page="redirect:/";
			  } else {
				  
				  long idValue = Long.parseLong(productId);
				  // if cart is already in session then retrieve it else create a new cart list and 
				  // save it to session
				  List<CartItem> cartItems = new ArrayList<CartItem>();
				  if (session.getAttribute("cart_items") != null)
	//			  if (cart_items != null)
					  cartItems = (List<CartItem>) session.getAttribute("cart_items");
	//				  cartItems = (List<CartItem>) cart_items;
				  if (isItemInCart(cartItems, idValue)) {
					  redir.addFlashAttribute("error", "This item is already in your cart");
					  page="redirect:/";
				  } else {
					  Product product = productService.getProductById(idValue);
					  CartItem item = new CartItem();
					  item.setProductId(idValue);
					  item.setQty(1);
					  item.setRate(product.getPrice());
					  BigDecimal dprice = item.getRate().multiply(new BigDecimal(item.getQty())); 
					  item.setPrice(dprice); 
					  item.setName(product.getName()); 
					  cartItems.add(item);
					  
					  session.setAttribute("cart_items", cartItems);
	//				  map.addAttribute("cart_items", cartItems);
				  }
			  }
			  
		        return page; 
		    }

	@RequestMapping(value = "/cartdeleteitem", method = RequestMethod.GET)
	public String cartDeleteItem(ModelMap map, HttpServletRequest request, 
			@RequestParam(value="id", required=true) String id) 
	{
	  // check if user is logged in
	  HttpSession session = request.getSession();
	  if (session.getAttribute("user_id") == null) {
		  map.addAttribute("error", "Error, You need to login before deleting items from cart");
	  } else {
		  long idValue = Long.parseLong(id);
		  List<CartItem> cartItems = new ArrayList<CartItem>();
		  if (session.getAttribute("cart_items") != null)
			  cartItems = (List<CartItem>) session.getAttribute("cart_items");
		  	  
		  for(CartItem item: cartItems) {
			  if (item.getProductId() == idValue) {
				  cartItems.remove(item);
				  session.setAttribute("cart_items", cartItems);
				  break;
			  }
		   }
	  }	
	    return "redirect:cart"; 
	}

	@RequestMapping(value = "/checkout", method = RequestMethod.GET)
	public String checkout(ModelMap map, HttpServletRequest request) 
	{
	  // check if user is logged in
	  HttpSession session = request.getSession();
	  if (session.getAttribute("user_id") == null) {
		  map.addAttribute("error", "Error, You need to login before checking out");
	  } else {
		  List<CartItem> cartItems = new ArrayList<CartItem>();
		  if (session.getAttribute("cart_items") != null)
			  cartItems = (List<CartItem>) session.getAttribute("cart_items");
		  BigDecimal totalValue = getCartValue(cartItems);
		  map.addAttribute("cartValue", totalValue);
		  map.addAttribute("cartItems", cartItems);
	  }
	  map.addAttribute("pageTitle", "SPORTY SHOES - CHECKOUT");
	    return "checkout"; 
	}

	@RequestMapping(value = "/completepurchase", method = RequestMethod.GET)
	public String completePurchase(ModelMap map, HttpServletRequest request) 
	{
	  // check if user is logged in
	  HttpSession session = request.getSession();
	  if (session.getAttribute("user_id") == null) {
		  map.addAttribute("error", "Error, You need to login before completing purchase");
	  } else {
		  // take items from cart and update the databae 
		  List<CartItem> cartItems = new ArrayList<CartItem>();
		  if (session.getAttribute("cart_items") != null)
			  cartItems = (List<CartItem>) session.getAttribute("cart_items");
		  BigDecimal totalValue = getCartValue(cartItems);
		  
		  long userId = (Long) session.getAttribute("user_id") ;
		  
		  Purchase purchase = new Purchase();
		  purchase.setUserId(userId);
		  purchase.setDate(Calendar.getInstance().getTime());
		  purchase.setTotal(totalValue);
		  long purchaseId = purchaseService.updatePurchase(purchase);
		  
		  for(CartItem item: cartItems) {
			  PurchaseItem pItem = new PurchaseItem();
			  pItem.setPurchaseId(purchaseId);
			  pItem.setProductId(item.getProductId());
			  pItem.setUserId(userId);
			  pItem.setRate(item.getRate());
			  pItem.setQty(item.getQty());
			  pItem.setPrice(item.getPrice());
			  
			  purchaseItemService.updateItem(pItem);
		  }
		  map.addAttribute("cartValue", totalValue);
		  map.addAttribute("cartItems", cartItems);
	
	  }
	
	    return "redirect:confirm";  
	}

	@RequestMapping(value = "/confirm", method = RequestMethod.GET)
	public String confirm(ModelMap map, HttpServletRequest request) 
	{
	  // check if user is logged in
	  HttpSession session = request.getSession();
	  if (session.getAttribute("user_id") == null) {
		  map.addAttribute("error", "Error, You need to login before completing the purchase");
	  } else {
		  // clear items from cart as order has completed 
		  List<CartItem> cartItems = new ArrayList<CartItem>();
		  if (session.getAttribute("cart_items") != null)
			  cartItems = (List<CartItem>) session.getAttribute("cart_items");
		  BigDecimal totalValue = getCartValue(cartItems);
		  map.addAttribute("cartValue", totalValue);
	
		  
		  cartItems.clear();
		  session.setAttribute("cart_items", null);
	  }
	  map.addAttribute("pageTitle", "SPORTY SHOES - PURCHASE CONFIRMATION");
	    return "confirm"; 
	}

}
