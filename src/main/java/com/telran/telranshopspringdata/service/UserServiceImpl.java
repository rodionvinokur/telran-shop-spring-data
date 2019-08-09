package com.telran.telranshopspringdata.service;

import com.telran.telranshopspringdata.controller.dto.*;
import com.telran.telranshopspringdata.data.*;
import com.telran.telranshopspringdata.data.document.*;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.telran.telranshopspringdata.service.Mapper.map;
import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;

@Service
@Transactional(isolation = READ_COMMITTED)
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private ShoppingCartRepository shoppingCartRepository;
    private ProductOrderRepository productOrderRepository;
    private OrderRepository orderRepository;
    private UserDetailsRepository userDetailsRepository;

    public UserServiceImpl(UserRepository userRepository,
                           ProductRepository productRepository,
                           CategoryRepository categoryRepository,
                           ShoppingCartRepository shoppingCartRepository,
                           ProductOrderRepository productOrderRepository,
                           OrderRepository orderRepository,
                           UserDetailsRepository userDetailsRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.productOrderRepository = productOrderRepository;
        this.orderRepository = orderRepository;
        this.userDetailsRepository = userDetailsRepository;
    }

    @Override
    public Optional<UserDto> addUserInfo(String email, String name, String phone) {
        if (name == null || phone == null || name.trim().isEmpty() || phone.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Phone or/and name are illegal");
        }
        UserDetailsEntity ude = userDetailsRepository.findById(email).get();
        if (!userRepository.existsById(email)) {
            UserEntity userEntity = UserEntity.builder()
                    .balance(BigDecimal.ZERO)
                    .detail(ude)
                    .email(email)
                    .name(name)
                    .phone(phone)
                    .build();
            userRepository.save(userEntity);
            return Optional.of(map(userEntity));
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User with email {" + email + "} not found");
    }

    @Override
    public Optional<UserDto> getUserInfo(String email) {
        UserEntity userEntity = userRepository.getUserByEmail(email);
        return Optional.of(map(userEntity));
    }

    @Override
    public Optional<ShoppingCartDto> addProductToCart(String userEmail, String productId, int count) {
        UserEntity userEntity = userRepository.getUserByEmail(userEmail);
        ProductEntity productEntity = productRepository.getProductById(productId);
        ShoppingCartEntity shoppingCartEntity = userEntity.getShoppingCart();
        if (shoppingCartEntity == null) {
            shoppingCartEntity = ShoppingCartEntity.builder()
                    .date(Timestamp.valueOf(LocalDateTime.now()))
                    .owner(userEntity)
                    .products(new ArrayList<>())
                    .build();
            shoppingCartRepository.save(shoppingCartEntity);
            userEntity.setShoppingCart(shoppingCartEntity); //add shopping cart to user
        }
        Optional<ProductOrderEntity> productOrderEntityOpt = shoppingCartEntity.getProducts()
                .stream()
                .filter(x -> x.getProductId().equals(productId))
                .findFirst();
        if (productOrderEntityOpt.equals(Optional.empty())) {
            productOrderEntityOpt = Optional.of(ProductOrderEntity.builder()
                    .productId(productId)
                    .category(productEntity.getCategory())
                    .count(count)
                    .name(productEntity.getName())
                    .price(productEntity.getPrice())
                    .shoppingCart(shoppingCartEntity) //add shopping cart to product_order
                    .build()
            );
            productOrderRepository.save(productOrderEntityOpt.get());
            shoppingCartEntity.getProducts().add(productOrderEntityOpt.get()); // add product_order to shopping cart
        } else {
            productOrderEntityOpt.get().setCount(productOrderEntityOpt.get().getCount() + count);
        }
        return Optional.of(Mapper.map(shoppingCartEntity));
    }

    @Override
    public Optional<ShoppingCartDto> removeProductFromCart(String userEmail, String productId, int count) {
        UserEntity userEntity = userRepository.getUserByEmail(userEmail);
        ShoppingCartEntity shoppingCartEntity = getShoppingCartByUser(userEntity);
        if (shoppingCartEntity.getProducts().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Shopping cart is empty");
        }
        ProductOrderEntity productOrderEntity = shoppingCartEntity.getProducts()
                .stream()
                .filter(x -> x.getProductId().equals(productId))
                .findFirst()
                .orElse(null);
        if (productOrderEntity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with id = " + productId + " not found in shopping cart");
        }
        if (count < 0 || productOrderEntity.getCount() < count) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Illegal argument value (count)");
        }
        if (productOrderEntity.getCount() > count) {
            productOrderEntity.setCount(productOrderEntity.getCount() - count);
        } else {
            shoppingCartEntity.getProducts().remove(productOrderEntity);
            productOrderRepository.delete(productOrderEntity);
        }
        return Optional.of(Mapper.map(shoppingCartEntity));
    }

    @Override
    public Optional<ShoppingCartDto> getShoppingCart(String userEmail) {
        UserEntity userEntity = userRepository.getUserByEmail(userEmail);
        ShoppingCartEntity shoppingCartEntity = getShoppingCartByUser(userEntity);
        return Optional.of(Mapper.map(shoppingCartEntity));
    }

    private ShoppingCartEntity getShoppingCartByUser(UserEntity userEntity) {
        ShoppingCartEntity shoppingCartEntity = userEntity.getShoppingCart();
        if (shoppingCartEntity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Shopping cart for user with email = " + userEntity.getEmail() + " not found");
        }
        return shoppingCartEntity;
    }

    @Override
    public boolean clearShoppingCart(String userEmail) {
        UserEntity userEntity = userRepository.getUserByEmail(userEmail);
        ShoppingCartEntity shoppingCartEntity = getShoppingCartByUser(userEntity);
        if (shoppingCartEntity.getProducts().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Shopping cart is empty");
        }
        val list = new ArrayList<>(shoppingCartEntity.getProducts().stream().map(ProductOrderEntity::getId).collect(Collectors.toList()));
        shoppingCartEntity.getProducts().clear();
        productOrderRepository.deleteProductOrderEntitiesByShoppingCart(list);
        return true;
    }

    @Override
    public List<OrderDto> getOrders(String userEmail) {
        UserEntity userEntity = userRepository.getUserByEmail(userEmail);
        return userEntity.getOrders().stream().map(Mapper::map).collect(Collectors.toList());
    }

    @Override
    public Optional<OrderDto> checkout(String userEmail) {
        UserEntity userEntity = userRepository.getUserByEmail(userEmail);
        ShoppingCartEntity shoppingCartEntity = getShoppingCartByUser(userEntity);
        if (shoppingCartEntity.getProducts().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Shopping cart is empty");
        }
        BigDecimal userAmount = userEntity.getBalance();
        BigDecimal currentAmountInCart = shoppingCartEntity.getProducts()
                .stream()
                .map(x -> x.getPrice().multiply(new BigDecimal(x.getCount())))
                .reduce(new BigDecimal(0.00), (x, y) -> x.add(y));
        if (currentAmountInCart.compareTo(userAmount) > 0) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Insufficient funds");
        }
        userEntity.setBalance(userAmount.subtract(currentAmountInCart));
        OrderEntity orderEntity = OrderEntity.builder()
                .date(Timestamp.valueOf(LocalDateTime.now()))
                .owner(userEntity)
                .status(OrderStatus.DONE)
                .products(shoppingCartEntity.getProducts())
                .build();
        orderRepository.save(orderEntity);
        shoppingCartEntity.setProducts(new ArrayList<>());
        val listProductOrderIdForUpdate = orderEntity.getProducts().stream().map(ProductOrderEntity::getId).collect(Collectors.toList());
        productOrderRepository.updateProductOrderEntitiesApplyCheckout(
                listProductOrderIdForUpdate
                , orderEntity);
        userEntity.getOrders().add(orderEntity);
        return Optional.of(Mapper.map(orderEntity));
    }

    @Override
    public boolean addBalance(String userEmail, BigDecimal price) {
        userRepository.getUserByEmail(userEmail).setBalance(price);
        return true;
    }

}
