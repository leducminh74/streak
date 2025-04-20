package vn.edu.hcmuaf.fit.online.shoe.shop.controller;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.hcmuaf.fit.online.shoe.shop.entity.Account;
import vn.edu.hcmuaf.fit.online.shoe.shop.entity.Order;
import vn.edu.hcmuaf.fit.online.shoe.shop.entity.OrderDetail;
import vn.edu.hcmuaf.fit.online.shoe.shop.entity.OrderStatus;
import vn.edu.hcmuaf.fit.online.shoe.shop.entity.Payment;
import vn.edu.hcmuaf.fit.online.shoe.shop.entity.Product;
import vn.edu.hcmuaf.fit.online.shoe.shop.payload.OrderDetailRequest;
import vn.edu.hcmuaf.fit.online.shoe.shop.payload.OrderRequest;
import vn.edu.hcmuaf.fit.online.shoe.shop.service.AccountService;
import vn.edu.hcmuaf.fit.online.shoe.shop.service.OrderService;

@RestController
@RequestMapping(path = "api/v1")
@CrossOrigin(origins = "*")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private AccountService accountService;

	@GetMapping("/order")
	public ResponseEntity<List<Order>> getAllOrder() {
		List<Order> list = orderService.getAllOrder();
		return new ResponseEntity<List<Order>>(list, HttpStatus.OK);
	}

	@GetMapping("/order/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
		Optional<Order> order = orderService.getOrderById(id);
		if (order.isPresent()) {
			return new ResponseEntity<>(order.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("/order")
	public ResponseEntity<Long> createOrder(@RequestBody OrderRequest orderRequest) throws UnsupportedEncodingException, MessagingException {
		Optional<OrderStatus> orderStatus = orderService.getOrderStatusById((long) 1);
		Optional<Account> account = accountService.getAccountById(orderRequest.getUserId());
		Optional<Payment> payment = orderService.getPaymentById(orderRequest.getPaymentId());

		if (orderStatus.isPresent() && account.isPresent() && payment.isPresent()) {
			Order order = new Order(account.get(), orderRequest.getTotal(), payment.get(), orderRequest.getFullName(),
					orderRequest.getAddress(), orderRequest.getPhoneNumber(), orderStatus.get());
			Order o = orderService.createOrder(order);
			if (o != null) {
				DecimalFormat formatter = new DecimalFormat("###,###,###");
				String subject = "Xác nhận đặt hàng thành công";
				   String content = "<!DOCTYPE html>\n" +
			                "<html>\n" +
			                "<head>\n" +
			                "<title></title>\n" +
			                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
			                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
			                "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\n" +
			                "<style type=\"text/css\">\n" +
			                "\n" +
			                "body, table, td, a { -webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; }\n" +
			                "table, td { mso-table-lspace: 0pt; mso-table-rspace: 0pt; }\n" +
			                "img { -ms-interpolation-mode: bicubic; }\n" +
			                "\n" +
			                "img { border: 0; height: auto; line-height: 100%; outline: none; text-decoration: none; }\n" +
			                "table { border-collapse: collapse !important; }\n" +
			                "body { height: 100% !important; margin: 0 !important; padding: 0 !important; width: 100% !important; }\n" +
			                "\n" +
			                "\n" +
			                "a[x-apple-data-detectors] {\n" +
			                "    color: inherit !important;\n" +
			                "    text-decoration: none !important;\n" +
			                "    font-size: inherit !important;\n" +
			                "    font-family: inherit !important;\n" +
			                "    font-weight: inherit !important;\n" +
			                "    line-height: inherit !important;\n" +
			                "}\n" +
			                "\n" +
			                "@media screen and (max-width: 480px) {\n" +
			                "    .mobile-hide {\n" +
			                "        display: none !important;\n" +
			                "    }\n" +
			                "    .mobile-center {\n" +
			                "        text-align: center !important;\n" +
			                "    }\n" +
			                "}\n" +
			                "div[style*=\"margin: 16px 0;\"] { margin: 0 !important; }\n" +
			                "</style>\n" +
			                "<body style=\"margin: 0 !important; padding: 0 !important; background-color: #eeeeee;\" bgcolor=\"#eeeeee\">\n" +
			                "\n" +
			                "\n" +
			                "<div style=\"display: none; font-size: 1px; color: #fefefe; line-height: 1px; font-family: Open Sans, Helvetica, Arial, sans-serif; max-height: 0px; max-width: 0px; opacity: 0; overflow: hidden;\">\n" +
			                "For what reason would it be advisable for me to think about business content? That might be little bit risky to have crew member like them. \n" +
			                "</div>\n" +
			                "\n" +
			                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
			                "    <tr>\n" +
			                "        <td align=\"center\" style=\"background-color: #eeeeee;\" bgcolor=\"#eeeeee\">\n" +
			                "        \n" +
			                "        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:600px;\">\n" +
			                "            <tr>\n" +
			                "                <td align=\"center\" valign=\"top\" style=\"font-size:0; padding: 35px;\" bgcolor=\"#F44336\">\n" +
			                "               \n" +
			                "                <div style=\"display:inline-block; max-width:50%; min-width:100px; vertical-align:top; width:100%;\">\n" +
			                "                    <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:300px;\">\n" +
			                "                        <tr>\n" +
			                "                            <td align=\"left\" valign=\"top\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 36px; font-weight: 800; line-height: 48px;\" class=\"mobile-center\">\n" +
			                "                                <h1 style=\"font-size: 36px; font-weight: 800; margin: 0; color: #ffffff;\">Box Shoe</h1>\n" +
			                "                            </td>\n" +
			                "                        </tr>\n" +
			                "                    </table>\n" +
			                "                </div>\n" +
			                "                \n" +
			                "                <div style=\"display:inline-block; max-width:50%; min-width:100px; vertical-align:top; width:100%;\" class=\"mobile-hide\">\n" +
			                "                    <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:300px;\">\n" +
			                "                        <tr>\n" +
			                "                            <td align=\"right\" valign=\"top\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 48px; font-weight: 400; line-height: 48px;\">\n" +
			                "                                <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"right\">\n" +
			                "                                    <tr>\n" +
			                "                                        <td style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400;\">\n" +
			                "                                            <p style=\"font-size: 18px; font-weight: 400; margin: 0; color: #ffffff;\"><a href=\"#\" target=\"_blank\" style=\"color: #ffffff; text-decoration: none;\">Shop &nbsp;</a></p>\n" +
			                "                                        </td>\n" +
			                "                                        <td style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400; line-height: 24px;\">\n" +
			                "                                            <a href=\"#\" target=\"_blank\" style=\"color: #ffffff; text-decoration: none;\"><img src=\"https://img.icons8.com/color/48/000000/small-business.png\" width=\"27\" height=\"23\" style=\"display: block; border: 0px;\"/></a>\n" +
			                "                                        </td>\n" +
			                "                                    </tr>\n" +
			                "                                </table>\n" +
			                "                            </td>\n" +
			                "                        </tr>\n" +
			                "                    </table>\n" +
			                "                </div>\n" +
			                "              \n" +
			                "                </td>\n" +
			                "            </tr>\n" +
			                "            <tr>\n" +
			                "                <td align=\"center\" style=\"padding: 35px 35px 20px 35px; background-color: #ffffff;\" bgcolor=\"#ffffff\">\n" +
			                "                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:600px;\">\n" +
			                "                    <tr>\n" +
			                "                        <td align=\"center\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding-top: 25px;\">\n" +
			                "                            <img src=\"https://img.icons8.com/carbon-copy/100/000000/checked-checkbox.png\" width=\"125\" height=\"120\" style=\"display: block; border: 0px;\" /><br>\n" +
			                "                            <h2 style=\"font-size: 30px; font-weight: 800; line-height: 36px; color: #333333; margin: 0;\">\n" +
			                "                                Cảm ơn vì đơn hàng của bạn!\n" +
			                "                            </h2>\n" +
			                "                        </td>\n" +
			                "                    </tr>\n" +
			                "                    <tr>\n" +
			                "                        <td align=\"left\" style=\"padding-top: 20px;\">\n" +
			                "                            <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\n" +
			                "                                <tr>\n" +
			                "                                    <td width=\"75%\" align=\"left\" bgcolor=\"#eeeeee\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 800; line-height: 24px; padding: 10px;\">\n" +
			                "                                        Xác nhận đơn hàng #\n" +
			                "                                    </td>\n" +
			                "                                    <td width=\"25%\" align=\"left\" bgcolor=\"#eeeeee\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 800; line-height: 24px; padding: 10px;\">\n" +
			                "                                        "+o.getId()+"\n" +
			                "                                    </td>\n" +
			                "                                </tr>\n" +
			                "                                <tr>\n" +
			                "                                    <td width=\"75%\" align=\"left\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 15px 10px 5px 10px;\">\n" +
			                "                                        Tổng tiền thanh toán ("+o.getPayment().getName()+")\n" +
			                "                                    </td>\n" +
			                "                                    <td width=\"25%\" align=\"left\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 15px 10px 5px 10px;\">\n" +
			                "                                        "+formatter.format(o.getTotal())+"đ\n" +
			                "                                    </td>\n" +
			                "                                </tr>\n" +
			                "                                <tr>\n" +
			                "                                    <td width=\"75%\" align=\"left\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 5px 10px;\">\n" +
			                "                                        Phí giao hàng\n" +
			                "                                    </td>\n" +
			                "                                    <td width=\"25%\" align=\"left\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 5px 10px;\">\n" +
			                "                                        0đ\n" +
			                "                                    </td>\n" +
			                "                                </tr>\n" +
			                "                            </table>\n" +
			                "                        </td>\n" +
			                "                    </tr>\n" +
			                "                    <tr>\n" +
			                "                        <td align=\"left\" style=\"padding-top: 20px;\">\n" +
			                "                            <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\n" +
			                "                                <tr>\n" +
			                "                                    <td width=\"75%\" align=\"left\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 800; line-height: 24px; padding: 10px; border-top: 3px solid #eeeeee; border-bottom: 3px solid #eeeeee;\">\n" +
			                "                                        Tổng cộng	\n" +
			                "                                    </td>\n" +
			                "                                    <td width=\"25%\" align=\"left\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 800; line-height: 24px; padding: 10px; border-top: 3px solid #eeeeee; border-bottom: 3px solid #eeeeee;\">\n" +
			                "                                        "+formatter.format(o.getTotal())+"đ\n" +
			                "                                    </td>\n" +
			                "                                </tr>\n" +
			                "                            </table>\n" +
			                "                        </td>\n" +
			                "                    </tr>\n" +
			                "                </table>\n" +
			                "                \n" +
			                "                </td>\n" +
			                "            </tr>\n" +
			                "             <tr>\n" +
			                "                <td align=\"center\" height=\"100%\" valign=\"top\" width=\"100%\" style=\"padding: 0 35px 35px 35px; background-color: #ffffff;\" bgcolor=\"#ffffff\">\n" +
			                "                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:660px;\">\n" +
			                "                    <tr>\n" +
			                "                        <td align=\"center\" valign=\"top\" style=\"font-size:0;\">\n" +
			                "                            <div style=\"display:inline-block; max-width:50%; min-width:240px; vertical-align:top; width:100%;\">\n" +
			                "\n" +
			                "                                <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:300px;\">\n" +
			                "                                    <tr>\n" +
			                "                                        <td align=\"left\" valign=\"top\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px;\">\n" +
			                "                                            <p style=\"font-weight: 800;\">Địa chỉ giao hàng</p>\n" +
			                "                                            <p>"+o.getAddress()+"</p>\n" +
			                "\n" +
			                "                                        </td>\n" +
			                "                                    </tr>\n" +
			                "                                </table>\n" +
			                "                            </div>\n" +
			                "                            <div style=\"display:inline-block; max-width:50%; min-width:240px; vertical-align:top; width:100%;\">\n" +
			                "                                <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:300px;\">\n" +
			                "                                    <tr>\n" +
			                "                                        <td align=\"left\" valign=\"top\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px;\">\n" +
			                "                                            <p style=\"font-weight: 800;\">Số điện thoại</p>\n" +
			                "                                            <p>"+o.getPhoneNumber()+"</p>\n" +
			                "                                        </td>\n" +
			                "                                    </tr>\n" +
			                "                                </table>\n" +
			                "                            </div>\n" +
			                "                        </td>\n" +
			                "                    </tr>\n" +
			                "                </table>\n" +
			                "                </td>\n" +
			                "            </tr>\n" +
			                "            <tr>\n" +
			                "                <td align=\"center\" style=\" padding: 35px; background-color: #ff7361;\" bgcolor=\"#1b9ba3\">\n" +
			                "                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:600px;\">\n" +
			                "                    <tr>\n" +
			                "                        <td align=\"center\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding-top: 25px;\">\n" +
			                "                            <h2 style=\"font-size: 24px; font-weight: 800; line-height: 30px; color: #ffffff; margin: 0;\">\n" +
			                "                                Giảm 10% cho đơn hàng tiếp theo.\n" +
			                "                            </h2>\n" +
			                "                        </td>\n" +
			                "                    </tr>\n" +
			                "                    <tr>\n" +
			                "                        <td align=\"center\" style=\"padding: 25px 0 15px 0;\">\n" +
			                "                            <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
			                "                                <tr>\n" +
			                "                                    <td align=\"center\" style=\"border-radius: 5px;\" bgcolor=\"#66b3b7\">\n" +
			                "                                      <a href=\"#\" target=\"_blank\" style=\"font-size: 18px; font-family: Open Sans, Helvetica, Arial, sans-serif; color: #ffffff; text-decoration: none; border-radius: 5px; background-color: #F44336; padding: 15px 30px; display: block;\">Tiếp tục xem sản phẩm</a>\n" +
			                "                                    </td>\n" +
			                "                                </tr>\n" +
			                "                            </table>\n" +
			                "                        </td>\n" +
			                "                    </tr>\n" +
			                "                </table>\n" +
			                "                </td>\n" +
			                "            </tr>\n" +
			                "            <tr>\n" +
			                "                <td align=\"center\" style=\"padding: 35px; background-color: #ffffff;\" bgcolor=\"#ffffff\">\n" +
			                "                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:600px;\">\n" +
			                "                    <tr>\n" +
			                "                        <td align=\"left\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 14px; font-weight: 400; line-height: 24px;\">\n" +
			                "                            <p style=\"font-size: 14px; font-weight: 400; line-height: 20px; color: #777777;\">\n" +
			                "                                Nếu bạn không sử dụng email này để tạo tài khoản.Vui lòng bỏ qua email này!\n" +
			                "                            </p>\n" +
			                "                        </td>\n" +
			                "                    </tr>\n" +
			                "                </table>\n" +
			                "                </td>\n" +
			                "            </tr>\n" +
			                "        </table>\n" +
			                "        </td>\n" +
			                "    </tr>\n" +
			                "</table>\n" +
			                "    \n" +
			                "</body>\n" +
			                "</html>\n";
				   accountService.sendEmail(o.getAccount().getEmail(), content, subject);
				return new ResponseEntity<Long>(o.getId(), HttpStatus.OK);
			}
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	@GetMapping("/orderDetail")
	public ResponseEntity<List<OrderDetail>> getAllOrderDetail() {
		List<OrderDetail> list = orderService.getAllOrderDetail();
		if (list.isEmpty()) {
			return new ResponseEntity<List<OrderDetail>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<OrderDetail>>(list, HttpStatus.OK);
	}

	@PostMapping("/orderDetail")
	public ResponseEntity<OrderDetail> createOrderDetail(@RequestBody OrderDetailRequest orderDetailRequest) {
		Optional<Order> order = orderService.getOrderById(orderDetailRequest.get_orderId());
		Product product = orderDetailRequest.get_product();
		if (orderDetailRequest.get_orderId() != null && order.isPresent() && product != null) {
			OrderDetail oDetail = new OrderDetail(order.get(), product, orderDetailRequest.get_price(),
					orderDetailRequest.get_quantity());
			orderService.updateProductQuantityOrder(oDetail);
			orderService.createOrderDetail(oDetail);
			return new ResponseEntity<OrderDetail>(oDetail, HttpStatus.OK);
		}

		return new ResponseEntity<OrderDetail>(HttpStatus.NOT_FOUND);

	}

	@GetMapping("/order/account/{id}")
	public ResponseEntity<List<Order>> getAllOrderByAccount(@PathVariable Long id) {
		System.out.println(id);
		Optional<Account> acc = accountService.getAccountById(id);
		System.out.println(acc);
		if (acc.isPresent()) {
			List<Order> list = orderService.getAllOrderByAccount(acc.get());
			if (!list.isEmpty()) {
				return new ResponseEntity<List<Order>>(list, HttpStatus.OK);
			}
		}
		return new ResponseEntity<List<Order>>(HttpStatus.NO_CONTENT);

	}

	@GetMapping("/orderDetail/order/{id}")
	public ResponseEntity<List<OrderDetail>> getAllOrderDetailByOrder(@PathVariable Long id) {
		Optional<Order> order = orderService.getOrderById(id);
		if (order.isPresent()) {
			List<OrderDetail> list = orderService.getAllOrderDetailByOrder(order.get());
			if (!list.isEmpty()) {
				return new ResponseEntity<List<OrderDetail>>(list, HttpStatus.OK);
			}
		}

		return new ResponseEntity<List<OrderDetail>>(HttpStatus.NO_CONTENT);

	}

	@PostMapping("/order/cancel")
	public ResponseEntity<Order> cancelOrder(@RequestBody Long id) throws Exception {
		Optional<Order> order = orderService.getOrderById(id);
		System.out.println(id);
		if (order.isPresent()) {
			Order order1 = orderService.cancelOrder(order.get());
			updateQuantityProduct(order);
			return new ResponseEntity<Order>(order1, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	@GetMapping("/order/accept/{id}")
	public ResponseEntity<Order> acceptOrder(@PathVariable Long id) throws Exception {
		Order order1 = orderService.acceptOrder(id);
		if (order1 != null) {
			return new ResponseEntity<Order>(order1, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	@GetMapping("/order/shipping/{id}")
	public ResponseEntity<Order> shippingOrder(@PathVariable Long id) throws Exception {
		Order order1 = orderService.shipping(id);
		if (order1 != null) {
			return new ResponseEntity<Order>(order1, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	@GetMapping("/order/delivered/{id}")
	public ResponseEntity<Order> deliveredOrder(@PathVariable Long id) throws Exception {
		Order order1 = orderService.delivered(id);
		if (order1 != null) {
			return new ResponseEntity<Order>(order1, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	public void updateQuantityProduct(Optional<Order> order) throws Exception {
		if (order.isPresent()) {
			List<OrderDetail> list = orderService.getAllOrderDetailByOrder(order.get());
			if (!list.isEmpty()) {
				for (OrderDetail orderDetail : list) {
					Product p = orderService.updateProductQuantityCancelOrder(orderDetail);
				}
			}
		} else {
			throw new Exception("Not found order: " + order.get());
		}

	}
	



}
