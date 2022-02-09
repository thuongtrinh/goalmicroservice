package com.txt.goalmsv.clients.notification;

public record NotificationRequest(Integer toCustomerId, String toCustomerName, String message) {
}
