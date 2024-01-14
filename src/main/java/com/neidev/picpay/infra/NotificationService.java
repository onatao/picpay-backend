package com.neidev.picpay.infra;

import com.neidev.picpay.domain.core.user.model.User;
import com.neidev.picpay.handler.exception.NotificationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class NotificationService {

    private final String NOTIFICATION_SERVER = "https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6";

    private final RestTemplate restTemplate;

    public NotificationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendNotification(User user, String message) throws Exception {
        try {
            if (!isNotificationSend())
                throw new NotificationException("Theres a problem sending confirmation email");
        } catch (NotificationException e) {
            throw new NotificationException(e.getMessage());
        }
    }

    boolean isNotificationSend() {
        var response = restTemplate.getForEntity(NOTIFICATION_SERVER, Map.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            var message = (String) response.getBody().get("message");
            return "true".equalsIgnoreCase(message);
        }
        return false;
    }
}
