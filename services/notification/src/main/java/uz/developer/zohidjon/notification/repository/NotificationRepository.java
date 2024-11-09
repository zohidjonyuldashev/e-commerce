package uz.developer.zohidjon.notification.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import uz.developer.zohidjon.notification.entity.Notification;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {
}
