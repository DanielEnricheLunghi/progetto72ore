import React from 'react';
import { NotificationDto } from '../types';
import NotificationItem from './NotificationItem';

interface Props {
  notifications: NotificationDto[];
  onClickItem?: (id: string) => void;
}

const NotificationList: React.FC<Props> = ({ notifications, onClickItem }) => {
  if (!notifications.length) return <p className="text-gray-400 p-2">Nessuna notifica.</p>;

  return (
    <div className="flex flex-col">
      {notifications.map(n => (
        <NotificationItem key={n.id} notification={n} onClick={onClickItem} />
      ))}
    </div>
  );
};

export default NotificationList;
