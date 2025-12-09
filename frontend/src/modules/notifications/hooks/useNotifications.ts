import { useState, useEffect, useCallback } from 'react';
import { NotificationDto } from '../types';
import NotificationService from '../services/notification.service';

export const useNotifications = (userId: string) => {
  const [notifications, setNotifications] = useState<NotificationDto[]>([]);
  const [loading, setLoading] = useState(false);

  const loadNotifications = useCallback(async () => {
    setLoading(true);
    try {
      const data = await NotificationService.getNotifications(userId);
      setNotifications(data);
    } finally {
      setLoading(false);
    }
  }, [userId]);

  const markAsRead = useCallback(async (id: string) => {
    await NotificationService.markAsRead(id);
    setNotifications(prev =>
      prev.map(n => (n.id === id ? { ...n, read: true } : n))
    );
  }, []);

  useEffect(() => {
    loadNotifications();
  }, [loadNotifications]);

  return {
    notifications,
    loading,
    loadNotifications,
    markAsRead,
    unreadCount: notifications.filter(n => !n.read).length,
  };
};
