package com.ivlev.javaspringstore.service;

import com.ivlev.javaspringstore.entity.Order;
import com.ivlev.javaspringstore.entity.OrderProductRelation;
import com.ivlev.javaspringstore.entity.User;
import com.ivlev.javaspringstore.feign.FeignImpl;
import com.ivlev.javaspringstore.model.CallOrderDto;
import com.ivlev.javaspringstore.model.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final FeignImpl feign;

    @Value("${bot.key}")
    private String key;
    public void sendOrderInfo(User user, Order order, List<OrderProductRelation> orderProductRelations) {
        StringBuilder relations = new StringBuilder();
        orderProductRelations.forEach(relation -> {
            relations.append(relation.getProduct().getName());
            relations.append(" - ");
            relations.append(relation.getRelationPrice());
            relations.append(" руб. - ");
            relations.append(relation.getRelationQuantity());
            relations.append(" шт.\\n");
        });
        String textTemplate = "Новый заказ номер: %d\nПользователь: %s\nТелефон: %s\nАдрес: %s\nТовары:\n%s\n";
        String message = String.format(textTemplate, order.getOrderNumber(), user.getName(), user.getPhoneNumber(),
                user.getAddress(), relations);
        feign.sendMessage(MessageDto.builder().text(message).key(key).build());
    }
    public void callOrder(CallOrderDto callOrderDto) {
        StringBuilder message = new StringBuilder();
        message.append("Заказ звонка! \\n");
        message.append("Номер телефона: ").append(callOrderDto.getPhone()).append("\\n");
        message.append("Имя: ").append(callOrderDto.getName()).append("\\n");
        message.append("Описание: \\n").append(callOrderDto.getMessage()).append("\\n");
        feign.sendMessage(MessageDto.builder().text(message.toString()).key(key).build());
    }
}
