package com.mulit.bookstore.notifications.events;

import com.mulit.bookstore.notifications.domain.NotificationService;
import com.mulit.bookstore.notifications.domain.models.OrderCancelledEvent;
import com.mulit.bookstore.notifications.domain.models.OrderCreatedEvent;
import com.mulit.bookstore.notifications.domain.models.OrderDeliveredEvent;
import com.mulit.bookstore.notifications.domain.models.OrderErrorEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
// @Transactional
public class OrderEventHandler {

    private final NotificationService notificationService;

    public OrderEventHandler(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @RabbitListener(queues = "${notifications.new-orders-queue}")
    void handleOrderCreatedEvent(OrderCreatedEvent event) {
        System.out.println("Order Created Event: " + event);
        notificationService.sendOrderCreatedNotification(event);
    }

    @RabbitListener(queues = "${notifications.delivered-orders-queue}")
    void handleOrderDeliveredEvent(OrderDeliveredEvent event) {
        System.out.println("Order Created Event: " + event);
        notificationService.sendOrderDeliveredNotification(event);
    }

    @RabbitListener(queues = "${notifications.cancelled-orders-queue}")
    void handleOrderCancelledEvent(OrderCancelledEvent event) {
        System.out.println("Order Created Event: " + event);
        notificationService.sendOrderCancelledNotification(event);
    }

    @RabbitListener(queues = "${notifications.error-orders-queue}")
    void handleOrderErrorEvent(OrderErrorEvent event) {
        System.out.println("Order Created Event: " + event);
        notificationService.sendOrderErrorEventNotification(event);
    }
}

   /* private static final Logger log = LoggerFactory.getLogger(OrderEventHandler.class);

       private final NotificationService notificationService;
       private final OrderEventRepository orderEventRepository;

       public OrderEventHandler(NotificationService notificationService, OrderEventRepository orderEventRepository) {
           this.notificationService = notificationService;
           this.orderEventRepository = orderEventRepository;
       }

       @RabbitListener(queues = "${notification.new-orders-queue}")
       public void handle(OrderCreatedEvent event) {
           if (orderEventRepository.existsByEventId(event.eventId())) {
               log.warn("Received duplicate OrderCreatedEvent with eventId: {}", event.eventId());
               return;
           }
           log.info("Received a OrderCreatedEvent with orderNumber:{}: ", event.orderNumber());
           notificationService.sendOrderCreatedNotification(event);
           var orderEvent = new OrderEventEntity(event.eventId());
           orderEventRepository.save(orderEvent);
       }

       @RabbitListener(queues = "${notification.delivered-orders-queue}")
       public void handle(OrderDeliveredEvent event) {
           if (orderEventRepository.existsByEventId(event.eventId())) {
               log.warn("Received duplicate OrderDeliveredEvent with eventId: {}", event.eventId());
               return;
           }
           log.info("Received a OrderDeliveredEvent with orderNumber:{}: ", event.orderNumber());
           notificationService.sendOrderDeliveredNotification(event);
           var orderEvent = new OrderEventEntity(event.eventId());
           orderEventRepository.save(orderEvent);
       }

       @RabbitListener(queues = "${notification.cancelled-orders-queue}")
       public void handle(OrderCancelledEvent event) {
           if (orderEventRepository.existsByEventId(event.eventId())) {
               log.warn("Received duplicate OrderCancelledEvent with eventId: {}", event.eventId());
               return;
           }
           notificationService.sendOrderCancelledNotification(event);
           log.info("Received a OrderCancelledEvent with orderNumber:{}: ", event.orderNumber());
           var orderEvent = new OrderEventEntity(event.eventId());
           orderEventRepository.save(orderEvent);
       }

       @RabbitListener(queues = "${notification.error-orders-queue}")
       public void handle(OrderErrorEvent event) {
           if (orderEventRepository.existsByEventId(event.eventId())) {
               log.warn("Received duplicate OrderErrorEvent with eventId: {}", event.eventId());
               return;
           }
           log.info("Received a OrderErrorEvent with orderNumber:{}: ", event.orderNumber());
           notificationService.sendOrderErrorEventNotification(event);
           OrderEventEntity orderEvent = new OrderEventEntity(event.eventId());
           orderEventRepository.save(orderEvent);
       }
   }
   */
