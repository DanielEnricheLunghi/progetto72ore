import React from 'react';
import { useNotifications } from '../hooks/useNotifications';
import NotificationList from '../components/NotificationList';
import NotificationBell from '../components/NotificationBell';

interface Props {
  userId: string;
}

const NotificationPage: React.FC<Props> = ({ userId }) => {
  const { notifications, loading, markAsRead, unreadCount } = useNotifications(userId);

  if (loading) return <p>Caricamento notifiche...</p>;

  return (
    <div className="p-4">
      <div className="flex items-center justify-between mb-4">
        <h2 className="text-xl font-bold">Notifiche</h2>
        <NotificationBell unreadCount={unreadCount} onClick={() => alert('Apri notifiche')} />
      </div>
      <NotificationList
        notifications={notifications}
        onClickItem={markAsRead}
      />
    </div>
  );
};

export default NotificationPage;
