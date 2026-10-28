package br.com.aula5.demo.order;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping("/{id}/status")
    public Order changeStatus(@PathVariable String id, @RequestBody ChangeStatusRequest request) {
        return service.changeStatus(id, request.status());
    }

    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleNotFound(OrderNotFoundException exception) {
        return new ErrorResponse(exception.getMessage());
    }

    public record ChangeStatusRequest(OrderStatus status) {
    }

    public record ErrorResponse(String message) {
    }
}
