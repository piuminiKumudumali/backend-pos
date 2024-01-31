package com.ijse.ijse103databasepos.service;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.ijse103databasepos.dto.OrderDto;
import com.ijse.ijse103databasepos.entity.Item;
import com.ijse.ijse103databasepos.entity.Order;
import com.ijse.ijse103databasepos.entity.Stock;
import com.ijse.ijse103databasepos.repository.ItemRepository;
import com.ijse.ijse103databasepos.repository.OrderRepository;
import com.ijse.ijse103databasepos.repository.StockRepository;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private StockRepository stockRepository;

    @Override
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }
    @Override
    public Order createOrder(OrderDto orderDto){
        Order order=new Order();
        List<Long> items=orderDto.getItems();
        Set<Item> itemSet=new HashSet<>();
        Double total;
        order.setTotal(0.0);

        for(Long itemId:items){
            Item item=itemRepository.findById(itemId).orElse(null);
            Stock stock=new Stock();
            
            if (item!=null) {
                itemSet.add(item);
                order.setTotal(order.getTotal()+item.getUnitPrice());
            }
        }

        Double tax=order.getTotal()/100*15;
        order.setTax(order.getTotal()*(15/100));
        order.setOrderTime(LocalDateTime.now());
        order.setItems(itemSet);
        
        return orderRepository.save(order);
    }
    @Override
    public Order getOrderById(Long id){
        return orderRepository.findById(id).orElse(null);
    }
    
}
