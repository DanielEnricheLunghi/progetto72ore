import React from 'react';
import { NotificationDto } from '../types';

interface Props {
  notification: NotificationDto;
  onClick?: (id: string) => void;
}

const NotificationItem: React.FC<Props> = ({ notification, onClick }) => {
  return (
    <div
      className={`p-2 border-b cursor-pointer hover:bg-gray-50 ${
        !notification.read ? 'bg-blue-50' : ''
      }`}
      onClick={() => onClick && onClick(notification.id)}
    >
      <p className="font-semibold">{notification.title}</p>
      <p className="text-sm text-gray-500">{notification.message}</p>
      <p className="text-xs text-gray-400">{new Date(notification.createdAt).toLocaleString()}</p>
    </div>
  );
};

export default NotificationItem;
