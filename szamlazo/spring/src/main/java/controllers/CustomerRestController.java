package controllers;

import exception.EmptyStringException;
import exception.InvalidValueException;
import exception.ObjectAlreadyExistException;
import model.Customer;
import model.Invoice;
import model.Item;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import service.CustomerServiceImpl;
import service.InvoiceServiceImpl;
import service.ItemServiceImpl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("rest")
public class CustomerRestController {

    private static final int sessionTimeoutPeriodinMinutes = 10;
    private static final double freeShippingOver = 5000;
    private static final double shippingPrice = 50;

    @Autowired
    private CustomerServiceImpl customerJsonDB;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private InvoiceServiceImpl invoiceJsonDB;

    @Autowired
    private ItemServiceImpl itemJsonDB;

    private static Map<String, LocalDateTime> sessions = new HashMap<>();

    @PostMapping(value = "/register", produces = "application/json", consumes = "application/json")
    @ResponseBody
    public String registerCustomer(@RequestBody String requestString){
        try {
            Customer customer = new Customer();
            JSONObject request = new JSONObject(requestString);
            customer.setEmail(request.has("email") && !request.isNull("email") ? request.getString("email") : null);
            customer.setPassword(request.has("password") && !request.isNull("password") ? encoder.encode(request.getString("password")) : null);
            customer.setName(request.has("name") && !request.isNull("name") ? request.getString("name") : null);
            customer.setCompany(request.has("company") ? request.getBoolean("company") : null);
            customer.setTaxID(request.has("taxID") && !request.isNull("taxID") ? request.getString("taxID") : null);
            customer.setPhoneNumber(request.has("phoneNumber") && !request.isNull("phoneNumber") ? request.getString("phoneNumber") : null);
            customer.setShipping_address_postcode(request.has("shipping_address_postcode") && !request.isNull("shipping_address_postcode") ? request.getString("shipping_address_postcode") : null);
            customer.setShipping_address_city(request.has("shipping_address_city") && !request.isNull("shipping_address_city") ? request.getString("shipping_address_city") : null);
            customer.setShipping_address_street(request.has("shipping_address_street") && !request.isNull("shipping_address_street") ? request.getString("shipping_address_street") : null);
            customer.setShipping_address_other(request.has("shipping_address_other") && !request.isNull("shipping_address_other") ? request.getString("shipping_address_other") : null);
            customer.setInvoicing_address_postcode(request.has("invoicing_address_postcode") && !request.isNull("invoicing_address_postcode") ? request.getString("invoicing_address_postcode") : null);
            customer.setInvoicing_address_city(request.has("invoicing_address_city") && !request.isNull("invoicing_address_city") ? request.getString("invoicing_address_city") : null);
            customer.setInvoicing_address_street(request.has("invoicing_address_street") && !request.isNull("invoicing_address_street") ? request.getString("invoicing_address_street") : null);
            customer.setInvoicing_address_other(request.has("invoicing_address_other") && !request.isNull("invoicing_address_other") ? request.getString("invoicing_address_other") : null);
            customerJsonDB.addCustomer(customer);
            return new JSONObject().put("error", "0").put("msg", "Sucessful registration!").toString();
        } catch (ObjectAlreadyExistException e) {
            e.printStackTrace();
            return new JSONObject().put("error", "1").put("msg", "User exist!").toString();
        } catch (InvalidValueException e) {
            e.printStackTrace();
            return new JSONObject().put("error", "2").put("msg", "Required field missing!").toString();
        } catch (EmptyStringException e) {
            e.printStackTrace();
            return new JSONObject().put("error", "2").put("msg", "Required field missing!").toString();
        } catch (NullPointerException e){
            e.printStackTrace();
            return new JSONObject().put("error", "2").put("msg", "Required field missing!").toString();
        } catch (Exception e){
            e.printStackTrace();
            return new JSONObject().put("error", "3").put("msg", "Unexpected error!").toString();
        }
    }

    @PostMapping(value = "/login", produces = "application/json", consumes = "application/json")
    @ResponseBody
    public String loginCustomer(@RequestBody String requestString, HttpServletResponse response,
                                @CookieValue("JSESSIONID") String session){
        try{
            JSONObject request = new JSONObject(requestString);
            String email = request.getString("email");
            String password = request.getString("password");

            Customer customer = customerJsonDB.getCustomerByEmail(email);
            if (encoder.matches(password, customer.getPassword())){
                sessions.put(session, LocalDateTime.now());

                Cookie cookie = new Cookie("user", customer.getEmail());
                cookie.setHttpOnly(false);
                cookie.setPath("/");
                response.addCookie(cookie);

                return new JSONObject().put("error", "0").put("msg", "Login sucessful!").toString();
            } else {
                return new JSONObject().put("error", "2").put("msg", "Incorrect password!").toString();
            }

        }catch (NullPointerException e){
            e.printStackTrace();
            return new JSONObject().put("error", "2").put("msg", "Required field missing!").toString();
        } catch (Exception e){
            return new JSONObject().put("error", "3").put("msg", "Unexpected error!").toString();
        }
    }

    @GetMapping(value = "/authorize", produces = "application/json")
    public String authorize(@CookieValue(value = "JSESSIONID", required = false) String session,
                            @CookieValue(value = "user", required = false) String email){
        System.out.println("SESSION: " + session);
        System.out.println("USER: " + email);

        try{
            if (session == null || !sessions.containsKey(session)){
                return new JSONObject().put("error", "1").put("msg", "Please login first!").toString();
            }
            long period = ChronoUnit.MINUTES.between(sessions.get(session), LocalDateTime.now());
            if (sessions.containsKey(session) && period <= sessionTimeoutPeriodinMinutes){
                return new JSONObject().put("error", "0").put("msg", "You are already logged in!").toString();
            } else {
                sessions.remove(session);
                return new JSONObject().put("error", "1").put("msg", "Please login first!").toString();
            }
        }catch (Exception e){
            e.printStackTrace();
            return new JSONObject().put("error", "2").put("msg", "User not defined").toString();
        }
    }

    @PostMapping(value = "/checkout", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public String checkOut(@CookieValue(value = "JSESSIONID", required = false) String session,
                           @CookieValue(value = "user", required = false) String email,
                           @CookieValue(value = "cart") String cartString){
        try{
            JSONObject authorizeReasponse = new JSONObject(authorize(session, email));
            if (authorizeReasponse.getString("error").equals("0")){
                System.out.println("USER: " + email + "\nCART: " + cartString);
                JSONArray cart = new JSONArray(cartString);
                Map<String, Integer> cartMap = new HashMap<>();
                double totalPrice = 0;
                for (int i = 0; i < cart.length(); i++){
                    JSONObject item = new JSONObject(cart.get(i).toString());
                    String barcode = item.getString("barcode");
                    int qty = item.getInt("quantity");
                    cartMap.put(barcode , qty);
                    totalPrice += itemJsonDB.getItem(barcode).getPrice() * qty;
                }
                Invoice invoice = new Invoice(
                       customerJsonDB.getCustomerByEmail(email).getId(),
                        cartMap,
                        totalPrice >= freeShippingOver ? 0 : shippingPrice
                );
                invoiceJsonDB.addInvoice(invoice);
                return new JSONObject().put("error", "0").put("msg", "Sucessfull checkout!").toString();
            } else {
                return authorizeReasponse.toString();
            }
        } catch (NullPointerException e){
            e.printStackTrace();
            return new JSONObject().put("error", "2").put("msg", "Cart is not defined!").toString();
        } catch (Exception e){
            e.printStackTrace();
            return new JSONObject().put("error", "3").put("msg", "Unexpected error!").toString();
        }

    }

    @GetMapping(value = "/invoices", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public String getUserInvoices(@CookieValue(value = "JSESSIONID", required = false) String session,
                                  @CookieValue(value = "user", required = false) String email){
        try {
            JSONObject authorizeReasponse = new JSONObject(authorize(session, email));
            if (authorizeReasponse.getString("error").equals("0")) {
                Customer customer = customerJsonDB.getCustomerByEmail(email);
                Collection<Invoice> invoices = invoiceJsonDB.getUserInvoices(customer.getId());
                JSONArray responseArray = new JSONArray();
                for (Invoice invoice :
                        invoices) {
                    JSONObject obj = new JSONObject();
                    JSONArray itemList = new JSONArray();
                    Map<String, Integer> itemsAndQuantity = invoice.getItems();
                    double totalPrice = 0;
                    int totalQuantity = 0;
                    for (String barcode :
                            itemsAndQuantity.keySet()) {
                        Item itemObj = itemJsonDB.getItem(barcode);
                        JSONObject item = new JSONObject();
                        int quantity = itemsAndQuantity.get(barcode);
                        double price = itemsAndQuantity.get(barcode) * itemObj.getPrice();
                        totalPrice += price;
                        totalQuantity += quantity;
                        item.put("name", itemObj.getName())
                                .put("manufacturer", itemObj.getManufacturer())
                                .put("barcode", itemObj.getBarcode())
                                .put("quantity", quantity)
                                .put("price", price)
                                .put("unitPrice", itemObj.getPrice());
                        itemList.put(item);
                    }
                    obj.put("id", invoice.getId())
                            .put("itemList", itemList)
                            .put("totalPrice", totalPrice)
                            .put("totalQuantity", totalQuantity)
                            .put("date", invoice.getDate().toString())
                            .put("shipping", invoice.getShipping());

                    responseArray.put(obj);
                }

                JSONObject billing = new JSONObject()
                        .put("name", customer.getName())
                        .put("city", customer.getInvoicing_address_city())
                        .put("postcode", customer.getInvoicing_address_postcode())
                        .put("street", customer.getInvoicing_address_street())
                        .put("other", customer.getInvoicing_address_other());

                JSONObject shipping = new JSONObject()
                        .put("name", customer.getName())
                        .put("city", customer.getShipping_address_city())
                        .put("postcode", customer.getShipping_address_postcode())
                        .put("street", customer.getShipping_address_street())
                        .put("other", customer.getShipping_address_other());

                JSONObject customerInfo = new JSONObject()
                        .put("email", customer.getEmail())
                        .put("phone", customer.getPhoneNumber())
                        .put("tax", customer.getTaxID());

                System.out.println("BILLING: " + billing);
                System.out.println("SHIPPING: " + shipping);
                System.out.println("response: " + responseArray.toString());

                return new JSONObject().put("error", "0")
                        .put("invoices", responseArray)
                        .put("billing", billing)
                        .put("shipping", shipping)
                        .put("customerInfo", customerInfo).toString();
            } else {
                return authorizeReasponse.toString();
            }
        }catch (NullPointerException e){
            return new JSONObject().put("error", "2").put("msg", "Invoices not found!").toString();
        }catch (Exception e){
            return new JSONObject().put("error", "3").put("msg", "Unexpected error!").toString();
        }
    }


}
