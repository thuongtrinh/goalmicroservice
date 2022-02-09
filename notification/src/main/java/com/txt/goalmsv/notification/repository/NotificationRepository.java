package com.txt.goalmsv.notification.repository;

import com.txt.goalmsv.notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
}
