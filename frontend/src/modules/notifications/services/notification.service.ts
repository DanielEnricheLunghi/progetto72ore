import axios from 'axios';
import { NotificationDto } from '../types';

const API_URL = import.meta.env.VITE_API_URL || 'http://localhost:8080/api/notifications';

class NotificationService {
  static async getNotifications(userId: string): Promise<NotificationDto[]> {
    const response = await axios.get(`${API_URL}/user/${userId}`);
    return response.data;
  }

  static async markAsRead(notificationId: string): Promise<void> {
    await axios.post(`${API_URL}/${notificationId}/read`);
  }

  static async createNotification(data: Partial<NotificationDto>): Promise<NotificationDto> {
    const response = await axios.post(`${API_URL}`, data);
    return response.data;
  }
}

export default NotificationService;
